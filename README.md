# Docbank

Sistema de gerenciamento de documentos PDF e links, com controle de acesso baseado em níveis de permissão e privilégios.

## 👤 Responsável

**Mateus Galvão de Araújo**

## 🚀 Status do Projeto

**Em desenvolvimento** 🛠️

## 💻 Tecnologias

Em seu estado atual, o projeto foi desenvolvido utilizando **Java**, **SQL** e tem sua build feita via **Maven**.
O projeto também implementa as dependências mysql-connector-j e junit-jupiter para conectividade com banco de dados e realização de testes.

À medida que o projeto evoluir e novas tecnologias forem incorporadas, este documento será atualizado para refletir as ferramentas e tecnologias utilizadas.

## 📝 Sobre o Projeto

O **Docbank** é um sistema de biblioteca e revisão de documentos baseado em diferentes níveis de permissão e privilégios de acesso.

A aplicação foi projetada para oferecer uma experiência organizada e intuitiva, permitindo:

* Criação e gerenciamento de contas;
* Definição de cargos e níveis de acesso;
* Criação de bibliotecas pessoais;
* Submissão de documentos para revisão;
* Revisão e gerenciamento de documentos;
* Busca e visualização de documentos aprovados.

O cadastro de uma conta **não é obrigatório** para acessar documentos que já tenham sido aprovados pelos gestores. Entretanto, usuários autenticados possuem acesso a recursos adicionais, como a submissão de artigos para revisão e o gerenciamento de uma biblioteca pessoal, na qual podem vincular documentos de seu interesse.

## ✨ Funcionalidades

### 🔐 Controle de Permissões

O Docbank possui diferentes níveis de acesso, cada um com permissões específicas.

#### Administrador

Possui acesso completo ao sistema, incluindo:

* Visualização, submissão, revisão, edição e remoção de documentos;
* Gerenciamento de contas de usuários;
* Alteração de cargos e níveis de acesso;
* Suspensão e exclusão de usuários;
* Gerenciamento de sua biblioteca pessoal.

#### Moderador

Possui permissões para:

* Visualizar, submeter, revisar, editar e remover documentos;
* Gerenciar sua biblioteca pessoal.

#### Usuário Comum

Possui acesso a:

* Visualização de documentos aprovados;
* Submissão de novos documentos para revisão;
* Gerenciamento de sua biblioteca pessoal.

#### Visitante

Usuário sem conta ou sem autenticação, com acesso a:

* Visualização de documentos aprovados.

### 👤 Cadastro de Usuários

Permite o registro de novos usuários por meio do preenchimento das seguintes informações:

* Nome;
* E-mail;
* Senha.

### ⚙️ Gerenciamento de Usuários

Funcionalidade exclusiva do **Administrador**, permitindo:

* Atribuir ou alterar cargos entre **Administrador**, **Moderador** e **Usuário**;
* Suspender contas;
* Excluir contas.

### 📚 Gerenciamento de Artigos

Disponível para **Administradores e Moderadores**, permitindo:

* Adicionar documentos;
* Editar títulos e tópicos de documentos;
* Remover documentos;

### 🔎 Submissão e Busca

#### Submissão

Usuários autenticados podem enviar documentos para a fila de revisão, onde serão avaliados antes de serem disponibilizados publicamente.

#### Busca e Exibição

O sistema possui um mecanismo de pesquisa dinâmica que permite localizar documentos por meio de termos-chave.

## ⚙️ Configuração

Para configurar o Docbank, siga os passos abaixo para preparar o banco de dados, definir as credenciais e estabelecer a conexão com o servidor MySQL.

### 1. 🗄️ Criação das Tabelas

Na pasta `SQL/`, localize o arquivo:

```text
ScriptDocbankMYSQL.sql
```

Execute o script em seu gerenciador de banco de dados MySQL. Ele será responsável por criar automaticamente as tabelas e colunas necessárias para o funcionamento da aplicação.

### 2. 🔑 Chave-Mestra e Privilégios Administrativos

Dentro da pasta `Aplicativo/`, localize o arquivo:

```text
config.txt
```

Nesse arquivo, é possível definir uma **chave-mestra**, utilizada para habilitar a criação de contas com privilégios administrativos.

> **Atenção:** mantenha essa chave protegida e evite compartilhá-la publicamente.

### 3. 🔌 Conexão com o Banco de Dados

Para conectar a aplicação ao seu servidor MySQL, edite a classe responsável pela conexão:

```text
Aplicativo/src/docbank/infra/database/Conexao.java
```

Altere o valor da `String` de conexão denominada `PASS` e informe a senha necessária para autenticar o acesso ao banco de dados.

## ▶️ Execução

Após concluir as etapas de configuração, a aplicação estará preparada para ser executada e estabelecer comunicação com o banco de dados.

Certifique-se de que:

* O servidor MySQL esteja em execução;
* O banco de dados tenha sido configurado corretamente;
* As credenciais de acesso estejam corretas;

---

## 📌 Observações

O projeto encontra-se em **desenvolvimento**. Portanto, funcionalidades, tecnologias e instruções de configuração poderão ser modificadas conforme o desenvolvimento do Docbank avançar.

---

**Docbank** — organização, revisão e gerenciamento de documentos em um único sistema.
