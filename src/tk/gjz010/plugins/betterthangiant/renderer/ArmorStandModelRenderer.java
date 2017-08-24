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

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import tk.gjz010.plugins.betterthangiant.bone.Joint;
import tk.gjz010.plugins.betterthangiant.bone.Model;
import tk.gjz010.plugins.betterthangiant.math.EulerAngle;
import tk.gjz010.plugins.betterthangiant.math.Matrix4d;
import tk.gjz010.plugins.betterthangiant.math.Vector3d;

/**
 * Represents a model renderer using ArmorStand's head to show the bones.
 * This is a simple implementation of ModelRenderer.
 * 
 * @author Guo Jingzhe
 */
public class ArmorStandModelRenderer implements ModelRenderer{
    protected Map<String,ArmorStand> real_map;
    protected World w;
    protected Location loc; //The core position
    private UUID uuid;
    public static final double ARMOR_STAND_HEIGHT=1.5;
    public ArmorStandModelRenderer(Location loc){
        this(UUID.randomUUID(),loc);
    }
    public void destroy(){
        for(ArmorStand as:real_map.values()){
            as.remove();
        }
    }
    public ArmorStandModelRenderer(UUID uuid,Location loc){
        real_map=new HashMap<>();
        this.w=loc.getWorld();
        this.loc=loc;
        this.uuid=uuid;
        loc.setDirection(new Vector(0,0,1));
    }
    private ArmorStand get(Joint j,Vector3d ttrans,EulerAngle tpose){
        String name=j.getName();
        if(!real_map.containsKey(name)){
            Location loc=this.loc.clone();
            loc.setX(ttrans.x);
            loc.setY(ttrans.y);
            loc.setZ(ttrans.z);
            ArmorStand s=(ArmorStand)w.spawnEntity(loc, EntityType.ARMOR_STAND);
            s.setGravity(false);
            s.setHelmet(new ItemStack(Material.DIAMOND_BLOCK));
            s.setVisible(false);
            s.setInvulnerable(true);
            s.setCustomName("<btgbone>"+uuid.toString()+"|"+name);
            s.setCustomNameVisible(false);
            s.setHeadPose(new org.bukkit.util.EulerAngle(Math.PI/2-tpose.pitch, -tpose.yaw, -tpose.roll));
            real_map.put(name, s);
            return s;
        }
        return real_map.get(name);
    }
    @Override
    public void apply(Joint j,Vector3d ttrans,EulerAngle tpose){
        //System.out.println(String.format("Rendering joint %s (pitch %f yaw %f roll %f) at %f %f %f !",j.getName(),tpose.pitch,tpose.roll,tpose.yaw,ttrans.x,ttrans.y,ttrans.z));
        ArmorStand s=get(j,ttrans,tpose);
        Location loc=s.getLocation();
        loc.setX(ttrans.x);
        loc.setY(ttrans.y);
        loc.setZ(ttrans.z);
        s.teleport(loc);
        
        s.setHeadPose(new org.bukkit.util.EulerAngle(Math.PI/2-tpose.pitch, -tpose.yaw, -tpose.roll));
    }
    @Override
    public Matrix4d getInitialTranslation(){
        return new Vector3d(loc.getX(),loc.getY(),loc.getZ()).toTranslateMatrix();
    }
    
    public static class Handler implements Listener{
        /**
         * Ban Armorstand actions.
         * @param ev
         */
        @EventHandler
        public void banManu(PlayerArmorStandManipulateEvent ev){
            if(ev.getRightClicked().getName()!=null){
                if(ev.getRightClicked().getName().startsWith("<btgbone>")){
                    ev.setCancelled(true);
                }
            }
        }
    }
}
