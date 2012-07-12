/*
 * Telefone.java
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
public class Telefone {

	 private int cd_cliente;
	 private int cd_fornecedor;
	 private int cd_terceirizada;
	 private int cd_vendedor;
	private String cd_tel1;
	private String cd_tel2;
	private String cd_tel3;
	private String cd_tel4;
	
	
	

	public Telefone(String cd_tel1, String cd_tel2, String cd_tel3,
			String cd_tel4) {
		super();
		this.cd_tel1 = cd_tel1;
		this.cd_tel2 = cd_tel2;
		this.cd_tel3 = cd_tel3;
		this.cd_tel4 = cd_tel4;
	}


	public Telefone(int cd_cliente, String cd_tel1, String cd_tel2,
			String cd_tel3, String cd_tel4) {
		super();
		this.cd_cliente = cd_cliente;
		this.cd_tel1 = cd_tel1;
		this.cd_tel2 = cd_tel2;
		this.cd_tel3 = cd_tel3;
		this.cd_tel4 = cd_tel4;
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


	public String getCd_tel1() {
		return cd_tel1;
	}


	public void setCd_tel1(String cd_tel1) {
		this.cd_tel1 = cd_tel1;
	}


	public String getCd_tel2() {
		return cd_tel2;
	}


	public void setCd_tel2(String cd_tel2) {
		this.cd_tel2 = cd_tel2;
	}


	public String getCd_tel3() {
		return cd_tel3;
	}


	public void setCd_tel3(String cd_tel3) {
		this.cd_tel3 = cd_tel3;
	}


	public String getCd_tel4() {
		return cd_tel4;
	}


	public void setCd_tel4(String cd_tel4) {
		this.cd_tel4 = cd_tel4;
	}


	public String toString() {
		return cd_tel1;
	}
}

