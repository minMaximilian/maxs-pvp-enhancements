package minmaximilian.reclaim.data.fabric;

import minmaximilian.reclaim.data.DataGeneratorRoot;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class DataGenFabric implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        DataGeneratorRoot.gatherData(pack, fabricDataGenerator.getRegistries());
    }
}
