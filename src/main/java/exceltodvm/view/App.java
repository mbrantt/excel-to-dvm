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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import exceltodvm.controller.Convert;
import exceltodvm.model.Archive;
import exceltodvm.model.ExcelFile;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;

public class App {

	private JFrame frame;
	private JTextField txtName,txtDescription;
	private JPanel topPanel1, botPanel1, panelOption, panelConfig, panelCode, panelButton;
	private JRadioButton rdbtnOnlyRow,rdbtnDocument;
	private JButton btnSaveFile, btnCopyCode, btnClear, btnNewFile;
	private JTextArea textAreaCode;
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
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 560, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{280, 140, 140};
		gridBagLayout.rowHeights = new int[]{162, 162, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 1.0};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		topPanel1 = new JPanel();
		topPanel1.setLayout(new FlowLayout());
		JPanel topPanel2 = new JPanel();
		topPanel2.setLayout(new FlowLayout());
		
		panelOption = new JPanel();
		panelOption.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Load", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		panelOption.setPreferredSize(new Dimension(265, 160));
		panelOption.setMaximumSize(panelOption.getPreferredSize());
		panelOption.setMinimumSize(panelOption.getPreferredSize());
		topPanel1.add(panelOption);
		panelOption.setLayout(new FlowLayout());
		
		rdbtnDocument = new JRadioButton("Document");
		rdbtnDocument.setSelected(true);
		rdbtnDocument.addActionListener(eventRadioButton);
		panelOption.add(rdbtnDocument);
		
		rdbtnOnlyRow = new JRadioButton("Only row");
		rdbtnOnlyRow.addActionListener(eventRadioButton);
		panelOption.add(rdbtnOnlyRow);
		
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnOnlyRow);
		group.add(rdbtnDocument);
		
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(30, 0, 0, 0));
		panelOption.add(panel);
		
		btnNewFile = new JButton("Load new Excel");
		panel.add(btnNewFile);
		
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
		panelConfig.setPreferredSize(new Dimension(265, 160));
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
		txtName.setColumns(10);
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
		
		JLabel lblDescription = new JLabel("Description:");
		panelConfig.add(lblDescription);
		
		txtDescription = new JTextField();
		panelConfig.add(txtDescription);
		txtDescription.setColumns(10);
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
		
		
		botPanel1 = new JPanel();
		botPanel1.setLayout(new FlowLayout());
		panelCode = new JPanel();
		panelCode.setBorder(new TitledBorder(null, "Code Preview", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelCode.setPreferredSize(new Dimension(400, 280));
		panelCode.setMaximumSize(panelCode.getPreferredSize());
		panelCode.setMinimumSize(panelCode.getPreferredSize());
		botPanel1.add(panelCode);
		FlowLayout fl_panelCode = new FlowLayout();
		fl_panelCode.setHgap(190);
		panelCode.setLayout(fl_panelCode);
		
		textAreaCode = new JTextArea();
		textAreaCode.setEditable(false);
		JScrollPane scroll=new JScrollPane(textAreaCode);
		scroll.setPreferredSize(new Dimension(380, 250));
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
		//botPanel2.setLayout(new FlowLayout());
		//botPanel2.setLayout(new BorderLayout());
		
		panelButton = new JPanel();
		panelButton.setBorder(new TitledBorder(null, "Option", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		//panelButton.setLayout(new BoxLayout(panelButton,BoxLayout.Y_AXIS));
		panelButton.setLayout(new FlowLayout(FlowLayout.CENTER));
	
		//panelButton.setLayout(new BorderLayout());
		panelButton.setPreferredSize(new Dimension(140, 280));
		panelButton.setMaximumSize(panelButton.getPreferredSize());
		panelButton.setMinimumSize(panelButton.getPreferredSize());
		botPanel2.add(panelButton, BorderLayout.PAGE_END);
		
		panel_1 = new JPanel();
		panel_1.setBorder(new EmptyBorder(30, 0, 0, 0));
		panel_1.setPreferredSize(new Dimension(130, 200));
		panel_1.setMaximumSize(panelButton.getPreferredSize());
		panel_1.setMinimumSize(panelButton.getPreferredSize());
		panelButton.add(panel_1);
		FlowLayout fl_panel_1 = new FlowLayout(FlowLayout.CENTER, 5, 20);
		panel_1.setLayout(fl_panel_1);
		btnSaveFile = new JButton("Save File");
		panel_1.add(btnSaveFile);
		
		btnCopyCode = new JButton("Copy Code");
		panel_1.add(btnCopyCode);
		
		btnClear = new JButton("Clean");
		panel_1.add(btnClear);
		btnClear.addActionListener(eventClean);
	}
	public void logicApp() {
		archivo = new ExcelFile();
		if(!archivo.getPathOrigin().isEmpty()) {
			app = new Convert(archivo.getLoadFile());
			txtName.setText(archivo.getName() + ".dvm");
			txtDescription.setText(archivo.getDescription());
			textAreaCode.setText(app.getAllDocument(txtName.getText(), txtDescription.getText()).toString());
		}
	}
	 ActionListener eventRadioButton = new ActionListener() {
		 public void actionPerformed(ActionEvent event) {
		        JRadioButton button = (JRadioButton) event.getSource();
		 
		        if (button == rdbtnDocument) {
		        	txtName.setEnabled(true);
					txtDescription.setEnabled(true);
					btnSaveFile.setEnabled(true);
					textAreaCode.setText(app.getAllDocument(txtName.getText(), txtDescription.getText()).toString());
		        } else if (button == rdbtnOnlyRow) {
		        	// txtName.setText("");
					// txtDescription.setText("");
		        	txtName.setEnabled(false);
					txtDescription.setEnabled(false);
					btnSaveFile.setEnabled(false);
					textAreaCode.setText(app.getAllRowDVMFormat().toString());
		        } 
		    }
		
	 };
	 ActionListener eventClean = new ActionListener() {
		 public void actionPerformed(ActionEvent event) {
		        JButton button = (JButton) event.getSource();
		 
		        if (button == btnClear) {
		        	textAreaCode.setText("");
		        }
		    }
		
	 };
	 ActionListener eventNameTextbox = new ActionListener() {
		 public void actionPerformed(ActionEvent event) {
		        JTextField txt = (JTextField) event.getSource();
		 
		        if (txt == txtName) {
		        	textAreaCode.setText(app.getAllDocument(txtName.getText(), txtDescription.getText()).toString());
		        }
		    }
		
	 };
	 private JPanel panel;
	 private JPanel panel_1;
	 public void eventNameAndDescriptionChangeText(){
		 textAreaCode.setText(app.getAllDocument(txtName.getText(), txtDescription.getText()).toString());
	 }
	
}
