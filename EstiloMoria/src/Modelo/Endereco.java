/*
 * Endereco.java
 *
 * Created on 30 de Agosto de 2011, 21:05
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package Modelo;

/**
 *
 * @author Familia-Silva
 */
public class Endereco {

	private int cd_cliente;
	 private int cd_fornecedor;
	 private int cd_terceirizada;
	 private int cd_vendedor;
	private String nm_logradouro;
	private String cd_numero;
	private String nm_complemente;
	private String nm_bairro;
	private String nm_cidade;
	private String sg_ufederativa;
	private String nm_observacao;
	private String cd_cep;

		
	

		public Endereco(String nm_logradouro, String cd_numero,
			String nm_complemente, String nm_bairro, String nm_cidade,
			String cd_cep) {
		
		
		this.nm_logradouro = nm_logradouro;
		this.cd_numero = cd_numero;
		this.nm_complemente = nm_complemente;
		this.nm_bairro = nm_bairro;
		this.nm_cidade = nm_cidade;
		this.cd_cep = cd_cep;
	}

		public Endereco(
			String nm_logradouro, String cd_numero, String nm_complemente,
			String nm_bairro, String nm_cidade, String sg_ufederativa,
			String cd_cep) {
		
		
		this.nm_logradouro = nm_logradouro;
		this.cd_numero = cd_numero;
		this.nm_complemente = nm_complemente;
		this.nm_bairro = nm_bairro;
		this.nm_cidade = nm_cidade;
		this.sg_ufederativa = sg_ufederativa;
		this.cd_cep = cd_cep;
	}

		public Endereco(int cd_cliente, String nm_logradouro, String cd_numero,
			String nm_complemente, String nm_bairro, String nm_cidade,
			String sg_ufederativa, String cd_cep) {
		super();
		this.cd_cliente = cd_cliente;
		this.nm_logradouro = nm_logradouro;
		this.cd_numero = cd_numero;
		this.nm_complemente = nm_complemente;
		this.nm_bairro = nm_bairro;
		this.nm_cidade = nm_cidade;
		this.sg_ufederativa = sg_ufederativa;
		this.cd_cep = cd_cep;
	}

		public Endereco(int cd_cliente, String nm_logradouro, String cd_numero,
			String nm_complemente, String nm_bairro, String nm_cidade,
			String sg_ufederativa) {
		super();
		this.cd_cliente = cd_cliente;
		this.nm_logradouro = nm_logradouro;
		this.cd_numero = cd_numero;
		this.nm_complemente = nm_complemente;
		this.nm_bairro = nm_bairro;
		this.nm_cidade = nm_cidade;
		this.sg_ufederativa = sg_ufederativa;
	}

		public Endereco(int cd_cliente, int cd_fornecedor, int cd_terceirizada,
			int cd_vendedor, String nm_logradouro, String cd_numero,
			String nm_complemente, String nm_bairro, String nm_cidade,
			String sg_ufederativa, String cd_cep) {
		super();
		this.cd_cliente = cd_cliente;
		this.cd_fornecedor = cd_fornecedor;
		this.cd_terceirizada = cd_terceirizada;
		this.cd_vendedor = cd_vendedor;
		this.nm_logradouro = nm_logradouro;
		this.cd_numero = cd_numero;
		this.nm_complemente = nm_complemente;
		this.nm_bairro = nm_bairro;
		this.nm_cidade = nm_cidade;
		this.sg_ufederativa = sg_ufederativa;
		this.cd_cep = cd_cep;
	}

		public Endereco(String nm_logradouro, String cd_numero,
			String nm_complemente, String nm_bairro, String nm_cidade,
			String sg_ufederativa, String nm_observacao, String cd_cep) {
		
		this.nm_logradouro = nm_logradouro;
		this.cd_numero = cd_numero;
		this.nm_complemente = nm_complemente;
		this.nm_bairro = nm_bairro;
		this.nm_cidade = nm_cidade;
		this.sg_ufederativa = sg_ufederativa;
		this.nm_observacao = nm_observacao;
		this.cd_cep = cd_cep;
	}

	public Endereco(int cd_cliente, String nm_logradouro, String cd_numero,
			String nm_complemente, String nm_bairro, String nm_cidade,
			String sg_ufederativa, String nm_observacao, String cd_cep) {
		
		this.cd_cliente = cd_cliente;
		this.nm_logradouro = nm_logradouro;
		this.cd_numero = cd_numero;
		this.nm_complemente = nm_complemente;
		this.nm_bairro = nm_bairro;
		this.nm_cidade = nm_cidade;
		this.sg_ufederativa = sg_ufederativa;
		this.nm_observacao = nm_observacao;
		this.cd_cep = cd_cep;
	}

	
	public int getCd_cliente() {
		return cd_cliente;
	}

	public void setCd_cliente(int cd_cliente) {
		this.cd_cliente = cd_cliente;
	}

	public int getCd_fornecedor() {
		return cd_fornecedor;
	}

	public void setCd_fornecedor(int cd_fornecedor) {
		this.cd_fornecedor = cd_fornecedor;
	}

	public int getCd_terceirizada() {
		return cd_terceirizada;
	}

	public void setCd_terceirizada(int cd_terceirizada) {
		this.cd_terceirizada = cd_terceirizada;
	}

	public int getCd_vendedor() {
		return cd_vendedor;
	}

	public void setCd_vendedor(int cd_vendedor) {
		this.cd_vendedor = cd_vendedor;
	}

	public String getCd_cep() {
		return cd_cep;
	}

	public void setCd_cep(String cd_cep) {
		this.cd_cep = cd_cep;
	}

	public String getNm_logradouro() {
		return nm_logradouro;
	}

	public void setNm_logradouro(String nm_logradouro) {
		this.nm_logradouro = nm_logradouro;
	}

	public String getCd_numero() {
		return cd_numero;
	}

	public void setCd_numero(String cd_numero) {
		this.cd_numero = cd_numero;
	}

	public String getNm_complemente() {
		return nm_complemente;
	}

	public void setNm_complemente(String nm_complemente) {
		this.nm_complemente = nm_complemente;
	}

	public String getNm_bairro() {
		return nm_bairro;
	}

	public void setNm_bairro(String nm_bairro) {
		this.nm_bairro = nm_bairro;
	}

	public String getNm_cidade() {
		return nm_cidade;
	}

	public void setNm_cidade(String nm_cidade) {
		this.nm_cidade = nm_cidade;
	}

	public String getSg_ufederativa() {
		return sg_ufederativa;
	}

	public void setSg_ufederativa(String sg_ufederativa) {
		this.sg_ufederativa = sg_ufederativa;
	}

	public String getNm_observacao() {
		return nm_observacao;
	}

	public void setNm_observacao(String nm_observacao) {
		this.nm_observacao = nm_observacao;
	}
	public String toString() {
		return nm_logradouro;
	}
}
