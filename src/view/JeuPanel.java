package view;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class JeuPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	public static final int TAILLE_CASE = 4;

	public JeuPanel() {

		// Refresh rate : 16ms*60 = 960ms (~1s) ==> 60fps
		Timer refresh = new Timer(16, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				updateUI();
			}
		});
		refresh.start();
	}


	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
}
