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
		@GET
		@Produces(MediaType.APPLICATION_JSON)
		public Test getTests(){
			JSONObject requirementJSON = new JSONObject();
			ArrayList<Test> tests = TestDAO.getInstance().getTests();
			JSONArray TestsJSON = new JSONArray();
			for(Test)
		}
}
