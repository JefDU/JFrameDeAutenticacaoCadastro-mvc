package controller;

import controller.dao.UsuarioDao;
import controller.dao.impl.UsuarioDaoImpl;
import controller.exception.RegraDeNegocioException;
import controller.exception.UsuarioDaoException;
import model.Usuario;

public class CadastrarUsuarioController {
	
	 UsuarioDao usuarioDao;
	
	public CadastrarUsuarioController() {
		usuarioDao = new UsuarioDaoImpl();
	}
	
	private void validarCadastro(Usuario user, String login) throws RegraDeNegocioException {
		String userLogin = null;
		
		try {
			for (Usuario usuario : usuarioDao.usuarioList()) {
				
				if (login.equals(usuario.getLogin())) {
					userLogin = usuario.getLogin();
				}
				
			}
		} catch (UsuarioDaoException e) {
			throw new RegraDeNegocioException("Falha técnica ao cadastrar: " + e.getMessage());
		}
		
		if (user.getCpf().trim().equals("")) {
			throw new RegraDeNegocioException("CPF não informado");
		}
		
		if (user.getNome().trim().equals("")) {
			throw new RegraDeNegocioException("Nome não informado");
		}
		
		if (user.getLogin().trim().equals("")) {
			throw new RegraDeNegocioException("Usuario não informado");
		}
		
		if (user.getSenha().trim().equals("")) {
			throw new RegraDeNegocioException("Senha não informada");
		}
		
		if (login.equals(userLogin)) {
			throw new RegraDeNegocioException("Usuario ja existe");
		}
		
	}
	
	public void inserirUsuario(Usuario user, String login) throws RegraDeNegocioException {
		
		try {
			validarCadastro(user, login);
			usuarioDao.salvarUsuario(user);
			
		} catch (UsuarioDaoException e) {
			
			throw new RegraDeNegocioException("Falha técnica ao inserir usuario " + e.getMessage());
		}
		
		
	}

}
