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

import ntut.csie.lab1321.softwareEngineer.json.JSONArray;
import ntut.csie.lab1321.softwareEngineer.json.JSONException;
import ntut.csie.lab1321.softwareEngineer.json.JSONObject;
import ntut.csie.lab1321.softwareEngineer.dao.TestDAO;
import ntut.csie.lab1321.softwareEngineer.model.Test;
@Path("projects/{projectId}/tests")
public class TestRESTfulApi {
		@POST
		@Produces
		public Response createTest(String entity){
			JSONObject json = new JSONObject(entity);
			Test test = TestDAO.getInstance().getTestByName(json.getString("name"));
			if(test != null){
				JSONObject response = new JSONObject();
				response.put("message", "TEST EXISTED");
				response.put("status_code", 1);
				String entityResponse = response.toString();
				return Response.status(Response.Status.NOT_ACCEPTABLE).entity(entityResponse).build();
			}
			test = new Test(json.getString("name"));
			test.setTestDescription(json.getString("description"));
			boolean status =TestDAO.getInstance().creatTest(test);
			if(status){
				JSONObject response = new JSONObject();
				response.put("message", "Create Test success");
				response.put("status_code", 3);
				String entityResponse = response.toString();
				return Response.status(Response.Status.OK).entity(entityResponse).build();
			}
			else{
				JSONObject response = new JSONObject();
				response.put("message", "Create Test Fail");
				response.put("status_code", 2);
				String entityResponse = response.toString();
				return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(entityResponse).build();
			}
		}
		@GET
		@Path("/{testId}")
		@Produces(MediaType.APPLICATION_JSON)
		public Response getTests(@PathParam("testId")int test_id){
			JSONObject testJSON = new JSONObject();
			ArrayList<Test> tests = TestDAO.getInstance().getTests();
			JSONArray testsJSON = new JSONArray();
			for(Test test : tests){
				testJSON.put("id",test.getTestId());
				testJSON.put("name",test.getTestName());
				testJSON.put("description", test.getTestDescription());
				testJSON.put("testrid", test.getTestRid());
				testsJSON.put(test);				
			}
			if(testsJSON.length()==0){
				return Response.status(Response.Status.NOT_FOUND).build();
			}
			return Response.status(Response.Status.OK).entity(testsJSON.toString()).build();
		}
		
		@GET
		@Path("/{testId}")
		@Produces(MediaType.APPLICATION_JSON)
		public Response getTest(@PathParam("testId")int test_id){
			JSONObject testJSON = new JSONObject();
			Test test = TestDAO.getInstance().getTestByID(test_id);
			if(test == null){
				return Response.status(Response.Status.NOT_FOUND).build();
			}
			testJSON.put("id",test.getTestId());
			testJSON.put("name",test.getTestName());
			testJSON.put("description", test.getTestDescription());
			testJSON.put("testrid", test.getTestRid());
			return Response.status(Response.Status.OK).entity(testJSON.toString()).build();
		}
		
		@PUT
		@Path("/{testId}")
		@Produces(MediaType.APPLICATION_JSON)
		public Response updateTest(@PathParam("testId") int testid,String entity){
			Test test = TestDAO.getInstance().getTestByID(testid);
			if(test == null){
				JSONObject response = new JSONObject();
				response.put("message", "Test is not found");
				response.put("status_code", 4);
				String entityResponse = response.toString();
				return Response.status(Response.Status.NOT_FOUND).entity(entityResponse).build();
			}
			
			try{
				JSONObject testJSON = new JSONObject(entity);
				test.setTestName(testJSON.getString("name"));
				test.setTestDescription(testJSON.getString("description"));
				test.setTestRid(testJSON.getInt("testrid"));
				TestDAO.getInstance().updateTest(test, testid);
				return Response.status(Response.Status.OK).entity(entity).build();

			}catch(JSONException e){
				return Response.status(Response.Status.BAD_REQUEST).entity(entity).build();
			}
		}
		
		@DELETE
		@Path("/{testId}")
		@Produces(MediaType.APPLICATION_JSON)
		public Response deleteTest(@PathParam("testId") int testid){
			Test test = TestDAO.getInstance().getTestByID(testid);
			if(test == null){
				JSONObject response = new JSONObject();
				response.put("message", "Test is not found");
				response.put("status_code", 4);
				String entityResponse = response.toString();
				return Response.status(Response.Status.NOT_FOUND).entity(entityResponse).build();
			}
			boolean isDeleteSuccess = TestDAO.getInstance().delete(testid);
			if(!isDeleteSuccess){
				return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
			}
			JSONObject response = new JSONObject();
			response.put("message", "success delete test");
			response.put("status_code", 5);
			String entityResponse = response.toString();
			return Response.status(Response.Status.OK).entity(entityResponse).build();
		}
}