package pl.luxdev.lol.util;

import java.util.HashSet;

import org.bukkit.block.Block;

public class BlockUtils {
	
	public static HashSet<Byte> blockAirFoliageSet = new HashSet<Byte>();

    static {
        blockAirFoliageSet.add((byte) 0);
        blockAirFoliageSet.add((byte) 6);
        blockAirFoliageSet.add((byte) 31);
        blockAirFoliageSet.add((byte) 32);
        blockAirFoliageSet.add((byte) 37);
        blockAirFoliageSet.add((byte) 38);
        blockAirFoliageSet.add((byte) 39);
        blockAirFoliageSet.add((byte) 40);
        blockAirFoliageSet.add((byte) 51);
        blockAirFoliageSet.add((byte) 59);
        blockAirFoliageSet.add((byte) 104);
        blockAirFoliageSet.add((byte) 105);
        blockAirFoliageSet.add((byte) 115);
        blockAirFoliageSet.add((byte) 141);
        blockAirFoliageSet.add((byte) 142);
    }
	
	@SuppressWarnings("deprecation")
	public static boolean airFoliage(Block block) {
        if (block == null)
            return false;
        return airFoliage(block.getTypeId());
    }

    public static boolean airFoliage(int block) {
        return airFoliage((byte) block);
    }

    public static boolean airFoliage(byte block) {
        return blockAirFoliageSet.contains(block);
    }

}
