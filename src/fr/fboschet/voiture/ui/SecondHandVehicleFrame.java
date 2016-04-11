package fr.fboschet.voiture.ui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fr.fboschet.voiture.SecondHandVehicle;
import fr.fboschet.voiture.builder.ComponentFactory;

// NO MORE
public class SecondHandVehicleFrame{

	private static SecondHandVehicleFrame INSTANCE;

	private SecondHandVehicleFrame() {}

	public static SecondHandVehicleFrame getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new SecondHandVehicleFrame();
		}
		return INSTANCE;
	}

	public void run(SecondHandVehicle vehicle) {
		ComponentFactory factory = ComponentFactory.getInstance();

		JFrame main = factory.getJFrame("SECOND HAND VEHICLE OPTIONS");

		JPanel mainPanel = factory.getGridPanel(3, 1);

		//Panel that display information (make, model, nbOfOwner(s))
		JPanel infoPanel = factory.getFlowPanel();

		JPanel makePanel = factory.getBoxPanel();
		factory.addBorders.accept(makePanel);
		JLabel make = factory.getJLabel(vehicle.getMake()+" ", 60, Font.BOLD);
		JLabel model = factory.getJLabel(vehicle.getModel(), 60, Font.PLAIN);
		JLabel year = factory.getJLabel(vehicle.getYear()+"", 12, Font.ITALIC);
		makePanel.add(make);
		makePanel.add(model);
		makePanel.add(year);
		infoPanel.add(makePanel);

		// TODO: cardinal factory
		JPanel ownerPanel = factory.getPanel();
		int owners = vehicle.getNumberOfOwners();
		JLabel now = factory.getJlabel(owners+" ", 13);
		JLabel owner = factory.getJlabel("owner"+(owners>1 ? "s" : ""), 13);
		ownerPanel.add(now);
		ownerPanel.add(owner);
		infoPanel.add(ownerPanel);
		//

		// The panel that display the value of the vehicle and
		// display the edit mode when needed
		final JPanel valuePanel = factory.getBorderPanel();
		factory.addBorders.accept(valuePanel);

		// A panel to display the current value and a button to switch to edit mode
		JPanel displayValue = factory.getBorderPanel();
		JLabel value = factory.getJLabel(vehicle.getValue()+" ", 100, Font.BOLD);
		JButton editMode = factory.getJButton("decrease");

		displayValue.add(value);
		displayValue.add(editMode, BorderLayout.EAST);

		// A hidden panel which is shown when edit mode is active
		JPanel editValue = factory.getBorderPanel();

		// TODO: formattedfield
		JTextField editField = factory.getJtextField(value.getText());
		JPanel buttons = factory.getGridPanel(2,  1);

		JButton update = factory.getJButton("update");
		JButton discard = factory.getJButton("discard");

		buttons.add(update);
		buttons.add(discard);

		editValue.add(editField, BorderLayout.CENTER);
		editValue.add(buttons, BorderLayout.EAST);

		// listeners
		// When we click on the "
		editMode.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand() == editMode.getActionCommand()) {
					valuePanel.removeAll();
					valuePanel.add(editValue);
					main.pack();
				}
			}
		});

		update.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand() == update.getActionCommand()) {
					boolean correctValue = true;
					double newValue = 0.0;
					try {
						newValue = Double.parseDouble(editField.getText());
					}catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(main, "No.", "Error", JOptionPane.ERROR_MESSAGE);
						correctValue = false;
					}

					if (correctValue){
						if (newValue > vehicle.getValue()) {
							JOptionPane.showMessageDialog(main, "You can only reduce the value of the vehicle,\n value : "+value.getText(), "Warning", JOptionPane.WARNING_MESSAGE);
						}
						else {
							vehicle.decreaseValue(newValue);
							value.setText(vehicle.getValue()+" ");

							valuePanel.removeAll();
							valuePanel.add(displayValue);
							main.pack();
						}
					}
				}
			}
		});

		discard.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand() == discard.getActionCommand()) {
					valuePanel.removeAll();
					valuePanel.add(displayValue);
					main.pack();
				}
			}
		});

		valuePanel.add(displayValue);
		//
		
		
		
		JPanel taxPanel = factory.getPanel();
		//

		mainPanel.add(infoPanel);
		mainPanel.add(valuePanel);
		mainPanel.add(taxPanel);

		main.add(mainPanel);
		main.pack();
		main.setVisible(true);
	}
}