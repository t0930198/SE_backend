package ntut.csie.lab1321.softwareEngineer.model;

public class Requirement {
	private int mId = -1;
	private String mRequirementName = "";
	private String mRequirementDescription = "";
	private long mRequirementStartTime = -1;
	private String mRequirementComment = "";
	private String mRequirementType = "";
	private int projectId = -1;
	private boolean mRequirementHadfix = false;
	
	public String getRequirementType() {
		return mRequirementType;
	}
	public void setRequirementType(String type) {
		this.mRequirementType = type;
	}
	
	public void deleteRequirement(int id){
		mId = id;
	}
	public Requirement(int id){
		mId = id;
	}
	public Requirement(String name){
		mRequirementName = name;
	}
	public int getId(){
		return mId;
	}
	public void setId(int id){
		this.mId = id;		
	}
	public String getRequirementName(){
		return mRequirementName;
	}
	public void setRequirementName(String name){
		this.mRequirementName = name;		
	}
	public String getRequirementDescription(){
		return mRequirementDescription;
	}
	public void setRequirementDescription(String description){
		this.mRequirementDescription = description;		
	}
	public long getRequirementStartTime(){

		return mRequirementStartTime;
	}
	public void setRequirementStartTime(long startTime){
		this.mRequirementStartTime = startTime;		
	}
	public String getRequirementComment(){
		return mRequirementComment;
	}
	
	public void setmRequirementComment(String comment){
		this.mRequirementComment = comment;		
	}
	
	public boolean getRequirementHadfix()
	{
		return mRequirementHadfix;
	}
	
	public void setRequirementHadfix(boolean hadfix)
	{
		this.mRequirementHadfix = hadfix;
	}
	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
}
