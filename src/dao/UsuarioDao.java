package dao;


import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import entidade.Usuario;
import util.JpaUtil;



public class UsuarioDao {
	
	
	public Usuario getUsuario(String nome, String senha) {
		   try {
			    EntityManager em = JpaUtil.criarEntityManager();
				Usuario usuario = (Usuario) em
		         .createQuery("SELECT u from Usuario u where u.nome = :nome and u.senha = :senha")
		         .setParameter("nome", nome)
		         .setParameter("senha", senha).getSingleResult();
				return usuario; 
		         
		     
		    } catch (NoResultException e) {
		    	return null;
		    }
	}
	
	public boolean inserirUsuario(Usuario usuario) {
        try {
        	EntityManager em = JpaUtil.criarEntityManager();
        	  em.getTransaction().begin();
              em.persist(usuario);
              em.getTransaction().commit();
              em.close();
              return true;
        } catch (Exception e) {
              e.printStackTrace();
              return false;
        }
  }

  public boolean deletarUsuario(Usuario usuario) {
        try {
        	EntityManager em = JpaUtil.criarEntityManager();
              em.remove(usuario);
              return true;
        } catch (Exception e) {
              e.printStackTrace();
              return false;
        }
  }
}