package entidade;

import dao.UsuarioDao;

public class Login {

	public static void main(String[] args) {
		UsuarioDao ud = new UsuarioDao();
		Usuario u = new Usuario();
		u.setNome("erick");
		u.setSenha("123");
		ud.inserirUsuario(u);
	}

}
