package exceltodvm.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JFileChooser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class Archive implements ArchiveInterface{
	private String name;
	private String pathDestination;
	private String pathOrigin;
	private File loadFile;
	private static final Logger logger = LogManager.getLogger(Archive.class);
	
	protected Archive(String name) {
		this.name = name;
	}
	protected Archive() {
	}
	@Override
	public void load() {
		JFileChooser file = new JFileChooser();
		int accion = file.showOpenDialog(file);
		if(accion == JFileChooser.APPROVE_OPTION) {
			loadFile = file.getSelectedFile();
			this.name = loadFile.getName();
			this.pathOrigin = loadFile.getPath();
		}else{
			System.exit(0);
		}
	}
	@Override
	public void save(StringBuilder documentToSave) {
		try(FileOutputStream fileOut = new FileOutputStream(new File(getPathDestination()))) {
			fileOut.write(documentToSave.toString().getBytes());
			fileOut.flush();
		} catch (FileNotFoundException e) {
			logger.error("File Error: %s", e);
		} catch (IOException e) {
			logger.error("Error: %s", e);
		}
		
	}

	public String getName() {
		return name;
	}

	public String getPathDestination() {
		return pathDestination;
	}

	public void setPathDestination(String pathDestination) {
		this.pathDestination = pathDestination;
	}

	public String getPathOrigin() {
		return pathOrigin;
	}

	public void setPathOrigin(String pathOrigin) {
		this.pathOrigin = pathOrigin;
	}

	public void setName(String name) {
		this.name = name;
	}

}
