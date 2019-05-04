package livrable1;

public class Artiste {

	private String id, nom, membre, photo;

	public Artiste(String id, String nom, String membre, String photo) {

		this.id = id;
		this.nom = nom;
		this.membre = membre;
		this.photo = photo;

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getMembre() {
		return membre;
	}

	public void setMembre(String membre) {
		this.membre = membre;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	@Override
	public String toString() {
		return this.getNom();
	}
}
