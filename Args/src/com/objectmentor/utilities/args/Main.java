package com.objectmentor.utilities.args;

public class Main {

	public static void main(String[] args) {		
		try {
			Args arg = new Args("p*", new String[] {"-pteste"});
			boolean logging = arg.getBoolean('l');
			int port = arg.getInt('p');
			String directory = arg.getString('d');
			executeApplication(logging, port, directory);
		} catch (ArgsException e) {
			System.out.printf("Argument error: %s\n", e.getErrorCode());
			System.out.printf("%s\n", e.getErrorArgumentId());
		} catch (Exception e) {
			System.out.printf("Argument error: %s\n", e.getMessage());
		}
	}
	
	private static void executeApplication(boolean logging, int port, String directory) {
		System.out.printf("Application running on the port: %s, in directory %s.", port, directory);
	}

}
