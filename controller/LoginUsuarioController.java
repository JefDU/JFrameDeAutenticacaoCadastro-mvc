package controller;

import controller.dao.UsuarioDao;
import controller.dao.impl.UsuarioDaoImpl;
import controller.exception.RegraDeNegocioException;
import controller.exception.UsuarioDaoException;
import model.Usuario;

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
	
	private void buscarUsuario(String login, String senha) throws RegraDeNegocioException {
		
			try {
				for (Usuario user : usuarioDao.usuarioList()) {
					
					if (!login.equals(user.getLogin()) && !senha.equals(user.getSenha())) {
						throw new RegraDeNegocioException("Usuario e Senha Invalidos");
					}
					
					if (!login.equals(user.getLogin())) {
						throw new RegraDeNegocioException("Usuario Invalido");
					}
					
					if (!senha.equals(user.getSenha())) {
						throw new RegraDeNegocioException("Senha Invalida");
					}
				}
			} catch (UsuarioDaoException e) {
				throw new RegraDeNegocioException("Erro técnico ao buscar usuario: " + e.getMessage());
			} 
		
	}
		
	public Boolean validarLogin(String login, String senha) throws RegraDeNegocioException {
		
		try {
			validarAcessoUsuario(login, senha);
			buscarUsuario(login, senha);
		return usuarioDao.validarAcesso(login, senha);
			
		} catch (UsuarioDaoException e) {
			throw new RegraDeNegocioException("Erro técnico ao acessar login: " + e.getMessage());
		}
		
	}
}
