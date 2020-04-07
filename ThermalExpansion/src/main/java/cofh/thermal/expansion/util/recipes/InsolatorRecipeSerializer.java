package cofh.thermal.expansion.util.recipes;

import cofh.thermal.core.util.recipes.ThermalRecipe;
import cofh.thermal.core.util.recipes.ThermalRecipeSerializer;
import com.google.gson.JsonObject;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;

import java.util.ArrayList;

import static cofh.thermal.core.util.recipes.RecipeJsonUtils.*;

public class InsolatorRecipeSerializer<T extends ThermalRecipe> extends ThermalRecipeSerializer<T> {

    protected final int defaultWater;

    public InsolatorRecipeSerializer(IFactory<T> recipeFactory, int defaultEnergy, int defaultWater) {

        super(recipeFactory, defaultEnergy);
        this.defaultWater = defaultWater;
    }

    @Override
    public T read(ResourceLocation recipeId, JsonObject json) {

        int energy = defaultEnergy;
        int water = defaultWater;
        float experience = 0.0F;

        ArrayList<Ingredient> inputItems = new ArrayList<>();
        ArrayList<FluidStack> inputFluids = new ArrayList<>();
        ArrayList<ItemStack> outputItems = new ArrayList<>();
        ArrayList<Float> outputItemChances = new ArrayList<>();
        ArrayList<FluidStack> outputFluids = new ArrayList<>();

        /* INPUT */
        parseInputs(inputItems, inputFluids, json.get(INGREDIENT));

        /* OUTPUT */
        parseOutputs(outputItems, outputItemChances, outputFluids, json.get(RESULT));

        /* ENERGY */
        if (json.has(ENERGY)) {
            energy = json.get(ENERGY).getAsInt();
        }
        if (json.has(ENERGY_MOD)) {
            energy *= json.get(ENERGY_MOD).getAsFloat();
        }
        /* EXPERIENCE */
        if (json.has(EXPERIENCE)) {
            experience = json.get(EXPERIENCE).getAsFloat();
        }
        /* WATER */
        if (json.has(WATER)) {
            water = json.get(WATER).getAsInt();
        }
        if (inputFluids.isEmpty()) {
            inputFluids.add(new FluidStack(Fluids.WATER, water));
        }
        return recipeFactory.create(recipeId, energy, experience, inputItems, inputFluids, outputItems, outputItemChances, outputFluids);
    }

}