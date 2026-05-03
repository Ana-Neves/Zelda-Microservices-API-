# User API - Projeto final ☕🦥🍃

## Descrição do projeto

O *User-API* é um projeto desenvolvido ao final do programa *Geração Caldeira 2024*, este projeto tem como objetivo a
construção de uma API RESTful utilizando as tecnologias mais recentes, como **Java 21** e **Spring Boot**, permitindo a
gestão completa de perfis de usuários dentro do sistema. Através dela, é possível realizar as seguintes operações:
  
- Criar novos usuários: Criar perfis completos com todas as informações necessárias;  
- Listar todos os usuários: Obter uma lista detalhada de todos os usuários que já foram cadastrados;  
- Buscar um usuário específico: Encontrar rapidamente um usuário por meio de seu identificador único;  
- Atualizar as informações dos usuários: Atualizar os dados de um usuário existente como nome, sobrenome e outras informações;  
- Excluir um usuário: Remover permanentemente um usuário do sistema.

---
## Ambiente de Desenvolvimento e suas Dependências

O projeto foi desenvolvido utilizando as seguintes ferramentas e tecnologias para garantir um desenvolvimento eficiente e
uma facilidade de uso:  
- *IntelliJ IDEA:*  
  - Uma IDE robusta e popular para desenvolvimento Java, oferecendo recursos avançados como de autocompletar, refatoração e depuração;  


- *Java versão 21:*
  - Utilizando a mais recente versão do Java para aproveitarmos as novas funcionalidades e melhorias de desempenho;  


- *Spring Boot:*
  - Uma ferramenta de desenvolvimento de software que simplifica e acelera a criação de aplicações Java, utilizamos a versão 3.3.5;  


- *Maven:*
  - Uma ferramenta de gerenciamento de build, simplificando a construção, os testes e a distribuição do projeto. Em nosso projeto,
foi adicionado junto quando criamos ele no [Spring initializr](https://start.spring.io/);  


- *Lombok:*
  - Um plugin que simplifica o código eliminando a necessidade de criar manualmente métodos como getters, setters e construtores;  


- *Spring Boot Web:*
  - Um framework que permite a criação de APIs RESTful e o gerenciamento de interações HTTP;  


- *Spring Boot Actuator:*
  - Uma ferramenta essencial para monitorar a saúde da aplicação e coletar métricas, auxiliando na identificação e resolução de problemas.  

---
## Guia de execução do projeto

Siga os passos abaixo para configurar e executar o projeto em sua própria máquina:

### Pré-requisitos
Certifique-se de que você possui os seguintes itens em seu ambiente:
1. [Java JDK 21](https://www.oracle.com/br/java/technologies/downloads/#java21)  
2. [PostgreSQL](https://www.postgresql.org/)  
3. Uma IDE com suporte para Spring Boot como [IntelliJ IDEA](https://www.jetbrains.com/idea/download/)  
  
### Para utilizar este sistema siga o passo a passo:
- Clone este repositório com sua chave HTTPS ou a SSH (que é a mais recomendável);  
    `git clone https://github.com/GabrielFleischmann/gc24-SeBeberNaoCode.git`  


- Instale as dependências executando o maven que está no arquivo pom.xml;  


- Execute o projeto em uma IDE de sua preferência, utilizamos o IntelliJ IDEA durante todo o processo;  


- E por último realize um teste da API em uma das ferramentas disponíveis, como o Swagger ou o Postman.
