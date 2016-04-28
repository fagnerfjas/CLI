import Cli.Classificador;
import weka.core.Instance;

/**
 * @version 0.1 Classificador Lia IFPB, Campina Grande, 2016
 * @author Fagner Jefferson
 *
 */


public class Main {
	private static Classificador classificador;

	public static void main(String[] args) throws Exception  {
		
		String url = "data/diabetes.arff";
		classificador = new  Classificador(url);
		
		Instance newInst = classificador.novaInstancia();
		
		classificador.listAtributos();
	}
}

