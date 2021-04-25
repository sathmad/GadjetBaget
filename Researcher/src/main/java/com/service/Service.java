package com.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.research;
import com.model.DBconnection;

public class Service {
	
	Connection con = null;

	public Service() {
		//Database Connection
		con = DBconnection.connecter();
	}
	
	//Insert
	public String insertResearcher(research research) {


		String output;
		try {
			Connection con = DBconnection.connecter();
			
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}

			
			//Data insert query
		String query = " insert into research(`researchID`,`researchTopic`,`description`,`researcherID`,`cost`)"
					+ " values (?, ?, ?, ?, ?)";

			//Pass the values into the database table
			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setString(1, research.getResearchID());
			preparedStmt.setString(2, research.getResearchTopic());
			preparedStmt.setString(3, research.getDescription());
			preparedStmt.setString(4, research.getResearcherID());
			preparedStmt.setFloat(5, research.getCost());
			
			// execute the statement
			preparedStmt.execute();
			
			//Message
			output = "Inserted successfully";

		} catch (SQLException e) {
			//If operation is false 
			output = "Error while inserting the researcher.";
			System.err.println(e.getMessage());
		}

		return output;
	}
	//Review
	public String readResearcher() {
		String output = " ";
		// Prepare the html table to be displayed in database
		output = "<table border=\"1\"><tr><th>Research ID</th>" + "<th>Research Topic</th><th>Description</th>"
				+ "<th>Researcher ID</th>" + "<th>Cost</th></tr>";
		
		try {
			Connection con = DBconnection.connecter();
			
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			//Select the table
			String query = "select * from research";
			
			Statement stmt = con.createStatement();
			ResultSet results = stmt.executeQuery(query);
			

			// Select the rows in the result set
			while (results.next()) {
				String researchID = results.getString("researchID");
				String researchTopic = results.getString("researchTopic");
				String description = results.getString("description");				
				String researcherID = results.getString("researcherID");
				float cost = results.getFloat("cost");
				
				// Add into the html table records
				output += "<tr><td>" + researchID + "</td>";
				output += "<td>" + researchTopic + "</td>";
				output += "<td>" + description + "</td>";
				output += "<td>" + researcherID + "</td>";
				output += "<td>" + cost + "</td>";
				
				   
			}

			// Close the html table
			output += "</table>";
		} catch (Exception e) {
			//If operation is false
			output = "Error while reading the Researcher.";
			System.err.println(e.getMessage());
		}
		return output;
	}
		//Update
		public String updateResearcher(research research) {
		
		
		String output = "";
		
		try {
			Connection con = DBconnection.connecter();
			
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			//Update query
			String query = "UPDATE research SET researchTopic=?,description=?,researcherID=?,cost=? WHERE researchID=?";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			
			preparedStmt.setString(1, research.getResearchTopic());
			preparedStmt.setString(2, research.getDescription());
			preparedStmt.setString(3, research.getResearcherID());
			preparedStmt.setFloat(4, research.getCost());
			preparedStmt.setString(5, research.getResearchID());
			
			// execute the statement
			preparedStmt.executeUpdate();
			
			
			
			if(preparedStmt.executeUpdate() == 1) {
				output = "Updated successfully";
			}else{
				output = "Error while updating the Researcher.";			}
			
				con.close();
			
		} catch (SQLException e) {
			output = "Error while updating the Research.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	//Delete
	public String deleteResearcher(research research) {
		
		
String output="";
		

		try {
			Connection con = DBconnection.connecter();
			
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			
			String query = "delete from research where researchID=?";

			
			// create a prepared statement

			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setString(1, research.getResearchID());
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
			
			
		} catch (Exception e) {
			output = "Error while deleting the Research.";
			System.err.println(e.getMessage());
		}
		return output;
	}



}
