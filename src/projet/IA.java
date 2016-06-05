package projet;

import java.awt.Color;
import java.util.ArrayList;

public class IA extends Grille {
	//<---------------------  SELECTION DES CASES PAR L'IA  -------------------------->//
	
		public IA(int dimensionX, int dimensionY) {
		super(dimensionX, dimensionY);
	}

		public static int selectionCasesParIA(Color couleurChoisie, Joueur joueur){
			
			ArrayList<Case> tableauCasePouvantEtreConqusieParCetteCouleur = new ArrayList<Case>();
			
			boolean continuer = true;
			
			while(continuer){
				
				continuer = false;
				
				for(int i=0; i<tableau.length; i++){
					for(int j=0; j<tableau[0].length; j++){
						
						Case caseij = tableau[i][j];
						
						if(caseij != null){
							if(caseij.couleur == couleurChoisie && Analyse.voisinPossede(i, j, joueur, tableauCasePouvantEtreConqusieParCetteCouleur) && caseij.joueur == null && !Analyse.caseExisteDejaDansTableau(tableauCasePouvantEtreConqusieParCetteCouleur, caseij)){
								tableauCasePouvantEtreConqusieParCetteCouleur.add(caseij);
								continuer = true;
							}
						}
					}
				}
			}
			
			return tableauCasePouvantEtreConqusieParCetteCouleur.size();
		}
		
		public static int selectionCasesParIASurDeuxTours(Color couleurChoisie, Color couleurChoisieDeuxiemeTour, Joueur joueur){
			
			ArrayList<Case> tableauCasePouvantEtreConqusieParCetteCouleur = new ArrayList<Case>();
			
			boolean continuer = true;
			
			while(continuer){
				
				continuer = false;
				
				for(int i=0; i<tableau.length; i++){
					for(int j=0; j<tableau[0].length; j++){
						
						Case caseij = tableau[i][j];
						
						if(caseij != null){
							if(caseij.couleur == couleurChoisie && Analyse.voisinPossede(i, j, joueur, tableauCasePouvantEtreConqusieParCetteCouleur) && caseij.joueur == null && !Analyse.caseExisteDejaDansTableau(tableauCasePouvantEtreConqusieParCetteCouleur, caseij)){
								tableauCasePouvantEtreConqusieParCetteCouleur.add(caseij);
								continuer = true;
							}	
						}
					}
				}
			}
			
			int nbCaseConquisePremierTour = tableauCasePouvantEtreConqusieParCetteCouleur.size();
			
			continuer = true;
			
			while(continuer){
				
				continuer = false;
				
				for(int i=0; i<tableau.length; i++){
					for(int j=0; j<tableau[0].length; j++){
						
						Case caseij = tableau[i][j];
						
						if(caseij != null){					
							if(caseij.couleur == couleurChoisieDeuxiemeTour && Analyse.voisinPossede(i, j, joueur, tableauCasePouvantEtreConqusieParCetteCouleur) && caseij.joueur == null && !Analyse.caseExisteDejaDansTableau(tableauCasePouvantEtreConqusieParCetteCouleur, caseij)){
								tableauCasePouvantEtreConqusieParCetteCouleur.add(caseij);
								continuer = true;
								
							}
						}
					}
				}
			}
			
			return tableauCasePouvantEtreConqusieParCetteCouleur.size()-nbCaseConquisePremierTour;
		}
		
		public static Color IADifficulte0(Joueur joueur, Grille grille){
			Color[] couleurPossible = {Color.red, Color.orange, Color.yellow, Color.green, Color.blue, Color.magenta};
			Color couleurAChoisir = Color.blue;
			for(Color couleurChoisie : couleurPossible){
				int nbAleatoire = (int) (Math.random() * 6);
				if(Analyse.couleurDisponible(couleurChoisie)){	
					if (nbAleatoire==0){
						Color choix = Color.red;
						couleurAChoisir = choix;
					}
					else if (nbAleatoire==1){
						Color choix = Color.orange;
						couleurAChoisir = choix;
					}
					else if (nbAleatoire==2){
						Color choix = Color.yellow;
						couleurAChoisir = choix;
					}
					else if (nbAleatoire==3){
						Color choix = Color.green;
						couleurAChoisir = choix;
					}
					else if (nbAleatoire==4){
						Color choix = Color.blue;
						couleurAChoisir = choix;
					}
					else if (nbAleatoire==5){
						Color choix = Color.magenta;
						couleurAChoisir = choix;
					}
				}
			}
			return couleurAChoisir;
			
		}

		public static Color IADifficulte1(Joueur joueur, Grille grille){		
			Color[] couleurPossible = {Color.red, Color.orange, Color.yellow, Color.green, Color.blue, Color.magenta};		
			double comparateur = -1;
			Color couleurACoisir = Color.blue;
			double nbCaseConquiseAvecCouleur;		
			for(Color couleurChoisie : couleurPossible){			
				if(Analyse.couleurDisponible(couleurChoisie)){				
					nbCaseConquiseAvecCouleur = selectionCasesParIA(couleurChoisie, joueur);				
					if( nbCaseConquiseAvecCouleur > comparateur){
						comparateur = nbCaseConquiseAvecCouleur;
						couleurACoisir = couleurChoisie;
					}
				}		
			}
			return couleurACoisir;
		}
		
		public static Color IADifficulte2(Joueur joueur, Grille grille){		
			Color[] couleurPossible = {Color.red, Color.orange, Color.yellow, Color.green, Color.blue, Color.magenta};
			double comparateur = -1;
			Color couleurACoisir = Color.blue;
			double nbCaseConquiseAvecCouleur;		
			for(Color couleurChoisie : couleurPossible){			
				if(Analyse.couleurDisponible(couleurChoisie)){				
					nbCaseConquiseAvecCouleur = selectionCasesParIA(couleurChoisie, joueur);
					for(Color deuxiemeCouleurChoisie : couleurPossible){					
						nbCaseConquiseAvecCouleur = nbCaseConquiseAvecCouleur + 0.9*selectionCasesParIASurDeuxTours(couleurChoisie, deuxiemeCouleurChoisie, joueur);	
					}			
					if( nbCaseConquiseAvecCouleur > comparateur){
						comparateur = nbCaseConquiseAvecCouleur;
						couleurACoisir = couleurChoisie;
					}
				}		
			}
			return couleurACoisir;
		}

	
}