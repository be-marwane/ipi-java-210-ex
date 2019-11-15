package com.ipiecoles.java.java210;

import java.util.ArrayList;
import java.util.Scanner;

public class Sudoku {
	final public static String FIN_SAISIE="FIN";
	public static boolean resolu=false;
	public short sudokuAResoudre[][];

	/**
	 * Constructeur par défaut
	 */
	public Sudoku() {
		
		sudokuAResoudre= new short[9][9];
	}
	public short[][] getSudokuAResoudre () {
		return this.sudokuAResoudre;
	}
	
	public void setSudokuAResoudre (short table[][]) {
		this.sudokuAResoudre=table;
		
	}

	public static boolean ligneSaisieEstCoherente(String ligneSaisie) {
		
		if (ligneSaisie==null|| ligneSaisie.trim().isEmpty()){
			String message = "Les coordonnées du chiffre et/ou sa valeur ne peuvent pas être nulles, vides ou remplies avec des espaces\n";
			System.out.print(message);
			return false;
		}
		if(ligneSaisie.length()!=3) {
			String message = "Les coordonnées du chiffre et/ou sa valeur doit faire 3 caractères\n";
			System.out.print(message);
			return false;
		}
		int x,y,z;
		char x1,y1,z1;
		x1=ligneSaisie.charAt(0);
		y1=ligneSaisie.charAt(1);
		z1=ligneSaisie.charAt(2);
		x=Character.getNumericValue(x1);
		y=Character.getNumericValue(y1);
		z=Character.getNumericValue(z1);
		if((x<0 || x>8 )|| (y<0 || y>8 )||(z<1 || z>9 )) {
			String message = "L'abscisse et l'ordonnée doivent être compris entre 0 et 8, la valeur entre 1 et 9\n";
			System.out.print(message);
			return false;
		}
		
		return true;
	}
	
	/**
	 * Cette méthode invite l'utilisateur à saisir un ensemble de coordonnées pour initialiser un sudoku à résoudre.
	 * Les coordonnées prennent la forme XYZ avec X correspondant à l'abscisse, Y l'ordonnée et Z la valeur. Seules les
	 * chiffres présents sont à saisir et l'utilisateur doit appuyer sur entrée après chaque saisie. 
	 * Lorsqu'il a terminé sa saisie, il entre la chaîne FIN. La fonction remplit au fur et à mesure un tableau de String
	 * comportant les coordonnées des chiffres saisis.
	 * 
	 * A noter que pour chaque ligne saisie, sa cohérence est vérifiée en appelant la méthode ligneSaisieEstCoherente
	 * En cas de mauvaise saisie, la saisie ne doit pas être prise en compte et l'utilisateur doit pouvoir saisie une nouvelle ligne
	 * La fonction doit également gérer le cas où l'utilisateur ne rentre rien mais appuye sur Entrée
	 *
	 * @return Un tableau comportant les coordonnées des chiffres présents dans le sudoku à résoudre
	 */
	public static String[] demandeCoordonneesSudoku() {
		Scanner sc=new Scanner(System.in);
		String[] tableau=new String[81];
		String coordonnees;
		int i=0;
		do {coordonnees = sc.nextLine();
			if(ligneSaisieEstCoherente(coordonnees))
			{tableau[i]=coordonnees;
			i++;}
		}while(!coordonnees.equals(FIN_SAISIE) && i<81);
		
		return tableau;
	}
	
	/**
	 * La méthode prend un tableau de coordonnées de chiffre soud la forme XYZ avec X correspondant 
	 * à l'abscisse, Y l'ordonnée et Z la valeur et remplit le tableau sudokuAResoudre avec les bonnes valeurs
	 * au bon endroit. Ex 012, première ligne deuxième colonne, on met la valeur 2. Lorsqu'une valeur nulle est 
	 * rencontrée dans le tableau, on arrête le traitement
	 * 
	 * Pour passer d'une String à un short, on pourra utiliser la méthode stringToInt(string)
	 * 
	 * @param tableauCoordonnees
	 */
	public void remplitSudokuATrous(String[] tableauCoordonnees) {
		int x,y,z;
		char x1,y1,z1;
		for(int i=0;i<tableauCoordonnees.length;i++) {
			x1=tableauCoordonnees[i].charAt(0);
			y1=tableauCoordonnees[i].charAt(1);
			z1=tableauCoordonnees[i].charAt(2);
			x=Character.getNumericValue(x1);
			y=Character.getNumericValue(y1);
			z=Character.getNumericValue(z1);
			sudokuAResoudre[x][y]=(short)z;
		}
		
    }
	
	private int stringToInt(String s) {
		return Integer.parseInt(s);
	}
	
	/**
	 * Cette méthode affiche un sudoku de manière formatée sur la console.
	 * Cela doit ressembler exactement à :
	 * -----------------------
	 * |   8   | 4   2 |   6   |
	 * |   3 4 |       | 9 1   |
	 * | 9 6   |       |   8 4 |
	 *  -----------------------
	 * |       | 2 1 6 |       |
	 * |       |       |       |
	 * |       | 3 5 7 |       |
	 *  -----------------------
	 * | 8 4   |       |   7 5 |
	 * |   2 6 |       | 1 3   |
	 * |   9   | 7   1 |   4   |
	 *  -----------------------
	 * 
	 * @param sudoku tableau de short représentant les valeurs d'un sudoku (résolu ou non). 
	 * Ce tableau fait 9 par 9 et contient des chiffres de 0 à 9, 0 correspondant à une valeur 
	 * non trouvée (dans ce cas, le programme affiche un blanc à la place de 0
	 */
	public static void ecrireSudoku(short[][] tableau) {
		String[][] sudoku=new String[9][9];
		for(int i=0;i<tableau.length;i++){
			for(int j=0;j<tableau.length;j++) {
				if(tableau[i][j]==0) {
					sudoku[i][j]=" ";
				}
				else
					sudoku[i][j]=Short.toString(tableau[i][j]);
			}
			
		}
	
		for(int i=0;i<sudoku.length;i++) {
			if(i%3==0) {System.out.print(" -----------------------\n");}
			for(int j=0;j<sudoku.length;j++) {
				if(j%3==0) {
					System.out.print("| "+sudoku[i][j]);
				}
				else if(j%3==2) {System.out.print(" "+sudoku[i][j]+" ");}
				else {System.out.print(" "+sudoku[i][j]);}
			}
			System.out.print("|\n");
		}
		System.out.print(" -----------------------");
		
		
    }
	public static void main(String[] args) {
		short[][] sudoku= {
			    {6, 3, 0, 0, 1, 0, 0, 0, 0},
			    {7, 0, 0, 0, 9, 0, 0, 0, 0},
			    {0, 6, 0, 0, 5, 0, 1, 0, 6},
			    {0, 0, 1, 0, 0, 0, 3, 7, 0},
			    {0, 0, 2, 0, 7, 3, 0, 0, 0},
			    {0, 7, 0, 0, 0, 0, 4, 0, 0},
			    {0, 0, 0, 0, 0, 6, 2, 0, 0},
			    {0, 0, 0, 1, 0, 0, 0, 8, 4},
			    {9, 5, 0, 0, 0, 0, 0, 0, 0},
			};

	

	}
	
	/**
	 * Cette méthode vérifie si un chiffre est autorisé à la position d'abscisse et
	 * d'ordonnée donnés dans le sudoku en appliquant les règles suivantes : 
	 * 
	 * 1 : Si la valeur est déjà dans la ligne, le chiffre n'est pas autorisé
	 * 2 : Si le valeur est déjà dans la colone, le chiffre n'est pas autorisé
	 * 3 : Si la valeur est est déjà dans la boite, le chiffre n'est pas autorisé
	 * 
	 * @param abscisse
	 * @param ordonnee
	 * @param chiffre
	 * @param sudoku
	 * @return
	 */
	public static boolean estAutorise(int abscisse, int ordonnee, short chiffre, short[][] sudoku) {
		for(int i=0;i<9;i++) {
			if (chiffre==sudoku[i][ordonnee]||chiffre==sudoku[abscisse][i]) {
				return false;
			}
			}
		for(int i=(abscisse/3)*3;i<((abscisse/3)*3)+3;i++) {
			for(int j=((ordonnee)/3)*3;j<((ordonnee/3)*3)+3;j++){
				if(chiffre==sudoku[i][j]) {
					return false;
				}}}	

		return true;
    }

	public static boolean resoudre(int abscisse, int ordonnee, short[][] sudoku) {
		if (abscisse==9) { 
			return true; 
		}
		
		int abscisseSuivant,ordonneeSuivant;
		if (ordonnee==8) {
			abscisseSuivant = abscisse+1;
			ordonneeSuivant=0; 
		}else{ 
			abscisseSuivant = abscisse;
			ordonneeSuivant = ordonnee+1; 
		}
	    	
			
		if(sudoku[abscisse][ordonnee]!=0) {
			if(resoudre(abscisseSuivant,ordonneeSuivant,sudoku)) {
				return true;
			}
		}else {
			for(short k=1;k<=9;k++) {
				if(estAutorise(abscisse,ordonnee,k,sudoku)){
					sudoku[abscisse][ordonnee]=k;
					if(resoudre(abscisseSuivant,ordonneeSuivant,sudoku)) {
						return true;
					}else{ 
						sudoku[abscisse][ordonnee]=0;
					}
				}
			}
		}
		return false;					
    }
}