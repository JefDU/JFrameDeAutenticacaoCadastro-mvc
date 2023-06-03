package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.AlterarSenhaController;
import controller.exception.RegraDeNegocioException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

public class FrameAlterarSenha extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtnCpf;
	private JTextField txtnNomeCompleto;
	private JTextField txtnNovaSenha;
	private JTextField txtfUsuario;
	private AlterarSenhaController alterarSenhaControl;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameAlterarSenha frame = new FrameAlterarSenha();
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
	public FrameAlterarSenha() {
		alterarSenhaControl = new AlterarSenhaController();
		setTitle("Alterar Senha");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 342, 324);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblInformeOsDados = new JLabel("Informe os dados para alteração da senha");
		lblInformeOsDados.setBounds(43, 61, 259, 14);
		contentPane.add(lblInformeOsDados);

		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setBounds(10, 153, 100, 14);
		contentPane.add(lblUsuario);

		JLabel lblTituloAlterarSenha = new JLabel("Alterar Senha");
		lblTituloAlterarSenha.setEnabled(false);
		lblTituloAlterarSenha.setFont(new Font("Arial", Font.BOLD, 14));
		lblTituloAlterarSenha.setBounds(103, 11, 172, 26);
		contentPane.add(lblTituloAlterarSenha);

		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setBounds(10, 103, 46, 14);
		contentPane.add(lblCpf);

		JLabel lblNomeCompleto = new JLabel("Nome Completo:");
		lblNomeCompleto.setBounds(10, 128, 100, 14);
		contentPane.add(lblNomeCompleto);

		JLabel lblNovaSenha = new JLabel("Nova Senha:");
		lblNovaSenha.setBounds(10, 199, 73, 14);
		contentPane.add(lblNovaSenha);

		txtnCpf = new JTextField();
		txtnCpf.setBounds(43, 100, 143, 20);
		contentPane.add(txtnCpf);
		txtnCpf.setColumns(10);

		txtnNomeCompleto = new JTextField();
		txtnNomeCompleto.setColumns(10);
		txtnNomeCompleto.setBounds(109, 125, 181, 20);
		contentPane.add(txtnNomeCompleto);

		txtfUsuario = new JTextField();
		txtfUsuario.setColumns(10);
		txtfUsuario.setBounds(63, 153, 181, 20);
		contentPane.add(txtfUsuario);

		txtnNovaSenha = new JTextField();
		txtnNovaSenha.setColumns(10);
		txtnNovaSenha.setBounds(91, 196, 161, 20);
		contentPane.add(txtnNovaSenha);

		JButton btnAlterarSenha = new JButton("Alterar Senha");
		btnAlterarSenha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (alterarSenhaControl.alterarSenha(txtnNovaSenha.getText(), txtnCpf.getText(),
							txtnNomeCompleto.getText(), txtfUsuario.getText())) {

						JOptionPane.showMessageDialog(btnAlterarSenha, "Senha Alterada Com Sucesso");
						new FrameLoginInicial().setVisible(true);
						dispose();
						
					} else {

						JOptionPane.showMessageDialog(btnAlterarSenha, "Tente novamente");
						txtnCpf.setText("");
                        txtnNomeCompleto.setText("");
                        txtfUsuario.setText("");
                        txtnNovaSenha.setText("");
						txtnCpf.requestFocus();

					}

				} catch (RegraDeNegocioException e1) {
					JOptionPane.showMessageDialog(btnAlterarSenha, e1.getMessage());
				}
			}
		});
		btnAlterarSenha.setBounds(101, 237, 121, 23);
		contentPane.add(btnAlterarSenha);

		JLabel lblVoltar = new JLabel("<-- Voltar");
		lblVoltar.setToolTipText("Voltar Para o Início");
		lblVoltar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new FrameLoginInicial().setVisible(true);
				dispose();
			}
		});
		lblVoltar.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblVoltar.setBounds(10, 254, 67, 20);
		contentPane.add(lblVoltar);
	}

}
