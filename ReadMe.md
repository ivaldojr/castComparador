# CastComparador
Aplicação desenvolvida para seleção na cast Group.
	
## Assignment

* Provide 2 http endpoints that accepts JSON base64 encoded binary data on both endpoints
   
    ```<host>/v1/diff/<ID>/left``` and ```<host>/v1/diff/<ID>/right```
     
* The provided data needs to be diff-ed and the results shall be available on a third end
point.

    ```<host>/v1/diff/<ID>```
    
* The results shall provide the following info in JSON format
    * If equal return that
    * If not of equal size just return that
    * If of same size provide insight in where the diffs are, actual diffs are not needed.
        * So mainly offsets + length in the data
* Make assumptions in the implementation explicit, choices are good but need to be
communicated assumptions in the implementation explicit, choices are good but need to be communicated

## Technologies

* Spring Boot - Framework
* Tomcat - Server
* Maven - Dependency Management

## Executando

Atualizar as bibliotecas maven.

Pode ser executado em uma IDE de desenvolvimento, de preferencia Eclipse ou Spring Tools.

## Test

Teste realizado com JUnit por cada classe.

