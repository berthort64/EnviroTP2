package livrable1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;

public class ConnectionBDD {

	private String url = "jdbc:mysql://localhost/Enviro_TP2";
	private Connection laConnexion = null;
	private Statement statement = null;

	public ConnectionBDD() {

		try {

			// Class.forName("com.mysql.cj.jdbc.Driver");
			laConnexion = DriverManager.getConnection(url, "Enviro_TP2", "p@ssw0rd");

		} catch (Exception ex) {

			System.out.print("Erreur: ");
			ex.printStackTrace();

		}

	}

	public ArrayList<Artiste> AfficherToutEnregistrements() {

		ArrayList<Artiste> artistes = new ArrayList<>();

		try {

			String requete = "Select * from artistes;";
			// A verifier membre=true/false or 1/0
			statement = laConnexion.createStatement();
			ResultSet jeuResultats = statement.executeQuery(requete);

			while (jeuResultats.next()) {

				String id = jeuResultats.getString(1);
				String nom = jeuResultats.getString(2);
				String membre = jeuResultats.getString(3);
				String photo = jeuResultats.getString(4);

				Artiste artisteTest = new Artiste(id, nom, membre, photo);

				artistes.add(artisteTest);

			}

		} catch (Exception ex) {

			System.out.print("Erreur: ");
			ex.printStackTrace();

		}
		return artistes;
	}

	public void Ajouter(String nom, String membre, String pathPhoto) {

		try {

			String requete = "Insert into artistes values(" + null + ",'" + nom + "'," + membre + ",'" + pathPhoto
					+ "');";
			// A verifier membre=true/false or 1/0
			statement = laConnexion.createStatement();
			statement.executeUpdate(requete);

		} catch (Exception ex) {

			System.out.print("Erreur: ");
			ex.printStackTrace();

		}

	}

	public void Modifier(String id, String nom, String membre, String pathPhoto) {

		try {

			String requete = "UPDATE artistes SET nom = '" + nom + "', membre = " + membre + ", photo = '" + pathPhoto
					+ "' where id=" + id + ";";
			statement = laConnexion.createStatement();
			statement.executeUpdate(requete);

		} catch (Exception ex) {

			System.out.print("Erreur: ");
			ex.printStackTrace();

		}

	}

	public void SupprimerToutEnregistrements() {

		try {

			String requete = "Delete from artistes;";
			statement = laConnexion.createStatement();
			statement.executeUpdate(requete);

		} catch (Exception ex) {

			System.out.print("Erreur: ");
			ex.printStackTrace();

		}

	}

	public void Supprimer(String id) {

		try {

			String requete = "Delete from artistes where id=" + id + ";";
			statement = laConnexion.createStatement();
			statement.executeUpdate(requete);

		} catch (Exception ex) {

			System.out.print("Erreur: ");
			ex.printStackTrace();

		}

	}

	public ArrayList<Artiste> Rechercher(String terme) {

		ArrayList<Artiste> artistes = new ArrayList<>();

		try {

			// Injection de code possible, mais c'est pas grave, la BDD est locale de toute
			// façon.
			String requete = "SELECT * FROM artistes WHERE nom LIKE '%" + terme + "%'";
			statement = laConnexion.createStatement();
			ResultSet jeuResultats = statement.executeQuery(requete);

			while (jeuResultats.next()) {

				String id = jeuResultats.getString(1);
				String nom = jeuResultats.getString(2);
				String membre = jeuResultats.getString(3);
				String photo = jeuResultats.getString(4);

				Artiste artisteTest = new Artiste(id, nom, membre, photo);

				artistes.add(artisteTest);
			}

		} catch (Exception ex) {

			System.out.print("Erreur: ");
			ex.printStackTrace();

		}

		return artistes;

	}

	public ArrayList<String> AlbumsParArtiste(Artiste artiste) {

		ArrayList<String> albums = new ArrayList<String>();

		try {

			// Injection de code possible, mais c'est pas grave, la BDD est locale de toute
			// façon.
			String requete = "SELECT titre, annee FROM albums WHERE idArtiste = " + artiste.getId();
			statement = laConnexion.createStatement();
			ResultSet jeuResultats = statement.executeQuery(requete);

			while (jeuResultats.next()) {
				albums.add(jeuResultats.getString(1) + " (" + jeuResultats.getString(2) + ")");
			}

		} catch (Exception ex) {

			System.out.print("Erreur: ");
			ex.printStackTrace();

		}

		return albums;

	}

}
