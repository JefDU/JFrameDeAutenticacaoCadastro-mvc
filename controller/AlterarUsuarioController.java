package controller;

import controller.dao.UsuarioDao;
import controller.dao.impl.UsuarioDaoImpl;
import controller.exception.RegraDeNegocioException;
import controller.exception.UsuarioDaoException;
import model.Usuario;

public class AlterarUsuarioController {

	 private UsuarioDao usuarioDao;

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
	
	private void buscarCadastro(String cpf, String nome) throws RegraDeNegocioException {
		String userCpf = null;
		String userNome = null;
		
		try {
			for (Usuario usuario : usuarioDao.usuarioList()) {
				if (cpf.equals(usuario.getCpf())) {
					userCpf = usuario.getCpf();
				}
				
				if (nome.equals(usuario.getNome())) {
					userNome = usuario.getNome();
				}
			}
			
			if (!cpf.equals(userCpf) && !nome.equals(userNome)) {				
				throw new RegraDeNegocioException("CPF e nome invalidos");

			}
			
			else if (!cpf.equals(userCpf)) { 
				throw new RegraDeNegocioException("CPF invalido");
			}
			
			else if (!nome.equals(userNome)) {
				throw new RegraDeNegocioException("Nome invalido");
			}
			
			
		} catch (UsuarioDaoException e) {
			throw new RegraDeNegocioException(e.getMessage());
		}
	}

	public Boolean alterarUsuario(String loginNovo, String cpf, String nome) throws RegraDeNegocioException {
		
		try {
			validarAlteracao(loginNovo, cpf, nome);
			buscarCadastro(cpf, nome);
			return usuarioDao.editarUsuario(loginNovo, cpf, nome);
			
		} catch (UsuarioDaoException e) {
			throw new RegraDeNegocioException("Erro técnico ao alterar usuario: " + e.getMessage());
		}
	}
}
