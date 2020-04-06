package com.bilisimegitim.postgreprj.vt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class VTBaglanti {
	
	public static Connection baglantiAc()// Static tek olur ise Claastan obje olusturmadan cagirabiliriz demektir
	{
		try {
			Class.forName("org.postgresql.Driver");
			Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/obs", "postgres", "postgres");
			return conn;// TRY ve catch lerde donus tipi void degil,birsey dönduruyor 
						// ise hem TRY de Hemde Catchde return null yapacagiz.

			} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}
		


	}

	public static void baglantiKapat(Connection p_conn)
	{
		try {
			p_conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
}
