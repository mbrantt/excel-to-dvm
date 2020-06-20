package exceltodvm.model;

import java.util.ArrayList;
import java.util.List;

public class DomainValueMap extends Archive{
	private List<ArrayList<String>> dataRowList;
	public DomainValueMap(String nameArchiveDVM) {
		super(nameArchiveDVM);
	}
	public DomainValueMap() {
		super();
	}
	public List<ArrayList<String>> getDataRowList() {
		return dataRowList;
	}
	public void setDataRowList(ArrayList<String> dataCell) {
		this.dataRowList.add(dataCell);
	}

}
