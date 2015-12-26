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
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRequirements(){
		//TODO
		JSONObject requirementJSON = new JSONObject();
		ArrayList<Requirement> requirements = RequirementDAO.getInstance().getRequirements();
		JSONArray requirementsJSON = new JSONArray();
		for(Requirement requirement : requirements){
			requirementJSON.put("id", requirement.getId());
			requirementJSON.put("name", requirement.getRequirementName());
			requirementJSON.put("description", requirement.getRequirementDescription());
			requirementJSON.put("status", requirement.getRequirementType());
			requirementJSON.put("starttime", requirement.getRequirementStartTime());
			requirementJSON.put("command", requirement.getRequirementCommand());
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

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response createRequirement(String entity){
		JSONObject json = new JSONObject(entity);
		Requirement requirement = new Requirement(json.getString("name"));
		requirement.setRequirementDescription(json.getString("description"));
		requirement.setRequirementStartTime(json.getLong("star_time"));
		requirement.setmRequirementCommand(json.getString("command"));	
		requirement.setRequirementHadfix(true);
		RequirementDAO.getInstance().creatRequirement(requirement);
		
		return Response.status(Response.Status.OK).entity("GET").build();
	}
	
	@GET
	@Path("/{requirementId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRequirement(@PathParam("requirementId") int requirementId){
		Requirement requirement = RequirementDAO.getInstance().getRequirementByrId(requirementId);
		if (requirement != null){
			JSONObject requirementJSON = new JSONObject();
			requirementJSON.put("id", requirement.getId());
			requirementJSON.put("name", requirement.getRequirementName());
			requirementJSON.put("description", requirement.getRequirementDescription());
			requirementJSON.put("star_time", requirement.getRequirementStartTime());
			requirementJSON.put("command", requirement.getRequirementCommand());
			requirementJSON.put("hadfix", requirement.getRequirementHadfix());
			String entity = requirementJSON.toString();		   
			return Response.status(Response.Status.OK).entity(entity).build();
		}
		return Response.status(Response.Status.NOT_FOUND).build();
	}
	
	@PUT
	@Path("/{requirementId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateRequirement(@PathParam("requirementId") int requirementId, String entity){
		Requirement requirement = RequirementDAO.getInstance().getRequirementByrId(requirementId);
		if(requirement == null)
			return Response.status(Response.Status.NOT_FOUND).entity(entity).build();
		try{
			JSONObject requirementJSON = new JSONObject();
			requirement.setRequirementName(requirementJSON.getString("name"));
			requirement.setRequirementDescription(requirementJSON.getString("description"));
			requirement.setmRequirementCommand(requirementJSON.getString("command"));
			requirement.setRequirementHadfix(requirementJSON.getBoolean("hadfix"));
			return Response.status(Response.Status.OK).entity(entity).build();
		}catch(JSONException e){
			return Response.status(Response.Status.BAD_REQUEST).entity(entity).build();
		}
	}
	@DELETE
	@Path("/{requirementId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteRequirement(@PathParam("requirementId") int requirementId, String entity) {
		Requirement requirement = RequirementDAO.getInstance().getRequirementByrId(requirementId);
		if (requirement == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		boolean isDeleteSuccess = RequirementDAO.getInstance().delete(requirementId);
		if (!isDeleteSuccess) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
		return Response.status(Response.Status.OK).entity("success delete requirement").build();
	}
}
