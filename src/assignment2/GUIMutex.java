package assignment2;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.text.JTextComponent;

/**
 * The GUI for assignment 2
 */
public class GUIMutex implements ActionListener {
	/**
	 * These are the components you need to handle. You have to add listeners
	 * and/or code
	 */
	private JFrame frame; // The Main window
	private JLabel lblTrans; // The transmitted text
	private JLabel lblRec; // The received text
	private JRadioButton bSync; // The sync radiobutton
	private JRadioButton bAsync; // The async radiobutton
	private JTextField txtTrans; // The input field for string to transfer
	private JButton btnRun; // The run button
	private JButton btnCompare; //The Compare button
	private JButton btnClear; // The clear button
	private JPanel pnlRes; // The colored result area
	private JLabel lblStatus; // The status of the transmission
	private JTextArea listW; // The write logger pane
	private JTextArea listR; // The read logger pane

	private Controller controller;


	/**
	 * Constructor
	 */
	public GUIMutex() {

	}

	/**
	 * Starts the application
	 */
	public void start() {
		frame = new JFrame();
		frame.setBounds(0, 0, 601, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		frame.setTitle("Concurrent Read/Write");
		InitializeGUI(); // Fill in components
		frame.setVisible(true);
		frame.setResizable(false); // Prevent user from change size
		frame.setLocationRelativeTo(null); // Start middle screen

		}

	/**
	 * Sets up the GUI with components
	 */
	private void InitializeGUI() {
		// First, create the static components
		// First the 4 static texts
		JLabel lab1 = new JLabel("Writer Thread Logger");
		lab1.setBounds(18, 29, 128, 13);
		frame.add(lab1);
		JLabel lab2 = new JLabel("Reader Thread Logger");
		lab2.setBounds(388, 29, 128, 13);
		frame.add(lab2);
		JLabel lab3 = new JLabel("Transmitted:");
		lab3.setBounds(13, 470, 100, 13);
		frame.add(lab3);
		JLabel lab4 = new JLabel("Received:");
		lab4.setBounds(383, 470, 100, 13);
		frame.add(lab4);
		// Then add the two lists (of string) for logging transfer
		listW = new JTextArea();
		listW.setBounds(13, 45, 197, 420);
		listW.setBorder(BorderFactory.createLineBorder(Color.black));
		listW.setEditable(false);
		frame.add(listW);
		listR = new JTextArea();
		listR.setBounds(386, 45, 183, 420);
		listR.setBorder(BorderFactory.createLineBorder(Color.black));
		listR.setEditable(false);
		frame.add(listR);
		// Next the panel that holds the "running" part
		JPanel pnlTest = new JPanel();
		pnlTest.setBorder(BorderFactory.createTitledBorder("Concurrent Tester"));
		pnlTest.setBounds(220, 45, 155, 420);
		pnlTest.setLayout(null);
		frame.add(pnlTest);
		lblTrans = new JLabel("");
		lblTrans.setBounds(13, 490, 200, 13);
		frame.add(lblTrans);
		lblRec = new JLabel("");
		lblRec.setBounds(383, 490, 200, 13);
		frame.add(lblRec);

		// These are the controls on the user panel, first the radiobuttons
		bSync = new JRadioButton("Syncronous Mode", false);
		bSync.setBounds(8, 37, 131, 17);
		pnlTest.add(bSync);
		bAsync = new JRadioButton("Asyncronous Mode", true);
		bAsync.setBounds(8, 61, 141, 17);
		pnlTest.add(bAsync);
		ButtonGroup grp = new ButtonGroup();
		grp.add(bSync);
		grp.add(bAsync);
		// then the label and textbox to input string to transfer
		JLabel lab5 = new JLabel("String to Transfer:");
		lab5.setBounds(6, 99, 141, 13);
		pnlTest.add(lab5);
		txtTrans = new JTextField();
		txtTrans.setBounds(6, 124, 123, 20);
		pnlTest.add(txtTrans);
		// The run button
		btnRun = new JButton("Run");
		btnRun.setBounds(26, 150, 75, 23);
		btnRun.addActionListener(this);
		pnlTest.add(btnRun);
		JLabel lab6 = new JLabel("Running status:");
		lab6.setBounds(23, 199, 110, 13);
		pnlTest.add(lab6);
		// The colored rectangle holding result status
		pnlRes = new JPanel();
		pnlRes.setBorder(BorderFactory.createLineBorder(Color.black));
		pnlRes.setBounds(26, 225, 75, 47);
		pnlRes.setBackground(Color.WHITE);
		pnlTest.add(pnlRes);
		// also to this text
		lblStatus = new JLabel("");
		lblStatus.setBounds(23, 275, 100, 13);
		pnlTest.add(lblStatus);

		// The clear input button, starts disabled
		btnClear = new JButton("Clear");
		btnClear.setBounds(26, 333, 90, 23);
		btnClear.setEnabled(false);
		btnClear.addActionListener(this);
		pnlTest.add(btnClear);
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}


	public JLabel getLblStatus() {
		return lblStatus;
	}

	public JPanel getPnlRes() {
		return pnlRes;
	}

	public JButton getBtnClear() {
		return btnClear;
	}

	public JTextField getTxtTrans() {
		return txtTrans;
	}

	public JTextArea getListW() {
		return listW;
	}

	public JTextArea getListR() {
		return listR;
	}

	public JButton getBtnRun() {
		return btnRun;
	}

	public JLabel getLblTrans() {
		return lblTrans;
	}

	public JLabel getLblRec() {
		return lblRec;
	}

	public void clearGUI() {
		listW.setText("");
		listR.setText("");
		lblStatus.setText("");
		pnlRes.setBackground(Color.WHITE);
		lblTrans.setText("");
		lblRec.setText("");
		btnRun.setEnabled(true);
		btnClear.setEnabled(false);
	}

	/**
	 * Controlls what happens when the different buttons are clicked
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnRun) {
			if(bSync.isSelected()) {
				controller.setSync(true);
			} else if (bAsync.isSelected()) {
				controller.setSync(false);
			}
			controller.run();
		}
		if(e.getSource() == btnClear) {
			controller.clear();
		}
 	}

}
