package controller;

import controller.dao.UsuarioDao;
import controller.dao.impl.UsuarioDaoImpl;
import controller.exception.UsuarioException;
import controller.exception.UsuarioDaoException;
import model.Usuario;

public class AlterarSenhaController {

	 private UsuarioDao usuarioDao;

	public AlterarSenhaController() {
		usuarioDao = new UsuarioDaoImpl();
	}

	private void validarAlteracao(String senhaNova, String cpf, String nome, String login) throws UsuarioException, UsuarioDaoException {
	
		if (cpf.trim().equals("")) {
			throw new UsuarioException("CPF não informado");
		}
		
		if (nome.trim().equals("")) {
			throw new UsuarioException("Nome não informado");
		}
		
		if (login.trim().equals("")) {
			throw new UsuarioException("Usuario não informado");
		}
		
		if (senhaNova.trim().equals("")) {
			throw new UsuarioException("Nova senha não informado");
		}
		
	}
	
	private void buscarCadastro(String cpf, String nome, String login) throws UsuarioException {
		String usuarioCpf = null;
		String usuarioNome = null;
		String usuarioLogin = null;
		
		try {
			for (Usuario usuario : usuarioDao.usuarioList()) {
				if (cpf.equals(usuario.getCpf())) {
					usuarioCpf = usuario.getCpf();
				}
				
				if (nome.equals(usuario.getNome())) {
					usuarioNome = usuario.getNome();
				}
				
				if (login.equals(usuario.getLogin())) {
					usuarioLogin = usuario.getLogin();
				}
			}
			
			if (!cpf.equals(usuarioCpf)) { 
				throw new UsuarioException("CPF invalido");
			}
			
			else if (!nome.equals(usuarioNome)) {
				throw new UsuarioException("Nome invalido");
			}
			
			else if (!login.equals(usuarioLogin)) {
				throw new UsuarioException("Login invalido");
			}
			
		} catch (UsuarioDaoException e) {
			throw new UsuarioException(e.getMessage());
		}
	}
	
	public Boolean alterarSenha(String senhaNova, String cpf, String nome, String login) throws UsuarioException {
		
		try {
			validarAlteracao(senhaNova, cpf, nome, login);
			buscarCadastro(cpf, nome, login);
			return usuarioDao.editarSenha(senhaNova, cpf, nome, login);
			
		} catch (UsuarioDaoException e) {
			throw new UsuarioException("Erro técnico ao alterar usuario: " + e.getMessage());
		}
	}
}
