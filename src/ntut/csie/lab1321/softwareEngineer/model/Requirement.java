package ntut.csie.lab1321.softwareEngineer.model;

public class Requirement {
	private int mId = -1;
	private String mReqirename = "";
	private String mReqiredescription = "";
	private long mReqirestartime = -1;
	private String mReqirecommand = "";
	
	public Requirement(int id){
		mId = id;
	}
	public Requirement(String name){
		mReqirename = name;
	}
	public int getId(){
		return mId;
	}
	public void setmId(int mrId){
		this.mId = mrId;		
	}
	public String getmReqirename(){
		return mReqirename;
	}
	public void setmReqirename(String mreqirename){
		this.mReqirename = mreqirename;		
	}
	public String getmReqiredescription(){
		return mReqiredescription;
	}
	public void setmReqiredescription(String reqiredescription){
		this.mReqiredescription = reqiredescription;		
	}
	public long getmReqirestartime(){
		return mReqirestartime;
	}
	public void setmReqirestartime(long reqirestartime){
		this.mReqirestartime = reqirestartime;		
	}
	public String getmReqirecommand(){
		return mReqirecommand;
	}
	public void setmReqirecommand(String reqirecommand){
		this.mReqirecommand = reqirecommand;		
	}
}
