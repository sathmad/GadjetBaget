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

import model.Fund;


@Path("/Fund")
public class FundService {


	Fund fundObj = new Fund();


	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readFund()
	{
		fundObj = new Fund();
		return fundObj.readFund();
	}


	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertFund(@FormParam("FundID") String FundID,@FormParam("FirstName") String FirstName,@FormParam("LastName") String LastName, @FormParam("NIC") String NIC,
			@FormParam("Address") String Address)
	{
		fundObj = new Fund();
		String output = fundObj.insertFund(FirstName, LastName, NIC, Address);
		return output;
	}


	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateFund(String FundData)
	{

		//Convert the input string to a JSON object
		JsonObject FundObject = new JsonParser().parse(FundData).getAsJsonObject();
		//Read the values from the JSON object
		int FundID = FundObject.get("FundID").getAsInt();
		String FirstName = FundObject.get("FirstName").getAsString();
		String LastName = FundObject.get("LastName").getAsString();
		String NIC = FundObject.get("NIC").getAsString();
		String Address = FundObject.get("Address").getAsString();
		String output = fundObj.updateFund(FirstName, LastName, NIC,Address,FundID);
		return output;
	}


	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteFund(String fundData)
	{
		
		//Convert the input string to an XML document
		 Document doc = Jsoup.parse(fundData, "", Parser.xmlParser());

		//Read the value from the element <itemID>
		 String FundID = doc.select("FundID").text();
		 String output = fundObj.deleteFund(FundID);
		return output;
		
	}


}
