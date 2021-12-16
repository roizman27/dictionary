package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.Set;

import exceptions.DictionaryException;
import model.Dictionary;

public class DicitionarySystem {

	private Dictionary dictionary = new Dictionary();
	private Scanner sc = new Scanner(System.in);
	private Boolean systemOn = false;

	public void start() {
		systemOn = true;
		while (systemOn) {
			try {
				printMenu();
				String command = this.sc.nextLine();
				switch (command) {
				case "add":
				case "a":
					doAdd();
					break;

				case "find":
				case "f":
					doFind();
					break;

				case "edit":
				case "e":
					doEdit();
					break;

				case "remove":
				case "r":
					doRemove();
					break;

				case "display":
				case "d":
					doDisplay();
					break;

				case "exit":
				case "q":
					this.systemOn = false;
					this.sc.close();
					this.dictionary.saveData();
					break;
				}
			} catch (DictionaryException e) {
				System.err.println("Error: " + e.getMessage());
			}
		}
		System.out.println("Bye!!");
	}

	public void doAdd() throws DictionaryException {
		System.out.println("Entry:");
		String k = sc.nextLine();
		System.out.println("Value:");
		String v = sc.nextLine();
		dictionary.addEntry(k, v);
	}

	public void doFind() {
		System.out.println("Entry to find: ");
		String k = sc.nextLine();
		String v = this.dictionary.getDefinition(k);
		if (v == null) {
			v = "NOT FOUND";
		}
		System.out.println("definition: " + v);
	}

	public void doRemove() {
		System.out.println("Entry:");
		String k = sc.nextLine();
		this.dictionary.deleteEntry(k);
		
	}

	public void doEdit() throws DictionaryException {
		System.out.println("Entry:");
		String k = sc.nextLine();
		System.out.println("the value is:");
		System.out.println(dictionary.getDefinition(k));
		System.out.println("enter new value: ");
		String v = sc.nextLine();
		dictionary.editEntry(k, v);
	}

	public void doDisplay() {
		Set<String> set = this.dictionary.getAllEntriesSorted();
		System.out.println("=== All Entries:");
		for (String entry : set) {
			System.out.println(entry);
		}
		System.out.println("=====================");
	}

	public void printMenu() {
		System.out.println("=== Dictionary System Manu");
		System.out.println("Add entry...................add/a");
		System.out.println("Find entry.................find/f");
		System.out.println("Edit entry.................edit/e");
		System.out.println("remove entry.............remove/r");
		System.out.println("display.................display/d");
		System.out.println("Exit ......................exit/q");
		System.out.print("Enter Choice: ");

	}

}
