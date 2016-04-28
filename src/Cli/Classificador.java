package Cli;

import java.io.File;
import java.io.FileNotFoundException;

import org.hamcrest.core.IsNot;

import weka.classifiers.bayes.BayesNet;
import weka.core.Instance;
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

	
	
	public Classificador(String url) {
		setArquivo(url);
		setInstancias(this.dataBase);

		classificador = new BayesNet();
		try {
			classificador.buildClassifier(instancias);
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}
	
	
	public void listAtributos(){
		for (int i = 0; i < (getInstancias().numAttributes()); i++) {
			System.out.println( instancias.attribute(i) );
		}
	}

	
	public BayesNet getClassificador(){
		return classificador;
	}
	
	public Instances getInstancias(){
		return instancias;
	}
	
	public DataSource getDatabase(){
		return dataBase;
	}
	
	public void status() {
		System.out.println("Atributos: " + this.instancias.numAttributes());
	}

	
	/**
	 * Definindo index
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

	
	/** 							INCOMPLETA
	 * Cria uma nova instancia
	 * @return
	 */
	public Instance novaInstancia() {
		
		Instance newInst = new Instance( instancias.numAttributes() );
		newInst.setDataset(this.instancias);
		newInst.setValue(0, 0); // gravidez
		newInst.setValue(1, 180); // Concentração de glicose
		newInst.setValue(2, 72); // pressão diastólica
		newInst.setValue(3, 35);
		newInst.setValue(4, 0);
		newInst.setValue(5, 33.6);
		newInst.setValue(6, 0.627);
		newInst.setValue(7, 50); // idade
		
		if(instancias.checkInstance(newInst)){
			return newInst;
		}
		return null;
	}

	public double calssificar(Instance instancia) throws Exception{
		return classificador.classifyInstance( instancia );
	}
	
}
