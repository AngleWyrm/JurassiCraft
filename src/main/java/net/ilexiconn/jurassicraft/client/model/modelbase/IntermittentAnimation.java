package net.ilexiconn.jurassicraft.client.model.modelbase;

import java.util.Random;

import net.minecraft.util.MathHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * This is a timer that can be used to easily animate models with intermittent poses. You have to
 * set the number of ticks between poses, a number of ticks that represents the interval of the pose
 * change, increase or decrease the timer, and get the percentage using a specific function.
 * 
 * @author RafaMv
 */
@SideOnly(Side.CLIENT)
public class IntermittentAnimation
{
    /**
     * It is the timer used to animate.
     */
    private double timer;
    
    /**
     * It is the limit time, the maximum value that the timer can be. I
     * represents the duration of the animation.
     */
    private double duration;

    /**
     * It is a boolean that shows if the animation is already in the new pose.
     */
    private boolean runInterval;
    /**
     * It is an inverter for the timer.
     */
    private int inverter;
    
    /**
     * It is the timer used for the interval.
     */
    private double timerInterval;

	/**
	 * It is the interval to return to the first animation.
	 */
    private double intervalDuration;
    
    /**
     * It is the chance to change the animation.
     */
    private int chance;
    
    /**
     * It is the random used to randomize the movement.
     */
    Random rand = new Random();

    public IntermittentAnimation(int duration, int intervalDuration, int chance)
    {
        this.timer = 0;
        this.duration = (double) duration;
        this.intervalDuration = (double) intervalDuration;
        this.runInterval = true;
        this.chance = chance;
        this.inverter = -1;
    }

    /**
     * Sets the duration of the animation in ticks. Try values around 50.
     *
     * @param duration is the maximum number of ticks that the timer can reach.
     */
    @SideOnly(Side.CLIENT)
    public void setDuration(int duration)
    {
        this.timer = 0;
        this.duration = (double) duration;
    }

	/**
	 * Returns the timer of this animation. Useful to save the progress of the animation.
	 */
    public void getTimer()
    {
        this.timer = 0;
    }

    /**
     * Sets the timer to a specific value.
     *
     * @param time is the number of ticks to be set.
     */
    @SideOnly(Side.CLIENT)
	public void setTimer(int time)
    {
		this.timer = (double) time;

		if (this.timer > this.duration)
		{
			this.timer = this.duration;
		}
		else if (this.timer < 0)
		{
			this.timer = 0;
		}
	}

    /**
     * Sets the timer to 0.
     */
    @SideOnly(Side.CLIENT)
    public void resetTimer()
    {
        this.timer = 0;
    }

    /**
     * Increases the timer by 1.
     */
    @SideOnly(Side.CLIENT)
    public void runAnimation()
    {
        System.out.println("this.timer " + this.timer + " this.duration " + this.duration + " this.inverter " + this.inverter + " this.timerInterval " + this.timerInterval);
        
    	if (!this.runInterval)
    	{
            if (this.timer < this.duration && this.timer > 0.0D)
            {
        		this.timer += this.inverter;
            }
        	else
        	{
                if (this.timer >= this.duration)
                {
            		this.timer = this.duration;
                }
                else if (this.timer <= 0.0D)
                {
            		this.timer = 0.0D;
                }
        		this.timerInterval = 0.0D;
        		this.runInterval = true;
        	}
    	}
    	else
    	{
    		if (this.timerInterval < this.intervalDuration)
    		{
        		this.timerInterval++;
    		}
    		else
    		{
    			if (this.rand.nextInt(this.chance) == 0)
    			{
            		if (this.inverter > 0)
            		{
                		this.inverter = -1;
            		}
            		else
            		{
                		this.inverter = 1;
            		}
            		this.timer += this.inverter;
            		this.runInterval = false;
    			}
    		}
    	}
    }

    /**
     * Decreases the timer by 1.
     */
    @SideOnly(Side.CLIENT)
    public void stopAnimation()
    {
		if (this.timer > 0.0D)
		{
			this.timer--;
		}
		else
		{
			this.timer = 0.0D;
			this.runInterval = true;
			this.timerInterval = 0.0D;
			this.inverter = 1;
		}
    }

    /**
     * Decreases the timer by a specific value.
     *
     * @param time is the number of ticks to be decreased in the timer
     */
    @SideOnly(Side.CLIENT)
    public void stopAnimation(int time)
    {
		if (this.timer - time > 0.0D)
		{
			this.timer -= time;
		}
		else
		{
			this.timer = 0.0D;
			this.runInterval = false;
			this.timerInterval = 0.0D;
			this.inverter = 1;
		}
    }

	/**
	 * Returns a float that represents a fraction of the animation, a value between 0.0F and 1.0F.
	 */
	@SideOnly(Side.CLIENT)
	public float getAnimationFraction()
    {
        return (float) (this.timer / this.duration);
    }

    /**
     * Returns a value between 0.0F and 1.0F depending on the timer and duration
     * of the animation. It reaches 1.0F using 1/(1 + e^(4-8*x)). It
     * is quite uniform but slow, and needs if statements.
     */
    @SideOnly(Side.CLIENT)
    public float getAnimationProgressSmooth()
    {
        if (this.timer > 0.0D)
        {
            if (this.timer < this.duration)
            {
                return (float) (1.0D / (1.0D + Math.exp(4.0D - 8.0D * (this.timer / this.duration))));
            }
            else
            {
                return 1.0F;
            }
        }
        return 0.0F;
    }

    /**
     * Returns a value between 0.0F and 1.0F depending on the timer and duration
     * of the animation. It reaches 1.0F using 1/(1 + e^(6-12*x)). It
     * is quite uniform, but fast.
     */
    @SideOnly(Side.CLIENT)
    public float getAnimationProgressSteep()
    {
        return (float) (1.0D / (1.0D + Math.exp(6.0D - 12.0D * (this.timer / this.duration))));
    }

    /**
     * Returns a value between 0.0F and 1.0F depending on the timer and duration
     * of the animation. It reaches 1.0F using a sine function. It is
     * fast in the beginning and slow in the end.
     */
    @SideOnly(Side.CLIENT)
    public float getAnimationProgressSin()
    {
        return MathHelper.sin(1.57079632679F * (float) (this.timer / this.duration));
    }

    /**
     * Returns a value between 0.0F and 1.0F depending on the timer and duration
     * of the animation. It reaches 1.0F using a sine function
     * squared. It is very smooth.
     */
    @SideOnly(Side.CLIENT)
    public float getAnimationProgressSinSqrt()
    {
        float result = MathHelper.sin(1.57079632679F * (float) (this.timer / this.duration));
        return result * result;
    }

    /**
     * Returns a value between 0.0F and 1.0F depending on the timer and duration
     * of the animation. It reaches 1.0F using a sine function to the
     * power of ten. It is slow in the beginning and fast in the end.
     */
    @SideOnly(Side.CLIENT)
    public float getAnimationProgressSinToTen()
    {
        return (float) Math.pow((double) MathHelper.sin(1.57079632679F * (float) (this.timer / this.duration)), 10);
    }

    /**
     * Returns a value between 0.0F and 1.0F depending on the timer and duration
     * of the animation. It reaches 1.0F using a sine function to a
     * specific power "i."
     *
     * @param i is the power of the sine function.
     */
    @SideOnly(Side.CLIENT)
    public float getAnimationProgressSinPowerOf(int i)
    {
        return (float) Math.pow((double) MathHelper.sin(1.57079632679F * (float) (this.timer / this.duration)), i);
    }

    /**
     * Returns a value between 0.0F and 1.0F depending on the timer and duration
     * of the animation. It reaches 1.0F using x^2 / (x^2 + (1-x)^2).
     * It is smooth.
     */
    @SideOnly(Side.CLIENT)
    public float getAnimationProgressPoly2()
    {
        float x = (float) (this.timer / this.duration);
        float x2 = x * x;
        return x2 / (x2 + (1 - x) * (1 - x));
    }

    /**
     * Returns a value between 0.0F and 1.0F depending on the timer and duration
     * of the animation. It reaches 1.0F using x^3 / (x^3 + (1-x)^3).
     * It is steep.
     */
    @SideOnly(Side.CLIENT)
    public float getAnimationProgressPoly3()
    {
        float x = (float) (this.timer / this.duration);
        float x3 = x * x * x;
        return x3 / (x3 + (1 - x) * (1 - x) * (1 - x));
    }

    /**
     * Returns a value between 0.0F and 1.0F depending on the timer and duration
     * of the animation. It reaches 1.0F using x^n / (x^n + (1-x)^n).
     * It is steeper when n increases.
     *
     * @param n is the power of the polynomial function.
     */
    @SideOnly(Side.CLIENT)
    public float getAnimationProgressPolyN(int n)
    {
        double x = this.timer / this.duration;
        double xi = Math.pow(x, (double) n);
        return (float) (xi / (xi + Math.pow((1.0D - x), (double) n)));
    }

    /**
     * Returns a value between 0.0F and 1.0F depending on the timer and duration
     * of the animation. It reaches 1.0F using 0.5 + arctan(PI * (x -
     * 0.5)) / 2.00776964. It is super smooth.
     */
    @SideOnly(Side.CLIENT)
    public float getAnimationProgressArcTan()
    {
        return (float) (0.5F + 0.49806510671F * Math.atan(3.14159265359D * ((double) (this.timer / this.duration) - 0.5D)));
    }

    /**
     * Returns a value between 0.0F and 1.0F depending on the timer and duration of the animation. 
     * This value starts at 1.0F and ends at 1.0F.
     * The equation used is 0.5 - 0.5 * cos(2 * PI * x + sin(2 * PI * x)). It is smooth.
     */
    @SideOnly(Side.CLIENT)
    public float getAnimationProgressTemporary()
    {
        float x = 6.28318530718F * (float) (this.timer / this.duration);
        return 0.5F - 0.5F * MathHelper.cos(x + MathHelper.sin(x));
    }

    /**
     * Returns a value between 0.0F and 1.0F depending on the timer and duration
     * of the animation. This value starts at 0.0F and ends at 0.0F.
     * The equation used is sin(x * PI + sin(x * PI)). It is fast in the beginning and slow in the end.
     */
    @SideOnly(Side.CLIENT)
    public float getAnimationProgressTemporaryFS()
    {
        float x = 3.14159265359F * (float) (this.timer / this.duration);
        return MathHelper.sin(x + MathHelper.sin(x));
    }

    /**
     * Returns a value between 0.0F and 1.0F depending on the timer and duration of the animation. 
     * This value starts at 1.0F and ends at 1.0F.
     * The equation used is 0.5 + 0.5 * cos(2 PI * x + sin(2 * PI * x)). It is smooth.
     */
    @SideOnly(Side.CLIENT)
    public float getAnimationProgressTemporaryInvesed()
    {
        float x = 6.28318530718F * (float) (this.timer / this.duration);
        return 0.5F + 0.5F * MathHelper.cos(x + MathHelper.sin(x));
    }
}
