package controller;

import controller.dao.UsuarioDao;
import controller.dao.impl.UsuarioDaoImpl;
import controller.exception.UsuarioException;
import controller.exception.UsuarioDaoException;
import model.Usuario;

public class CadastrarUsuarioController {
	
	private UsuarioDao usuarioDao;
	
	public CadastrarUsuarioController() {
		usuarioDao = new UsuarioDaoImpl();
	}
	
	private void validarCadastro(Usuario user, String login) throws UsuarioException {
		String userLogin = null;
		
		try {
			for (Usuario usuario : usuarioDao.usuarioList()) {
				
				if (login.equals(usuario.getLogin())) {
					userLogin = usuario.getLogin();
				}
				
			}
		} catch (UsuarioDaoException e) {
			throw new UsuarioException(e.getMessage());
		}
		
		if (user.getCpf().trim().equals("")) {
			throw new UsuarioException("CPF não informado");
		}
		
		if (user.getNome().trim().equals("")) {
			throw new UsuarioException("Nome não informado");
		}
		
		if (user.getLogin().trim().equals("")) {
			throw new UsuarioException("Usuario não informado");
		}
		
		if (user.getSenha().trim().equals("")) {
			throw new UsuarioException("Senha não informada");
		}
		
		if (login.equals(userLogin)) {
			throw new UsuarioException("Usuario ja existe");
		}
		
	}
	
	public void inserirUsuario(Usuario user, String login) throws UsuarioException {
		
		try {
			validarCadastro(user, login);
			usuarioDao.salvarUsuario(user);
			
		} catch (UsuarioDaoException e) {
			
			throw new UsuarioException("Falha técnica ao inserir usuario " + e.getMessage());
		}
		
		
	}

}
