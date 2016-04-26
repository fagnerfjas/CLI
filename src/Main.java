import Cli.Classificador;

/**
 * @version 0.1 Classificador Lia IFPB, Campina Grande, 2016
 * @author Fagner Jefferson
 *
 */


public class Main {

	public static void main(String[] args) {
		Classificador clas = new Classificador();
		System.out.println("olá");
		clas.setArquivo("url/arquivo.arff");
	}
}
