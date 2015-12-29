package ntut.csie.lab1321.softwareEngineer.RESTfulApi;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ntut.csie.lab1321.softwareEngineer.dao.MemberDAO;
import ntut.csie.lab1321.softwareEngineer.json.JSONArray;
import ntut.csie.lab1321.softwareEngineer.json.JSONObject;
import ntut.csie.lab1321.softwareEngineer.model.Member;

@Path("memmber")
public class MemberRESTfulApi {
	
	@GET
	@Path("/{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProjectsByUserId(@PathParam("userId") int userId, String entity){
		JSONObject memberJSON = new JSONObject();
		ArrayList<Member> members = MemberDAO.getInstance().getMembersByUserId(userId);
		JSONArray membersJSON = new JSONArray();
		for(Member member : members){
			memberJSON.put("id", member.getId());
			memberJSON.put("project_id", member.getProjectId());
			memberJSON.put("user_id", member.getUserId());
			memberJSON.put("role", member.getRole());
			membersJSON.put(memberJSON);
		}
		if(membersJSON.length() == 0){
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		return Response.status(Response.Status.OK).entity(membersJSON.toString()).build();
	}
}
