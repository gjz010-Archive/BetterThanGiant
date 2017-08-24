/*
 *     BetterThanGiant - Simple FK animation generator for Bukkit/Spigot, Minecraft.
 *     Copyright (C) 2017  gjz010
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU Affero General Public License as published
 *     by the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU Affero General Public License for more details.
 *
 *     You should have received a copy of the GNU Affero General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package tk.gjz010.plugins.betterthangiant.renderer;

import tk.gjz010.plugins.betterthangiant.bone.Joint;
import tk.gjz010.plugins.betterthangiant.math.EulerAngle;
import tk.gjz010.plugins.betterthangiant.math.Matrix4d;
import tk.gjz010.plugins.betterthangiant.math.Quaternion;
import tk.gjz010.plugins.betterthangiant.math.Vector3d;

/**
 * A renderer that shows an abstract model on Minecraft world.
 *
 * @author Guo Jingzhe
 */
public interface ModelRenderer {

    /**
     * Rendering one Joint of the model.
     *
     * @param j The joint to render.
     * @param ttrans The world coordinate of the joint.
     * @param tpose The rotation of the joint.
     */
    public void apply(Joint j, Vector3d ttrans, EulerAngle tpose);

    /**
     * Returning the initial translation of the model. It is usually the global
     * property of a model, like where the model is put and how the model is
     * rotated.
     *
     * @return The initial translation of the model.
     */
    public default Matrix4d getInitialTranslation() {
        return Matrix4d.IDENTITY();
    }

    /**
     * Returning the initial rotation of the model.
     * @return The initial rotation of the model.
     */
    public default Quaternion getInitialPose() {
        return new Quaternion(1, 0, 0, 0);
    }

}
