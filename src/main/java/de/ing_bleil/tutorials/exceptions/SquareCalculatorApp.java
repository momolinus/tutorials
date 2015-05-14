package de.ing_bleil.tutorials.exceptions;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SquareCalculatorApp extends JFrame {

	private JTextField area;
	private JTextField side;
	private SquareCalculator calculator;

	public SquareCalculatorApp() {
		calculator = new SquareCalculator();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("SquareCalculatorApp");

		JLabel areaLabel = new JLabel("Area");

		area = new JTextField();
		area.setHorizontalAlignment(SwingConstants.RIGHT);
		area.setColumns(10);

		side = new JTextField();
		side.setEditable(false);
		side.setHorizontalAlignment(SwingConstants.RIGHT);
		side.setColumns(10);

		JButton sideCalculateButton = new JButton("Calculate side from area");
		sideCalculateButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				double areaValue;

				try {
					areaValue = Double.parseDouble(area.getText());
					side.setText(String.format("%.2f", calculator.sideLength(areaValue)));
				}
				catch (NullPointerException | NumberFormatException | SquareCalculatorExceptions e) {
					JOptionPane
							.showMessageDialog(	SquareCalculatorApp.this,
												"<html><h3>You put a wrong area value '"
													+ area.getText()
													+ "' into area field, only positive numbers allowed!</h3>"
													+ "<p>see detailed error message:<br>"
													+ e.toString() + "</p></html>", "Error",
												JOptionPane.ERROR_MESSAGE);
					area.setText("");
					side.setText("");
				}
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout
				.setHorizontalGroup(groupLayout
						.createParallelGroup(Alignment.LEADING)
						.addGroup(	groupLayout
											.createSequentialGroup()
											.addGap(18)
											.addGroup(	groupLayout
																.createParallelGroup(	Alignment.TRAILING,
																						false)
																.addComponent(	sideCalculateButton,
																				Alignment.LEADING,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE)
																.addGroup(	Alignment.LEADING,
																			groupLayout
																					.createSequentialGroup()
																					.addComponent(	areaLabel)
																					.addPreferredGap(	ComponentPlacement.UNRELATED)
																					.addComponent(	area,
																									GroupLayout.PREFERRED_SIZE,
																									GroupLayout.DEFAULT_SIZE,
																									GroupLayout.PREFERRED_SIZE)
																					.addPreferredGap(	ComponentPlacement.UNRELATED)
																					.addComponent(	side,
																									GroupLayout.PREFERRED_SIZE,
																									GroupLayout.DEFAULT_SIZE,
																									GroupLayout.PREFERRED_SIZE)))
											.addContainerGap(201, Short.MAX_VALUE)));
		groupLayout
				.setVerticalGroup(groupLayout
						.createParallelGroup(Alignment.LEADING)
						.addGroup(	groupLayout
											.createSequentialGroup()
											.addContainerGap()
											.addGroup(	groupLayout
																.createParallelGroup(	Alignment.BASELINE)
																.addComponent(	area,
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE)
																.addComponent(areaLabel)
																.addComponent(	side,
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE))
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(sideCalculateButton)
											.addContainerGap(197, Short.MAX_VALUE)));
		getContentPane().setLayout(groupLayout);
	}

	public static void main(String[] args) {
		SquareCalculatorApp app = new SquareCalculatorApp();

		app.setSize(400, 300);
		app.setLocationRelativeTo(null);
		app.setVisible(true);

	}
}
