package bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;


import dao.UsuarioDao;
import entidade.Usuario;


@ManagedBean
@ViewScoped
public class UsuarioBean {

	private UsuarioDao usuarioDao = new UsuarioDao();
	private Usuario usuario = new Usuario();

	
	public String enviar() {
		usuario = usuarioDao.getUsuario(usuario.getNome(), usuario.getSenha());
		if (usuario == null) {
			FacesContext.getCurrentInstance().addMessage(
			null,
			new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário não encontrado!", 
				"Erro no login"));
			return null;
		}else {
			
			return "index";
		}
	}
	
	public UsuarioDao getUsuarioDao() {
		return usuarioDao;
	}

	public void setUsuarioDao(UsuarioDao usuarioDao) {
		this.usuarioDao = usuarioDao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}