package br.com.jvp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import br.com.jvp.dao.ProdutoDAO;
import br.com.jvp.dao.IProdutoDAO;
import br.com.jvp.domain.Produto;

public class ProdutoTest {

    @Test
    public void cadastrarTest() throws Exception {
        IProdutoDAO dao = new ProdutoDAO();

        Produto produto = new Produto();
        produto.setCodigo("P001");
        produto.setNome("Produto Teste");
        produto.setPreco(99.99);

        Integer countCad = dao.cadastrar(produto);
        assertTrue(countCad == 1);

        Produto produtoBD = dao.consultar(produto.getCodigo());
        assertNotNull(produtoBD);
        assertNotNull(produtoBD.getId());
        assertEquals(produto.getCodigo(), produtoBD.getCodigo());
        assertEquals(produto.getNome(), produtoBD.getNome());
        assertEquals(produto.getPreco(), produtoBD.getPreco(), 0.01);

        Integer countDel = dao.excluir(produtoBD);
        assertTrue(countDel == 1);
    }

    @Test
    public void buscarTodosTest() throws Exception {
        IProdutoDAO dao = new ProdutoDAO();

        // Cadastrar alguns produtos para teste
        Produto produto1 = new Produto();
        produto1.setCodigo("P002");
        produto1.setNome("Produto A");
        produto1.setPreco(50.0);
        dao.cadastrar(produto1);

        Produto produto2 = new Produto();
        produto2.setCodigo("P003");
        produto2.setNome("Produto B");
        produto2.setPreco(75.0);
        dao.cadastrar(produto2);

        // Buscar todos os produtos
        List<Produto> produtos = dao.buscarTodos();
        assertNotNull(produtos);
        assertTrue(produtos.size() > 1); // Verifica se temos mais de um produto

        // Verifica se os produtos cadastrados estão na lista
        assertTrue(produtos.stream().anyMatch(p -> p.getCodigo().equals(produto1.getCodigo())));
        assertTrue(produtos.stream().anyMatch(p -> p.getCodigo().equals(produto2.getCodigo())));
    }

    @Test
    public void atualizarTest() throws Exception {
        IProdutoDAO dao = new ProdutoDAO();

        // Cadastrar um produto para atualizar
        Produto produto = new Produto();
        produto.setCodigo("P004");
        produto.setNome("Produto C");
        produto.setPreco(30.0);
        dao.cadastrar(produto);

        // Atualizar os dados do produto
        produto.setNome("Produto C Atualizado");
        produto.setPreco(35.0);
        Integer countAtualizar = dao.atualizar(produto);
        assertTrue(countAtualizar == 1);

        // Consultar para verificar se os dados foram atualizados
        Produto produtoBD = dao.consultar(produto.getCodigo());
        assertNotNull(produtoBD);
        assertEquals("Produto C Atualizado", produtoBD.getNome());
        assertEquals(35.0, produtoBD.getPreco(), 0.01);
        
        // Excluir o produto para limpar os testes
        dao.excluir(produtoBD);
    }

    @Test
    public void excluirTest() throws Exception {
        IProdutoDAO dao = new ProdutoDAO();

        // Cadastrar um produto para excluir
        Produto produto = new Produto();
        produto.setCodigo("P005");
        produto.setNome("Produto D");
        produto.setPreco(40.0);
        dao.cadastrar(produto);

        // Excluir o produto
        Integer countDel = dao.excluir(produto);
        assertTrue(countDel == 1);

        // Verificar se o produto foi realmente excluído
        Produto produtoBD = dao.consultar(produto.getCodigo());
        assertTrue(produtoBD == null);
    }
}
