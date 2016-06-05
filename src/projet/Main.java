package projet;


import java.awt.Color;
import java.util.Scanner;



public class Main{

//<------GENERATION DE LA GRILLE ET DES JOUEURS------->//
	public static void main(String[] args) {
		System.out.println("Bienvenue sur le jeu des six couleurs de Nicolas et Rapha�lle !");
		System.out.print("Choisissez une taille de grille pour commencer � jouer : ");
		int tailleGrille = new Scanner(System.in).nextInt();
		Grille grille = new Grille(tailleGrille,tailleGrille);		
		grille.grilleAleatoire();
		System.out.print("Entrez le nombre de joueur souhait� (de 1 � 4) : ");
		int NBJ = new Scanner(System.in).nextInt();
		Joueur[] participants = creation(NBJ, grille);	
		int numeroJ=0;
		for (Joueur actuel : participants){
			numeroJ=numeroJ+1;
			System.out.print("Entrez le nom du "+actuel.Nom+": ");				
			String nom = new Scanner(System.in).nextLine();
			actuel.Nom = nom;
			System.out.println("Bienvenue "+actuel.Nom+" ! Vous �tes le joueur num�ro " +numeroJ+".");
		}	
		
		jeu(grille, participants);
	}
	
	public static Joueur[] creation(int NBJ, Grille grille){
		Joueur[] tableauJoueur=new Joueur[NBJ];
		int[][] positionDeDepart = {{0,0}, {grille.dimensionX-1, grille.dimensionY-1}, {grille.dimensionX-1, 0}, {0, grille.dimensionY-1}};
		for (int i = 0; i <NBJ; i++){
			boolean reponse = true;
			while(reponse){
				System.out.print("Souhaitez-vous que le joueur "+(i+1)+" soit une intelligence artificielle ? (oui/non) ");
				String ordi = new Scanner(System.in).nextLine();
				if (ordi.equals("non")){
					reponse=false;
					tableauJoueur[i]=new Joueur("Joueur " +(i+1), (i+1), positionDeDepart[i][0], positionDeDepart[i][1], false, 0, true, true);
				}
				else if (ordi.equals("oui")){
					reponse=false;{
					System.out.print("Choisissez la difficult� de l'IA (0 � 2) : ");
					int diffIA = new Scanner(System.in).nextInt();
					tableauJoueur[i]=new Joueur("Joueur " +(i+1), (i+1), positionDeDepart[i][0], positionDeDepart[i][1], true, diffIA, true, true);
					}
				}
				else{
					System.out.println("R�ponse impossible.");
				}
			}
		}
		return tableauJoueur;
	}

//<------GENERATION DE LA GRILLE ET DES JOUEURS------->//
	
//<------DEROULEMENT DU JEU----->//
	public static void jeu(Grille grille, Joueur[] joueursTableau){		
		Affichage.caseDepart(joueursTableau, grille);		
		while(true){			
			boolean continuer = true;			
			for (Joueur joueur : joueursTableau){	
				
				grille.afficherGrille();				
				Affichage.possession(joueur, joueursTableau, grille);
				Affichage.score(joueursTableau, joueur, grille);				
				Color affichageImpossibilit�ChoixCouleurJoueur = Color.black;				
				if(joueur.ordinateur){
					if(joueur.difficulte == 0){
						affichageImpossibilit�ChoixCouleurJoueur = IA.IADifficulte0(joueur, grille);
					}
					else if(joueur.difficulte == 1){
						affichageImpossibilit�ChoixCouleurJoueur = IA.IADifficulte1(joueur, grille);
					}
					else if(joueur.difficulte == 2){
						affichageImpossibilit�ChoixCouleurJoueur = IA.IADifficulte2(joueur, grille);
					}
				}
				else{
					affichageImpossibilit�ChoixCouleurJoueur = Affichage.affichageImpossibilit�Choix(grille);
				}				
				grille.actualiserGrille(affichageImpossibilit�ChoixCouleurJoueur, joueur);								
				continuer = Analyse.conditionDeVictoire(joueur, grille);				
				if(!continuer){
					break;
				}					
			}			
			if(!continuer){
				break;
			}			
		}
		grille.afficherGrille();
		//fin de la partie//
	}
//<------DEROULEMENT DU JEU----->//
	
	
}