package cofh.thermal.expansion.util.recipes.machine;

import cofh.thermal.core.util.IMachineInventory;
import cofh.thermal.core.util.recipes.internal.BaseMachineRecipe;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.List;

import static cofh.lib.util.constants.Constants.BASE_CHANCE_LOCKED;

public class BottlerRecipePotion extends BaseMachineRecipe {

    public BottlerRecipePotion(int energy, float experience, int minTicks, @Nonnull ItemStack inputItem, @Nonnull FluidStack inputFluid, @Nonnull ItemStack outputItem) {

        super(energy, experience, minTicks);
        this.inputItems.add(inputItem);
        this.inputFluids.add(inputFluid);
        this.outputItems.add(outputItem);
        this.outputItemChances.add(BASE_CHANCE_LOCKED);
    }

    @Override
    public List<ItemStack> getOutputItems(IMachineInventory inventory) {

        FluidStack fluidPotion = inventory.inputTanks().get(0).getFluidStack();
        ItemStack itemPotion = outputItems.get(0).copy();
        if (fluidPotion.hasTag()) {
            itemPotion.setTag(fluidPotion.getTag().copy());
        }
        return Collections.singletonList(itemPotion);
    }

}
