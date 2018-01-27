package assignment3;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * The GUI for assignment 3
 */
public class GUIFoodSupply implements ActionListener {
	/**
	 * These are the components you need to handle. You have to add listeners
	 * and/or code Static controls are defined inline
	 */
	private JFrame frame; // The Main window
	private JProgressBar bufferStatus; // The progressbar, showing content in
										// buffer
	private JLabel currentAmtInBuffer;

	// Data for Producer Scan
	private JButton btnStartS; // Button start Scan
	private JButton btnStopS; // Button stop Scan
	private JLabel lblStatusS; // Status Scan
	// DAta for producer Arla
	private JButton btnStartA; // Button start Arla
	private JButton btnStopA; // Button stop Arla
	private JLabel lblStatusA; // Status Arla
	// Data for producer AxFood
	private JButton btnStartX; // Button start AxFood
	private JButton btnStopX; // Button stop AxFood
	private JLabel lblStatusX; // Status AxFood

	// Data for consumer ICA
	private JLabel lblIcaItems; // Ica limits
	private JLabel lblIcaWeight;
	private JLabel lblIcaVolume;
	private JLabel lblIcaStatus; // load status
	private JTextArea lstIca; // The cargo list
	private JButton btnIcaStart; // The buttons
	private JButton btnIcaStop;
	private JCheckBox chkIcaCont; // Continue checkbox
	// Data for consumer COOP
	private JLabel lblCoopItems;
	private JLabel lblCoopWeight;
	private JLabel lblCoopVolume;
	private JLabel lblCoopStatus; // load status
	private JTextArea lstCoop; // The cargo list
	private JButton btnCoopStart; // The buttons
	private JButton btnCoopStop;
	private JCheckBox chkCoopCont; // Continue checkbox
	// Data for consumer CITY GROSS
	private JLabel lblCGItems;
	private JLabel lblCGWeight;
	private JLabel lblCGVolume;
	private JLabel lblCGStatus; // load status
	private JTextArea lstCG; // The cargo list
	private JButton btnCGStart; // The buttons
	private JButton btnCGStop;
	private JCheckBox chkCGCont; // Continue checkbox

	private Controller controller;

	/**
	 * Constructor, creates the window
	 */
	public GUIFoodSupply() {

	}

	public void setController(Controller controller) {
		this.controller = controller;
	}

	/**
	 * Starts the application
	 */
	public void Start() {
		frame = new JFrame();
		frame.setBounds(0, 0, 730, 526);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		frame.setTitle("Food Supply System");
		InitializeGUI(); // Fill in components
		frame.setVisible(true);
		frame.setResizable(false); // Prevent user from change size
		frame.setLocationRelativeTo(null); // Start middle screen
	}

	/**
	 * Sets up the GUI with components
	 */
	private void InitializeGUI() {
		// First create the three main panels
		JPanel pnlBuffer = new JPanel();
		pnlBuffer.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Storage"));
		pnlBuffer.setBounds(13, 403, 693, 82);
		pnlBuffer.setLayout(null);
		// Then create the progressbar, only component in buffer panel
		bufferStatus = new JProgressBar();
		bufferStatus.setBounds(155, 37, 500, 23);
		bufferStatus.setBorder(BorderFactory.createLineBorder(Color.black));
		bufferStatus.setForeground(Color.GREEN);
		bufferStatus.setMaximum(50);
		pnlBuffer.add(bufferStatus);
		JLabel lblmax = new JLabel("Max capacity (items): 50");
		lblmax.setBounds(10, 42, 146, 13);
		pnlBuffer.add(lblmax);
		currentAmtInBuffer = new JLabel("Current amount in buffer: ");
		currentAmtInBuffer.setBounds(10, 15, 200, 13);
		pnlBuffer.add(currentAmtInBuffer);
		frame.add(pnlBuffer);

		JPanel pnlProd = new JPanel();
		pnlProd.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Producers"));
		pnlProd.setBounds(13, 13, 229, 379);
		pnlProd.setLayout(null);

		JPanel pnlCons = new JPanel();
		pnlCons.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Consumers"));
		pnlCons.setBounds(266, 13, 440, 379);
		pnlCons.setLayout(null);

		// Now add the three panels to producer panel
		JPanel pnlScan = new JPanel();
		pnlScan.setBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Producer: Scan"));
		pnlScan.setBounds(6, 19, 217, 100);
		pnlScan.setLayout(null);

		// Content Scan panel
		btnStartS = new JButton("Start Producing");
		btnStartS.setBounds(10, 59, 125, 23);
		btnStartS.addActionListener(this);
		pnlScan.add(btnStartS);
		btnStopS = new JButton("Stop");
		btnStopS.setBounds(140, 59, 65, 23);
		btnStopS.setEnabled(false);
		btnStopS.addActionListener(this);
		pnlScan.add(btnStopS);
		lblStatusS = new JLabel("Status: ");
		lblStatusS.setBounds(10, 31, 200, 13);
		pnlScan.add(lblStatusS);
		// Add Scan panel to producers
		pnlProd.add(pnlScan);

		// The Arla panel
		JPanel pnlArla = new JPanel();
		pnlArla.setBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Producer: Arla"));
		pnlArla.setBounds(6, 139, 217, 100);
		pnlArla.setLayout(null);

		// Content Arla panel
		btnStartA = new JButton("Start Producing");
		btnStartA.setBounds(10, 59, 125, 23);
		btnStartA.addActionListener(this);
		pnlArla.add(btnStartA);
		btnStopA = new JButton("Stop");
		btnStopA.setBounds(140, 59, 65, 23);
		btnStopA.setEnabled(false);
		btnStopA.addActionListener(this);
		pnlArla.add(btnStopA);
		lblStatusA = new JLabel("Status: ");
		lblStatusA.setBounds(10, 31, 200, 13);
		pnlArla.add(lblStatusA);
		// Add Arla panel to producers
		pnlProd.add(pnlArla);

		// The AxFood Panel
		JPanel pnlAxfood = new JPanel();
		pnlAxfood.setBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Producer: AxFood"));
		pnlAxfood.setBounds(6, 262, 217, 100);
		pnlAxfood.setLayout(null);

		// Content AxFood Panel
		btnStartX = new JButton("Start Producing");
		btnStartX.setBounds(10, 59, 125, 23);
		btnStartX.addActionListener(this);
		pnlAxfood.add(btnStartX);
		btnStopX = new JButton("Stop");
		btnStopX.setBounds(140, 59, 65, 23);
		btnStopX.setEnabled(false);
		btnStopX.addActionListener(this);
		pnlAxfood.add(btnStopX);
		lblStatusX = new JLabel("Status: ");
		lblStatusX.setBounds(10, 31, 200, 13);
		pnlAxfood.add(lblStatusX);
		// Add Axfood panel to producers
		pnlProd.add(pnlAxfood);
		// Producer panel done, add to frame
		frame.add(pnlProd);

		// Next, add the three panels to Consumer panel
		JPanel pnlICA = new JPanel();
		pnlICA.setBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Consumer: ICA"));
		pnlICA.setBounds(19, 19, 415, 100);
		pnlICA.setLayout(null);

		// Content ICA panel
		// First the limits panel
		JPanel pnlLim = new JPanel();
		pnlLim.setBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Package Limits"));
		pnlLim.setBounds(6, 19, 107, 75);
		pnlLim.setLayout(null);
		JLabel lblItems = new JLabel("Items: 15");
		lblItems.setBounds(7, 20, 75, 13);
		pnlLim.add(lblItems);
		JLabel lblWeight = new JLabel("Weight: 27.00");
		lblWeight.setBounds(7, 35, 80, 13);
		pnlLim.add(lblWeight);
		JLabel lblVolume = new JLabel("Volume: 12");
		lblVolume.setBounds(7, 50, 75, 13);
		pnlLim.add(lblVolume);
		lblIcaItems = new JLabel("");
		lblIcaItems.setBounds(60, 20, 47, 13);
		pnlLim.add(lblIcaItems);
		lblIcaWeight = new JLabel("");
		lblIcaWeight.setBounds(60, 35, 47, 13);
		pnlLim.add(lblIcaWeight);
		lblIcaVolume = new JLabel("");
		lblIcaVolume.setBounds(60, 50, 47, 13);
		pnlLim.add(lblIcaVolume);
		pnlICA.add(pnlLim);
		// Then rest of controls
		lstIca = new JTextArea();
		lstIca.setEditable(false);
		JScrollPane spane = new JScrollPane(lstIca);
		spane.setBounds(307, 16, 102, 69);
		spane.setBorder(BorderFactory.createLineBorder(Color.black));
		pnlICA.add(spane);
		btnIcaStart = new JButton("Start Loading");
		btnIcaStart.setBounds(118, 64, 120, 23);
		btnIcaStart.addActionListener(this);
		pnlICA.add(btnIcaStart);
		btnIcaStop = new JButton("Stop");
		btnIcaStop.setBounds(240, 64, 60, 23);
		btnIcaStop.setEnabled(false);
		btnIcaStop.addActionListener(this);
		pnlICA.add(btnIcaStop);
		lblIcaStatus = new JLabel("Status: ");
		lblIcaStatus.setBounds(118, 16, 150, 23);
		pnlICA.add(lblIcaStatus);
		chkIcaCont = new JCheckBox("Continue load");
		chkIcaCont.setBounds(118, 39, 130, 17);
		pnlICA.add(chkIcaCont);
		// All done, add to consumers panel
		pnlCons.add(pnlICA);

		JPanel pnlCOOP = new JPanel();
		pnlCOOP.setBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Consumer: COOP"));
		pnlCOOP.setBounds(19, 139, 415, 100);
		pnlCOOP.setLayout(null);
		pnlCons.add(pnlCOOP);

		// Content COOP panel
		// First the limits panel
		JPanel pnlLimC = new JPanel();
		pnlLimC.setBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Package Limits"));
		pnlLimC.setBounds(6, 19, 107, 75);
		pnlLimC.setLayout(null);
		JLabel lblItemsC = new JLabel("Items: 12");
		lblItemsC.setBounds(7, 20, 75, 13);
		pnlLimC.add(lblItemsC);
		JLabel lblWeightC = new JLabel("Weight: 31.50");
		lblWeightC.setBounds(7, 35, 80, 13);
		pnlLimC.add(lblWeightC);
		JLabel lblVolumeC = new JLabel("Volume: 10");
		lblVolumeC.setBounds(7, 50, 75, 13);
		pnlLimC.add(lblVolumeC);
		lblCoopItems = new JLabel("");
		lblCoopItems.setBounds(60, 20, 47, 13);
		pnlLimC.add(lblCoopItems);
		lblCoopWeight = new JLabel("");
		lblCoopWeight.setBounds(60, 35, 47, 13);
		pnlLimC.add(lblIcaWeight);
		lblCoopVolume = new JLabel("");
		lblCoopVolume.setBounds(60, 50, 47, 13);
		pnlLimC.add(lblIcaVolume);
		pnlCOOP.add(pnlLimC);
		// Then rest of controls
		lstCoop = new JTextArea();
		lstCoop.setEditable(false);
		JScrollPane spaneC = new JScrollPane(lstCoop);
		spaneC.setBounds(307, 16, 102, 69);
		spaneC.setBorder(BorderFactory.createLineBorder(Color.black));
		pnlCOOP.add(spaneC);
		btnCoopStart = new JButton("Start Loading");
		btnCoopStart.setBounds(118, 64, 120, 23);
		btnCoopStart.addActionListener(this);
		pnlCOOP.add(btnCoopStart);
		btnCoopStop = new JButton("Stop");
		btnCoopStop.setBounds(240, 64, 60, 23);
		btnCoopStop.setEnabled(false);
		btnCoopStop.addActionListener(this);
		pnlCOOP.add(btnCoopStop);
		lblCoopStatus = new JLabel("Status: ");
		lblCoopStatus.setBounds(118, 16, 150, 23);
		pnlCOOP.add(lblCoopStatus);
		chkCoopCont = new JCheckBox("Continue load");
		chkCoopCont.setBounds(118, 39, 130, 17);
		pnlCOOP.add(chkCoopCont);
		// All done, add to consumers panel
		pnlCons.add(pnlCOOP);

		JPanel pnlCG = new JPanel();
		pnlCG.setBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Consumer: CITY GROSS"));
		pnlCG.setBounds(19, 262, 415, 100);
		pnlCG.setLayout(null);
		pnlCons.add(pnlCG);

		// Content CITY GROSS panel
		// First the limits panel
		JPanel pnlLimG = new JPanel();
		pnlLimG.setBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Package Limits"));
		pnlLimG.setBounds(6, 19, 107, 75);
		pnlLimG.setLayout(null);
		JLabel lblItemsG = new JLabel("Items: 19");
		lblItemsG.setBounds(7, 20, 75, 13);
		pnlLimG.add(lblItemsG);
		JLabel lblWeightG = new JLabel("Weight: 22.30");
		lblWeightG.setBounds(7, 35, 80, 13);
		pnlLimG.add(lblWeightG);
		JLabel lblVolumeG = new JLabel("Volume: 17");
		lblVolumeG.setBounds(7, 50, 75, 13);
		pnlLimG.add(lblVolumeG);
		lblCGItems = new JLabel("");
		lblCGItems.setBounds(60, 20, 47, 13);
		pnlLimG.add(lblCGItems);
		lblCGWeight = new JLabel("");
		lblCGWeight.setBounds(60, 35, 47, 13);
		pnlLimG.add(lblCGWeight);
		lblCGVolume = new JLabel("");
		lblCGVolume.setBounds(60, 50, 47, 13);
		pnlLimG.add(lblCGVolume);
		pnlCG.add(pnlLimG);
		// Then rest of controls
		lstCG = new JTextArea();
		lstCG.setEditable(false);
		JScrollPane spaneG = new JScrollPane(lstCG);
		spaneG.setBounds(307, 16, 102, 69);
		spaneG.setBorder(BorderFactory.createLineBorder(Color.black));
		pnlCG.add(spaneG);
		btnCGStart = new JButton("Start Loading");
		btnCGStart.setBounds(118, 64, 120, 23);
		btnCGStart.addActionListener(this);
		pnlCG.add(btnCGStart);
		btnCGStop = new JButton("Stop");
		btnCGStop.setBounds(240, 64, 60, 23);
		btnCGStop.setEnabled(false);
		btnCGStop.addActionListener(this);
		pnlCG.add(btnCGStop);
		lblCGStatus = new JLabel("Status: ");
		lblCGStatus.setBounds(118, 16, 150, 23);
		pnlCG.add(lblCGStatus);
		chkCGCont = new JCheckBox("Continue load");
		chkCGCont.setBounds(118, 39, 130, 17);
		pnlCG.add(chkCGCont);
		// All done, add to consumers panel
		pnlCons.add(pnlCOOP);

		// Add consumer panel to frame
		frame.add(pnlCons);
	}

	public JTextArea getListIca() {
		return lstIca;
	}

	public JTextArea getListCoop() {
		return lstCoop;
	}

	public JTextArea getListCityGross() {
		return lstCG;
	}

	public JCheckBox getCheckBoxIca() {
		return chkIcaCont;
	}

	public JCheckBox getCheckBoxCoop() {
		return chkCoopCont;
	}

	public JCheckBox getCheckBoxCityGross() {
		return chkCGCont;
	}

	public JProgressBar getBufferStatus() {
		return bufferStatus;
	}

	public JLabel getStatusScan() {
		return lblStatusS;
	}

	public JLabel getStatusArla() {
		return lblStatusA;
	}

	public JLabel getStatusAxFood() {
		return lblStatusX;
	}

	public JLabel getStatusIca() {
		return lblIcaStatus;
	}

	public JLabel getStatusCoop() {
		return lblCoopStatus;
	}

	public JLabel getStatusCityGross() {
		return lblCGStatus;
	}

	public JLabel getCurrentAmtInBuffer() {
		return currentAmtInBuffer;
	}

	public JButton getBtnIcaStop() {
		return btnIcaStop;
	}

	public JButton getBtnCoopStop() {
		return btnCoopStop;
	}

	public JButton getBtnCityGrossStop() {
		return btnCGStop;
	}

	public JButton getBtnIcaStart() {
		return btnIcaStart;
	}

	public JButton getBtnCoopStart() {
		return btnCoopStart;
	}

	public JButton getBtnCityGrossStart() {
		return btnCGStart;
	}

	/**
	 * Method that determines what will happen when the different buttons are clicked
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnStartS) {
			btnStartS.setEnabled(false);
			controller.runScan();
			btnStopS.setEnabled(true);
		}

		if(e.getSource() == btnStartA) {
			btnStartA.setEnabled(false);
			controller.runArla();
			btnStopA.setEnabled(true);
		}

		if(e.getSource() == btnStartX) {
			btnStartX.setEnabled(false);
			controller.runAxFood();
			btnStopX.setEnabled(true);
		}

		if(e.getSource() == btnStopS) {
			btnStopS.setEnabled(false);
			controller.stopScan();
			btnStartS.setEnabled(true);
		}

		if(e.getSource() == btnStopA) {
			btnStopA.setEnabled(false);
			controller.stopArla();
			btnStartA.setEnabled(true);
		}

		if(e.getSource() == btnStopX) {
			btnStopX.setEnabled(false);
			controller.stopAxFood();
			btnStartX.setEnabled(true);
		}

		if(e.getSource() == btnIcaStart) {
			btnIcaStart.setEnabled(false);
			controller.runIca();
			btnIcaStop.setEnabled(true);
		}

		if(e.getSource() == btnCoopStart) {
			btnCoopStart.setEnabled(false);
			controller.runCoop();
			btnCoopStop.setEnabled(true);
		}

		if(e.getSource() == btnCGStart) {
			btnCGStart.setEnabled(false);
			controller.runCityGross();
			btnCGStop.setEnabled(true);
		}

		if(e.getSource() == btnIcaStop) {
			btnIcaStop.setEnabled(false);
			controller.stopIca();
			btnIcaStart.setEnabled(true);
		}

		if(e.getSource() == btnCoopStop) {
			btnCoopStop.setEnabled(false);
			controller.stopCoop();
			btnCoopStart.setEnabled(true);
		}

		if(e.getSource() == btnCGStop) {
			btnCGStop.setEnabled(false);
			controller.stopCityGross();
			btnCGStart.setEnabled(true);
		}
	}
}
