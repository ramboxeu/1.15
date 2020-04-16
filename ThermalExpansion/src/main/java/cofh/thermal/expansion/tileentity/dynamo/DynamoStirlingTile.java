package cofh.thermal.expansion.tileentity.dynamo;

import cofh.lib.inventory.ItemStorageCoFH;
import cofh.thermal.core.tileentity.DynamoTileBase;
import cofh.thermal.core.util.managers.dynamo.StirlingFuelManager;
import cofh.thermal.expansion.inventory.container.dynamo.DynamoStirlingContainer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;

import javax.annotation.Nullable;

import static cofh.lib.util.StorageGroup.INPUT;
import static cofh.thermal.expansion.init.TExpReferences.DYNAMO_STIRLING_TILE;

public class DynamoStirlingTile extends DynamoTileBase {

    protected ItemStorageCoFH fuelSlot = new ItemStorageCoFH(StirlingFuelManager.instance()::validFuel);

    public DynamoStirlingTile() {

        super(DYNAMO_STIRLING_TILE);

        inventory.addSlot(fuelSlot, INPUT);
    }

    // region PROCESS
    @Override
    protected boolean canProcessStart() {

        return StirlingFuelManager.instance().getEnergy(fuelSlot.getItemStack()) > 0;
    }

    @Override
    protected void processStart() {

        fuel += StirlingFuelManager.instance().getEnergy(fuelSlot.getItemStack());
        fuelSlot.consume();
    }
    // endregion

    @Nullable
    @Override
    public Container createMenu(int i, PlayerInventory inventory, PlayerEntity player) {

        return new DynamoStirlingContainer(i, world, pos, inventory, player);
    }

}