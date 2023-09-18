package controller;

import controller.dao.UsuarioDao;
import controller.dao.impl.UsuarioDaoImpl;
import controller.exception.UsuarioException;
import controller.exception.UsuarioDaoException;
import model.Usuario;

public class AlterarUsuarioController {

	 private UsuarioDao usuarioDao;

	public AlterarUsuarioController() {
		usuarioDao = new UsuarioDaoImpl();
	}

	private void validarAlteracao(String loginNovo, String cpf, String nome) throws UsuarioException {
		if (cpf.trim().equals("")) {
			throw new UsuarioException("CPF não informado");
		}
		
		if (nome.trim().equals("")) {
			throw new UsuarioException("Nome não informado");
		}
		
		if (loginNovo.trim().equals("")) {
			throw new UsuarioException("Novo usuario não informado");
		}
		
	}
	
	private void buscarCadastro(String cpf, String nome) throws UsuarioException {
		String usuarioCpf = null;
		String usuarioNome = null;
		
		try {
			for (Usuario usuario : usuarioDao.usuarioList()) {
				if (cpf.equals(usuario.getCpf())) {
					usuarioCpf = usuario.getCpf();
				}
				
				if (nome.equals(usuario.getNome())) {
					usuarioNome = usuario.getNome();
				}
			}
			
			if (!cpf.equals(usuarioCpf) && !nome.equals(usuarioNome)) {				
				throw new UsuarioException("CPF e nome invalidos");

			}
			
			else if (!cpf.equals(usuarioCpf)) { 
				throw new UsuarioException("CPF invalido");
			}
			
			else if (!nome.equals(usuarioNome)) {
				throw new UsuarioException("Nome invalido");
			}
			
			
		} catch (UsuarioDaoException e) {
			throw new UsuarioException(e.getMessage());
		}
	}

	public Boolean alterarUsuario(String loginNovo, String cpf, String nome) throws UsuarioException {
		
		try {
			validarAlteracao(loginNovo, cpf, nome);
			buscarCadastro(cpf, nome);
			return usuarioDao.editarUsuario(loginNovo, cpf, nome);
			
		} catch (UsuarioDaoException e) {
			throw new UsuarioException("Erro técnico ao alterar usuario: " + e.getMessage());
		}
	}
}
