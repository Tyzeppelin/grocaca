package fr.fboschet.voiture.ui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;

import fr.fboschet.voiture.SecondHandVehicle;
import fr.fboschet.voiture.builder.CardinalFactory;
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
		CardinalFactory cardinal = CardinalFactory.getInstance();

		JFrame main = factory.getJFrame("SECOND HAND VEHICLE OPTIONS");

		JPanel mainPanel = factory.getVerticalPanel();

		//Panel that display information (make, model, nbOfOwner(s))
		JPanel infoPanel = factory.getFlowPanel();

		JPanel makePanel = factory.getHorizontalPanel();
		//factory.addBorders.accept(makePanel);
		JLabel make = factory.getJLabel(vehicle.getMake()+" ", 60, Font.BOLD);
		JLabel model = factory.getJLabel(vehicle.getModel(), 60, Font.PLAIN);
		JLabel year = factory.getJLabel(vehicle.getYear()+"", 12, Font.ITALIC);
		makePanel.add(make);
		makePanel.add(model);
		makePanel.add(year);
		infoPanel.add(makePanel);

		// Panel that display the number of owners this car had
		JPanel ownerPanel = factory.getPanel();
		int owners = vehicle.getNumberOfOwners();
		JLabel now = factory.getJlabel(cardinal.getCardinal(owners), 13);
		JLabel owner = factory.getJlabel("owner", 13);
		ownerPanel.add(now);
		ownerPanel.add(owner);
		infoPanel.add(ownerPanel);
		//

		// The panel that display the value of the vehicle and
		// display the edit mode when needed
		final JPanel valuePanel = factory.getBorderPanel();
		//factory.addBorders.accept(valuePanel);

		// A panel to display the current value and a button to switch to edit mode
		JPanel displayValue = factory.getBorderPanel();
		JLabel value = factory.getJLabel(vehicle.getValue()+"€", 100, Font.BOLD);
		JButton editMode = factory.getJButton("decrease");

		displayValue.add(value);
		displayValue.add(editMode, BorderLayout.EAST);

		// A hidden panel which is shown when edit mode is active
		JPanel editValue = factory.getBorderPanel();

		// We use a JSpinner to decrease the value of the vehicle
		// And it also ensure that the value of the spinner is a double
		JSpinner editField = factory.getDoubleJSpiner(vehicle.getValue());
		JPanel buttons = factory.getGridPanel(2,  1);

		JButton update = factory.getJButton("update");
		JButton discard = factory.getJButton("discard");

		buttons.add(update);
		buttons.add(discard);

		editValue.add(editField, BorderLayout.CENTER);
		editValue.add(buttons, BorderLayout.EAST);

		// listeners
		// When we click on the decrease button
		editMode.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand() == editMode.getActionCommand()) {
					// We display the edit panel in place of the display panel
					valuePanel.removeAll();
					editField.setValue(vehicle.getValue());
					valuePanel.add(editValue);
					main.pack();
				}
			}
		});

		update.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand() == update.getActionCommand()) {
					double newValue = 0.0;
					newValue = (Double)editField.getValue();
					// This shouldn't be happening since we set the SpinnerNumberModel with a 
					// maximum of vehicle.value()
					if (newValue > vehicle.getValue()) {
						JOptionPane.showMessageDialog(main, "You can only reduce the value of the vehicle,\n value : "+value.getText(), "Warning", JOptionPane.WARNING_MESSAGE);
					}
					else {
						// we decrease the value of the current vehicle
						vehicle.decreaseValue(newValue);
						value.setText(vehicle.getValue()+"€");
						// and display the display panel
						valuePanel.removeAll();
						valuePanel.add(displayValue);
						main.pack();
					}
				}
			}
		});

		discard.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand() == discard.getActionCommand()) {
					// discard the decrease of the value <=> do nothing but switch display/edit
					valuePanel.removeAll();
					valuePanel.add(displayValue);
					main.pack();
				}
			}
		});

		valuePanel.add(displayValue);
		//


		// Display tax and qualifiedForScrappage()
		JPanel taxPanel = factory.getPanel();

		// display the tax
		JPanel taxDisplay = factory.getFlowPanel();
		JLabel taxLabel = factory.getJlabel("Tax : ", 20);
		JButton updateTax = factory.getJButton("calculate");
		updateTax.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand() == updateTax.getActionCommand()) {
					JSpinner spinner = factory.getIntJSpinner();
					int option = JOptionPane.showOptionDialog(null, 
							spinner, 
							"Engine Size", 
							JOptionPane.OK_CANCEL_OPTION, 
							JOptionPane.QUESTION_MESSAGE, 
							null, null, null);
					if (option == JOptionPane.OK_OPTION) {
						double tax = vehicle.calculateTaxPayable((Integer)spinner.getValue());
						taxLabel.setText("Tax : " + tax + "€");
						taxDisplay.remove(updateTax);
					}
				}
			}
		});

		taxDisplay.add(taxLabel);
		taxDisplay.add(updateTax);

		// display scrappage
		JPanel scrappageDisplay = factory.getPanel();
		JLabel scrappage = factory.getJlabel("Good for scrappage? "+vehicle.qualifyForScrappage(), 20);
		scrappageDisplay.add(scrappage);


		taxPanel.add(taxDisplay);
		taxPanel.add(scrappageDisplay);
		//

		mainPanel.add(infoPanel);
		mainPanel.add(factory.getJSeparator());
		mainPanel.add(valuePanel);
		mainPanel.add(factory.getJSeparator());
		mainPanel.add(taxPanel);

		main.add(mainPanel);
		main.pack();
		main.setVisible(true);

	}
}