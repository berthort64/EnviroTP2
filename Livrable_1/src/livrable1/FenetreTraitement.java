package livrable1;
 
import java.awt.BorderLayout;
import java.awt.EventQueue;
 
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
 
public class FenetreTraitement extends JFrame {
 
    private JPanel contentPane;
    private JButton btnArtistes;
    private JButton btnAlbums;
    private JButton btnQuitter;
 
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    FenetreTraitement frame = new FenetreTraitement();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
 
    /**
     * Create the frame.
     */
    public FenetreTraitement() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(FenetreTraitement.class.getResource("/livrable1/MusicNote.png")));
        setTitle("Gestion musique \u266A");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 300, 200);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        contentPane.add(getBtnArtistes());
        contentPane.add(getBtnAlbums());
        contentPane.add(getButton_1());
    }
    private JButton getBtnArtistes() {
        if (btnArtistes == null) {
            btnArtistes = new JButton("Artistes");
            btnArtistes.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                   
                    GestionArtistes gestionArtistes=new GestionArtistes();
                    gestionArtistes.setVisible(true);
                   
                }
            });
            btnArtistes.setBounds(87, 11, 104, 35);
        }
        return btnArtistes;
    }
    private JButton getBtnAlbums() {
        if (btnAlbums == null) {
            btnAlbums = new JButton("Albums");
            btnAlbums.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                   
                    JOptionPane.showMessageDialog(null, "Cette fonction n'est pas encore implémenté");
                   
                }
            });
            btnAlbums.setBounds(87, 57, 104, 35);
        }
        return btnAlbums;
    }
    private JButton getButton_1() {
        if (btnQuitter == null) {
            btnQuitter = new JButton("Quitter");
            btnQuitter.addActionListener(new ActionListener() {
               
                public void actionPerformed(ActionEvent e) {
                   
                     int option = JOptionPane.showOptionDialog(null,
                                "Voulez vous vraiment quitter?",
                                null,
                                JOptionPane.OK_CANCEL_OPTION,
                                JOptionPane.INFORMATION_MESSAGE,
                                null,
                                new String[]{"Oui", "Non"},
                                "default");
                        if( option == JOptionPane.YES_OPTION ) {  
                            System.exit( 0 );  
                        }  
                   
                }
            });
            btnQuitter.setBounds(87, 103, 104, 35);
        }
        return btnQuitter;
    }
}