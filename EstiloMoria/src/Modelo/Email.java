/*
 * Email.java
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




public class Email {

	private int cd_cliente;
	 private int cd_fornecedor;
	 private int cd_terceirizada;
	 private int cd_vendedor;
	private String nm_email1;
	
	
	
	public Email(String nm_email1) {
		
		this.nm_email1 = nm_email1;
	}
	public Email(int cd_cliente, String nm_email1) {
		
		this.cd_cliente = cd_cliente;
		this.nm_email1 = nm_email1;
	}
	public Email(int cd_cliente, int cd_fornecedor, int cd_terceirizada,
			int cd_vendedor, String nm_email1) {
		
		this.cd_cliente = cd_cliente;
		this.cd_fornecedor = cd_fornecedor;
		this.cd_terceirizada = cd_terceirizada;
		this.cd_vendedor = cd_vendedor;
		this.nm_email1 = nm_email1;
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
	public String getNm_email1() {
		return nm_email1;
	}
	public void setNm_email1(String nm_email1) {
		this.nm_email1 = nm_email1;
	}

		
}