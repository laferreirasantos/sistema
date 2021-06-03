package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import entidade.Cliente;
import entidade.Usuario;
import util.JpaUtil;

public class ClienteDao {

	public static void salvar(Cliente cliente) {
		EntityManager em = JpaUtil.criarEntityManager();
		em.getTransaction().begin();
		em.persist(cliente);
		em.getTransaction().commit();
		em.close();
	}

	public static List<Cliente> listar() {
		EntityManager em = JpaUtil.criarEntityManager();
		Query query = em.createQuery("select c from Cliente c order by c.id desc");
		List<Cliente> lista = query.getResultList();
		em.close();
		return lista;
	}

	public static Cliente merge(Cliente cliente) {
		EntityManager em = JpaUtil.criarEntityManager();
		em.getTransaction().begin();
		Cliente retorno = em.merge(cliente);
		em.getTransaction().commit();
		em.close();

		return retorno;
	}

	public static void remover(Cliente cliente) {
		EntityManager em = JpaUtil.criarEntityManager();
		em.getTransaction().begin();
		cliente = em.find(Cliente.class, cliente.getId());
		em.remove(cliente);
		em.getTransaction().commit();
		em.close();
	}

	public static  List<Cliente> pesquisar(String pesquisa) {
		EntityManager em = JpaUtil.criarEntityManager();
		Query query = em.createQuery("from Cliente where nomecompleto like '%" + pesquisa + "%' ");
		
		return query.getResultList();
	}


}