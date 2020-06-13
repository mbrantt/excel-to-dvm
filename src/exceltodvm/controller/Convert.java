package exceltodvm.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import exceltodvm.model.DVM;

public class Convert {
	private DVM dvm;
	private ArrayList<String> fila;
	private String value;
	
	public Convert(String rutaArchivo) {
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
		dvm.getData().stream().flatMap(numFila -> numFila.stream()).forEach(cell -> dvmBody.append(cell).append(System.lineSeparator()));
		return dvmBody;
	}
	public StringBuilder getAllDocument() {
		StringBuilder dvmHead = new StringBuilder();
		int lengthColumnHead = dvm.getData().get(0).stream().mapToInt(t -> t.length()).sum();
		dvmHead.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>").append(System.lineSeparator());
		dvmHead.append("<dvm name=\""+dvm.getNombre()+"\" xmlns=\"http://xmlns.oracle.com/dvm\">").append(System.lineSeparator());
		dvmHead.append("<description>"+dvm.getDescripcion()+"</description>").append(System.lineSeparator());
		dvmHead.append("<columns>").append(System.lineSeparator());
		dvm.getData().get(0).stream()
			.filter(cell -> cell.contains("<cell>"))
			.forEach(column -> dvmHead.append("<column name=\""+column.substring(6, column.length()-7)+"\"/>"));
		dvmHead.append("</columns>").append(System.lineSeparator());
		dvmHead.append("<rows>").append(getAllRowDVMFormat().delete(0, lengthColumnHead)).append("</rows>");
		dvmHead.append("</dvm>");
		return dvmHead;
	}
	public void saveDocumentFile(File file) {
		try(FileOutputStream fOut = new FileOutputStream(file)) {
			fOut.write(getAllDocument().toString().getBytes());
	        fOut.flush();
		} catch (FileNotFoundException e) {
			System.out.println("File Error: " + e);
		} catch (IOException e) {
			System.out.println("Error: " + e);
		}
        
	}
}
