package Cli.extra;

import java.util.Scanner;

import Cli.Classificador;
import weka.filters.SimpleStreamFilter;

public class AppMenu {

	private Scanner input;
	private Classificador cla;
	
	public AppMenu(){
		try {
			cla = new Classificador("data/diabetes.arff");
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage() );
		}
		input = new Scanner(System.in);
	}
	
	/**
	 * Switch principal
	 */
	public void execute(){
		int option = - 1;
		
		do {
			switch (option) {
			case 1:
				
				addInstance();
				option = menu();
				break;
			case 2:
				imprime("opção 2");
				option = menu();
				break;
			case 3: 
				imprime("opção 3");
				option = menu();
			default:
				option = menu();
				break;
			}
		} while (option != 0);
	}
	
	/**
	 * Primeiro menu no opções
	 * @return
	 */
	private int menu() {
	    System.out.println("-- Opções --");
	    System.out.println(
	        "Select an option: \n" +
	        "  1) Adicionar e classificar Instancia \n" +
	        "  0) Sair\n "
	    );
	    System.out.print(" >> "); 
	    int selection = input.nextInt();
	    input.nextLine();
	    return selection;
	}
	
	/**
	 * Imprimir os resultados com uma divisão padrão
	 * @param resposta
	 */
	private void imprime(String resposta){
		System.out.println("\n+----------------------------------------");
		System.out.println("|   " + resposta );
		System.out.println("+----------------------------------------\n");
	}
	
	/**
	 * Oção de inserir novo database
	 */
	private void addInstance(){
		 imprime("Digite o endereço do arquivo >> "); 
		 String valor = input.next();
		 
			try {
				System.out.println( cla.setInstance(valor) );
				
				cla.classificarLiteral( cla.getTestinstance() );
			} catch (Exception e) {
				System.out.println("Erro: " + e.getMessage());
			}
		
		 imprime(" ok ");
	}
	
}
