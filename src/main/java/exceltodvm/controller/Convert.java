package exceltodvm.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
	private static final Logger logger = LogManager.getLogger(Convert.class);
	
	public Convert(String rutaArchivo) {
		try (InputStream excel = new FileInputStream(new File(rutaArchivo))) {
			try (XSSFWorkbook worbook = new XSSFWorkbook(excel)) {
	    		XSSFSheet sheet = worbook.getSheetAt(0);
	    		
	    		FormulaEvaluator formulaEvaluator = worbook.getCreationHelper().createFormulaEvaluator();
	    		
	    		fila = new ArrayList<>();
	    		List<Row> rowList = StreamSupport.stream(sheet.spliterator(), false).collect(Collectors.toList());
	    		rowList.stream().flatMap(row -> StreamSupport.stream(row.spliterator(), false))
	    					.map(cell -> cell.getStringCellValue())
	    					.forEach(System.out::println);
	    		//logger.debug(r);
	    		/**
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
		    	            	
		    	            //	if(cell.getRow().getLastCellNum()==1)
		    	            //		fila.add("<cell>WARNING: cell "+(cell.getRow().getRowNum() + 1)+", column "+cell.getRow().getLastCellNum()+" is BLANK</cell>");
		    	            //	else
		    	            //		fila.add("<cell>WARNING: cell "+(cell.getRow().getRowNum() + 1)+", column "+cell.getRow().getLastCellNum()+" is BLANK</cell>");
		    	            //    break;
	    				}
	    			}
		
	    			fila.add("</row>");
	    			dvm = new DVM();
	    			dvm.setRow(fila);
	    			
	    		}
	    		*/
			}
    		
    	} catch (Exception e) {
    		logger.error("ERROR: "+e.getMessage());
    	}      
	}
	public StringBuilder getAllRowDVMFormat() {
		StringBuilder dvmBody = new StringBuilder();
		dvm.getData().stream().flatMap(numFila -> numFila.stream()).forEach(cell -> dvmBody.append(cell).append(System.lineSeparator()));
		return dvmBody;
	}
	public StringBuilder getAllDocument() {
		StringBuilder dvmDocument = new StringBuilder();
		int lengthColumnHead = dvm.getData().get(0).stream().mapToInt(t -> t.length()).sum();
		dvmDocument.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>").append(System.lineSeparator())
			.append("<dvm name=\""+dvm.getNombre()+"\" xmlns=\"http://xmlns.oracle.com/dvm\">").append(System.lineSeparator())
			.append("<description>"+dvm.getDescripcion()+"</description>").append(System.lineSeparator())
			.append("<columns>").append(System.lineSeparator());
		dvm.getData().get(0).stream()
			.filter(cell -> cell.contains("<cell>"))
			.forEach(column -> dvmDocument.append("<column name=\""+column.substring(6, column.length()-7)+"\"/>"));
		dvmDocument.append("</columns>").append(System.lineSeparator())
			.append("<rows>").append(getAllRowDVMFormat().delete(0, lengthColumnHead)).append("</rows>")
			.append("</dvm>");
		//logger.info(dvmDocument);
		return dvmDocument;
	}
	public void saveDocumentFile(File file) {
		try(FileOutputStream fOut = new FileOutputStream(file)) {
			fOut.write(getAllDocument().toString().getBytes());
	        fOut.flush();
		} catch (FileNotFoundException e) {
			logger.error("File Error: %s", e);
		} catch (IOException e) {
			logger.error("Error: %s", e);
		}
        
	}
}