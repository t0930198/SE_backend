package ntut.csie.lab1321.softwareEngineer.model;

public class Requirement_Relation {
	private int mid = -1;
	private int mrid = -1;
	private int mrelationid = -1;
	
	public Requirement_Relation(int rid){
		mrid = rid;
	}
	
	public int getId(){
		return mid;
	}
	public void setId(int id){
		mid=id;
	}
	
	public int getRequirementId(){
		return mrid;
	}
	public void setRequirementId(int rid){
		mrid = rid;
	}
	
	public int getRelationId(){
		return mrelationid;
	}
	public void setRelationId(int relation_id){
		mrelationid = relation_id;
	}
	
}
