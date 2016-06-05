package projet;

import java.awt.Color;
import edu.princeton.cs.introcs.StdDraw;


//<---------------------DESSINER LA GRILLE DU JEU-------------------------->//



public class Grille {
	
	static int dimensionX;
	static int dimensionY;
		
	static Case[][] tableau;
	

//<---------------------DEFINIR LES DIMENSIONS DE LA GRILLE-------------------------->//	
	
	public Grille(int dimensionX, int dimensionY) {		
		Grille.dimensionX = dimensionX;
		Grille.dimensionY = dimensionY;
		tableau = new Case[dimensionX][dimensionY];
		
		//StdDraw.setCanvasSize(900, 900);
		StdDraw.setXscale(-5, tableau[0].length-0.5+5);
		StdDraw.setYscale(-5, tableau.length-0.5+5);
		StdDraw.clear(Color.white);
	}


//<---------------------GENERER UNE GRILLE ALEATOIRE-------------------------->//
			
	public void grilleAleatoire(){
		
		Color[] couleurPossible = {Color.red, Color.orange, Color.yellow, Color.green, Color.blue, Color.magenta, Color.gray};
		
		for(int i=0; i<tableau.length; i++){
			for(int j=0; j<tableau[i].length; j++){
				
				int indiceTableau = (int)(7*Math.random()); //disposition aléatoire des cases 
								
				tableau[i][j] = new Case(couleurPossible[indiceTableau]);
			}
		}
	}
	
	
//<---------------------AFFICHAGE DE LA GRILLE-------------------------->//
		
	public void afficherGrille(){
		
		for(int i=0; i<tableau.length; i++){
			for(int j=0; j<tableau[0].length; j++){
				StdDraw.setPenColor(tableau[i][j].couleur);
				afficherImage(i, j, tableau[i][j].couleur);
			}
		}
	}
	
//<---------------------AFFICHAGE DES CASES DE COULEUR DANS LA GRILLE-------------------------->//
	
	public static void afficherImage(int i, double d, Color couleur){
		if(couleur == Color.blue){
			StdDraw.picture(i, d, "Images/blue.jpg", 1, 1);
		}
		else if(couleur == Color.green){
			StdDraw.picture(i, d, "Images/green.jpg", 1, 1);
		}
		else if(couleur == Color.magenta){
			StdDraw.picture(i, d, "Images/magenta.jpg", 1, 1);
		}
		else if(couleur == Color.orange){
			StdDraw.picture(i, d, "Images/orange.jpg", 1, 1);
		}
		else if(couleur == Color.red){
			StdDraw.picture(i, d, "Images/red.jpg", 1, 1);
		}
		else if(couleur == Color.yellow){
			StdDraw.picture(i, d, "Images/yellow.jpg", 1, 1);
		}
		else if(couleur == Color.gray){
			StdDraw.picture(i, d, "Images/gris.jpg", 1, 1);
		}
	}

	
//<---------------------REGENERER LA GRILLE A CHAQUE TOUR-------------------------->//
	
	public void actualiserGrille(Color couleurChoisie, Joueur joueur){
		
		boolean continuer = true;
		
		while(continuer){
			
			continuer = false;
			
			for(int i=0; i<tableau.length; i++){
				for(int j=0; j<tableau[0].length; j++){
					
					Case caseij = tableau[i][j];
					
					
					if(caseij.joueur == joueur){
						caseij.couleur = couleurChoisie;
					}
					
					else if(caseij.couleur == couleurChoisie && Analyse.voisinPossede1(i, j, joueur)){
						caseij.joueur = joueur;
						continuer = true;
					}
					
				}
			}
		}
	}
}
	
	

	
