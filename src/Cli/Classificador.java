package Cli;

import java.io.File;
import java.io.IOException;

import weka.classifiers.bayes.BayesNet;
import weka.core.Attribute;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.supervised.attribute.Discretize;

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
	private Discretize filter;
	
	
	public Classificador(String urlFileDataBase) throws Exception{
		filter = new Discretize();
		
		if( setFileDataBase(urlFileDataBase) ){
			dataBase = new DataSource(this.urlFileDataBase);
			instances = dataBase.getDataSet();
			//filter.setInputFormat(instances);
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
	
	
	
	/**
	 * Classifica uma instância e da como responsta o atributo index, na forma 
	 * escrita.
	 * @param instancia
	 * @throws Exception
	 */
	public void classificarLiteral(Instance instancia) throws Exception{
		int indexAtt = instances.numAttributes() - 1;
		Attribute att = instances.attribute( indexAtt );
		int n = (int) classificar(instancia);
		System.out.println(att.value(n));
	}
	
	
	
	/**
	 * Retorna a ultima instancia de um arquivo .arff
	 * @param urlFile
	 * @return
	 * @throws Exception
	 */
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
	
	/**
	 * Transforma um array de string e uma instancia
	 * @param values
	 * @return
	 */
	public Instance setInstance(String[] values){
		int nAttributos = instances.numAttributes();
		Instance newInst = new Instance(nAttributos);
		newInst.setDataset(instances);
		
		for (int i = 0; i < nAttributos; i++) {
			if( values[i] != null ){
				try {
					double val = Double.parseDouble(values[i]);
					newInst.setValue(i, val);
				} catch (Exception e) {	
					newInst.setValue(i, values[i]);
				}
			}
		}
		return newInst;
	}	
	
	/**
	 * salva todas as instancias carregadas para o arquivo de base de dados
	 * @throws IOException
	 */
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
