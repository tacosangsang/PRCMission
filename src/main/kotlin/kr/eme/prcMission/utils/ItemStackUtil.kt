package kr.eme.prcMission.utils

import kr.eme.prcMission.enums.MissionVersion
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta

object ItemStackUtil {
    fun build(material: Material, block: (ItemMeta) -> Unit): ItemStack {
        val item = ItemStack(material)
        val meta = item.itemMeta
        block(meta)
        item.itemMeta = meta
        return item
    }

    fun rewardItem(
        material: Material,
        name: String = "",
        lore: List<String>? = null,
        customModelData: Int? = null,
        amount: Int = 1
    ): ItemStack {
        return ItemStack(material, amount).apply {
            itemMeta = itemMeta?.apply {
                if (name.isNotBlank()) setDisplayName(name)
                if (!lore.isNullOrEmpty()) {
                    this.lore = lore
                } else {
                    this.lore = null
                }
                if (customModelData != null) setCustomModelData(customModelData)
            }
        }
    }




    // 네비게이션 버튼
    fun leftButton(name: String): ItemStack = build(Material.BROWN_DYE) { meta ->
        meta.setDisplayName(name)
        meta.setCustomModelData(1)
    }

    fun rightButton(name: String): ItemStack = build(Material.BROWN_DYE) { meta ->
        meta.setDisplayName(name)
        meta.setCustomModelData(2)
    }

    private const val TIP_COLOR = "§x§F§A§8§2§3§7" // #FA8237

    fun withLore(
        material: Material,
        title: String,
        description: String,
        rewardDescription: String,
        customModel: Int,
        status: String,
        tutorialLink: String = ""
    ): ItemStack = build(material) { meta ->
        meta.setDisplayName(title)
        meta.lore = buildList {
            add("§7$description")
            add("§f보상: $rewardDescription")
            if (tutorialLink.isNotBlank()) {
                add("${TIP_COLOR}홈 모듈에 배치된 가상훈련 모듈에서 [$tutorialLink]을 참고하세요.")
            }
            add(" ")
            add(status)
        }
        meta.setCustomModelData(customModel)
    }

    fun iconDone(title: String, version: MissionVersion, description: String, reward: String, tutorialLink: String = ""): ItemStack {
        return when (version) {
            MissionVersion.V1 -> withLore(Material.BROWN_DYE, title, description, reward, 3, "§a이미 완료된 미션입니다.", tutorialLink)
            MissionVersion.V2 -> withLore(Material.BROWN_DYE, title, description, reward, 24, "§a이미 완료된 미션입니다.", tutorialLink)
        }
    }

    fun iconProgress(title: String, version: MissionVersion, description: String, reward: String, tutorialLink: String = ""): ItemStack {
        return when (version) {
            MissionVersion.V1 -> withLore(Material.BROWN_DYE, title, description, reward, 5, "§e진행중인 미션입니다.", tutorialLink)
            MissionVersion.V2 -> withLore(Material.BROWN_DYE, title, description, reward, 26, "§e진행중인 미션입니다.", tutorialLink)
        }
    }

    fun iconAcceptable(title: String, version: MissionVersion, description: String, reward: String, tutorialLink: String = ""): ItemStack {
        return when (version) {
            MissionVersion.V1 -> withLore(Material.BROWN_DYE, title, description, reward, 7, "§e수락 가능한 미션입니다.", tutorialLink)
            MissionVersion.V2 -> withLore(Material.BROWN_DYE, title, description, reward, 28, "§e수락 가능한 미션입니다.", tutorialLink)
        }
    }

    fun iconLock(title: String, version: MissionVersion, description: String, reward: String, tutorialLink: String = ""): ItemStack {
        return when (version) {
            MissionVersion.V1 -> withLore(Material.BROWN_DYE, title, description, reward, 7, "§c받지 않은 미션입니다.", tutorialLink)
            MissionVersion.V2 -> withLore(Material.BROWN_DYE, title, description, reward, 28, "§c받지 않은 미션입니다.", tutorialLink)
        }
    }

    fun iconRewardPending(title: String, version: MissionVersion, description: String, reward: String, tutorialLink: String = ""): ItemStack {
        return when (version) {
            MissionVersion.V1 -> withLore(Material.BROWN_DYE, title, description, reward, 3, "§a보상을 수령할 수 있습니다.", tutorialLink)
            MissionVersion.V2 -> withLore(Material.BROWN_DYE, title, description, reward, 24, "§a보상을 수령할 수 있습니다.", tutorialLink)
        }
    }

    fun iconSpoiler(version: MissionVersion): ItemStack {
        return when (version) {
            MissionVersion.V1 -> withLore(Material.BROWN_DYE, "§f???", "이전 임무를 완료하세요", "???", 7, "§c받지 않은 미션입니다.")
            MissionVersion.V2 -> withLore(Material.BROWN_DYE, "§f???", "이전 임무를 완료하세요", "???", 28, "§c받지 않은 미션입니다.")
        }
    }
}
