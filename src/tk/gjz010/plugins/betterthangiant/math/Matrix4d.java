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
public class Matrix4d {
    public double data[][]=new double[4][4];
    public static Matrix4d IDENTITY(){
        Matrix4d result=new Matrix4d();
        result.data[0][0]=1;
        result.data[1][1]=1;
        result.data[2][2]=1;
        result.data[3][3]=1;
        return result;
    }
    public Matrix4d mul(Matrix4d r){
        double[][] lm=data;
        double[][] rm=r.data;
        Matrix4d result=new Matrix4d();
        
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                double item=0;
                for(int k=0;k<4;k++){
                    item+=lm[i][k]*rm[k][j];
                }
                result.data[i][j]=item;
            }
        }
        return result;
    }
    @Override
    public String toString(){
        String line="%f\t%f\t%f\t%f";
        String desc[]=new String[]{"Matrix4d:[",
            String.format(line, data[0][0],data[0][1],data[0][2],data[0][3]),
            String.format(line, data[1][0],data[1][1],data[1][2],data[1][3]),
            String.format(line, data[2][0],data[2][1],data[2][2],data[2][3]),
            String.format(line, data[3][0],data[3][1],data[3][2],data[3][3]),
            "]"
        };
        return String.join("\n", desc);
    }
}
