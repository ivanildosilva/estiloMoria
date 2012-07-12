/*
 * Terceirizada.java
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



public class Terceirizada {
    
    private int cd_terceirizada;
    private String cd_cpf_terceirizada;
    private String nm_contato;
    private Telefone telefone;
    private Endereco endereco;
    private Email email;
    
    
    
    
    public Terceirizada(String cd_cpf_terceirizada, String nm_contato,
            Telefone telefone, Endereco endereco) {
        
        this.cd_cpf_terceirizada = cd_cpf_terceirizada;
        this.nm_contato = nm_contato;
        this.telefone = telefone;
        this.endereco = endereco;
        
        
    }
    
    
    
    
    
    
    
    
    
    public Terceirizada(int cd_terceirizada, String nm_contato) {
        super();
        this.cd_terceirizada = cd_terceirizada;
        this.nm_contato = nm_contato;
    }
    
    
    public Terceirizada(int cd_terceirizada, String cd_cpf_terceirizada,
            String nm_contato) {
        super();
        this.cd_terceirizada = cd_terceirizada;
        this.cd_cpf_terceirizada = cd_cpf_terceirizada;
        this.nm_contato = nm_contato;
    }
    
    
    public Terceirizada(int cd_terceirizada, String cd_cpf_terceirizada,
            String nm_contato, Telefone telefone, Endereco endereco, Email email) {
        
        this.cd_terceirizada = cd_terceirizada;
        this.cd_cpf_terceirizada = cd_cpf_terceirizada;
        this.nm_contato = nm_contato;
        this.telefone = telefone;
        this.endereco = endereco;
        this.email = email;
    }
    
    
    
    
    public int getCd_terceirizada() {
        return cd_terceirizada;
    }
    
    
    
    
    public void setCd_terceirizada(int cd_terceirizada) {
        this.cd_terceirizada = cd_terceirizada;
    }
    
    
    
    
    
    
    
    public String getCd_cpf_terceirizada() {
        return cd_cpf_terceirizada;
    }
    
    
    
    
    public void setCd_cpf_terceirizada(String cd_cpf_terceirizada) {
        this.cd_cpf_terceirizada = cd_cpf_terceirizada;
    }
    
    
    
    
    public String getNm_contato() {
        return nm_contato;
    }
    
    
    
    
    public void setNm_contato(String nm_contato) {
        this.nm_contato = nm_contato;
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
        return nm_contato;
    }
}
