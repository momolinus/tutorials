/**
 * created at 04.01.2017
 */
package de.m_bleil.javafx.app;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * @author Marcus Bleil, www.m-bleil.de
 */
public class ExceptionDemoSwing extends JFrame implements ActionListener {

	private JButton exceptionButton;
	private JCheckBox checkBox;

	/**
	 * 
	 */
	public ExceptionDemoSwing() {
		exceptionButton = new JButton("exception");
		exceptionButton.addActionListener(this);

		checkBox = new JCheckBox("custom exception", false);
		checkBox.addActionListener(this);

		this.setLayout(new FlowLayout());

		this.add(exceptionButton);
		this.add(checkBox);
		this.setSize(new Dimension(600, 400));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		this.setLocationRelativeTo(null);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ExceptionDemoSwing app = new ExceptionDemoSwing();

		app.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == exceptionButton) {
			throw new RuntimeException(
				"unhandled exception in " + Thread.currentThread().getName());
		}

		if (e.getSource() == checkBox) {
			if (checkBox.isSelected()) {
				Thread.currentThread().setUncaughtExceptionHandler((t, ex) -> {
					System.out.println("#########");
					ex.printStackTrace();
					
					System.exit(9);
				});
			}
			else {
				Thread.currentThread().setUncaughtExceptionHandler(null);
			}
		}
	}
}
