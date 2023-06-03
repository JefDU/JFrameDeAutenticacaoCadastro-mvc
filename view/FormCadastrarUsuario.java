package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.CadastrarUsuarioController;
import controller.exception.RegraDeNegocioException;
import model.Usuario;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FormCadastrarUsuario extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtnCpf;
	private JTextField txtfNomeCompleto;
	private JTextField txtfUsuario;
	private JTextField txtnSenha;
	private CadastrarUsuarioController cadastroController;
	private Usuario user;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormCadastrarUsuario frame = new FormCadastrarUsuario();
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
	public FormCadastrarUsuario() {
		cadastroController = new CadastrarUsuarioController();
		user = new Usuario();

		setResizable(false);
		setTitle("Cadastro");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 373, 288);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTituloCadastro = new JLabel("Cadastro de Login");
		lblTituloCadastro.setEnabled(false);
		lblTituloCadastro.setFont(new Font("Arial", Font.BOLD, 16));
		lblTituloCadastro.setBounds(112, 11, 166, 24);
		contentPane.add(lblTituloCadastro);

		JLabel lblTituloCpf = new JLabel("CPF:");
		lblTituloCpf.setBounds(10, 64, 46, 14);
		contentPane.add(lblTituloCpf);

		JLabel lblNomeCompleto = new JLabel("Nome Completo:");
		lblNomeCompleto.setBounds(10, 87, 102, 14);
		contentPane.add(lblNomeCompleto);

		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setBounds(10, 112, 57, 14);
		contentPane.add(lblUsuario);

		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(10, 137, 46, 14);
		contentPane.add(lblSenha);

		txtnCpf = new JTextField();
		txtnCpf.setBounds(39, 61, 148, 20);
		contentPane.add(txtnCpf);
		txtnCpf.setColumns(10);

		txtfNomeCompleto = new JTextField();
		txtfNomeCompleto.setColumns(10);
		txtfNomeCompleto.setBounds(110, 84, 206, 20);
		contentPane.add(txtfNomeCompleto);

		txtfUsuario = new JTextField();
		txtfUsuario.setColumns(10);
		txtfUsuario.setBounds(60, 109, 206, 20);
		contentPane.add(txtfUsuario);

		txtnSenha = new JTextField();
		txtnSenha.setColumns(10);
		txtnSenha.setBounds(52, 137, 191, 20);
		contentPane.add(txtnSenha);

		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					user.setCpf(txtnCpf.getText());
					user.setNome(txtfNomeCompleto.getText());
					user.setLogin(txtfUsuario.getText());
					user.setSenha(txtnSenha.getText());

					cadastroController.inserirUsuario(user, txtfUsuario.getText());
					JOptionPane.showMessageDialog(btnCadastrar, "Usuario Cadastrado Com Sucesso");

					new FormLoginInicial().setVisible(true);
					dispose();

				} catch (RegraDeNegocioException e1) {

					JOptionPane.showMessageDialog(btnCadastrar, e1.getMessage());
				}

			}
		});
		btnCadastrar.setBounds(122, 183, 110, 23);
		contentPane.add(btnCadastrar);
		
		JLabel lblVoltar = new JLabel("<-- Voltar");
		lblVoltar.setToolTipText("Voltar Para o Início");
		lblVoltar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new FormLoginInicial().setVisible(true);
				dispose();
			}
		});
		lblVoltar.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblVoltar.setBounds(10, 217, 67, 32);
		contentPane.add(lblVoltar);
		
		JLabel lblCpfSomenteNumeros = new JLabel("Somente Números");
		lblCpfSomenteNumeros.setToolTipText("CPF Somente Números");
		lblCpfSomenteNumeros.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblCpfSomenteNumeros.setBounds(186, 65, 119, 14);
		contentPane.add(lblCpfSomenteNumeros);
	}
}
