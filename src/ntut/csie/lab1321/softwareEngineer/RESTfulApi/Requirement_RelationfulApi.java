package ntut.csie.lab1321.softwareEngineer.RESTfulApi;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import java.util.ArrayList;

import javax.ws.rs.DELETE;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ntut.csie.lab1321.softwareEngineer.json.JSONArray;
import ntut.csie.lab1321.softwareEngineer.json.JSONObject;
import ntut.csie.lab1321.softwareEngineer.dao.Requirement_RelationDAO;
import ntut.csie.lab1321.softwareEngineer.dao.RequirementDAO;
import ntut.csie.lab1321.softwareEngineer.model.Requirement;
import ntut.csie.lab1321.softwareEngineer.model.Requirement_Relation;

@Path("projects/{projectId}/requirement_relations")
public class Requirement_RelationfulApi {
	/*@POST
	@Produces(MediaType.APPLICATION_JSON)
	
	public Response changeRelations(@PathParam("projectId") int projectId, ArrayList<String> entity,int rid){
		//�P�_rid�O�_�s�b
		Requirement _requirement = RequirementDAO.getInstance().getRequirementByrId(rid, projectId);
		if(_requirement == null){
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		//�T�wArrayList<String> entity�ҿ�JId�b�P�@Project
		for(String _entity :entity){
			JSONObject json = new JSONObject(_entity);
			ArrayList<Requirement> requirements = RequirementDAO.getInstance().getRequirements(projectId);
			boolean relation_RequirementIdSuccess = false;
			for(Requirement requirement : requirements){
				if(json.getInt("requirmentid") ==requirement.getId()){
					relation_RequirementIdSuccess = true;
				}
			}
			if(!relation_RequirementIdSuccess){
				JSONObject response = new JSONObject();
				response.put("message", "NO FIND RequirementId:" + json.getInt("requirmentid") + " in this Project.");
				response.put("status_code", 1);
				String entityResponse = response.toString();
				return Response.status(Response.Status.NOT_ACCEPTABLE).entity(entityResponse).build();
			}
		}
		//�P�_���Lrid����ƦbRequirement_RelationDB��		
		ArrayList<Requirement_Relation> requirementrelations = Requirement_RelationDAO.getInstance().getRelationsByRequirementId(rid);
		//�L��ơAentity���s�W�iDB
		if(requirementrelations == null){
			for(String _entity :entity){
				JSONObject json = new JSONObject(_entity);
				Requirement_Relation newrelation = new Requirement_Relation(rid);
				newrelation.setRelationId(json.getInt("relation_id"));
				boolean status = Requirement_RelationDAO.getInstance().creatRequirementRelation(newrelation, rid);
				if(!status){
					JSONObject response = new JSONObject();
					response.put("message", "Create Relation Id:"+json.getInt("relation_id")+" Fail");
					response.put("status_code", 2);
					String entityResponse = response.toString();
					return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(entityResponse).build();
					}
				}
			JSONObject response = new JSONObject();
			response.put("message", "Change Relation success");
			response.put("status_code", 3);
			String entityResponse = response.toString();
			return Response.status(Response.Status.OK).entity(entityResponse).build();
			}
		//����ơCDB���Bentity�L�A�R��DB���;DB�L�Bentity���A�s�W��ơC
		else{
			//DB���Bentity�L�A�j�M
			for(Requirement_Relation requirementrelation : requirementrelations){
				boolean havetodelete = true;				
				for(String _entity :entity){
					JSONObject json = new JSONObject(_entity);
					if(requirementrelation.getRelationId() == json.getInt("relation_id")){
						havetodelete = false;
					}
				}
				if(havetodelete){
					boolean isDeleteSuccess = Requirement_RelationDAO.getInstance().deleteone(requirementrelation.getId());
					if (!isDeleteSuccess) {
						return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
					}
				}
			}
			for(String _entity :entity){
				JSONObject json = new JSONObject(_entity);
				boolean havecreat = true;
				for(Requirement_Relation requirementrelation : requirementrelations){
					if(json.getInt("relation_id") == requirementrelation.getRelationId()){
						havecreat = false;
					}
				}
				if(havecreat){
					Requirement_Relation newrelation = new Requirement_Relation(rid);
					newrelation.setRelationId(json.getInt("relation_id"));
				}
			}
			JSONObject response = new JSONObject();
			response.put("message", "Change Relation success");
			response.put("status_code", 3);
			String entityResponse = response.toString();
			return Response.status(Response.Status.OK).entity(entityResponse).build(); 
		}
	}
	*/
	/*@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRelations(int requirementId){		
		ArrayList<Requirement_Relation> requirementrelations = Requirement_RelationDAO.getInstance().getRelationsByRequirementId(requirementId);
		JSONArray relationsJSON = new JSONArray();
		for(Requirement_Relation requirementrelation : requirementrelations){
			JSONObject relationJSON = new JSONObject();
			relationJSON.put("id", requirementrelation.getId());
			relationJSON.put("rid", requirementrelation.getRequirementId());
			relationJSON.put("relation_id",requirementrelation.getRelationId());
			relationsJSON.put(relationJSON);
		}
		if (relationsJSON.length() == 0) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		return Response.status(Response.Status.OK).entity(relationsJSON.toString()).build();
	}*/
	/*
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteRelations(@PathParam("requirementId") int requirementId){
		ArrayList<Requirement_Relation> requirementrelations = Requirement_RelationDAO.getInstance().getRelationsByRequirementId(requirementId);
		if(requirementrelations == null){
			JSONObject response = new JSONObject();
			response.put("message", "Requirement_relation is not found");
			response.put("status_code", 4);
			String entityResponse = response.toString();
			return Response.status(Response.Status.NOT_FOUND).entity(entityResponse).build();
		}
		boolean isDeleteSuccess = Requirement_RelationDAO.getInstance().deletemanny(requirementId);
		if (!isDeleteSuccess) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
		JSONObject response = new JSONObject();
		response.put("message", "success delete Requirement_relation");
		response.put("status_code", 5);
		String entityResponse = response.toString();
		return Response.status(Response.Status.OK).entity(entityResponse).build();
	}*/
}
