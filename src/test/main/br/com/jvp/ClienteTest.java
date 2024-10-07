package br.com.jvp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import br.com.jvp.dao.ClienteDAO;
import br.com.jvp.dao.IClienteDAO;
import br.com.jvp.domain.Cliente;


public class ClienteTest {

	
	
		
		
		@Test
		public void cadastrarTest() throws Exception {
			
			IClienteDAO dao = new ClienteDAO();
			
		
			
			Cliente cliente = new Cliente();
			cliente.setCodigo("01");
			cliente.setNome("Rodrigo Pires");
			
			Integer countCad = dao.cadastrar(cliente);
			assertTrue(countCad == 1);
			
			
			
			
			Cliente clienteBD = dao.consultar(cliente.getCodigo());
			assertNotNull(clienteBD);
			assertNotNull(clienteBD.getId());
			assertEquals(cliente.getCodigo(), clienteBD.getCodigo());
			assertEquals(cliente.getNome(), clienteBD.getNome());
			
			Integer countDel = dao.excluir(clienteBD);
			assertTrue(countDel == 1);
		}
		
	}

		

