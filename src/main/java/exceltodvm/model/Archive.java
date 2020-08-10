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
	private String description;
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
		try {
			JFileChooser file = new JFileChooser();
			int accion = file.showOpenDialog(file);
			if(accion == JFileChooser.APPROVE_OPTION) {
				loadFile = file.getSelectedFile();
				this.name = loadFile.getName();
				this.pathOrigin = loadFile.getPath();
			}else if(this.pathOrigin == null || this.pathOrigin == ""){
				loadFile = null;
				this.name = "";
				this.pathOrigin = "";
			}
		} catch(Exception e) {
			logger.error("Error: " + e.getMessage());
		}
	}
	@Override
	public void save(StringBuilder documentToSave) {
		JFileChooser chooser = new JFileChooser();
		
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int retrival = chooser.showSaveDialog(null);
		if(retrival == JFileChooser.APPROVE_OPTION) {
			pathDestination = chooser.getSelectedFile().toString();
			try(FileOutputStream fileOut = new FileOutputStream(new File(pathDestination + "/" + name))) {
				fileOut.write(documentToSave.toString().getBytes());
				fileOut.flush();
				logger.info("Saved file.");
			} catch (FileNotFoundException e) {
				logger.error("File Error: " + e);
			} catch (IOException e) {
				logger.error("Error: " + e);
			}
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
	public File getLoadFile() {
		return loadFile;
	}
	public void setLoadFile(File loadFile) {
		this.loadFile = loadFile;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}
