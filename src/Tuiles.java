import java.util.ArrayList;
import java.util.Collections;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/* Création de la classe Tuiles qui 
	modeliser les 16 tuiles de la grille */

public class Tuiles {
	private int nbLignes;
	private int nbColonne;
	private int type; 
	
	/* Le type de la tuile permettra de pouvoir comparer deux tuiles lors de la
						 * comparaison apres qu'elles se soient retournées
						 */
	private static Image imgdefault; // variable qui gere l'image par defaut
	private Image imagedeFace; // variable qui gere l'image de face
	private boolean retourner;// booleen qui controle si une tuile est retournée

	/* Constructeur avec parametres qui va modeliser une tuile */

	public Tuiles(int nbLignes, int nbColonne, int type) throws SlickException {
		this.type = type;
		this.retourner = false;
		this.nbLignes = nbLignes;
		this.nbColonne = nbColonne;
		imgdefault = new Image("icons/def.jpg"); /*
		
													 * attribution de l'image de dessous présent dans le package "icons"
													 */

		/*
		 * J'utilise un switch case sur le type pour pouvoir attribuer a chaque tuile
		 * une image precise(en doublon)
		 */
		switch (type) {
		case 0:
			imagedeFace = new Image("icons/1.jpg");
			break;
		case 1:
			imagedeFace = new Image("icons/11.jpg");
			break;
		case 2:
			imagedeFace = new Image("icons/2.jpg");
			break;
		case 3:
			imagedeFace = new Image("icons/3.jpg");
			break;
		case 4:
			imagedeFace = new Image("icons/5.jpg");
			break;
		case 5:
			imagedeFace = new Image("icons/6.JPG");
			break;
		case 6:
			imagedeFace = new Image("icons/8.jpg");
			break;
		case 7:
			imagedeFace = new Image("icons/9.jpg");
			break;
		}

	}

	// Getters et setters

	public int getNbLignes() {
		return nbLignes;
	}

	public void setNbLignes(int nbLignes) {
		this.nbLignes = nbLignes;
	}

	public int getNbColonne() {
		return nbColonne;
	}

	public void setNbColonne(int nbColonne) {
		this.nbColonne = nbColonne;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public static Image getImgdefault() {
		return imgdefault;
	}

	public static void setImgdefault(Image imgdefault) {
		Tuiles.imgdefault = imgdefault;
	}

	public Image getImagedeDos() {
		return imagedeFace;
	}

	public void setImagedeDos(Image imagedeDos) {
		this.imagedeFace = imagedeDos;
	}

	public boolean isRetourner() {
		return retourner;
	}

	public void setRetourner(boolean retourner) {
		this.retourner = retourner;
	}

	/*
	 * methode pour dessiner chaque tuiles avec son image � un emplacement precis
	 * definit par nbLignes,nbColonnes
	 */

	public void dessiner(Graphics g) {
		if (retourner == true)
			g.drawImage(imagedeFace, this.nbLignes, this.nbColonne);
		else
			g.drawImage(imgdefault, this.nbLignes, this.nbColonne);
	}

}
