package exceltodvm.model;

import java.util.ArrayList;
import java.util.List;

public class DVM {
	private String nombre;
	private String descripcion;
	private ArrayList<ArrayList<String>> row;
	
	public DVM() {
		row = new ArrayList<>();
	}

	public DVM(String nombre) {
		this.nombre = nombre;
	}

	public DVM(String nombre, String descripcion) {
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	public List<ArrayList<String>> getData() {
		return row;
	}

	public void setRow(ArrayList<String> cell) {
		this.row.add(cell);
	}

	public String getNombre() {
		return nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}
	
}
