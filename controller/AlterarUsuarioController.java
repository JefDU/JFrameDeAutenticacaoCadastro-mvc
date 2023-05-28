package controller;

import controller.dao.UsuarioDao;
import controller.dao.impl.UsuarioDaoImpl;
import controller.exception.RegraDeNegocioException;
import controller.exception.UsuarioDaoException;
import model.Usuario;

public class AlterarUsuarioController {

	UsuarioDao usuarioDao;

	public AlterarUsuarioController() {
		usuarioDao = new UsuarioDaoImpl();
	}

	private void validarAlteracao(String loginNovo, String cpf, String nome) throws RegraDeNegocioException {
		if (cpf.trim().equals("")) {
			throw new RegraDeNegocioException("CPF não informado");
		}
		
		if (nome.trim().equals("")) {
			throw new RegraDeNegocioException("Nome não informado");
		}
		
		if (loginNovo.trim().equals("")) {
			throw new RegraDeNegocioException("Novo usuario não informado");
		}
		
	}
	
	private void buscarUsuario(String cpf, String nome) throws RegraDeNegocioException {
		try {
			for (Usuario user: usuarioDao.usuarioList()) {
				if (!cpf.equals(user.getCpf())) {
					throw new RegraDeNegocioException("CPF Invalido");
				}
				
				if (!nome.equals(user.getNome())) {
					throw new RegraDeNegocioException("Nome Invalido");
				}
				
			}
		} catch (UsuarioDaoException e) {
			throw new RegraDeNegocioException("Erro técnico ao buscar usuario: " + e.getMessage());
		} 
	}
	
	public Boolean alterarUsuario(String loginNovo, String cpf, String nome) throws RegraDeNegocioException {
		
		try {
			validarAlteracao(loginNovo, cpf, nome);
			buscarUsuario(cpf, nome);
			return usuarioDao.editarUsuario(loginNovo, cpf, nome);
			
		} catch (UsuarioDaoException e) {
			throw new RegraDeNegocioException("Erro técnico ao alterar usuario: " + e.getMessage());
		}
	}
}
