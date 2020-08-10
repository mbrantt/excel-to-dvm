package exceltodvm.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.Dimension;

import javax.swing.JScrollPane;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import javax.swing.JTextArea;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import exceltodvm.controller.Convert;
import exceltodvm.model.Archive;
import exceltodvm.model.ExcelFile;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.EmptyBorder;

public class App {

	private JFrame frame;
	private JTextField txtName,txtDescription;
	private JPanel topPanel1, botPanel1, panelLoad, panelConfig, panelCode, panelOption;
	private JRadioButton rdbtnOnlyRow,rdbtnDocument;
	private JButton btnSaveFile, btnCopyCode, btnClear, btnNewFile;
	private JTextArea textAreaCode;
	private JPanel panelLoadButton;
	private JPanel panelOptionButton;
	private Archive archivo;
	private Convert app;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App window = new App();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public App() {
		initialize();
		logicApp();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		archivo = new ExcelFile();
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 620, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{314, 148, 153};
		gridBagLayout.rowHeights = new int[]{130, 162, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 1.0};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		topPanel1 = new JPanel();
		topPanel1.setLayout(new FlowLayout());
		JPanel topPanel2 = new JPanel();
		topPanel2.setLayout(new FlowLayout());
		
		panelLoad = new JPanel();
		panelLoad.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Load", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		panelLoad.setPreferredSize(new Dimension(265, 130));
		panelLoad.setMaximumSize(panelLoad.getPreferredSize());
		panelLoad.setMinimumSize(panelLoad.getPreferredSize());
		topPanel1.add(panelLoad);
		panelLoad.setLayout(new FlowLayout());
		
		rdbtnDocument = new JRadioButton("Document");
		rdbtnDocument.setSelected(true);
		
		panelLoad.add(rdbtnDocument);
		
		rdbtnOnlyRow = new JRadioButton("Only row");
		
		panelLoad.add(rdbtnOnlyRow);
		
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnOnlyRow);
		group.add(rdbtnDocument);
		
		panelLoadButton = new JPanel();
		panelLoadButton.setBorder(new EmptyBorder(30, 0, 0, 0));
		panelLoad.add(panelLoadButton);
		
		btnNewFile = new JButton("Load new Excel");
		
		panelLoadButton.add(btnNewFile);
		
		GridBagConstraints gbc_topPanel = new GridBagConstraints();
		gbc_topPanel.fill = GridBagConstraints.BOTH;
		gbc_topPanel.insets = new Insets(0, 0, 5, 5);
		gbc_topPanel.gridx = 0;
		gbc_topPanel.gridy = 0;
		frame.getContentPane().add(topPanel1, gbc_topPanel);
		GridBagConstraints gbc_topPanel2 = new GridBagConstraints();
		gbc_topPanel2.gridwidth = 2;
		gbc_topPanel2.insets = new Insets(0, 0, 5, 0);
		gbc_topPanel2.gridy = 0;
		gbc_topPanel2.fill = GridBagConstraints.BOTH;
		gbc_topPanel2.gridx = 1;
		frame.getContentPane().add(topPanel2, gbc_topPanel2);
		
		panelConfig = new JPanel();
		panelConfig.setBorder(new TitledBorder(null, "Config", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelConfig.setPreferredSize(new Dimension(265, 130));
		panelConfig.setMaximumSize(panelConfig.getPreferredSize());
		panelConfig.setMinimumSize(panelConfig.getPreferredSize());
		topPanel2.add(panelConfig);
		FlowLayout fl_panelConfig = new FlowLayout();
		fl_panelConfig.setHgap(100);
		panelConfig.setLayout(fl_panelConfig);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setVerticalAlignment(SwingConstants.TOP);
		lblName.setHorizontalAlignment(SwingConstants.LEFT);
		panelConfig.add(lblName);
		
		txtName = new JTextField();
		
		panelConfig.add(txtName);
		txtName.setColumns(15);
		
		JLabel lblDescription = new JLabel("Description:");
		panelConfig.add(lblDescription);
		
		txtDescription = new JTextField();
		panelConfig.add(txtDescription);
		txtDescription.setColumns(15);

		botPanel1 = new JPanel();
		botPanel1.setLayout(new FlowLayout());
		panelCode = new JPanel();
		panelCode.setBorder(new TitledBorder(null, "Code Preview", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelCode.setPreferredSize(new Dimension(450, 320));
		panelCode.setMaximumSize(panelCode.getPreferredSize());
		panelCode.setMinimumSize(panelCode.getPreferredSize());
		botPanel1.add(panelCode);
		FlowLayout fl_panelCode = new FlowLayout();
		fl_panelCode.setHgap(190);
		panelCode.setLayout(fl_panelCode);
		
		textAreaCode = new JTextArea();
		textAreaCode.setEditable(false);

		JScrollPane scroll=new JScrollPane(textAreaCode);
		scroll.setPreferredSize(new Dimension(420, 280));
		panelCode.add(scroll);
		
		GridBagConstraints gbc_botPanel1 = new GridBagConstraints();
		gbc_botPanel1.gridwidth = 2;
		gbc_botPanel1.insets = new Insets(0, 0, 0, 5);
		gbc_botPanel1.fill = GridBagConstraints.BOTH;
		gbc_botPanel1.gridx = 0;
		gbc_botPanel1.gridy = 1;
		frame.getContentPane().add(botPanel1, gbc_botPanel1);
		
		JPanel botPanel2 = new JPanel();
		GridBagConstraints gbc_botPanel2 = new GridBagConstraints();
		gbc_botPanel2.fill = GridBagConstraints.BOTH;
		gbc_botPanel2.gridx = 2;
		gbc_botPanel2.gridy = 1;
		frame.getContentPane().add(botPanel2, gbc_botPanel2);
		
		panelOption = new JPanel();
		panelOption.setBorder(new TitledBorder(null, "Option", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		FlowLayout fl_panelOption = new FlowLayout(FlowLayout.CENTER);
		fl_panelOption.setVgap(10);
		panelOption.setLayout(fl_panelOption);
	
		panelOption.setPreferredSize(new Dimension(140, 280));
		panelOption.setMaximumSize(panelOption.getPreferredSize());
		panelOption.setMinimumSize(panelOption.getPreferredSize());
		botPanel2.add(panelOption, BorderLayout.PAGE_END);
		
		panelOptionButton = new JPanel();
		panelOptionButton.setBorder(new EmptyBorder(30, 0, 0, 0));
		panelOptionButton.setPreferredSize(new Dimension(130, 200));
		panelOptionButton.setMaximumSize(panelOption.getPreferredSize());
		panelOptionButton.setMinimumSize(panelOption.getPreferredSize());
		panelOption.add(panelOptionButton);
		FlowLayout fl_panelOptionButton = new FlowLayout(FlowLayout.CENTER, 5, 20);
		panelOptionButton.setLayout(fl_panelOptionButton);
		btnSaveFile = new JButton("Save File");
		
		panelOptionButton.add(btnSaveFile);
		
		btnCopyCode = new JButton("Copy Code");
		panelOptionButton.add(btnCopyCode);
		
		btnClear = new JButton("Clean");
		btnClear.setEnabled(false);
		panelOptionButton.add(btnClear);
		
		// Events JTextField
		txtName.getDocument().addDocumentListener(new DocumentListener() {
		    @Override
		    public void insertUpdate(DocumentEvent e) {
		    	eventNameAndDescriptionChangeText();
		    }

		    @Override
		    public void removeUpdate(DocumentEvent e) {
		    	eventNameAndDescriptionChangeText();
		    }

		    @Override
		    public void changedUpdate(DocumentEvent e) {
		    	eventNameAndDescriptionChangeText();
		    }
		});
		txtDescription.getDocument().addDocumentListener(new DocumentListener() {
		    @Override
		    public void insertUpdate(DocumentEvent e) {
		    	eventNameAndDescriptionChangeText();
		    }

		    @Override
		    public void removeUpdate(DocumentEvent e) {
		    	eventNameAndDescriptionChangeText();
		    }

		    @Override
		    public void changedUpdate(DocumentEvent e) {
		    	eventNameAndDescriptionChangeText();
		    }
		});
		// Events JButton
		btnClear.addActionListener(event ->{
		      JButton button = (JButton) event.getSource();
		       if (button == btnClear) {
		        	textAreaCode.setText("");
		       }
		});
		btnNewFile.addActionListener(event -> {
			JButton button = (JButton) event.getSource();
			if (button == btnNewFile) {
				archivo.load();
				logicApp();
			}
		});
		btnSaveFile.addActionListener(event -> {
			event.getSource();
			archivo.save(app.getAllDocument(txtName.getText(), txtDescription.getText()));
			if(archivo.getPathDestination() != null) {
				JOptionPane.showMessageDialog(frame, "File saved in "+ archivo.getPathDestination());
			}else {
				JOptionPane.showMessageDialog(frame, "File not saved","Warning",JOptionPane.WARNING_MESSAGE);
			}
			
		});
		btnCopyCode.addActionListener(event -> {
			if(textAreaCode.getText().equals("") || textAreaCode.getText() == null || textAreaCode == null) {
				JOptionPane.showMessageDialog(frame, "There is nothing to copy.","Warning",JOptionPane.WARNING_MESSAGE);
			}
			else {
				StringSelection stringSelection = new StringSelection(textAreaCode.getText());
				Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
				clipboard.setContents(stringSelection, null);
				JOptionPane.showMessageDialog(frame, "Text copied to the clipboard!");
			}
		});
		// Events RadioButton
		rdbtnDocument.addActionListener(eventRadioButton);
		rdbtnOnlyRow.addActionListener(eventRadioButton);
	}
	public void logicApp() {
		try {
			if(!archivo.getPathOrigin().isEmpty()) {
				rdbtnDocument.setEnabled(true);
				rdbtnOnlyRow.setEnabled(true);
				btnSaveFile.setEnabled(true);
				txtDescription.setEnabled(true);
				txtName.setEnabled(true);
				app = new Convert(archivo.getLoadFile());
				txtName.setText(archivo.getName());
				txtDescription.setText(archivo.getDescription());
				textAreaCode.setText(app.getAllDocument(archivo.getName(), archivo.getDescription()).toString());
			}else {
				rdbtnDocument.setEnabled(false);
				rdbtnOnlyRow.setEnabled(false);
				btnSaveFile.setEnabled(false);
				txtDescription.setEnabled(false);
				txtName.setEnabled(false);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(frame, "An unexpected error has occurred, see details in logging-ETD.log", "Error",JOptionPane.ERROR_MESSAGE);
			rdbtnDocument.setEnabled(false);
			rdbtnOnlyRow.setEnabled(false);
			btnSaveFile.setEnabled(false);
			txtDescription.setEnabled(false);
			txtName.setEnabled(false);
		}
	}
	 ActionListener eventRadioButton = new ActionListener() {
		 public void actionPerformed(ActionEvent event) {
		        JRadioButton button = (JRadioButton) event.getSource();
		 
		        if (button == rdbtnDocument) {
		        	txtName.setEnabled(true);
					txtDescription.setEnabled(true);
					btnSaveFile.setEnabled(true);
					textAreaCode.setText(app.getAllDocument(archivo.getName(), archivo.getDescription()).toString());
		        } else if (button == rdbtnOnlyRow) {
		        	txtName.setEnabled(false);
					txtDescription.setEnabled(false);
					btnSaveFile.setEnabled(false);
					textAreaCode.setText(app.getAllRowDVMFormat().toString());
		        } 
		    }
		
	 };
	 public void eventNameAndDescriptionChangeText(){
		 archivo.setName(txtName.getText() + ".dvm");
		 archivo.setDescription(txtDescription.getText());
		 textAreaCode.setText(app.getAllDocument(archivo.getName(), archivo.getDescription()).toString());
	 }
	
}
