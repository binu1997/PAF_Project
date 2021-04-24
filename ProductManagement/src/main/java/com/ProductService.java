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

import model.Product;

@Path("/Products")
public class ProductService {
	
	
	Product ProductObj = new Product();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readProducts()
	{
		return ProductObj.readProducts();
	}
	
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertProduct(@FormParam("productName") String productName,
	 @FormParam("productCategory") String productCategory,
	 @FormParam("productPrice") String productPrice,
	 @FormParam("productDesc") String productDesc)
	 
	{
		 String output = ProductObj.insertProduct(productName, productCategory, productPrice, productDesc);
		return output;
	}
	
		
		@PUT
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String updateProduct(String productData)
		{
		//Convert the input string to a JSON object
		 JsonObject ProductObject = new JsonParser().parse(productData).getAsJsonObject();
		//Read the values from the JSON object
		 String productID  = ProductObject.get("productID").getAsString();
		 String productName = ProductObject.get("productName").getAsString();
		 String productCategory = ProductObject.get("productCategory").getAsString();
		 String productPrice = ProductObject.get("productPrice").getAsString();
		 String productDesc = ProductObject.get("productDesc").getAsString();
		
		 
		 String output = ProductObj.updateProduct(productID,productName,productCategory,productPrice,productDesc);
		 return output;
		}
		
		
		@DELETE
		@Path("/")
		@Consumes(MediaType.APPLICATION_XML)
		@Produces(MediaType.TEXT_PLAIN)
		public String deleteProduct(String productData)
		{
		//Convert the input string to an XML document
		 Document doc = Jsoup.parse(productData, " ", Parser.xmlParser());

		//Read the value from the element <itemID>
		 String productID = doc.select("productID").text();
		 String output = ProductObj.deleteProduct(productID);
		return output;
		}
	
	
	

}
