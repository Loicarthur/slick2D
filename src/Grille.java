import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Grille {
	/*
	 * Creation de la classe Grille qui va modeliser le support des tuiles
	 */

	private int case1[] = new int[2];
	private int case2[] = new int[2];
	private static int nbtentatives;
	private static int score;
	private Tuiles cartes[][] = new Tuiles[4][4];
	private Tuiles cases[][] = new Tuiles[4][4];

	// ArrayList <Tuiles> tuiles = new ArrayList<Tuiles>();

	// Constructeur sans parametres
	public Grille() throws SlickException {
		init();

		case1 = null;
		case2 = null;
		nbtentatives = 0;
		score = 0;
	}

	/*
	 * Methode qui va initialiser les 16 tuiles sur la grille et le tableau  des cartes va
	 * se charger de dessiner toute les 16 images en fonction qu'elles soient
	 * retournées ou non Tandis que le tableau cases va se charger de dessiner les
	 * carrés arrondis apres que les images soient retirés de la grille Aussi, le
	 * tableau tab contient les 8 types d'images distinct en doublons donc 16 types
	 */
	public void init() throws SlickException {

		int[] tab = alea();
		for (int ligne = 0, i = 0, x = 20; ligne < 4; ligne++, x += 120) {
			for (int colonne = 0, y = 60; colonne < 4; colonne++, y = y + 120, i++) {

				cartes[ligne][colonne] = new Tuiles(x + 5, y + 5, tab[i]);
				cases[ligne][colonne] = new Tuiles(x + 5, y + 5, tab[i]);
			}

		}

	}

	/*
	 * La méthode alea permet de generer aleatoirement 8 chiffres entre 0 et 7 qui
	 * representerons les cases de notre switch dans la classe Tuiles Mais ces 8 types
	 * seront doubles vu que tabtype est de taille 16 et elle renverra un tableau
	 */
	public int[] alea() {
		
		int[] tabtype = new int[16];
		for (int i = 0; i < tabtype.length; i++) {
			int cpt = 0;
			tabtype[i] = (int) (Math.random() * 8);
			for (int j = 0; j <= i; j++) {
				if (tabtype[j] == tabtype[i]) {
					cpt++;
				}

			}
			if (cpt > 2) {
				i--;
			}

		}
		return tabtype;
	}

	// Getters & setters
	public int[] getCase1() {
		return case1;
	}

	public void setCase1(int[] case1) {
		this.case1 = case1;
	}

	public int[] getCase2() {
		return case2;
	}

	public void setCase2(int[] case2) {
		this.case2 = case2;
	}

	public static int getNbtentatives() {
		return nbtentatives;
	}

	public static void setNbtentatives(int nbtentatives) {
		Grille.nbtentatives = nbtentatives;
	}

	public static int getScore() {
		return score;
	}

	public static void setScore(int score) {
		Grille.score = score;
	}

	public Tuiles[][] getCartes() {
		return cartes;
	}

	public void setCartes(Tuiles[][] cartes) {
		this.cartes = cartes;
	}

	public Tuiles[][] getCases() {
		return cases;
	}

	public void setCases(Tuiles[][] cases) {
		this.cases = cases;
	}

	// La méthode initCases permet de dessiner les cases sur lesquelles seront
	// deposées les tuiles
	public void initCases(Graphics g) {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				g.setColor(Color.black);
				g.fillRect(cases[i][j].getNbLignes(), cases[i][j].getNbColonne(), 100, 100);
			}
		}
	}

	/*
	 * Ici, on dessine les Tuiles a l'aide de la methode dessiner de la classe
	 * Tuiles également d'afficher le score et le nombre de tentatives
	 */

	public void dessinerTuiles(Graphics g) {
		initCases(g);

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (cartes[i][j] != null) {
					cartes[i][j].dessiner(g);
				}
			}
		}
		g.setColor(Color.white);
		g.drawString("Tentatives :\n " + nbtentatives, 100, 30);
		g.drawString("Score :\n " + score, 0, 30);
	}

	/*
	 * La methode quelleCase qui renvoie les coordonnées de la case (dans la grille)
	 * contenant les coordonnées (x,y) dans la fenetre. Par exemple,
	 * quelleCase(26,42) devra renvoyer 0,0, car les coordonnées (26,42) de la
	 * fenetre appartiennent a la case(0,0) .
	 */

	public int[] quelleCase(int x, int y) {// pour les coordonner de la souris
		int[] tab = { -1, -1 };
		for (int i = 0; i < 4; i++) {
			if (y >= 60 + i * (120) && y <= 60 + i * (120) + 120) {
				tab[1] = i;
			}
			if (x >= 20 + i * (120) && x <= 20 + i * (120) + 120) {
				tab[0] = i;

			}
		}

		return tab;
	}

	/*
	 * Une méthode void clicCase1(int x,int y) qui prend en parametres les
	 * coordonnées d'un clic souris (dans le repere de la fenetre), et remplit case1
	 * avec les coordonnées de la case correspondante,et retourne la Tuile
	 */

	public void clicCase1(int x, int y) {
		int[] tb = quelleCase(x, y); // Mettre les coordonnées de la case choisi dans tb
		if (tb[0] != -1 && tb[1] != -1 && cartes[tb[0]][tb[1]] != null)
			case1 = tb;
		cartes[tb[0]][tb[1]].setRetourner(true);// retourne la piece cliquée

	}

	/*
	 * Une méthode void clicCase2(int x,inty) qui prend en parametres les
	 * coordonnées d'un clic souris (dans le repere de la fen�tre), et remplit case1
	 * avec les coordonnées de la case correspondante,et retourne la Tuile
	 */

	public void clicCase2(int x, int y) {
		int[] tb = quelleCase(x, y);
		// on verifie que l'emplacement du 2eme clic est differente de la premiere et
		// que
		if (tb[0] != case1[0] || tb[1] != case1[1] && cartes[tb[0]][tb[1]] != null) {
			if (tb[0] != -1 && tb[1] != -1) {
				case2 = tb;
				cartes[tb[0]][tb[1]].setRetourner(true);// retourne la piece cliquée
			}
		}
	}

	/*
	 * Ici on compare les deux cartes selectioné0
	 * 
	 * ++++++++++++++
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * +
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * es en fonction du type de la tuile ;
	 * si elles sont égales elles sont supprimées de la grille et on icremente le
	 * score et sinon elles sont retournées au dos et on incremente la tentative
	 */
	
	public void comparerCartes() throws InterruptedException {
		Thread.sleep(1000);/*
							 * Pour gerer le temps le temps apres que deux cartes se soient retournées ici 1
							 * seconde
							 */
		if (cartes[case1[0]][case1[1]].getType() == cartes[case2[0]][case2[1]].getType()) {
			cartes[case1[0]][case1[1]] = null;
			cartes[case2[0]][case2[1]] = null;
			score++;
		} else {
			cartes[case1[0]][case1[1]].setRetourner(false);
			cartes[case2[0]][case2[1]].setRetourner(false);
			nbtentatives++;
		}
		case1 = null;
		case2 = null;
	}

	// Verifie si toute les cartes sont supprimées de la grille
	public boolean isGrilleTermine() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (cartes[i][j] != null)
					return false;
			}

		}
		return true;
	}

}
