package model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class Order {
	
	public Connection connect()
	 {
	 Connection con = null;
	 try
	 {
	 Class.forName("com.mysql.cj.jdbc.Driver");
	 
	 //Provide the correct details: DBServer/DBName, username, password
	 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/order?serverTimezone=UTC", "root", "");
	 
	//For testing
	 System.out.print("Successfully connected"); 
	 
	 }
	 catch (Exception e)
	 {e.printStackTrace();}
	 return con;
	 }
	
	//insert to database
	public String insertItem(String orderName, String orderCategory, String orderPrice, String orderDate)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for inserting."; }
	 // create a prepared statement
	 String query = " insert into ordertable(`orderID `,`orderName`,`orderCategory`,`orderPrice`,`orderDate`)"
	 + " values (?, ?, ?, ?, ?)";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setInt(1, 0);
	 preparedStmt.setString(2, orderName);
	 preparedStmt.setString(3, orderCategory);
	 preparedStmt.setString(4, orderPrice);
	 preparedStmt.setString(5, orderDate);
	
	 
	// execute the statement
	 preparedStmt.execute();
	 con.close();
	 output = "Inserted successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while inserting the item.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	
	
	
	
	public String readItems()
	 {
	 String output = "";
	 try
	 {
		 
	 Connection con = connect();
	 
	 if (con == null)
	 {
	 return "Error while connecting to the database for readingggg."; }
	 // Prepare the html table to be displayed
	 output = "<table border=\"1\"><tr><th>orderID </th><th> orderName </th><th> orderCategory </th><th> orderPrice </th><th> orderDate </th><th>Update</th><th>Remove</th></tr>";
	 String query = "select * from ordertable";
	 Statement stmt = con.createStatement();
	 ResultSet rs = stmt.executeQuery(query);
	 // iterate through the rows in the result set
	 while (rs.next())
	 {
		 String orderID = Integer.toString(rs.getInt("orderID"));
		 String orderName = rs.getString("orderName");
		 String orderCategory = rs.getString("orderCategory");
		 String orderPrice = rs.getString("orderPrice");		
		 String orderDate = rs.getString("orderDate");
		 
	 // Add into the html table
		 output += "<tr><td>" + orderID + "</td>";
		 output += "<td>" + orderName + "</td>";
		 output += "<td>" + orderCategory + "</td>";
		 output += "<td>" + orderPrice + "</td>";
		 output += "<td>" + orderDate + "</td>";
		
		 
	 // buttons
	 output += "<td><input name=\"btnUpdate\" type=\"button\"value=\"Update\" class=\"btn btn-secondary\"></td>"+ "<td><form method=\"post\" action=\"items.jsp\">" + "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\"class=\"btn btn-danger\">"
	 + "<input name=\"funderID\" type=\"hidden\" value=\"" + orderID
	 + "\">" + "</form></td></tr>";
	 }
	 con.close();
	 // Complete the html table
	 output += "</table>";
	 }
	 catch (Exception e)
	 {
	 output = "Error while reading the items.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 } 
	
	

	public String updateItem(String orderID, String orderName, String orderCategory, String orderPrice, String orderDate)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for updating."; }
	 // create a prepared statement
	 String query = "UPDATE ordertable SET orderName=?,orderCategory=?,orderPrice=?,orderDate=?WHERE orderID=?";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setString(1, orderName);
	 preparedStmt.setString(2, orderCategory);
	 preparedStmt.setString(3, orderPrice);
	 preparedStmt.setString(4, orderDate);
	 preparedStmt.setInt(5,Integer.parseInt(orderID));
	 // execute the statement
	 preparedStmt.execute();
	 con.close();
	 output = "Updated successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while updating the item.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	
	public String deleteItem(String funderID)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for deleting."; }
	 // create a prepared statement
	 String query = "delete from ordertable where orderID=?";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setInt(1, Integer.parseInt(funderID));
	 // execute the statement
	 preparedStmt.execute();
	 con.close();
	 output = "Deleted successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while deleting the item.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 } 


}
