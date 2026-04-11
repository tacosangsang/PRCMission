package kr.eme.prcMission.managers

import io.papermc.paper.scoreboard.numbers.NumberFormat
import kr.eme.prcMission.enums.MissionVersion
import kr.eme.prcMission.main
import kr.eme.prcMission.objects.models.Mission
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextColor
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.scoreboard.Criteria
import org.bukkit.scoreboard.DisplaySlot
import org.bukkit.scoreboard.Scoreboard
import java.util.UUID
import java.util.concurrent.ConcurrentHashMap

object HudManager {
    private val playerBoards = ConcurrentHashMap<UUID, Scoreboard>()

    private const val OBJECTIVE_NAME = "prc_mission_hud"

    private val TITLE = Component.text("미션 트래커", TextColor.color(255, 215, 0), TextDecoration.BOLD)

    private val LABEL_COLOR = TextColor.color(180, 200, 255)
    private val MISSION_COLOR = NamedTextColor.WHITE
    private val PROGRESS_COLOR = TextColor.color(180, 230, 180)
    private val DONE_COLOR = TextColor.color(120, 230, 120)
    private val UNDONE_COLOR = TextColor.color(230, 120, 120)
    private val REWARD_COLOR = TextColor.color(255, 220, 130)
    private val HINT_COLOR = TextColor.color(140, 140, 140)
    private val SEP_COLOR = TextColor.color(80, 80, 80)

    fun start() {
        for (player in Bukkit.getOnlinePlayers()) update(player)
    }

    fun stop() {
        for (player in Bukkit.getOnlinePlayers()) {
            player.scoreboard = Bukkit.getScoreboardManager().newScoreboard
        }
        playerBoards.clear()
    }

    fun show(player: Player) {
        update(player)
    }

    fun hide(player: Player) {
        playerBoards.remove(player.uniqueId)
    }

    fun refreshAll() {
        val online = Bukkit.getOnlinePlayers().map { it.uniqueId }.toSet()
        playerBoards.keys.removeAll { it !in online }
        for (player in Bukkit.getOnlinePlayers()) update(player)
    }

    private fun resolveActiveMission(): Pair<MissionVersion, Mission>? {
        for (version in MissionVersion.entries) {
            if (!MissionStateManager.canStart(version)) continue
            if (MissionStateManager.isVersionCleared(version)) continue
            val idx = MissionStateManager.getCurrentIndex(version)
            if (idx < 0) continue // 미션 수락 전에는 표시하지 않음
            val mission = MissionManager.getCurrent(version, idx) ?: continue
            return version to mission
        }
        return null
    }

    private fun update(player: Player) {
        val active = resolveActiveMission()
        if (active == null) {
            // 진행 중인 미션 없음 → 사이드바 숨김
            if (playerBoards.remove(player.uniqueId) != null) {
                player.scoreboard = Bukkit.getScoreboardManager().newScoreboard
            }
            return
        }

        val sb = playerBoards.getOrPut(player.uniqueId) {
            Bukkit.getScoreboardManager().newScoreboard.also { newSb ->
                val obj = newSb.registerNewObjective(OBJECTIVE_NAME, Criteria.DUMMY, TITLE)
                obj.displaySlot = DisplaySlot.SIDEBAR
                player.scoreboard = newSb
            }
        }
        val obj = sb.getObjective(OBJECTIVE_NAME) ?: return

        for (entry in sb.entries.toList()) {
            sb.resetScores(entry)
        }

        val lines = buildLines(active)

        for ((index, comp) in lines.withIndex()) {
            val entry = invisibleEntry(index)
            val score = obj.getScore(entry)
            score.score = lines.size - index
            score.customName(comp)
            score.numberFormat(NumberFormat.blank())
        }

        if (player.scoreboard != sb) player.scoreboard = sb
    }

    private fun buildLines(active: Pair<MissionVersion, Mission>): List<Component> {
        val (version, mission) = active
        val progress = MissionStateManager.getProgress(version, mission.id)
        val cond = mission.condition

        val lines = mutableListOf<Component>()
        lines += Component.empty()
        lines += Component.text("[${version.name}] ", LABEL_COLOR)
            .append(Component.text(mission.title, MISSION_COLOR))
        lines += Component.empty()

        if (cond.goal != null) {
            // 누적형: 진행도 표시
            val goal = cond.goal!!
            val cur = progress.progressCount.coerceAtMost(goal)
            lines += Component.text("진행도 ", LABEL_COLOR)
                .append(Component.text("$cur / $goal", PROGRESS_COLOR))
        } else {
            // 체크리스트형: 각 조건별 V/X
            cond.values.forEach { v ->
                val desc = cond.descriptions[v] ?: return@forEach
                val done = progress.completedConditions.contains(v)
                val mark = if (done) "✔ " else "✘ "
                val color = if (done) DONE_COLOR else UNDONE_COLOR
                lines += Component.text(mark + desc, color)
            }
        }

        lines += buildRewardLines(mission)

        lines += Component.empty()
        lines += Component.text("통신 모듈에서 진행상황 확인 가능", HINT_COLOR)
        return lines
    }

    private fun buildRewardLines(mission: Mission): List<Component> {
        val raw = mission.rewardDescription.takeIf { it.isNotBlank() }
            ?: if (mission.reward.ep > 0) "${mission.reward.ep} EP" else return emptyList()
        val parts = raw.split(",").map { stripColors(it) }.filter { it.isNotBlank() }
        if (parts.isEmpty()) return emptyList()

        val result = mutableListOf<Component>()
        result += Component.text("보상", LABEL_COLOR)
        parts.forEach { part ->
            result += Component.text(" • ", LABEL_COLOR)
                .append(Component.text(part, REWARD_COLOR))
        }
        return result
    }

    private fun stripColors(s: String): String =
        s.replace(Regex("§[0-9a-fk-orA-FK-OR]"), "").trim()

    // 각 entry 를 unique 하게 만들기 위한 invisible prefix
    private fun invisibleEntry(index: Int): String {
        val hex = "0123456789abcdef"
        return "§${hex[index % 16]}§r"
    }
}
