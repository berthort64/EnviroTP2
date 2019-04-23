package livrable1;

import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ConnectionBDD con=new ConnectionBDD();
		con.SupprimerToutEnregistrements();
		con.Ajouter("Artiste1","1","Images/img1.png");
		con.Ajouter("Artiste2","0","Images/img2");
		con.Ajouter("Artiste3","0","Images/img3");
		con.Ajouter("Artiste4","1","Images/img4");
		con.Ajouter("Artiste5","0","Images/img5");
		
		
		try {
			BufferedImage img;
			img = ImageIO.read(new File("Images/img1.png"));
			ImageIcon icon=new ImageIcon(img);
	        JFrame frame=new JFrame();
	        frame.setLayout(new FlowLayout());
	        frame.setSize(200,300);
	        JLabel lbl=new JLabel();
	        lbl.setIcon(icon);
	        frame.add(lbl);
	        frame.setVisible(true);
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    
		
	}

}
