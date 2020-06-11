package exceltodvm.controller;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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
		    	            	/*
		    	            	if(cell.getRow().getLastCellNum()==1)
		    	            		fila.add("<cell>WARNING: cell "+(cell.getRow().getRowNum() + 1)+", column "+cell.getRow().getLastCellNum()+" is BLANK</cell>");
		    	            	else
		    	            		fila.add("<cell>WARNING: cell "+(cell.getRow().getRowNum() + 1)+", column "+cell.getRow().getLastCellNum()+" is BLANK</cell>");
		    	                */
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
	public StringBuilder getAllRowDVMFormat() {
		StringBuilder dvmBody = new StringBuilder();
		dvm.getData().stream().flatMap(numFila -> numFila.stream()).forEach(column -> dvmBody.append(column).append(System.lineSeparator()));
		return dvmBody;
	}
	public void leerDVM() {
		
	}
}
