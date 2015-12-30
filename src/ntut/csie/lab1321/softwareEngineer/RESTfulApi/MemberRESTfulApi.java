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

@Path("member")
public class MemberRESTfulApi {
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response createMember(String entity){
		JSONObject json = new JSONObject(entity);
		Member member = MemberDAO.getInstance().getMemberByProjectId(json.getInt("project_id"), json.getInt("user_id"));
		if(member != null){
			JSONObject response = new JSONObject();
			response.put("message", "member EXISTED");
			response.put("status_code", 1);
			String entityResponse = response.toString();
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(entityResponse).build();
		}
		member = new Member(json.getInt("project_id"));
		member.setUserId(json.getInt("user_id"));
		member.setRole(json.getString("role"));
		member.setUsername(json.getString("username"));
		boolean status = MemberDAO.getInstance().createMemberDAO(member);
		if(status){
			JSONObject response = new JSONObject();
			response.put("message", "Create Member success");
			response.put("status_code", 3);
			String entityResponse = response.toString();
			return Response.status(Response.Status.OK).entity(entityResponse).build();
		}
		else{
			JSONObject response = new JSONObject();
			response.put("message", "Create Member Fail");
			response.put("status_code", 2);
			String entityResponse = response.toString();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(entityResponse).build();
		}
	}
	@GET
	@Path("/{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProjectsByUserId(@PathParam("userId") int userId, String entity){
		
		ArrayList<Member> members = MemberDAO.getInstance().getMembersByUserId(userId);
		JSONArray membersJSON = new JSONArray();
		for(Member member : members){
			JSONObject memberJSON = new JSONObject();
			memberJSON.put("id", member.getId());
			memberJSON.put("project_id", member.getProjectId());
			memberJSON.put("user_id", member.getUserId());
			memberJSON.put("role", member.getRole());
			memberJSON.put("username", member.getUsername());
			membersJSON.put(memberJSON);
		}
		if(membersJSON.length() == 0){
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		return Response.status(Response.Status.OK).entity(membersJSON.toString()).build();
	}
}
