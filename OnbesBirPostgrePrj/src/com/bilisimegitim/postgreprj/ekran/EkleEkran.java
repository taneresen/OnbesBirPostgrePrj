package com.bilisimegitim.postgreprj.ekran;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.bilisimegitim.postgreprj.vt.VTBaglanti;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.sql.*;
import java.awt.event.ActionEvent;

public class EkleEkran extends JFrame {

	private JPanel contentPane;
	private JTextField tfNo;
	private JTextField tfAd;
	private JTextField tfSoyad;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EkleEkran frame = new EkleEkran();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public EkleEkran() {
		setTitle("Ögrenci Ekrani");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 564, 390);
		contentPane = new JPanel();
		contentPane.setForeground(Color.RED);
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("No :");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(98, 66, 82, 27);
		contentPane.add(lblNewLabel);
		
		tfNo = new JTextField();
		tfNo.setBounds(171, 69, 116, 22);
		contentPane.add(tfNo);
		tfNo.setColumns(10);
		
		JLabel lblAd = new JLabel("Ad :");
		lblAd.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblAd.setBounds(98, 106, 46, 27);
		contentPane.add(lblAd);
		
		JLabel lblSoyad = new JLabel("Soyad :");
		lblSoyad.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblSoyad.setBounds(77, 146, 82, 27);
		contentPane.add(lblSoyad);
		
		tfAd = new JTextField();
		tfAd.setBounds(171, 106, 116, 22);
		contentPane.add(tfAd);
		tfAd.setColumns(10);
		
		tfSoyad = new JTextField();
		tfSoyad.setBounds(171, 149, 116, 22);
		contentPane.add(tfSoyad);
		tfSoyad.setColumns(10);
		
		JLabel sonucLabel = new JLabel("");
		sonucLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		sonucLabel.setForeground(Color.BLACK);
		sonucLabel.setHorizontalAlignment(SwingConstants.CENTER);
		sonucLabel.setBounds(0, 277, 534, 34);
		contentPane.add(sonucLabel);
		
		
		JButton btnNewButton = new JButton("Ekle");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Connection conn =null;
				try {
					conn = VTBaglanti.baglantiAc();
					String sorgu = "insert into ogrenci(no,ad,soyad) values (?,?,?)";
					PreparedStatement ps = conn.prepareStatement(sorgu);
					ps.setInt(1, Integer.parseInt(tfNo.getText()));
					ps.setString(2, tfAd.getText());
					ps.setString(3, tfSoyad.getText());
					int sonuc =ps.executeUpdate();
					VTBaglanti.baglantiKapat(conn);
					
					if (sonuc>0) 
					{
						sonucLabel.setText(tfNo.getText()+"Eklendi :))");
						tfNo.setText("");
						tfAd.setText("");
						tfSoyad.setText("");
					} else 
					{
						sonucLabel.setText(tfNo.getText()+"Eklenemedi :(( ");
						tfNo.setText("");
						tfAd.setText("");
						tfSoyad.setText("");
					}
					
					
					
				} catch (Exception e1) {
					sonucLabel.setText("Hata:"+e1.getMessage()); // hata Mesajini Aliriz
					VTBaglanti.baglantiKapat(conn);
					e1.printStackTrace();
				} 
				
				
				
				
			}
		});
		btnNewButton.setBounds(83, 216, 97, 25);
		contentPane.add(btnNewButton);
		
		JButton btnGetir = new JButton("Getir");
		btnGetir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Connection conn = null; 
				try 
				{
					String ad = null;
					String soyad = null;
					conn = VTBaglanti.baglantiAc();
					String sorgu = "select ad,soyad from ogrenci where no=?";
					PreparedStatement ps = conn.prepareStatement(sorgu);
					ps.setInt(1, Integer.parseInt(tfNo.getText()));
					ResultSet rs = ps.executeQuery();
					VTBaglanti.baglantiKapat(conn);
					
					
					
					while(rs.next()) 
					{
						ad = rs.getString("ad");
						soyad = rs.getString("soyad");
					} 
					rs.close();
					
					if (ad==null && soyad==null)
					{
						tfAd.setText("-");
						tfSoyad.setText("-");
						sonucLabel.setText("Bulunamadi ");
					}
					else
					{
						tfAd.setText(ad);
						tfSoyad.setText(soyad);
						sonucLabel.setText("Bulundu :) ");
					}
					
					ps.close();
					
					
					
					
				}
				
				catch (Exception e2) 
				{
					sonucLabel.setText("Bulunamadi :( "+ e2.getMessage());
					VTBaglanti.baglantiKapat(conn);
					e2.printStackTrace();
				}
				
				
			}
		});
		btnGetir.setBounds(225, 216, 97, 25);
		contentPane.add(btnGetir);
		
	
	}
}
