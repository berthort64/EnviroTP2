package livrable1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class ConnectionBDD { 
	
	private String url = "jdbc:mysql://localhost/Enviro_TP2";
	private Connection laConnexion=null;
	private Statement statement=null;
	
	public ConnectionBDD() {
		
	try {
		
	Class.forName("org.gjt.mm.mysql.Driver"); 
	laConnexion=DriverManager.getConnection(url,"root","p@ssw0rd");	
		
	}catch(Exception ex) {
		
		System.out.print("Erreur: ");
		ex.printStackTrace();
		
	}
		
	}
	
	public void Ajouter(String nom,String membre,String pathPhoto) {
		
		try {
			
		String requete="Insert into artistes values('"+nom+"',"+membre+",'"+pathPhoto+"');";
		//A verifier membre=true/false or 1/0
		statement = laConnexion.createStatement();
		statement.executeUpdate(requete);
		
		}catch(Exception ex) {
			
		System.out.print("Erreur: ");
		ex.printStackTrace();
			
		}
		
	}
	
	public static void Modifier() {
		
		
		
		
	}
	
	public static void Supprimer() {
		
		
		
		
	}
	
	
	
}
