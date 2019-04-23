package livrable1;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.border.BevelBorder;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.JList;

public class GestionArtistes extends JFrame {
	private JTextField textField;
	private JTable table;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionArtistes frame = new GestionArtistes();
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
	public GestionArtistes() {
		setTitle("Gestion des artistes");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 673, 479);
		getContentPane().setLayout(null);
		
		JLabel lblRechercherUnArtiste = new JLabel("Rechercher un artiste");
		lblRechercherUnArtiste.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblRechercherUnArtiste.setBounds(35, 26, 180, 15);
		getContentPane().add(lblRechercherUnArtiste);
		
		textField = new JTextField();
		textField.setBounds(35, 53, 241, 19);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnRechercher = new JButton("Rechercher");
		btnRechercher.setBounds(288, 47, 114, 25);
		getContentPane().add(btnRechercher);
		
		JButton btnQuitter = new JButton("Quitter");
		btnQuitter.setBounds(521, 47, 114, 25);
		getContentPane().add(btnQuitter);
		
		JLabel lblArtistes = new JLabel("Artistes");
		lblArtistes.setFont(new Font("Dialog", Font.BOLD, 16));
		lblArtistes.setBounds(35, 106, 104, 25);
		getContentPane().add(lblArtistes);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{new Integer(1), "Jean", null},
			},
			new String[] {
				"No", "Nom", "Membre"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, Object.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.setBounds(214, 171, 1, 1);
		getContentPane().add(table);
		
		JButton btnNouveau = new JButton("Nouveau");
		btnNouveau.setBounds(521, 134, 114, 25);
		getContentPane().add(btnNouveau);
		
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.setBounds(521, 171, 114, 25);
		getContentPane().add(btnAjouter);
		
		JButton btnModifier = new JButton("Modifier");
		btnModifier.setBounds(521, 208, 114, 25);
		getContentPane().add(btnModifier);
		
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.setBounds(521, 245, 114, 25);
		getContentPane().add(btnSupprimer);
		
		JButton btnRemplacer = new JButton("Remplacer");
		btnRemplacer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnRemplacer.setBounds(25, 245, 114, 25);
		getContentPane().add(btnRemplacer);
		
		JLabel lblInformations = new JLabel("Informations");
		lblInformations.setFont(new Font("Dialog", Font.BOLD, 16));
		lblInformations.setBounds(35, 293, 116, 25);
		getContentPane().add(lblInformations);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(100, 330, 144, 19);
		getContentPane().add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(100, 361, 144, 19);
		getContentPane().add(textField_2);
		
		JCheckBox chckbxMember = new JCheckBox("Membr\ne");
		chckbxMember.setBounds(35, 390, 126, 23);
		getContentPane().add(chckbxMember);
		
		JLabel lblNumro = new JLabel("Numéro");
		lblNumro.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNumro.setBounds(35, 330, 60, 15);
		getContentPane().add(lblNumro);
		
		JLabel lblNom = new JLabel("Nom");
		lblNom.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNom.setBounds(35, 363, 60, 15);
		getContentPane().add(lblNom);
		
		JList list = new JList();
		list.setBounds(288, 330, 164, 87);
		getContentPane().add(list);
	}
}
