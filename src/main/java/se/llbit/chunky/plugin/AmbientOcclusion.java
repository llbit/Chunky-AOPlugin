/* Copyright (c) 2016 Jesper Öqvist <jesper@llbit.se>
 *
 * Chunky is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Chunky is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with Chunky.  If not, see <http://www.gnu.org/licenses/>.
 */
package se.llbit.chunky.plugin;

import se.llbit.chunky.Plugin;
import se.llbit.chunky.main.Chunky;
import se.llbit.chunky.main.ChunkyOptions;
import se.llbit.chunky.resources.TexturePackLoader;
import se.llbit.chunky.ui.ChunkyFx;

import java.io.FileNotFoundException;

/**
 * This plugin changes the Chunky path tracing renderer to render ambient occlusion.
 */
public class AmbientOcclusion implements Plugin {
  @Override public void attach(Chunky chunky) {
    chunky.setRayTracerFactory(AmbientOcclusionTracer::new);
  }

  public static void main(String[] args)
      throws FileNotFoundException, TexturePackLoader.TextureLoadingError {
    // Start Chunky normally with this plugin attached.
    Chunky.loadDefaultTextures();
    Chunky chunky = new Chunky(ChunkyOptions.getDefaults());
    new AmbientOcclusion().attach(chunky);
    ChunkyFx.startChunkyUI(chunky);
  }
}
