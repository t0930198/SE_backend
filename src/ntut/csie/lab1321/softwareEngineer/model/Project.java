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
    public Project(String projectName){
    	if(mName != null && mName != ""){
    		mName = projectName;
		}else {
			throw new RuntimeException();
		}
    }
	public int getId() {
		return mId;
	}
	public void setId(int id) {
		this.mId = id;
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
