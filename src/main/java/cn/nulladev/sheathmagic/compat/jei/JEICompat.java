package cn.nulladev.sheathmagic.compat.jei;

import cn.nulladev.sheathmagic.content.crafting.AbstractCoreOutputRecipe;
import cn.nulladev.sheathmagic.content.crafting.AbstractCrystalRecipe;
import cn.nulladev.sheathmagic.content.crafting.DefaultCrystalRecipe;
import cn.nulladev.sheathmagic.content.crafting.MachineCoreOutputRecipe;
import cn.nulladev.sheathmagic.init.SheathMagic;
import cn.nulladev.sheathmagic.init.registrate.SMBlocks;
import cn.nulladev.sheathmagic.init.registrate.SMItems;
import cn.nulladev.sheathmagic.init.registrate.SMRecipes;
import dev.xkmc.l2library.util.Proxy;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.registration.*;
import mezz.jei.api.runtime.IJeiRuntime;
import net.minecraft.resources.ResourceLocation;

import java.util.List;

@JeiPlugin
public class JEICompat implements IModPlugin {

    public static JEICompat INSTANCE;

    public static final ResourceLocation UID = new ResourceLocation(SheathMagic.MODID, "jei_plugin");
    public static final CrystalRecipeCategory BASIC = new CrystalRecipeCategory(
            new ResourceLocation(SheathMagic.MODID, "crystal_basic"),
            SMItems.SPACE_CRYSTAL_BASIC.get().getDefaultInstance(),
            new ResourceLocation(SheathMagic.MODID, "textures/gui/container/crystal_3.png"),
            "jei.title.crystal.basic", 3);
    public static final CrystalRecipeCategory ADVANCED = new CrystalRecipeCategory(
            new ResourceLocation(SheathMagic.MODID, "crystal_advanced"),
            SMItems.SPACE_CRYSTAL_ADVANCED.get().getDefaultInstance(),
            new ResourceLocation(SheathMagic.MODID, "textures/gui/container/crystal_4.png"),
            "jei.title.crystal.advanced", 4);
    public static final CrystalRecipeCategory ULTIMATE = new CrystalRecipeCategory(
            new ResourceLocation(SheathMagic.MODID, "crystal_ultimate"),
            SMItems.SPACE_CRYSTAL_ULTIMATE.get().getDefaultInstance(),
            new ResourceLocation(SheathMagic.MODID, "textures/gui/container/crystal_5.png"),
            "jei.title.crystal.ultimate", 5);

    public static final MachineRecipeCategory MACHINE = new MachineRecipeCategory(
            new ResourceLocation(SheathMagic.MODID, "core_machine"),
            SMBlocks.WORLD_INTEROPERATOR.asStack(),
            new ResourceLocation(SheathMagic.MODID, "textures/gui/container/core_machine.png"),
            "jei.title.machine"
    );

    public JEICompat() {
        INSTANCE = this;
    }

    @Override
    public ResourceLocation getPluginUid() {
        return UID;
    }

    @Override
    public void registerItemSubtypes(ISubtypeRegistration registration) {
    }


    @Override
    public void registerIngredients(IModIngredientRegistration registration) {
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        IGuiHelper helper = registration.getJeiHelpers().getGuiHelper();
        registration.addRecipeCategories(BASIC.init(helper));
        registration.addRecipeCategories(ADVANCED.init(helper));
        registration.addRecipeCategories(ULTIMATE.init(helper));
        registration.addRecipeCategories(MACHINE.init(helper));
    }

    @Override
    public void registerVanillaCategoryExtensions(IVanillaCategoryExtensionRegistration registration) {
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        List<AbstractCrystalRecipe<?>> crystalList = Proxy.getClientWorld().getRecipeManager().getAllRecipesFor(SMRecipes.RECIPE_TYPE_CRYSTAL.get());
        List<DefaultCrystalRecipe> crystalDef = crystalList.stream().filter(e -> e instanceof DefaultCrystalRecipe).map(e -> (DefaultCrystalRecipe) e).toList();
        List<DefaultCrystalRecipe> basic = crystalDef.stream().filter(e -> e.pattern.length == 3).toList();
        List<DefaultCrystalRecipe> advanced = crystalDef.stream().filter(e -> e.pattern.length == 4).toList();
        List<DefaultCrystalRecipe> ultimate = crystalDef.stream().filter(e -> e.pattern.length == 5).toList();
        List<AbstractCoreOutputRecipe<?>> machineList = Proxy.getClientWorld().getRecipeManager().getAllRecipesFor(SMRecipes.RECIPE_TYPE_CORE_OUTPUT.get());
        List<MachineCoreOutputRecipe> machineDef = machineList.stream().filter(e -> e instanceof MachineCoreOutputRecipe).map(e -> (MachineCoreOutputRecipe) e).toList();
        registration.addRecipes(BASIC.getRecipeType(), basic);
        registration.addRecipes(ADVANCED.getRecipeType(), advanced);
        registration.addRecipes(ULTIMATE.getRecipeType(), ultimate);
        registration.addRecipes(MACHINE.getRecipeType(), machineDef);
    }

    @Override
    public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration) {
        //TODO
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(SMItems.SPACE_CRYSTAL_BASIC.get().getDefaultInstance(), BASIC.getRecipeType());
        registration.addRecipeCatalyst(SMItems.SPACE_CRYSTAL_ADVANCED.get().getDefaultInstance(), ADVANCED.getRecipeType());
        registration.addRecipeCatalyst(SMItems.SPACE_CRYSTAL_ULTIMATE.get().getDefaultInstance(), ULTIMATE.getRecipeType());
        registration.addRecipeCatalyst(SMBlocks.WORLD_INTEROPERATOR.asStack(), MACHINE.getRecipeType());
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
    }

    @Override
    public void registerAdvanced(IAdvancedRegistration registration) {
    }

    @Override
    public void onRuntimeAvailable(IJeiRuntime jeiRuntime) {
    }

}
