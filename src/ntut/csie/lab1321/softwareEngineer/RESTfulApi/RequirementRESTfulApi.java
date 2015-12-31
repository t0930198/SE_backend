package ntut.csie.lab1321.softwareEngineer.RESTfulApi;

import java.util.ArrayList;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ntut.csie.lab1321.softwareEngineer.dao.RequirementDAO;
import ntut.csie.lab1321.softwareEngineer.json.JSONArray;
import ntut.csie.lab1321.softwareEngineer.json.JSONException;
import ntut.csie.lab1321.softwareEngineer.json.JSONObject;
import ntut.csie.lab1321.softwareEngineer.model.Requirement;

@Path("projects/{projectId}/requirements")
public class RequirementRESTfulApi {
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response createRequirement(@PathParam("projectId") int projectId, String entity){
		JSONObject json = new JSONObject(entity);
		Requirement requirement = RequirementDAO.getInstance().getRequirementByName(json.getString("name"), projectId);
		if(requirement != null){
			JSONObject response = new JSONObject();
			response.put("message", "PROJECT EXISTED");
			response.put("status_code", 1);
			String entityResponse = response.toString();
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(entityResponse).build();
		}
		requirement = new Requirement(json.getString("name"));
		requirement.setRequirementDescription(json.getString("description"));
		requirement.setRequirementStartTime(System.currentTimeMillis());
		requirement.setmRequirementComment(json.getString("comment"));	
		requirement.setRequirementHadfix(true);
		requirement.setProjectId(projectId);
		boolean status = RequirementDAO.getInstance().creatRequirement(requirement, projectId);
		if(status){
			JSONObject response = new JSONObject();
			response.put("message", "Create Requirement success");
			response.put("status_code", 3);
			String entityResponse = response.toString();
			return Response.status(Response.Status.OK).entity(entityResponse).build();
		}
		else{
			JSONObject response = new JSONObject();
			response.put("message", "Create Requirement Fail");
			response.put("status_code", 2);
			String entityResponse = response.toString();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(entityResponse).build();
		}
	}
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRequirements(@PathParam("projectId") int projectId){
		
		ArrayList<Requirement> requirements = RequirementDAO.getInstance().getRequirements(projectId);
		JSONArray requirementsJSON = new JSONArray();
		for(Requirement requirement : requirements){
			JSONObject requirementJSON = new JSONObject();
			requirementJSON.put("id", requirement.getId());
			//System.out.println("id:"+requirement.getId());
			requirementJSON.put("name", requirement.getRequirementName());
			requirementJSON.put("description", requirement.getRequirementDescription());
			requirementJSON.put("status", requirement.getRequirementType());
			requirementJSON.put("comment", requirement.getRequirementComment());
			requirementJSON.put("starttime", requirement.getRequirementStartTime());
			requirementJSON.put("hadfix", requirement.getRequirementHadfix());
			requirementsJSON.put(requirementJSON);
		}
//		for(int i =0; i<requirements.size(); i++ ){
//			requirements.get(i);
//		}
		if (requirementsJSON.length() == 0) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		return Response.status(Response.Status.OK).entity(requirementsJSON.toString()).build();
	}

	@GET
	@Path("/{requirementId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRequirement(@PathParam("requirementId") int requirementId, @PathParam("projectId") int projectId){
		Requirement requirement = RequirementDAO.getInstance().getRequirementByrId(requirementId, projectId);
		if (requirement != null){
			JSONObject requirementJSON = new JSONObject();
			requirementJSON.put("id", requirement.getId());
			requirementJSON.put("name", requirement.getRequirementName());
			requirementJSON.put("description", requirement.getRequirementDescription());
			requirementJSON.put("comment", requirement.getRequirementComment());
			requirementJSON.put("star_time", requirement.getRequirementStartTime());
			requirementJSON.put("hadfix", requirement.getRequirementHadfix());
			String entity = requirementJSON.toString();		   
			return Response.status(Response.Status.OK).entity(entity).build();
		}
		return Response.status(Response.Status.NOT_FOUND).build();
	}
	
	@PUT
	@Path("/{requirementId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateRequirement(@PathParam("requirementId") int requirementId, @PathParam("projectId") int projectId ,String entity){
		Requirement requirement = RequirementDAO.getInstance().getRequirementByrId(requirementId, projectId);
		if(requirement == null){
			JSONObject response = new JSONObject();
			response.put("message", "Requirement is not found");
			response.put("status_code", 4);
			String entityResponse = response.toString();
			return Response.status(Response.Status.NOT_FOUND).entity(entityResponse).build();
			}
		try{
			JSONObject requirementJSON = new JSONObject(entity);
			requirement.setRequirementName(requirementJSON.getString("name"));
			requirement.setRequirementDescription(requirementJSON.getString("description"));
			requirement.setmRequirementComment(requirementJSON.getString("comment"));
			requirement.setRequirementType(requirementJSON.getString("Type"));
			requirement.setRequirementHadfix(requirementJSON.getBoolean("hadfix"));
			RequirementDAO.getInstance().updateRequirement(requirement, requirementId, requirementJSON.getBoolean("hadfix"), projectId);
			return Response.status(Response.Status.OK).entity(entity).build();
		}catch(JSONException e){
			return Response.status(Response.Status.BAD_REQUEST).entity(entity).build();
		}
		//return Response.status(Response.Status.BAD_REQUEST).entity(entity).build();
		 
	}
	
	@DELETE
	@Path("/{requirementId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteRequirement(@PathParam("requirementId") int requirementId, @PathParam("projectId") int projectId) {
		Requirement requirement = RequirementDAO.getInstance().getRequirementByrId(requirementId, projectId);
		if (requirement == null) {
			JSONObject response = new JSONObject();
			response.put("message", "Requirement is not found");
			response.put("status_code", 4);
			String entityResponse = response.toString();
			return Response.status(Response.Status.NOT_FOUND).entity(entityResponse).build();		}
		boolean isDeleteSuccess = RequirementDAO.getInstance().delete(requirementId);
		if (!isDeleteSuccess) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
		JSONObject response = new JSONObject();
		response.put("message", "success delete requirement");
		response.put("status_code", 5);
		String entityResponse = response.toString();
		return Response.status(Response.Status.OK).entity(entityResponse).build();
	}
}
