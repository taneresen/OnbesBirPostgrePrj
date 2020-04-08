package com.bilisimegitim.postgreprj;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import com.bilisimegitim.postgreprj.vt.VTBaglanti;

public class VTInsertYeni {

	public static void main(String[] args) {
		
		Connection conn =null;
		try {
			conn = VTBaglanti.baglantiAc();
			String sorgu = "insert into ogrenci (no,ad,soyad) values(?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sorgu);
			ps.setInt(1, 22);
			ps.setString(2, "Atilla");
			ps.setString(3, "Karacali");
			int sonuc = ps.executeUpdate();
			VTBaglanti.baglantiKapat(conn);
			
			if (sonuc>0) 
			{
				System.out.println("Eklendi");
			} else 
			{
				System.out.println("Eklenemedi");
			}
			
		} catch (Exception e) {
			VTBaglanti.baglantiKapat(conn);
			e.printStackTrace();
		}
		
		
		
	}

}
