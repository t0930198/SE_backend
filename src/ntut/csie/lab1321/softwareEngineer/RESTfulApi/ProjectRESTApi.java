package ntut.csie.lab1321.softwareEngineer.RESTfulApi;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import ntut.csie.lab1321.softwareEngineer.dao.ProjectDAO;
import ntut.csie.lab1321.softwareEngineer.model.Project;
import ntut.csie.lab1321.softwareEngineer.json.JSONException;
import ntut.csie.lab1321.softwareEngineer.json.JSONObject;

@Path("projects")
public class ProjectRESTApi {
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response createProject(String entity){
		JSONObject json = new JSONObject(entity);
		Project project = new Project(json.getInt("id"),json.getString("name"));
		project.setNote(json.getString("notes"));
		ProjectDAO.getInstance().createProject(project);
		return Response.status(Response.Status.OK).entity("GET").build();
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
