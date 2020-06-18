package exceltodvm;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import exceltodvm.controller.Convert;
import exceltodvm.model.Load;

/**
 * @author Mauricio Brantt Caceres
 *
 */
public class App {
	private static final Logger logger = LogManager.getLogger(App.class);
	public static void main(String[] args) {
		Load archivo = new Load();
		if(!archivo.getRutaArchivo().isEmpty()) {
			Convert app=new Convert(archivo.getRutaArchivo());
			//System.out.println(app.getAllRowDVMFormat());
			logger.info("Starting APP");
			app.getAllDocument();
		}
		
	}

}
