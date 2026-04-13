package kr.eme.prcMission.objects.missions.rewardPresets

import kr.eme.prcMission.objects.models.Reward
import kr.eme.prcMission.objects.items.ItemTemplates as IT

object RewardPresetV2 {
    val m1_reward = Reward(
        items = listOf(
            IT.ironIngot.copy(amount = 20),
            IT.copperIngot.copy(amount = 20)
        )
    )
    val m2_reward = Reward(
        items = listOf(
            IT.nutrientCapsule.copy(amount = 16),
            IT.growthCapsule.copy(amount = 16),
            IT.weedkillerCapsule.copy(amount = 10)
        )
    )
    val m3_reward = Reward(
        items = listOf(
            IT.ALCURecipe.copy(amount = 1),
            IT.ALMGRecipe.copy(amount = 1)
        )
    )
    val m4_reward = Reward(
        items = listOf(IT.ALCUIngot.copy(amount = 10))
    )
    val m5_reward = Reward(
        items = listOf(IT.CUAUIngot.copy(amount = 10))
    )
    val m6_reward = Reward(7000)
    val m7_reward = Reward(
        items = listOf(
            IT.coffeeBeanSeed.copy(amount = 32),
            IT.mug.copy(amount = 16),
            IT.growthCapsule.copy(amount = 16),
            IT.nutrientCapsule.copy(amount = 16),
            IT.ironHelmet.copy(amount = 1),
            IT.ironChestplate.copy(amount = 1),
            IT.ironLeggings.copy(amount = 1),
            IT.ironBoots.copy(amount = 1)
        )
    )
    val m8_reward = Reward(4000)
    val m9_reward = Reward(
        items = listOf(
            IT.copperIngot.copy(amount = 10),
            IT.platinumIngot.copy(amount = 3)
        )
    )
    val m10_reward = Reward(5000)
    val m11_reward = Reward(
        items = listOf(IT.ironIngot.copy(amount = 35))
    )
    val m12_reward = Reward(
        items = listOf(
            IT.coffeeModule.copy(amount = 2),
            IT.alloyDrill.copy(amount = 1),
            IT.alloyCoolingMold.copy(amount = 1)
        )
    )
    val m13_reward = Reward(6500)
    val m14_reward = Reward(
        items = listOf(
            IT.copperIngot.copy(amount = 20),
            IT.lithiumIngot.copy(amount = 16),
            IT.platinumIngot.copy(amount = 10)
        )
    )
    val m15_reward = Reward(
        items = listOf(
            IT.ironIngot.copy(amount = 20),
            IT.titaniumIngot.copy(amount = 3)
        )
    )
    val m16_reward = Reward(5000)
    val m17_reward = Reward(6000)
    val m18_reward = Reward(
        items = listOf(
            IT.platinumIngot.copy(amount = 5),
            IT.nickelIngot.copy(amount = 5),
            IT.titaniumIngot.copy(amount = 3)
        )
    )
    val m19_reward = Reward(6500)
    val m20_reward = Reward(
        items = listOf(IT.endingItem.copy(amount = 1))
    )
}
