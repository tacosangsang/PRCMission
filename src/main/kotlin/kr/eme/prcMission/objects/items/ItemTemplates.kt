package kr.eme.prcMission.objects.items



import kr.eme.prcShop.api.PRCItems
import kr.eme.prcMission.objects.models.ItemReward as IR
import org.bukkit.Material as M
import org.bukkit.NamespacedKey
import org.bukkit.attribute.Attribute
import org.bukkit.attribute.AttributeModifier
import org.bukkit.inventory.EquipmentSlotGroup
import kr.eme.prcMission.main

object ItemTemplates {
    // material
    // HIDE_ATTRIBUTES applied by default in ItemReward

    val ironOre = IR(PRCItems.IRON)

    val aluminumIngot = IR(PRCItems.ALUMINUM_INGOT)
    val ironIngot = IR(PRCItems.IRON_INGOT)
    val copperIngot = IR(PRCItems.COPPER_INGOT)
    val lithiumIngot = IR(PRCItems.LITHIUM_INGOT)
    val platinumIngot = IR(PRCItems.PLATINUM_INGOT)
    val nickelIngot = IR(PRCItems.NICKEL_INGOT)
    val titaniumIngot = IR(PRCItems.TITANIUM_INGOT)
    val ALCUIngot = IR(PRCItems.AL_CU_ALLOY_INGOT)
    val CUAUIngot = IR(PRCItems.CU_AU_ALLOY_INGOT)

    // module
    val furnaceModule = IR(PRCItems.FURNACE_MODULE)

    // recipe
    val ironIngotRecipe = IR(PRCItems.RECIPE_IRON_INGOT)
    val ALCURecipe = IR(PRCItems.RECIPE_AL_CU_ALLOY)

    // other
    val mug = IR(PRCItems.MUG)
    val nutrientCapsule = IR(PRCItems.NUTRITION_CAPSULE)
    val growthCapsule = IR(PRCItems.GROWTH_CAPSULE)
    val weedkillerCapsule = IR(PRCItems.HERBICIDE_CAPSULE)

    val knife = IR(M.WOODEN_SHOVEL,1,"§f나이프","",10)
    val coffee = IR(M.BOWL,1,"§f커피","",2)

    val transportationItem = IR(
        material = M.IRON_BOOTS,
        amount = 1,
        name = "§f이동수단",
        metaModifier = {
            val stepModifier = AttributeModifier(
                NamespacedKey(main, "step_height"),
                1.0,
                AttributeModifier.Operation.ADD_NUMBER,
                EquipmentSlotGroup.FEET
            )
            addAttributeModifier(Attribute.STEP_HEIGHT, stepModifier)
        }
    )
    val longSword = IR(M.WOODEN_SHOVEL, 1, "§f장도","",11)
    val drill = IR(M.WOODEN_SHOVEL, 1, "§f가벼운 채굴기", "", 2)
}
