package kr.eme.prcMission.objects.items



import kr.eme.prcMission.main
import kr.eme.prcShop.api.PRCItems
import org.bukkit.NamespacedKey
import org.bukkit.attribute.Attribute
import org.bukkit.attribute.AttributeModifier
import org.bukkit.inventory.EquipmentSlotGroup
import kr.eme.prcMission.objects.models.ItemReward as IR
import org.bukkit.Material as M

object ItemTemplates {
    // material
    // HIDE_ATTRIBUTES applied by default in ItemReward

    val ironOre = IR(PRCItems.IRON)

    val test = PRCItems.COPPER_INGOT

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
    val coffeeModule = IR(PRCItems.COFFEE_MACHINE_MODULE)

    // recipe
    val ironIngotRecipe = IR(PRCItems.RECIPE_IRON_INGOT)
    val ALCURecipe = IR(PRCItems.RECIPE_AL_CU_ALLOY)

    // other
    val mug = IR(PRCItems.MUG)
    val nutrientCapsule = IR(PRCItems.NUTRITION_CAPSULE)
    val growthCapsule = IR(PRCItems.GROWTH_CAPSULE)
    val weedkillerCapsule = IR(PRCItems.HERBICIDE_CAPSULE)
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
}
