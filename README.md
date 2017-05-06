# advancerh
System that demonstrates how to scan an internet page in search of information. Written in Java Swing. The page used as a demonstration is a company that shows job vacancies, the program scans the page and captures the vacancies.


Advancerh features:

<ul>

  <li>Java Swing</li>
  <li>Use class URL and BufferedReader to capture the content of the webpage</li>

<ul>

<h3>Insert the url</h3>
In package br.com.advancerh class pagina.

String endereco = "http://www.advancerh.com.br/vagas.php";

<h3>Create attribute BufferedReader</h3>
Will receive the content of the webpage

private BufferedReader pagina = null;

<h3>Receiving the content of the webage</h3>

URL url = new URL(endereco);
            
this.pagina  = new BufferedReader(new InputStreamReader(url.openStream()));


# advancerh

O sistema demonstra como escanear uma pagina da internet. Escrito em java usando a biblioteca gráfica Swing, desenvolvido com uso da  IDE NETBEANS. A pagina usada para demonstração é de uma empresa que disponibiliza vagas de trabalho, então o sistema varre a pagina e captura as vagas. 

Advancerh caracteristicas:

<ul>

  <li>Java</li>
  <li> Biblioteca gráfica Swing</li>
  <li>Usando a classe URL e BufferedReader para capturar o conteúdo da webpage</li>

<ul>

<h3>Inserindo a Url</h3>
No pacote br.com.advancerh tem a classe pagina.

String endereco = "http://www.advancerh.com.br/vagas.php";

<h3>Criando o atributo BufferedReader</h3>
Will receive the content of the webpage

private BufferedReader pagina = null;

<h3>Recebendo o conteúdo da webpage</h3>

URL url = new URL(endereco);
            
this.pagina  = new BufferedReader(new InputStreamReader(url.openStream()));
                

