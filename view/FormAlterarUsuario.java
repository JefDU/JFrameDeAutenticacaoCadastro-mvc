package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.AlterarUsuarioController;
import controller.exception.RegraDeNegocioException;

public class FormAlterarUsuario extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtnCpf;
	private JTextField txtfNome;
	private JTextField txtfNovoUsuario;
	private AlterarUsuarioController alterarUsuarioControl;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormAlterarUsuario frame = new FormAlterarUsuario();
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
	public FormAlterarUsuario() {
		alterarUsuarioControl = new AlterarUsuarioController();

		setTitle("Alterar Usuario");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblAlterarUsuario = new JLabel("Alterar Usuario");
		lblAlterarUsuario.setFont(new Font("Arial", Font.BOLD, 14));
		lblAlterarUsuario.setEnabled(false);
		lblAlterarUsuario.setBounds(151, 0, 172, 26);
		contentPane.add(lblAlterarUsuario);

		JLabel lblInformeOsDados_1 = new JLabel("Informe os dados para alteração do usuario");
		lblInformeOsDados_1.setBounds(91, 50, 259, 14);
		contentPane.add(lblInformeOsDados_1);

		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setBounds(58, 92, 46, 14);
		contentPane.add(lblCpf);

		JLabel lblNomeCompleto = new JLabel("Nome Completo:");
		lblNomeCompleto.setBounds(58, 117, 100, 14);
		contentPane.add(lblNomeCompleto);

		JLabel lblNovoUsuario = new JLabel("Novo Usuario:");
		lblNovoUsuario.setBounds(58, 158, 82, 14);
		contentPane.add(lblNovoUsuario);

		txtnCpf = new JTextField();
		txtnCpf.setColumns(10);
		txtnCpf.setBounds(91, 89, 143, 20);
		contentPane.add(txtnCpf);

		txtfNome = new JTextField();
		txtfNome.setColumns(10);
		txtfNome.setBounds(157, 114, 181, 20);
		contentPane.add(txtfNome);

		txtfNovoUsuario = new JTextField();
		txtfNovoUsuario.setColumns(10);
		txtfNovoUsuario.setBounds(144, 155, 161, 20);
		contentPane.add(txtfNovoUsuario);

		JButton btnAlterarUsuario = new JButton("Alterar Usuario");
		btnAlterarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (alterarUsuarioControl.alterarUsuario(txtfNovoUsuario.getText(), txtnCpf.getText(),
							txtfNome.getText())) {

						JOptionPane.showMessageDialog(btnAlterarUsuario, "Usuario Alterado Com Sucesso");
						new FormLoginInicial().setVisible(true);
						dispose();
					} else {

						JOptionPane.showMessageDialog(btnAlterarUsuario, "Tente Novamente");
						txtnCpf.setText("");
						txtfNome.setText("");
						txtfNovoUsuario.setText("");
						txtnCpf.requestFocus();

					}

				} catch (RegraDeNegocioException e1) {
					JOptionPane.showMessageDialog(btnAlterarUsuario, e1.getMessage());
				}
			}
		});
		btnAlterarUsuario.setBounds(151, 202, 121, 23);
		contentPane.add(btnAlterarUsuario);

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
		lblVoltar.setBounds(10, 224, 67, 26);
		contentPane.add(lblVoltar);
	}
}
