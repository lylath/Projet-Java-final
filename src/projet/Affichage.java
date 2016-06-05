package projet;

import java.awt.Color;

import edu.princeton.cs.introcs.StdDraw;

public class Affichage {
	//<------- AFFICHAGE DU CHOIX DES COULEURS ------->//
	
		//<------ CREATION DES COULEURS SELECTIONNABLES ET AFFICHAGE SUR LE COTE DROIT ------>//
		public static Color choixCouleur(Grille grille){	
			StdDraw.setPenColor(Color.black);
			StdDraw.filledRectangle(grille.dimensionX+2, grille.dimensionY-3.5, 1, 3.8);		
			Color[] couleurPossible = {Color.red, Color.orange, Color.yellow, Color.green, Color.blue, Color.magenta/*, Color.gray*/};
			int longueur = grille.tableau.length;		
			for(int i=0; i<6; i++){			
				Color couleur = couleurPossible[i];			
				if(Analyse.couleurDisponible(couleur)){
					Grille.afficherImage(longueur + 2, longueur - (i+1), couleur);
				}
			}
			Color CouleurChoisie;		
			while(true){			
				if(StdDraw.mousePressed()){								
					double X = StdDraw.mouseX();
					double Y = StdDraw.mouseY();
					if(longueur + 1.5 < X && X < longueur +  2.5){					
						if(longueur - 1.5 < Y && longueur - 2.5 < Y){
							CouleurChoisie = couleurPossible[0];
							//System.out.println("red");
							//Vérification pour debug//
							break;	
						}
						if(longueur - 2.5 < Y && longueur - 3.5 < Y){
							CouleurChoisie = couleurPossible[1];
							//System.out.println("orange");
							//Vérification pour debug//
							break;	
						}
						if(longueur - 3.5 < Y && longueur - 4.5 < Y){
							CouleurChoisie = couleurPossible[2];
							//System.out.println("yellow");
							//Vérification pour debug//
							break;	
						}
						if(longueur - 4.5 < Y && longueur - 5.5 < Y){
							CouleurChoisie = couleurPossible[3];
							//System.out.println("green");
							//Vérification pour debug//
							break;	
						}
						if(longueur - 5.5 < Y && longueur - 6.5 < Y){
							CouleurChoisie = couleurPossible[4];
							//System.out.println("blue");
							//Vérification pour debug//
							break;	
						}
						if(longueur - 6.5 < Y && longueur - 7.5 < Y){
							CouleurChoisie = couleurPossible[5];
							//System.out.println("magenta");
							//Vérification pour debug//
							break;	
						}
						/*if(longueur - 7.5 < Y && longueur - 8.5 < Y){
							CouleurChoisie = couleurPossible[6];
							//System.out.println("gray");
							//Vérification pour debug//
							break;	
						}*/
					}				
				}			
			}
			return CouleurChoisie;		
		}
		//<------ CREATION DES COULEURS SELECTIONNABLES ET AFFICHAGE SUR LE COTE DROIT ------>//
		
		//<----- MESSAGE D'ERREUR EN CAS DE MAUVAIS CHOIX DE COULEUR ------->//
		public static Color affichageImpossibilitéChoix(Grille grille){
			Color CouleurChoisie;		
			while(true){			
				CouleurChoisie = choixCouleur(grille);			
				if (Analyse.couleurDisponible(CouleurChoisie)){
					StdDraw.setPenColor(Color.white);
					StdDraw.filledRectangle(grille.dimensionX/2, grille.dimensionY +1, 10, 1);				
					break;
				}
				else{
					StdDraw.setPenColor(Color.red);
					StdDraw.text(grille.dimensionX/2, grille.dimensionY +1 , "Vous ne pouvez pas choisir cette couleur");
				}
			}
			return CouleurChoisie;
		}
		//<----- MESSAGE D'ERREUR EN CAS DE MAUVAIS CHOIX DE COULEUR ------->//
		
	//<------- AFFICHAGE DU CHOIX DES COULEURS ------->//
		
	//<----------- IMPLEMENTATION DES CASES DEPART DES JOUEURS---------->//
		public static void caseDepart(Joueur[] joueurs, Grille grille){
			
			for(Joueur joueur : joueurs){
				grille.tableau[joueur.DepartX][joueur.DepartY].joueur = joueur;
			}
		}
	//<----------- IMPLEMENTATION DES CASES DEPART DES JOUEURS---------->//		
		
	//<-----INDIQUE LES CASES POSSEDEES PAR LE JOUEUR------>//
		public static void possession(Joueur joueur, Joueur[] joueurs, Grille grille){			
			for(int i=0; i<grille.tableau.length; i++){
				for(int j=0; j<grille.tableau[0].length; j++){
					if(grille.tableau[i][j].joueur != null){
						if (Case.affichage == true) {
							if(grille.tableau[i][j].joueur == joueur){
								//Cases possédées par le joueur ayant la main//
								StdDraw.setPenColor(Color.white);
								StdDraw.filledCircle(i, j, 0.2);
							}
							else{
								//Cases possédées par le joueur n'ayant pas la main//
								StdDraw.setPenColor(Color.black);
								StdDraw.filledCircle(i, j, 0.2);
							}
						}
					}
				}
			}
			
		}
	//<-----INDIQUE LES CASES POSSEDEES PAR LE JOUEUR------>//
		
	//<---------AFFICHAGE DES SCORES ET DU TOUR DU JOUEUR----->>
		public static void score(Joueur[] joueurs, Joueur TourJoueur, Grille grille){		
			StdDraw.setPenColor(Color.white);
			StdDraw.filledRectangle(grille.dimensionX/2, -2.5, grille.dimensionY, 1);		
			int compteur = (grille.dimensionX)/(2*joueurs.length)-3;		
			for (Joueur joueur : joueurs){
				if(joueur == TourJoueur){
					StdDraw.setPenColor(Color.red);
					StdDraw.text(compteur, -2.5 , joueur.Nom + "  (Score : " + Analyse.score(joueur) + ")");
					StdDraw.setPenColor(Color.white);
					StdDraw.filledRectangle(grille.dimensionX/2, grille.dimensionY +3, 20, 1);	
					StdDraw.setPenColor(Color.black);
					StdDraw.text(grille.dimensionX/2, grille.dimensionY +3 , "C'est au tour de "+joueur.Nom +" de jouer.");
					
				}
				else{
					StdDraw.setPenColor(Color.black);
					StdDraw.text(compteur, -2.5 , joueur.Nom + "  (Score : " + Analyse.score(joueur) + ")");
				}
				compteur = compteur + (grille.dimensionX + 8)/joueurs.length;
			}	
		}
	//<---------AFFICHAGE DES SCORES ET DU TOUR DU JOUEUR----->>
		
		

}


