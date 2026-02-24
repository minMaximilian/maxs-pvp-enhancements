package minmaximilian.reclaim.data.providers;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import minmaximilian.reclaim.Reclaim;
import minmaximilian.reclaim.block.ReclaimBlocks;
import minmaximilian.reclaim.item.ReclaimItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;

@SuppressWarnings("unused")
public class BlockItemsRecipeProvider extends ReclaimRecipeProvider {

    GeneratedRecipe HEPHAESTUS_BAG = create(ReclaimItems.HEPHAESTUS_BAG)
        .unlockedBy(() -> Items.NETHER_STAR)
        .viaShaped(b -> b
            .define('+', Items.NETHER_STAR)
            .define('#', () -> Items.ECHO_SHARD)
            .pattern("###")
            .pattern("#+#")
            .pattern("###")
        );

    GeneratedRecipe WALL_PLASTER = create(ReclaimBlocks.WALL_PLASTER)
        .unlockedBy(() -> Blocks.GRAVEL)
        .returns(16)
        .viaShapeless(b -> b
            .requires(Blocks.GRAVEL)
            .requires(Blocks.SAND)
            .requires(Blocks.CLAY)
        );

    public BlockItemsRecipeProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> registries) {
        super(pOutput, registries);
    }

    GeneratedRecipeBuilder create(Supplier<? extends ItemLike> result) {
        return new GeneratedRecipeBuilder("/", result);
    }

    GeneratedRecipeBuilder create(ResourceLocation result) {
        return new GeneratedRecipeBuilder("/", result);
    }

    class GeneratedRecipeBuilder {

        private final String path;
        private String suffix;
        private Supplier<? extends ItemLike> result;
        private ResourceLocation compatDatagenOutput;

        private Supplier<? extends ItemLike> unlockedByItem;
        private int amount;

        private GeneratedRecipeBuilder(String path) {
            this.path = path;
            this.suffix = "";
            this.amount = 1;
        }

        public GeneratedRecipeBuilder(String path, Supplier<? extends ItemLike> result) {
            this(path);
            this.result = result;
        }

        public GeneratedRecipeBuilder(String path, ResourceLocation result) {
            this(path);
            this.compatDatagenOutput = result;
        }

        private static ResourceLocation clean(ResourceLocation loc) {
            String path = loc.getPath();
            while (path.contains("//")) {
                path = path.replaceAll("//", "/");
            }
            return ResourceLocation.fromNamespaceAndPath(loc.getNamespace(), path);
        }

        GeneratedRecipeBuilder returns(int amount) {
            this.amount = amount;
            return this;
        }

        GeneratedRecipeBuilder unlockedBy(Supplier<? extends ItemLike> item) {
            this.unlockedByItem = item;
            return this;
        }

        GeneratedRecipeBuilder withSuffix(String suffix) {
            this.suffix = suffix;
            return this;
        }

        GeneratedRecipe viaShaped(UnaryOperator<ShapedRecipeBuilder> builder) {
            return register(output -> {
                ShapedRecipeBuilder b = builder.apply(
                    ShapedRecipeBuilder.shaped(RecipeCategory.MISC, result.get(), amount));
                if (unlockedByItem != null) {
                    b.unlockedBy("has_item", has(unlockedByItem.get()));
                }
                b.save(output, createLocation("crafting"));
            });
        }

        GeneratedRecipe viaShapeless(UnaryOperator<ShapelessRecipeBuilder> builder) {
            return register(output -> {
                ShapelessRecipeBuilder b = builder.apply(
                    ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, result.get(), amount));
                if (unlockedByItem != null) {
                    b.unlockedBy("has_item", has(unlockedByItem.get()));
                }
                b.save(output, createLocation("crafting"));
            });
        }

        private ResourceLocation createLocation(String recipeType) {
            return clean(
                Reclaim.asResource(recipeType + "/" + path + "/" + getRegistryName().getPath() + suffix));
        }

        private ResourceLocation getRegistryName() {
            return compatDatagenOutput == null ? BuiltInRegistries.ITEM.getKey(result.get()
                .asItem()) : compatDatagenOutput;
        }
    }
}
