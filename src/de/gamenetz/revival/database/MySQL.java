package de.gamenetz.revival.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.bukkit.Bukkit;

import de.gamenetz.revival.Revival;

public class MySQL {
	
	
    public static Connection con;
    
    public static void connect(){
           
            try {
                    con = DriverManager.getConnection("jdbc:mysql://" + Revival.host + ":3306/"+ Revival.db + "?autoReconnect=true",Revival.user,Revival.pass);
                    System.out.println("[Revival-PvP] Du hast erfolgreich die Verbindung zum Server hergestellt.");
                   
            } catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println("[Revival-PvP] Konnte keine Verbindung zur Datenbank herstellen.");
                    Bukkit.getServer().shutdown();
            }
           
    }
   
    public static void close(){
           
            if(con != null){
                    try {
                            con.close();
                    } catch (SQLException e) {
                            e.printStackTrace();
                    }
            }
           
    }
   
    public static void Update(String qry){  
    	try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(qry);
    	} catch (SQLException e) {
            e.printStackTrace();
    }
       
}
    
    
 
   
    public static ResultSet Query(String qry){
            ResultSet rs = null;
           
            try {
                    Statement stmt = con.createStatement();
                    rs = stmt.executeQuery(qry);
            } catch (SQLException e) {
                    e.printStackTrace();
            }
           
            return rs;
    }

    
    

}
