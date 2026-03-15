package minmaximilian.reclaim.data.fabric;

import minmaximilian.reclaim.Reclaim;
import minmaximilian.reclaim.data.providers.fabric.BlockItemsRecipeProviderImpl;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.data.recipes.RecipeProvider;

public class DataGenFabric implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        Reclaim.init();
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider((output, registries) -> (RecipeProvider) BlockItemsRecipeProviderImpl.create(output, registries));
        Thread shutdown = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ignored) {
            }
            System.exit(0);
        }, "reclaim-datagen-shutdown");
        shutdown.setDaemon(true);
        shutdown.start();
    }
}
