package controller;

import controller.dao.UsuarioDao;
import controller.dao.impl.UsuarioDaoImpl;
import controller.exception.RegraDeNegocioException;
import controller.exception.UsuarioDaoException;

public class AlterarSenhaController {

	UsuarioDao usuarioDao;

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
	
	public Boolean alterarSenha(String senhaNova, String cpf, String nome, String login) throws RegraDeNegocioException {
		
		try {
			validarAlteracao(senhaNova, cpf, nome, login);
			return usuarioDao.editarSenha(senhaNova, cpf, nome, login);
			
		} catch (UsuarioDaoException e) {
			throw new RegraDeNegocioException("Erro técnico ao alterar usuario: " + e.getMessage());
		}
	}
}
