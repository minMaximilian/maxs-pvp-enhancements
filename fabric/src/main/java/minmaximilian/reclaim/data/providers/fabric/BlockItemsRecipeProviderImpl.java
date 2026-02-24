package minmaximilian.reclaim.data.providers.fabric;

import java.util.concurrent.CompletableFuture;

import minmaximilian.reclaim.data.providers.BlockItemsRecipeProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;

public class BlockItemsRecipeProviderImpl extends BlockItemsRecipeProvider {

    protected BlockItemsRecipeProviderImpl(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> registries) {
        super(packOutput, registries);
    }

    public static RecipeProvider create(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> registries) {
        BlockItemsRecipeProviderImpl provider = new BlockItemsRecipeProviderImpl(packOutput, registries);
        return new FabricRecipeProvider((FabricDataOutput) packOutput, registries) {
            @Override
            public void buildRecipes(RecipeOutput exporter) {
                provider.buildRecipes(exporter);
            }
        };
    }
}
