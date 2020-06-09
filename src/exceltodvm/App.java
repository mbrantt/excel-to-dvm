package exceltodvm;

import exceltodvm.controller.Transformador;
import exceltodvm.model.Load;

/**
 * @author Mauricio Brantt Caceres
 *
 */
public class App {
	private static Load archivo;
	public static void main(String[] args) {
		archivo = new Load();
		if(!archivo.getRutaArchivo().isEmpty()) {
			Transformador app=new Transformador(archivo.getRutaArchivo());
			app.leerFilas();
		}
		
	}

}
