package controller.dao;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import controller.exception.UsuarioDaoException;
import model.Usuario;

public interface UsuarioDao {
	public void salvarUsuario(Usuario user) throws UsuarioDaoException;
	public Boolean editarUsuario(String loginNovo, String cpf, String nome) throws UsuarioDaoException;
	public Boolean editarSenha(String senhaNova, String cpf, String nome, String login) throws UsuarioDaoException;
	public Boolean validarAcesso(String login, String senha) throws UsuarioDaoException;
	public DefaultTableModel listarUsuarios() throws UsuarioDaoException;
	public List<Usuario> usuarioList() throws UsuarioDaoException;
	public Boolean excluir(String cpf) throws UsuarioDaoException;
}
