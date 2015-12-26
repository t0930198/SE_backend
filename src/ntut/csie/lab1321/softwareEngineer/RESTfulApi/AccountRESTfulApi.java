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
		// 創立帳號的時候, 如果有重複創建就擋掉
		Account account = AccountDAO.getInstance().getAccountByName(json.getString("username"));
		if(account != null){
			JSONObject response = new JSONObject();
			response.put("message", "ACCOUNT EXISTED");
			response.put("status_code", 1);
			String entityResponse = response.toString();
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(entityResponse).build();
		}
		// 如果沒有重複創帳號, 則可創立新帳號
		account = new Account(json.getString("username"));
		account.setmEmail(json.getString("email"));
		account.setmPassword(json.getString("password"));
		
		boolean status = AccountDAO.getInstance().createAccount(account);
		if(status){
			JSONObject response = new JSONObject();
			response.put("message", "Create Account");
			response.put("status_code", 3);
			String entityResponse = response.toString();
			return Response.status(Response.Status.OK).entity(entityResponse).build();
		} else {
			JSONObject response = new JSONObject();
			response.put("message", "Create Account Fail");
			response.put("status_code", 2);
			String entityResponse = response.toString();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(entityResponse).build();
		}
		
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
		if(account == null){
			JSONObject response = new JSONObject();
			response.put("message", "Account is not found");
			response.put("status_code", 4);
			String entityResponse = response.toString();
			return Response.status(Response.Status.NOT_FOUND).entity(entityResponse).build();
		}
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
