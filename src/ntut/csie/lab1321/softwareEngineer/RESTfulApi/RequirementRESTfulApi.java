package ntut.csie.lab1321.softwareEngineer.RESTfulApi;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ntut.csie.lab1321.softwareEngineer.dao.RequirementDAO;
import ntut.csie.lab1321.softwareEngineer.json.JSONException;
import ntut.csie.lab1321.softwareEngineer.json.JSONObject;
import ntut.csie.lab1321.softwareEngineer.model.Requirement;

@Path("requirements")
public class RequirementRESTfulApi {
//	@GET
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response getRequirements(){
//		//TODO
//	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response createResponse(String entity){
		JSONObject json = new JSONObject(entity);
		Requirement requirement = new Requirement(json.getString("name"));
		requirement.setmReqiredescription(json.getString("description"));
		requirement.setmReqirestartime(json.getLong("star_time"));
		requirement.setmReqirecommand(json.getString("command"));	
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
			requirementJSON.put("name", requirement.getmReqirename());
			requirementJSON.put("description", requirement.getmReqiredescription());
			requirementJSON.put("star_time", requirement.getmReqirestartime());
			requirementJSON.put("command", requirement.getmReqirecommand());
			String entity = requirementJSON.toString();		   
			return Response.status(Response.Status.OK).entity(entity).build();
		}
		return Response.status(Response.Status.NOT_FOUND).build();
	}
	
	@PUT
	@Path("{requirementId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateRequirement(@PathParam("requirementId") int requirementId, String entity){
		Requirement requirement = RequirementDAO.getInstance().getRequirementByrId(requirementId);
		if(requirement == null)
			return Response.status(Response.Status.NOT_FOUND).entity(entity).build();
		try{
			JSONObject requirementJSON = new JSONObject();
			requirement.setmReqirename(requirementJSON.getString("name"));
			requirement.setmReqiredescription(requirementJSON.getString("description"));
			requirement.setmReqirecommand(requirementJSON.getString("command"));
			return Response.status(Response.Status.OK).entity(entity).build();
		}catch(JSONException e){
			return Response.status(Response.Status.BAD_REQUEST).entity(entity).build();
		}
	}
}
