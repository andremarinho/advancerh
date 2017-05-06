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
                
<img src="https://www.dropbox.com/s/0antfewkusnqxcy/advanrh.jpg?dl=0" height="42" width="42"/>
