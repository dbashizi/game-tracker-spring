package com.tiy;

public class ScreenUtils {

	static String os = System.getProperty("os.name"); 

	public static void main(String[] args) throws Exception { 
		System.out.println("Running ..."); 

		clearScreen(); 

		System.out.println("Done!"); 
	}

	private static void clearWindowsScreen() throws Exception {
		new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor(); 
	}

	public static void clearScreen() {
		try {
			if (os.contains("Windows")) {
				clearWindowsScreen();
			} else {
				System.out.print("\033[H\033[2J");
			}
		} catch (Exception exception) {
		}
	}

}