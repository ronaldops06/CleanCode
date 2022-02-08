package com.objectmentor.utilities.args;

public class Main {

	public static void main(String[] args) {		
		try {
			Args arg = new Args("x#", new String[] {"-x", "Forty two"});
			System.out.printf(arg.errorMessage()+"\n");
			System.out.print(arg.errorArgumentId+"\n");
			System.out.printf(arg.errorParameter+"\n");
			boolean logging = arg.getBoolean('1');
			int port = arg.getInt('p');
			String directory = arg.getString('d');
			executeApplication(logging, port, directory);
		} catch (Exception e) {
			System.out.printf("Argument error: %s\n", e.getMessage());
		}
	}
	
	private static void executeApplication(boolean logging, int port, String directory) {
		System.out.printf("Application running on the port: %s, in directory %s.", port, directory);
	}

}
