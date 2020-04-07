package cofh.thermal.expansion.gui.client;

import cofh.core.gui.element.ElementScaled;
import cofh.core.gui.element.ElementScaled.StartDirection;
import cofh.core.gui.element.ElementScaledFluid;
import cofh.core.util.GuiHelper;
import cofh.lib.util.helpers.StringHelper;
import cofh.thermal.core.gui.client.MachineScreenBase;
import cofh.thermal.expansion.inventory.container.MachineCrucibleContainer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

import static cofh.core.util.GuiHelper.*;
import static cofh.lib.util.constants.Constants.ID_THERMAL;

public class MachineCrucibleScreen extends MachineScreenBase<MachineCrucibleContainer> {

    public static final String TEX_PATH = ID_THERMAL + ":textures/gui/machine/crucible.png";
    public static final ResourceLocation TEXTURE = new ResourceLocation(TEX_PATH);

    public MachineCrucibleScreen(MachineCrucibleContainer container, PlayerInventory inv, ITextComponent titleIn) {

        super(container, inv, container.tile, StringHelper.getTextComponent("block.thermal.machine_crucible"));
        texture = TEXTURE;
        info = generateTabInfo("info.thermal.machine_crucible");
    }

    @Override
    public void init() {

        super.init();

        addElement(GuiHelper.createLargeFluidStorage(this, 151, 8, tile.getTank(0)));

        progressOverlay = (ElementScaledFluid) addElement(new ElementScaledFluid(this, 103, 34).setFluid(tile.getRenderFluid()).setDirection(StartDirection.LEFT).setSize(PROGRESS, 16).setTexture(PROG_DROP_RIGHT, 64, 16));
        // progress = (ElementScaled) addElement(new ElementScaled(this, 103, 34).setDirection(StartDirection.LEFT).setSize(PROGRESS, 16).setTexture(PROG_DROP_RIGHT, 64, 16));
        speed = (ElementScaled) addElement(new ElementScaled(this, 53, 44).setSize(16, SPEED).setTexture(SCALE_FLAME, 32, 16));
    }

}
