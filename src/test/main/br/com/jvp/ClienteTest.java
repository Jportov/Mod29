package br.com.jvp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.jvp.dao.ClienteDAO;
import br.com.jvp.dao.IClienteDAO;
import br.com.jvp.domain.Cliente;

public class ClienteTest {
    
    private IClienteDAO dao;

    @Before
    public void setUp() throws Exception {
        // Aqui você pode inicializar sua conexão com o banco de dados e o DAO
        dao = new ClienteDAO(/* Aqui passe a conexão, se necessário */);
        
        // Limpeza do banco de dados ou preparação, se necessário
        limparBancoDeDados();
    }

    private void limparBancoDeDados() throws Exception {
        // Implemente a lógica para remover todos os registros da tabela tb_cliente
        // Exemplo: dao.excluirTodos(); (você pode precisar implementar este método)
    }

    @Test
    public void cadastrarTest() throws Exception {
        Cliente cliente = new Cliente();
        cliente.setCodigo("01");
        cliente.setNome("Rodrigo Pires");
        // Defina os outros campos necessários
        // cliente.setCpf(...);
        // cliente.setTel(...);
        // cliente.setEndereco(...);
        // cliente.setNumero(...);
        // cliente.setCidade(...);
        // cliente.setEstado(...);
        
        Integer countCad = dao.cadastrar(cliente);
        assertTrue("Cadastro não realizado com sucesso", countCad == 1);

        Cliente clienteBD = dao.consultar(cliente.getCodigo());
        assertNotNull("Cliente não encontrado no banco de dados", clienteBD);
        assertNotNull("ID do cliente deve ser preenchido", clienteBD.getId());
        assertEquals("Código do cliente não confere", cliente.getCodigo(), clienteBD.getCodigo());
        assertEquals("Nome do cliente não confere", cliente.getNome(), clienteBD.getNome());

        Integer countDel = dao.excluir(clienteBD);
        assertTrue("Exclusão do cliente não realizada com sucesso", countDel == 1);
    }

    @Test
    public void consultarTest() throws Exception {
        // Primeiramente, cadastre um cliente para garantir que temos um registro
        Cliente cliente = new Cliente();
        cliente.setCodigo("02");
        cliente.setNome("Maria Silva");
        // Defina os outros campos necessários
        
        dao.cadastrar(cliente);
        
        // Agora, vamos consultar o cliente
        Cliente clienteBD = dao.consultar(cliente.getCodigo());
        assertNotNull("Cliente não encontrado ao consultar", clienteBD);
        assertEquals("Código do cliente não confere", cliente.getCodigo(), clienteBD.getCodigo());
        assertEquals("Nome do cliente não confere", cliente.getNome(), clienteBD.getNome());
    }

    @Test
    public void excluirTest() throws Exception {
        // Cadastre um cliente para poder excluí-lo
        Cliente cliente = new Cliente();
        cliente.setCodigo("03");
        cliente.setNome("João Santos");
        // Defina os outros campos necessários
        
        dao.cadastrar(cliente);

        // Agora, vamos excluí-lo
        Integer countDel = dao.excluir(cliente);
        assertTrue("Exclusão do cliente não realizada com sucesso", countDel == 1);

        // Verifique se o cliente foi realmente excluído
        Cliente clienteBD = dao.consultar(cliente.getCodigo());
        assertNull("Cliente ainda encontrado após exclusão", clienteBD);
    }
    @Test
    public void buscarTodosTest() throws Exception {
        IClienteDAO dao = new ClienteDAO();

        // Cadastrar alguns clientes para teste
        Cliente cliente1 = new Cliente();
        cliente1.setCodigo("04");
        cliente1.setNome("Carlos Mendes");
        dao.cadastrar(cliente1);

        Cliente cliente2 = new Cliente();
        cliente2.setCodigo("05");
        cliente2.setNome("Ana Paula");
        dao.cadastrar(cliente2);

        // Buscar todos os clientes
        List<Cliente> clientes = dao.buscarTodos();
        assertNotNull(clientes);
        assertTrue(clientes.size() > 1); // Verifica se temos mais de um cliente

        // Verifica se os clientes cadastrados estão na lista
        assertTrue(clientes.stream().anyMatch(c -> c.getCodigo().equals(cliente1.getCodigo())));
        assertTrue(clientes.stream().anyMatch(c -> c.getCodigo().equals(cliente2.getCodigo())));
    }

    @Test
    public void atualizarTest() throws Exception {
        IClienteDAO dao = new ClienteDAO();

        // Cadastrar um cliente para atualizar
        Cliente cliente = new Cliente();
        cliente.setCodigo("06");
        cliente.setNome("Fernando Almeida");
        dao.cadastrar(cliente);

        // Atualizar o nome do cliente
        cliente.setNome("Fernando Alves");
        Integer countAtualizar = dao.atualizar(cliente);
        assertTrue(countAtualizar == 1);

        // Consultar para verificar se o nome foi atualizado
        Cliente clienteBD = dao.consultar(cliente.getCodigo());
        assertNotNull(clienteBD);
        assertEquals("Fernando Alves", clienteBD.getNome());
    }

}
