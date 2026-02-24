package minmaximilian.reclaim.data.providers.neoforge;

import java.util.concurrent.CompletableFuture;

import org.jetbrains.annotations.NotNull;

import minmaximilian.reclaim.data.providers.BlockItemsRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;

public class BlockItemsRecipeProviderImpl extends BlockItemsRecipeProvider {

    protected BlockItemsRecipeProviderImpl(PackOutput pPackoutput, CompletableFuture<HolderLookup.Provider> registries) {
        super(pPackoutput, registries);
    }

    public static RecipeProvider create(PackOutput gen, CompletableFuture<HolderLookup.Provider> registries) {
        BlockItemsRecipeProviderImpl provider = new BlockItemsRecipeProviderImpl(gen, registries);
        return new RecipeProvider(gen, registries) {
            @Override
            protected void buildRecipes(@NotNull RecipeOutput output) {
                provider.buildRecipes(output);
            }
        };
    }
}
