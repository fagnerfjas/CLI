package Cli.extra;

import java.io.File;
import java.io.IOException;

import jxl.Cell;
import jxl.CellType;
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
	
	public void showData(){
		Sheet folha = loadFile();
		for (int i = 0; i < folha.getColumns(); i++) {
			for (int j = 0; j < folha.getRows(); j++) {
				
				Cell cell = folha.getCell(j, i);
				CellType type = cell.getType();
				
				if (type == CellType.LABEL) {
					System.out.println("I got a label " + cell.getContents());
				}
	
				if (type == CellType.NUMBER) {
					System.out.println("I got a number "+ cell.getContents());
				}
			}
		}
	}
	
	public String getInputFile(){
		return this.inputFile;
	}
	
	public void setIinputFile(String inputFile){
		this.inputFile = inputFile;
	}
	
}
