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
	
	private void validarCadastro(Usuario user) throws RegraDeNegocioException {
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
		
	}
	
	public void inserirUsuario(Usuario user) throws RegraDeNegocioException {
		validarCadastro(user);
		try {
			usuarioDao.salvarUsuario(user);
			
		} catch (UsuarioDaoException e) {
			
			throw new RegraDeNegocioException("Falha técnica ao inserir usuario " + e.getMessage());
		}
		
		
	}

}
