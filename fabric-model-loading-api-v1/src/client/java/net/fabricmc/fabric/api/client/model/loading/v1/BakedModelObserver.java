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
	BakedModel observeBakedModel(BakedModel model, Context context);

	record Context(Identifier location, UnbakedModel sourceModel, Function<SpriteIdentifier, Sprite> textureGetter, ModelBakeSettings settings, Baker baker, ModelLoader loader) {}
}
