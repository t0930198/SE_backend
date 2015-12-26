package ntut.csie.lab1321.softwareEngineer.model;

public class Test {
	private int mId =-1;
	private String mTestName = "";
	private String mTestDescription = "";
	private int mTestRid = -1;
	
	public int getTestId(){
		return mId;
	}
	public void setTestId(int id){
		this.mId = id;
	}
	
	public String getTestName(){
		return mTestName;
	}
	public void setTestName(String name){
		this.mTestName = name;
	}
	
	public String getTestDescription(){
		return mTestDescription;
	}
	public void setTestDescription(String description){
		this.mTestDescription = description;
	}
	
	public int getTestRid(){
		return mTestRid;
	}
	public void setTestRid(int rid){
		this.mTestRid = rid;
	}
}
