# Projeto Encurtador de URLs

## Visão Geral
O projeto do Encurtador de URLs visa fornecer uma solução para encurtar URLs longas, tornando-as mais fáceis de compartilhar e gerenciar. Ele oferece endpoints RESTful para encurtar URLs e redirecionar usuários para URLs originais com base nos identificadores encurtados.

## Funcionalidades Principais
1. **Encurtamento de URLs:** Os usuários podem enviar uma URL longa para o serviço, que retornará um identificador único de URL encurtada.
2. **Redirecionamento:** Ao acessar a URL encurtada, os usuários são redirecionados para a URL original correspondente.
3. **Estatísticas de Acesso:** O sistema mantém estatísticas de acesso para URLs encurtadas, permitindo a análise do uso e da popularidade de cada URL.

## Tecnologias Utilizadas
- **Java:** A linguagem de programação principal utilizada para desenvolver a lógica de negócios e a lógica do back-end.
- **Spring Boot:** Um framework Java amplamente utilizado para criar aplicativos Java, facilitando a configuração e o desenvolvimento de aplicativos de microsserviços.
- **Swagger/OpenAPI:** Utilizado para documentar e expor os endpoints da API RESTful do serviço de encurtamento de URLs.
- **JUnit e Mockito:** Para testes unitários e de integração, garantindo a qualidade e a confiabilidade do código.
- **Bucket4j:** Uma biblioteca Java para limitação de taxa, utilizada para controlar a quantidade de solicitações recebidas por determinado período de tempo.

## Estrutura do Projeto
O projeto segue uma estrutura baseada em pacotes, utilizando a arquitetura MVC (Model-View-Controller) para organizar o código fonte. Aqui está uma visão geral da estrutura do projeto:
- **application:** Contém as classes responsáveis pela lógica de aplicação, como serviços e DTOs (Data Transfer Objects).
- **domain:** Classes que representam o modelo de domínio do aplicativo, incluindo entidades e objetos de valor.
- **infrastructure:** Classes de infraestrutura, como configurações, filtros e utilitários.

## Rodando o Projeto
Antes de rodar o projeto, certifique-se de ter o Docker instalado na sua máquina. Para executar o projeto, siga estas etapas:
1. No terminal, navegue até o diretório raiz do projeto.
2. Execute o comando `docker-compose up`.
3. Aguarde até que o Docker inicie os contêineres necessários.
4. Uma vez que os contêineres estejam em execução, execute o projeto via IDE.



