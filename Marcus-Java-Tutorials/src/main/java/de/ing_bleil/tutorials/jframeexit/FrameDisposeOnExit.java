package de.ing_bleil.tutorials.jframeexit;

import javax.swing.JFrame;

public class FrameDisposeOnExit extends FrameExitOnClose {

	public FrameDisposeOnExit(String title) {
		super(title);
	}

	public static void main(String[] args) {
		FrameDisposeOnExit app = new FrameDisposeOnExit(
				"JFrame.DISPOSE_ON_CLOSE");

		app.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		app.setVisible(true);
	}
}
