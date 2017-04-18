/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.advancerh.util;

import br.com.advancerh.vaga.Vaga;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Andre
 */
public class Util {
    
    public String getDataInserida(String data){
        StringBuilder sb = new StringBuilder();
        String dataFinal;
        
        for(int i=data.length()-51;i<data.length();i++){
           char c = data.charAt(i);
           sb.append(c);
        }
        dataFinal = sb.toString();
        
        dataFinal = dataFinal.substring(0, 10);
        
        return dataFinal;
    }
    
    public String getDataFim(String data){
        StringBuilder sb = new StringBuilder();
        String dataFinal;
        
        
        for(int i=data.length()-14;i<data.length();i++){
           char c = data.charAt(i);
           sb.append(c);
        }
        
        return sb.toString();
        
        
    }
    
    public String getEmail(String linha){
        int inicio=0;
        int arroba=0;
        char c;
        String email;
        StringBuilder sb = new StringBuilder();
        
        for(int i = 0;i<linha.length();i++){
            c=linha.charAt(i);
            
            if(c=='@'){
                arroba = i;
                //break;
            }
        }
        
        for(int i = arroba;i>0;i--){
           c = linha.charAt(i);
           if((c=='>')||(c==':')||(c==' ')){
               inicio = i +1;
               break;
           }
        }
        
        for(int i = inicio;i<linha.length();i++){
            c = linha.charAt(i);
            if((c=='<')||(c=='"')||(Character.isSpaceChar(c))){
                break;
            }else{
                sb.append(c);
            }
            
        }
        
        email = sb.toString();
       
        return email;
    }
    
    
    
    public String getDescricao(String linha){
     boolean campoDesc = false;
     StringBuilder sb = new StringBuilder();
     
     for(int i = 0;i<linha.length();i++){
         char c = linha.charAt(i);
         if(c=='<'){
             campoDesc = false;
         }
         
         if(c=='>' || campoDesc){
             campoDesc = true;
             
             if(!(c=='>')){
                 sb.append(c);
             }
         }
     }
        
        return sb.toString();
    }
    
    
    public String getCargo(String linha){
        linha = linha.trim();
        
        return linha;
    }
    
    public Date toDate(String string){
                Date data = new Date();
                String pattern = "dd/MM/yyyy";
                SimpleDateFormat formato = new SimpleDateFormat(pattern);
                 try{
                    data = formato.parse(string);
                }catch(Exception e ){
                    
                }
                 return data;
    }
    
    
    public List<Vaga> ordemLista(List<Vaga> vagas){
       
        Vaga vaga;
        
       for(int i=0;i<vagas.size();i++){
           
           for(int b=0;b<vagas.size();b++){
               
               
               if(vagas.get(i).getDataCadastro().getTime()>vagas.get(b).getDataCadastro().getTime()){
                  vaga = vagas.get(i);
                  vagas.set(i, vagas.get(b));
                  vagas.set(b, vaga);
               }
           }
       } 
       
       return vagas;
    }
    
}
