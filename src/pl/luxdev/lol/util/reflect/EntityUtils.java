package pl.luxdev.lol.util.reflect;

import java.lang.reflect.Field;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_10_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_10_R1.entity.CraftCreature;
import org.bukkit.craftbukkit.v1_10_R1.entity.CraftEnderDragon;
import org.bukkit.craftbukkit.v1_10_R1.entity.CraftEntity;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import net.minecraft.server.v1_10_R1.EntityCreature;
import net.minecraft.server.v1_10_R1.EntityEnderDragon;
import net.minecraft.server.v1_10_R1.EntityHuman;
import net.minecraft.server.v1_10_R1.EntityInsentient;
import net.minecraft.server.v1_10_R1.NavigationAbstract;
import net.minecraft.server.v1_10_R1.PathfinderGoal;
import net.minecraft.server.v1_10_R1.PathfinderGoalLookAtPlayer;
import net.minecraft.server.v1_10_R1.PathfinderGoalMoveTowardsRestriction;
import net.minecraft.server.v1_10_R1.PathfinderGoalRandomLookaround;
import net.minecraft.server.v1_10_R1.PathfinderGoalSelector;
import pl.luxdev.lol.util.MathUtils;
import pl.luxdev.lol.util.VectorUtils;

public class EntityUtils {
	
	/*
	 * EntityUtils
	 * @author EldziPl, dzieki :)
	 */
	
	private static Field _goalSelector;
    private static Field _targetSelector;
    private static Field _bsRestrictionGoal;
    private static Field _pathfinderBList;
    private static Field _pathfinderCList;

    public void addAI(Entity entity, int value, Object ai) {
        if (((CraftEntity) entity).getHandle() instanceof EntityInsentient) {
            EntityInsentient ei = (EntityInsentient) ((CraftEntity) entity).getHandle();

            if (_goalSelector == null) {
                try {
                    _goalSelector = EntityInsentient.class.getDeclaredField("goalSelector");
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                    return;
                }
                _goalSelector.setAccessible(true);
            }

            PathfinderGoal pf = (PathfinderGoal) ai;
            try {
                ((PathfinderGoalSelector) _goalSelector.get(ei)).a(value, pf);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

    }

    public void addLookAtPlayerAI(Entity entity, float dist) {
        if (((CraftEntity) entity).getHandle() instanceof EntityInsentient) {
            addAI(entity, 7, new PathfinderGoalLookAtPlayer((EntityInsentient) ((CraftEntity) entity).getHandle(),
                    EntityHuman.class, dist));
            addAI(entity, 8, new PathfinderGoalRandomLookaround((EntityInsentient) ((CraftEntity) entity).getHandle()));

        }

    }

    public void creatureMove(Entity ent, Location target, float speed) {
        if (!(ent instanceof Creature))
            return;

        if (MathUtils.offset(ent.getLocation(), target) < 0.1)
            return;

        EntityCreature ec = ((CraftCreature) ent).getHandle();
        NavigationAbstract nav = ec.getNavigation();

        if (MathUtils.offset(ent.getLocation(), target) > 16) {
            Location newTarget = ent.getLocation();

            newTarget.add(VectorUtils.getTrajectory(ent.getLocation(), target).multiply(16));

            nav.a(newTarget.getX(), newTarget.getY(), newTarget.getZ(), speed);
        } else
            nav.a(target.getX(), target.getY(), target.getZ(), speed);

    }

    public boolean creatureMoveFast(Entity ent, Location target, float speed, boolean slow) {
        if (!(ent instanceof Creature))
            return false;

        if (MathUtils.offset(ent.getLocation(), target) < 0.1)
            return false;

        if (MathUtils.offset(ent.getLocation(), target) < 2)
            speed = Math.min(speed, 1f);

        EntityCreature ec = ((CraftCreature) ent).getHandle();
        ec.getControllerMove().a(target.getX(), target.getY(), target.getZ(), speed);

        return true;
    }

    public int getNewEntityId(boolean modifynumber) {
        try {
            Field field = Entity.class.getDeclaredField("entityCount");
            field.setAccessible(true);
            int entityId = field.getInt(null);
            if (modifynumber)
                field.set(null, entityId + 1);
            return entityId;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return -1;
    }

    public void ghost(Entity entity, boolean ghost, boolean invisible) {
        ((CraftEntity) entity).getHandle().setInvisible(invisible);

    }

    public void moveDragon(Player player, Vector vector, org.bukkit.entity.Entity entity) {
        EntityEnderDragon ec = ((CraftEnderDragon) entity).getHandle();

        ec.hurtTicks = -1;

        ec.getBukkitEntity().setVelocity(vector);

        ec.pitch = player.getLocation().getPitch();
        ec.yaw = player.getLocation().getYaw() - 180;

        Vector v = ec.getBukkitEntity().getLocation().getDirection();
        Vector v1 = ec.getBukkitEntity().getLocation().getDirection().multiply(-1);
        ec.move(v1.getX(), v.getY(), v1.getZ());
    }

    public void removeGoalSelectors(Entity entity) {
        try {
            if (_goalSelector == null) {
                _goalSelector = EntityInsentient.class.getDeclaredField("goalSelector");
                _goalSelector.setAccessible(true);
            }

            if (((CraftEntity) entity).getHandle() instanceof EntityInsentient) {
                EntityInsentient creature = (EntityInsentient) ((CraftEntity) entity).getHandle();

                PathfinderGoalSelector goalSelector = new PathfinderGoalSelector(
                        ((CraftWorld) entity.getWorld()).getHandle().methodProfiler);

                _goalSelector.set(creature, goalSelector);
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        }

    }

    public void silence(Entity entity, boolean silence) {
        net.minecraft.server.v1_10_R1.Entity e = ((CraftEntity) entity).getHandle();
        e.setSilent(true);

    }

    public void vegetate(Entity entity, boolean mute) {
        try {
            if (_goalSelector == null) {
                _goalSelector = EntityInsentient.class.getDeclaredField("goalSelector");
                _goalSelector.setAccessible(true);
            }

            if (_targetSelector == null) {
                _targetSelector = EntityInsentient.class.getDeclaredField("targetSelector");
                _targetSelector.setAccessible(true);
            }

            if (_pathfinderBList == null) {
                _pathfinderBList = PathfinderGoalSelector.class.getDeclaredField("b");
                _pathfinderBList.setAccessible(true);
            }

            if (_pathfinderCList == null) {
                _pathfinderCList = PathfinderGoalSelector.class.getDeclaredField("c");
                _pathfinderCList.setAccessible(true);
            }

            if (entity instanceof CraftCreature) {
                EntityCreature creature = ((CraftCreature) entity).getHandle();

                if (_bsRestrictionGoal == null) {
                    _bsRestrictionGoal = EntityCreature.class.getDeclaredField("bs");
                    _bsRestrictionGoal.setAccessible(true);
                }

                _bsRestrictionGoal.set(creature, new PathfinderGoalMoveTowardsRestriction(creature, 0D));
            }

            if (((CraftEntity) entity).getHandle() instanceof EntityInsentient) {
                EntityInsentient creature = (EntityInsentient) ((CraftEntity) entity).getHandle();

                ((List<?>) _pathfinderBList.get(_goalSelector.get(creature))).clear();
                ((List<?>) _pathfinderCList.get(_goalSelector.get(creature))).clear();

                ((List<?>) _pathfinderBList.get(_targetSelector.get(creature))).clear();
                ((List<?>) _pathfinderCList.get(_targetSelector.get(creature))).clear();
            }

        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

}
