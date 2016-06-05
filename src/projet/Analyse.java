package projet;

import java.awt.Color;
import java.util.ArrayList;

import edu.princeton.cs.introcs.StdDraw;

public class Analyse extends Grille {
	public Analyse(int dimensionX, int dimensionY) {
		super(dimensionX, dimensionY);
		// TODO Auto-generated constructor stub
	}

	//<---------------------VERIFIER QU'UNE CASE EST DEJA PRISE OU NON  -------------------------->//	
	
		public static boolean caseExisteDejaDansTableau(ArrayList<Case> tableauCases, Case caseEstDansTableau){
			
			boolean estDansLeTableau = false;
			
			for(Case caseConsideree : tableauCases){
				if(caseConsideree == caseEstDansTableau){
					estDansLeTableau = true;
					break;
				}
			}
			return estDansLeTableau;
		}
		
		//<---------------------     -------------------------->//	
		public static boolean voisinPossede(int X, int Y, Joueur joueur, ArrayList<Case> tableauCasePouvantEtreConquiseParCetteCouleur){
			
			boolean voisinPossede = false;
			
			for(int i=X-1; i<X+2; i++){
				for(int j=Y-1; j<Y+2; j++){
					
					if( (!(i<0 || i>tableau.length-1 || j<0 || j>tableau[0].length-1 || (i == X && j == Y)))){
						
						Case caseij = tableau[i][j];
						
						if(caseij.joueur == joueur || caseExisteDejaDansTableau(tableauCasePouvantEtreConquiseParCetteCouleur, caseij)){
							voisinPossede = true;
							break;
						}
					}
				}
			} 
			
			return voisinPossede;
		}
		
	//<---------------------VERIFIER SI LES CASES AUTOUR DE LA CASE CONQUISE SONT POSSEDES OU NON PAR LE JOUEUR-------------------------->//
		
		public static boolean voisinPossede1(int X, int Y, Joueur joueur){
			
			boolean voisinPossede = false;
			
			for(int i=X-1; i<X+2; i++){
				for(int j=Y-1; j<Y+2; j++){
					
					if( (!(i<0 || i>tableau.length-1 || j<0 || j>tableau[0].length-1 || (i == X && j == Y)))){
						
						if(tableau[i][j].joueur == joueur){
							voisinPossede = true;
							break;
						}
					}
				}
			}
			
			if((X-1)>=0){
				if(tableau[X-1][Y].joueur == joueur){
					voisinPossede = true;
				}
			}
			if((X+1)<tableau.length){
				if(tableau[X+1][Y].joueur == joueur){
					voisinPossede = true;
				
			}
			if((Y-1)>=0){
					if(tableau[X][Y-1].joueur == joueur){
						voisinPossede = true;
					}
			}
			if((Y+1)<tableau[1].length){
				if(tableau[X][Y+1].joueur == joueur){
					voisinPossede = true;
				}
			}
			}
			
			
			return voisinPossede;
		}
			
		//<---------------------VERIFIER QU'UNE COULEUR EST DISPONIBLE OU NON-------------------------->//	
		
			public static boolean couleurDisponible(Color couleur){
				
				boolean peutEtreChoisie = true;
				
				for(int i=0; i<tableau.length; i++){ 
					for(int j=0; j<tableau[0].length; j++){
						Case caseij = tableau[i][j];
						if(caseij.joueur != null && caseij.couleur == couleur){
							peutEtreChoisie = false;
						}
					}
				}
				return peutEtreChoisie;
			}

		
	//<---------------------CALCULER LE SCORE DES JOUEURS-------------------------->//	
		
		public static int score(Joueur joueur){
			
			int score = 0;
			
			for(int i=0; i<tableau.length; i++){ 
				for(int j=0; j<tableau[0].length; j++){
					
					if(tableau[i][j].joueur == joueur){
						score +=1;
					}
				}
			}
			
			return score;
			
		}
		
	//<---------------------CONTINUER LA PARTIE EN FONCTION DES DIMENSIONS DE LA GRILLE-------------------------->//	
		
		public static boolean conditionDeVictoire(Joueur joueur, Grille grille){
			
			boolean continuer = false;
			
			if(score(joueur)> (int) ((dimensionX*dimensionY-nombreDeCaseDeCetteCouleur(Color.gray))/2)){   //On compte le nombre de cases conquises par le joueur en fonction des dimensions de la grille
				StdDraw.setPenColor(Color.black);
				StdDraw.text(grille.dimensionX/2, grille.dimensionY + 1, joueur.Nom + " a conquis plus de la moitié des cases !");
				StdDraw.setPenColor(Color.white);
				StdDraw.filledRectangle(grille.dimensionX/2, grille.dimensionY +3, 20, 1);	
				System.out.println("Fin de la partie");
				return false;  // Si le joueur a conquis plus de la moitie des cases, il a gagné 
			}
			
			else{  
				for(int i=0; i<tableau.length; i++){
					for(int j=0; j<tableau[0].length; j++){  
						
						if(tableau[i][j].joueur == null){ 
							continuer = true;
							break;  // Si le joueur ou son adversaire n'a pas encore conquis la moitié des cases, il peut continuer de jouer. 
						}
					}
				}
				
				return continuer;
			}
		}
		
		
	//<-------------- CALCUL DU NOMBRE DE CASES D'UNE COULEUR DONNEE----------------->//

		public static int nombreDeCaseDeCetteCouleur(Color couleur){
			int nb = 0;
			for (int i=0; i<tableau.length; i++){
				for (int j=0; j<tableau.length; j++){
					if(tableau[i][j].couleur == couleur){
						nb=nb+1;
					}
				}
			}
			return nb;
		}
		
		

	

}
