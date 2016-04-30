import Cli.Classificador;
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
		
		String url = "data/diabetes.arff";
		String excel = "data/instancia_teste.xls";
		//classificador = new  Classificador(url);
		file = new FileManager(excel);
		
		
		//Instance newInst = classificador.novaInstancia();
		
		//classificador.listAtributos();
		
		file.showData();
	}
}

