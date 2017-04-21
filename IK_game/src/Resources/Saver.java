package Resources;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import Game.GameInfo;

public class Saver
{
	public String filePath = "";
	public boolean isSaving = false;
	public boolean isLoading = false;

	public String fullestGameName;
	public String NameOfTheGame;
	public String StageOfDevelopment;
	public int GlobalVersion;
	public int LocalVersion;
	public long timeWhenFileSaved;
	
	public Saver(String filePath)
	{
		this.filePath = filePath;
		initLoad();
	}
	
	///////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////unit voids///////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////
	
	private int getIndexByKey(String key, String[] array)
	{
		for(int i = 0; i < array.length; i++) if(array[i].equals(key)) return i;
		return -1;
	}
	
	private <T> T[] addToArray(T obj, T[] array)
	{
		ArrayList<T> buff = new ArrayList<T>();
		for(int i = 0; i < array.length; i++) buff.add(array[i]);
		buff.add(obj);
		return buff.toArray(array);
	}
	
	private <T> T[] removeFromArray(int index, T[] array)
	{
		ArrayList<T> buff = new ArrayList<T>();
		for(int i = 0; i < array.length && i < index; i++) buff.add(array[i]);
		for(int i = index+1; i < array.length; i++) buff.add(array[i]);
		return buff.toArray(array);
	}
	
	private <T> void update(T obj, String key, T[] values, String[] keys)
	{
		int index = this.getIndexByKey(key, keys);
		update(obj, index, values, keys);
	}
	
	private <T> void update(T obj, int index, T[] values, String[] keys)
	{
		if(index < values.length && index < keys.length && index >= 0) values[index] = obj;
		else throw new IndexOutOfBoundsException("keys.length: " + keys.length + " values.length: " + values.length + " index: " + index);
	}
	
	private <T> T get(String key, T[] values, String[] keys)
	{
		int index = this.getIndexByKey(key, keys);
		return get(index, values, keys);
	}
	
	private <T> T get(int index, T[] values, String[] keys)
	{
		if(index < values.length && index < keys.length && index >= 0) return values[index];
		else throw new IndexOutOfBoundsException("keys.length: " + keys.length + " values.length: " + values.length + " index: " + index);
	}

	
	///////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////BYTEs///////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////
	
	private Byte[] byteValues = new Byte[0];
	private String[] byteKeys = new String[0];
	
	public void addByte(byte v, String key) { byteValues = this.addToArray(v, byteValues); byteKeys = this.addToArray(key, byteKeys); }
	public void updateByte(byte v, String key) { this.update(v, key, byteValues, byteKeys); }
	public void updateByte(byte v, int index) { this.update(v, index, byteValues, byteKeys); }
	public void removeByte(String key) { removeByte(this.getIndexByKey(key, byteKeys)); }
	public void removeByte(int index) { byteValues = this.removeFromArray(index, byteValues); byteKeys = this.removeFromArray(index, byteKeys); }
	public byte getByte(String key) { return this.get(key, byteValues, byteKeys); }
	public byte getByte(int index) { return this.get(index, byteValues, byteKeys); }
	public Byte[] getByteArray() { return byteValues; }
	public String[] getByteKeysArray() { return byteKeys; }
	public boolean existByte(String key) { return this.getIndexByKey(key, byteKeys) != -1; }
	
	///////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////SHORTs//////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////
	
	private Short[] shortValues = new Short[0];
	private String[] shortKeys = new String[0];
	
	public void addShort(short v, String key) { shortValues = this.addToArray(v, shortValues); shortKeys = this.addToArray(key, shortKeys); }
	public void updateShort(short v, String key) { this.update(v, key, shortValues, shortKeys); }
	public void updateShort(short v, int index) { this.update(v, index, shortValues, shortKeys); }
	public void removeShort(String key) { removeShort(this.getIndexByKey(key, shortKeys)); }
	public void removeShort(int index) { shortValues = this.removeFromArray(index, shortValues); shortKeys = this.removeFromArray(index, shortKeys); }
	public short getShort(String key) { return this.get(key, shortValues, shortKeys); }
	public short getShort(int index) { return this.get(index, shortValues, shortKeys); }
	public Short[] getShortArray() { return shortValues; }
	public String[] getShortKeysArray() { return shortKeys; }
	public boolean existShort(String key) { return this.getIndexByKey(key, shortKeys) != -1; }
	
	///////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////INTs//////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////
	
	private Integer[] intValues = new Integer[0];
	private String[] intKeys = new String[0];
	
	public void addInt(int v, String key) { intValues = this.addToArray(v, intValues); intKeys = this.addToArray(key, intKeys); }
	public void updateInt(int v, String key) { this.update(v, key, intValues, intKeys); }
	public void updateInt(int v, int index) { this.update(v, index, intValues, intKeys); }
	public void removeInt(String key) { removeInt(this.getIndexByKey(key, intKeys)); }
	public void removeInt(int index) { intValues = this.removeFromArray(index, intValues); intKeys = this.removeFromArray(index, intKeys); }
	public int getInt(String key) { return this.get(key, intValues, intKeys); }
	public int getInt(int index) { return this.get(index, intValues, intKeys); }
	public Integer[] getIntArray() { return intValues; }
	public String[] getIntKeysArray() { return intKeys; }
	public boolean existInt(String key) { return this.getIndexByKey(key, intKeys) != -1; }
	
	///////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////LONGs///////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////
	
	private Long[] longValues = new Long[0];
	private String[] longKeys = new String[0];
	
	public void addLong(long v, String key) { longValues = this.addToArray(v, longValues); longKeys = this.addToArray(key, longKeys); }
	public void updateLong(long v, String key) { this.update(v, key, longValues, longKeys); }
	public void updateLong(long v, int index) { this.update(v, index, longValues, longKeys); }
	public void removeLong(String key) { removeLong(this.getIndexByKey(key, longKeys)); }
	public void removeLong(int index) { longValues = this.removeFromArray(index, longValues); longKeys = this.removeFromArray(index, longKeys); }
	public long getLong(String key) { return this.get(key, longValues, longKeys); }
	public long getLong(int index) { return this.get(index, longValues, longKeys); }
	public Long[] getLongArray() { return longValues; }
	public String[] getLongKeysArray() { return longKeys; }
	public boolean existLong(String key) { return this.getIndexByKey(key, longKeys) != -1; }
	
	///////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////DOUBLEs//////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////
	
	private Double[] doubleValues = new Double[0];
	private String[] doubleKeys = new String[0];
	
	public void addDouble(double v, String key) { doubleValues = this.addToArray(v, doubleValues); doubleKeys = this.addToArray(key, doubleKeys); }
	public void updateDouble(double v, String key) { this.update(v, key, doubleValues, doubleKeys); }
	public void updateDouble(double v, int index) { this.update(v, index, doubleValues, doubleKeys); }
	public void removeDouble(String key) { removeDouble(this.getIndexByKey(key, doubleKeys)); }
	public void removeDouble(int index) { doubleValues = this.removeFromArray(index, doubleValues); doubleKeys = this.removeFromArray(index, doubleKeys); }
	public double getDouble(String key) { return this.get(key, doubleValues, doubleKeys); }
	public double getDouble(int index) { return this.get(index, doubleValues, doubleKeys); }
	public Double[] getDoubleArray() { return doubleValues; }
	public String[] getDoubleKeysArray() { return doubleKeys; }
	public boolean existDouble(String key) { return this.getIndexByKey(key, doubleKeys) != -1; }
	
	///////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////FLOATs///////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////
	
	private Float[] floatValues = new Float[0];
	private String[] floatKeys = new String[0];
	
	public void addFloat(float v, String key) { floatValues = this.addToArray(v, floatValues); floatKeys = this.addToArray(key, floatKeys); }
	public void updateFloat(float v, String key) { this.update(v, key, floatValues, floatKeys); }
	public void updateFloat(float v, int index) { this.update(v, index, floatValues, floatKeys); }
	public void removeFloat(String key) { removeFloat(this.getIndexByKey(key, floatKeys)); }
	public void removeFloat(int index) { floatValues = this.removeFromArray(index, floatValues); floatKeys = this.removeFromArray(index, floatKeys); }
	public float getFloat(String key) { return this.get(key, floatValues, floatKeys); }
	public float getFloat(int index) { return this.get(index, floatValues, floatKeys); }
	public Float[] getFloatArray() { return floatValues; }
	public String[] getFloatKeysArray() { return floatKeys; }
	public boolean existFloat(String key) { return this.getIndexByKey(key, floatKeys) != -1; }
	
	///////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////BOOLEANs//////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////
	
	private Boolean[] booleanValues = new Boolean[0];
	private String[] booleanKeys = new String[0];
	
	public void addBoolean(boolean v, String key) { booleanValues = this.addToArray(v, booleanValues); booleanKeys = this.addToArray(key, booleanKeys); }
	public void updateBoolean(boolean v, String key) { this.update(v, key, booleanValues, booleanKeys); }
	public void updateBoolean(boolean v, int index) { this.update(v, index, booleanValues, booleanKeys); }
	public void removeBoolean(String key) { removeBoolean(this.getIndexByKey(key, booleanKeys)); }
	public void removeBoolean(int index) { booleanValues = this.removeFromArray(index, booleanValues); booleanKeys = this.removeFromArray(index, booleanKeys); }
	public boolean getBoolean(String key) { return this.get(key, booleanValues, booleanKeys); }
	public boolean getBoolean(int index) { return this.get(index, booleanValues, booleanKeys); }
	public Boolean[] getBooleanArray() { return booleanValues; }
	public String[] getBooleanKeysArray() { return booleanKeys; }
	public boolean existBoolean(String key) { return this.getIndexByKey(key, booleanKeys) != -1; }
	
	///////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////STRINGs//////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////
	
	private String[] stringValues = new String[0];
	private String[] stringKeys = new String[0];
	
	public void addString(String v, String key) { stringValues = this.addToArray(v, stringValues); stringKeys = this.addToArray(key, stringKeys); }
	public void updateString(String v, String key) { this.update(v, key, stringValues, stringKeys); }
	public void updateString(String v, int index) { this.update(v, index, stringValues, stringKeys); }
	public void removeString(String key) { removeString(this.getIndexByKey(key, stringKeys)); }
	public void removeString(int index) { stringValues = this.removeFromArray(index, stringValues); stringKeys = this.removeFromArray(index, stringKeys); }
	public String getString(String key) { return this.get(key, stringValues, stringKeys); }
	public String getString(int index) { return this.get(index, stringValues, stringKeys); }
	public String[] getStringArray() { return stringValues; }
	public String[] getStringKeysArray() { return stringKeys; }
	public boolean existString(String key) { return this.getIndexByKey(key, stringKeys) != -1; }
	
	///////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////BYTEARRAYs//////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////

	private Byte[][] bytesArrayValues = new Byte[0][];
	private String[] bytesArrayKeys = new String[0];
	
	public void addBytesArray(byte[] v, String key) { bytesArrayValues = this.addToArray(byteArrayToByteArray(v), bytesArrayValues); bytesArrayKeys = this.addToArray(key, bytesArrayKeys); }
	public void updateBytesArray(byte[] v, String key) { this.update(byteArrayToByteArray(v), key, bytesArrayValues, bytesArrayKeys); }
	public void updateBytesArray(byte[] v, int index) { this.update(byteArrayToByteArray(v), index, bytesArrayValues, bytesArrayKeys); }
	public void removeBytesArray(String key) { removeBytesArray(this.getIndexByKey(key, bytesArrayKeys)); }
	public void removeBytesArray(int index) { bytesArrayValues = this.removeFromArray(index, bytesArrayValues); bytesArrayKeys = this.removeFromArray(index, bytesArrayKeys); }
	public Byte[] getBytesArray(String key) { return this.get(key, bytesArrayValues, bytesArrayKeys); }
	public Byte[] getBytesArray(int index) { return this.get(index, bytesArrayValues, bytesArrayKeys); }
	public Byte[][] getBytesArrayArray() { return bytesArrayValues; }
	public String[] getBytesArrayKeysArray() { return bytesArrayKeys; }
	public boolean existBytesArray(String key) { return this.getIndexByKey(key, bytesArrayKeys) != -1; }
	
	private Byte[] byteArrayToByteArray(byte[] arr)
	{
		Byte[] ret = new Byte[arr.length];
		for(int i = 0; i < ret.length; i++) ret[i] = arr[i];
		return ret;
	}
	
	///////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////INTARRAYs//////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////
	
	private Integer[][] intsArrayValues = new Integer[0][];
	private String[] intsArrayKeys = new String[0];
	
	public void addIntsArray(int[] v, String key) { intsArrayValues = this.addToArray(intArrayToIntegerArray(v), intsArrayValues); intsArrayKeys = this.addToArray(key, intsArrayKeys); }
	public void updateIntsArray(int[] v, String key) { this.update(intArrayToIntegerArray(v), key, intsArrayValues, intsArrayKeys); }
	public void updateIntsArray(int[] v, int index) { this.update(intArrayToIntegerArray(v), index, intsArrayValues, intsArrayKeys); }
	public void removeIntsArray(String key) { removeIntsArray(this.getIndexByKey(key, intsArrayKeys)); }
	public void removeIntsArray(int index) { intsArrayValues = this.removeFromArray(index, intsArrayValues); intsArrayKeys = this.removeFromArray(index, intsArrayKeys); }
	public Integer[] getIntsArray(String key) { return this.get(key, intsArrayValues, intsArrayKeys); }
	public Integer[] getIntsArray(int index) { return this.get(index, intsArrayValues, intsArrayKeys); }
	public Integer[][] getIntsArrayArray() { return intsArrayValues; }
	public String[] getIntsArrayKeysArray() { return intsArrayKeys; }
	public boolean existIntsArray(String key) { return this.getIndexByKey(key, intsArrayKeys) != -1; }
	
	private Integer[] intArrayToIntegerArray(int[] arr)
	{
		Integer[] ret = new Integer[arr.length];
		for(int i = 0; i < ret.length; i++) ret[i] = arr[i];
		return ret;
	}
	
	///////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////STRINGARRAYs////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////
	
	private String[][] stringsArrayValues = new String[0][];
	private String[] stringsArrayKeys = new String[0];
	
	public void addStringsArray(String[] v, String key) { stringsArrayValues = this.addToArray(v, stringsArrayValues); stringsArrayKeys = this.addToArray(key, stringsArrayKeys); }
	public void updateStringsArray(String[] v, String key) { this.update(v, key, stringsArrayValues, stringsArrayKeys); }
	public void updateStringsArray(String[] v, int index) { this.update(v, index, stringsArrayValues, stringsArrayKeys); }
	public void removeStringsArray(String key) { removeStringsArray(this.getIndexByKey(key, stringsArrayKeys)); }
	public void removeStringsArray(int index) { stringsArrayValues = this.removeFromArray(index, stringsArrayValues); stringsArrayKeys = this.removeFromArray(index, stringsArrayKeys); }
	public String[] getStringsArray(String key) { return this.get(key, stringsArrayValues, stringsArrayKeys); }
	public String[] getStringsArray(int index) { return this.get(index, stringsArrayValues, stringsArrayKeys); }
	public String[][] getStringsArrayArray() { return stringsArrayValues; }
	public String[] getStringsArrayKeysArray() { return stringsArrayKeys; }
	public boolean existStringsArray(String key) { return this.getIndexByKey(key, stringsArrayKeys) != -1; }
	
	///////////////////////////////////////////////////////////////////////////////
	//////////////////////////////SAVE/LOAD voids//////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////
	
	public void initLoad()
	{
		isLoading = true;
		
		File f = new File(this.filePath);
		if(f.exists())
		{
			DataInputStream in = null;
			try
			{
				in = new DataInputStream(new FileInputStream(f));
			}
			catch (FileNotFoundException e)
			{
				System.err.println("File " + this.filePath + " doesn't exists");
				e.printStackTrace();
			}
			
			try
			{
				if(in != null)
				{
					//reading game info as string
					this.fullestGameName = in.readUTF();
					//reading game name as string
					this.NameOfTheGame = in.readUTF();
					//reading game stage of development as string
					this.StageOfDevelopment = in.readUTF();
					//reading game versions as int
					this.GlobalVersion = in.readInt();
					this.LocalVersion = in.readInt();
					//reading system time as Mills when file was saved
					this.timeWhenFileSaved = in.readLong();

					//reading Bytes
					readObjectAndKeys(in, Byte.class);
					//reading Short
					readObjectAndKeys(in, Short.class);
					//reading Integer
					readObjectAndKeys(in, Integer.class);
					//reading Long
					readObjectAndKeys(in, Long.class);
					//reading Double
					readObjectAndKeys(in, Double.class);
					//reading Float
					readObjectAndKeys(in, Float.class);
					//reading Boolean
					readObjectAndKeys(in, Boolean.class);
					//reading String
					readObjectAndKeys(in, String.class);
					
					//reading Arrays
					readObjectArrayAndKeys(in, Byte.class);
					readObjectArrayAndKeys(in, Integer.class);
					readObjectArrayAndKeys(in, String.class);
				}
				else
				{
					System.err.println("DataInputStream doesn't created");
				}
			}
			catch (IOException e)
			{
				System.err.println("Can't read " + this.filePath + " file");
				e.printStackTrace();
			}
		}
		else
		{
			fullestGameName = GameInfo.getGameFullestName();
			NameOfTheGame = GameInfo.NameOfTheGame;
			StageOfDevelopment = GameInfo.StageOfDevelopment;
			GlobalVersion = GameInfo.GlobalVersion;
			LocalVersion = GameInfo.LocalVersion;
			timeWhenFileSaved = System.currentTimeMillis();
		}
		
		isLoading = false;
	}
	
	private void readObjectArrayAndKeys(DataInputStream in, Class cl) throws IOException
	{
		int arrayLength = in.readInt();
		int keysArrayLength = in.readInt();

		if(arrayLength != keysArrayLength) throw new IOException("Can't read file " + this.filePath + ". arrayLength != keysArrayLength");
		
		for(int i = 0; i < arrayLength; i++)
		{
			String key = in.readUTF();
			int arrayLength2 = in.readInt();

			if(cl == Byte.class)
			{
				byte[] bb = new byte[arrayLength2];
				for(int j = 0; j < arrayLength2; j++) bb[j] = in.readByte();
				this.addBytesArray(bb, key);
			}

			if(cl == Integer.class)
			{
				int[] ii = new int[arrayLength2];
				for(int j = 0; j < arrayLength2; j++) ii[j] = in.readInt();
				this.addIntsArray(ii, key);
			}

			if(cl == String.class)
			{
				String[] ss = new String[arrayLength2];
				for(int j = 0; j < arrayLength2; j++) ss[j] = in.readUTF();
				this.addStringsArray(ss, key);
			}
		}
	}
	
	private void readObjectAndKeys(DataInputStream in, Class cl) throws IOException
	{
		int arrayLength = in.readInt();
		int keysArrayLength = in.readInt();
		
		if(arrayLength != keysArrayLength) throw new IOException("Can't read file " + this.filePath + ". arrayLength != keysArrayLength");
		
		for(int i = 0; i < arrayLength; i++)
		{
			String key = in.readUTF();
			
			Object o;

			if(cl == Byte.class)
			{
				o = in.readByte();
				this.addByte((byte)o, key);
			}
			if(cl == Short.class)
			{
				o = in.readShort();
				this.addShort((short)o, key);
			}
			if(cl == Integer.class)
			{
				o = in.readInt();
				this.addInt((int)o, key);
			}
			if(cl == Long.class)
			{
				o = in.readLong();
				this.addLong((long)o, key);
			}
			if(cl == Double.class)
			{
				o = in.readDouble();
				this.addDouble((double)o, key);
			}
			if(cl == Float.class)
			{
				o = in.readFloat();
				this.addFloat((float)o, key);
			}
			if(cl == Boolean.class)
			{
				o = in.readBoolean();
				this.addBoolean((boolean)o, key);
			}
			if(cl == String.class)
			{
				o = in.readUTF();
				this.addString((String)o, key);
			}
		}
	}
	
	public void initSave()
	{
		isSaving = true;
		
		File f = new File(this.filePath);
		if(!f.exists())
		{
			try
			{
				f.createNewFile();
			}
			catch (IOException e)
			{
				System.err.println("Can't create " + this.filePath + " file");
				e.printStackTrace();
			}
		}
		
		DataOutputStream out = null;
		
		try
		{
			 out = new DataOutputStream(new FileOutputStream(f));
		}
		catch (FileNotFoundException e)
		{
			System.err.println("File " + this.filePath + " doesn't exists");
			e.printStackTrace();
		}
		
		try
		{
			if(out != null)
			{
				//Saving game info as string
				out.writeUTF("123 test 1.0");
				//Saving game name as string
				out.writeUTF("123");
				//Saving game stage of development as string
				out.writeUTF("test");
				//Saving game versions as int
				out.writeInt(1);
				out.writeInt(0);
				//Saving system time as Mills
				out.writeLong(System.currentTimeMillis());

				//Saving Bytes
				saveObjectArrayWithKeys(out, this.getByteArray(), this.getByteKeysArray());
				//Saving Short
				saveObjectArrayWithKeys(out, this.getShortArray(), this.getShortKeysArray());
				//Saving Integer
				saveObjectArrayWithKeys(out, this.getIntArray(), this.getIntKeysArray());
				//Saving Long
				saveObjectArrayWithKeys(out, this.getLongArray(), this.getLongKeysArray());
				//Saving Double
				saveObjectArrayWithKeys(out, this.getDoubleArray(), this.getDoubleKeysArray());
				//Saving Float
				saveObjectArrayWithKeys(out, this.getFloatArray(), this.getFloatKeysArray());
				//Saving Boolean
				saveObjectArrayWithKeys(out, this.getBooleanArray(), this.getBooleanKeysArray());
				//Saving String
				saveObjectArrayWithKeys(out, this.getStringArray(), this.getStringKeysArray());
				
				//Saving Arrays
				saveObjectDoubleArrayWithKeys(out, this.getBytesArrayArray(), this.getBytesArrayKeysArray());
				saveObjectDoubleArrayWithKeys(out, this.getIntsArrayArray(), this.getIntsArrayKeysArray());
				saveObjectDoubleArrayWithKeys(out, this.getStringsArrayArray(), this.getStringsArrayKeysArray());
				
				out.close();
			}
			else
			{
				System.err.println("DataOutputStream doesn't created");
			}
		}
		catch (IOException e)
		{
			System.err.println("Can't write to " + this.filePath + " file");
			e.printStackTrace();
		}
		
		isSaving = false;
	}
	
	private void saveObjectDoubleArrayWithKeys(DataOutputStream out, Object[][] array, String[] keys) throws IOException
	{
		if(array.length != keys.length) throw new IOException("Can't save file " + this.filePath + ". Object[][].length != Keys[].length");
		out.writeInt(array.length);
		out.writeInt(keys.length);
		for(int i = 0; i < array.length; i++)
		{
			out.writeUTF(keys[i]);
			out.writeInt(array[i].length);
			for(int j = 0; j < array[i].length; j++)
			{
				Object o = array[i][j];

				if(o instanceof Byte) out.writeByte((Byte)o);
				if(o instanceof Short) out.writeShort((short)o);
				if(o instanceof Integer) out.writeInt((int)o);
				if(o instanceof Long) out.writeLong((long)o);
				if(o instanceof Double) out.writeDouble((double)o);
				if(o instanceof Float) out.writeFloat((float)o);
				if(o instanceof Boolean) out.writeBoolean((boolean)o);
				if(o instanceof String) out.writeUTF((String)o);
			}
		}
	}
	
	private void saveObjectArrayWithKeys(DataOutputStream out, Object[] array, String[] keys) throws IOException
	{
		if(array.length != keys.length) throw new IOException("Can't save file " + this.filePath + ". Object[].length != Keys[].length");
		out.writeInt(array.length);
		out.writeInt(keys.length);
		for(int i = 0; i < array.length; i++)
		{
			out.writeUTF(keys[i]);
			
			Object o = array[i];

			if(o instanceof Byte) out.writeByte((Byte)o);
			if(o instanceof Short) out.writeShort((short)o);
			if(o instanceof Integer) out.writeInt((int)o);
			if(o instanceof Long) out.writeLong((long)o);
			if(o instanceof Double) out.writeDouble((double)o);
			if(o instanceof Float) out.writeFloat((float)o);
			if(o instanceof Boolean) out.writeBoolean((boolean)o);
			if(o instanceof String) out.writeUTF((String)o);
		}
	}
}






















