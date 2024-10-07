package br.com.jvp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import br.com.jvp.dao.jdbc.ConnectionFactory;
import br.com.jvp.domain.Cliente;

public class ClienteDAO  implements IClienteDAO{

	@Override
	public Integer cadastrar(Cliente cliente) throws Exception {
			
		Connection connection = null;
		PreparedStatement stm = null;
		
		
			try { 
				connection = ConnectionFactory.getConnection();
				String sql = "INSERT INTO TB_CLIENTE_2 (ID, CODIGO, NOME) VALUES (nextval('SQ_CLIENTE_2'), ?, ?";
				stm = connection.prepareStatement(sql);
				stm.setString(1, cliente.getCodigo());
				stm.setString(2, cliente.getNome());
				return stm.executeUpdate();
			} catch(Exception e ) {
				throw e;
			}finally {
				if (stm != null && !stm.isClosed()) {
					stm.close();
				} if (connection != null && !connection.isClosed()) {
					connection.close();
				}
				
			}
	}

	@Override
	public Cliente consultar(String codigo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer excluir(Cliente clienteBD) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
