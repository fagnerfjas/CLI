package Cli.extra;

import java.io.File;
import java.io.IOException;

import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;


public class FileManager {
	
	private String inputFile;
	
	public FileManager(String inputfile){
		setIinputFile(inputfile);
	}
	
	
	
	
	/**
	 * pega o arquivo que foi passado no construtor e retorna um objeto do tipo Sheet.
	 * @return
	 */
	private Sheet loadFile(){
		File file = new File( getInputFile() );
		try {
			Workbook wb = Workbook.getWorkbook(file);
			return wb.getSheet(0);
		} catch (BiffException | IOException e) {
			System.out.println("Erro: " + e.getMessage());
		}
		return null;
	}
	
	public String getInputFile(){
		return this.inputFile;
	}
	
	public void setIinputFile(String inputFile){
		this.inputFile = inputFile;
	}
	
}
