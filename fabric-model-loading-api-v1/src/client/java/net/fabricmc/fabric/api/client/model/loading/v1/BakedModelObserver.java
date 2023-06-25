package net.fabricmc.fabric.api.client.model.loading.v1;

import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.Baker;
import net.minecraft.client.render.model.ModelBakeSettings;
import net.minecraft.client.render.model.ModelLoader;
import net.minecraft.client.render.model.UnbakedModel;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.util.Identifier;

import java.util.function.Function;

@FunctionalInterface
public interface BakedModelObserver {
	BakedModel observeBakedModel(Context context);

	record Context(Identifier location, UnbakedModel model, BakedModel bakedModel, Function<SpriteIdentifier, Sprite> textureGetter, ModelBakeSettings settings, Baker baker, ModelLoader loader) {
		public Context withModel(BakedModel newModel) {
			if(bakedModel == newModel)
				return this;
			else
				return new Context(location, model, newModel, textureGetter, settings, baker, loader);
		}
	}
}
