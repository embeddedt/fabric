package net.fabricmc.fabric.api.client.model.loading.v1;

import net.minecraft.client.render.model.ModelLoader;
import net.minecraft.client.render.model.UnbakedModel;
import net.minecraft.util.Identifier;

@FunctionalInterface
public interface UnbakedModelObserver {
	UnbakedModel observeUnbakedModel(Context context);

	record Context(Identifier location, UnbakedModel model, ModelLoader loader) {
		public Context withModel(UnbakedModel newModel) {
			if(model == newModel)
				return this;
			else
				return new Context(location, newModel, loader);
		}
	}
}
