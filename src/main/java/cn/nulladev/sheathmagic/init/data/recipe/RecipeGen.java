package cn.nulladev.sheathmagic.init.data.recipe;

import cn.nulladev.sheathmagic.init.SheathMagic;
import cn.nulladev.sheathmagic.init.registrate.SMItems;
import dev.xkmc.l2library.repack.registrate.providers.RegistrateRecipeProvider;
import dev.xkmc.l2library.repack.registrate.util.DataIngredient;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.function.BiFunction;

public class RecipeGen {

	public static void genMachineCoreOutputRecipe(RegistrateRecipeProvider provider) {
        SheathMagic.LOGGER.info("Start Recipe Gen");
		String currentFolder = "machine_core_output/";
		unlock(provider, new MachineCoreOutputRecipeBuilder(SMItems.CONCEPT_CORE_COBBLESTONE.asStack())::unlockedBy, SMItems.CONCEPT_CORE_COBBLESTONE.get())
				.addOutput(Items.COBBLESTONE.getDefaultInstance(), 100)
				.save(provider, getID(currentFolder + "cobblestone"));
	}

	private static <T> T unlock(RegistrateRecipeProvider provider, BiFunction<String, InventoryChangeTrigger.TriggerInstance, T> func, Item item) {
		return func.apply("has_" + provider.safeName(item), DataIngredient.items(item).getCritereon(provider));
	}

	private static ResourceLocation getID(String suffix) {
		return new ResourceLocation(SheathMagic.MODID, suffix);
	}
}
