package cn.nulladev.sheathmagic.init.registrate;

import cn.nulladev.sheathmagic.content.item.CoreComponent;
import cn.nulladev.sheathmagic.content.item.InteroperationWand;
import cn.nulladev.sheathmagic.content.item.SpaceCrystal;
import cn.nulladev.sheathmagic.content.item.conceptcore.*;
import dev.xkmc.l2library.repack.registrate.util.entry.ItemEntry;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.entity.projectile.ThrownEgg;
import net.minecraft.world.entity.projectile.ThrownEnderpearl;
import net.minecraft.world.level.block.Blocks;

import static cn.nulladev.sheathmagic.init.SheathMagic.REGISTRATE;

public class SMItems {

    /* Components */

    // Basic components that can be crafted in crafting table.
    public static final ItemEntry<CoreComponent> CORE_AMPLIFIER = REGISTRATE.item("core_amplifier", CoreComponent::new).defaultModel().defaultLang().register();
    public static final ItemEntry<CoreComponent> WORLD_INTEROPERATION_IO = REGISTRATE.item("world_interoperation_io", CoreComponent::new).defaultModel().defaultLang().register();

    // Higher level components that should be crafted in space crystal
    public static final ItemEntry<CoreComponent> CORE_MODIFIER_PULSE =
            REGISTRATE.item("core_modifier_pulse", CoreComponent::new).defaultModel().defaultLang().register();
    public static final ItemEntry<CoreComponent> CORE_MODIFIER_ENDER_UPDATE =
            REGISTRATE.item("core_modifier_ender_update", CoreComponent::new).defaultModel().defaultLang().register();
    public static final ItemEntry<CoreComponent> CORE_MODIFIER_DARK_BOX =
            REGISTRATE.item("core_modifier_dark_box", CoreComponent::new).defaultModel().defaultLang().register();
    public static final ItemEntry<CoreComponent> CORE_MODIFIER_FALL =
            REGISTRATE.item("core_modifier_falling", CoreComponent::new).defaultModel().defaultLang().register();
    public static final ItemEntry<CoreComponent> CORE_MODIFIER_SHULKER =
            REGISTRATE.item("core_modifier_shulker", CoreComponent::new).defaultModel().defaultLang().register();
    public static final ItemEntry<CoreComponent> CORE_MODIFIER_SNOW_GOLEM =
            REGISTRATE.item("core_modifier_snow_golem", CoreComponent::new).defaultModel().defaultLang().register();
    public static final ItemEntry<CoreComponent> CORE_MODIFIER_WITHER =
            REGISTRATE.item("core_modifier_wither", CoreComponent::new).defaultModel().defaultLang().register();
    public static final ItemEntry<CoreComponent> CORE_MODIFIER_PULSE_CONTROLLABLE =
            REGISTRATE.item("core_modifier_pulse_controllable", CoreComponent::new).defaultModel().defaultLang().register();
    public static final ItemEntry<CoreComponent> CORE_MODIFIER_PISTON_WORM =
            REGISTRATE.item("core_modifier_piston_worm", CoreComponent::new).defaultModel().defaultLang().register();
    public static final ItemEntry<CoreComponent> CORE_MODIFIER_VILLAGER =
            REGISTRATE.item("core_modifier_villager", CoreComponent::new).defaultModel().defaultLang().register();
    public static final ItemEntry<CoreComponent> CORE_MODIFIER_ENDERMITE =
            REGISTRATE.item("core_modifier_endermite", CoreComponent::new).defaultModel().defaultLang().register();

    /* Interoperation Wand */
    public static final ItemEntry<InteroperationWand> INTEROPERATION_WAND =
            REGISTRATE.item("interoperation_wand", InteroperationWand::new).defaultModel().defaultLang().register();

    /* Concept Cores */

    // Simple concept cores
    public static final ItemEntry<SimplePlacementConceptCore> CONCEPT_CORE_COBBLESTONE =
            REGISTRATE.item(
                    "concept_core_cobblestone",
                    (p) -> new SimplePlacementConceptCore(p, 30, Blocks.COBBLESTONE)
            ).defaultModel().defaultLang().register();

    public static final ItemEntry<SimplePlacementConceptCore> CONCEPT_CORE_COBBLESTONE_ADVANCED =
            REGISTRATE.item(
                    "concept_core_cobblestone_advanced",
                    (p) -> new SimplePlacementConceptCore(p, 10, Blocks.COBBLESTONE)
            ).defaultModel().defaultLang().register();

    public static final ItemEntry<SimplePlacementConceptCore> CONCEPT_CORE_STONE =
            REGISTRATE.item(
                    "concept_core_stone",
                    (p) -> new SimplePlacementConceptCore(p, 30, Blocks.STONE)
            ).defaultModel().defaultLang().register();

    public static final ItemEntry<SimplePlacementConceptCore> CONCEPT_CORE_OBSIDIAN =
            REGISTRATE.item(
                    "concept_core_obsidian",
                    (p) -> new SimplePlacementConceptCore(p, 24000, Blocks.OBSIDIAN)
            ).defaultModel().defaultLang().register();

    public static final ItemEntry<SimplePlacementConceptCore> CONCEPT_CORE_OBSIDIAN_ADVANCED =
            REGISTRATE.item(
                    "concept_core_obsidian_advanced",
                    (p) -> new SimplePlacementConceptCore(p, 5, Blocks.OBSIDIAN)
            ).defaultModel().defaultLang().register();

    public static final ItemEntry<SimplePlacementConceptCore> CONCEPT_CORE_LAVA =
            REGISTRATE.item(
                    "concept_core_lava",
                    (p) -> new SimplePlacementConceptCore(p, 24000, Blocks.LAVA)
            ).defaultModel().defaultLang().register();

    public static final ItemEntry<SimplePlacementConceptCore> CONCEPT_CORE_WATER =
            REGISTRATE.item(
                    "concept_core_water",
                    (p) -> new SimplePlacementConceptCore(p, 5, Blocks.WATER)
            ).defaultModel().defaultLang().register();

    public static final ItemEntry<SimplePlacementConceptCore> CONCEPT_CORE_SUGAR_CANE =
            REGISTRATE.item(
                    "concept_core_sugar_cane",
                    (p) -> new SimplePlacementConceptCore(p, 24000, Blocks.SUGAR_CANE)
            ).defaultModel().defaultLang().register();

    public static final ItemEntry<SimplePlacementConceptCore> CONCEPT_CORE_CACTUS =
            REGISTRATE.item(
                    "concept_core_cactus",
                    (p) -> new SimplePlacementConceptCore(p, 12000, Blocks.CACTUS)
            ).defaultModel().defaultLang().register();

    public static final ItemEntry<SimplePlacementConceptCore> CONCEPT_CORE_TREE =
            REGISTRATE.item(
                    "concept_core_tree",
                    (p) -> new SimplePlacementConceptCore(p, 20, Blocks.OAK_LOG)
            ).defaultModel().defaultLang().register();

    public static final ItemEntry<SimpleThrownConceptCore> CONCEPT_CORE_EGG =
            REGISTRATE.item(
                    "concept_core_egg",
                    (p) -> new SimpleThrownConceptCore(p, 400, ThrownEgg::new)
            ).defaultModel().defaultLang().register();

    public static final ItemEntry<SimpleThrownConceptCore> CONCEPT_CORE_ENDER_PEARL =
            REGISTRATE.item(
                    "concept_core_ender_pearl",
                    (p) -> new SimpleThrownConceptCore(p, 400, ThrownEnderpearl::new)
            ).defaultModel().defaultLang().register();

    public static final ItemEntry<SimpleThrownConceptCore> CONCEPT_CORE_SNOWBALL =
            REGISTRATE.item(
                    "concept_core_snowball",
                    (p) -> new SimpleThrownConceptCore(p, 10, Snowball::new)
            ).defaultModel().defaultLang().register();

    public static final ItemEntry<SimpleFoodConceptCore> CONCEPT_CORE_COOKED_CHICKEN =
            REGISTRATE.item(
                    "concept_core_cooked_chicken",
                    (p) -> new SimpleFoodConceptCore(p, 400, 6, 1.2f)
            ).defaultModel().defaultLang().register();

    public static final ItemEntry<SimpleFoodConceptCore> CONCEPT_CORE_COOKED_PORKCHOP =
            REGISTRATE.item(
                    "concept_core_cooked_porkchop",
                    (p) -> new SimpleFoodConceptCore(p, 200, 8, 1.6f)
            ).defaultModel().defaultLang().register();


    public static final ItemEntry<SimpleFoodConceptCore> CONCEPT_CORE_GLOW_BERRIES =
            REGISTRATE.item(
                    "concept_core_glow_berries",
                    (p) -> new SimpleFoodConceptCore(p, 86400, 2, 0.2f)
            ).defaultModel().defaultLang().register();

    // Non simple concept cores
    public static final ItemEntry<AttachmentConceptCore> CONCEPT_CORE_ATTACHMENT =
            REGISTRATE.item(
                    "concept_core_attachment",
                    AttachmentConceptCore::new
            ).defaultModel().defaultLang().register();

    public static final ItemEntry<BoneMealConceptCore> CONCEPT_CORE_BONE_MEAL =
            REGISTRATE.item(
                    "concept_core_bone_meal",
                    (p) -> new BoneMealConceptCore(p, 12000)
            ).defaultModel().defaultLang().register();


    public static final ItemEntry<BoneMealConceptCore> CONCEPT_CORE_BONE_MEAL_ADVANCED =
            REGISTRATE.item(
                    "concept_core_bone_meal_advanced",
                    (p) -> new BoneMealConceptCore(p, 2)
            ).defaultModel().defaultLang().register();

    public static final ItemEntry<FallingBlockConceptCore> CONCEPT_CORE_FALLING_BLOCK =
            REGISTRATE.item(
                    "concept_core_falling_block",
                    FallingBlockConceptCore::new
            ).defaultModel().defaultLang().register();

    public static final ItemEntry<FrameBreakerConceptCore> CONCEPT_CORE_FRAME_BREAKER =
            REGISTRATE.item(
                    "concept_core_frame_breaker",
                    FrameBreakerConceptCore::new
            ).defaultModel().defaultLang().register();


    public static final ItemEntry<LevelEaterConceptCore> CONCEPT_CORE_LEVEL_EATER =
            REGISTRATE.item(
                    "concept_core_level_eater",
                    LevelEaterConceptCore::new
            ).defaultModel().defaultLang().register();


    public static final ItemEntry<TNTConceptCore> CONCEPT_CORE_TNT =
            REGISTRATE.item(
                    "concept_core_tnt",
                    TNTConceptCore::new
            ).defaultModel().defaultLang().register();


    public static final ItemEntry<VillagerSpawnConceptCore> CONCEPT_CORE_VILLAGER_SPAWN =
            REGISTRATE.item(
                    "concept_core_villager_spawn",
                    VillagerSpawnConceptCore::new
            ).defaultModel().defaultLang().register();

    // Machine output only concept cores
    public static final ItemEntry<BaseConceptCore> CONCEPT_CORE_GHAST =
            REGISTRATE.item(
                    "concept_core_ghast",
                    (p) -> new BaseConceptCore(p, 200)
            ).defaultModel().defaultLang().register();

    public static final ItemEntry<BaseConceptCore> CONCEPT_CORE_GOLD =
            REGISTRATE.item(
                    "concept_core_gold",
                    (p) -> new BaseConceptCore(p, 200)
            ).defaultModel().defaultLang().register();

    public static final ItemEntry<BaseConceptCore> CONCEPT_CORE_IRON =
            REGISTRATE.item(
                    "concept_core_iron",
                    (p) -> new BaseConceptCore(p, 300)
            ).defaultModel().defaultLang().register();

    public static final ItemEntry<BaseConceptCore> CONCEPT_CORE_MOB_GRINDER =
            REGISTRATE.item(
                    "concept_core_mob_grinder",
                    (p) -> new BaseConceptCore(p, 100)
            ).defaultModel().defaultLang().register();

    /* Space Crystal */

    public static final ItemEntry<SpaceCrystal> SPACE_CRYSTAL_BASIC =
            REGISTRATE.item("space_crystal_basic", (p) -> new SpaceCrystal(p, 3)).register();

    public static final ItemEntry<SpaceCrystal> SPACE_CRYSTAL_ADVANCED =
            REGISTRATE.item("space_crystal_advanced", (p) -> new SpaceCrystal(p, 4)).register();

    public static final ItemEntry<SpaceCrystal> SPACE_CRYSTAL_ULTIMATE =
            REGISTRATE.item("space_crystal_ultimate", (p) -> new SpaceCrystal(p, 5)).register();

    public static void register() {}
}
