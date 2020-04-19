package cofh.lib.block.deco;

import cofh.lib.util.helpers.StringHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CarvedPumpkinBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShearsItem;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

import java.util.function.Supplier;

public class CarvedPumpkinBlockCoFH extends CarvedPumpkinBlock {

    protected Supplier<Block> carvePrev;
    protected Supplier<Block> carveNext;

    /**
     * This ensures that the predicate check isn't stupid. Can't do this for other hardcoded cases unfortunately.
     */
    public static void updateTest() {

        IS_PUMPKIN = (state) -> state != null && (state.getBlock() instanceof CarvedPumpkinBlockCoFH);
    }

    public CarvedPumpkinBlockCoFH(Properties properties) {

        super(properties);
    }

    public CarvedPumpkinBlockCoFH setCarvePrev(Supplier<Block> carvePrev) {

        this.carvePrev = carvePrev;
        return this;
    }

    public CarvedPumpkinBlockCoFH setCarveNext(Supplier<Block> carveNext) {

        this.carveNext = carveNext;
        return this;
    }

    @Override
    public String getTranslationKey() {

        String specificTranslation = Util.makeTranslationKey("block", Registry.BLOCK.getKey(this));
        if (StringHelper.canLocalize(specificTranslation)) {
            return specificTranslation;
        }
        return this.lightValue > 0 ? "block.minecraft.jack_o_lantern" : "block.minecraft.carved_pumpkin";
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {

        ItemStack itemstack = player.getHeldItem(handIn);
        if (itemstack.getItem() instanceof ShearsItem) {
            if (!worldIn.isRemote) {
                Direction direction = hit.getFace();
                Direction direction1 = direction.getAxis() == Direction.Axis.Y ? player.getHorizontalFacing().getOpposite() : direction;
                worldIn.playSound(null, pos, SoundEvents.BLOCK_PUMPKIN_CARVE, SoundCategory.BLOCKS, 1.0F, 1.0F);
                worldIn.setBlockState(pos, (player.isShiftKeyDown() ? carvePrev.get() : carveNext.get())
                        .getDefaultState()
                        .with(CarvedPumpkinBlock.FACING, direction1), 11);
                //                itemstack.damageItem(1, player, (consumer) -> {
                //                    consumer.sendBreakAnimation(handIn);
                //                });
            }
            return ActionResultType.SUCCESS;
        } else {
            return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
        }
    }

}
