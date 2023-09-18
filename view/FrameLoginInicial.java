package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.LoginController;
import controller.exception.UsuarioException;

public class FrameLoginInicial extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtfUsuario;
	private JPasswordField txtPassword;
	private LoginController loginControl;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameLoginInicial frame = new FrameLoginInicial();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public FrameLoginInicial() {

		loginControl = new LoginController();

		setResizable(false);
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 328, 268);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitulo = new JLabel("Login");
		lblTitulo.setEnabled(false);
		lblTitulo.setFont(new Font("Palatino Linotype", Font.BOLD, 16));
		lblTitulo.setBounds(138, 11, 84, 25);
		contentPane.add(lblTitulo);

		JLabel lblTituloLogin = new JLabel("Usuario:");
		lblTituloLogin.setFont(new Font("Arial", Font.BOLD, 12));
		lblTituloLogin.setBounds(20, 36, 52, 14);
		contentPane.add(lblTituloLogin);

		JLabel lblTituloSenha = new JLabel("Senha:");
		lblTituloSenha.setFont(new Font("Arial", Font.BOLD, 12));
		lblTituloSenha.setBounds(20, 91, 46, 14);
		contentPane.add(lblTituloSenha);

		txtfUsuario = new JTextField();
		txtfUsuario.setBounds(70, 33, 209, 20);
		contentPane.add(txtfUsuario);
		txtfUsuario.setColumns(10);

		txtPassword = new JPasswordField();
		txtPassword.setBounds(65, 85, 214, 20);
		contentPane.add(txtPassword);

		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (loginControl.validarLogin(txtfUsuario.getText(), String.valueOf(txtPassword.getPassword()))) {
						new FrameTabela().setVisible(true);
						dispose();
					} 
						
				} catch (UsuarioException e1) {
					JOptionPane.showMessageDialog(btnEntrar, e1.getMessage());
					
				}
				
				txtPassword.setText("");
				txtfUsuario.requestFocus();

			}
		});
		btnEntrar.setBounds(20, 156, 89, 23);
		contentPane.add(btnEntrar);

		JButton btnCadastrarLogin = new JButton("Cadastrar Login");
		btnCadastrarLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new FrameCadastrarUsuario().setVisible(true);
				dispose();

			}
		});
		btnCadastrarLogin.setBounds(138, 156, 141, 23);
		contentPane.add(btnCadastrarLogin);

		JLabel lblEsqueceuSenha = new JLabel("Esqueceu a senha?");
		lblEsqueceuSenha.setToolTipText("Clique Para Recuperar a Senha");
		lblEsqueceuSenha.addMouseMotionListener(new MouseMotionAdapter() {

		});
		lblEsqueceuSenha.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new FrameAlterarSenha().setVisible(true);
				dispose();

			}

		});
		lblEsqueceuSenha.setBounds(30, 116, 135, 14);
		contentPane.add(lblEsqueceuSenha);

		JLabel lblEsqueceuUsuario = new JLabel("Esqueceu o usuario?");
		lblEsqueceuUsuario.setToolTipText("Clique Para Recuperar o Usuario");
		lblEsqueceuUsuario.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new FrameAlterarUsuario().setVisible(true);
				dispose();
			}
		});
		lblEsqueceuUsuario.setBounds(30, 61, 135, 14);
		contentPane.add(lblEsqueceuUsuario);
	}
}
