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
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
	
	public Consumer<JPanel> addBorders = (JPanel it) -> it.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	
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
	

	public JButton getJButton(String string) {
		return new JButton(string);
	}
	
	public JTextField getJtextField(String text) {
		JTextField jtf = new JTextField(text);
		jtf.setFont(new Font("Arial", Font.BOLD, 100));
		return jtf;
	}
	
	public JPanel getPanel() {
		return new JPanel();
	}
	
	public JPanel getFlowPanel() {
		return new JPanel(new FlowLayout());
	}
	
	public JPanel getBoxPanel(){
		JPanel j = getPanel();
		j.setLayout( new BoxLayout(j, BoxLayout.X_AXIS));
		return j;
	}
	
	public JPanel getBorderPanel() {
		return new JPanel(new BorderLayout());
	}
	
	public JPanel getGridPanel(int i, int j) {
		return new JPanel(new GridLayout(i, j));
	}
	
	public JFrame getJFrame(String title) {
		JFrame frame = new JFrame(title);
        frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
