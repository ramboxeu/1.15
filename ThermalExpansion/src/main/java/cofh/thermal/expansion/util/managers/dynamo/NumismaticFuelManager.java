package cofh.thermal.expansion.util.managers.dynamo;

import cofh.lib.inventory.FalseIInventory;
import cofh.thermal.core.util.managers.SingleItemFuelManager;
import cofh.thermal.core.util.recipes.ThermalFuel;
import cofh.thermal.core.util.recipes.internal.IDynamoFuel;
import cofh.thermal.expansion.util.recipes.TExpRecipeTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.util.ResourceLocation;

import java.util.Map;

public class NumismaticFuelManager extends SingleItemFuelManager {

    private static final NumismaticFuelManager INSTANCE = new NumismaticFuelManager();
    protected static int DEFAULT_ENERGY = 16000;

    public static NumismaticFuelManager instance() {

        return INSTANCE;
    }

    private NumismaticFuelManager() {

        super(DEFAULT_ENERGY);
    }

    public int getEnergy(ItemStack stack) {

        IDynamoFuel fuel = getFuel(stack);
        return fuel != null ? fuel.getEnergy() : 0;
    }

    // region IManager
    @Override
    public void config() {

    }

    @Override
    public void refresh(RecipeManager recipeManager) {

        clear();
        Map<ResourceLocation, IRecipe<FalseIInventory>> recipes = recipeManager.getRecipes(TExpRecipeTypes.FUEL_NUMISMATIC);
        for (Map.Entry<ResourceLocation, IRecipe<FalseIInventory>> entry : recipes.entrySet()) {
            addFuel((ThermalFuel) entry.getValue());
        }
    }
    // endregion
}
