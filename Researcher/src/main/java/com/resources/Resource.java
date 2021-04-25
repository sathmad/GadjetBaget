package com.resources;

import com.service.Service;
//import com.service.doctor.doctorService;
import com.model.DBconnection;

import java.sql.SQLException;

//import javax.annotation.security.RolesAllowed;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.research;
//For JSON
import com.google.gson.*;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

@Path("/Researchers")
public class Resource {
	

	
		research Res = new research();

		//Get 
		@GET
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_HTML)
		//Review data
		public String readResearcher() {
			Service Res = new Service();

			String output = Res.readResearcher();
			return output;

		}
		

		//Post
		@POST
		@Path("/") 
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		
		//Insert Data
		public String insertResearcher(String resData) {
			// Convert the input string to a JSON object
			JsonObject ResObj = new JsonParser().parse(resData).getAsJsonObject();
			
			// Read the values for variable from the JSON object
			String researchID = ResObj.get("researchID").getAsString();
			String researchTopic = ResObj.get("researchTopic").getAsString();
			String description = ResObj.get("description").getAsString();
			String researcherID = ResObj.get("researcherID").getAsString();
			Float cost = ResObj.get("cost").getAsFloat();
			
			
			Service resobject1 = new Service();
						
			Res.setResearchID(researchID);
			Res.setResearchTopic(researchTopic);
			Res.setDescription(description);
			Res.setResearcherID(researcherID);
			Res.setCost(cost);
			

			String output = resobject1.insertResearcher(Res);
			return output;
		}
		
		@PUT
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		
		//Update Data
		public String updateResearcher(String resData) throws SQLException {
			// Convert the input string to a JSON object
			JsonObject ResObj = new JsonParser().parse(resData).getAsJsonObject();
			
			// Read the values for variables from the JSON object

			String researchID = ResObj.get("researchID").getAsString();
			String researchTopic = ResObj.get("researchTopic").getAsString();
			String description = ResObj.get("description").getAsString();
			String researcherID = ResObj.get("researcherID").getAsString();
			Float cost = ResObj.get("cost").getAsFloat();
			

			Service resobject2 = new Service();
			
			Res.setResearchID(researchID);
			Res.setResearchTopic(researchTopic);
			Res.setDescription(description);
			Res.setResearcherID(researcherID);
			Res.setCost(cost);

			String output = resobject2.updateResearcher(Res);
			return output;
		}
		
		@DELETE
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		
		//Delete Data
		public String deleteResearcher(String resData) {
			JsonObject resObject = new JsonParser().parse(resData).getAsJsonObject();

			// Read the value from the element <RID>
			String researchID = resObject.get("researchID").getAsString(); 

			Service resobject3 = new Service();
			Res.setResearchID(researchID);
			String output = resobject3.deleteResearcher(Res);
			return output;
		}
		
		

}
