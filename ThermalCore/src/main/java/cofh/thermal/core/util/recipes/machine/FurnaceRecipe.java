package cofh.thermal.core.util.recipes.machine;

import cofh.thermal.core.init.ThermalRecipeTypes;
import cofh.thermal.core.util.recipes.ThermalRecipe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.AbstractCookingRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;

import static cofh.lib.util.constants.Constants.BASE_CHANCE_LOCKED;
import static cofh.thermal.core.ThermalCore.RECIPE_SERIALIZERS;
import static cofh.thermal.core.init.ThermalReferences.ID_RECIPE_FURNACE;

public class FurnaceRecipe extends ThermalRecipe {

    public FurnaceRecipe(ResourceLocation recipeId, int energy, float experience, @Nullable List<Ingredient> inputItems, @Nullable List<FluidStack> inputFluids, @Nullable List<ItemStack> outputItems, @Nullable List<Float> outputItemChances, @Nullable List<FluidStack> outputFluids) {

        super(recipeId, energy, experience, inputItems, inputFluids, outputItems, outputItemChances, outputFluids);
    }

    public FurnaceRecipe(ResourceLocation recipeId, int energy, float experience, AbstractCookingRecipe recipe) {

        this(recipeId, energy, experience, recipe.getIngredients(), Collections.emptyList(), Collections.singletonList(recipe.getRecipeOutput()), Collections.singletonList(BASE_CHANCE_LOCKED), Collections.emptyList());
    }

    @Nonnull
    @Override
    public IRecipeSerializer<?> getSerializer() {

        return RECIPE_SERIALIZERS.get(ID_RECIPE_FURNACE);
    }

    @Nonnull
    @Override
    public IRecipeType<?> getType() {

        return ThermalRecipeTypes.RECIPE_FURNACE;
    }

    //    @Nonnull
    //    @Override
    //    public String getGroup() {
    //
    //        return MACHINE_FURNACE_BLOCK.getTranslationKey();
    //    }
    //
    //    @Nonnull
    //    @Override
    //    public ItemStack getIcon() {
    //
    //        return new ItemStack(MACHINE_FURNACE_BLOCK);
    //    }

}