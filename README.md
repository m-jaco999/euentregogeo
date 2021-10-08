# EuEntrego Geo

Desafio euEntrego, criar uma api que recebe uma requisição e realiza outra requisição para a api do Google Maps.

<h2>Tutorial</h2>

Importar o projeto com a dependêcia Maven + uma versão do Java. 

Nesse projeto, utilizei a versão 11 do JAVA (O java precisa estar instalado e configurado corretamente...)

Uma vez com as dependêcias setadas -> basta ir até geolocation\src\main\java\com\desafio\geolocation\GeolocationApplication.java e dar run na mesma caso utilize uma IDEA

Via console, basta entrar na pasta raiz do projeto e executar o comando : **./mvnw spring-boot:run**

<h2>Realizando as chamadas</h2>

O sistema realiza a conta para os primeiros dois endereços, caso o JSON de envio possua mais endereços, os mesmos serão pesquisados porém sem nenhum retorno...

URL para chamada: http://localhost:8080/


Metódo HTML : POST


JSON exemplo:

{

"enderecos": "Av. Rio Branco, 1 - Centro, Rio de Janeiro - RJ, 20090-003; Praça Mal. Âncora, 122 - Centro, Rio de Janeiro - RJ, 20021-200; Rua 19 de Fevereiro, 34 - Botafogo, Rio de Janeiro - RJ, 22280-030"  

}

Retorno esperado: 

{

"distancia:": "O endereco 1 possui 0.011921952761606592 de distancia do endereço 2"
    
}

O valor retornado, é a menor distância entre os dois pontos em uma linha reta, não está convertido para graus, metros, km ou milhas
