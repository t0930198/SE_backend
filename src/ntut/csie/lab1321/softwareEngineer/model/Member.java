package ntut.csie.lab1321.softwareEngineer.model;

public class Member {
	
	private int mId = -1;
	private int mUesrId = -1;
	private int mProjectId = -1;
	private String mRole = "";
	private String mUsername ="";
	public  Member(int userid,int projectid){
		mUesrId = userid;
		mProjectId = projectid;
	}
	public Member(int id){
		mId = id;
	}
	public Member(int userid,String role){
		mUesrId = userid;
		mRole = role;
	}
	public String getUsername(){
		return mUsername;
	}
	public void setUsername(String username){
		mUsername = username;
	}
	public int getId(){
		return mId;
	}
	public void setId(int id){
		mId = id;
	}
	
	public int getUserId(){
		return mUesrId;
	}
	public void setUserId(int userid){
		mUesrId = userid;
	}
	
	public int getProjectId(){
		return mProjectId;
	}
	public void setProjectId(int projectid){
		mProjectId = projectid;
	}
	
	public String getRole(){
		return mRole;
	}
	public void setRole(String role){
		mRole = role;
	}
}
