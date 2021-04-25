package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Fund {
	
	//A common method to connect to the DB
	private Connection connect() 
	{ 
		Connection con = null; 
		try
		{   
			Class.forName("com.mysql.jdbc.Driver"); 
			//Provide the correct details: DBServer/DBName, username, password 
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/funddatadb", "root", ""); 
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		} 
		return con; 
	} 
		
	public String insertFund(String userID, String projectID, String famount, String remark,
			 String creationdate, String modifiedby, String modifieddate) 
	{ 
		String output = ""; 
		try
		{ 
			Connection con = connect(); 
			if (con == null) 
			{
				return "Error while connecting to the database for inserting."; 
			} 
			String query = " insert into funds  (`fundID`,`userID`,`projectID`,`famount`,`remark`,"
					+ "`creationdate`)"+ " values "
					+ "(?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query); 
			// binding values
			preparedStmt.setInt(1, 0); 
			preparedStmt.setInt(2, Integer.parseInt(userID)); 
			preparedStmt.setInt(3, Integer.parseInt(projectID)); 
			preparedStmt.setDouble(4, Double.parseDouble(famount)); 
			preparedStmt.setString(5, remark);
			preparedStmt.setString(6, creationdate);
			//preparedStmt.setInt(7, Integer.parseInt(modifiedby));
			//preparedStmt.setString(8, modifieddate);
			// execute the statement
			preparedStmt.execute(); 
			con.close(); 
			output = "Inserted successfully"; 
		} 
		catch (Exception e) 
		{ 
			output = "Error while inserting the fund."; 
			System.err.println(e.getMessage()); 
		} 
			return output; 
	}
	
	public String readFunds() 
	{ 
		String output = ""; 
		try
		{ 
			Connection con = connect(); 
			if (con == null) 
			{
				return "Error while connecting to the database for reading."; 
			} 
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Fund ID</th><th>User ID</th><th>Project ID</th>" +"<th>Amount</th>" +  
			"<th>Remark</th>" +  "<th>Created Date</th>" +
			"<th>Modified By</th>" +  "<th>Modified Date</th>" +
			"<th>Update</th><th>Remove</th></tr>";    
			String query = "select * from funds"; 
			Statement stmt = con.createStatement(); 
			ResultSet rs = stmt.executeQuery(query); 
			// iterate through the rows in the result set
			while (rs.next()) 
			{ 
				String fundID = Integer.toString(rs.getInt("fundID")); 
				String userID = rs.getString("userID"); 
				String projectID = rs.getString("projectID"); 
				String famount = Double.toString(rs.getDouble("famount")); 
				String remark = rs.getString("remark");
				String creationdate = rs.getString("creationdate");
				String modifiedby = rs.getString("modifiedby");
				String modifieddate = rs.getString("modifieddate");
				// Add into the html table
				output += "<tr><td>" + fundID + "</td>"; 
				output += "<td>" + userID + "</td>";
				output += "<td>" + projectID + "</td>"; 
				output += "<td>" + famount + "</td>"; 
				output += "<td>" + remark + "</td>";
				output += "<td>" + creationdate + "</td>"; 
				output += "<td>" + modifiedby + "</td>";
				output += "<td>" + modifieddate + "</td>";
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"+ "<td><form method='post' action='items.jsp'>"+ "<input name='btnRemove' type='submit' value='Remove'  class='btn btn-danger'>"+ "<input name='fundID' type='hidden' value='" + fundID+ "'>" + "</form></td></tr>"; 
			} 
			con.close(); 
			// Complete the html table
			output += "</table>"; 
		} 
		catch (Exception e) 
		{ 
			output = "Error while reading the funds."; 
			System.err.println(e.getMessage()); 
		} 
		return output; 
	}
	
	public String readFundDetailsByUserId(String puserID) 
	{ 
		String output = ""; 
		try
		{ 
			Connection con = connect(); 
			if (con == null) 
			{
				return "Error while connecting to the database for reading."; 
			} 
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Fund ID</th><th>User ID</th><th>Project ID</th>" +"<th>Amount</th>" +  
			"<th>Remark</th>" +  "<th>Created Date</th>" +
			"<th>Modified By</th>" +  "<th>Modified Date</th>" +
			"<th>Update</th><th>Remove</th></tr>";    
			String query = "select * from funds where userID='"+puserID+"'"; 
			Statement stmt = con.createStatement(); 
			ResultSet rs = stmt.executeQuery(query); 
			// iterate through the rows in the result set
			while (rs.next()) 
			{ 
				String fundID = Integer.toString(rs.getInt("fundID")); 
				String userID = rs.getString("userID"); 
				String projectID = rs.getString("projectID"); 
				String famount = Double.toString(rs.getDouble("famount")); 
				String remark = rs.getString("remark");
				String creationdate = rs.getString("creationdate");
				String modifiedby = rs.getString("modifiedby");
				String modifieddate = rs.getString("modifieddate");
				// Add into the html table
				output += "<tr><td>" + fundID + "</td>"; 
				output += "<td>" + userID + "</td>";
				output += "<td>" + projectID + "</td>"; 
				output += "<td>" + famount + "</td>"; 
				output += "<td>" + remark + "</td>";
				output += "<td>" + creationdate + "</td>"; 
				output += "<td>" + modifiedby + "</td>";
				output += "<td>" + modifieddate + "</td>";
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"+ "<td><form method='post' action='items.jsp'>"+ "<input name='btnRemove' type='submit' value='Remove'  class='btn btn-danger'>"+ "<input name='fundID' type='hidden' value='" + fundID+ "'>" + "</form></td></tr>"; 
			} 
			con.close(); 
			// Complete the html table
			output += "</table>"; 
		} 
		catch (Exception e) 
		{ 
			output = "Error while reading the funds."; 
			System.err.println(e.getMessage()); 
		} 
		return output; 
	}
		
	public String updateFund(String fundID, String userID, String projectID, String famount, String remark,
		   String creationdate, String modifiedby, String modifieddate)
	{ 
		String output = ""; 
		try
		{
			Connection con = connect(); 
			if (con == null) 
			{
				return "Error while connecting to the database for updating."; 
			} 
			// create a prepared statement
			String query = "UPDATE funds SET userID=?,projectID=?,famount=?,remark=?,"
					+ "creationdate=?,modifiedby=?,modifieddate=? WHERE fundID=?"; 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(userID)); 
			preparedStmt.setInt(2, Integer.parseInt(projectID)); 
			preparedStmt.setDouble(3, Double.parseDouble(famount)); 
			preparedStmt.setString(4, remark); 
			preparedStmt.setDate(5, Date.valueOf(creationdate));
			preparedStmt.setInt(6, Integer.parseInt(modifiedby));
			preparedStmt.setDate(7, Date.valueOf(modifieddate));
			preparedStmt.setInt(8, Integer.parseInt(fundID));
			// execute the statement
			preparedStmt.execute(); 
			con.close(); 
			output = "Updated successfully"; 
		} 
		catch (Exception e) 
		{ 
			output = "Error while updating the fund."; 
			System.err.println(e.getMessage()); 
		} 
		return output; 
	}
		
	public String deleteFund(String fundID) 
	{ 
		String output = ""; 
		try
		{ 
			Connection con = connect(); 
			if (con == null) 
			{
				return "Error while connecting to the database for deleting."; 
			} 
			// create a prepared statement
			String query = "delete from funds where fundID=?"; 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(fundID)); 
			// execute the statement
			preparedStmt.execute(); 
			con.close(); 
			output = "Deleted successfully"; 
		} 
		catch (Exception e) 
		{
			output = "Error while deleting the fund."; 
			System.err.println(e.getMessage()); 
		} 
		return output; 
	}
	
}
