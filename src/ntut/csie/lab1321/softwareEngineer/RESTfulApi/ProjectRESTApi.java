package ntut.csie.lab1321.softwareEngineer.RESTfulApi;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ntut.csie.lab1321.softwareEngineer.dao.ProjectDAO;
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
			response.put("message", "Create Account");
			response.put("status_code", 3);
			String entityResponse = response.toString();
			return Response.status(Response.Status.OK).entity(entityResponse).build();
		}
		else{
			JSONObject response = new JSONObject();
			response.put("message", "Create Account Fail");
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
	/*
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateProject(int projectid,String entity){
		Project project = ProjectDAO.getInstance().getProjectById(projectid);
		if(project == null){
			return Response.status(Response.Status.NOT_FOUND).entity(entity).build();
		}
		try{
			JSONObject projectJSON = new JSONObject();
			project.setName(projectJSON.getString("name"));
			project.setNote(projectJSON.getString("notes"));
			return Response.status(Response.Status.OK).entity(entity).build();
		}catch(JSONException e){
			return Response.status(Response.Status.BAD_REQUEST).entity(entity).build();
		}
	}*/
}
