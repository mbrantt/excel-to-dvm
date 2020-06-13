package exceltodvm;

import exceltodvm.controller.Convert;
import exceltodvm.model.Load;

/**
 * @author Mauricio Brantt Caceres
 *
 */
public class App {
	public static void main(String[] args) {
		Load archivo = new Load();
		if(!archivo.getRutaArchivo().isEmpty()) {
			Convert app=new Convert(archivo.getRutaArchivo());
			//System.out.println(app.getAllRowDVMFormat());
			app.getAllDocument();
		}
		
	}

}
