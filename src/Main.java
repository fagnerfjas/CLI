import Cli.Classificador;
import Cli.extra.AppMenu;
import Cli.extra.FileManager;
import weka.core.Instance;

/**
 * @version 0.1 Classificador Lia IFPB, Campina Grande, 2016
 * @author Fagner Jefferson
 *
 */


public class Main {
	private static Classificador classificador;
	private static FileManager file;
	
	public static void main(String[] args) throws Exception  {
		AppMenu appMenu = new AppMenu();
		
		appMenu.execute();
	}
}

