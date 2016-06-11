package com.example.mylogin;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.StreamCorruptedException;
import java.util.HashMap;

public class Signin {
	@SuppressWarnings("unchecked")
	public static boolean Login(String email, String password){
		//New a hash map to read information from file information.ser
		HashMap<String, InformationOfUser> hashMap = new HashMap<String, InformationOfUser>();
		
		//Open file information.ser and save to hash map
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
		
		//Check login
		for (String s: hashMap.keySet()) {
			// check user name = user name and password = password
			if(s.equals(email) && hashMap.get(s).getPassword().equals(password)){
				return true;
			}
		}
		return false;
	}
}
