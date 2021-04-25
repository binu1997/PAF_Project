package model; 

import java.sql.*; 
public class Research
{ 		//A common method to connect to the DB
		private Connection connect() 
		{ 
				Connection con = null; 
 
				try
				{ 
					Class.forName("com.mysql.jdbc.Driver"); 
 
					//Provide the correct details: DBServer/DBName, username, password 
					con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test", "root", ""); 
				} 
				catch (Exception e) 
				{e.printStackTrace();} 
				
				return con; 
		} 

		public String insertResearch(String researchID, String researchName, String researchType, String researchDesc) 
		{ 
			String output = ""; 
			
			try
			{ 
				Connection con = connect(); 
 
				if (con == null) 
				{return "Error while connecting to the database for inserting."; } 
 
				// create a prepared statement
				String query = " insert into researchs "
						(`researchID`,`researchName`,`researchType`,`researchDesc`)"
						+ " values (?, ?, ?, ?, ?)"; 
 
				PreparedStatement preparedStmt = con.prepareStatement(query); 

				// binding values
				preparedStmt.setInt(1, 0); 
				preparedStmt.setString(2, researchID); 
				preparedStmt.setString(3, researchName); 
				preparedStmt.setString(4, researchType); 
				preparedStmt.setString(5, researchDesc); 
				
				// execute the statement
				preparedStmt.execute(); 
				con.close(); 
				 
				output = "Inserted successfully"; 
			} 
			catch (Exception e) 
			{ 
				 output = "Error while inserting the research."; 
				 System.err.println(e.getMessage()); 
			} 
			
			return output; 
		}
		
		public String readResearchs() 
		{ 
				 String output = ""; 
				 
				 try
				 { 
					 Connection con = connect(); 
					 
					 if (con == null) 
					 {return "Error while connecting to the database for reading."; } 
					 
					 // Prepare the html table to be displayed
					 output = "<table border='1'><tr><th>Item Code</th><th>Item Name</th>" +
							 "<th>Item Price</th>" + 
							 "<th>Item Description</th>" +
							 "<th>Update</th><th>Remove</th></tr>"; 
				 
					 String query = "select * from researchs"; 
					 Statement stmt = con.createStatement(); 
					 ResultSet rs = stmt.executeQuery(query); 
					 
					 // iterate through the rows in the result set
					 while (rs.next()) 
					 { 
						 String researchID = Integer.toString(rs.getInt("researchID")); 
						 String researchName = rs.getString("researchName"); 
						 String researchType = rs.getString("researchType");  
						 String researchDesc = rs.getString("researchDesc"); 
						 
						 // Add into the html table
						 output += "<tr><td>" + researchID + "</td>"; 
						 output += "<td>" + researchName + "</td>"; 
						 output += "<td>" + researchType + "</td>"; 
						 output += "<td>" + researchDesc + "</td>"; 
						 
						 // buttons
						 output += "<td><input name='btnUpdate' type='button' value='Update' "
								 	class='btn btn-secondary'></td>"
								+ "<td><form method='post' action='items.jsp'>"
								+ "<input name='btnRemove' type='submit' value='Remove' 
									class='btn btn-danger'>"
								+ "<input name='researchID' type='hidden' value='" + researchID 
								+ "'>" + "</form></td></tr>"; 
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
				
			public String updateResearch1(String researchID, String researchName, String researchType, String researchDesc)
			{ 
				 String output = ""; 
				 
				 try
				 { 
					 	Connection con = connect(); 
				 
					 	if (con == null) 
					 	{return "Error while connecting to the database for updating."; } 
					 	
					 	// create a prepared statement
					 	String query = "UPDATE researchs SET researchID=?,researchName=?,researchType=?,researchDesc=? 
					 					WHERE researchID=?"; 
					 	PreparedStatement preparedStmt = con.prepareStatement(query); 
				 
					 	// binding values
						 preparedStmt.setString(1, id); 
						 preparedStmt.setString(2, name); 
						 preparedStmt.setString(3, desc); 
						 preparedStmt.setInt(4, Integer.parseInt(ID)); 
				 
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
			public String deleteResearch(String researchID) 
			{ 
				 String output = ""; 
				 
				 try
				 { 
					 Connection con = connect(); 
					 if (con == null) 
					 {return "Error while connecting to the database for deleting."; } 
				
					 // create a prepared statement
					 String query = "delete from items where researchID=?"; 
				 
					 PreparedStatement preparedStmt = con.prepareStatement(query); 
				 
					 // binding values
					 preparedStmt.setInt(1, Integer.parseInt(researchID)); 
				 
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

			public String updateResearch(String researchID, String researchName, String researchType,
					String researchDesc) {
				// TODO Auto-generated method stub
				return null;
			}

			public Object get(String string) {
				// TODO Auto-generated method stub
				return null;
			} 
}