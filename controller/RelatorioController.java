package controller;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

import controller.dao.UsuarioDao;
import controller.dao.impl.UsuarioDaoImpl;
import controller.exception.RegraDeNegocioException;
import controller.exception.UsuarioDaoException;
import model.Usuario;

public class RelatorioController {

	private UsuarioDao usuarioDao;
	private String diretorio;
	
	public RelatorioController() {
		usuarioDao = new UsuarioDaoImpl();
		this.diretorio = "C:/Projetos Developer/ProjetosPortifolio/projetoJFrameDeLoginCadastroV1_MVC/Relatorio-de-usuarios.txt";
	}

	public void salvarRelatorio() throws RegraDeNegocioException {
		try {
			PrintWriter pw = new PrintWriter(diretorio);
			for (Usuario user : usuarioDao.usuarioList()) {

				pw.println("CPF:" + user.getCpf() + "\n" 
				+ "Nome:" + user.getNome() + "\n" 
						+ "Login:" + user.getLogin() + "\n"
						+ "Senha: " + user.getSenha() + "\n"
						+ "-------------------------------- \n");
			}
			pw.println("---Quantide de Cadastros---");
			pw.println("Quantidade De Usuarios = " + usuarioDao.usuarioList().size() + " Usuarios cadastrados");

			pw.flush();
			pw.close();

		} catch (FileNotFoundException | UsuarioDaoException e) {
			throw new RegraDeNegocioException("Erro técnico ao salvar o relatório: " + e.getMessage());
		}
	}
}
