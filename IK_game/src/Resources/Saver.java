package Resources;

import java.io.File;
import java.io.IOException;

import Game.GameInfo;

public class Saver
{
	public static int getIndexByKey(String key, String[] array)
	{
		for(int i = 0; i < array.length; i++)
		{
			if(array[i].equals(key)) return i;
		}
		return -1;
	}
	
	public static byte[] byteValues = new byte[0];
	public static String[] byteKeys = new String[0];
	
	public static void add(byte b, String key)
	{
		
	}
	
	public static void update(byte b, String key)
	{
		
	}
	
	public static void update(byte b, int index)
	{
		
	}
	
	public static void remove(String key)
	{
		
	}
	
	public static void remove(int index)
	{
		
	}
	
	public static void get(String key)
	{
		
	}
	
	public static void get(int index)
	{
		
	}
	
	public static short[] shortValues = new short[0];
	public static String[] shortKeys = new String[0];
	
	public static int[] intValues = new int[0];
	public static String[] intKeys = new String[0];
	
	public static long[] longValues = new long[0];
	public static String[] longKeys = new String[0];
	
	public static double[] doubleValues = new double[0];
	public static String[] doubleKeys = new String[0];
	
	public static float[] floatValues = new float[0];
	public static String[] floatKeys = new String[0];
	
	public static boolean[] booleanValues = new boolean[0];
	public static String[] booleanKeys = new String[0];
	
	public static String[] stringValues = new String[0];
	public static String[] stringKeys = new String[0];

	public static byte[][] byteArrayValues = new byte[0][];
	public static String[] byteArrayKeys = new String[0];
	
	public static int[][] intArrayValues = new int[0][];
	public static String[] intArrayKeys = new String[0];
	
	public static String[][] stringArrayValues = new String[0][];
	public static String[] stringArrayKeys = new String[0];
	
	public static void initLoad()
	{
		File f = new File(GameInfo.StageOfDevelopment + "_" + GameInfo.NameOfTheGame + ".saver");
		if(!f.exists()) initSave();
		else
		{
			
		}
	}
	
	public static void initSave()
	{
		File f = new File(GameInfo.StageOfDevelopment + "_" + GameInfo.NameOfTheGame + ".saver");
		if(!f.exists())
		{
			try
			{
				f.createNewFile();
			}
			catch (IOException e)
			{
				System.out.println("Can't create " + GameInfo.StageOfDevelopment + "_" + GameInfo.NameOfTheGame + ".saver" + " file");
				e.printStackTrace();
			}
		}
		else
		{
			
		}
	}
}
