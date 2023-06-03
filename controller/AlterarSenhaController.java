package controller;

import controller.dao.UsuarioDao;
import controller.dao.impl.UsuarioDaoImpl;
import controller.exception.RegraDeNegocioException;
import controller.exception.UsuarioDaoException;
import model.Usuario;

public class AlterarSenhaController {

	 private UsuarioDao usuarioDao;

	public AlterarSenhaController() {
		usuarioDao = new UsuarioDaoImpl();
	}

	private void validarAlteracao(String senhaNova, String cpf, String nome, String login) throws RegraDeNegocioException, UsuarioDaoException {
	
		if (cpf.trim().equals("")) {
			throw new RegraDeNegocioException("CPF não informado");
		}
		
		if (nome.trim().equals("")) {
			throw new RegraDeNegocioException("Nome não informado");
		}
		
		if (login.trim().equals("")) {
			throw new RegraDeNegocioException("Usuario não informado");
		}
		
		if (senhaNova.trim().equals("")) {
			throw new RegraDeNegocioException("Nova senha não informado");
		}
		
	}
	
	private void buscarCadastro(String cpf, String nome, String login) throws RegraDeNegocioException {
		String userCpf = null;
		String userNome = null;
		String userLogin = null;
		
		try {
			for (Usuario usuario : usuarioDao.usuarioList()) {
				if (cpf.equals(usuario.getCpf())) {
					userCpf = usuario.getCpf();
				}
				
				if (nome.equals(usuario.getNome())) {
					userNome = usuario.getNome();
				}
				
				if (login.equals(usuario.getLogin())) {
					userLogin = usuario.getLogin();
				}
			}
			
			if (!cpf.equals(userCpf)) { 
				throw new RegraDeNegocioException("CPF invalido");
			}
			
			else if (!nome.equals(userNome)) {
				throw new RegraDeNegocioException("Nome invalido");
			}
			
			else if (!login.equals(userLogin)) {
				throw new RegraDeNegocioException("Login invalido");
			}
			
		} catch (UsuarioDaoException e) {
			throw new RegraDeNegocioException(e.getMessage());
		}
	}
	
	public Boolean alterarSenha(String senhaNova, String cpf, String nome, String login) throws RegraDeNegocioException {
		
		try {
			validarAlteracao(senhaNova, cpf, nome, login);
			buscarCadastro(cpf, nome, login);
			return usuarioDao.editarSenha(senhaNova, cpf, nome, login);
			
		} catch (UsuarioDaoException e) {
			throw new RegraDeNegocioException("Erro técnico ao alterar usuario: " + e.getMessage());
		}
	}
}
