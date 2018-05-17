package com.endie.thaumicadditions.init;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//import org.apache.commons.lang3.ArrayUtils;

import com.endie.thaumicadditions.InfoTAR;
import com.endie.thaumicadditions.api.AspectUtil;

import static com.endie.thaumicadditions.api.AspectUtil.*;
import com.endie.thaumicadditions.recipes.RecipeApplySalt;
import com.endie.thaumicadditions.recipes.RecipeClearSalt;
import com.endie.thaumicadditions.recipes.RecipeMixSalts;
import com.endie.thaumicadditions.tiles.TileAuraCharger;
import com.pengu.hammercore.recipeAPI.helper.RecipeRegistry;
import com.pengu.hammercore.recipeAPI.helper.RegisterRecipes;
import com.pengu.hammercore.utils.ArrayHelper;
import com.pengu.hammercore.utils.OnetimeCaller;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.blocks.BlocksTC;
import thaumcraft.api.crafting.InfusionRecipe;
import thaumcraft.api.crafting.ShapedArcaneRecipe;
import thaumcraft.api.items.ItemsTC;

@RegisterRecipes(modid = InfoTAR.MOD_ID)
public class RecipesTAR extends RecipeRegistry
{
	public static final Map<Item, List<ResourceLocation>> FAKE_RECIPE_MAP = new HashMap<>();
	
	public static OnetimeCaller init;
	private static RecipesTAR instance;
	{
		instance = this;
		init = new OnetimeCaller(this::init);
	}
	
	private void init()
	{
		infusing();
		arcaneCrafting();
	}
	
	@Override
	public void crafting()
	{
		shaped(BlocksTAR.CRAFTING_FURNACE, "c", "g", "f", 'c', Blocks.CRAFTING_TABLE, 'g', "gearIron", 'f', Blocks.FURNACE);
		shapeless(ItemsTAR.ADAMINITE_INGOT, ItemsTAR.ADAMINITE_NUGGET, ItemsTAR.ADAMINITE_NUGGET, ItemsTAR.ADAMINITE_NUGGET, ItemsTAR.ADAMINITE_NUGGET, ItemsTAR.ADAMINITE_NUGGET, ItemsTAR.ADAMINITE_NUGGET, ItemsTAR.ADAMINITE_NUGGET, ItemsTAR.ADAMINITE_NUGGET);
		shapeless(ItemsTAR.MITHRILLIUM_INGOT, ItemsTAR.MITHRILLIUM_NUGGET, ItemsTAR.MITHRILLIUM_NUGGET, ItemsTAR.MITHRILLIUM_NUGGET, ItemsTAR.MITHRILLIUM_NUGGET, ItemsTAR.MITHRILLIUM_NUGGET, ItemsTAR.MITHRILLIUM_NUGGET, ItemsTAR.MITHRILLIUM_NUGGET, ItemsTAR.MITHRILLIUM_NUGGET, ItemsTAR.MITHRILLIUM_NUGGET);
		shapeless(new ItemStack(ItemsTAR.ADAMINITE_NUGGET, 8), ItemsTAR.ADAMINITE_INGOT);
		shapeless(new ItemStack(ItemsTAR.MITHRILLIUM_NUGGET, 9), ItemsTAR.MITHRILLIUM_INGOT);
		shaped(new ItemStack(ItemsTAR.MITHRILLIUM_PLATE, 3), "ppp", 'p', ItemsTAR.MITHRILLIUM_INGOT);
		shaped(new ItemStack(ItemsTAR.ADAMINITE_PLATE, 3), "ppp", 'p', ItemsTAR.ADAMINITE_INGOT);
		shaped(new ItemStack(ItemsTAR.MITHMINITE_PLATE, 3), "ppp", 'p', ItemsTAR.MITHMINITE_INGOT);
		recipe(new RecipeMixSalts().setRegistryName(new ResourceLocation(getMod(), "essence_salt.mix")));
		recipe(new RecipeApplySalt().setRegistryName(new ResourceLocation(getMod(), "essence_salt.apply")));
		recipe(new RecipeClearSalt().setRegistryName(new ResourceLocation(getMod(), "essence_salt.remove")));
	}
	
	private void infusing()
	{
		addInfusionRecipe("mithrillium_ingot", new ItemStack(ItemsTAR.MITHRILLIUM_INGOT, 2), "TAR_MITHRILLIUM", 5, new ItemStack(ItemsTC.ingots, 1, 1), new AspectList().add(Aspect.CRYSTAL, 30).add(Aspect.ENERGY, 15).add(Aspect.ELDRITCH, 10).add(Aspect.METAL, 30).add(Aspect.MAGIC, 10), new ItemStack(ItemsTC.amber), new ItemStack(ItemsTC.alumentum), new ItemStack(ItemsTC.quicksilver), new ItemStack(ItemsTC.fabric), new ItemStack(ItemsTC.salisMundus), new ItemStack(ItemsTC.amber), new ItemStack(ItemsTC.alumentum), new ItemStack(ItemsTC.quicksilver), new ItemStack(ItemsTC.fabric), new ItemStack(ItemsTC.salisMundus));
		addInfusionRecipe("adaminite_ingot", new ItemStack(ItemsTAR.ADAMINITE_INGOT, 1), "TAR_ADAMINITE", 10, new ItemStack(ItemsTAR.MITHRILLIUM_INGOT), new AspectList().add(Aspect.LIFE, 45).add(Aspect.ALCHEMY, 30).add(Aspect.EXCHANGE, 40).add(Aspect.METAL, 40).add(Aspect.SOUL, 120).add(Aspect.MAGIC, 40), new ItemStack(Items.NETHER_STAR), new ItemStack(ItemsTC.fabric), new ItemStack(ItemsTC.primordialPearl), new ItemStack(Items.NETHER_STAR));
		addInfusionRecipe("mithminite_ingot", new ItemStack(ItemsTAR.MITHMINITE_INGOT, 3), "TAR_MITHMINITE", 8, new ItemStack(ItemsTAR.ADAMINITE_INGOT), new AspectList().add(KnowledgeTAR.CAELES, 10).add(Aspect.METAL, 60).add(Aspect.LIFE, 90).add(Aspect.MAGIC, 120), new ItemStack(ItemsTAR.MITHRILLIUM_INGOT), new ItemStack(ItemsTC.quicksilver), new ItemStack(ItemsTAR.MITHRILLIUM_INGOT), new ItemStack(ItemsTC.quicksilver));
		addInfusionRecipe("mithminite_jar", new ItemStack(BlocksTAR.MITHMINITE_JAR), "TAR_MITHMINITE_JAR", 7, new ItemStack(BlocksTAR.ADAMINITE_JAR), new AspectList().add(KnowledgeTAR.CAELES, 16).add(Aspect.ALCHEMY, 32).add(Aspect.EXCHANGE, 10).add(Aspect.WATER, 40).add(Aspect.VOID, 30), new ItemStack(ItemsTAR.MITHMINITE_INGOT), new ItemStack(ItemsTC.amber), new ItemStack(ItemsTC.alumentum), new ItemStack(BlocksTC.jarVoid));
		addInfusionRecipe("aspect_combiner", new ItemStack(BlocksTAR.ASPECT_COMBINER), "TAR_ASPECT_COMBINER", 6, new ItemStack(BlocksTC.centrifuge), new AspectList().add(Aspect.EXCHANGE, 16).add(Aspect.ENTROPY, 30).add(Aspect.ALCHEMY, 20).add(Aspect.MECHANISM, 15), new ItemStack(ItemsTAR.MITHRILLIUM_INGOT), new ItemStack(ItemsTC.mechanismComplex), crystalEssence(Aspect.EXCHANGE), new ItemStack(ItemsTC.filter), new ItemStack(ItemsTC.plate, 1, 0), new ItemStack(ItemsTC.alumentum));
		addInfusionRecipe("aura_charger", new ItemStack(BlocksTAR.AURA_CHARGER), "TAR_AURA_CHARGER", 8, new ItemStack(BlocksTAR.ASPECT_COMBINER), new AspectList().add(TileAuraCharger.AURA, 20).add(Aspect.MAGIC, 20).add(Aspect.ENERGY, 40), crystalEssence(TileAuraCharger.AURA), new ItemStack(ItemsTAR.ADAMINITE_NUGGET), new ItemStack(ItemsTAR.ADAMINITE_NUGGET), new ItemStack(ItemsTC.mechanismComplex), new ItemStack(ItemsTAR.ADAMINITE_NUGGET), new ItemStack(ItemsTAR.ADAMINITE_NUGGET));
		addInfusionRecipe("crystal_crusher", new ItemStack(BlocksTAR.CRYSTAL_CRUSHER), "TAR_CRYSTAL_CRUSHER", 3, new ItemStack(ItemsTC.mechanismComplex), new AspectList().add(Aspect.CRAFT, 20).add(KnowledgeTAR.EXITIUM, 20).add(Aspect.TOOL, 20), crystalEssence(Aspect.AIR), crystalEssence(Aspect.EARTH), crystalEssence(Aspect.FIRE), crystalEssence(Aspect.WATER), crystalEssence(Aspect.ORDER), crystalEssence(Aspect.ENTROPY), new ItemStack(ItemsTC.plate, 1, 2), new ItemStack(ItemsTC.plate, 1, 2), new ItemStack(ItemsTC.plate, 1, 2), new ItemStack(BlocksTC.slabArcaneStone), new ItemStack(BlocksTC.slabArcaneStone), new ItemStack(BlocksTC.slabArcaneStone), new ItemStack(ItemsTAR.SALT_ESSENCE), new ItemStack(ItemsTAR.SALT_ESSENCE));
		addInfusionRecipe("aura_disperser", new ItemStack(BlocksTAR.AURA_DISPERSER), "TAR_AURA_DISPERSER", 4, new ItemStack(Blocks.DISPENSER), new AspectList().add(KnowledgeTAR.FLUCTUS, 30).add(Aspect.AURA, 10).add(Aspect.ALCHEMY, 20), new ItemStack(ItemsTC.mechanismComplex), AspectUtil.salt(Aspect.AURA), AspectUtil.salt(Aspect.ALCHEMY), new ItemStack(BlocksTC.shimmerleaf), new ItemStack(ItemsTC.morphicResonator), AspectUtil.salt(KnowledgeTAR.FLUCTUS), AspectUtil.salt(KnowledgeTAR.DRACO), new ItemStack(BlocksTC.nitor.get(EnumDyeColor.YELLOW)));
		addInfusionRecipe("crystal_bore", new ItemStack(BlocksTAR.CRYSTAL_BORE), "TAR_CRYSTAL_BORE", 5, new ItemStack(ItemsTC.morphicResonator), new AspectList().add(KnowledgeTAR.EXITIUM, 20).add(Aspect.EARTH, 10).add(Aspect.ENTROPY, 30), new ItemStack(BlocksTC.stoneArcane), new ItemStack(ItemsTC.plate), new ItemStack(BlocksTC.stoneArcane), new ItemStack(ItemsTC.plate), new ItemStack(BlocksTC.stoneArcane), new ItemStack(ItemsTC.mechanismComplex));
	}
	
	private void arcaneCrafting()
	{
		addShapedArcaneRecipe("mithrillium_smelter", "TAR_MITHRILLIUM_SMELTER", 1000, new AspectList().add(Aspect.FIRE, 6).add(Aspect.WATER, 2), new ItemStack(BlocksTAR.MITHRILLIUM_SMELTER), "bsb", "mcm", "mmm", 'b', new ItemStack(ItemsTC.plate, 1, 0), 's', BlocksTC.smelterVoid, 'm', ItemsTAR.MITHRILLIUM_PLATE, 'c', BlocksTC.metalAlchemicalAdvanced);
		addShapedArcaneRecipe("adaminite_smelter", "TAR_ADAMINITE_SMELTER", 1200, new AspectList().add(Aspect.FIRE, 12).add(Aspect.WATER, 6), new ItemStack(BlocksTAR.ADAMINITE_SMELTER), "bsb", "mcm", "mmm", 'b', new ItemStack(ItemsTC.plate, 1, 0), 's', BlocksTAR.MITHRILLIUM_SMELTER, 'm', ItemsTAR.ADAMINITE_PLATE, 'c', BlocksTC.metalAlchemicalAdvanced);
		addShapedArcaneRecipe("mithminite_smelter", "TAR_MITHMINITE_SMELTER", 1500, new AspectList().add(Aspect.FIRE, 24).add(Aspect.WATER, 12), new ItemStack(BlocksTAR.MITHMINITE_SMELTER), "bsb", "mcm", "mmm", 'b', new ItemStack(ItemsTC.plate, 1, 0), 's', BlocksTAR.ADAMINITE_SMELTER, 'm', ItemsTAR.MITHMINITE_PLATE, 'c', BlocksTC.metalAlchemicalAdvanced);
		
		addShapedArcaneRecipe("brass_jar", "TAR_BRASS_JAR", 7, new AspectList(), new ItemStack(BlocksTAR.BRASS_JAR), "gpg", "gjg", "ggg", 'g', "paneGlass", 'p', new ItemStack(ItemsTC.plate, 1, 0), 'j', BlocksTC.jarNormal);
		addShapedArcaneRecipe("thaumium_jar", "TAR_THAUMIUM_JAR", 15, new AspectList().add(Aspect.WATER, 2), new ItemStack(BlocksTAR.THAUMIUM_JAR), "gpg", "gjg", "ggg", 'g', "paneGlass", 'p', new ItemStack(ItemsTC.plate, 1, 2), 'j', BlocksTAR.BRASS_JAR);
		addShapedArcaneRecipe("eldritch_jar", "TAR_ELDRITCH_JAR", 150, new AspectList().add(Aspect.WATER, 6), new ItemStack(BlocksTAR.ELDRITCH_JAR), "gpg", "gjg", "ggg", 'g', "paneGlass", 'p', new ItemStack(ItemsTC.plate, 1, 3), 'j', BlocksTAR.THAUMIUM_JAR);
		addShapedArcaneRecipe("mithrillium_jar", "TAR_MITHRILLIUM_JAR", 750, new AspectList().add(Aspect.WATER, 12), new ItemStack(BlocksTAR.MITHRILLIUM_JAR), "gpg", "gjg", "ggg", 'g', "paneGlass", 'p', new ItemStack(ItemsTAR.MITHRILLIUM_PLATE), 'j', BlocksTAR.ELDRITCH_JAR);
		addShapedArcaneRecipe("adaminite_jar", "TAR_ADAMINITE_JAR@2", 1000, new AspectList().add(Aspect.WATER, 24), new ItemStack(BlocksTAR.ADAMINITE_JAR), "gpg", "gjg", "ggg", 'g', "paneGlass", 'p', new ItemStack(ItemsTAR.ADAMINITE_PLATE), 'j', BlocksTAR.MITHRILLIUM_JAR);
	}
	
	private static void addInfusionRecipe(String path, Object output, String research, int instability, Object catalyst, AspectList aspects, Object... inputs)
	{
		ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation(InfoTAR.MOD_ID, path), new InfusionRecipe(research, output, instability, aspects, catalyst, inputs));
	}
	
	static ResourceLocation defaultGroup = new ResourceLocation("");
	
	private static void addShapedArcaneRecipe(String path, String res, int vis, AspectList crystals, ItemStack result, Object... recipe)
	{
		ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation(InfoTAR.MOD_ID, path), new ShapedArcaneRecipe(defaultGroup, res, vis, crystals, result, recipe));
	}
	
	private static void addShapedArcaneRecipe(String path, String res, int vis, AspectList crystals, Item result, Object... recipe)
	{
		ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation(InfoTAR.MOD_ID, path), new ShapedArcaneRecipe(defaultGroup, res, vis, crystals, result, recipe));
	}
	
	private static void addShapedArcaneRecipe(String path, String res, int vis, AspectList crystals, Block result, Object... recipe)
	{
		ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation(InfoTAR.MOD_ID, path), new ShapedArcaneRecipe(defaultGroup, res, vis, crystals, result, recipe));
	}
	
	@Override
	public void smelting()
	{
	}
	
	@Override
	protected void recipe(IRecipe recipe)
	{
		super.recipe(recipe);
		Item it = recipe.getRecipeOutput().getItem();
		List<ResourceLocation> locs = FAKE_RECIPE_MAP.get(it);
		if(locs == null)
			FAKE_RECIPE_MAP.put(it, locs = new ArrayList<>());
		if(!locs.contains(recipe.getRegistryName()))
			locs.add(recipe.getRegistryName());
		ThaumcraftApi.addFakeCraftingRecipe(recipe.getRegistryName(), recipe);
	}
	
	public static String[] getFakeRecipesFor(Item it)
	{
		List<ResourceLocation> locs = FAKE_RECIPE_MAP.get(it);
		if(locs == null)
			FAKE_RECIPE_MAP.put(it, locs = new ArrayList<>());
		return locs.stream().map(l -> l.toString()).collect(Collectors.toList()).toArray(new String[locs.size()]);
	}
	
	public static String[] getFakeRecipes(Item it, String... appends)
	{
		return ArrayHelper.merge(getFakeRecipesFor(it), appends);
	}
	
	public static String[] getFakeRecipesPre(Item it, String... prepends)
	{
		return ArrayHelper.merge(prepends, getFakeRecipesFor(it));
	}
}