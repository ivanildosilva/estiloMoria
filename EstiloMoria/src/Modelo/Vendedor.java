/*
 * Vendedor.java
 *
 * Created on 30 de Agosto de 2011, 21:06
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package Modelo;

/**
 *
 * @author Familia-Silva
 */
public class Vendedor {

	private int cd_vendedor;
	private String cd_cpf_vendedor;
	private String nm_vendedor;
	private Telefone telefone;
	private Endereco endereco;
	private Email email;
	
	public Vendedor(String cd_cpf_vendedor, String nm_vendedor,
			Telefone telefone, Endereco endereco) {
		
		this.cd_cpf_vendedor = cd_cpf_vendedor;
		this.nm_vendedor = nm_vendedor;
		this.telefone = telefone;
		this.endereco = endereco;
	}


	public Vendedor(int cd_vendedor, String cd_cpf_vendedor,
			String nm_vendedor, Telefone telefone) {
		super();
		this.cd_vendedor = cd_vendedor;
		this.cd_cpf_vendedor = cd_cpf_vendedor;
		this.nm_vendedor = nm_vendedor;
		this.telefone = telefone;
	}


	public Vendedor(int cd_vendedor,String nm_vendedor) {
		
		this.cd_vendedor = cd_vendedor;
		this.nm_vendedor = nm_vendedor;
	}


	public Vendedor(int cd_vendedor, String cd_cpf_vendedor, String nm_vendedor) {
		super();
		this.cd_vendedor = cd_vendedor;
		this.cd_cpf_vendedor = cd_cpf_vendedor;
		this.nm_vendedor = nm_vendedor;
	}


	public Vendedor(int cd_vendedor, String cd_cpf_vendedor,
			String nm_vendedor, Telefone telefone, Endereco endereco,
			Email email) {
		
		this.cd_vendedor = cd_vendedor;
		this.cd_cpf_vendedor = cd_cpf_vendedor;
		this.nm_vendedor = nm_vendedor;
		this.telefone = telefone;
		this.endereco = endereco;
		this.email = email;
	}


	public int getCd_vendedor() {
		return cd_vendedor;
	}


	public void setCd_vendedor(int cd_vendedor) {
		this.cd_vendedor = cd_vendedor;
	}


	public String getCd_cpf_vendedor() {
		return cd_cpf_vendedor;
	}


	public void setCd_cpf_vendedor(String cd_cpf_vendedor) {
		this.cd_cpf_vendedor = cd_cpf_vendedor;
	}


	public String getNm_vendedor() {
		return nm_vendedor;
	}


	public void setNm_vendedor(String nm_vendedor) {
		this.nm_vendedor = nm_vendedor;
	}


	public Telefone getTelefone() {
		return telefone;
	}


	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}


	public Endereco getEndereco() {
		return endereco;
	}


	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}


	public Email getEmail() {
		return email;
	}


	public void setEmail(Email email) {
		this.email = email;
	}
	public String toString() {
		return nm_vendedor;
	}		
	
}
