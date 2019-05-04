package livrable1;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField txtUser;
	private JPasswordField txtPwd;
	private JLabel lblMotDePasse;
	private Login login;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setTitle("Connexion");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 245, 136);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtUser = new JTextField();
		txtUser.setBounds(104, 8, 124, 20);
		contentPane.add(txtUser);
		txtUser.setColumns(10);
		
		txtPwd = new JPasswordField();
		txtPwd.setBounds(106, 39, 122, 20);
		contentPane.add(txtPwd);
		
		JLabel lblNomDutilisateur = new JLabel("Nom d'utilisateur :");
		lblNomDutilisateur.setBounds(10, 11, 86, 14);
		contentPane.add(lblNomDutilisateur);
		
		lblMotDePasse = new JLabel("Mot de passe :");
		lblMotDePasse.setBounds(10, 42, 86, 14);
		contentPane.add(lblMotDePasse);
		login=this;
		
		JButton btnNewButton = new JButton("Valider");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtUser.getText().equals("admin") && String.copyValueOf(txtPwd.getPassword()).equals("admin")) {
					
					FenetreTraitement fen=new FenetreTraitement();
					fen.setVisible(true);
					login.setVisible(false);
					
				} else {
					JOptionPane.showMessageDialog(null, "Le nom d'usager ou le mot de passe est incorrect.", "Erreur", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnNewButton.setBounds(139, 70, 89, 23);
		contentPane.add(btnNewButton);
	}
}
