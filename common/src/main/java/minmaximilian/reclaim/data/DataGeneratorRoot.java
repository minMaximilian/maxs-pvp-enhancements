package minmaximilian.reclaim.data;

import java.util.concurrent.CompletableFuture;

import minmaximilian.reclaim.data.providers.BlockItemsRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;

public class DataGeneratorRoot {

    public static void gatherData(DataGenerator.PackGenerator gen, CompletableFuture<HolderLookup.Provider> registries) {
        gen.addProvider(output -> new BlockItemsRecipeProvider(output, registries));
    }
}
