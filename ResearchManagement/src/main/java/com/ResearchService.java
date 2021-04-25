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

import model.Research;


public class ResearchService 
{
	Research ResearchObj = new Research();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readItems()
	{
		return ResearchObj.readResearchs();
	}
	
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertResearch(@FormParam("researchID") String researchID, 
								@FormParam("researchName") String researchName, 
								@FormParam("researchType") String researchType, 
								@FormParam("researchDesc") String researchDesc) 
	{ 
	 
	String output = ResearchObj.insertResearch(researchID, researchName, researchType, researchDesc); 
	return output; 
	}
	
	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateResearch(String researchData) 
	{ 
			//Convert the input string to a JSON object 
		JsonObject researchObject = new JsonParser().parse(researchData).getAsJsonObject();
		
			//Read the values from the JSON object
			String researchID = researchObject.get("researchID").toString(); 
			String researchName = researchObject.get("researchName").toString(); 
			String researchType =  researchObject.get("researchType").toString(); 
			String researchDesc =  researchObject.get("researchDesc").toString(); 
			String output = ResearchObj.updateResearch(researchID, researchName, researchType, researchDesc); 
			return output; 
	}
	
	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteResearch(String researchData) 
	{ 
		//Convert the input string to an XML document
		Document doc = Jsoup.parse(researchData, "", Parser.xmlParser()); 
	 
		//Read the value from the element <itemID>
		String researchID = doc.select("itemID").text(); 
		String output = ResearchObj.deleteResearch(researchID); 
		return output; 
	}



}
