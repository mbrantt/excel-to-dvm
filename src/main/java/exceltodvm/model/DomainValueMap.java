package exceltodvm.model;

import java.util.ArrayList;

public class DomainValueMap extends Archive{
	private ArrayList<ArrayList<String>> dataRowList;
	
	public DomainValueMap(String nameArchiveDVM) {
		super(nameArchiveDVM);
	}
	
	public DomainValueMap() {
		super();
		dataRowList = new ArrayList<>();
	}
	
	public ArrayList<ArrayList<String>> getDataRowList() {
		return dataRowList;
	}
	
	public void setDataRowList(ArrayList<String> dataCell) {
		this.dataRowList.add(dataCell);
	}

}
