package com.simplilearn.assessment;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
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

	// Implement option 1: Return the current file names in ascending order.

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
				System.out.println("Non-integer option entered.");
			}

		} while (option != 4);

	}

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

	private static void deleteFile() {
		String rootDirectory = "resources";
		File directory = new File(rootDirectory);
		String[] sortedList = null;
		if (directory.isDirectory()) {
			sortedList = directory.list();
		}
		System.out.println("Enter the file name: ");
		String name = sc.next();
		File file = new File(rootDirectory, name);
		boolean fileFound = false;

		for (int i = 0; i < sortedList.length; i++) {
			if (name.compareTo(sortedList[i]) == 0) {
				fileFound = true;
				file.delete();
				System.out.println("File deleted");
				break;
			}
		}
		if(!fileFound)
			System.out.println("File not found.");
	}

	private static void searchFile() {
		String rootDirectory = "resources";
		System.out.println("Enter the file name: ");
		String name = sc.next();

		String[] sortedList = null;
		File directory = new File(rootDirectory);
		if (directory.isDirectory()) {
			sortedList = directory.list();
		}
		
		boolean fileFound = false;

		for (int i = 0; i < sortedList.length; i++) {
			if (name.compareTo(sortedList[i]) == 0) {
				fileFound = true;
				System.out.println("File is found." + " " + directory.getAbsolutePath() + "\\" + name);
				break;
			}
		}
		if(!fileFound)
			System.out.println("File not found.");			
	}

}
