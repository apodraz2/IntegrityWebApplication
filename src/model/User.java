package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.mail.MessagingException;




/**
 * A User bean
 * @author adampodraza
 *
 */
public class User {
	private String username;
	private String password;
	private String email;
	private ArrayList<String> items = new ArrayList<String>();
	private ArrayList<EbayGetter> ebayGetters = new ArrayList<EbayGetter>();
	
	
	public User() {}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getEmail() {
		return this.email;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void addItem(String title) {
		
		items.add(title);
		
	}
	
	public boolean removeItem(String title) {
		
		if (getUserItems().size() == 0) {
			return false;
		}
		for (int i = 0; i < items.size(); i++) {
			String item = items.get(i);
			if(item.equals(title)){
				items.remove(item);
				return true;
			}
		}
		
		return false;
		
	}
	
	private ArrayList<ArrayList<Item>> callEbay() {
		System.out.println(items.get(0));
		System.out.println(items.size());
		ArrayList<ArrayList<Item>> itemArray = new ArrayList<ArrayList<Item>>();
		
		for (int i = 0; i < items.size(); i++) {
			EbayGetter eg = new EbayGetter(this.items.get(i));
			
			
			ArrayList<Item> ebayItems = (ArrayList<Item>) eg.getItems();
			
			itemArray.add(ebayItems);
		}
		
		return itemArray;
	}
	
	public List<String> getUserItems() {
		if (items == null)
			return null;
		
		return this.items;
	}
	
	public void sendEmail() {
		ArrayList<ArrayList<Item>> items = callEbay();
		System.out.println("Ebay called.");
		for(ArrayList<Item> itemList : items) {
			sendEmailFromList(itemList);
		}
	}
	
	private void sendEmailFromList(ArrayList<Item> itemList) {
		SendEmail se = new SendEmail(itemList, this);
		try {
			se.send();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		if(getUserItems().size() == 0) {
			sb.append("Please enter some items.");
			return sb.toString(); 
		}
		
		else {
			for (int i = 0; i < getUserItems().size(); i++) {
				sb.append(getUserItems().get(i) + "</br>" + "</br>");
			}
			return sb.toString();
			}
	}
	

}
