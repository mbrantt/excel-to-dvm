package exceltodvm.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import exceltodvm.model.DomainValueMap;

public class Convert {
	private DomainValueMap archiveDVM;
	private ArrayList<String> fila;
	private static final Logger logger = LogManager.getLogger(Convert.class);
	
	public Convert(File fileToConvert) {
		try (InputStream excel = new FileInputStream(fileToConvert)) {
			try (XSSFWorkbook worbook = new XSSFWorkbook(excel)) {
	    		XSSFSheet sheet = worbook.getSheetAt(0);
	    		archiveDVM = new DomainValueMap();
	    		List<Row> sheetList = StreamSupport.stream(sheet.spliterator(), false).collect(Collectors.toList());
	    		sheetList.stream().map(rowList -> {
	    			List<Cell> cellList= StreamSupport.stream(rowList.spliterator(), false).collect(Collectors.toList());
	    			fila = new ArrayList<>();
	    			fila.add("<row>");
	    			cellList.forEach(cell -> fila.add("<cell>"+cell+"</cell>"));
	    			fila.add("</row>");
	    			archiveDVM.setDataRowList(fila);
	    			return cellList.stream();
	    		}).collect(Collectors.toList());
			}
    		
    	} catch (Exception e) {
    		logger.error("Error: " + e.getMessage());
    	}      
	}
	
	public StringBuilder getAllRowDVMFormat() {
		StringBuilder dvmBody = new StringBuilder();
		archiveDVM.getDataRowList().stream().flatMap(numFila -> numFila.stream()).forEach(cell -> dvmBody.append(cell).append(System.lineSeparator()));
		return dvmBody;
	}
	
	public StringBuilder getAllDocument(String name, String description) {
		archiveDVM.setName(name);
		archiveDVM.setDescription(description);
		StringBuilder dvmDocument = new StringBuilder();
		int lengthColumnHead = archiveDVM.getDataRowList().get(0).stream().mapToInt(t -> t.length()+2).sum();
		dvmDocument.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>").append(System.lineSeparator())
			.append("<dvm name=\""+archiveDVM.getName()+"\" xmlns=\"http://xmlns.oracle.com/dvm\">").append(System.lineSeparator())
			.append("<description>"+archiveDVM.getDescription()+"</description>").append(System.lineSeparator())
			.append("<columns>").append(System.lineSeparator());
		archiveDVM.getDataRowList().get(0).stream()
			.filter(cell -> cell.contains("<cell>"))
			.forEach(columnName -> dvmDocument.append("<column name=\""+columnName.substring(6, columnName.length()-7)+"\"/>").append(System.lineSeparator()));
		dvmDocument.append("</columns>").append(System.lineSeparator())
			.append("<rows>").append(System.lineSeparator())
			.append(getAllRowDVMFormat().delete(0, lengthColumnHead)).append("</rows>")
			.append("</dvm>");
		logger.info(dvmDocument);
		return dvmDocument;
	}
}
