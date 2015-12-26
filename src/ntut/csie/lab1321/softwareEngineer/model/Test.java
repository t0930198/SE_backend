package ntut.csie.lab1321.softwareEngineer.model;

public class Test {
	private int mId =-1;
	private String mTestName = "";
	private String mTestDescription = "";
	private int mTestRid = -1;
	
	
	public Test(int id) {
		mId = id;
	}
	public Test(String name){
		mTestName = name;
	}
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
		mTestName = name;
	}
	
	public String getTestDescription(){
		return mTestDescription;
	}
	public void setTestDescription(String description){
		mTestDescription = description;
	}
	
	public int getTestRid(){
		return mTestRid;
	}
	public void setTestRid(int rid){
		mTestRid = rid;
	}
}
