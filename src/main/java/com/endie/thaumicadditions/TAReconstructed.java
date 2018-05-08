package com.endie.thaumicadditions;

import org.apache.logging.log4j.Logger;

import com.endie.thaumicadditions.init.BlocksTAR;
import com.endie.thaumicadditions.init.ItemsTAR;
import com.endie.thaumicadditions.init.KnowledgeTAR;
import com.endie.thaumicadditions.init.RecipesTAR;
import com.endie.thaumicadditions.proxy.CommonProxy;
import com.pengu.hammercore.HammerCore;
import com.pengu.hammercore.common.SimpleRegistration;
import com.pengu.hammercore.common.utils.HammerCoreUtils;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.ModMetadata;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLFingerprintViolationEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchCategory;

@Mod(modid = InfoTAR.MOD_ID, name = InfoTAR.MOD_NAME, version = InfoTAR.MOD_VERSION, certificateFingerprint = "4d7b29cd19124e986da685107d16ce4b49bc0a97", dependencies = "required-after:hammercore;required-after:thaumcraft@[6.1.BETA13,)")
public class TAReconstructed
{
	public static final Logger LOG = org.apache.logging.log4j.LogManager.getLogger(InfoTAR.MOD_ID);
	@Instance
	public static TAReconstructed instance;
	public static CreativeTabs tab;
	private static final ResourceLocation BACK_OVER = new ResourceLocation("thaumcraft", "textures/gui/gui_research_back_over.png");
	@SidedProxy(serverSide = "com.endie.thaumicadditions.proxy.CommonProxy", clientSide = "com.endie.thaumicadditions.proxy.ClientProxy")
	public static CommonProxy proxy;
	
	public static ResearchCategory RES_CAT;
	
	@EventHandler
	public void certificateViolation(FMLFingerprintViolationEvent e)
	{
		LOG.warn("*****************************");
		LOG.warn("WARNING: Somebody has been tampering with HammerCore jar!");
		LOG.warn("It is highly recommended that you redownload mod from https://minecraft.curseforge.com/projects/247401 !");
		LOG.warn("*****************************");
		HammerCore.invalidCertificates.put(InfoTAR.MOD_ID, "https://minecraft.curseforge.com/projects/232564");
	}
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent e)
	{
		tab = HammerCoreUtils.createStaticIconCreativeTab(InfoTAR.MOD_ID, new ItemStack(ItemsTAR.MITHMINITE_INGOT));
		
		ModMetadata meta = e.getModMetadata();
		meta.autogenerated = false;
		meta.version = InfoTAR.MOD_VERSION;
		meta.modId = InfoTAR.MOD_ID;
		meta.name = InfoTAR.MOD_NAME;
		meta.authorList = HammerCore.AUTHORS;
		
		SimpleRegistration.registerFieldItemsFrom(ItemsTAR.class, InfoTAR.MOD_ID, tab);
		SimpleRegistration.registerFieldBlocksFrom(BlocksTAR.class, InfoTAR.MOD_ID, tab);
	}
	
	@EventHandler
	public void init(FMLInitializationEvent e)
	{
		proxy.init();
		RES_CAT = ResearchCategories.registerCategory("THAUMADDITIONS", "UNLOCKINFUSION", new AspectList().add(Aspect.MAGIC, 5).add(Aspect.TOOL, 5).add(Aspect.EARTH, 3), new ResourceLocation(InfoTAR.MOD_ID, "textures/gui/thaumonomicon_icon.png"), CommonProxy.TEXTURE_THAUMONOMICON_BG, BACK_OVER);
		RecipesTAR.init.call();
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent e)
	{
		KnowledgeTAR.init();
	}
}