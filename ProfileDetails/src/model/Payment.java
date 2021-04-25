package model;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Payment { // A common method to connect to the DB
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
//Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/item", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

public String insertPayment(String id, String name, String price, String cardNo)
{
String output = "";
try
{
Connection con = connect();
if (con == null)
{return "Error while connecting to the database for inserting."; }
// create a prepared statement
String query = " insert into items(`CustomerID`,`CustomerName`,`Amount`,`cardNo`)"
+ " values (?, ?, ?, ?)";
PreparedStatement preparedStmt = con.prepareStatement(query);
// binding values
preparedStmt.setInt(1, 0);
preparedStmt.setString(2, id);
preparedStmt.setString(3, name);
preparedStmt.setString(4, price);
preparedStmt.setString(5, cardNo);
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

public String readPayments()
{
String output = "";
try
{
Connection con = connect();
if (con == null)
{return "Error while connecting to the database for reading."; }
// Prepare the html table to be displayed
output = "<table border='1'><tr><th>Customer ID</th><th>Customer ID</th>" +
"<th>Customer Name</th>" +
"<th>Amount</th>" +
"<th>Update</th><th>Remove</th></tr>";
String query = "select * from items";
Statement stmt = con.createStatement();
ResultSet rs = stmt.executeQuery(query);
// iterate through the rows in the result set
while (rs.next())
{

String CustomerID = rs.getString("CustomerID");
String CustomerName = rs.getString("CustomerName");
String Amount = rs.getString("Amount");
String cardNo = Integer.toString(rs.getInt("cardNo"));
// Add into the html table
output += "<tr><td>" + CustomerID + "</td>";
output += "<td>" + CustomerName + "</td>";
output += "<td>" + Amount + "</td>";
output += "<td>" + cardNo + "</td>";
// buttons
output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"
+ "<td><form method='post' action='items.jsp'>"
+ "<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>"

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

public String updatePayment(String id, String name, String price, String cardnumber)

{
String output = "";
try
{
Connection con = connect();
if (con == null)
{return "Error while connecting to the database for updating."; }
// create a prepared statement
String query = "UPDATE item SET CustomerName=?,Amount=?,cardNo=? WHERE CustomerID=?";
PreparedStatement preparedStmt = con.prepareStatement(query);
// binding values

preparedStmt.setString(1, id);
preparedStmt.setString(2, name);
preparedStmt.setString(3, price);
preparedStmt.setInt(4, Integer. parseInt(cardnumber));
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

public String deletePayment(String customerCode)
{
String output = "";
try
{
Connection con = connect();
if (con == null)
{return "Error while connecting to the database for deleting."; }
// create a prepared statement
String query = "delete from items where itemID=?";
PreparedStatement preparedStmt = con.prepareStatement(query);
// binding values
preparedStmt.setInt(1, Integer.parseInt(customerCode));
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