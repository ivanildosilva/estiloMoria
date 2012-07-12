/*
 * Cliente.java
 *
 * Created on 30 de Agosto de 2011, 21:04
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package Modelo;

/**
 *
 * @author Familia-Silva
 */

import java.util.Date;

public class Cliente {

	private int cd_cliente;
	private String cd_cpf_cliente;
	private String cd_cnpj_cliente;
	private String nm_cliente;
	private String nm_fantasia_cliente;
	private Date dt_nasicmento_cliente;
	private String cd_rg_cliente;
	private String ic_sexo_cliente;
	private Telefone telefone;
	private Endereco endereco;
	private Email email;
	
	
	
	public Cliente(String cd_cpf_cliente, String cd_cnpj_cliente,
			String nm_cliente, 
			Date dt_nasicmento_cliente, String cd_rg_cliente,
			String ic_sexo_cliente, Telefone telefone, Endereco endereco,
			Email email) {
		super();
		this.cd_cpf_cliente = cd_cpf_cliente;
		this.cd_cnpj_cliente = cd_cnpj_cliente;
		this.nm_cliente = nm_cliente;
		
		this.dt_nasicmento_cliente = dt_nasicmento_cliente;
		this.cd_rg_cliente = cd_rg_cliente;
		this.ic_sexo_cliente = ic_sexo_cliente;
		this.telefone = telefone;
		this.endereco = endereco;
		this.email = email;
	}

	public Cliente(String nm_cliente) {
		super();
		this.nm_cliente = nm_cliente;
	}

	public Cliente(int cd_cliente, String nm_cliente) {
		super();
		this.cd_cliente = cd_cliente;
		this.nm_cliente = nm_cliente;
	}

	public Cliente(int cd_cliente, String cd_cpf_cliente,
			String cd_cnpj_cliente, String nm_cliente,
			String nm_fantasia_cliente, Date dt_nasicmento_cliente,
			String cd_rg_cliente, String ic_sexo_cliente, Telefone telefone) {
		
		this.cd_cliente = cd_cliente;
		this.cd_cpf_cliente = cd_cpf_cliente;
		this.cd_cnpj_cliente = cd_cnpj_cliente;
		this.nm_cliente = nm_cliente;
		this.nm_fantasia_cliente = nm_fantasia_cliente;
		this.dt_nasicmento_cliente = dt_nasicmento_cliente;
		this.cd_rg_cliente = cd_rg_cliente;
		this.ic_sexo_cliente = ic_sexo_cliente;
		this.telefone = telefone;
	}

	public Cliente(int cd_cliente, String nm_fantasia_cliente,
			Date dt_nasicmento_cliente, String cd_rg_cliente,
			String ic_sexo_cliente) {
		
		this.cd_cliente = cd_cliente;
		this.nm_fantasia_cliente = nm_fantasia_cliente;
		this.dt_nasicmento_cliente = dt_nasicmento_cliente;
		this.cd_rg_cliente = cd_rg_cliente;
		this.ic_sexo_cliente = ic_sexo_cliente;
	}

	public Cliente(int cd_cliente, String cd_cpf_cliente,
			String cd_cnpj_cliente, String nm_cliente,
			String nm_fantasia_cliente, Date dt_nasicmento_cliente,
			String cd_rg_cliente, String ic_sexo_cliente, Telefone telefone,
			Endereco endereco, Email email) {
		super();
		this.cd_cliente = cd_cliente;
		this.cd_cpf_cliente = cd_cpf_cliente;
		this.cd_cnpj_cliente = cd_cnpj_cliente;
		this.nm_cliente = nm_cliente;
		this.nm_fantasia_cliente = nm_fantasia_cliente;
		this.dt_nasicmento_cliente = dt_nasicmento_cliente;
		this.cd_rg_cliente = cd_rg_cliente;
		this.ic_sexo_cliente = ic_sexo_cliente;
		this.telefone = telefone;
		this.endereco = endereco;
		this.email = email;
	}

	public Cliente(int cd_cliente, String cd_cpf_cliente,
			String cd_cnpj_cliente, String nm_cliente,
			String nm_fantasia_cliente, Date dt_nasicmento_cliente,
			String cd_rg_cliente, String ic_sexo_cliente) {

		this.cd_cliente = cd_cliente;
		this.cd_cpf_cliente = cd_cpf_cliente;
		this.cd_cnpj_cliente = cd_cnpj_cliente;
		this.nm_cliente = nm_cliente;
		this.nm_fantasia_cliente = nm_fantasia_cliente;
		this.dt_nasicmento_cliente = dt_nasicmento_cliente;
		this.cd_rg_cliente = cd_rg_cliente;
		this.ic_sexo_cliente = ic_sexo_cliente;

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

	public Telefone getTelefone() {
		return telefone;
	}

	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}

	public int getCd_cliente() {
		return cd_cliente;
	}

	public void setCd_cliente(int cd_cliente) {
		this.cd_cliente = cd_cliente;
	}

	public String getCd_cpf_cliente() {
		return cd_cpf_cliente;
	}

	public void setCd_cpf_cliente(String cd_cpf_cliente) {
		this.cd_cpf_cliente = cd_cpf_cliente;
	}

	public String getCd_cnpj_cliente() {
		return cd_cnpj_cliente;
	}

	public void setCd_cnpj_cliente(String cd_cnpj_cliente) {
		this.cd_cnpj_cliente = cd_cnpj_cliente;
	}

	public String getNm_cliente() {
		return nm_cliente;
	}

	public void setNm_cliente(String nm_cliente) {
		this.nm_cliente = nm_cliente;
	}

	public String getNm_fantasia_cliente() {
		return nm_fantasia_cliente;
	}

	public void setNm_fantasia_cliente(String nm_fantasia_cliente) {
		this.nm_fantasia_cliente = nm_fantasia_cliente;
	}

	public Date getDt_nasicmento_cliente() {
		return dt_nasicmento_cliente;
	}

	public void setDt_nasicmento_cliente(Date dt_nasicmento_cliente) {
		this.dt_nasicmento_cliente = dt_nasicmento_cliente;
	}

	public String getCd_rg_cliente() {
		return cd_rg_cliente;
	}

	public void setCd_rg_cliente(String cd_rg_cliente) {
		this.cd_rg_cliente = cd_rg_cliente;
	}

	public String getIc_sexo_cliente() {
		return ic_sexo_cliente;
	}

	public void setIc_sexo_cliente(String ic_sexo_cliente) {
		this.ic_sexo_cliente = ic_sexo_cliente;
	}

	public String toString() {
		return nm_cliente;
	}
}