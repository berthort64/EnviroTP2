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
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.ListSelectionModel;

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
		
		ConnectionBDD con =new ConnectionBDD();
		
		JCheckBox chckbxMember = new JCheckBox("Membre");
		chckbxMember.setBounds(35, 390, 126, 23);
		getContentPane().add(chckbxMember);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[0][3],
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
		table.setBounds(171, 134, 338, 135);
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	        	if (table.getSelectedRow() != -1) {
		            textField_1.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
		            textField_2.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
		            chckbxMember.setSelected(table.getValueAt(table.getSelectedRow(), 2).toString().equalsIgnoreCase("oui"));
	        	} else {
	        		textField_1.setText("");
					textField_2.setText("");
					chckbxMember.setSelected(false);
	        	}
	        }
	    });
		getContentPane().add(table);
		
		JButton btnRechercher = new JButton("Rechercher");
		btnRechercher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<Artiste> artistes = con.Rechercher(textField.getText());
				
				Object[][] liste = new Object[artistes.size()][3];
				
				for (int i = 0; i < artistes.size(); i++) {
					liste[i][0] = artistes.get(i).getId();
					liste[i][1] = artistes.get(i).getNom();
					liste[i][2] = artistes.get(i).getMembre().equals("1") ? "Oui" : "Non";
				}
				
				table.setModel(new DefaultTableModel(
						liste,
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
			}
		});
		btnRechercher.setBounds(288, 47, 114, 25);
		getContentPane().add(btnRechercher);
		
		JButton btnQuitter = new JButton("Quitter");
		btnQuitter.setBounds(521, 47, 114, 25);
		getContentPane().add(btnQuitter);
		
		JLabel lblArtistes = new JLabel("Artistes");
		lblArtistes.setFont(new Font("Dialog", Font.BOLD, 16));
		lblArtistes.setBounds(35, 106, 104, 25);
		getContentPane().add(lblArtistes);
		
		JButton btnNouveau = new JButton("Nouveau");
		btnNouveau.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField_1.setText("");
				textField_2.setText("");
				chckbxMember.setSelected(false);
			}
		});
		btnNouveau.setBounds(521, 134, 114, 25);
		getContentPane().add(btnNouveau);
		
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (textField_1.getText().equals("") ? true : JOptionPane.showConfirmDialog(null, "Attention!\n\nVous n'aviez pas créé un nouvel enregistrement avant de remplir les champs.\nVoulez-vous tout de même enregistrer un nouvel artiste (avec un nouvel identifiant) avec les données fournies?") == JOptionPane.YES_OPTION) {
					if (validerChamps()) {
						con.Ajouter(textField_2.getText(), chckbxMember.isSelected() ? "true" : "false", "/dev/null");
						btnRechercher.doClick();
					} else {
						JOptionPane.showMessageDialog(null, "Veuillez vous assurer que tous les champs sont valides.");
					}
				}
			}
		});
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
				con.Rechercher(textField.getText());
			}
		});
		btnRemplacer.setBounds(25, 245, 114, 25);
		getContentPane().add(btnRemplacer);
		
		JLabel lblInformations = new JLabel("Informations");
		lblInformations.setFont(new Font("Dialog", Font.BOLD, 16));
		lblInformations.setBounds(35, 293, 116, 25);
		getContentPane().add(lblInformations);
		
		textField_1 = new JTextField();
		textField_1.setEnabled(false);
		textField_1.setColumns(10);
		textField_1.setBounds(100, 330, 144, 19);
		getContentPane().add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(100, 361, 144, 19);
		getContentPane().add(textField_2);
		
		JLabel lblNumro = new JLabel("Numero");
		lblNumro.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNumro.setBounds(35, 330, 60, 15);
		getContentPane().add(lblNumro);
		
		JLabel lblNom = new JLabel("Nom");
		lblNom.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNom.setBounds(35, 363, 60, 15);
		getContentPane().add(lblNom);
		
		JList list = new JList();
		list.setBounds(288, 324, 164, 93);
		getContentPane().add(list);
		Object[] columns = {"No","Nom","Membre"};
		DefaultTableModel model = new DefaultTableModel();
		model.setColumnIdentifiers(columns);
		Object[] row = new Object[3];
		row[0]="1";
		row[1]="nom1";
		row[2]="oui";
		model.addRow(row);
	}
	
	public boolean validerChamps() {
		return textField_2.getText().matches("^[a-zA-Z\\-_ ]{3,63}$");
	}
}
