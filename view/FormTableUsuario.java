package view;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.ListaController;
import controller.exception.RegraDeNegocioException;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

public class FormTableUsuario extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final JTable tableUsuarios = new JTable();
	private ListaController listaControl;
	private JTextField txtnCpf;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormTableUsuario frame = new FormTableUsuario();
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
	public FormTableUsuario() {
		listaControl = new ListaController();
		setResizable(false);
		setTitle("Lista de Usuarios");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 603, 535);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		tableUsuarios.setFillsViewportHeight(true);
		tableUsuarios.setColumnSelectionAllowed(true);
		tableUsuarios.setCellSelectionEnabled(true);
		tableUsuarios.setBounds(37, 98, 540, 387);
		contentPane.add(tableUsuarios);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(10, 98, 17, 387);
		contentPane.add(scrollBar);
		
		try {
			tableUsuarios.setModel(listaControl.tableUsuarios());
			
			JLabel lblCPF = new JLabel("CPF");
			lblCPF.setEnabled(false);
			lblCPF.setBounds(70, 83, 46, 14);
			contentPane.add(lblCPF);
			
			JLabel lblNome = new JLabel("Nome");
			lblNome.setEnabled(false);
			lblNome.setBounds(249, 83, 46, 14);
			contentPane.add(lblNome);
			
			JLabel lblUsuario = new JLabel("Usuario");
			lblUsuario.setEnabled(false);
			lblUsuario.setBounds(440, 83, 46, 14);
			contentPane.add(lblUsuario);
			
			JLabel lblCPF2 = new JLabel("CPF:");
			lblCPF2.setBounds(37, 43, 34, 14);
			contentPane.add(lblCPF2);
			
			txtnCpf = new JTextField();
			txtnCpf.setBounds(74, 40, 151, 20);
			contentPane.add(txtnCpf);
			txtnCpf.setColumns(10);
			
			JButton btnDeletar = new JButton("Deletar");
			btnDeletar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						if(listaControl.deletarUsuario(txtnCpf.getText())) {
							
							JOptionPane.showMessageDialog(btnDeletar, "Usuario Deletado Com Sucesso");
							txtnCpf.setText("");
							
							tableUsuarios.setModel(listaControl.tableUsuarios());
							
						} else {
							
							JOptionPane.showMessageDialog(btnDeletar, "CPF Invalido");
							txtnCpf.setText("");
						}
						
					} catch (RegraDeNegocioException e1) {
						JOptionPane.showMessageDialog(btnDeletar, e1.getMessage());
						
					}
				}
			});
			btnDeletar.setBounds(235, 39, 89, 23);
			contentPane.add(btnDeletar);
		} catch (RegraDeNegocioException e) {
			JOptionPane.showMessageDialog(scrollBar, e.getMessage());
		}
		
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
		lblVoltar.setBounds(10, 0, 67, 32);
		contentPane.add(lblVoltar);
	}
}
