package br.com.jvp.dao;

import java.util.List;

import br.com.jvp.domain.Cliente;

public interface IClienteDAO {

	
	
			public Integer cadastrar(Cliente cliente) throws Exception; 
			
			public Cliente consultar(String codigo) throws Exception; 
			
			public Integer excluir(Cliente clienteBD) throws Exception;
			
			List<Cliente> buscarTodos() throws Exception;
			
		    Integer atualizar(Cliente cliente) throws Exception;
}
