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
import javax.swing.border.TitledBorder;
import javax.swing.JTextArea;
import javax.swing.UIManager;

public class App {

	private JFrame frame;

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
		
		JPanel topPanel1 = new JPanel();
		topPanel1.setLayout(new FlowLayout());
		JPanel topPanel2 = new JPanel();
		topPanel2.setLayout(new FlowLayout());
		
		JPanel panelOption = new JPanel();
		panelOption.setBorder(new TitledBorder(null, "Option", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelOption.setPreferredSize(new Dimension(265, 160));
		panelOption.setMaximumSize(panelOption.getPreferredSize());
		panelOption.setMinimumSize(panelOption.getPreferredSize());
		topPanel1.add(panelOption);
		panelOption.setLayout(new FlowLayout());
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
		
		JPanel panelConfig = new JPanel();
		panelConfig.setBorder(new TitledBorder(null, "Config", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelConfig.setPreferredSize(new Dimension(265, 160));
		panelConfig.setMaximumSize(panelConfig.getPreferredSize());
		panelConfig.setMinimumSize(panelConfig.getPreferredSize());
		topPanel2.add(panelConfig);
		FlowLayout fl_panelConfig = new FlowLayout();
		fl_panelConfig.setHgap(100);
		panelConfig.setLayout(fl_panelConfig);
		
		
		JPanel botPanel1 = new JPanel();
		botPanel1.setLayout(new FlowLayout());
		JPanel panelCode = new JPanel();
		panelCode.setBorder(new TitledBorder(null, "Code Preview", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelCode.setPreferredSize(new Dimension(400, 280));
		panelCode.setMaximumSize(panelCode.getPreferredSize());
		panelCode.setMinimumSize(panelCode.getPreferredSize());
		botPanel1.add(panelCode);
		FlowLayout fl_panelCode = new FlowLayout();
		fl_panelCode.setHgap(190);
		panelCode.setLayout(fl_panelCode);
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		JScrollPane scroll=new JScrollPane(textArea);
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
		
		JPanel panelButton = new JPanel();
		panelButton.setBorder(UIManager.getBorder("List.focusCellHighlightBorder"));
		//panelButton.setLayout(new BoxLayout(panelButton,BoxLayout.Y_AXIS));
		panelButton.setLayout(new FlowLayout(FlowLayout.CENTER));
	
		//panelButton.setLayout(new BorderLayout());
		panelButton.setPreferredSize(new Dimension(140, 280));
		panelButton.setMaximumSize(panelButton.getPreferredSize());
		panelButton.setMinimumSize(panelButton.getPreferredSize());
		botPanel2.add(panelButton, BorderLayout.PAGE_END);
		JButton btnNewButton_1 = new JButton("Save File");
		panelButton.add(btnNewButton_1, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("Copy Code");
		panelButton.add(btnNewButton, BorderLayout.NORTH);
	}
}
