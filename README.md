# BANTADS - Microserviço de Clientes

Este repositório contém o microserviço responsável pela gestão de clientes no sistema BANTADS. Ele lida com o ciclo de vida do cliente, incluindo o cadastro de dados pessoais e endereços, além de se integrar ao fluxo de orquestração SAGA para processos de auto-cadastro e atualizações de perfil.

## Tecnologias Utilizadas

O projeto foi construído utilizando as seguintes tecnologias:

* **Java 17+** (JDK)
* **Spring Boot 3.x**
    * Spring Data JPA (Persistência de dados)
    * Spring Web (Interface REST)
    * Spring Validation (Validação de dados de entrada)
* **PostgreSQL** (Base de dados relacional)
* **ModelMapper** (Mapeamento de entidades para DTOs)
* **Maven** (Gerenciador de dependências)

## Estrutura do Projeto

A organização dos pacotes em `br.net.dac.cliente` segue o padrão de camadas clássico para microserviços:

* **`model/`**: Entidades JPA representando as tabelas `Cliente` e `Endereco`.
* **`repository/`**: Interfaces de abstração do Spring Data para persistência.
* **`rest/`**: Controllers que expõem os endpoints da API (REST).
* **`services/`**: Camada de regras de negócio.
* **`dto/`**: Objetos para transferência de dados, garantindo o desacoplamento da API com o banco de dados.

### Pré-requisitos
* Banco de Dados PostgreSQL ativo.
* Maven 3.x+.
* JDK 17+.

Este projeto é parte da disciplina de Desenvolvimento de Aplicações Corporativas (DAC).
