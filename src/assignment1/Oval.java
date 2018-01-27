package assignment1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

/**
 * This class draws an oval on a JPanel
 * @author Nicolai Jaynes
 *
 */

public class Oval extends JPanel {

	@Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs);

        Graphics2D g2d = (Graphics2D) grphcs;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setColor(Color.GREEN);
        g2d.fillOval(10,10,20,20);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(35, 35);
    }
}
