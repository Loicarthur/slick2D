import java.awt.Color;
import java.awt.Font;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;

public class Memory extends BasicGame {

	
	Grille grille;
    Music music;
	public Memory(String title) {
		super(title);
		// TODO Auto-generated constructor stub
	}

	/*
	 * La méthode render() est quant a  elle dédiée au rendu visuel de notre
	 * application 
	 */
	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		grille.dessinerTuiles(g);
		
		if (grille.isGrilleTermine()) {
		g.drawString("jeu terminé", 100, 100);
		}
	}

	/*
	 * Comme son nom l'indique, la méthode init() est chargée de regrouper
	 * l'ensemble des instructions et d'exécuter  a l'initialisation du jeu
	 */
	@Override
	public void init(GameContainer arg0) throws SlickException {
		// TODO Auto-generated method stub

		grille = new Grille();
       // music = new Music("aa");
       // music.setVolume(0.5f);
      //  music.loop();
	}

	/*
	 * Pour finir, la méthode update() est chargée de gérer la dynamique du jeu,
	 * c'est a dire d'appliquer du mouvement aux objets, de réagir aux commandes du
	 * joueur, ou encore de gérer d'éventuelles collisions. C'est ici qu'on appelera
	 * les méthodes de la classe grille
	 */
	@Override
	public void update(GameContainer gc, int arg1) throws SlickException {
		// TODO Auto-generated method stub
		Input input = gc.getInput();

		
		

		// verifie si le 2eme clic est fait
		 if (grille.getCase2() == null) {
			if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {// attends le premier clic
				if (grille.getCase1() == null) {
					// appel de la methode clicCase1
					grille.clicCase1(input.getMouseX(), input.getMouseY());
				} else {
					// appel de la methode clicCase2
					grille.clicCase2(input.getMouseX(), input.getMouseY());
				}
			}
		} else {
			/*
			 * Ici on met la methode comparerCartes dans un try catch pour gérer toutes les
			 * eventuelles erreurs
			 */
			try {
				grille.comparerCartes();
			} catch (InterruptedException e) {  /*L’exception interrompue est généralement levée par tous les blocages 
				méthodes permettant de le traiter et d’exécuter les mesures correctives*/
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
