package livrable1;

import javax.swing.JFrame;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.event.ListSelectionEvent;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class GestionArtistes extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTable table;
	private JTextField textField_1;
	private JTextField textField_2;
	private JLabel lblNewLabel;
	private String currentpath="MusicNote.png";
	/**
	 * Create the frame.
	 */
	@SuppressWarnings("serial")
	public GestionArtistes() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(FenetreTraitement.class.getResource("/livrable1/MusicNote.png")));
		setTitle("Gestion des artistes \u266A");
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

		JLabel label = new JLabel("");
		label.setBounds(530, 316, 105, 96);
		getContentPane().add(label);
		
		JList list = new JList();
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				label.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource(list.getSelectedValue().toString() + ".png")).getImage().getScaledInstance(90, 90, Image.SCALE_DEFAULT)));
			}
		});
		list.setBounds(288, 324, 208, 93);
		getContentPane().add(list);
		
		JButton btnRechercher = new JButton("Rechercher");
		btnRechercher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<Artiste> artistes = con.Rechercher(textField.getText());
				
				Object[][] liste = new Object[artistes.size()][3];
				
				for (int i = 0; i < artistes.size(); i++) {
					liste[i][0] = artistes.get(i).getId();
					liste[i][1] = artistes.get(i);
					liste[i][2] = artistes.get(i).getMembre().equals("1") ? "Oui" : "Non";
				}
				
				table.setModel(new DefaultTableModel(
						liste,
						new String[] {
							"No", "Nom", "Membre"
						}
					) {
						Class[] columnTypes = new Class[] {
							Object.class, Object.class, Object.class
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
				
				list.setListData(new String[0]);
	            label.setIcon(null);
	            lblNewLabel.setIcon(null);
			}
		});
		btnNouveau.setBounds(521, 134, 114, 25);
		getContentPane().add(btnNouveau);
		
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (textField_1.getText().equals("") ? true : JOptionPane.showConfirmDialog(null, "Attention!\n\nVous n'aviez pas créé un nouvel enregistrement avant de remplir les champs.\nVoulez-vous tout de même enregistrer un nouvel artiste (avec un nouvel identifiant) avec les données fournies?") == JOptionPane.YES_OPTION) {
					if (validerChamps()) {
						con.Ajouter(textField_2.getText(), chckbxMember.isSelected() ? "true" : "false", currentpath);
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
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textField_1.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Veuillez s�lectionner un artiste.", "Erreur", JOptionPane.ERROR_MESSAGE);
				} else {
					if (validerChamps()) {
						con.Ajouter(textField_2.getText(), chckbxMember.isSelected() ? "true" : "false", currentpath);
						btnRechercher.doClick();
					} else {
						JOptionPane.showMessageDialog(null, "Veuillez vous assurer que tous les champs sont valides.");
					}
				}
			}
		});
		btnModifier.setBounds(521, 208, 114, 25);
		getContentPane().add(btnModifier);
		
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(textField_1.getText().equals("")){
					
					JOptionPane.showMessageDialog(null,"Aucun enregistrement s�lectionn�, veuillez s�lectionner un enregistrement");
					
				}else{
					
					String id=textField_1.getText();
					
					if(JOptionPane.showConfirmDialog(null, "Voulez vous r�ellement effacer l'enrigstrement n� "+id+" ?", "Confirmation", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
						
						ConnectionBDD con=new ConnectionBDD();
						con.Supprimer(id);
						btnRechercher.doClick();
						
					}
					
				}
				
			}
		});
		btnSupprimer.setBounds(521, 245, 114, 25);
		getContentPane().add(btnSupprimer);
		
		JButton btnRemplacer = new JButton("Remplacer");
		btnRemplacer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				JFileChooser chooser = new JFileChooser();
		        chooser.setFileFilter(new FileNameExtensionFilter("Images", "jpg", "gif", "png"));
		        
		        if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
		        	
		            String newPhoto = chooser.getSelectedFile().getAbsolutePath();
		            ImageIcon image;
					try {
						image = new ImageIcon(new ImageIcon(ImageIO.read(new File(newPhoto))).getImage().getScaledInstance(90, 90, Image.SCALE_DEFAULT));
			            currentpath=newPhoto;
			            lblNewLabel.setIcon(image);
			            
			            System.out.println("les go");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		            
		        }
		        
        		
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
		
		ImageIcon image=new ImageIcon(new ImageIcon(this.getClass().getResource("MusicNote.png")).getImage().getScaledInstance(90, 90, Image.SCALE_DEFAULT));
		lblNewLabel = new JLabel(image);
		lblNewLabel.setBounds(35, 143, 90, 90);
		getContentPane().add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(161, 134, 350, 136);
		getContentPane().add(scrollPane);
		
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
		table.setDefaultEditor(Object.class, null);
		table.getTableHeader().setReorderingAllowed(false);
		
		table.setModel(new DefaultTableModel(
			new Object[0][3],
			new String[] {
				"No", "Nom", "Membre"
			}
		
		)
		
		{
			Class[] columnTypes = new Class[] {
				Object.class, Object.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("?");
		menuBar.add(menu);
		
		JMenuItem mntmPropos = new JMenuItem("\u00C0 propos");
		mntmPropos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				FenetreAPropos fen=new FenetreAPropos();
				fen.setVisible(true);
			
			}
		});
		
		menu.add(mntmPropos);
		
		JMenuItem mntmAideEnLigne = new JMenuItem("Aide en ligne");
		mntmAideEnLigne.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				
			}
		});
		menu.add(mntmAideEnLigne);
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	        	try {
	        	if (table.getSelectedRow() != -1) {
	        		//test image
	        		Artiste artiste=(Artiste)(table.getValueAt(table.getSelectedRow(),1));
	        		ImageIcon image=new ImageIcon(new ImageIcon(artiste.getPhoto()).getImage().getScaledInstance(90, 90, Image.SCALE_DEFAULT));
	        		currentpath=artiste.getPhoto();
	        		lblNewLabel.setIcon(image);
		            textField_1.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
		            textField_2.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
		            chckbxMember.setSelected(table.getValueAt(table.getSelectedRow(), 2).toString().equalsIgnoreCase("oui"));
		            
		            list.setListData(con.AlbumsParArtiste(artiste).toArray());
		            label.setIcon(null);
		            
	        	} else {
	        		textField_1.setText("");
					textField_2.setText("");
					chckbxMember.setSelected(false);
	        	}
	        	}catch(Exception e) {
	        		
	        		
	        		
	        	}
	        }
	    });
		DefaultTableModel model = new DefaultTableModel();
		btnRechercher.doClick();
	}
	
	public boolean validerChamps() {
		return textField_2.getText().matches("^[a-zA-Z\\-_ ]{3,63}$");
	}
}
