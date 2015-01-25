package net.ilexiconn.jurassicraft.client.model.modelbase;

import net.minecraft.util.MathHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * This is a timer that can be used to easily animate models between poses. You
 * have to set the number of ticks between poses, increase or decrease the
 * timer, and get the percentage using a specific function.
 * 
 * @author RafaMv
 */
@SideOnly(Side.CLIENT)
public class ControlledAnimation
{
	/** It is the timer used to animate */
	private double timer;
	/**
	 * It is the limit time, the maximum value that the timer can be. I
	 * represents the duration of the animation
	 */
	private double limitTime;

	public ControlledAnimation(int limitTime)
	{
		this.timer = 0;
		this.limitTime = (double) limitTime;
	}

	/**
	 * Sets the duration of the animation in ticks. Try values around 50.
	 * 
	 * @param limitTime is the maximum number of ticks that the timer can reach.
	 */
    @SideOnly(Side.CLIENT)
	public void setLimitTime(int limitTime)
	{
		this.timer = 0;
		this.limitTime = (double) limitTime;
	}

	/**
	 * Sets the timer to a specific value.
	 * 
	 * @param time is the number of ticks to be set.
	 */
    @SideOnly(Side.CLIENT)
	public void setTimer(int time)
	{
		if ((double) time < this.limitTime)
			this.timer = (double) time;
	}

	/** Sets the timer to 0. */
    @SideOnly(Side.CLIENT)
	public void resetTimer()
	{
		this.timer = 0;
	}

	/** Increases the timer by 1. */
    @SideOnly(Side.CLIENT)
	public void increaseTimer()
	{
		if (this.timer < this.limitTime)
			this.timer++;
	}

	/**
	 * Increases the timer by a specific value.
	 * 
	 * @param time is the number of ticks to be increased in the timer
	 */
    @SideOnly(Side.CLIENT)
	public void increaseTimer(int time)
	{
		if (this.timer + (double) time < this.limitTime)
		{
			this.timer += (double) time;
		}
		else
		{
			this.timer = this.limitTime;
		}
	}

	/** Decreases the timer by 1. */
    @SideOnly(Side.CLIENT)
	public void decreaseTimer()
	{
		if (this.timer > 0.0D)
			this.timer--;
	}

	/**
	 * Decreases the timer by a specific value.
	 * 
	 * @param time is the number of ticks to be decreased in the timer
	 */
    @SideOnly(Side.CLIENT)
	public void decreaseTimer(int time)
	{
		if (this.timer - (double) time > 0.0D)
		{
			this.timer -= (double) time;
		}
		else
		{
			this.timer = 0.0D;
		}
	}

	/**
	 * Returns a value between 0.0F and 1.0F depending on the timer and duration
	 * of the animation (limitTime).It reaches 1.0F using 1/(1 + e^(4-8*x)). It
	 * is quite uniform but slow, and needs if statements.
	 */
    @SideOnly(Side.CLIENT)
	public float getAnimationProgressSmooth()
	{
		if (this.timer > 0.0D) 
		{
			if (this.timer < this.limitTime) 
			{
				return (float) (1.0D / (1.0D + Math.exp(4.0D - 8.0D * (this.timer / this.limitTime))));
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
	 * of the animation (limitTime).It reaches 1.0F using 1/(1 + e^(6-12*x)). It
	 * is quite uniform, but fast.
	 */
    @SideOnly(Side.CLIENT)
	public float getAnimationProgressSteep()
	{
		return (float) (1.0D / (1.0D + Math.exp(6.0D - 12.0D * (this.timer / this.limitTime))));
	}

	/**
	 * Returns a value between 0.0F and 1.0F depending on the timer and duration
	 * of the animation (limitTime).It reaches 1.0F using a sine function. It is
	 * fast in the beginning and slow in the end.
	 */
    @SideOnly(Side.CLIENT)
	public float getAnimationProgressSin()
	{
		return MathHelper.sin(1.57079632679F * (float) (this.timer / this.limitTime));
	}

	/**
	 * Returns a value between 0.0F and 1.0F depending on the timer and duration
	 * of the animation (limitTime).It reaches 1.0F using a sine function
	 * squared. It is very smooth.
	 */
    @SideOnly(Side.CLIENT)
	public float getAnimationProgressSinSqrt()
	{
		float result = MathHelper.sin(1.57079632679F * (float) (this.timer / this.limitTime));
		return result * result;
	}

	/**
	 * Returns a value between 0.0F and 1.0F depending on the timer and duration
	 * of the animation (limitTime).It reaches 1.0F using a sine function to the
	 * power of ten. It is slow in the beginning and fast in the end.
	 */
    @SideOnly(Side.CLIENT)
	public float getAnimationProgressSinToTen()
	{
		return (float) Math.pow((double) MathHelper.sin(1.57079632679F * (float) (this.timer / this.limitTime)), 10);
	}

	/**
	 * Returns a value between 0.0F and 1.0F depending on the timer and duration
	 * of the animation (limitTime).It reaches 1.0F using a sine function to a
	 * specific power "i."
	 * 
	 * @param i is the power of the sine function.
	 */
    @SideOnly(Side.CLIENT)
	public float getAnimationProgressSinPowerOf(int i)
	{
		return (float) Math.pow((double) MathHelper.sin(1.57079632679F * (float) (this.timer / this.limitTime)), i);
	}

	/**
	 * Returns a value between 0.0F and 1.0F depending on the timer and duration
	 * of the animation (limitTime).It reaches 1.0F using x^2 / (x^2 + (1-x)^2).
	 * It is smooth.
	 */
    @SideOnly(Side.CLIENT)
	public float getAnimationProgressPoly2() 
	{
		float x = (float) (this.timer / this.limitTime);
		float x2 = x * x;
		return x2 / (x2 + (1 - x) * (1 - x));
	}

	/**
	 * Returns a value between 0.0F and 1.0F depending on the timer and duration
	 * of the animation (limitTime).It reaches 1.0F using x^3 / (x^3 + (1-x)^3).
	 * It is steep.
	 */
    @SideOnly(Side.CLIENT)
	public float getAnimationProgressPoly3() 
	{
		float x = (float) (this.timer / this.limitTime);
		float x3 = x * x * x;
		return x3 / (x3 + (1 - x) * (1 - x) * (1 - x));
	}

	/**
	 * Returns a value between 0.0F and 1.0F depending on the timer and duration
	 * of the animation (limitTime).It reaches 1.0F using x^n / (x^n + (1-x)^n).
	 * It is steeper when n increases.
	 * 
	 * @param n is the power of the polynomial function.
	 */
    @SideOnly(Side.CLIENT)
	public float getAnimationProgressPolyN(int n) 
	{
		double x = this.timer / this.limitTime;
		double xi = Math.pow(x, (double) n);
		return (float) (xi / (xi + Math.pow((1.0D - x), (double) n)));
	}

	/**
	 * Returns a value between 0.0F and 1.0F depending on the timer and duration
	 * of the animation (limitTime).It reaches 1.0F using 0.5 + arctan(PI * (x -
	 * 0.5)) / 2.00776964. It is super smooth.
	 */
    @SideOnly(Side.CLIENT)
	public float getAnimationProgressArcTan()
	{
		return (float) (0.5F + 0.49806510671F * Math.atan(3.14159265359D * ((double) (this.timer / this.limitTime) - 0.5D)));
	}
}
