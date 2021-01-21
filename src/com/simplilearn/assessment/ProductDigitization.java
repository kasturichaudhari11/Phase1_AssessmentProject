package com.simplilearn.assessment;

import java.io.File;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ProductDigitization {

	// Single scanner for application to avoid NoSuchElementException.
	public static final Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		// create a welcome screen

		System.out.println("Welcome to LockedMe.com");
		System.out.println("Developed By: Kasturi Chaudhari");
		int option = 0;

		do {
			System.out.println(" \n" + "Select one of the following options: \n"
					+ "1: Return the current file names in ascending order. \n" + "2: Details of the user interface \n"
					+ "3: Close the application.");

			try {
				option = sc.nextInt();

				switch (option) {

				case 1:
					retrievedSortedFileList();
					break;

				case 2:
					userInterface();
					break;

				case 3:

					System.out.println("Application is closed.");
					break;

				default:
					System.out.println("Incorrect option selected.");

				}
			} catch (InputMismatchException e) {
				System.out.println("Non-integer option entered.");
			}

		} while (option != 3);
		sc.close();

	}

	// Implement main option 1: Return the current file names in ascending order.

	private static String[] retrievedSortedFileList() {
		String[] sortedList = null;
		File directory = new File("resources");
		if (directory.isDirectory()) {
			sortedList = directory.list();
			System.out.println("List of files and directories in the specified directory:");
			for (int i = 0; i < sortedList.length; i++) {
				System.out.println(sortedList[i]);
			}
		} else {
			System.out.println("Invalid directory path.");
		}

		return sortedList;
	}
	
	// Implement main option 2: Return the details of the user interface displaying sub options.
	
	private static void userInterface() {
		int option = 0;
		do {
			System.out.println(" \n" + "Select one of the following options: \n"
					+ "1: Add a file to the existing directory list. \n"
					+ "2: Delete a user specified from the existing directory list. \n"
					+ "3: Search a user specified file from the main directory.  \n"
					+ "4: Navigate back to the main context.");
			try {

				option = sc.nextInt();

				switch (option) {

				case 1:
					addFile();
					break;

				case 2:
					deleteFile();
					break;

				case 3:

					searchFile();
					break;

				case 4:

					break;

				default:
					System.out.println("Incorrect option selected.");

				}
			} catch (InputMismatchException e) {
				sc.next(); //The exception leaves the invalid input in token so we are going to burn that token.
				System.out.println("Non-integer option entered.");
			}

		} while (option != 4);

	}
	
	
	// Implement sub option 1: Return the current file names in ascending order.

	private static void addFile() {
		String rootDirectory = "resources";
		System.out.println("Enter the file name: ");
		String name = sc.next();
		File newFile = new File(rootDirectory, name);

		try {

			if (newFile.createNewFile())
				System.out.println("New file is added");
			else
				System.out.println("File already exists, choose different name.");
		} catch (IOException e) {
			System.out.println("Incorrect file path!");
			e.printStackTrace();
		}

	}
	
	// Implement sub option 2: Delete a user specified file from the existing directory list.

	private static void deleteFile() {
		String fileName = searchFile();
		if (fileName != null) {
			File file = new File(fileName);
			file.delete();
			System.out.println("File deleted.");
		}

	}
	
	// Implement sub option 2: Search a user specified file from the main directory.

	private static String searchFile() {
		String rootDirectory = "resources";
		System.out.println("Enter the file name: ");
		String name = sc.next();
		String filePath = null;

		String[] sortedList = null;
		File directory = new File(rootDirectory);
		if (directory.isDirectory()) {
			sortedList = directory.list();
		}

		for (int i = 0; i < sortedList.length; i++) {
			if (name.compareTo(sortedList[i]) == 0) {
				filePath = directory.getAbsolutePath() + "\\" + name;
				System.out.println("File is found." + " " + filePath);
				break;
			}
		}
		if (filePath == null)
			System.out.println("File not found.");
		return filePath;
	}

}
