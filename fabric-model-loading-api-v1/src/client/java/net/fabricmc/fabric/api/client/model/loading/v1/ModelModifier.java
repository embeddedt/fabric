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
	public static final Identifier OVERRIDE_PHASE = new Identifier("fabric", "override");
	public static final Identifier DEFAULT_PHASE = new Identifier("fabric", "default");
	public static final Identifier WRAP_PHASE = new Identifier("fabric", "wrap");

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
