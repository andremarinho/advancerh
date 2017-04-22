/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.advancerh.pagina;

import br.com.advancerh.util.Util;
import br.com.advancerh.vaga.Vaga;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andre
 */
public class Pagina {
    
    private String endereco = "http://www.advancerh.com.br/vagas.php";
    private BufferedReader pagina = null;
    private List<Vaga> vagas = new ArrayList<Vaga>();
    private StringBuilder sb = new StringBuilder();
    
    public Pagina(){
       
        carregaPagina(endereco);
        
    }
    
    
    private void carregaPagina(String endereco){
        
        try {
            URL url = new URL(endereco);
            
            this.pagina    = new BufferedReader(new InputStreamReader(url.openStream()));
            
            } catch (MalformedURLException ex) {
            Logger.getLogger(Pagina.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Pagina.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }
    
    private void carregaListaVaga() throws IOException{
        Util util = new Util();
        String inputLine;
        boolean titulo = false;
        String cargo=null;
        String email=null;

        boolean campoDescricao = false;

        while ((inputLine = pagina.readLine()) != null) {

            //Captura a descrição do cargo
            if (campoDescricao) {

                sb.append(inputLine);

                if (inputLine.contains("</section>")) {

                    //System.out.println(sb.toString());
                    campoDescricao = false;}
                
            }

            //Identificando o cargo e setando a descrição
            if (titulo) {
                if (!inputLine.contains("A ADVANCERH INFORMA: NOTA DE REPÚDIO")) {

                    inputLine.trim();
                    inputLine.replace(" ", "");
                    inputLine.replace("<p><strong>::", "");
                    inputLine.replace("::</strong></p>", "");
                    inputLine = inputLine.subSequence(0, inputLine.length() - 15).toString();
                    inputLine = inputLine.subSequence(21, inputLine.length()).toString();
                    cargo = util.getCargo(inputLine);
                    System.out.println(cargo);
                    titulo = false;
                    campoDescricao = true;

                } else {
                    titulo = false;
                }

            }

            //Identifica que a proxima linha é o cargo
            if (inputLine.contains("<section id=\"\">")) {
                titulo = true;

            }

            if (inputLine.contains("<p><span style=\"font-weight: bold; color:#000000;\">") && inputLine.contains("Inserida")) {
                Date data = new Date();
                String pattern = "dd/MM/yyyy";
                SimpleDateFormat formato = new SimpleDateFormat(pattern);
                
            }

            if (inputLine.contains("Candidate-se")) {
                Date data = new Date();
                String pattern = "dd/MM/yyyy";
                SimpleDateFormat formato = new SimpleDateFormat(pattern);
                
            }

            if (inputLine.contains("@")) {

                email = util.getEmail(sb.toString());
                System.out.println(email);
            }
            
            //Finalizando a vaga
            if(inputLine.contains("</section>")){
                    Vaga vaga = new Vaga();
                    vaga.setDescricao(util.getDescricao(sb.toString()));
                    System.out.println(vaga.getDescricao());
                    vaga.setCargo(cargo);
                    vaga.setDataCadastro(util.toDate(util.getDataInserida(vaga.getDescricao())));
                    vaga.setDataFim(util.toDate(util.getDataFim(vaga.getDescricao())));
                    System.out.println(util.getDataFim(vaga.getDescricao()));
                    System.out.println(util.getDataInserida(vaga.getDescricao()));
                    
                    vaga.setEmail(email);
                    vagas.add(vaga);
                    sb.delete(0, sb.length());
                    /*
                    qtd = qtd + 1;
                    System.out.println("Vaga cadastrada  foi " + vaga.getCargo() + " cargo numero: " + qtd);
                    this.descricao = null;
                    this.cargo = null;
                    this.dataInserida = null;
                    this.dataFim = null;
                     */ 
            }

        }

        //return vagas;
    }
    
    
}
