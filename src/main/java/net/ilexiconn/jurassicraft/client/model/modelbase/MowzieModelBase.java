package net.ilexiconn.jurassicraft.client.model.modelbase;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.util.MathHelper;

@SideOnly(Side.CLIENT)
public class MowzieModelBase extends ModelBase
{
    protected void addChildTo(ModelRenderer child, ModelRenderer parent)
    {
        float distance = (float) Math.sqrt(Math.pow((child.rotationPointZ - parent.rotationPointZ), 2) + Math.pow((child.rotationPointY - parent.rotationPointY), 2));
        float oldRotateAngleX = parent.rotateAngleX;
        float parentToChildAngle = (float) Math.atan((child.rotationPointZ - parent.rotationPointZ) / (child.rotationPointY - parent.rotationPointY));
        float childRelativeRotation = parentToChildAngle - parent.rotateAngleX;
        float newRotationPointY = (float) (distance * (Math.cos(childRelativeRotation)));
        float newRotationPointZ = (float) (distance * (Math.sin(childRelativeRotation)));
        parent.rotateAngleX = 0F;
        child.setRotationPoint(child.rotationPointX - parent.rotationPointX, newRotationPointY, newRotationPointZ);
        parent.addChild(child);
        parent.rotateAngleX = oldRotateAngleX;
        child.rotateAngleX -= parent.rotateAngleX;
        child.rotateAngleY -= parent.rotateAngleY;
        child.rotateAngleZ -= parent.rotateAngleZ;
    }

    public void faceTarget(MowzieModelRenderer box, int divider, float f3, float f4)
    {
        box.rotateAngleY += (f3 / (180f / (float) Math.PI)) / divider;
        box.rotateAngleX += (f4 / (180f / (float) Math.PI)) / divider;
    }

    public void walk(MowzieModelRenderer box, float speed, float degree, boolean invert, float offset, float weight, float f, float f1)
    {
        int inverted = 1;
        if (invert) inverted = -1;
        box.rotateAngleX += MathHelper.cos(f * speed + offset) * degree * inverted * f1 + weight * f1;
    }

    public void flap(MowzieModelRenderer box, float speed, float degree, boolean invert, float offset, float f, float f1)
    {
        int inverted = 1;
        if (invert) inverted = -1;
        box.rotateAngleZ += MathHelper.cos(f * speed + offset) * degree * inverted * f1;
    }

    public void bob(MowzieModelRenderer box, float speed, float degree, boolean bounce, float f, float f1)
    {
        float bob = (float) (Math.sin(f * speed) * f1 * degree - f1 * degree);
        if (bounce) bob = (float) -Math.abs((Math.sin(f * speed) * f1 * degree));
        box.rotationPointY += bob;
    }

    public void tailSwing(MowzieModelRenderer[] boxes, float speed, float degree, double rootOffset, float frame)
    {
        int numberOfSegments = boxes.length;
        float offset = (float) ((rootOffset * Math.PI) / (2 * numberOfSegments));
        for (int i = 0; i < numberOfSegments; i++)
            boxes[i].rotateAngleY += MathHelper.cos(frame * speed + offset * i) * degree;
    }

    public void chainWave(MowzieModelRenderer[] boxes, float speed, float degree, double rootOffset, float f, float f1)
    {
        int numberOfSegments = boxes.length;
        float offset = (float) ((rootOffset * Math.PI) / (2 * numberOfSegments));
        for (int i = 0; i < numberOfSegments; i++)
            boxes[i].rotateAngleX += MathHelper.cos(f * speed + offset * i) * f1 * degree;
    }
}