package controller;

import controller.dao.UsuarioDao;
import controller.dao.impl.UsuarioDaoImpl;
import controller.exception.RegraDeNegocioException;
import controller.exception.UsuarioDaoException;

public class LoginUsuarioController {

	UsuarioDao usuarioDao;

	public LoginUsuarioController() {
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
	
	public Boolean validarLogin(String login, String senha) throws RegraDeNegocioException {

		try {
		     validarAcessoUsuario(login, senha);	
			return usuarioDao.validarAcesso(login, senha);

		} catch (UsuarioDaoException e) {
			throw new RegraDeNegocioException("Erro técnico ao acessar login: " + e.getMessage());
		}

	}
}
