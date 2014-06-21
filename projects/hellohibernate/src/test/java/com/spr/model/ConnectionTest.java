package com.spr.model;

import static org.junit.Assert.assertNotNull;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;


public class ConnectionTest {

	@Test
	public void test() throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://192.168.1.135:3306/certprodb",
			      "certprouser","password");
		assertNotNull(con);
	}

}
