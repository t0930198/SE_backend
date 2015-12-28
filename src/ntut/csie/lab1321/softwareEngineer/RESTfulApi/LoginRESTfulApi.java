package ntut.csie.lab1321.softwareEngineer.RESTfulApi;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ntut.csie.lab1321.softwareEngineer.dao.AccountDAO;
import ntut.csie.lab1321.softwareEngineer.json.JSONObject;
import ntut.csie.lab1321.softwareEngineer.model.Account;
@Path("Login")
public class LoginRESTfulApi {

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response AccountLogin(String entity){
		JSONObject json = new JSONObject(entity);
		Account account = AccountDAO.getInstance().getAccountByName(json.getString("account"));
		//比對帳號，無此帳號
		if(account == null){
			JSONObject response = new JSONObject();
			response.put("message", "USERNAME IS WRONG");
			response.put("status_code", 5);
			String entityResponse = response.toString();
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(entityResponse).build();
		}
		//比對此帳號下的PASSWORD，PASSWORD錯誤
		if(!account.getmPassword().equals(json.getString("password"))){
			JSONObject response = new JSONObject();
			response.put("message", "PASSWORD IS WRONG");
			response.put("status_code", 6);
			String entityResponse = response.toString();
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(entityResponse).build();
		}
		JSONObject accountJSON = new JSONObject();
		accountJSON.put("id", account.getId());
		accountJSON.put("username", account.getmUsername());
		accountJSON.put("password", account.getmPassword());
		accountJSON.put("email", account.getmEmail());
		String _entity = accountJSON.toString();		   
		return Response.status(Response.Status.OK).entity(_entity).build();
	}
}
