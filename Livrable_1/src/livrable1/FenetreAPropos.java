package livrable1;

import javax.swing.JFrame;
import java.awt.TextArea;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Toolkit;

public class FenetreAPropos extends JFrame{
	
	public FenetreAPropos(){
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(FenetreAPropos.class.getResource("/livrable1/MusicNote.png")));
		setTitle("\u00C0 Propos \u266A");
		
	setSize(331,131);	
	getContentPane().setLayout(null);
	
	JLabel lblNewLabel = new JLabel("\u00A9 2019, Jo\u00EBl Pimpar\u00E9 Larocque - Gabriel G\u00E9rin Roze.");
	lblNewLabel.setBounds(10, 11, 295, 14);
	getContentPane().add(lblNewLabel);
	
	JLabel lblAllRightsReserved = new JLabel("All rights reserved");
	lblAllRightsReserved.setBounds(10, 48, 295, 14);
	getContentPane().add(lblAllRightsReserved);
		
	}
}
