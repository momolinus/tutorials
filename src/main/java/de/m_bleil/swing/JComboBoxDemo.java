/**
 *
 */
package de.m_bleil.swing;

import java.awt.FlowLayout;
import java.awt.event.*;

import javax.swing.*;

import org.pmw.tinylog.Logger;

/**
 * @author Marcus Bleil, www.marcusbleil.de
 */
public class JComboBoxDemo extends JFrame implements ItemListener, ActionListener {

	private JComboBox<String> comboBoxWith3Items;

	private JButton itemSetterButton;

	private JButton modelSetterButton;

	public JComboBoxDemo() {
		this.setSize(800, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new FlowLayout());
		this.setLocationRelativeTo(null);

		itemSetterButton = new JButton("set 'item 1' selected");
		itemSetterButton.setName("itemSetterButton");
		itemSetterButton.addActionListener(this);
		this.add(itemSetterButton);

		modelSetterButton = new JButton("set models 'item 2' selected");
		modelSetterButton.setName("modelSetterButton");
		modelSetterButton.addActionListener(this);
		this.add(modelSetterButton);

		comboBoxWith3Items = new JComboBox<String>(new String[] { "item 0", "item 1", "item 3" });
		comboBoxWith3Items.setName("comboBoxWith3Items");
		comboBoxWith3Items.addItemListener(this);
		comboBoxWith3Items.addActionListener(this);
		this.add(comboBoxWith3Items);
	}

	public static void main(String[] args) {
		JComboBoxDemo app = new JComboBoxDemo();
		app.setVisible(true);
	}

	@Override
	public void itemStateChanged(ItemEvent itemEvent) {

		Logger.info("state {}", () -> {

			if (itemEvent.getStateChange() == ItemEvent.DESELECTED) {
				return "DESELECTED";
			}
			else if (itemEvent.getStateChange() == ItemEvent.SELECTED) {
				return "SELECTED";
			}
			return "UNKNOW_STATE";
		});

	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		if (actionEvent.getSource() == itemSetterButton) {
			Logger.info("on {} action", itemSetterButton.getName());
			comboBoxWith3Items.setSelectedIndex(0);
		}
		else if (actionEvent.getSource() == modelSetterButton) {
			Logger.info("on {} action", modelSetterButton.getName());
			comboBoxWith3Items.getModel().setSelectedItem(comboBoxWith3Items.getItemAt(2));
		}
		else if (actionEvent.getSource() == comboBoxWith3Items) {
			Logger.info("on {} action", comboBoxWith3Items.getName());
		}
	}
}
