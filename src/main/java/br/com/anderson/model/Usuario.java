package br.com.anderson.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name= "seq_usuario",sequenceName ="seq_usuario")
public class Usuario implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private  Long id;
	private String nome;
	private int idade;
	public Long getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public int getIdade() {
		return idade;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setIdade(int idade) {
		this.idade = idade;
	}

}




