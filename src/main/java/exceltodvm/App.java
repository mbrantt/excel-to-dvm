package exceltodvm;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import exceltodvm.controller.Convert;
import exceltodvm.model.Archive;
import exceltodvm.model.ExcelFile;

/**
 * @author Mauricio Brantt Caceres
 *
 */
public class App {
	private static final Logger logger = LogManager.getLogger(App.class);
	public static void main(String[] args) {
		Archive archivo = new ExcelFile();
		if(!archivo.getPathOrigin().isEmpty()) {
			Convert app=new Convert(archivo.getLoadFile());
			logger.info("Starting APP");
			archivo.setPathDestination(archivo.getPathOrigin()+".dvm");
			archivo.save(app.getAllDocument(archivo.getName(), archivo.getDescription()));
			logger.info("Origin file: " + archivo.getPathOrigin());
			logger.info("Destination file: " + archivo.getPathDestination());
		}
		
	}

}
