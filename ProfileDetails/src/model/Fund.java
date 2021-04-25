package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Fund {
	
	// A common method to connect to the DB
		private Connection connect() {
			
			Connection con = null;
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");

				// Provide the correct details: DBServer/DBName, username, password
				con = DriverManager.getConnection("jdbc:mysql://localhost:3307/gadgetbadget?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return con;
		}

	public String insertFund(String FirstName, String LastName, String NIC, String Address)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for inserting."; }
	 // create a prepared statement
	 String query = " insert into fundtbl(`FundID`,`FirstName`,`LastName`,`NIC`,`Address`)"
	 + " values (?, ?, ?, ?, ?)";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setInt(1, 0);
	 preparedStmt.setString(2, FirstName);
	 preparedStmt.setString(3, LastName);
	 preparedStmt.setString(4, NIC);
	 preparedStmt.setString(5, Address);
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

	public String readFund()
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for reading."; }
	 // Prepare the html table to be displayed
	 output = "<table border='1'><tr><th>FundID</th>  <th>FirstName</th>" + "<th>LastName</th>" + "<th>NIC</th>" + "<th>Address</th>" + "<th>Update</th> <th>Remove</th> </tr>";
	 

	 String query = "select * from fundtbl";
	 Statement stmt = con.createStatement();
	 ResultSet rs = stmt.executeQuery(query);
	 // iterate through the rows in the result set
	 while (rs.next())
	 {
	 String FundID = Integer.toString(rs.getInt("FundID"));
	 String FirstName = rs.getString("FirstName");
	 String LastName = rs.getString("LastName");
	 String NIC = rs.getString("NIC");
	 String Address = rs.getString("Address");
	 // Add into the html table
	 output += "<tr><td>" + FundID + "</td>";
	 output += "<td>" + FirstName + "</td>";
	 output += "<td>" + LastName + "</td>";
	 output += "<td>" + NIC + "</td>";
	 output += "<td>" + Address + "</td>";
	 // buttons
	 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
	 + "<td><form method='post' action='items.jsp'>"
	 + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
	 + "<input name='FundID' type='hidden' value='" + FundID
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

	public String updateFund( String FirstName, String LastName, String NIC, String Address, int FundID)

	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for updating."; }
	 // create a prepared statement
	 String query = "UPDATE fundtbl SET FirstName=?,LastName=?,NIC=?,Address=? WHERE FundID=?";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setString(1,FirstName );
	 preparedStmt.setString(2, LastName);
	 preparedStmt.setString(3, NIC);
	 preparedStmt.setString(4, Address);
	 preparedStmt.setInt(5, FundID);

	 // execute the statement
	 preparedStmt.execute();
	 con.close();
	 output = "Updated successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while updating the Details.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }

		public String deleteFund(String fundID) {
			String output = "";
			try {
				Connection con = connect();
				if (con == null) {
					return "Error while connecting to the database for deleting.";
				}
				// create a prepared statement
				String query = "delete from fundtbl where FundID=?";
				PreparedStatement preparedStmt = con.prepareStatement(query);
				// binding values
				preparedStmt.setInt(1, Integer.parseInt(fundID));
				// execute the statement
				preparedStmt.execute();
				con.close();
				output = "Deleted successfully";
			} catch (Exception e) {
				output = "Error while deleting the item.";
				System.err.println(e.getMessage());
			}
			return output;
		}

}
