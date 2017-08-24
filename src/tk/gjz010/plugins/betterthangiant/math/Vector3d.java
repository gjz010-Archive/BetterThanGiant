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
 * Represents a 3-d vector.
 * @author Guo Jingzhe
 */
public class Vector3d {
    public double x;
    public double y;
    public double z;
    public Vector3d(){
        this(0,0,0);
    }
    public Vector3d(double x,double y,double z){
        this.x=x;
        this.y=y;
        this.z=z;
    }
    public Matrix4d toTranslateMatrix(){
        Matrix4d result=Matrix4d.IDENTITY();
        result.data[0][3]=x;
        result.data[1][3]=y;
        result.data[2][3]=z;
        return result;
    }
    public Vector4d toVector4d(){
        return new Vector4d(x,y,z,1);
    }
    @Override
    public String toString(){
        return String.format("Vector3d [%f %f %f]",x,y,z);
    }
}
