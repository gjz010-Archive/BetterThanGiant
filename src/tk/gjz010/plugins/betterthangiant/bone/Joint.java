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

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import tk.gjz010.plugins.betterthangiant.renderer.ModelRenderer;
import tk.gjz010.plugins.betterthangiant.math.EulerAngle;
import tk.gjz010.plugins.betterthangiant.math.MathHelper;
import tk.gjz010.plugins.betterthangiant.math.Matrix4d;
import tk.gjz010.plugins.betterthangiant.math.Quaternion;
import tk.gjz010.plugins.betterthangiant.math.Vector3d;

/**
 * Represents one joint in the model.
 * @author Guo Jingzhe
 */
public class Joint {
    protected List<Joint> sub_joints;
    

    public EulerAngle angle; //Current pose(rotation) of the joint.
    public Vector3d pos; //Current position(translation) of the joint.
    protected Model m;
    protected Joint parent;
    protected String name;
    protected boolean dirty;
    public Joint(String name,Joint j){
        this.dirty = true;
        this.pos = new Vector3d();
        this.angle = new EulerAngle();
        this.name=name;
        this.sub_joints=new ArrayList<>();
        if(j!=null)
        this.attachTo(j);
    }
    private void attachTo(Joint j){
        //if(parent!=null) throw new IllegalStateException("Attaching an attached joint is forbidden!");
        this.parent=j;
        j.sub_joints.add(this);
        m=j.getModel();
        m.total_joints.put(name, this);
    }

    /**
     * Setting a Joint to be refreshed next render.
     */
    public void taint(){
        dirty=true;
        if(parent!=null)
        parent.taint();
    }

    /**
     * Getting the model.
     * @return The parent model.
     */
    public Model getModel(){
        return m;
    }

    /**
     * Getting the name.
     * @return The name.
     */
    public String getName(){
        return name;
    }

    /**
     * Lambda iteration helper through Joints.
     * @param func
     */
    public void forEach(Consumer<? super Joint> func){
        func.accept(this);
        sub_joints.forEach(func);
    }
    //Think of trans as a group of unit base vectors at one point.
    //When you apply trans to a vector v (trans*v) you translate the relative v
    //to the specific position using the specific base.

    /**
     * 
     * @param rmodel The specified renderer.
     * @param trans The translation matrix stack.
     * @param pose The rotation quaternion stack.
     * @param forced Whether to render clean Joints which haven't been tainted.
     */
    public void render(ModelRenderer rmodel,Matrix4d trans,Quaternion pose,boolean forced){ 
        if(dirty ||forced){
            
            Vector3d realpos=MathHelper.mulMatVec4d(trans, pos.toVector4d()).smash(); //translated
            Matrix4d newmat=(trans).mul(pos.toTranslateMatrix().mul(angle.toRotateMatrix())); //can't be exchanged!
            /*
            System.out.println(trans);
            System.out.println(pos);
            System.out.println(realpos);
            
            System.out.println(pos.toTranslateMatrix());
            System.out.println(angle.toRotateMatrix());
            System.out.println(pos.toTranslateMatrix().mul(angle.toRotateMatrix()));
            System.out.println(trans);
            System.out.println(newmat);
            */
            Quaternion newpose=pose.mul(angle.toQuaternion()); //rotated for texture.
            rmodel.apply(this, realpos, newpose.toEulerAngle());
            sub_joints.forEach((Joint j)->{
                j.render(rmodel,newmat,newpose,forced);
            });
        }
    }
}
