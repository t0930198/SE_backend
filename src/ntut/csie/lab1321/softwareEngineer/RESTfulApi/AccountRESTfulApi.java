package ntut.csie.lab1321.softwareEngineer.RESTfulApi;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ntut.csie.lab1321.softwareEngineer.dao.AccountDAO;
import ntut.csie.lab1321.softwareEngineer.json.JSONException;
import ntut.csie.lab1321.softwareEngineer.json.JSONObject;
import ntut.csie.lab1321.softwareEngineer.model.Account;

@Path("accounts")
public class AccountRESTfulApi {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAccounts(){
//		Account account = AccountDAO.getInstance().getAccountById(accountId);
//		JSONObject accountJSON = new JSONObject();
//		accountJSON.put("id", account.getId());
//		accountJSON.put("username", account.getmUsername());
//		accountJSON.put("password", account.getmPassword());
//		accountJSON.put("email", account.getmEmail());
		//String entity = accountJSON.toString();		   
		return Response.status(Response.Status.OK).entity("").build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response createAccount(String entity){
		JSONObject json = new JSONObject(entity);
		Account account = new Account(json.getString("username"));
		account.setmEmail(json.getString("email"));
		account.setmPassword(json.getString("password"));
		AccountDAO.getInstance().creatAccount(account);
		
		return Response.status(Response.Status.OK).entity("GET").build();
	}
	
	@GET
	@Path("/{accountId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAccount(@PathParam("accountId") int accountId){
		Account account = AccountDAO.getInstance().getAccountById(accountId);
		if (account != null) {
			JSONObject accountJSON = new JSONObject();
			accountJSON.put("id", account.getId());
			accountJSON.put("username", account.getmUsername());
			accountJSON.put("password", account.getmPassword());
			accountJSON.put("email", account.getmEmail());
			String entity = accountJSON.toString();		   
			return Response.status(Response.Status.OK).entity(entity).build();
		}	   
		return Response.status(Response.Status.NOT_FOUND).build();
	}
	
	@PUT
	@Path("{accountId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateAccount(@PathParam("accountId") int accountId, String entity){
		Account account = AccountDAO.getInstance().getAccountById(accountId);
		if(account == null)
			return Response.status(Response.Status.NOT_FOUND).entity(entity).build();
		try{
			JSONObject accountJson = new JSONObject(entity);
			account.setmUsername(accountJson.getString("username"));
			account.setmPassword(accountJson.getString("password"));
			account.setmEmail(accountJson.getString("email"));
			AccountDAO.getInstance().updateAccount(account, accountId);
			return Response.status(Response.Status.OK).entity(entity).build();
		}catch(JSONException e){
			return Response.status(Response.Status.BAD_REQUEST).entity(entity).build();
		}
	}
	
}
