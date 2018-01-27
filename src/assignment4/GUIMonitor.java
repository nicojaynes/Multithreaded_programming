package assignment4;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.*;

import javax.swing.*;

/**
 * The GUI for assignment 4
 */
public class GUIMonitor
{
	/**
	 * These are the components you need to handle.
	 * You have to add listeners and/or code
	 */
	private JFrame frame;				// The Main window
	private JMenu fileMenu;				// The menu
	private JMenuItem openItem;			// File - open
	private JMenuItem saveItem;			// File - save as
	private JMenuItem exitItem;			// File - exit
	private JTextField txtFind;			// Input string to find
	private JTextField txtReplace; 		// Input string to replace
	private JCheckBox chkNotify;		// User notification choise
	private JLabel lblInfo;				// Hidden after file selected
	private JButton btnCreate;			// Start copying
	private JButton btnClear;			// Removes dest. file and removes marks
	private JLabel lblChanges;			// Label telling number of replacements
	private JTextPane txtPaneSource;
	private JTextPane txtPaneDest;

	private BoundedBuffer buffer;
	private Writer writer;
	private Reader reader;
	private Modifier modifier;


	/**
	 * Constructor
	 */
	public GUIMonitor()	{

	}

	/**
	 * Starts the application
	 */
	public void start()
	{
		frame = new JFrame();
		frame.setBounds(0, 0, 714,600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		frame.setTitle("Text File Copier - with Find and Replace");
		initializeGUI();					// Fill in components
		frame.setVisible(true);
		frame.setResizable(false);			// Prevent user from change size
		frame.setLocationRelativeTo(null);	// Start middle screen
	}

	/**
	 * Loads the text from the selected file to the source pane
	 * @param selectedFile File from which the text is to be read from
	 */
	private void loadFileToSourcePane(File selectedFile) {
		try {
			FileReader fileReader = new FileReader(selectedFile);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line;
			StringBuilder sourceText = new StringBuilder();
			while ((line = bufferedReader.readLine()) != null) {
				sourceText.append(line);
			}
			txtPaneSource.setText(sourceText.toString());
			bufferedReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}


	/**
	 * Sets up the GUI with components
	 */
	private void initializeGUI()
	{
		fileMenu = new JMenu("File");
		openItem = new JMenuItem("Open Source File");
		openItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		openItem.addActionListener(new OpenItemListener());
		saveItem = new JMenuItem("Save Destination File As");
		saveItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		saveItem.addActionListener(new SaveItemListener());
		saveItem.setEnabled(false);
		exitItem = new JMenuItem("Exit");
		exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
		fileMenu.add(openItem);
		fileMenu.add(saveItem);
		fileMenu.addSeparator();
		fileMenu.add(exitItem);
		JMenuBar  bar = new JMenuBar();
		frame.setJMenuBar(bar);
		bar.add(fileMenu);

		JPanel pnlFind = new JPanel();
		pnlFind.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Find and Replace"));
		pnlFind.setBounds(12, 32, 436, 122);
		pnlFind.setLayout(null);
		frame.add(pnlFind);
		JLabel lab1 = new JLabel("Find:");
		lab1.setBounds(7, 30, 80, 13);
		pnlFind.add(lab1);
		JLabel lab2 = new JLabel("Replace with:");
		lab2.setBounds(7, 63, 80, 13);
		pnlFind.add(lab2);

		txtFind = new JTextField();
		txtFind.setBounds(88, 23, 327, 20);
		pnlFind.add(txtFind);
		txtReplace = new JTextField();
		txtReplace.setBounds(88, 60, 327, 20);
		pnlFind.add(txtReplace);
		chkNotify = new JCheckBox("Notify user on every match");
		chkNotify.setBounds(88, 87, 180, 17);
		pnlFind.add(chkNotify);

		lblInfo = new JLabel("Select Source File..");
		lblInfo.setBounds(485, 42, 120, 13);
		frame.add(lblInfo);

		btnCreate = new JButton("Create the destination file");
		btnCreate.setBounds(465, 119, 230, 23);
		btnCreate.addActionListener(new CreateListener());
		frame.add(btnCreate);
		btnClear = new JButton("Clear Dest. file and remove marks");
		btnClear.setBounds(465, 151, 230, 23);
		btnClear.addActionListener(new ClearListener());
		frame.add(btnClear);

		lblChanges = new JLabel("No. of Replacements:");
		lblChanges.setBounds(279, 161, 210, 13);
		frame.add(lblChanges);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(12, 170, 653, 359);
		frame.add(tabbedPane);
		txtPaneSource = new JTextPane();
		JScrollPane scrollSource = new JScrollPane(txtPaneSource);
		tabbedPane.addTab("Source", null, scrollSource, null);
		txtPaneDest = new JTextPane();
		JScrollPane scrollDest = new JScrollPane(txtPaneDest);
		tabbedPane.addTab("Destination", null, scrollDest, null);
	}

	/**
	 * Method that writes the modified text to the destination pane
	 */
	public void writeToDestPane() {
		List<String> list;
		list = reader.getStrings();
		StringBuilder text = new StringBuilder();
		for (int i = 0; i < list.size(); i++) {
			text.append(list.get(i));
		}
		txtPaneDest.setText(text.toString());
		lblChanges.setText("No. of Replacements: " + buffer.getNbrReplacements());
		saveItem.setEnabled(true);
	}


	/**
	 * Listener for the clear button.
	 * It clears the destination pane and resets other buttons and settings
	 * @author Nicolai Jaynes
	 *
	 */
	private class ClearListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			txtPaneDest.setText("");
			btnClear.setEnabled(false);
			btnCreate.setEnabled(true);
			saveItem.setEnabled(false);
			lblChanges.setText("No. of Replacements:");
		}
	}

	/**
	 * Listener for the create button.
	 * It creates an array of words based on the text in the
	 * source textpane. It then instantiates a buffer, reader, writer and modifier.
	 * It then starts the writer, reader and modifier threads.
	 * @author Nicolai Jaynes
	 *
	 */
	private class CreateListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			btnCreate.setEnabled(false);
			btnClear.setEnabled(true);
			String text = txtPaneSource.getText();
			String[] words = text.split(" ");
			buffer = new BoundedBuffer(15, txtFind.getText(), txtReplace.getText());
			reader = new Reader(buffer, words.length, txtPaneDest, saveItem, lblChanges);
			writer = new Writer(buffer, words);
			modifier = new Modifier(buffer, words.length);
			writer.start();
			modifier.start();
			reader.start();
		}
	}

	/**
	 * Listener for the open item button.
	 * It uses JFileChooser to let the user choose a file
	 * from the hard drive. If the selected file is not a .txt file
	 * it won't be opened and user will be prompted to choose a .txt file
	 * @author Nicolai Jaynes
	 *
	 */
	private class OpenItemListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JFileChooser fileChooser = new JFileChooser();
			int returnValue = fileChooser.showOpenDialog(null);
			if (returnValue == JFileChooser.APPROVE_OPTION) {
				File selectedFile = fileChooser.getSelectedFile();
				if (selectedFile.getName().contains(".txt")) {
					loadFileToSourcePane(selectedFile);
				} else {
					JOptionPane.showMessageDialog(null, "Must be a text file!");
				}
			}

		}

	}

	/**
	 * Save item listener.
	 * It uses JFileChooser to let the user save their modified text
	 * to their hard drive.
	 * @author Nicolai Jaynes
	 *
	 */
	private class SaveItemListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String output = txtPaneDest.getText();
			JFileChooser fileChooser = new JFileChooser();
		    int retrival = fileChooser.showSaveDialog(null);
		    if (retrival == JFileChooser.APPROVE_OPTION) {
		        try {
		            FileWriter fw = new FileWriter(fileChooser.getSelectedFile()+".txt");
		            fw.write(output);
		            fw.close();
		        } catch (Exception ex) {
		            ex.printStackTrace();
		        }
		    }
		}
	}

}
