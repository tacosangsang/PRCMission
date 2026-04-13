package kr.eme.prcMission.objects.missions.rewardPresets

import kr.eme.prcMission.objects.models.Reward
import kr.eme.prcMission.objects.items.ItemTemplates as IT

object RewardPresetV1 {
    val m1_reward = Reward(
        items = listOf(IT.ironIngot.copy(amount = 20))
    )
    val m2_reward = Reward(
        items = listOf(IT.ironIngot.copy(amount = 15))
    )
    val m3_reward = Reward(
        items = listOf(IT.printerModule.copy(amount = 1))
    )
    val m4_reward = Reward(
        items = listOf(
            IT.ironIngot.copy(amount = 5),
            IT.aluminumIngot.copy(amount = 10)
        )
    )
    val m5_reward = Reward(490)
    val m6_reward = Reward(
        items = listOf(IT.mineModule.copy(amount = 1))
    )
    val m7_reward = Reward(800)
    val m8_reward = Reward(1000)
    val m9_reward = Reward(
        items = listOf(
            IT.grinderModule.copy(amount = 2),
            IT.furnaceModule.copy(amount = 1)
        )
    )
    val m10_reward = Reward(750)
    val m11_reward = Reward(
        items = listOf(IT.ironIngotRecipe.copy(amount = 1))
    )
    val m12_reward = Reward(
        items = listOf(
            IT.ironIngot.copy(amount = 5),
            IT.copperIngot.copy(amount = 10),
            IT.lithiumIngot.copy(amount = 10)
        )
    )
    val m13_reward = Reward(
        items = listOf(
            IT.growthCapsule.copy(amount = 10),
            IT.weedkillerCapsule.copy(amount = 5)
        )
    )
    val m14_reward = Reward(
        items = listOf(
            IT.ironIngot.copy(amount = 20),
            IT.copperIngot.copy(amount = 20)
        )
    )
    val m15_reward = Reward(1500)
    val m16_reward = Reward(
        items = listOf(IT.mug.copy(amount = 10))
    )
    val m17_reward = Reward(
        items = listOf(
            IT.coffeeBeanSeed.copy(amount = 6),
            IT.mug.copy(amount = 10),
            IT.growthCapsule.copy(amount = 3),
            IT.nutrientCapsule.copy(amount = 3)
        )
    )
    val m18_reward = Reward(
        items = listOf(
            IT.copperIngot.copy(amount = 15),
            IT.lithiumIngot.copy(amount = 7)
        )
    )
    val m19_reward = Reward(2500)
    val m20_reward = Reward(5000)
}
