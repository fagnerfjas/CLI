package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Cli.Classificador;
import junit.framework.Assert;
import weka.core.Instance;
import weka.core.Instances;

public class TestCli {
	 
	public Classificador classificador;
	public Classificador classificador2;

	@Before
	public void make() throws Exception{

		classificador = new Classificador("data/weather.arff");
	}
	
	@Test
	public void test() throws Exception {
		Assert.assertTrue( classificador.setFileDataBase("data/weather.arff") );
		Instance instancia = classificador.setInstance("data/weather_instance.arff");
		System.out.println(instancia);
		System.out.println( classificador.classificar(instancia) );
		classificador.salvaInstancia();
		
	}

}
