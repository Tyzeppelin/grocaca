package fr.fboschet.voiture.builder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.util.function.Consumer;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

public class ComponentFactory {

	// My factory is a lazy singleton.
	// It means there will only be one instance of it
	// @runtime, and it will be instantiate only when it
	// will be needed
	private static ComponentFactory INSTANCE;

	private ComponentFactory() {}

	public static ComponentFactory getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new ComponentFactory();
		}
		return INSTANCE;
	}

	// consumer that add a 1px border to a JPanel
	// I replaced bordes by JSeparator
	public Consumer<JPanel> addBorders = (JPanel it) -> it.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	
	// We use the Arial font by default. Can't be override
	// We still can change style (default is PLAIN) and size
	public JLabel getJlabel(String s, int size) {
		JLabel j = new JLabel(s);
		j.setFont(new Font("Arial", Font.PLAIN, size));
		return j;
	}

	public JLabel getJLabel(String s, int size, int style) {
		JLabel j = new JLabel(s);
		j.setFont(new Font("Arial", style, size));
		j.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		return j;
	}

	// standard button
	public JButton getJButton(String string) {
		return new JButton(string);
	}
	
	// standard textField (has been replaced by spinner)
	@Deprecated
	public JTextField getJtextField(String text) {
		JTextField jtf = new JTextField(text);
		jtf.setFont(new Font("Arial", Font.BOLD, 100));
		return jtf;
	}

	// Spinner that allow only Double. Used for decrease the value
	public JSpinner getDoubleJSpiner(double init) {
		// we need to attach a model to our spinner
		SpinnerNumberModel sModel = new SpinnerNumberModel(0.0, 0.0, init, 1.0);
		JSpinner spinner = new JSpinner(sModel);
		spinner.setValue(init);
		spinner.setFont(new Font("Arial", Font.BOLD, 100));
		// get the inner editor inside the JSpinner
		// then set the alignment.
		// We need this since JSpinner is only a container
		((JSpinner.DefaultEditor)spinner.getEditor()).getTextField().setHorizontalAlignment(JFormattedTextField.LEFT);
		// otherwise its ugly
		((JSpinner.DefaultEditor)spinner.getEditor()).getTextField().setColumns(3);
		return spinner;
	}
	
	// Spinner of Integer . Used for setting the engine size
	public JSpinner getIntJSpinner() {
		SpinnerNumberModel sModel = new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1);
		JSpinner spinner = new JSpinner(sModel);
		spinner.setValue(0);
		return spinner;
	}
	
	public JSeparator getJSeparator() {
		return new JSeparator();
	}

	// standard panel
	public JPanel getPanel() {
		return new JPanel();
	}

	// panel with a X layout (X ::= getXPanel)
	
	// ...
	public JPanel getFlowPanel() {
		return new JPanel(new FlowLayout());
	}
	
	// ...
	public JPanel getVerticalPanel() {
		JPanel j = getPanel();
		j.setLayout( new BoxLayout(j, BoxLayout.Y_AXIS));
		return j;
	}
	
	// ...
	public JPanel getHorizontalPanel(){
		JPanel j = getPanel();
		j.setLayout( new BoxLayout(j, BoxLayout.X_AXIS));
		return j;
	}
	
	// ...
	public JPanel getBorderPanel() {
		return new JPanel(new BorderLayout());
	}
	
	// ...
	public JPanel getGridPanel(int i, int j) {
		return new JPanel(new GridLayout(i, j));
	}
	
	// Custom JFrame
	public JFrame getJFrame(String title) {
		JFrame frame = new JFrame(title);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// default icon image
		frame.setIconImage((new ImageIcon("res/images/icon.png")).getImage());
		// we bind the ESCAPE key to the WINDOW_CLOSING event
		KeyboardFocusManager fm = KeyboardFocusManager.getCurrentKeyboardFocusManager();
		fm.addKeyEventDispatcher(new KeyEventDispatcher() {
			@Override
			public boolean dispatchKeyEvent(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ESCAPE)
					frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));;
					return arg0.getKeyCode() == KeyEvent.VK_ESCAPE;
			}
		});
		return frame;
	}
}
