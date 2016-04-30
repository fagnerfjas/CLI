package Cli;

import java.io.File;
import java.io.IOException;

import weka.classifiers.bayes.BayesNet;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.ConverterUtils.DataSource;

/**
 * @version 0.1 Classificador Lia IFPB, Campina Grande, 2016
 * @author Fagner Jefferson
 *
 */
public class Classificador {

	private String urlFileDataBase;
	private DataSource dataBase;
	private Instances instances;
	private BayesNet classify;
	private Instance testInstance;
	
	
	public Classificador(String urlFileDataBase) throws Exception{
		if( setFileDataBase(urlFileDataBase) ){
			dataBase = new DataSource(this.urlFileDataBase);
			instances = dataBase.getDataSet();
			instances.setClassIndex( instances.numAttributes() - 1);
			classify = new BayesNet();
			classify.buildClassifier(instances);
			testInstance = instances.lastInstance();
		}
	}
	
	/**
	 * Seta um novo arquivo para base de dados
	 * @param urlFileDataBase
	 */
	public boolean setFileDataBase(String urlFileDataBase){
		File fileDataBase = new File(urlFileDataBase);
		if (fileDataBase.exists()) {
			this.urlFileDataBase = urlFileDataBase;
			return true;
		}
		System.out.println("Erro: Arquivo de base de dados não encontrado");
		return false;
	}
	
	/**
	 * Classifica uma nova instância e retorna o seu valor
	 * @param inst
	 * @return
	 * @throws Exception
	 */
	public double classificar(Instance inst) throws Exception{
		return this.classify.classifyInstance(inst);
	}
	/**
	 * Recebe o indice da instancia a ser classificada
	 * @param indexInstance
	 * @return
	 * @throws Exception
	 */
	public double classificar(int indexInstance) throws Exception{
		return classificar(this.instances.instance(indexInstance));
	}
	/**
	 * classifica a ultima instância da base de dados
	 * @return
	 * @throws Exception
	 */
	public double classificar() throws Exception{
		return classificar( this.instances.lastInstance() );
	}
	
	public Instance setInstance(String urlFile) throws Exception {
		File testFile = new File(urlFile);
		if(!testFile.exists()){
			return null;
		}
		
		DataSource dado = new DataSource(urlFile);
		Instances insts = dado.getDataSet();
		insts.setClassIndex(insts.numAttributes() - 1);
		this.instances.add(insts.lastInstance());
		return insts.lastInstance();
			
	}
	
	public void salvaInstancia() throws IOException{
		ArffSaver saver = new ArffSaver();
		saver.setInstances(this.instances);
		saver.setFile(new File(	this.urlFileDataBase ));
		//saver.setDestination(new File( this.urlFileDataBase ));  
		saver.writeBatch();
	}
	
	public Instances getInstances(){
		return this.instances;
	}
}
