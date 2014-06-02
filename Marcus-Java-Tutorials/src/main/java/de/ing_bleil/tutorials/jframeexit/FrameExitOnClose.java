package de.ing_bleil.tutorials.jframeexit;

import javax.swing.JFrame;

public class FrameExitOnClose extends JFrame {

	public FrameExitOnClose(String title) {
		super(title);

		setSize(400, 300);
		setLocationRelativeTo(null);
	}

	public static void main(String[] args) {
		FrameExitOnClose app = new FrameExitOnClose("JFrame.EXIT_ON_CLOSE");

		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		app.setVisible(true);
	}
}
