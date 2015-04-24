package view;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class FenetrePlateau extends JFrame {
	private static final long serialVersionUID = 1L;

	public FenetrePlateau() {
		setTitle("Tron Game");
		setSize(817, 596);
		setLocationRelativeTo(null);		// Au centre de l'écran
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		addKeyListener(new ActionUser());
	}

	class ActionUser implements KeyListener{

		@Override
		public void keyTyped(KeyEvent e) { }

		@Override
		public void keyPressed(KeyEvent e) {
			
		}

		@Override
		public void keyReleased(KeyEvent e) { }		
	}

}