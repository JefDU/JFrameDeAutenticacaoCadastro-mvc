package controller.dao.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import controller.exception.UsuarioDaoException;

public class UsuarioConnectionSql {

	private static String databaseName = "projeto_login_cadastro_v1";

	public static Connection connection() throws UsuarioDaoException, SQLException  {

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

			String dadosDoBanco = "jdbc:sqlserver://localhost:1433;" + "databaseName=" + databaseName
					+ ";integratedSecurity=true;" + "encrypt=true;trustServerCertificate=true";

			Connection retornoConexao = DriverManager.getConnection(dadosDoBanco);
			return retornoConexao;

		} catch (ClassNotFoundException error) {

			throw new UsuarioDaoException("Driver não Encontrado " + error.getMessage());
		}

	}
}
