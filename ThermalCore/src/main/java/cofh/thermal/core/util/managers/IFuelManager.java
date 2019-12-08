package cofh.thermal.core.util.managers;

import cofh.thermal.core.util.IThermalInventory;
import cofh.thermal.core.util.recipes.IDynamoFuel;

import java.util.List;

public interface IFuelManager extends IManager {

    default boolean validFuel(IThermalInventory inventory) {

        return getFuel(inventory) != null;
    }

    IDynamoFuel getFuel(IThermalInventory inventory);

    List<IDynamoFuel> getFuelList();

}
