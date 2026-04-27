package kr.eme.prcMission.managers

import io.papermc.paper.scoreboard.numbers.NumberFormat
import kr.eme.prcMission.enums.MissionVersion
import kr.eme.prcMission.objects.models.Mission
import kr.eme.prcMoney.managers.MoneyManager
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextColor
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.scoreboard.Criteria
import org.bukkit.scoreboard.DisplaySlot
import org.bukkit.scoreboard.Scoreboard
import java.util.*
import java.util.concurrent.ConcurrentHashMap

object HudManager {
    private val playerBoards = ConcurrentHashMap<UUID, Scoreboard>()

    private const val OBJECTIVE_NAME = "prc_mission_hud"

    private val TITLE = Component.empty()

    // 색상 팔레트
    private val COLOR_BLUE   = TextColor.color(0x86, 0xD6, 0xDF)  // #86d6df
    private val COLOR_RED    = TextColor.color(0xE3, 0x38, 0x29)  // #e33829
    private val COLOR_GRAY   = TextColor.color(0xD5, 0xD5, 0xD5)  // #d5d5d5
    private val COLOR_PURPLE = TextColor.color(0xD6, 0xAA, 0xFF)  // #d6aaff
    private val COLOR_GOLD   = TextColor.color(255, 215, 0)
    private val COLOR_WHITE  = NamedTextColor.WHITE
    private val COLOR_HINT   = TextColor.color(140, 140, 140)
    private val COLOR_DONE   = TextColor.color(120, 230, 120)

    // 고정 줄 수 (V2 m7 출입모듈 설치 - 보상 5개 + EP/힌트 포함 기준)
    private const val FIXED_LINES = 13

    // 버전별 상단 장식 아이콘
    private fun headerIcon(version: MissionVersion): String = when (version) {
        MissionVersion.V1 -> "\u342F\u3450"
        MissionVersion.V2 -> "\u342F\u3451"
    }

    // 버전별 보상 아이콘
    private fun rewardIcon(version: MissionVersion): String = when (version) {
        MissionVersion.V1 -> "\u3406"
        MissionVersion.V2 -> "\u3405"
    }

    // 버전별 진행도 색상
    private fun progressColor(version: MissionVersion): TextColor = when (version) {
        MissionVersion.V1 -> COLOR_BLUE    // #86d6df
        MissionVersion.V2 -> COLOR_PURPLE  // #d6aaff
    }

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
            if (idx < 0) continue
            val mission = MissionManager.getCurrent(version, idx) ?: continue
            return version to mission
        }
        return null
    }

    private fun update(player: Player) {
        val active = resolveActiveMission()
        if (active == null) {
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

        val content = mutableListOf<Component>()

        // 상단 장식 아이콘 (V1: \u342F\u3450, V2: \u342F\u3451)
        content += Component.text(headerIcon(version), COLOR_WHITE)

        // 제목
        content += Component.text(mission.title, COLOR_WHITE)

        // 빈 줄
        content += Component.empty()

        // 진행도 섹션
        val beforeSize = content.size
        if (cond.goal != null) {
            val goal = cond.goal
            val cur = progress.progressCount.coerceAtMost(goal)
            content += Component.text("진행도 ", COLOR_GRAY)
                .append(Component.text("$cur / $goal", progressColor(version)))
        } else {
            cond.values.forEach { v ->
                val desc = cond.descriptions[v] ?: return@forEach
                val done = progress.completedConditions.contains(v)
                val mark = if (done) "✔ " else "✘ "
                val color = if (done) COLOR_DONE else COLOR_RED
                content += Component.text(mark + desc, color)
            }
        }

        // 진행도가 있을 때만 빈 줄 추가 (없으면 제목 아래 빈 줄 1개로 충분)
        if (content.size > beforeSize) {
            content += Component.empty()
        }

        // 보상 섹션
        content += buildRewardLines(version, mission)

        // 힌트 (항상 맨 아래) + 그 위에 EP 표시
        val epLine = Component.text("보유 EP ", COLOR_GRAY)
            .append(Component.text("${MoneyManager.getMoney()}", COLOR_GOLD))
        val hintLine = Component.text("통신 모듈에서 자세한 내용/팁 확인 가능", COLOR_RED)

        // 패딩: 보상 아래 ~ EP 줄 사이를 빈 줄로 채워서 고정 크기 유지
        // 하단 고정: [패딩...] + EP + 빈 줄 + 힌트 (총 3줄 추가)
        val bottomFixedLines = 3
        val currentSize = content.size + bottomFixedLines
        val padding = (FIXED_LINES - currentSize).coerceAtLeast(1)
        repeat(padding) { content += Component.empty() }

        content += epLine
        content += Component.empty()
        content += hintLine
        return content
    }

    private fun buildRewardLines(version: MissionVersion, mission: Mission): List<Component> {
        val raw = mission.rewardDescription.takeIf { it.isNotBlank() }
            ?: if (mission.reward.ep > 0) "${mission.reward.ep} EP" else return emptyList()
        val parts = raw.split(",").map { stripColors(it) }.filter { it.isNotBlank() }
        if (parts.isEmpty()) return emptyList()

        val result = mutableListOf<Component>()
        result += Component.text(rewardIcon(version), COLOR_GOLD)
        parts.forEach { part ->
            result += Component.text("- ", COLOR_GRAY)
                .append(Component.text(part, COLOR_GOLD))
        }
        return result
    }

    private fun stripColors(s: String): String =
        s.replace(Regex("§[0-9a-fk-orA-FK-OR]"), "").trim()

    private fun invisibleEntry(index: Int): String {
        val hex = "0123456789abcdef"
        return "§${hex[index % 16]}§r"
    }
}
