package exceltodvm.controller;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import exceltodvm.model.DVM;

public class Transformador {
	private DVM dvm;
	private ArrayList<String> fila;
	private String value;
	
	public Transformador(String rutaArchivo) {
		try (FileInputStream excel = new FileInputStream(new File(rutaArchivo))) {
			try (XSSFWorkbook worbook = new XSSFWorkbook(excel)) {
	    		XSSFSheet sheet = worbook.getSheetAt(0);
	    		dvm = new DVM();
	    		FormulaEvaluator formulaEvaluator = worbook.getCreationHelper().createFormulaEvaluator();
	    		for(Row row: sheet) {
	    			fila = new ArrayList<>();
	    			fila.add("<row>");
	    			for(Cell cell: row) {
	    				switch(formulaEvaluator.evaluateInCell(cell).getCellTypeEnum()) {
		    				case NUMERIC:
		    					value = Double.toString(cell.getNumericCellValue());
		    					String[] val = value.split("\\.");
		    					fila.add("<cell>"+val[0]+"</cell>");
		    	                break;
		    	            case STRING:
		    	            	value = cell.getStringCellValue();
		    					fila.add("<cell>"+value+"</cell>");
		    	                break;
		    	            default:
		    	                break;
	    				}
	    			}
	    			fila.add("</row>");
	    			dvm.setRow(fila);
	    		}
	    		
			}
    		
    	} catch (Exception e) {
    		System.out.println("ERROR: "+e.getMessage());
    	}      
	}
	public void leerFilas() {
		for(int numFila=0; numFila < dvm.getData().size(); numFila++) {
			for(int columna=0; columna < dvm.getData().get(numFila).size(); columna++) {
				System.out.println(dvm.getData().get(numFila).get(columna));
			}
		}
	}
	public void leerDVM() {
		
	}
}
