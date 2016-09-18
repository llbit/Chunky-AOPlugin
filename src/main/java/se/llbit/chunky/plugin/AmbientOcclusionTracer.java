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

import se.llbit.chunky.renderer.WorkerState;
import se.llbit.chunky.renderer.scene.PreviewRayTracer;
import se.llbit.chunky.renderer.scene.RayTracer;
import se.llbit.chunky.renderer.scene.Scene;
import se.llbit.math.Ray;

import java.util.Random;

public class AmbientOcclusionTracer implements RayTracer {
  @Override public void trace(Scene scene, WorkerState state) {
    Ray ray = state.ray;
    Random random = state.random;
    while (true) {
      if (!PreviewRayTracer.nextIntersection(scene, ray)) {
        // The ray exited the scene.
        ray.color.set(1, 1, 1, 1);
        break;
      } else if (!scene.kill(ray.depth + 1, random)) {
        ray.diffuseReflection(ray, random);
      } else {
        ray.color.set(0, 0, 0, 1);
        break;
      }
    }
  }
}
