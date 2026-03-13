# Docbank
Um aplicativo de gerenciamento de arquivos PDF e links com sistema de permissão integrado.

👤 Responsáveis

• Mateus Galvão

🚀 Status

• Em desenvolvimento 🛠️

💻 Tecnologias Aplicadas

• Este projeto, em seu estado atual, foi inteiramente desenvolvido utilizando as linguagens Java e SQL.
Com o seu amadurecimento e decorrentes modificações, este arquivo passará por atualizações que refletirão as novas tecnologias aplicadas.

📝 Apresentação

• O Docbank propõe um sistema de biblioteca e revisão de documentos baseado em níveis de permissão e privilégios.
A aplicação proporciona uma experiência organizada e intuitiva, permitindo a criação de contas, definição de cargos, criação de bibliotecas pessoais exclusivas e um fluxo completo de revisão de documentos.
O cadastro de conta não é obrigatório para acessar documentos já aprovados pelos gestores, mas o registro oferece uma experiência mais completa: o usuário logado pode submeter artigos para revisão e gerenciar sua própria biblioteca, vinculando seus documentos favoritos à sua conta.

✨ Funcionalidades

1. Permissões de Usuários

• Administrador:

- Acesso total: visualização, submissão, revisão, edição e remoção de documentos (em revisão ou aprovados).

- Gerenciamento de contas: alteração de cargos, suspensão e exclusão de usuários.

- Biblioteca pessoal vinculada à conta.

• Moderadores:

- Acesso à visualização, submissão, revisão, edição e remoção de documentos.

- Biblioteca pessoal vinculada à conta.

• Usuário Comum:

- Visualização de documentos aprovados e submissão de novos arquivos para revisão.

- Biblioteca pessoal vinculada à conta.

• Visitante (Sem conta):

- Acesso apenas à visualização de documentos aprovados.

2. Cadastro de Usuário

• Permite o registro de novos usuários por meio do preenchimento de e-mail, nome e senha.

3. Gerenciamento de Usuários

• Funcionalidade exclusiva do Administrador, permitindo atribuir cargos (Administrador, Moderador ou Usuário), além de suspender ou excluir contas.

4. Gerenciamento de Artigos

• Acessível a Administradores e Moderadores, permite adicionar, editar e remover documentos e links de referência do banco de dados.

5. Submissão e Busca

• Submissão: Permite que usuários enviem documentos para a fila de revisão.

• Busca e Exibição: Mecanismo de pesquisa dinâmica para localização de documentos através de termos-chave.

⚙️ Configurações Necessárias

Siga os passos abaixo para configurar as tabelas, credenciais e o acesso ao banco de dados:

1. Criação das Tabelas

• Na pasta SQL/, localize o arquivo ScriptDocbankMYSQL.sql. Execute este script no seu gerenciador de banco de dados para criar automaticamente as tabelas e colunas necessárias.

2. Chave-Mestra e Privilégios Administrativos

• Dentro da pasta Docbank, localize o arquivo config.txt. Nele, você poderá definir uma chave-mestra para habilitar a criação de contas com privilégios administrativos.

3. Conexão com o Banco de Dados

• Para conectar o aplicativo ao seu servidor, edite a classe de conexão:

• Caminho: Docbank/src/docbank/app/sql/Conexao.java

• O que fazer? Altere o valor da String de conexão e defina a senha necessária para validar o acesso.

E isso é tudo! Feitos esses passos, a aplicação estará pronta para rodar e se comunicar com o banco de dados.
