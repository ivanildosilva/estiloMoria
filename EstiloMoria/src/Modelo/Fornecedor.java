/*
 * Fornecedor.java
 *
 * Created on 30 de Agosto de 2011, 21:07
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package Modelo;

/**
 *
 * @author Familia-Silva
 */



public class Fornecedor {

	
	private int cd_fornecedor;
	private String cd_cnpj_fornecedor;
	private String nm_razaosocial_fornecedor;
	private String nm_fantasia_fornecdor;
	private String nm_observacao;
	private Telefone telefone;
	private Endereco endereco;
	
	
	
	
	
	public Fornecedor(String nm_razaosocial_fornecedor) {
		super();
		this.nm_razaosocial_fornecedor = nm_razaosocial_fornecedor;
	}


	public Fornecedor(String cd_cnpj_fornecedor, String nm_fantasia_fornecdor,
			Telefone telefone, Endereco endereco) {
		
		this.cd_cnpj_fornecedor = cd_cnpj_fornecedor;
		this.nm_fantasia_fornecdor = nm_fantasia_fornecdor;
		this.telefone = telefone;
		this.endereco = endereco;
	}


	public Fornecedor(int cd_fornecedor, String nm_razaosocial_fornecedor) {
		super();
		this.cd_fornecedor = cd_fornecedor;
		this.nm_razaosocial_fornecedor = nm_razaosocial_fornecedor;
	}


	public Fornecedor(int cd_fornecedor, Endereco endereco) {
		super();
		this.cd_fornecedor = cd_fornecedor;
		this.endereco = endereco;
	}


	public Fornecedor(int cd_fornecedor, Telefone telefone) {
		super();
		this.cd_fornecedor = cd_fornecedor;
		this.telefone = telefone;
	}


	private Email email;
	
	
	public Fornecedor(int cd_fornecedor, String nm_fantasia_fornecdor,
			String nm_observacao) {
		super();
		this.cd_fornecedor = cd_fornecedor;
		this.nm_fantasia_fornecdor = nm_fantasia_fornecdor;
		this.nm_observacao = nm_observacao;
	}


	public Fornecedor(String nm_fantasia_fornecdor, String nm_observacao) {
		
		this.nm_fantasia_fornecdor = nm_fantasia_fornecdor;
		this.nm_observacao = nm_observacao;
	}


	public Fornecedor(int cd_fornecedor, String cd_cnpj_fornecedor,
			String nm_razaosocial_fornecedor, String nm_fantasia_fornecdor,
			String nm_observacao, Telefone telefone, Endereco endereco,
			Email email) {
		
		this.cd_fornecedor = cd_fornecedor;
		this.cd_cnpj_fornecedor = cd_cnpj_fornecedor;
		this.nm_razaosocial_fornecedor = nm_razaosocial_fornecedor;
		this.nm_fantasia_fornecdor = nm_fantasia_fornecdor;
		this.nm_observacao = nm_observacao;
		this.telefone = telefone;
		this.endereco = endereco;
		this.email = email;
	}


	public Fornecedor(int cd_fornecedor, String nm_razaosocial_fornecedor,
			String nm_fantasia_fornecdor, String nm_observacao) {
		super();
		this.cd_fornecedor = cd_fornecedor;
		this.nm_razaosocial_fornecedor = nm_razaosocial_fornecedor;
		this.nm_fantasia_fornecdor = nm_fantasia_fornecdor;
		this.nm_observacao = nm_observacao;
	}


	public Fornecedor(int cd_fornecedor, String cd_cnpj_fornecedor,
			String nm_razaosocial_fornecedor, String nm_fantasia_fornecdor,
			String nm_observacao) {
		super();
		this.cd_fornecedor = cd_fornecedor;
		this.cd_cnpj_fornecedor = cd_cnpj_fornecedor;
		this.nm_razaosocial_fornecedor = nm_razaosocial_fornecedor;
		this.nm_fantasia_fornecdor = nm_fantasia_fornecdor;
		this.nm_observacao = nm_observacao;
	}


	public int getCd_fornecedor() {
		return cd_fornecedor;
	}


	public void setCd_fornecedor(int cd_fornecedor) {
		this.cd_fornecedor = cd_fornecedor;
	}


	public Fornecedor(String cd_cnpj_fornecedor,
			String nm_razaosocial_fornecedor, String nm_fantasia_fornecdor,
			String nm_observacao, Telefone telefone, Endereco endereco,
			Email email) {
	
		this.cd_cnpj_fornecedor = cd_cnpj_fornecedor;
		this.nm_razaosocial_fornecedor = nm_razaosocial_fornecedor;
		this.nm_fantasia_fornecdor = nm_fantasia_fornecdor;
		this.nm_observacao = nm_observacao;
		this.telefone = telefone;
		this.endereco = endereco;
		this.email = email;
	}


	public String getCd_cnpj_fornecedor() {
		return cd_cnpj_fornecedor;
	}


	public void setCd_cnpj_fornecedor(String cd_cnpj_fornecedor) {
		this.cd_cnpj_fornecedor = cd_cnpj_fornecedor;
	}


	public String getNm_razaosocial_fornecedor() {
		return nm_razaosocial_fornecedor;
	}


	public void setNm_razaosocial_fornecedor(String nm_razaosocial_fornecedor) {
		this.nm_razaosocial_fornecedor = nm_razaosocial_fornecedor;
	}


	public String getNm_fantasia_fornecdor() {
		return nm_fantasia_fornecdor;
	}


	public void setNm_fantasia_fornecdor(String nm_fantasia_fornecdor) {
		this.nm_fantasia_fornecdor = nm_fantasia_fornecdor;
	}


	public String getNm_observacao() {
		return nm_observacao;
	}


	public void setNm_observacao(String nm_observacao) {
		this.nm_observacao = nm_observacao;
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
		return nm_razaosocial_fornecedor;
	}
}