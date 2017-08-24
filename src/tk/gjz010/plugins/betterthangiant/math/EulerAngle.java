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
 *
 * @author Guo Jingzhe
 */
public class EulerAngle {

    public double pitch; //pitch
    public double yaw; //yaw
    public double roll; //roll

    public EulerAngle(double x, double y, double z) {
        this.pitch = x;
        this.yaw = y;
        this.roll = z;
    }

    public EulerAngle() {
    }

    public Quaternion toQuaternion() {
        double cosRoll = Math.cos(roll * 0.5);
        double sinRoll = Math.sin(roll * 0.5);

        double cosPitch = Math.cos(pitch * 0.5);
        double sinPitch = Math.sin(pitch * 0.5);

        double cosYaw = Math.cos(yaw * 0.5);
        double sinYaw = Math.sin(yaw * 0.5);

        double q0 = cosRoll * cosPitch * cosYaw + sinRoll * sinPitch * sinYaw;
        double q1 = sinRoll * cosPitch * cosYaw - cosRoll * sinPitch * sinYaw;
        double q2 = cosRoll * sinPitch * cosYaw + sinRoll * cosPitch * sinYaw;
        double q3 = cosRoll * cosPitch * sinYaw - sinRoll * sinPitch * cosYaw;
        return new Quaternion(q0,q1,q2,q3);
    }
    public Matrix4d toRotateMatrix(){
        /*
        System.out.println("Matrix X!");
        System.out.println(pitch);
        System.out.println(MathHelper.rotX(pitch));
        System.out.println("Matrix Y!");
        System.out.println(yaw);
        System.out.println(MathHelper.rotX(yaw));
        System.out.println("Matrix Z!");
        System.out.println(roll);
        System.out.println(MathHelper.rotX(roll));
        System.out.println("Result!");
        System.out.println(MathHelper.rotZ(roll).mul(MathHelper.rotY(yaw)).mul(MathHelper.rotX(pitch)));
        */
        return MathHelper.rotZ(roll).mul(MathHelper.rotY(yaw)).mul(MathHelper.rotX(pitch));
    }
}
