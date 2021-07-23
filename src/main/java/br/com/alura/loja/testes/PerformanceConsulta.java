package br.com.alura.loja.testes;

import br.com.alura.loja.dao.PedidoDao;
import br.com.alura.loja.modelo.Pedido;
import br.com.alura.loja.util.JPAUtil;

import javax.persistence.EntityManager;

public class PerformanceConsulta {

    public static void main(String[] args) {
        PopulaBanco.popularDados();
        EntityManager em = JPAUtil.getEntityManager();

        Pedido pedido = em.find(Pedido.class, 1L);

        PedidoDao pedidoDao = new PedidoDao(em);
        Pedido pedido1 = pedidoDao.buscarPedidoComCliente(1L);

        System.out.println(pedido1.toString());

        System.out.println(pedido.getItens().size());
    }


}
