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
public class MathHelper {
    
    /**
     * Applying a rotation to a vector.
     * @param q The rotation.
     * @param v The vector.
     * @return The result vector.
     */
    public static Vector3d applyRotate(Quaternion q,Vector3d v){
        Quaternion qv=new Quaternion(0,v.x,v.y,v.z);
        Quaternion qconj=q.conj();
        Quaternion qvn=q.mul(qv).mul(qconj);
        return new Vector3d(qvn.x,qvn.y,qvn.z);
    }
    public static Vector4d mulMatVec4d(Matrix4d mat,Vector4d vec){
        double data[]=new double[4];
        double veca[]=new double[]{vec.x,vec.y,vec.z,vec.w};
        for(int i=0;i<4;i++){
            double arg=0;
            for(int j=0;j<4;j++){
                arg+=mat.data[i][j]*veca[j];
            }
            data[i]=arg;
        }
        return new Vector4d(data[0],data[1],data[2],data[3]);
    }
    public static Matrix4d rotX(double angle){
        Matrix4d result=Matrix4d.IDENTITY();
        double c=Math.cos(angle);
        double s=Math.sin(angle);
        result.data[1][1]=c;
        result.data[1][2]=-s;
        result.data[2][1]=s;
        result.data[2][2]=c;
        return result;
    }
    public static Matrix4d rotY(double angle){
        Matrix4d result=Matrix4d.IDENTITY();
        double c=Math.cos(angle);
        double s=Math.sin(angle);
        result.data[0][0]=c;
        result.data[2][0]=-s;
        result.data[0][2]=s;
        result.data[2][2]=c;
        return result;
    }
    public static Matrix4d rotZ(double angle){
        Matrix4d result=Matrix4d.IDENTITY();
        double c=Math.cos(angle);
        double s=Math.sin(angle);
        result.data[0][0]=c;
        result.data[0][1]=-s;
        result.data[1][0]=s;
        result.data[1][1]=c;
        return result;
    }
    
    /**
     * Combining a translation and a rotation.
     * @param rotate The rotation.
     * @param translate The translation.
     * @return The result matrix.
     */
    @Deprecated
    public static Matrix4d combine(Matrix4d rotate,Matrix4d translate){
        Matrix4d result=new Matrix4d();
        result.data=rotate.data.clone();
        for(int i=0;i<4;i++){
            result.data[i][3]=translate.data[i][3];
        }
        return result;
    }
}
