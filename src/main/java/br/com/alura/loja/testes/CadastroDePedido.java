package br.com.alura.loja.testes;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ClienteDao;
import br.com.alura.loja.dao.PedidoDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.*;
import br.com.alura.loja.util.JPAUtil;
import br.com.alura.loja.vo.RelatorioVendasVO;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class CadastroDePedido {

    public static void main(String[] args) {
        popularDados();

        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);
        Produto produto = produtoDao.buscarPorId(1L);
        Produto produto2 = produtoDao.buscarPorId(2L);
        Produto produto3 = produtoDao.buscarPorId(3L);

        em.getTransaction().begin();

        Cliente cliente = new Cliente("NomeDoCliente", "1234564");
        Pedido pedido = new Pedido(cliente);
        pedido.adicionarItem(new ItemPedido(10, pedido, produto));
        pedido.adicionarItem(new ItemPedido(40, pedido, produto2));

        Pedido pedido1 = new Pedido(cliente);
        pedido1.adicionarItem(new ItemPedido(2, pedido1, produto3));

        ClienteDao clienteDao = new ClienteDao(em);
        clienteDao.cadastrar(cliente);

        PedidoDao pedidoDao = new PedidoDao(em);
        pedidoDao.cadastrar(pedido);
        pedidoDao.cadastrar(pedido1);

        em.getTransaction().commit();

        BigDecimal totalVendido = pedidoDao.valorTotalVendido();
        System.out.println("VALOR TOTAL: " + totalVendido);

        List<RelatorioVendasVO> relatorios = pedidoDao.relatorioDeVendas();
        for (RelatorioVendasVO rel: relatorios) {
            System.out.println(rel);
        }
    }

    private static void popularDados() {
        Categoria celulares = new Categoria("CELULARES");
        Categoria videogames = new Categoria("VIDEOGAMES");
        Categoria informatica = new Categoria("INFORMATICA");

        Produto celular = new Produto("Xiaomi Redmi", "Muito legal", new BigDecimal("800"), celulares );
        Produto videogame = new Produto("Wii", "play 5", new BigDecimal("900"), videogames );
        Produto mcbook = new Produto("mcbk", "bem novo", new BigDecimal("7800"), informatica );

        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);
        CategoriaDao categoriaDao = new CategoriaDao(em);

        em.getTransaction().begin();

        categoriaDao.cadastrar(celulares);
        categoriaDao.cadastrar(videogames);
        categoriaDao.cadastrar(informatica);
        produtoDao.cadastrar(celular);
        produtoDao.cadastrar(videogame);
        produtoDao.cadastrar(mcbook);

        em.getTransaction().commit();
        em.close();
    }
}
