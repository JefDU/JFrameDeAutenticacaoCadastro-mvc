package controller;

import javax.swing.table.DefaultTableModel;

import controller.dao.UsuarioDao;
import controller.dao.impl.UsuarioDaoImpl;
import controller.exception.UsuarioException;
import controller.exception.UsuarioDaoException;

public class ListaController {

	private UsuarioDao usuarioDao;

	public ListaController() {
		usuarioDao = new UsuarioDaoImpl();
	}

	public DefaultTableModel tableUsuarios() throws UsuarioException {
		try {
			return usuarioDao.listarUsuarios();

		} catch (UsuarioDaoException e) {
			throw new UsuarioException("Erro técnico ao listar usuarios: " + e.getMessage());
		}
	}

	private void validarAlteracao(String cpf) throws UsuarioException {
		if (cpf.trim().equals("")) {
			throw new UsuarioException("CPF não informado");
		}
	}

	public Boolean deletarUsuario(String cpf) throws UsuarioException {
		try {
			validarAlteracao(cpf);
			return usuarioDao.excluir(cpf);
		} catch (UsuarioDaoException e) {
			throw new UsuarioException("Erro técnico ao deletar usuarios: " + e.getMessage());
		}

	}
}
