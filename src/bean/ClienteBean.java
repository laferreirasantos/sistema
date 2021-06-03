package bean;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import com.google.gson.Gson;

import dao.ClienteDao;
import entidade.Cliente;

@ViewScoped
@ManagedBean(name = "clienteBean")
public class ClienteBean {

	
	private Cliente cliente = new Cliente();
	private List<Cliente> lista;
	private String pesquisa;

	public String salvar() {
		cliente = ClienteDao.merge(cliente);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Status", "SUCESSO!"));
		return null;
	}

	public String novo() {
		cliente = new Cliente();
		return null;
	}

	public void pesquisaEndereco(AjaxBehaviorEvent evento) {
		try {
			URL url = new URL("https://viacep.com.br/ws/" + cliente.getCep() + "/json/");
			URLConnection connection = url.openConnection();
			InputStream retorno = connection.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(retorno, "UTF-8"));

			String cep = "";
			StringBuilder json = new StringBuilder();
			while ((cep = reader.readLine()) != null) {
				json.append(cep);
			}

			Cliente cepCliete = new Gson().fromJson(json.toString(), Cliente.class);

			cliente.setCep(cepCliete.getCep());
			cliente.setLogradouro(cepCliete.getLogradouro());
			cliente.setComplemento(cepCliete.getComplemento());
			cliente.setBairro(cepCliete.getBairro());
			cliente.setLocalidade(cepCliete.getLocalidade());
			cliente.setUf(cepCliete.getUf());
			cliente.setUnidade(cepCliete.getUnidade());

			if (cliente.getLogradouro() == null) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falha", "CEP não encontrado"));

			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Status", "CEP encontrado"));

			}

		} catch (Exception e) {

		}
	}

	public String remover() {
		ClienteDao.remover(cliente);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, "Status", "Removido!"));
		return null;
	}

	public void pesquisar() {
		lista = ClienteDao.pesquisar(pesquisa);
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Cliente> getLista() {
		if (lista == null) {
			lista = ClienteDao.listar();
		}

		return lista;
	}

	
	public void setLista(List<Cliente> lista) {
		this.lista = lista;
	}

	public String getPesquisa() {
		return pesquisa;
	}

	public void setPesquisa(String pesquisa) {
		this.pesquisa = pesquisa;
	}

}
