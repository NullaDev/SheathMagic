package cn.nulladev.sheathmagic.init.data.recipe;

import cn.nulladev.sheathmagic.content.item.ItemContainable;
import cn.nulladev.sheathmagic.content.item.conceptcore.BaseConceptCore;
import cn.nulladev.sheathmagic.init.SheathMagic;
import cn.nulladev.sheathmagic.init.registrate.SMItems;
import dev.xkmc.l2library.repack.registrate.providers.RegistrateRecipeProvider;
import dev.xkmc.l2library.repack.registrate.util.DataIngredient;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Tuple;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.List;
import java.util.function.BiFunction;

public class RecipeGen {

	static String currentFolder = "machine_core_output/";

	public static void genMachineCoreOutputRecipe(RegistrateRecipeProvider provider) {
        SheathMagic.LOGGER.info("Start Recipe Gen");

		unlock(provider, new MachineCoreOutputRecipeBuilder(SMItems.CONCEPT_CORE_COBBLESTONE.asStack())::unlockedBy, SMItems.CONCEPT_CORE_COBBLESTONE.get())
				.addOutput(Items.COBBLESTONE.getDefaultInstance(), 100)
				.save(provider, getID(currentFolder + "cobblestone"));
		unlock(provider, new MachineCoreOutputRecipeBuilder(SMItems.CONCEPT_CORE_COBBLESTONE_ADVANCED.asStack())::unlockedBy, SMItems.CONCEPT_CORE_COBBLESTONE_ADVANCED.get())
				.addOutput(Items.COBBLESTONE.getDefaultInstance(), 100)
				.save(provider, getID(currentFolder + "cobblestone_advanced"));
		unlock(provider, new MachineCoreOutputRecipeBuilder(SMItems.CONCEPT_CORE_STONE.asStack())::unlockedBy, SMItems.CONCEPT_CORE_STONE.get())
				.addOutput(Items.STONE.getDefaultInstance(), 100)
				.save(provider, getID(currentFolder + "stone"));
		unlock(provider, new MachineCoreOutputRecipeBuilder(SMItems.CONCEPT_CORE_OBSIDIAN_ADVANCED.asStack())::unlockedBy, SMItems.CONCEPT_CORE_OBSIDIAN_ADVANCED.get())
				.addOutput(Items.OBSIDIAN.getDefaultInstance(), 100)
				.addOutput(Items.COBBLESTONE.getDefaultInstance(), 50)
				.save(provider, getID(currentFolder + "obsidian_advanced"));
		unlock(provider, new MachineCoreOutputRecipeBuilder(SMItems.CONCEPT_CORE_SUGAR_CANE.asStack())::unlockedBy, SMItems.CONCEPT_CORE_SUGAR_CANE.get())
				.addOutput(Items.SUGAR_CANE.getDefaultInstance(), 100)
				.save(provider, getID(currentFolder + "sugar_cane"));
		unlock(provider, new MachineCoreOutputRecipeBuilder(SMItems.CONCEPT_CORE_CACTUS.asStack())::unlockedBy, SMItems.CONCEPT_CORE_CACTUS.get())
				.addOutput(Items.CACTUS.getDefaultInstance(), 100)
				.save(provider, getID(currentFolder + "cactus"));
		unlock(provider, new MachineCoreOutputRecipeBuilder(SMItems.CONCEPT_CORE_TREE.asStack())::unlockedBy, SMItems.CONCEPT_CORE_TREE.get())
				.addOutput(Items.OAK_LOG.getDefaultInstance(), 100)
				.addOutput(Items.AZALEA.getDefaultInstance(), 50)
				.addOutput(Items.FLOWERING_AZALEA.getDefaultInstance(), 12.5)
				.addOutput(Items.WHEAT_SEEDS.getDefaultInstance(), 25)
				.addOutput(Items.MOSS_CARPET.getDefaultInstance(), 75)
				.save(provider, getID(currentFolder + "tree"));
		unlock(provider, new MachineCoreOutputRecipeBuilder(SMItems.CONCEPT_CORE_EGG.asStack())::unlockedBy, SMItems.CONCEPT_CORE_EGG.get())
				.addOutput(Items.EGG.getDefaultInstance(), 100)
				.save(provider, getID(currentFolder + "egg"));
		unlock(provider, new MachineCoreOutputRecipeBuilder(SMItems.CONCEPT_CORE_ENDER_PEARL.asStack())::unlockedBy, SMItems.CONCEPT_CORE_ENDER_PEARL.get())
				.addOutput(Items.ENDER_PEARL.getDefaultInstance(), 100)
				.save(provider, getID(currentFolder + "ender_pearl"));
		unlock(provider, new MachineCoreOutputRecipeBuilder(SMItems.CONCEPT_CORE_SNOWBALL.asStack())::unlockedBy, SMItems.CONCEPT_CORE_SNOWBALL.get())
				.addOutput(Items.SNOWBALL.getDefaultInstance(), 100)
				.save(provider, getID(currentFolder + "snowball"));
		unlock(provider, new MachineCoreOutputRecipeBuilder(SMItems.CONCEPT_CORE_COOKED_CHICKEN.asStack())::unlockedBy, SMItems.CONCEPT_CORE_COOKED_CHICKEN.get())
				.addOutput(Items.COOKED_CHICKEN.getDefaultInstance(), 100)
				.addOutput(Items.FEATHER.getDefaultInstance(), 1.0 / 3.0)
				.addOutput(Items.FEATHER.getDefaultInstance(), 1.0 / 3.0)
				.save(provider, getID(currentFolder + "cooked_chicken"));
		unlock(provider, new MachineCoreOutputRecipeBuilder(SMItems.CONCEPT_CORE_COOKED_PORKCHOP.asStack())::unlockedBy, SMItems.CONCEPT_CORE_COOKED_PORKCHOP.get())
				.addOutput(Items.COOKED_PORKCHOP.getDefaultInstance(), 100)
				.addOutput(Items.COOKED_PORKCHOP.getDefaultInstance(), 100)
				.addOutput(Items.COOKED_PORKCHOP.getDefaultInstance(), 1.0 / 3.0)
				.addOutput(Items.COOKED_PORKCHOP.getDefaultInstance(), 1.0 / 3.0)
				.save(provider, getID(currentFolder + "cooked_porkchop"));
		unlock(provider, new MachineCoreOutputRecipeBuilder(SMItems.CONCEPT_CORE_GLOW_BERRIES.asStack())::unlockedBy, SMItems.CONCEPT_CORE_GLOW_BERRIES.get())
				.addOutput(Items.GLOW_BERRIES.getDefaultInstance(), 100)
				.save(provider, getID(currentFolder + "glow_berries"));
		unlock(provider, new MachineCoreOutputRecipeBuilder(SMItems.CONCEPT_CORE_BONE_MEAL.asStack())::unlockedBy, SMItems.CONCEPT_CORE_BONE_MEAL.get())
				.addOutput(Items.BONE_MEAL.getDefaultInstance(), 100)
				.save(provider, getID(currentFolder + "bone_meal"));
		unlock(provider, new MachineCoreOutputRecipeBuilder(SMItems.CONCEPT_CORE_BONE_MEAL_ADVANCED.asStack())::unlockedBy, SMItems.CONCEPT_CORE_BONE_MEAL_ADVANCED.get())
				.addOutput(Items.BONE_MEAL.getDefaultInstance(), 100)
				.save(provider, getID(currentFolder + "bone_meal_advanced"));
		unlock(provider, new MachineCoreOutputRecipeBuilder(SMItems.CONCEPT_CORE_GHAST.asStack())::unlockedBy, SMItems.CONCEPT_CORE_GHAST.get())
				.addOutput(Items.GHAST_TEAR.getDefaultInstance(), 50)
				.addOutput(Items.GUNPOWDER.getDefaultInstance(), 50)
				.addOutput(Items.GUNPOWDER.getDefaultInstance(), 50)
				.save(provider, getID(currentFolder + "ghast"));
		unlock(provider, new MachineCoreOutputRecipeBuilder(SMItems.CONCEPT_CORE_GOLD.asStack())::unlockedBy, SMItems.CONCEPT_CORE_GOLD.get())
				.addOutput(Items.GOLDEN_SWORD.getDefaultInstance(), 8.5)
				.addOutput(Items.GOLD_INGOT.getDefaultInstance(), 2.5)
				.addOutput(Items.GOLD_NUGGET.getDefaultInstance(), 50)
				.addOutput(Items.ROTTEN_FLESH.getDefaultInstance(), 50)
				.save(provider, getID(currentFolder + "gold"));
		unlock(provider, new MachineCoreOutputRecipeBuilder(SMItems.CONCEPT_CORE_IRON.asStack())::unlockedBy, SMItems.CONCEPT_CORE_IRON.get())
				.addOutput(Items.IRON_INGOT.getDefaultInstance(), 100)
				.addOutput(Items.IRON_INGOT.getDefaultInstance(), 100)
				.addOutput(Items.IRON_INGOT.getDefaultInstance(), 100)
				.addOutput(Items.IRON_INGOT.getDefaultInstance(), 50)
				.addOutput(Items.IRON_INGOT.getDefaultInstance(), 50)
				.addOutput(Items.POPPY.getDefaultInstance(), 50)
				.addOutput(Items.POPPY.getDefaultInstance(), 50)
				.save(provider, getID(currentFolder + "iron"));
		unlock(provider, new MachineCoreOutputRecipeBuilder(SMItems.CONCEPT_CORE_SHULKER_SHELL.asStack())::unlockedBy, SMItems.CONCEPT_CORE_SHULKER_SHELL.get())
				.addOutput(Items.SHULKER_SHELL.getDefaultInstance(), 100)
				.save(provider, getID(currentFolder + "shulker_shell"));
		unlock(provider, new MachineCoreOutputRecipeBuilder(SMItems.CONCEPT_CORE_MOB_GRINDER.asStack())::unlockedBy, SMItems.CONCEPT_CORE_MOB_GRINDER.get())
				.addOutput(Items.ROTTEN_FLESH.getDefaultInstance(), 0.25 / 3.0)
				.addOutput(Items.ROTTEN_FLESH.getDefaultInstance(), 0.25 / 3.0)
				.addOutput(Items.ROTTEN_FLESH.getDefaultInstance(), 0.25 / 3.0)
				.addOutput(Items.ROTTEN_FLESH.getDefaultInstance(), 0.25 / 3.0)
				.addOutput(Items.GUNPOWDER.getDefaultInstance(), 0.25 / 3.0)
				.addOutput(Items.GUNPOWDER.getDefaultInstance(), 0.25 / 3.0)
				.addOutput(Items.STRING.getDefaultInstance(), 0.25 / 3.0)
				.addOutput(Items.STRING.getDefaultInstance(), 0.25 / 3.0)
				.addOutput(Items.BONE.getDefaultInstance(), 0.25 / 3.0)
				.addOutput(Items.BONE.getDefaultInstance(), 0.25 / 3.0)
				.addOutput(Items.ARROW.getDefaultInstance(), 0.25 / 3.0)
				.addOutput(Items.ARROW.getDefaultInstance(), 0.25 / 3.0)
				.save(provider, getID(currentFolder + "mob_grinder"));

		var attachmentContent = List.of(
				new Tuple<>(Items.BLACK_CARPET, "black_carpet"),
				new Tuple<>(Items.BLUE_CARPET, "blue_carpet"),
				new Tuple<>(Items.BROWN_CARPET, "brown_carpet"),
				new Tuple<>(Items.CYAN_CARPET, "cyan_carpet"),
				new Tuple<>(Items.GRAY_CARPET, "gray_carpet"),
				new Tuple<>(Items.GREEN_CARPET, "green_carpet"),
				new Tuple<>(Items.LIGHT_BLUE_CARPET, "light_blue_carpet"),
				new Tuple<>(Items.LIGHT_GRAY_CARPET, "light_gray_carpet"),
				new Tuple<>(Items.LIME_CARPET, "lime_carpet"),
				new Tuple<>(Items.MAGENTA_CARPET, "magenta_carpet"),
				new Tuple<>(Items.ORANGE_CARPET, "orange_carpet"),
				new Tuple<>(Items.PINK_CARPET, "pink_carpet"),
				new Tuple<>(Items.PURPLE_CARPET, "purple_carpet"),
				new Tuple<>(Items.RED_CARPET, "red_carpet"),
				new Tuple<>(Items.WHITE_CARPET, "white_carpet"),
				new Tuple<>(Items.YELLOW_CARPET, "yellow_carpet"),
				new Tuple<>(Items.RAIL, "rail"),
				new Tuple<>(Items.ACTIVATOR_RAIL, "activator_rail"),
				new Tuple<>(Items.DETECTOR_RAIL, "detector_rail"),
				new Tuple<>(Items.POWERED_RAIL, "powered_rail")
		);

		for (var content : attachmentContent) {
			simpleRecipeWithContent(provider, SMItems.CONCEPT_CORE_ATTACHMENT.get(), content.getA(), "attachment_" + content.getB());
		}

		var fallingContent = List.of(
				new Tuple<>(Items.GRAVEL, "gravel"),
				new Tuple<>(Items.SAND, "sand")
		);

		for (var content : fallingContent) {
			simpleRecipeWithContent(provider, SMItems.CONCEPT_CORE_FALLING_BLOCK.get(), content.getA(), "falling_" + content.getB());
		}

		var pumpkinConceptCore = coreWithContent(SMItems.CONCEPT_CORE_MELON.get(), Items.PUMPKIN_SEEDS);
		unlock(provider, new MachineCoreOutputRecipeBuilder(pumpkinConceptCore)::unlockedBy, SMItems.CONCEPT_CORE_MELON.get())
				.addOutput(Items.PUMPKIN.getDefaultInstance(), 100)
				.save(provider, getID(currentFolder + "pumpkin"));

		var melonConceptCore = coreWithContent(SMItems.CONCEPT_CORE_MELON.get(), Items.MELON_SEEDS);
		unlock(provider, new MachineCoreOutputRecipeBuilder(melonConceptCore)::unlockedBy, SMItems.CONCEPT_CORE_MELON.get())
				.addOutput(Items.MELON_SLICE.getDefaultInstance(), 100)
				.addOutput(Items.MELON_SLICE.getDefaultInstance(), 100)
				.addOutput(Items.MELON_SLICE.getDefaultInstance(), 100)
				.addOutput(Items.MELON_SLICE.getDefaultInstance(), 20)
				.addOutput(Items.MELON_SLICE.getDefaultInstance(), 20)
				.addOutput(Items.MELON_SLICE.getDefaultInstance(), 20)
				.addOutput(Items.MELON_SLICE.getDefaultInstance(), 20)
				.addOutput(Items.MELON_SLICE.getDefaultInstance(), 20)
				.save(provider, getID(currentFolder + "melon"));

		var carrotConceptCore = coreWithContent(SMItems.CONCEPT_CORE_FARM.get(), Items.CARROT);
		unlock(provider, new MachineCoreOutputRecipeBuilder(carrotConceptCore)::unlockedBy, SMItems.CONCEPT_CORE_FARM.get())
				.addOutput(Items.CARROT.getDefaultInstance(), 100)
				.save(provider, getID(currentFolder + "carrot"));
		var potatoConceptCore = coreWithContent(SMItems.CONCEPT_CORE_FARM.get(), Items.POTATO);
		unlock(provider, new MachineCoreOutputRecipeBuilder(potatoConceptCore)::unlockedBy, SMItems.CONCEPT_CORE_FARM.get())
				.addOutput(Items.POTATO.getDefaultInstance(), 100)
				.save(provider, getID(currentFolder + "potato"));
		var beetrootConceptCore = coreWithContent(SMItems.CONCEPT_CORE_FARM.get(), Items.BEETROOT_SEEDS);
		unlock(provider, new MachineCoreOutputRecipeBuilder(beetrootConceptCore)::unlockedBy, SMItems.CONCEPT_CORE_FARM.get())
				.addOutput(Items.BEETROOT.getDefaultInstance(), 100)
				.save(provider, getID(currentFolder + "beetroot"));
		var wheatConceptCore = coreWithContent(SMItems.CONCEPT_CORE_FARM.get(), Items.WHEAT_SEEDS);
		unlock(provider, new MachineCoreOutputRecipeBuilder(wheatConceptCore)::unlockedBy, SMItems.CONCEPT_CORE_FARM.get())
				.addOutput(Items.BREAD.getDefaultInstance(), 1.0 / 3.0)
				.save(provider, getID(currentFolder + "wheat"));
	}

	private static <T> T unlock(RegistrateRecipeProvider provider, BiFunction<String, InventoryChangeTrigger.TriggerInstance, T> func, Item item) {
		return func.apply("has_" + provider.safeName(item), DataIngredient.items(item).getCritereon(provider));
	}

	private static ResourceLocation getID(String suffix) {
		return new ResourceLocation(SheathMagic.MODID, suffix);
	}

	private static void simpleRecipeWithContent(RegistrateRecipeProvider provider, BaseConceptCore core, Item content, String name) {
		var contentStack = content.getDefaultInstance();
		var coreStack = core.getDefaultInstance();
		ItemContainable.writeTagContent(coreStack, contentStack);
		unlock(provider, new MachineCoreOutputRecipeBuilder(coreStack)::unlockedBy, core)
				.addOutput(contentStack, 100)
				.save(provider, getID(currentFolder + name));
	}

	private static ItemStack coreWithContent(BaseConceptCore core, Item content) {
		var contentStack = content.getDefaultInstance();
		var coreStack = core.getDefaultInstance();
		ItemContainable.writeTagContent(coreStack, contentStack);
		return coreStack;
	}
}
