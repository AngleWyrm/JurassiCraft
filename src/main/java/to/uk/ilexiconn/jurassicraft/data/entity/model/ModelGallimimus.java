// Date: 13.06.2014 13:20:20
// Template version 1.1
// Java generated by Techne
// Keep in mind that you still need to fill in some blanks
// - ZeuX






package to.uk.ilexiconn.jurassicraft.data.entity.model;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelGallimimus extends MowzieModelBase
{
  //fields
	MowzieModelRenderer Left_Upper_Foot;
    MowzieModelRenderer Right_Upper_Foot;
    MowzieModelRenderer Left_Calf_1;
    MowzieModelRenderer Right_Calf_1;
    MowzieModelRenderer Left_Thigh;
    MowzieModelRenderer Right_Thigh;
    MowzieModelRenderer Body_1;
    MowzieModelRenderer Body_2;
    MowzieModelRenderer Head;
    MowzieModelRenderer Upper_Jaw;
    MowzieModelRenderer Lower_Jaw;
    MowzieModelRenderer Tail_1;
    MowzieModelRenderer Tail_2;
    MowzieModelRenderer Tail_3;
    MowzieModelRenderer Tail_4;
    MowzieModelRenderer Tail_5;
    MowzieModelRenderer Upper_Arm_Right;
    MowzieModelRenderer Upper_Arm_Left;
    MowzieModelRenderer Lower_Arm_Left;
    MowzieModelRenderer Lower_Arm_Right;
    MowzieModelRenderer Hand_Left;
    MowzieModelRenderer Hand_Right;
    MowzieModelRenderer Hand_Left_Claw_Left;
    MowzieModelRenderer Hand_Left_Claw_Right;
    MowzieModelRenderer Hand_Left_Claw_Middle;
    MowzieModelRenderer Hand_Right_Claw_Right;
    MowzieModelRenderer Hand_Right_Claw_Left;
    MowzieModelRenderer Hand_Right_Claw_Middle;
    MowzieModelRenderer Foot_Left;
    MowzieModelRenderer Foot_Right;
    MowzieModelRenderer Neck_1;
    MowzieModelRenderer Neck_2;
    MowzieModelRenderer Neck_3;
    MowzieModelRenderer Neck_4;
    MowzieModelRenderer Neck_5;
  
  public ModelGallimimus()
  {
    textureWidth = 256;
    textureHeight = 256;
    
    Left_Calf_1 = new MowzieModelRenderer(this, 65, 0);
    Left_Calf_1.addBox(-1.5F, 0F, 0F, 3, 10, 3);
    Left_Calf_1.setRotationPoint(5F, 12.5F, 2F);
    Left_Calf_1.setTextureSize(256, 256);
    Left_Calf_1.mirror = false;
    setRotation(Left_Calf_1, 0.7238469F, 0F, 0F);
    Right_Calf_1 = new MowzieModelRenderer(this, 65, 0);
    Right_Calf_1.addBox(-1.5F, 0F, 0F, 3, 10, 3);
    Right_Calf_1.setRotationPoint(-5F, 12.5F, 2F);
    Right_Calf_1.setTextureSize(256, 256);
    Right_Calf_1.mirror = false;
    setRotation(Right_Calf_1, 0.7238469F, 0F, 0.0174533F);
    Left_Thigh = new MowzieModelRenderer(this, 27, 57);
    Left_Thigh.addBox(0F, 0F, -10F, 4, 4, 11);
    Left_Thigh.setRotationPoint(3F, 4F, 6F);
    Left_Thigh.setTextureSize(256, 256);
    Left_Thigh.mirror = false;
    setRotation(Left_Thigh, 1.029744F, 0F, 0F);
    Right_Thigh = new MowzieModelRenderer(this, 27, 57);
    Right_Thigh.addBox(-4F, 0F, -10F, 4, 4, 11);
    Right_Thigh.setRotationPoint(-3F, 4F, 6F);
    Right_Thigh.setTextureSize(256, 256);
    Right_Thigh.mirror = false;
    setRotation(Right_Thigh, 1.029744F, 0F, 0F);
    Body_1 = new MowzieModelRenderer(this, 118, 0);
    Body_1.addBox(-3.5F, -2F, -9F, 7, 8, 12);
    Body_1.setRotationPoint(0F, 3.6F, 7F);
    Body_1.setTextureSize(256, 256);
    Body_1.mirror = false;
    setRotation(Body_1, -0.0219973F, 0F, 0F);
    Body_2 = new MowzieModelRenderer(this, 182, 21);
    Body_2.addBox(-3F, 0F, -7F, 6, 7, 7);
    Body_2.setRotationPoint(0F, 2.2F, 2F);
    Body_2.setTextureSize(256, 256);
    Body_2.mirror = false;
    setRotation(Body_2, -0.2595274F, 0F, 0F);
    Head = new MowzieModelRenderer(this, 0, 92);
    Head.addBox(-2F, 0F, -5F, 4, 5, 5);
    Head.setRotationPoint(0F, -10.5F, -12.5F);
    Head.setTextureSize(256, 256);
    Head.mirror = false;
    setRotation(Head, 0F, 0F, 0F);
    Upper_Jaw = new MowzieModelRenderer(this, 12, 26);
    Upper_Jaw.addBox(-1F, -2F, -4F, 2, 2, 4);
    Upper_Jaw.setRotationPoint(0F, -7F, -17.5F);
    Upper_Jaw.setTextureSize(256, 256);
    Upper_Jaw.mirror = false;
    setRotation(Upper_Jaw, 0F, 0F, 0F);
    Lower_Jaw = new MowzieModelRenderer(this, 70, 27);
    Lower_Jaw.addBox(-1F, -1F, -5F, 2, 1, 4);
    Lower_Jaw.setRotationPoint(0F, -6F, -16.5F);
    Lower_Jaw.setTextureSize(256, 256);
    Lower_Jaw.mirror = false;
    setRotation(Lower_Jaw, 0F, 0F, 0F);
    Tail_1 = new MowzieModelRenderer(this, 118, 39);
    Tail_1.addBox(-3F, 0F, 0F, 6, 6, 8);
    Tail_1.setRotationPoint(0F, 2.1F, 7F);
    Tail_1.setTextureSize(256, 256);
    Tail_1.mirror = false;
    setRotation(Tail_1, 0.0181172F, 0F, 0F);
    Tail_2 = new MowzieModelRenderer(this, 118, 61);
    Tail_2.addBox(-2.5F, 0F, -0.2F, 5, 5, 6);
    Tail_2.setRotationPoint(0F, 2.3F, 15F);
    Tail_2.setTextureSize(256, 256);
    Tail_2.mirror = false;
    setRotation(Tail_2, 0.0351279F, 0F, 0F);
    Tail_3 = new MowzieModelRenderer(this, 118, 119);
    Tail_3.addBox(-2F, 0F, 0F, 4, 4, 6);
    Tail_3.setRotationPoint(0F, 2.7F, 20F);
    Tail_3.setTextureSize(256, 256);
    Tail_3.mirror = false;
    setRotation(Tail_3, -0.0546319F, 0F, 0F);
    Tail_4 = new MowzieModelRenderer(this, 118, 80);
    Tail_4.addBox(-1.5F, 0F, 0F, 3, 3, 7);
    Tail_4.setRotationPoint(0F, 3.5F, 25F);
    Tail_4.setTextureSize(256, 256);
    Tail_4.mirror = false;
    setRotation(Tail_4, -0.0698132F, 0F, 0F);
    Tail_5 = new MowzieModelRenderer(this, 118, 100);
    Tail_5.addBox(-1F, 0F, 0F, 2, 2, 9);
    Tail_5.setRotationPoint(0F, 4.5F, 31F);
    Tail_5.setTextureSize(256, 256);
    Tail_5.mirror = false;
    setRotation(Tail_5, 0.0500879F, 0F, 0F);
    Upper_Arm_Right = new MowzieModelRenderer(this, 0, 56);
    Upper_Arm_Right.addBox(-2F, 0F, 0F, 2, 2, 5);
    Upper_Arm_Right.setRotationPoint(-3F, 5F, -4F);
    Upper_Arm_Right.setTextureSize(256, 256);
    Upper_Arm_Right.mirror = false;
    setRotation(Upper_Arm_Right, -1.22173F, 0F, 0F);
    Upper_Arm_Left = new MowzieModelRenderer(this, 0, 56);
    Upper_Arm_Left.addBox(0F, 0F, 0F, 2, 2, 5);
    Upper_Arm_Left.setRotationPoint(3F, 5F, -4F);
    Upper_Arm_Left.setTextureSize(256, 256);
    Upper_Arm_Left.mirror = false;
    setRotation(Upper_Arm_Left, -1.22173F, 0F, 0F);
    Lower_Arm_Left = new MowzieModelRenderer(this, 0, 68);
    Lower_Arm_Left.addBox(-1F, 0F, 0F, 1, 5, 1);
    Lower_Arm_Left.setRotationPoint(4.5F, 9F, -3.5F);
    Lower_Arm_Left.setTextureSize(256, 256);
    Lower_Arm_Left.mirror = false;
    setRotation(Lower_Arm_Left, -0.6320364F, 0F, 0F);
    Lower_Arm_Right = new MowzieModelRenderer(this, 0, 68);
    Lower_Arm_Right.addBox(-1F, 0F, 0F, 1, 5, 1);
    Lower_Arm_Right.setRotationPoint(-3.5F, 9F, -3.5F);
    Lower_Arm_Right.setTextureSize(256, 256);
    Lower_Arm_Right.mirror = false;
    setRotation(Lower_Arm_Right, -0.6320364F, 0F, 0F);
    Hand_Left = new MowzieModelRenderer(this, 81, 54);
    Hand_Left.addBox(0F, -1F, 0F, 2, 1, 2);
    Hand_Left.setRotationPoint(3.5F, 13F, -7F);
    Hand_Left.setTextureSize(256, 256);
    Hand_Left.mirror = false;
    setRotation(Hand_Left, -1.58825F, -0.4537856F, 0F);
    Hand_Right = new MowzieModelRenderer(this, 81, 54);
    Hand_Right.addBox(-2F, 0F, 0F, 2, 1, 2);
    Hand_Right.setRotationPoint(-3F, 13F, -6.2F);
    Hand_Right.setTextureSize(256, 256);
    Hand_Right.mirror = false;
    setRotation(Hand_Right, -1.58825F, 0.4537856F, 0F);
    Foot_Left = new MowzieModelRenderer(this, 0, 0);
    Foot_Left.addBox(-1.5F, 0F, -3F, 3, 2, 5);
    Foot_Left.setRotationPoint(5F, 22F, 5.5F);
    Foot_Left.setTextureSize(256, 256);
    Foot_Left.mirror = false;
    setRotation(Foot_Left, 0F, 0F, 0F);
    Foot_Right = new MowzieModelRenderer(this, 0, 0);
    Foot_Right.addBox(-1.5F, 0F, -3F, 3, 2, 5);
    Foot_Right.setRotationPoint(-5.1F, 22F, 5.5F);
    Foot_Right.setTextureSize(256, 256);
    Foot_Right.mirror = false;
    setRotation(Foot_Right, 0F, 0F, 0F);
    Neck_1 = new MowzieModelRenderer(this, 182, 38);
    Neck_1.addBox(-2F, 0F, -7F, 4, 4, 8);
    Neck_1.setRotationPoint(0F, 2.6F, -3F);
    Neck_1.setTextureSize(256, 256);
    Neck_1.mirror = false;
    setRotation(Neck_1, -0.8573801F, 0F, 0F);
    Neck_2 = new MowzieModelRenderer(this, 182, 52);
    Neck_2.addBox(-1.5F, 0F, -4F, 3, 3, 4);
    Neck_2.setRotationPoint(0F, -1.2F, -7.3F);
    Neck_2.setTextureSize(256, 256);
    Neck_2.mirror = false;
    setRotation(Neck_2, -1.202625F, 0F, 0F);
    Neck_3 = new MowzieModelRenderer(this, 200, 52);
    Neck_3.addBox(-1.5F, 0F, -3F, 3, 3, 3);
    Neck_3.setRotationPoint(0F, -3.8F, -8.5F);
    Neck_3.setTextureSize(256, 256);
    Neck_3.mirror = false;
    setRotation(Neck_3, -1.552414F, 0F, 0F);
    Neck_4 = new MowzieModelRenderer(this, 182, 65);
    Neck_4.addBox(-1.5F, 0F, -2F, 3, 2, 2);
    Neck_4.setRotationPoint(0F, -6.8F, -8.6F);
    Neck_4.setTextureSize(256, 256);
    Neck_4.mirror = false;
    setRotation(Neck_4, -0.9795531F, 0F, 0F);
    Neck_5 = new MowzieModelRenderer(this, 182, 72);
    Neck_5.addBox(-1.5F, 0F, -3F, 3, 3, 3);
    Neck_5.setRotationPoint(0F, -8.5F, -9.7F);
    Neck_5.setTextureSize(256, 256);
    Neck_5.mirror = false;
    setRotation(Neck_5, -0.3846954F, 0F, 0F);
    Left_Upper_Foot = new MowzieModelRenderer(this, 67, 1);
    Left_Upper_Foot.addBox(-1F, 0F, -1F, 2, 7, 2);
    Left_Upper_Foot.setRotationPoint(5F, 18F, 8.6F);
    Left_Upper_Foot.setTextureSize(256, 256);
    Left_Upper_Foot.mirror = false;
    setRotation(Left_Upper_Foot, -0.6123111F, 0F, 0F);
    Right_Upper_Foot = new MowzieModelRenderer(this, 67, 1);
    Right_Upper_Foot.addBox(-1F, 0F, -1F, 2, 7, 2);
    Right_Upper_Foot.setRotationPoint(-5F, 18F, 8.6F);
    Right_Upper_Foot.setTextureSize(256, 256);
    Right_Upper_Foot.mirror = false;
    setRotation(Right_Upper_Foot, -0.6123111F, 0F, 0F);
    Hand_Left_Claw_Left = new MowzieModelRenderer(this, 81, 57);
    Hand_Left_Claw_Left.addBox(0F, 0F, -0.5F, 1, 2, 1);
    Hand_Left_Claw_Left.setRotationPoint(4.5F, 14F, -6.7F);
    Hand_Left_Claw_Left.setTextureSize(256, 256);
    Hand_Left_Claw_Left.mirror = false;
    setRotation(Hand_Left_Claw_Left, 0F, -2.089644F, 0F);
    Hand_Left_Claw_Right = new MowzieModelRenderer(this, 81, 57);
    Hand_Left_Claw_Right.addBox(0F, 0F, 0F, 1, 2, 1);
    Hand_Left_Claw_Right.setRotationPoint(4.5F, 14F, -6.4F);
    Hand_Left_Claw_Right.setTextureSize(256, 256);
    Hand_Left_Claw_Right.mirror = false;
    setRotation(Hand_Left_Claw_Right, 0.5235988F, -2.089644F, 0F);
    Hand_Left_Claw_Middle = new MowzieModelRenderer(this, 81, 57);
    Hand_Left_Claw_Middle.addBox(0F, 0F, -0.7F, 1, 2, 1);
    Hand_Left_Claw_Middle.setRotationPoint(4.5F, 14F, -6.44F);
    Hand_Left_Claw_Middle.setTextureSize(256, 256);
    Hand_Left_Claw_Middle.mirror = false;
    setRotation(Hand_Left_Claw_Middle, -0.5917596F, -2.089644F, 0F);
    Hand_Right_Claw_Right = new MowzieModelRenderer(this, 81, 57);
    Hand_Right_Claw_Right.addBox(-0.9F, 0F, -0.5F, 1, 2, 1);
    Hand_Right_Claw_Right.setRotationPoint(-4.4F, 14F, -6.7F);
    Hand_Right_Claw_Right.setTextureSize(256, 256);
    Hand_Right_Claw_Right.mirror = false;
    setRotation(Hand_Right_Claw_Right, 0F, 2.089648F, 0F);
    Hand_Right_Claw_Left = new MowzieModelRenderer(this, 81, 57);
    Hand_Right_Claw_Left.addBox(-1F, 0F, 0F, 1, 2, 1);
    Hand_Right_Claw_Left.setRotationPoint(-4.3F, 14F, -6.4F);
    Hand_Right_Claw_Left.setTextureSize(256, 256);
    Hand_Right_Claw_Left.mirror = false;
    setRotation(Hand_Right_Claw_Left, 0.5235988F, 2.089648F, 0F);
    Hand_Right_Claw_Middle = new MowzieModelRenderer(this, 81, 57);
    Hand_Right_Claw_Middle.addBox(-1F, -0.1F, -0.6F, 1, 2, 1);
    Hand_Right_Claw_Middle.setRotationPoint(-4.5F, 14F, -6.4F);
    Hand_Right_Claw_Middle.setTextureSize(256, 256);
    Hand_Right_Claw_Middle.mirror = false;
    setRotation(Hand_Right_Claw_Middle, -0.5917596F, 2.089648F, 0F);
      
 //     addChildTo(Foot, Calf);
 //     addChildTo(Calf, Thigh2);
 //     addChildTo(Foot2, Calf2);
 //     addChildTo(Calf2, Thigh);
      
      Left_Upper_Foot.setInitValuesToCurrentPose();
      Right_Upper_Foot.setInitValuesToCurrentPose();
       Left_Calf_1.setInitValuesToCurrentPose();
       Right_Calf_1.setInitValuesToCurrentPose();
       Left_Thigh.setInitValuesToCurrentPose();
       Right_Thigh.setInitValuesToCurrentPose();
       Body_1.setInitValuesToCurrentPose();
       Body_2.setInitValuesToCurrentPose();
       Head.setInitValuesToCurrentPose();
       Upper_Jaw.setInitValuesToCurrentPose();
       Lower_Jaw.setInitValuesToCurrentPose();
       Tail_1.setInitValuesToCurrentPose();
       Tail_2.setInitValuesToCurrentPose();
       Tail_3.setInitValuesToCurrentPose();
       Tail_4.setInitValuesToCurrentPose();
       Tail_5.setInitValuesToCurrentPose();
       Upper_Arm_Right.setInitValuesToCurrentPose();
       Upper_Arm_Left.setInitValuesToCurrentPose();
       Lower_Arm_Left.setInitValuesToCurrentPose();
       Lower_Arm_Right.setInitValuesToCurrentPose();
       Hand_Left.setInitValuesToCurrentPose();
       Hand_Right.setInitValuesToCurrentPose();
       Hand_Left_Claw_Left.setInitValuesToCurrentPose();
       Hand_Left_Claw_Right.setInitValuesToCurrentPose();
       Hand_Left_Claw_Middle.setInitValuesToCurrentPose();
       Hand_Right_Claw_Right.setInitValuesToCurrentPose();
       Hand_Right_Claw_Left.setInitValuesToCurrentPose();
       Hand_Right_Claw_Middle.setInitValuesToCurrentPose();
       Foot_Left.setInitValuesToCurrentPose();
       Foot_Right.setInitValuesToCurrentPose();
       Neck_1.setInitValuesToCurrentPose();
       Neck_2.setInitValuesToCurrentPose();
       Neck_3.setInitValuesToCurrentPose();
       Neck_4.setInitValuesToCurrentPose();
       Neck_5.setInitValuesToCurrentPose();
       
      // System.out.println("Model was constructed");
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5);
    Left_Upper_Foot.render(f5);
    Right_Upper_Foot.render(f5);
    Left_Calf_1.render(f5);
    Right_Calf_1.render(f5);
    Left_Thigh.render(f5);
    Right_Thigh.render(f5);
    Body_1.render(f5);
    Body_2.render(f5);
    Head.render(f5);
    Upper_Jaw.render(f5);
    Lower_Jaw.render(f5);
    Tail_1.render(f5);
    Tail_2.render(f5);
    Tail_3.render(f5);
    Tail_4.render(f5);
    Tail_5.render(f5);
    Upper_Arm_Right.render(f5);
    Upper_Arm_Left.render(f5);
    Lower_Arm_Left.render(f5);
    Lower_Arm_Right.render(f5);
    Hand_Left.render(f5);
    Hand_Right.render(f5);
    Hand_Left_Claw_Left.render(f5);
    Hand_Left_Claw_Right.render(f5);
    Hand_Left_Claw_Middle.render(f5);
    Hand_Right_Claw_Right.render(f5);
    Hand_Right_Claw_Left.render(f5);
    Hand_Right_Claw_Middle.render(f5);
    Foot_Left.render(f5);
    Foot_Right.render(f5);
    Neck_1.render(f5);
    Neck_2.render(f5);
    Neck_3.render(f5);
    Neck_4.render(f5);
    Neck_5.render(f5);
   // System.out.println("rendered");
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, null);
//    walk(Thigh, 0.7F, 0.4F, false, 0F, f, f1);
//    walk(Thigh2, 0.7F, 0.4F, true, 0F, f, f1);
  }
}
