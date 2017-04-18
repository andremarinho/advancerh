/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.advancerh.util;


import br.com.advancerh.vaga.Vaga;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author Andre
 */
public class LoadPage {

    private boolean titulo = false;
    private List<Vaga> vagas = new ArrayList<>();
    private Date dataInserida;
    private Date dataFim;
    private String cargo;
    private String descricao;
    private String email;
    private int qtd = 0;
    private StringBuilder sb = new StringBuilder();

    public List<Vaga> getPage(URL url, File file) throws IOException {
        BufferedReader in
                = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
        
        Util util = new Util();
        String inputLine;

        boolean campoDescricao = false;

        while ((inputLine = in.readLine()) != null) {

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

                    
                    inputLine = inputLine.subSequence(0, inputLine.length() - 15).toString();
                    inputLine = inputLine.subSequence(21, inputLine.length()).toString();
                    this.cargo = util.getCargo(inputLine);
                    System.out.println(this.cargo);
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
            
            //capturando o e-mail
            if(inputLine.contains("@")){
                email = util.getEmail(inputLine);
            }
                 

            //Finalizando a vaga
            if(inputLine.contains("</section>")){
                    Vaga vaga = new Vaga();
                    vaga.setDescricao(util.getDescricao(sb.toString()));
                    System.out.println(vaga.getDescricao());
                    vaga.setCargo(this.cargo);
                    vaga.setDataCadastro(util.toDate(util.getDataInserida(vaga.getDescricao())));
                    vaga.setDataFim(util.toDate(util.getDataFim(vaga.getDescricao())));
                    System.out.println(util.getDataFim(vaga.getDescricao()));
                    System.out.println(util.getDataInserida(vaga.getDescricao()));
                    
                    vaga.setEmail(email);
                    vagas.add(vaga);
                    sb.delete(0, sb.length());

                    qtd = qtd + 1;
                    System.out.println("Vaga cadastrada  foi " + vaga.getCargo() + " cargo numero: " + qtd);
                    this.descricao = null;
                    this.cargo = null;
                    this.dataInserida = null;
                    this.dataFim = null;
                      
            }

        }
        
        vagas =util.ordemLista(vagas);
        
        return vagas;
    }

}
