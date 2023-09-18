package controller;

import controller.dao.UsuarioDao;
import controller.dao.impl.UsuarioDaoImpl;
import controller.exception.UsuarioException;
import controller.exception.UsuarioDaoException;
import model.Usuario;

public class LoginController {

	 private UsuarioDao usuarioDao;

	public LoginController() {
		usuarioDao = new UsuarioDaoImpl();
	}

	private void validarAcessoUsuario(String login, String senha) throws UsuarioException {
		if (login.trim().equals("")) {
			throw new UsuarioException("Usuario não informado");
		}

		if (senha.trim().equals("")) {
			throw new UsuarioException("Senha não informada");
		}

	}
	
	private void buscarCadastro(String login, String senha) throws UsuarioException {
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
				throw new UsuarioException("Usuario e senha invalidos");
			}
			
			else if (!login.equals(usuarioLogin)) {
				throw new UsuarioException("Usuario invalido");
			}
			
			else if (!senha.equals(usuarioSenha)) {
				
				throw new UsuarioException("Senha invalida");
			}
			
		} catch (UsuarioDaoException e) {
			throw new UsuarioException("Erro técnico ao buscar cadastro: " + e.getMessage());
		}
	}
	
	public Boolean validarLogin(String login, String senha) throws UsuarioException {

		try {
		     validarAcessoUsuario(login, senha);	
		     buscarCadastro(login, senha);
			return usuarioDao.validarAcesso(login, senha);

		} catch (UsuarioDaoException e) {
			throw new UsuarioException("Erro técnico ao acessar login: " + e.getMessage());
		}

	}
}
