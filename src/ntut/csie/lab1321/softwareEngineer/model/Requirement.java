package ntut.csie.lab1321.softwareEngineer.model;

public class Requirement {
	private int mId = -1;
	private String mRequireName = "";
	private String mRequireDescription = "";
	private long mRequireStartime = -1;
	private String mRequireCommand = "";
	private String mRequireStatus = "";
	
	public String getmRequireStatus() {
		return mRequireStatus;
	}
	public void setmRequireStatus(String mRequireStatus) {
		this.mRequireStatus = mRequireStatus;
	}
	public Requirement(int id){
		mId = id;
	}
	public Requirement(String name){
		mRequireName = name;
	}
	public int getId(){
		return mId;
	}
	public void setmId(int mrId){
		this.mId = mrId;		
	}
	public String getmReqirename(){
		return mRequireName;
	}
	public void setmReqirename(String mreqirename){
		this.mRequireName = mreqirename;		
	}
	public String getmReqiredescription(){
		return mRequireDescription;
	}
	public void setmReqiredescription(String reqiredescription){
		this.mRequireDescription = reqiredescription;		
	}
	public long getmReqirestartime(){

		return mRequireStartime;
	}
	public void setmReqirestartime(long reqirestartime){
		this.mRequireStartime = reqirestartime;		
	}
	public String getmReqirecommand(){
		return mRequireCommand;
	}
	
	public void setmReqirecommand(String reqirecommand){
		this.mRequireCommand = reqirecommand;		
	}
}
