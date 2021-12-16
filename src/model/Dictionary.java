package model;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;

import exceptions.DictionaryException;

import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Dictionary {
	
	private Map<String,String> dict = new TreeMap<>();
	private File dir = new File("c:/dictionaryProject");
	private File file = new File(dir,"entries.data");
	
	{
		if(!dir.exists()) {
			dir.mkdir();
		}
		
		loadData();
	}
	
	public void loadData() {
		if(file.exists()) {
			try(ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)))){
				this.dict = (Map<String, String>) in.readObject();
			}catch(IOException | ClassNotFoundException e){
				e.printStackTrace();
			}
		}
	}
	
	public void saveData() {
		try (ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)))) {
			out.writeObject(this.dict);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void addEntry(String k,String v) throws DictionaryException{
		if(k == null || k == ""||k.length()==0) {
			throw new DictionaryException("You must use a Key to join the value with");
			}
		if(dict.containsKey(k)) {
			throw new DictionaryException("The key String is already in the dictionary");
		}
		this.dict.put(k, v);
		
	}
	
	public String getDefinition(String k) {
		return (this.dict.get(k));
	}
	
	public void editEntry(String k,String v) throws DictionaryException{
		if (k == null || k.length() == 0) {
			throw new DictionaryException("editEntry failed - empty entry");
		}
		if (!dict.containsKey(k)) {
			throw new DictionaryException("editEntry failed - entry " + k + " NOT exists.");
		}
		dict.replace(k, v);
	}
	
	public void deleteEntry(String k){
		this.dict.remove(k);
	}
	
	
	public Set<String> getAllEntriesSorted(){
		Set<String> s = new TreeSet<>(dict.keySet());
		return (s);	
	}
	
}
