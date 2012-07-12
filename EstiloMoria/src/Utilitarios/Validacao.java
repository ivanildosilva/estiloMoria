/*
 * Validacao.java
 *
 * Created on 20 de Setembro de 2011, 14:10
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package Utilitarios;

/**
 *
 * @author Familia-Silva
 */
public class Validacao {
    
    public boolean calculaCPF(String cpfNum) {
        int[] cpf = new int[cpfNum.length()]; //define o valor com o tamanho da  string
        int resultP = 0;
        int resultS = 0;
        
        //converte a string para um array de integer
        for (int i = 0; i < cpf.length; i++) {
            cpf[i] = Integer.parseInt(cpfNum.substring(i, i + 1));
            if(cpfNum.equals( "11111111111")||cpfNum.equals( "22222222222")
            ||cpfNum.equals( "33333333333")||cpfNum.equals( "44444444444")
            ||cpfNum.equals( "55555555555")||cpfNum.equals( "66666666666")
            ||cpfNum.equals( "77777777777")||cpfNum.equals( "88888888888")
            ||cpfNum.equals( "99999999999")||cpfNum.equals( "00000000000")){
                return false;
                
            }
        }
        //calcula o primeiro número(DIV) do cpf
        for (int i = 0; i < 9; i++) {
            resultP += cpf[i] * (i + 1);
        }
        int divP = resultP % 11;
        
        //se o resultado for diferente ao 10º digito do cpf retorna falso
        if (divP != cpf[9]) {
            return false;
        } else {
            //calcula o segundo número(DIV) do cpf
            for (int i = 0; i < 10; i++) {
                resultS += cpf[i] * (i);
            }
            int divS = resultS % 11;
            
            //se o resultado for diferente ao 11º digito do cpf retorna falso
            if (divS != cpf[10]) {
                return false;
            }
            
            
        }
        
        
        //se tudo estiver ok retorna verdadeiro
        return true;
    }//fim do calcular cpf ==================================================
    
    public boolean calculaCNPJ(String cnpjNum) {
        int[] cnpj = new int[cnpjNum.length()];
        int resultP = 0;
        int resultS = 0;
        int divP = 0;
        int divS = 0;
        
        //converte string para um array de integer
        for (int i = 0; i < cnpjNum.length(); i++) {
            cnpj[i] = Integer.parseInt(cnpjNum.substring(i, i + 1));
            if(cnpjNum.equals("00000000000000")){
                return false;
            }
        }
        
        int j = 6;
        //calcula o primeiro div
        for (int i = 0; i < 12; i++) {
            resultP += cnpj[i] * j;
            
            j++;
            if (j > 9) {
                j = 2;
            }
        }
        divP = resultP % 11;
        
        if (divP != cnpj[12]) {
            return false;
        } else {
            j = 5;
            //calcula o segundo div
            for (int i = 0; i < 13; i++) {
                resultS += cnpj[i] * j;
                
                j++;
                if (j > 9) {
                    j = 2;
                }
            }
            divS = resultS % 11;
            
            if (divS != cnpj[13])
                return false;
        }
        return true;
    }//fim do calcular CNPJ
    // ==================================================
    
    public static void main(String arg[]) {
        
        Validacao v = new Validacao();
        System.out.println(v.calculaCPF("26015490845"));
        
    }
    
    {
        
    }
    
}