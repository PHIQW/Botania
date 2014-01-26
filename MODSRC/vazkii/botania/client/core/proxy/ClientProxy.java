/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * 
 * Botania is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 * 
 * File Created @ [Jan 13, 2014, 7:46:05 PM (GMT)]
 */
package vazkii.botania.client.core.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import vazkii.botania.api.LexiconEntry;
import vazkii.botania.client.core.handler.HUDHandler;
import vazkii.botania.client.fx.FXSparkle;
import vazkii.botania.client.fx.FXWisp;
import vazkii.botania.client.gui.GuiLexicon;
import vazkii.botania.client.gui.GuiLexiconEntry;
import vazkii.botania.client.gui.GuiLexiconIndex;
import vazkii.botania.client.lib.LibRenderIDs;
import vazkii.botania.client.render.block.RenderAltar;
import vazkii.botania.client.render.block.RenderPool;
import vazkii.botania.client.render.block.RenderSpecialFlower;
import vazkii.botania.client.render.block.RenderSpreader;
import vazkii.botania.client.render.tile.RenderTileAltar;
import vazkii.botania.client.render.tile.RenderTilePool;
import vazkii.botania.client.render.tile.RenderTileSpreader;
import vazkii.botania.common.block.tile.TileAltar;
import vazkii.botania.common.block.tile.TilePool;
import vazkii.botania.common.block.tile.TileSpreader;
import vazkii.botania.common.core.proxy.CommonProxy;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.event.FMLInitializationEvent;

public class ClientProxy extends CommonProxy {

	@Override
	public void init(FMLInitializationEvent event) {
		super.init(event);
		
		MinecraftForge.EVENT_BUS.register(new HUDHandler());
		
		initRenderers();
	}
	
	private void initRenderers() {
		LibRenderIDs.idAltar = RenderingRegistry.getNextAvailableRenderId();
		LibRenderIDs.idSpecialFlower = RenderingRegistry.getNextAvailableRenderId();
		LibRenderIDs.idSpreader = RenderingRegistry.getNextAvailableRenderId();
		LibRenderIDs.idPool = RenderingRegistry.getNextAvailableRenderId();

		RenderingRegistry.registerBlockHandler(new RenderAltar());
		RenderingRegistry.registerBlockHandler(new RenderSpecialFlower());
		RenderingRegistry.registerBlockHandler(new RenderSpreader());
		RenderingRegistry.registerBlockHandler(new RenderPool());

		ClientRegistry.bindTileEntitySpecialRenderer(TileAltar.class, new RenderTileAltar());
		ClientRegistry.bindTileEntitySpecialRenderer(TileSpreader.class, new RenderTileSpreader());
		ClientRegistry.bindTileEntitySpecialRenderer(TilePool.class, new RenderTilePool());
	}
	
	@Override
	public void setEntryToOpen(LexiconEntry entry) {
		GuiLexicon.currentOpenLexicon = new GuiLexiconEntry(entry, new GuiLexiconIndex(entry.category));
	}
	
	@Override
	public void sparkleFX(World world, double x, double y, double z, float r, float g, float b, float size, int m, boolean fake) {
		FXSparkle sparkle = new FXSparkle(world, x, y, z, size, r, g, b, m);
		sparkle.fake = fake;
//		sparkle.noClip = true;
		Minecraft.getMinecraft().effectRenderer.addEffect(sparkle);
	}
	
	@Override
	public void wispFX(World world, double x, double y, double z, float r, float g, float b, float size, float motionx, float motiony, float motionz) {
		FXWisp wisp = new FXWisp(world, x, y, z, size, r, g, b);
		wisp.motionX = motionx;
		wisp.motionY = motiony;
		wisp.motionZ = motionz;

		Minecraft.getMinecraft().effectRenderer.addEffect(wisp);
	}
}
