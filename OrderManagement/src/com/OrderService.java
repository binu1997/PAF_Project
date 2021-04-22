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

import model.Order;


public class OrderService {
	
Order OrderObj = new Order();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readItems()
	{
		return OrderObj.readItems();
	}
	
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertItem(@FormParam("orderName") String orderName,
	 @FormParam("orderCategory") String orderCategory,
	 @FormParam("orderPrice") String orderPrice,
	 @FormParam("orderDate") String orderDate)
	 

	
	{
	 String output = OrderObj.insertItem(orderName, orderCategory, orderPrice, orderDate);
	return output;
	}
	
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateItem(String itemData)
	{
	//Convert the input string to a JSON object
	 JsonObject OrderObject = new JsonParser().parse(itemData).getAsJsonObject();
	//Read the values from the JSON object
	 String orderID  = OrderObject.get("orderID").getAsString();
	 String orderName = OrderObject.get("orderName").getAsString();
	 String orderCategory = OrderObject.get("orderCategory").getAsString();
	 String orderPrice = OrderObject.get("orderPrice").getAsString();
	 String orderDate = OrderObject.get("orderDate").getAsString();
	
	 
	 String output = OrderObj.updateItem(orderID,orderName,orderCategory,orderPrice,orderDate);
	 return output;
	}
	
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteItem(String itemData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(itemData, " ", Parser.xmlParser());

	//Read the value from the element <itemID>
	 String funderID = doc.select("funderID").text();
	 String output = OrderObj.deleteItem(funderID);
	return output;
	}
	
}
	


