package net.ilexiconn.jurassicraft.packet;

import net.ilexiconn.jurassicraft.network.IPacket;
import net.ilexiconn.jurassicraft.tile.TileEntityGrinder;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * @author ProPercivalalb
 **/
public class PacketGrindSound extends IPacket {

	public int x, y, z;
	
	public PacketGrindSound() {}
	public PacketGrindSound(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	@Override
	public void read(DataInputStream data) throws IOException {
		this.x = data.readInt();
		this.y = data.readInt();
		this.z = data.readInt();
	}

	@Override
	public void write(DataOutputStream data) throws IOException {
		data.writeInt(this.x);
		data.writeInt(this.y);
		data.writeInt(this.z);
	}

	@Override
	public void execute(EntityPlayer player) {
		World world = player.worldObj;
		
		TileEntity tileEntity = world.getTileEntity(this.x, this.y, this.z);
		
		if(tileEntity instanceof TileEntityGrinder) {
			TileEntityGrinder grinder = (TileEntityGrinder)tileEntity;
			grinder.clientSpinTime = 0;
		}
	}

}
