package ntut.csie.lab1321.softwareEngineer.model;

import ntut.csie.lab1321.softwareEngineer.dao.AccountDAO;

public class Account {
	
	private int mId = -1;
	private String mUsername = "";
	private String mPassword = "";
	private String mEmail = "";
	
	public Account(int id,String username){
		mUsername = username;
		mId = id;
	}
	public Account(int id){
		mId = id;
	}
	
	public Account(String username){
		if(username != null && username != ""){
			mUsername = username;
		}else {
			throw new RuntimeException();
		}
	}
	public int getId() {
		return mId;
	}
	public void setmId(int mId) {
		this.mId = mId;
	}
	public String getmUsername() {
		return mUsername;
	}
	public void setmUsername(String mUsername) {
		this.mUsername = mUsername;
	}
	public String getmPassword() {
		return mPassword;
	}
	public void setmPassword(String mPassword) {
		this.mPassword = mPassword;
	}
	public String getmEmail() {
		return mEmail;
	}
	public void setmEmail(String mEmail) {
		this.mEmail = mEmail;
	}
	
	
}
