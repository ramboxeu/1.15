package cofh.thermal.expansion.tileentity.dynamo;

import cofh.lib.fluid.FluidStorageCoFH;
import cofh.thermal.core.tileentity.DynamoTileBase;
import cofh.thermal.expansion.inventory.container.dynamo.DynamoCompressionContainer;
import cofh.thermal.expansion.util.managers.dynamo.CompressionFuelManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;

import javax.annotation.Nullable;

import static cofh.lib.util.StorageGroup.INPUT;
import static cofh.lib.util.constants.Constants.TANK_SMALL;
import static cofh.thermal.core.common.ThermalConfig.dynamoAugments;
import static cofh.thermal.core.util.managers.SingleFluidFuelManager.FLUID_FUEL_AMOUNT;
import static cofh.thermal.expansion.init.TExpReferences.DYNAMO_COMPRESSION_TILE;

public class DynamoCompressionTile extends DynamoTileBase {

    protected FluidStorageCoFH fuelTank = new FluidStorageCoFH(TANK_SMALL, CompressionFuelManager.instance()::validFuel);

    public DynamoCompressionTile() {

        super(DYNAMO_COMPRESSION_TILE);

        tankInv.addTank(fuelTank, INPUT);

        addAugmentSlots(dynamoAugments);
        initHandlers();
    }

    // region PROCESS
    @Override
    protected boolean canProcessStart() {

        return CompressionFuelManager.instance().getEnergy(fuelTank.getFluidStack()) > 0 && fuelTank.getAmount() >= FLUID_FUEL_AMOUNT;
    }

    @Override
    protected void processStart() {

        fuel += fuelMax = Math.round(CompressionFuelManager.instance().getEnergy(fuelTank.getFluidStack()) * energyMod);
        fuelTank.modify(-FLUID_FUEL_AMOUNT);
    }
    // endregion

    @Nullable
    @Override
    public Container createMenu(int i, PlayerInventory inventory, PlayerEntity player) {

        return new DynamoCompressionContainer(i, world, pos, inventory, player);
    }

}
