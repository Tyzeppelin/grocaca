package fr.fboschet.voiture.ui;

import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import fr.fboschet.voiture.SecondHandVehicle;
import fr.fboschet.voiture.Vehicle;

public class DialogFactory {

	private static DialogFactory INSTANCE;
	
	private DialogFactory() {
	}
	
	public static DialogFactory getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new DialogFactory();
		}
		return INSTANCE;
	}
	
	public int passwordDialog() {
		JPanel panel = new JPanel();
		JLabel label = new JLabel("Enter a password:");
		JPasswordField pass = new JPasswordField(10);
		panel.add(label);
		panel.add(pass);
		String[] options = new String[]{"OK", "Cancel"};
		JOptionPane.showOptionDialog(null, panel, "The title",
		                         JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
		                         null, options, options[1]);
		return new String(pass.getPassword()).hashCode();
	}

	public SecondHandVehicle choiceDialog(List<Vehicle> vehicles) {
		return (SecondHandVehicle)JOptionPane.showInputDialog(null, "Choose a SecondHandVehicle", 
				"VEHICLES AVAILABLE", JOptionPane.INFORMATION_MESSAGE, null, vehicles.toArray(), vehicles.get(0));
	}

}
