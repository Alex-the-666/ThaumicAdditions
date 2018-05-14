package com.endie.thaumicadditions.client.tesr;

import org.lwjgl.opengl.GL11;

import com.endie.thaumicadditions.InfoTAR;
import com.endie.thaumicadditions.api.phys.ColRotator;
import com.endie.thaumicadditions.client.models.ModelAspectCombiner;
import com.endie.thaumicadditions.client.models.ModelAuraCharger;
import com.endie.thaumicadditions.client.models.ModelCrystalCrusher;
import com.endie.thaumicadditions.tiles.TileAspectCombiner;
import com.endie.thaumicadditions.tiles.TileAuraCharger;
import com.endie.thaumicadditions.tiles.TileCrystalCrusher;
import com.pengu.hammercore.client.render.tesr.TESR;
import com.pengu.hammercore.client.utils.RenderBlocks;
import com.pengu.hammercore.utils.ColorHelper;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing.Axis;
import net.minecraft.util.ResourceLocation;
import thaumcraft.api.aspects.Aspect;

public class TESRCrystalCrusher extends TESR<TileCrystalCrusher>
{
	public ResourceLocation texture = new ResourceLocation(InfoTAR.MOD_ID, "textures/models/crystal_crusher.png");
	public ModelCrystalCrusher model = new ModelCrystalCrusher();
	
	@Override
	public void renderTileEntityAt(TileCrystalCrusher te, double x, double y, double z, float partialTicks, ResourceLocation destroyStage, float alpha)
	{
		ColRotator cr = te.rotator;
		for(int i = 0; i < (destroyStage != null ? 2 : 1); ++i)
		{
			GL11.glPushMatrix();
			GL11.glTranslated(x + .5, y + 1.5, z + .5);
			// GL11.glRotated(90, 0, 1, 0);
			GL11.glRotated(180, 1, 0, 0);
			bindTexture(i == 1 ? destroyStage : texture);
			model.shape1.render(1 / 16F);
			GL11.glPushMatrix();
			GL11.glTranslatef(0, 1 / 16F, 0);
			GL11.glRotatef(cr.getActualRotation(partialTicks), 0, 1, 0);
			model.shape3.render(1 / 16F);
			GL11.glPopMatrix();
			GL11.glPopMatrix();
		}
	}
	
	@Override
	public void renderItem(ItemStack item)
	{
		GL11.glPushMatrix();
		GL11.glTranslated(.5, 1.5, .5);
		GL11.glRotated(180, 1, 0, 0);
		bindTexture(texture);
		model.render(null, 0, 0, 0, 0, 0, 1 / 16F);
		GL11.glPopMatrix();
	}
}