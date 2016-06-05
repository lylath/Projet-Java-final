package projet;



public class Joueur {	
	
	String Nom;	
	int Numero;
	int DepartX;
	int DepartY;	
	boolean ordinateur;	
	int difficulte;	
	boolean conquis;
	public static boolean affichage;	
	
	public Joueur(String Nom, int Numero, int DepartX, int DepartY, boolean ordinateur, int difficulte, boolean conquis, boolean affichage) {
		this.Nom = Nom;
		this.Numero = Numero;
		this.DepartX = DepartX;
		this.DepartY = DepartY;
		this.ordinateur = ordinateur;
		this.difficulte = difficulte;
		this.conquis = conquis;
		Case.affichage = affichage;
	}
}