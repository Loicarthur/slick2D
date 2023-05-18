import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class MainMemory {

	public static void main(String[] args) throws SlickException {
		// TODO Auto-generated method stub

		Memory jeu = new Memory("Hello World");
		AppGameContainer app = new AppGameContainer(jeu, 550, 550, false);
		app.start();
	}

}
