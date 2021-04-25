package com.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBconnection {

	public static  Connection connecter() {
		Connection con = null;

			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				//to check the database connection 
				con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3311/reseacher?useTimezone=true&serverTimezone=UTC",
						"root", "");
				// If connection is true
				System.out.print("Successfully connected");
			} catch (Exception e) {
				//If connection is false
				System.out.print("connection fail");
				e.printStackTrace();
				System.out.print(e);
			}

			return con;
		}
}
