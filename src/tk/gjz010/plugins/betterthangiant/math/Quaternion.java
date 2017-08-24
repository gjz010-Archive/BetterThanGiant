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
package tk.gjz010.plugins.betterthangiant.math;

/**
 * Represents a quaternion.
 *
 * @author Guo Jingzhe
 */
public class Quaternion {

    public double w;
    public double x;
    public double y;
    public double z;

    public Quaternion(double w, double x, double y, double z) {
        this.w = w;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Quaternion mul(Quaternion q) {
        double w1 = w, x1 = x, y1 = y, z1 = z;
        double w2 = q.w, x2 = q.x, y2 = q.y, z2 = q.z;
        return new Quaternion(w1 * w2 - x1 * x2 - y1 * y2 - z1 * z2,
                w1 * x2 + x1 * w2 + y1 * z2 - z1 * y2,
                w1 * y2 - x1 * z2 + y1 * w2 + z1 * x2,
                w1 * z2 + x1 * y2 - y1 * x2 + z1 * w2);
    }
    public Quaternion conj(){
        return new Quaternion(w,-x,-y,-z);
    }
    public EulerAngle toEulerAngle() {
        double roll = Math.atan2(2.0 * (y * z + w * x), w * w - x * x - y * y + z * z);
        double pitch = Math.asin(2.0 * (w * y - x * z));
        double yaw = Math.atan2(2.0 * (x * y + w * z), w * w + x * x - y * y - z * z);
        return new EulerAngle(pitch, yaw, roll);
    }
}
