// Copyright © 2016 Brian Pomerantz. All Rights Reserved.

import java.io.*;

public class BrianReader {
	private InputStreamReader reader;
	private BufferedReader buffer;
	
	private Runtime rt;
	private FileInputStream fis;
	
	public BrianReader() {
		reader = new InputStreamReader(System.in);
		buffer = new BufferedReader(reader);
		
		rt = Runtime.getRuntime();
	}
	
	public BrianReader(String file) {
		reader = new InputStreamReader(System.in);
		buffer = new BufferedReader(reader);
		
		rt = Runtime.getRuntime();
		
		try {
			fis = new FileInputStream(file);
		}
		
		catch (IOException e) {
			System.out.println(e);
		}
	}

	public void pause() {
		System.out.print("\nPress Enter to continue . . . ");
		try	{
			buffer.readLine();
		}
		catch(Exception e)	{
			//System.exit(0);
		}
	}

	public int readInt(String prompt) {
		int value = 0;
		String s = "";
		while (true) {
			System.out.print(prompt);
			try {
				s = buffer.readLine().trim();
				value = (new Integer(s)).intValue();
				break;
			}
			catch(Exception e) {
				System.out.println("\n\nError: your input doesn't represent a valid integer value");
				pause();
				System.out.println();
				//System.exit(0);
			}
		}
		return value;
	}

	public int readInt() {
		return readInt("");
	}

	public double readDouble(String prompt) {
		double value = 0.0D;
		String s = "";
		while (true) {
			System.out.print(prompt);
			try {
				s = buffer.readLine().trim();
				value = (new Double(s)).doubleValue();
				break;
			}
			catch(Exception e) {
				System.out.println("\n\nError: your input doesn't represent a valid double value");
				pause();
				System.out.println();
				//System.exit(0);
			}
		}
		return value;
	}

	public double readDouble() {
		return readDouble("");
	}

	public char readChar(String prompt) {
		int value = 0;
		String s = "";
		while (true) {
			System.out.print(prompt);
			try {
				s = buffer.readLine();
				s = String.valueOf(String.valueOf(s)).concat("?");
				value = s.charAt(0);
				break;
			}
			catch(Exception e) {
				System.out.println(String.valueOf(String.valueOf((new StringBuffer("\n\nError in method readChar:\n")).append(e.toString()).append("\n"))));
				pause();
				//System.exit(0);
			}
		}
		return (char)value;
	}

	public char readChar() {
		return readChar("");
	}

	public String readLine(String prompt) {
		String value = "";
		System.out.print(prompt);
		try {
			value = buffer.readLine();
		}
		catch(Exception e) {
			System.out.println(String.valueOf(String.valueOf((new StringBuffer("\n\nError in Console.readLine\n")).append(e.toString()).append("\n"))));
			pause();
			//System.exit(0);
		}
		return value;
	}

	public String readLine() {
		return readLine("");
	}

	public void cls() {
		Clear console = new Clear();
		console.cls();
	}
	
	public void exec(String s) {
		try {
			rt.exec(s);
		}
		
		catch (IOException e) {
			System.out.println(e);
		}
	}
	
	public boolean setFile(String file) {
		try {
			FileInputStream fis = new FileInputStream(file);
			
			return true;
		}
		
		catch (IOException e) {
			System.out.println(e);
			
			return false;
		}
	}
	
	public String readFile(String file) {
		try {
			FileInputStream fis2 = new FileInputStream(file);

			String text = "";
			
			while (fis2.available() > 0) {
				text += (char) fis2.read();
			}
		
			return text;
		}
		
		catch (IOException e){
			System.out.println(e);
			
			return null;
		}
	}
	
	public String readFile() {
		try {
			if (fis == null) {
				return null;
			}
			
			String text = "";
			
			while (fis.available() > 0) {
				text += (char) fis.read();
			}
		
			return text;
		}
		
		catch (IOException e){
			System.out.println(e);
			
			return null;
		}
	}
	
	public String readFileLine(String file) {
		try {
			FileInputStream fis2 = new FileInputStream(file);
			
			if (fis == null) {
				return null;
			}
			
			String text = "";
			char temp;
			
			while (fis2.available() > 0) {
				temp = (char) fis.read();
				
				if (temp == '\n') {
					return text;
				}
				
				text += temp;
			}
		
			return text;
		}
		
		catch (IOException e){
			System.out.println(e);
			
			return null;
		}
	}
	
	public String readFileLine() {
		try {
			if (fis == null) {
				return null;
			}
			
			String text = "";
			char temp;
			
			while (fis.available() > 0) {
				temp = (char) fis.read();
				
				if (temp == '\n') {
					return text;
				}
				
				text += temp;
			}
		
			return text;
		}
		
		catch (IOException e){
			System.out.println(e);
			
			return null;
		}
	}
}

class Clear {
	public native void cls();
	static {
		System.loadLibrary("clsdll");
	}
}
