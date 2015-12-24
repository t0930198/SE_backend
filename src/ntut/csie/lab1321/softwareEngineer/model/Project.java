package ntut.csie.lab1321.softwareEngineer.model;

public class Project {
	private int mId = -1;
	private String mName = "";
    private String mNote = "";
    
    public Project(int id){
    	mId = id;
    }
    public Project(int id, String projectName){
    	mName = projectName;
    	mId = id;
    }
    
	public int getmId() {
		return mId;
	}
	public void setmId(int mId) {
		this.mId = mId;
	}
	public String getName() {
		return mName;
	}
	public void setName(String name) {
		this.mName = name;
	}
	public String getNote() {
		return mNote;
	}
	public void setNote(String note) {
		this.mNote = note;
	}
    
}
