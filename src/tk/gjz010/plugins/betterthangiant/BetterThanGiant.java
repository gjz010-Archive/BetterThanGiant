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
package tk.gjz010.plugins.betterthangiant;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;
import org.bukkit.plugin.java.JavaPlugin;
import tk.gjz010.plugins.betterthangiant.bone.*;
import tk.gjz010.plugins.betterthangiant.math.EulerAngle;
import tk.gjz010.plugins.betterthangiant.math.Matrix4d;
import tk.gjz010.plugins.betterthangiant.math.Vector3d;
import tk.gjz010.plugins.betterthangiant.renderer.ArmorStandModelRenderer;
import tk.gjz010.plugins.betterthangiant.renderer.ModelRenderer;

/**
 * The plugin base class.
 * @author Guo Jingzhe
 */
public class BetterThanGiant extends JavaPlugin{
    @Override
    public void onEnable(){
        Bukkit.getPluginManager().registerEvents(new ArmorStandModelRenderer.Handler(), this);
        getServer().getLogger().info("BetterThanGiant Enabled!");
    }
    /*
    @EventHandler
    public void chatTest(AsyncPlayerChatEvent ev){
        if(ev.getPlayer().isOp() && ev.getMessage().equals("!!summonwind"))
        Bukkit.getScheduler().runTask(this, ()->{
            ev.getPlayer().sendMessage("test model created!");
            Location loc=ev.getPlayer().getLocation();
            ArmorStandModelRenderer asmr=new ArmorStandModelRenderer(loc);
            Model m=new Model("arm");
            Joint m1=new Joint("arm1",m);
            Joint m2=new Joint("arm2",m1);
            Joint m3=new Joint("arm3",m2);
            Joint m4=new Joint("arm4",m1);
            Joint m5=new Joint("arm5",m4);
            Joint m6=new Joint("arm6",m1);
            Joint m7=new Joint("arm7",m6);
            Joint m8=new Joint("arm8",m1);
            Joint m9=new Joint("arm9",m8);
            m1.pos=new Vector3d(0,0,1);
            m2.pos=new Vector3d(0,1,0);
            m3.pos=new Vector3d(0,1,0);
            m4.pos=new Vector3d(0,-1,0);
            m5.pos=new Vector3d(0,-1,0);
            m6.pos=new Vector3d(1,0,0);
            m7.pos=new Vector3d(1,0,0);
            m8.pos=new Vector3d(-1,0,0);
            m9.pos=new Vector3d(-1,0,0);
            m.render(asmr);
            Bukkit.getScheduler().scheduleSyncRepeatingTask(this, ()->{
                m.angle.roll+=0.2;
                m.taint();
                m.render(asmr);
            }, 0, 2);
        });
    }
    */
    @Override
    public void onDisable(){
        getServer().getLogger().info("BetterThanGiant Disabled!");
    }
    

    
    /**
     * Test class.
     * @param args
     */
    public static void main(String args[]){
        Model m=new Model("arm");
        Joint m1=new Joint("arm1",m);
        m1.pos=new Vector3d(2,0,0);
        m.angle=new EulerAngle(0,1,0);
        m1.angle=new EulerAngle(0,0,0);
        long l1=System.currentTimeMillis();
            m.render(new ModelRenderer() {
            public Matrix4d getInitialTranslation(){
                Matrix4d mat=Matrix4d.IDENTITY();
                mat.data[0][3]=2;
                return mat;
            }
            @Override
            public void apply(Joint j, Vector3d ttrans, EulerAngle tpose) {
                System.out.println(String.format("Rendering joint %s (pitch %f yaw %f roll %f) at %f %f %f !",j.getName(),tpose.pitch,tpose.roll,tpose.yaw,ttrans.x,ttrans.y,ttrans.z));
            }
        },true);
        long l2=System.currentTimeMillis();
        System.out.println(l2-l1);

        
    }
}
