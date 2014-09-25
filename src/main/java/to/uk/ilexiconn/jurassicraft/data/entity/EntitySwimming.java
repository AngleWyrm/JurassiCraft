package to.uk.ilexiconn.jurassicraft.data.entity;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import to.uk.ilexiconn.jurassicraft.Util;

/* Inherithed from Animals+ by Click_Me */

public abstract class EntitySwimming extends EntityLiving implements IAnimals {

    public byte creatureID;

    private double swimTargetX;
    private double swimTargetY;
    private double swimTargetZ;

    private Entity targetEntity;
    private boolean isAttacking;

    protected float swimRadius = 4.0F;
    protected float swimRadiusHeight = 4.0F;

    protected boolean isAgressive = false;
    protected int attackInterval = 50;
    protected float attackSpeed = 1.2F;

    protected float swimSpeed = 0.5F;
    protected boolean jumpOnLand = true;

    public EntitySwimming(World world, byte id) {
        super(world);
        if (id >= 0 && id < Util.getDinos().size()) {
            this.creatureID = id;
        } else {
            this.creatureID = 0;
        }
    }

    @Override
    protected boolean canTriggerWalking() {
        return false;
    }

    @Override
    protected boolean isAIEnabled() {
        return true;
    }

    @Override
    public boolean isInWater() {
        return this.worldObj.handleMaterialAcceleration(this.boundingBox, Material.water, this);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();

        if (this.isInWater())
            this.motionY *= 0.1D;
    }

    @Override
    protected void updateAITasks() {
        super.updateAITasks();

        if (this.isInWater()) {
            double dx = this.swimTargetX - this.posX;
            double dy = this.swimTargetY - this.posY;
            double dz = this.swimTargetZ - this.posZ;
            double dist = MathHelper.sqrt_double(dx * dx + dy * dy + dz * dz);

            if (dist < 1.0D || dist > 1000.0D) {
                this.swimTargetX = this.posX + (double) ((this.rand.nextFloat() * 2.0F - 1.0F) * this.swimRadius);
                this.swimTargetY = this.posY + (double) ((this.rand.nextFloat() * 2.0F - 1.0F) * this.swimRadiusHeight);
                this.swimTargetZ = this.posZ + (double) ((this.rand.nextFloat() * 2.0F - 1.0F) * this.swimRadius);
                this.isAttacking = false;
            }

            if (this.worldObj.getBlock(MathHelper.floor_double(this.swimTargetX),
                    MathHelper.floor_double(this.swimTargetY + this.height), MathHelper.floor_double(this.swimTargetZ))
                    .getMaterial() == Material.water) {
                this.motionX += dx / dist * 0.05D * (double) this.swimSpeed;
                this.motionY += dy / dist * 0.1D * (double) this.swimSpeed;
                this.motionZ += dz / dist * 0.05D * (double) this.swimSpeed;
            } else {
                this.swimTargetX = this.posX;
                this.swimTargetY = this.posY + 0.1D;
                this.swimTargetZ = this.posZ;
            }

            if (this.isAttacking) {
                this.motionX *= this.attackSpeed;
                this.motionY *= this.attackSpeed;
                this.motionZ *= this.attackSpeed;
            }
            if (this.isAgressive && this.rand.nextInt(this.attackInterval) == 0) {
                this.targetEntity = this.findEntityToAttack();
                if (this.targetEntity != null && this.targetEntity.isInWater()) {
                    this.swimTargetX = this.targetEntity.posX;
                    this.swimTargetY = this.targetEntity.posY;
                    this.swimTargetZ = this.targetEntity.posZ;
                    this.isAttacking = true;
                }
            }

            this.renderYawOffset += (-((float) Math.atan2(this.motionX, this.motionZ)) * 180.0F / (float) Math.PI - this.renderYawOffset) * 0.5F;
            this.rotationYaw = this.renderYawOffset;
            float f = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
            this.rotationPitch += ((float) Math.atan2(this.motionY, (double) f) * 180.0F / (float) Math.PI - this.rotationPitch) * 0.5F;
        } else {
            if (this.jumpOnLand && this.onGround && this.rand.nextInt(30) == 0) {
                this.motionY = 0.3F;
                this.motionX = -0.2F + this.rand.nextFloat() * 0.4F;
                this.motionZ = -0.2F + this.rand.nextFloat() * 0.4F;
            }
        }
    }

    protected Entity findEntityToAttack() {
        EntityPlayer player = this.worldObj.getClosestVulnerablePlayerToEntity(this, 16.0D);
        return player != null && this.canEntityBeSeen(player) ? player : null;
    }

    @Override
    public void applyEntityCollision(Entity entity) {
        super.applyEntityCollision(entity);

        if (this.isAgressive && this.targetEntity == entity) {
            this.attackEntityAsMob(entity);
        }
    }

    @Override
    public boolean attackEntityAsMob(Entity entity) {
        float f = (float) this.getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue();
        return entity.attackEntityFrom(DamageSource.causeMobDamage(this), f);
    }

    @Override
    public boolean canBreatheUnderwater() {
        return true;
    }

    @Override
    public void onEntityUpdate() {
        int air = this.getAir();
        super.onEntityUpdate();

        if (this.isEntityAlive() && !this.isInWater()) {
            --air;
            this.setAir(air);

            if (this.getAir() == -20) {
                this.setAir(0);
                this.attackEntityFrom(DamageSource.drown, 2.0F);
            }
        } else
            this.setAir(300);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.attackDamage);
    }

    @Override
    public boolean getCanSpawnHere() {
        return this.worldObj.checkNoEntityCollision(this.boundingBox);
    }

}
