package controller;

import javax.swing.table.DefaultTableModel;

import controller.dao.UsuarioDao;
import controller.dao.impl.UsuarioDaoImpl;
import controller.exception.RegraDeNegocioException;
import controller.exception.UsuarioDaoException;

public class ListaController {

UsuarioDao usuarioDao;
	
	public ListaController() {
		usuarioDao = new UsuarioDaoImpl();
	}
	
	public DefaultTableModel tableUsuarios() throws RegraDeNegocioException {
		try {
			return usuarioDao.listarUsuarios();
			
		} catch (UsuarioDaoException e) {
			throw new RegraDeNegocioException("Erro técnico ao listar usuarios: " + e.getMessage());
		}
	}
	
	private void validarAlteracao(String cpf) throws RegraDeNegocioException {
		if (cpf.trim().equals("")) {
			throw new RegraDeNegocioException("CPF não informado");
		}
	}
	
	public Boolean deletarUsuario(String cpf) throws RegraDeNegocioException {
		try {
			validarAlteracao(cpf);
			return usuarioDao.excluir(cpf);
		} catch (UsuarioDaoException e) {
			throw new RegraDeNegocioException("Erro técnico ao deletar usuarios: " + e.getMessage());
		}
	
	}
}
