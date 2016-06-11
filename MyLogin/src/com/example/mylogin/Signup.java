package com.example.mylogin;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.HashMap;

public class Signup {
	
	// Create new account.
	@SuppressWarnings("unchecked")
	public static boolean createAccount(InformationOfUser informationOfUser){
		// Create new Hash Map
		HashMap<String,InformationOfUser> hashMap = new HashMap<String, InformationOfUser>();
		
		// Open file save information of user
		try {
			ObjectInputStream ob = new ObjectInputStream(new BufferedInputStream(new FileInputStream("information.ser")));
			hashMap = (HashMap<String, InformationOfUser>) ob.readObject();
			ob.close();
		} catch (StreamCorruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Check email is exist.
		for (String s : hashMap.keySet()) {
			//If email already exists then we return result false.
			if(informationOfUser.getEmail().equals(s))
				return false;
		}
		
		//If email does not exist then we add user to hash map.
		hashMap.put(informationOfUser.getEmail(), informationOfUser);
		
		//Open file information.ser to save information of user.
		try {
			ObjectOutputStream ob = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("information.ser")));
			ob.writeObject(hashMap);
			ob.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// return true
		return true;
	}
}
