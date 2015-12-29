package ntut.csie.lab1321.softwareEngineer.RESTfulApi;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ntut.csie.lab1321.softwareEngineer.dao.ProjectDAO;
import ntut.csie.lab1321.softwareEngineer.json.JSONException;
import ntut.csie.lab1321.softwareEngineer.json.JSONObject;
import ntut.csie.lab1321.softwareEngineer.model.Project;

@Path("projects")
public class ProjectRESTApi {
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response createProject(String entity){
		JSONObject json = new JSONObject(entity);
		Project project = ProjectDAO.getInstance().getProjectByName(json.getString("name"));
		if(project != null){
			JSONObject response = new JSONObject();
			response.put("message", "PROJECT EXISTED");
			response.put("status_code", 1);
			String entityResponse = response.toString();
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(entityResponse).build();
		}
		project = new Project(json.getString("name"));
		project.setNote(json.getString("notes"));
		boolean status = ProjectDAO.getInstance().createProject(project);
		if(status){
			JSONObject response = new JSONObject();
			response.put("message", "Create Project success");
			response.put("status_code", 3);
			String entityResponse = response.toString();
			return Response.status(Response.Status.OK).entity(entityResponse).build();
		}
		else{
			JSONObject response = new JSONObject();
			response.put("message", "Create Project Fail");
			response.put("status_code", 2);
			String entityResponse = response.toString();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(entityResponse).build();
		}
	}
	
	@GET
	@Path("/{projectId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProject(@PathParam("projectId") int project_id) {
		JSONObject projectJSON = new JSONObject();
		Project project = ProjectDAO.getInstance().getProjectById(project_id);
		if (project == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}

		projectJSON.put("id", project.getId());
		projectJSON.put("name", project.getName());
		projectJSON.put("note", project.getNote());
		return Response.status(Response.Status.OK).entity(projectJSON.toString()).build();

	}
	
	@PUT
	@Path("/{projectId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateProject(@PathParam("projectId") int projectid,String entity){
		Project project = ProjectDAO.getInstance().getProjectById(projectid);
		if(project == null){
			JSONObject response = new JSONObject();
			response.put("message", "Project is not found");
			response.put("status_code", 4);
			String entityResponse = response.toString();
			return Response.status(Response.Status.NOT_FOUND).entity(entityResponse).build();
		}
		
		try{
			JSONObject projectJSON = new JSONObject(entity);
			project.setName(projectJSON.getString("name"));
			project.setNote(projectJSON.getString("notes"));
			ProjectDAO.getInstance().updateProject(project, projectid);
			return Response.status(Response.Status.OK).entity(entity).build();
		}catch(JSONException e){
			return Response.status(Response.Status.BAD_REQUEST).entity(entity).build();
		}
	}
	@DELETE
	@Path("/{projectId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteProject(@PathParam("projectId") int projectid){
		Project project = ProjectDAO.getInstance().getProjectById(projectid);
		if(project == null){
			JSONObject response = new JSONObject();
			response.put("message", "Project is not found");
			response.put("status_code", 4);
			String entityResponse = response.toString();
			return Response.status(Response.Status.NOT_FOUND).entity(entityResponse).build();
		}
		boolean isDeleteSuccess = ProjectDAO.getInstance().delete(projectid);
		if (!isDeleteSuccess) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
		JSONObject response = new JSONObject();
		response.put("message", "success delete project");
		response.put("status_code", 5);
		String entityResponse = response.toString();
		return Response.status(Response.Status.OK).entity(entityResponse).build();
	}
}
