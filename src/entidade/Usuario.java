package entidade;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
public class Usuario {
	@Id
	@GeneratedValue
	@Column(name="id", nullable = false, unique = true)
	private Integer id;
	
	@Column(name="nome", nullable = false, unique = false)
	private String nome;
	
	@Column(name="senha", nullable = false, unique = false)
	private String senha;
	
	@Column(name="ultimoAcesso", unique = true)
	@Temporal (TemporalType.DATE)
	private Date ultimoAcesso;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public Date getUltimoAcesso() {
		return ultimoAcesso;
	}
	public void setUltimoAcesso(Date ultimoAcesso) {
		this.ultimoAcesso = ultimoAcesso;
	}

}
