package br.com.alura.loja.testes;

import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;
import org.hibernate.engine.spi.SessionDelegatorBaseImpl;

import javax.persistence.EntityManager;
import java.util.List;

public class TesteCriteria {

    public static void main(String[] args) {
        PopulaBanco.popularDados();
        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);
        List<Produto> ps5 = produtoDao.buscaPorParametrosComCriteria("PS5", null, null);

        System.out.println(ps5.size());
    }

}
