package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Product {

	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/profile?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertProduct(String prname, String prdate, String prdes) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into product(`pid`, `prname`, `prdate`, `prdes`)" + " values ( ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, prname);
			preparedStmt.setString(3, prdate);
			preparedStmt.setString(4, prdes);

			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting the Products.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readProduct() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>Product ID</th><th>Product Name</th><th>Date</th><th>Description</th></tr>";
			String query = "select * from product";
			Statement stmt = (Statement) con.createStatement();
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String pid = Integer.toString(rs.getInt("pid"));
				String prname = rs.getString("prname");
				String prdate = rs.getString("prdate");
				String prdes = rs.getString("prdes");

				output += "<tr><td>" + pid + "</td>";
				output += "<td>" + prname + "</td>";
				output += "<td>" + prdate + "</td>";
				output += "<td>" + prdes + "</td>";

			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the Products.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updateProduct(String pid, String prname, String prdate, String prdes) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			// create a prepared statement
			String query = "UPDATE product SET prname=?,prdate=?,prdes=? WHERE pid=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values

			preparedStmt.setString(1, prname);
			preparedStmt.setString(2, prdate);
			preparedStmt.setString(3, prdes);
			preparedStmt.setInt(4, Integer.parseInt(pid));

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the item.";
			System.err.println(e.getMessage());
		}

		return output;
	}

	public String deleteProduct(String pid) {

		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			// create a prepared statement
			String query = "delete from product where pid=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, Integer.parseInt(pid));

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the Product.";
			System.err.println(e.getMessage());
		}

		return output;
	}

}