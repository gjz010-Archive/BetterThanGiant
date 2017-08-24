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
package tk.gjz010.plugins.betterthangiant.bone;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import tk.gjz010.plugins.betterthangiant.renderer.ModelRenderer;
import tk.gjz010.plugins.betterthangiant.math.Matrix4d;
import tk.gjz010.plugins.betterthangiant.math.Quaternion;

/**
 * Represents a model.
 * A model is a special(dummy) Joint that handles all joints.
 * It decides the whole yaw, pitch and roll of all joints.
 * This had better be on the center (0,0,0) of the model;
 * @author Guo Jingzhe
 */
public class Model extends Joint{
    protected Map<String,Joint> total_joints;
    protected Map<String,String> joint_texture;

    /**
     * Creating a model joint.
     * @param name The name of the model.
     */
    public Model(String name){
        super(name,null);
        total_joints=new HashMap<>();
        m=this;
    }
    public Joint findJoint(String name){
        return total_joints.get(name);
    }
    
    public void render(ModelRenderer fm,boolean forced){
        super.render(fm, fm.getInitialTranslation(), fm.getInitialPose(),forced);
    }
    public void render(ModelRenderer fm){
        render(fm,false);
    }
    
    
}
