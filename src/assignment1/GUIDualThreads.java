package assignment1;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

import javax.swing.*;
import javax.swing.border.*;

/**
 * The GUI for assignment 1, DualThreads
 */
public class GUIDualThreads implements ActionListener {
	/**
	 * These are the components you need to handle. You have to add listeners
	 * and/or code
	 */
	private JFrame frame; // The Main window
	private JButton btnDisplay; // Start thread moving display
	private JButton btnDStop; // Stop moving display thread
	private JButton btnTriangle;// Start moving graphics thread
	private JButton btnTStop; // Stop moving graphics thread
	private JPanel pnlMove; // The panel to move display in
	private JPanel pnlRotate; // The panel to move graphics in
	private JLabel displayTextLabel;
	private Oval pnlOval;

	private RandomTextThread rtt;
	private RotateThread rt;
	private Thread threadText;
	private Thread rotateThread;

	/**
	 * Constructor
	 */
	public GUIDualThreads() {

	}

	/**
	 * Starts the application
	 */
	public void Start() {
		frame = new JFrame();
		frame.setBounds(0, 0, 494, 332);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		frame.setTitle("Multiple Thread Demonstrator");
		InitializeGUI(); // Fill in components
		frame.setVisible(true);
		frame.setResizable(false); // Prevent user from change size
		frame.setLocationRelativeTo(null); // Start middle screen

		rtt = new RandomTextThread(displayTextLabel);
		rt = new RotateThread(pnlOval);
		
		// Adds a windowlistener which has a method that closes the threads on
		// exit
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				if (rt.isRunning() || rtt.isRunning()) {
					rt.setRunning(false);
					rtt.setRunning(false);
					JOptionPane.showMessageDialog(null, "Closing threads...");
				}
			}
		});

	}

	/**
	 * Sets up the GUI with components
	 */
	private void InitializeGUI() {
		// The moving display outer panel
		JPanel pnlDisplay = new JPanel();
		Border b2 = BorderFactory.createTitledBorder("Display Thread");
		pnlDisplay.setBorder(b2);
		pnlDisplay.setBounds(12, 12, 222, 269);
		pnlDisplay.setLayout(null);

		// Add buttons and drawing panel to this panel
		btnDisplay = new JButton("Start Display");
		btnDisplay.setBounds(10, 226, 121, 23);
		btnDisplay.addActionListener(this);
		pnlDisplay.add(btnDisplay);

		btnDStop = new JButton("Stop");
		btnDStop.setBounds(135, 226, 75, 23);
		btnDStop.addActionListener(this);
		btnDStop.setEnabled(false);
		pnlDisplay.add(btnDStop);

		pnlMove = new JPanel();
		pnlMove.setBounds(10, 19, 200, 200);
		Border b21 = BorderFactory.createLineBorder(Color.black);
		pnlMove.setBorder(b21);
		pnlDisplay.add(pnlMove);
		displayTextLabel = new JLabel("Display Text!");
		displayTextLabel.setVisible(false);
		pnlMove.add(displayTextLabel);
		// Then add this to main window
		frame.add(pnlDisplay);

		// The moving graphics outer panel
		JPanel pnlTriangle = new JPanel();
		Border b3 = BorderFactory.createTitledBorder("Triangle Thread");
		pnlTriangle.setBorder(b3);
		pnlTriangle.setBounds(240, 12, 222, 269);
		pnlTriangle.setLayout(null);

		// Add buttons and drawing panel to this panel
		btnTriangle = new JButton("Start Rotate");
		btnTriangle.setBounds(10, 226, 121, 23);
		btnTriangle.addActionListener(this);
		pnlTriangle.add(btnTriangle);

		btnTStop = new JButton("Stop");
		btnTStop.setBounds(135, 226, 75, 23);
		btnTStop.addActionListener(this);
		btnTStop.setEnabled(false);
		pnlTriangle.add(btnTStop);

		pnlOval = new Oval();
		pnlOval.setVisible(false);
		pnlRotate = new JPanel();
		pnlRotate.setBounds(10, 19, 200, 200);
		Border b31 = BorderFactory.createLineBorder(Color.black);
		pnlRotate.setBorder(b31);
		pnlRotate.add(pnlOval);
		pnlTriangle.add(pnlRotate);
		// Add this to main window
		frame.add(pnlTriangle);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnDisplay) {
			rtt.setRunning(true);
			threadText = new Thread(rtt);
			threadText.start();
			btnDisplay.setEnabled(false);
			btnDStop.setEnabled(true);
		} else if (e.getSource() == btnDStop) {
			rtt.setRunning(false);
			btnDisplay.setEnabled(true);
			btnDStop.setEnabled(false);
		} else if (e.getSource() == btnTriangle) {
			rt.setRunning(true);
			rotateThread = new Thread(rt);
			rotateThread.start();
			btnTriangle.setEnabled(false);
			btnTStop.setEnabled(true);
		} else if (e.getSource() == btnTStop) {
			rt.setRunning(false);
			btnTriangle.setEnabled(true);
			btnTStop.setEnabled(false);
		}
	}

}
