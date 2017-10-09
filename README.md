# AGENDAMENTO

Frameworks e linguagens utilizados:

Java 8;
Spring MVC;
Spring Data;
Spring Boot;
Maven (Para gerenciar dependências e build);
Log4j para o controle de logs;
Banco H2 para persistência em memoria dos dados (Não necessário nenhuma instalação para sua utilização);
JUnit (Testes unitários e testes de integração);
Design Patterns Utilizado: Chain of Responsibility para determinar a prioridade do calculo das taxas de acordo com as regras de cada cálculo. Isso proporciona uma clareza maior para cada classe, que fica somente com a reponsabilidade de calcular o seu tipo de taxa.
Não sendo necessário informar o tipo de taxa que será calculada.

Para testar esta aplicação:

Clonar o repositório: git clone https://github.com/adrsilva100/agendamento.git
Executar o comando na pasta raiz do repositorio: mvn clean install jetty:run
Json de exemplo para envio de um agendamento:
{"contaOrigem":"578416", "contaDestino" : "987451", "valorTransferencia" : "550.60", "dataTransferencia" : "15/10/2017"}

URL para realizar o agendamento: http://localhost:8080/ metodo: POST
URL para realizar a listagem dos agendamentos: http://localhost:8080/ metodo: GET
