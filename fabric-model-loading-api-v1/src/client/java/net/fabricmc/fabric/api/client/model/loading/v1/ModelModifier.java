/*
 * Copyright (c) 2016, 2017, 2018, 2019 FabricMC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.fabricmc.fabric.api.client.model.loading.v1;

import net.fabricmc.fabric.api.event.Event;

import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.Baker;
import net.minecraft.client.render.model.ModelBakeSettings;
import net.minecraft.client.render.model.ModelLoader;
import net.minecraft.client.render.model.UnbakedModel;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.util.Identifier;

import java.util.function.Function;

/**
 * Contains interfaces for the events mods can use to modify models. These events have multiple phases: OVERRIDE,
 * DEFAULT, and WRAP, that fire in that order. These can be used to help maximize compatibility among multiple mods.
 */
public final class ModelModifier {
	/**
	 * Recommended phase to use when overriding models, e.g. replacing a model with another model.
	 */
	public static final Identifier OVERRIDE_PHASE = new Identifier("fabric", "override");
	/**
	 * Recommended phase to use for transformations that need to happen before wrapping, but after model overrides.
	 */
	public static final Identifier DEFAULT_PHASE = Event.DEFAULT_PHASE;
	/**
	 * Recommended phase to use when wrapping your own mod's existing models.
	 */
	public static final Identifier WRAP_SELF_PHASE = new Identifier("fabric", "wrap_self");
	/**
	 * Recommended phase to use when wrapping other models, e.g. for connected textures or other similar visual
	 * effects.
	 */
	public static final Identifier WRAP_FINAL_PHASE = new Identifier("fabric", "wrap_final");

	private ModelModifier() {}

	@FunctionalInterface
	public interface Unbaked {
		UnbakedModel modifyUnbakedModel(UnbakedModel model, Context context);

		record Context(Identifier location, ModelLoader loader) {}
	}

	@FunctionalInterface
	public interface Baked {
		BakedModel modifyBakedModel(BakedModel model, Context context);

		record Context(Identifier location, UnbakedModel sourceModel, Function<SpriteIdentifier, Sprite> textureGetter, ModelBakeSettings settings, Baker baker, ModelLoader loader) {}
	}
}
