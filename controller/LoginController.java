package controller;

import controller.dao.UsuarioDao;
import controller.dao.impl.UsuarioDaoImpl;
import controller.exception.RegraDeNegocioException;
import controller.exception.UsuarioDaoException;
import model.Usuario;

public class LoginController {

	 private UsuarioDao usuarioDao;

	public LoginController() {
		usuarioDao = new UsuarioDaoImpl();
	}

	private void validarAcessoUsuario(String login, String senha) throws RegraDeNegocioException {
		if (login.trim().equals("")) {
			throw new RegraDeNegocioException("Usuario não informado");
		}

		if (senha.trim().equals("")) {
			throw new RegraDeNegocioException("Senha não informada");
		}

	}
	
	private void buscarCadastro(String login, String senha) throws RegraDeNegocioException {
		String usuarioLogin = null;
		String usuarioSenha = null;
		
		try {
			for (Usuario usuario : usuarioDao.usuarioList()) {
				
				if (login.equals(usuario.getLogin())) {
					usuarioLogin = usuario.getLogin();
				}
				
				if (senha.equals(usuario.getSenha())) {
					usuarioSenha = usuario.getSenha();
				}
			}
			
			if (!login.equals(usuarioLogin) && !senha.equals(usuarioSenha)) {
				throw new RegraDeNegocioException("Usuario e senha invalidos");
			}
			
			else if (!login.equals(usuarioLogin)) {
				throw new RegraDeNegocioException("Usuario invalido");
			}
			
			else if (!senha.equals(usuarioSenha)) {
				
				throw new RegraDeNegocioException("Senha invalida");
			}
			
		} catch (UsuarioDaoException e) {
			throw new RegraDeNegocioException("Erro técnico ao buscar cadastro: " + e.getMessage());
		}
	}
	
	public Boolean validarLogin(String login, String senha) throws RegraDeNegocioException {

		try {
		     validarAcessoUsuario(login, senha);	
		     buscarCadastro(login, senha);
			return usuarioDao.validarAcesso(login, senha);

		} catch (UsuarioDaoException e) {
			throw new RegraDeNegocioException("Erro técnico ao acessar login: " + e.getMessage());
		}

	}
}
