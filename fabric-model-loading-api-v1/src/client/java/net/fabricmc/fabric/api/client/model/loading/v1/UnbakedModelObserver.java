package net.fabricmc.fabric.api.client.model.loading.v1;

import net.minecraft.client.render.model.ModelLoader;
import net.minecraft.client.render.model.UnbakedModel;
import net.minecraft.util.Identifier;

@FunctionalInterface
public interface UnbakedModelObserver {
	UnbakedModel observeUnbakedModel(UnbakedModel model, Context context);

	record Context(Identifier location, ModelLoader loader) {}
}
