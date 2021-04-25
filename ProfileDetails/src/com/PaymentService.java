package com;


import model.Payment;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;
@Path("/items")
public class PaymentService
{
	Payment paymentObj = new Payment();
	
@GET
@Path("/")
@Produces(MediaType.TEXT_HTML)
public String readPayments()
{
		return paymentObj.readPayments();
}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertPayment(@FormParam("CustomerID") String CustomerID,
	@FormParam("CustomerName") String CustomerName,
	@FormParam("Amount") String Amount,
	@FormParam("cardNo") String cardNo)
	{
	String output = paymentObj.insertPayment(CustomerID, CustomerName, Amount, cardNo);
	return output;
	}
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updatePayment(String itemData)
	{
	//Convert the input string to a JSON object
	JsonObject paymentObject = new JsonParser().parse(itemData).getAsJsonObject();
	//Read the values from the JSON object

	String CustomerID = paymentObject.get("CustomerID").getAsString();
	String CustomerName = paymentObject.get("CustomerName").getAsString();
	String Amount = paymentObject.get("Amount").getAsString();
	String cardNo = paymentObject.get("cardNo").getAsString();
	String output = paymentObj.updatePayment(CustomerID, CustomerName, Amount, cardNo);
	return output;
	}
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deletePayment(String paymentData)
	{
	//Convert the input string to an XML document
	Document doc = Jsoup.parse(paymentData, "", Parser.xmlParser());
	//Read the value from the element <itemID>
	String CustomerID = doc.select("CustomerID").text();
	String output = paymentObj.deletePayment(CustomerID);
	return output;
	
}
}