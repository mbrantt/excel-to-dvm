package exceltodvm;

import exceltodvm.controller.Transformador;
import exceltodvm.model.Load;

/**
 * @author Mauricio Brantt Caceres
 *
 */
public class App {
	public static void main(String[] args) {
		Load archivo = new Load();
		if(!archivo.getRutaArchivo().isEmpty()) {
			Transformador app=new Transformador(archivo.getRutaArchivo());
			app.leerFilas();
		}
		
	}

}
