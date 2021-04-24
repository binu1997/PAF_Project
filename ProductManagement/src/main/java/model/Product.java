package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Product {
	public Connection connect()
	 {
	 Connection con = null;
	 try
	 {
	 Class.forName("com.mysql.cj.jdbc.Driver");
	 //?serverTimezone=UTC
	 //Provide the correct details: DBServer/DBName, username, password
	 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/productapiproject?serverTimezone=UTC", "root", "");
	 
	//For testing
	 System.out.print("Successfully connected"); 
	 
	 }
	 catch (Exception e)
	 {e.printStackTrace();}
	 return con;
	 }
	
	//insert to database
	public String insertProduct(String productName, String productCategory, String productPrice, String productDesc)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for inserting."; }
	 // create a prepared statement
	 String query = " insert into producttable(`productID `,`productName`,`productCategory`,`productPrice`,`productDesc`)"
	 + " values (?, ?, ?, ?, ?)";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setInt(1, 0);
	 preparedStmt.setString(2, productName);
	 preparedStmt.setString(3, productCategory);
	 preparedStmt.setString(4, productPrice);
	 preparedStmt.setString(5, productDesc);
	
	 
	// execute the statement
	 preparedStmt.execute();
	 con.close();
	 output = "Inserted successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while inserting the product.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	
	
	
	
	public String readProducts()
	 {
	 String output = "";
	 try
	 {
		 
	 Connection con = connect();
	 
	 if (con == null)
	 {
	 return "Error while connecting to the database for reading."; }
	 // Prepare the html table to be displayed
	 output = "<table border=\"1\"><tr><th>productID </th><th> productName </th><th> productCategory </th><th> productPrice </th><th> productDesc </th><th>Update</th><th>Remove</th></tr>";
	 String query = "select * from producttable";
	 Statement stmt = con.createStatement();
	 ResultSet rs = stmt.executeQuery(query);
	 // iterate through the rows in the result set
	 while (rs.next())
	 {
		 String productID = Integer.toString(rs.getInt("productID"));
		 String productName = rs.getString("productName");
		 String productCategory = rs.getString("productCategory");
		 String productPrice = rs.getString("productPrice");		
		 String productDesc = rs.getString("productDesc");
		 
	 // Add into the html table
		 output += "<tr><td>" + productID + "</td>";
		 output += "<td>" + productName + "</td>";
		 output += "<td>" + productCategory + "</td>";
		 output += "<td>" + productPrice + "</td>";
		 output += "<td>" + productDesc + "</td>";
		
		 
	 // buttons
	 output += "<td><input name=\"btnUpdate\" type=\"button\"value=\"Update\" class=\"btn btn-secondary\"></td>"+ "<td><form method=\"post\" action=\"products.jsp\">" + "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\"class=\"btn btn-danger\">"
	 + "<input name=\"productID\" type=\"hidden\" value=\"" + productID
	 + "\">" + "</form></td></tr>";
	 }
	 con.close();
	 // Complete the html table
	 output += "</table>";
	 }
	 catch (Exception e)
	 {
	 output = "Error while reading the products.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 } 
	
	

	public String updateProduct(String productID, String productName, String productCategory, String productPrice, String productDesc)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for updating."; }
	 // create a prepared statement
	 String query = "UPDATE producttable SET productName=?,productCategory=?,productPrice=?,productDesc=?WHERE productID=?";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setString(1, productName);
	 preparedStmt.setString(2, productCategory);
	 preparedStmt.setString(3, productPrice);
	 preparedStmt.setString(4, productDesc);
	 preparedStmt.setInt(5,Integer.parseInt(productID));
	 // execute the statement
	 preparedStmt.execute();
	 con.close();
	 output = "Updated successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while updating the Product.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	
	public String deleteProduct(String productID)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for deleting."; }
	 // create a prepared statement
	 String query = "delete from producttable where productID=?";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setInt(1, Integer.parseInt(productID));
	 // execute the statement
	 preparedStmt.execute();
	 con.close();
	 output = "Deleted successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while deleting the product.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 } 
}
