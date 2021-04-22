package com;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.Profile;

@Path("/Profiles")
public class ProfileService {
	
	
	
	Profile profileObj = new Profile();
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readProfiles()
	{
		return profileObj.readProfiles();
	}


	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertProfile(@FormParam("customerName") String customerName,
	 @FormParam("customerEmail") String customerEmail,
	 @FormParam("customerNIC") String customerNIC,
	 @FormParam("customerPhoneNum") String customerPhoneNum,
	 @FormParam("customerAddress") String customerAddress)
	{
	 String output = profileObj.insertProfile(customerName, customerEmail, customerNIC, customerPhoneNum, customerAddress);
	return output;
	}
	
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateProfile(String profileData)
	{
	//Convert the input string to a JSON object
	 JsonObject profileObject = new JsonParser().parse(profileData).getAsJsonObject();
	//Read the values from the JSON object
	 String customerID = profileObject.get("customerID").getAsString();
	 String customerName = profileObject.get("customerName").getAsString();
	 String customerEmail = profileObject.get("customerEmail").getAsString();
	 String customerNIC = profileObject.get("customerNIC").getAsString();
	 String customerPhoneNum = profileObject.get("customerPhoneNum").getAsString();
	 String customerAddress = profileObject.get("customerAddress").getAsString();
	 String output = profileObject.updateProfile(customerID, customerName, customerEmail, customerNIC, customerPhoneNum, customerAddress);
	return output;
	}
	
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteItem(String profileData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(profileData, "", Parser.xmlParser());

	//Read the value from the element <itemID>
	 String customerID = doc.select("customerID").text();
	 String output = profileObj.deleteProfile(customerID);
	return output;
	}



}
