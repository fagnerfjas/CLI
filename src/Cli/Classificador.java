package Cli;

import java.io.File;
import java.io.FileNotFoundException;

import weka.classifiers.bayes.BayesNet;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

/**
 * @version 0.1 Classificador Lia IFPB, Campina Grande, 2016
 * @author Fagner Jefferson
 *
 */
public class Classificador {

	private DataSource dataBase;
	private Instances instancias;
	private BayesNet classificador;

	public void Classificador(String urlArquivo) throws Exception {
		setArquivo(urlArquivo);
		setInstancias(this.dataBase);
		
		classificador = new BayesNet();
		classificador.buildClassifier(instancias);
	}
	
	public void status(){
		System.out.println(
				"Atributos: " + this.instancias.numAttributes()
				);
	}
	
	/**
	 * Definindo index
	 * 
	 * @param index
	 */
	private void setIndex(int index) {
		this.instancias.setClassIndex(index);
	}

	/**
	 * Carregar instâncias, e define o index como sendo o ultimo atributo.
	 * 
	 * @param dataBase
	 */
	private void setInstancias(DataSource dataBase) {
		try {
			this.instancias = this.dataBase.getDataSet();
			setIndex(this.instancias.numAttributes() - 1);
		} catch (Exception e) {
			System.out.println("erro: " + e.getMessage());
		}
	}

	/**
	 * Carrega o arquivo de base de dados para treinamento do algoritmo
	 * 
	 * @param urlFile
	 * @throws Exception
	 */
	public void setArquivo(String urlArquivo) {
		File arquivo = new File(urlArquivo);
		if (arquivo.exists()) {
			try {
				this.dataBase = new DataSource(urlArquivo);
			} catch (Exception e) {
				System.out.println("Erro: " + e.getMessage());
			}
		} else {
			System.out.println("Arquivo não encontrado!!!");
		}
	}
}
