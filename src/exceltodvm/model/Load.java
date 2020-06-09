package exceltodvm.model;

import java.io.File;

import javax.swing.JFileChooser;

public class Load {

	private String nombreArchivo;
	private String rutaArchivo;
	
	public Load() {
		cargarArchivo();
	}
	
	public void cargarArchivo() {
		JFileChooser file = new JFileChooser();
		int accion = file.showOpenDialog(file);
		if(accion == JFileChooser.APPROVE_OPTION) {
			File excel = file.getSelectedFile();
			this.nombreArchivo = excel.getName();
			this.rutaArchivo = excel.getPath();
		}else{
			System.exit(0);
		}
	}

	public String getNombreArchivo() {
		return nombreArchivo;
	}

	public String getRutaArchivo() {
		return rutaArchivo;
	}

}
