# 📱 SIGA - Sistema de Gestão Acadêmica (Aplicativo Mobile)

## ✨ Visão Geral do Projeto

O **SIGA** é um aplicativo mobile acadêmico, desenvolvido em Kotlin para a plataforma Android, com o objetivo de digitalizar, facilitar e centralizar o acesso a dados acadêmicos para alunos de uma instituição de ensino. Este aplicativo foi criado como parte de um **projeto interdisciplinar de desenvolvimento mobile**, demonstrando a integração de tecnologias front-end e comunicação com um backend robusto.

## 🚀 Funcionalidades Principais

O aplicativo oferece uma série de recursos para que o aluno possa acompanhar suas informações escolares de forma prática e rápida:

* **Autenticação Segura:**
    * **Tela de Login:** Permite que o aluno acesse o aplicativo utilizando CPF e senha, realizando a autenticação via API com token JWT.
    * **Redefinição de Senha (Simulado):** Funcionalidade para simular a solicitação de redefinição de senha, exibindo um diálogo para coleta de dados.

* **Navegação Intuitiva:**
    * **Menu Lateral Fixo:** Interface intuitiva que permite a navegação rápida entre as seções do aplicativo.
    * **Indicação Visual do Menu:** O botão do menu selecionado é visualmente destacado (mudança de cor de fundo e ícone) para indicar a tela atual.

* **Acompanhamento Acadêmico:**
    * **Tela Home:** Exibe as últimas notícias e comunicados da instituição (conteúdo estático para demonstração).
    * **Perfil do Aluno:** Apresenta todos os dados do aluno autenticado (Nome, CPF, RA, E-mail, Curso), consumidos diretamente da API.
    * **Notas:** Consulta detalhada das notas das disciplinas matriculadas, incluindo o cálculo da média.
    * **Faltas:** Visualização das faltas acumuladas por disciplina, com indicação do total de aulas e percentual.

* **Recursos e Serviços:**
    * **Documentos:** Simulação de solicitação de comprovante de matrícula com gerenciamento de status local.
    * **Calendário:** Redirecionamento para um link externo (PDF) para consulta do calendário escolar e horários de aula.

## 🛠️ Tecnologias e Ferramentas

O aplicativo foi construído utilizando as seguintes tecnologias e práticas:

* **Linguagem:** Kotlin
* **Plataforma:** Android SDK
* **Arquitetura:** Navegação baseada em Fragments
* **UI/UX:**
    * `ConstraintLayout` e `LinearLayout` para organização de layouts complexos.
    * `RecyclerView` para exibição de listas dinâmicas (Notas, Faltas, Notícias).
    * Ícones em `VectorDrawable` (SVG) para escalabilidade e fácil manipulação de cores.
    * Cores e estilos customizados definidos em `colors.xml` e `themes.xml`.
* **Comunicação com API:**
    * **Retrofit:** Cliente HTTP Typesafe para consumo da API RESTful.
    * **Gson:** Biblioteca para serialização/desserialização de objetos Kotlin/JSON.
    * **Kotlin Coroutines:** Para gerenciamento de operações assíncronas de rede, garantindo uma UI fluida.
* **Persistência Local:** `SharedPreferences` para armazenamento seguro de token JWT e dados de sessão/status.
* **Controle de Versão:** Git e GitHub.

## 🔗 Integração com a API (Backend)

O aplicativo se comunica com uma API RESTful desenvolvida em **Node.js, Express e MongoDB**, com foco em gerenciamento de alunos, cursos, matérias, notas, presença e autenticação JWT.

### Endpoints Consumidos:

* `POST /api/auth/login`: Realiza a autenticação do usuário e retorna um token JWT para acesso a rotas protegidas.
* `GET /api/student`: Busca os dados completos do aluno autenticado, incluindo informações do curso e detalhes das matérias matriculadas (notas e faltas), utilizando o token JWT no cabeçalho `Authorization`.
* `POST /api/student`: Utilizado internamente (via Postman/Insomnia) para o cadastro de novos alunos no sistema.

### Conexão em Ambiente de Desenvolvimento:

Para testar o aplicativo com o backend local, a ferramenta `ngrok` é utilizada para expor o servidor Node.js à internet, gerando uma URL HTTPS pública temporária. Essa necessidade surgiu especificamente durante os testes em um dispositivo físico, como o Poco X6, que não consegue acessar `localhost` diretamente. Essa URL é configurada na `BASE_URL` do aplicativo Android (`RetrofitInitializer.kt`).

## ⚙️ Instalação e Execução (Frontend)

Para colocar o aplicativo SIGA em funcionamento em seu ambiente de desenvolvimento:

### Pré-requisitos:

* **Android Studio:** Versão 2022.3.1 (Hedgehog) ou superior.
* **Android SDK:** Nível de API 31+ (Android 12) recomendado.
* **Java Development Kit (JDK):** Versão 17 ou superior.
* **Dispositivo Android Físico ou Emulador:** Com acesso à internet.

### Passos:

1.  **Clone o repositório do aplicativo:**
    ```bash
    git clone https://seu-repositorio-do-app/siga-app.git
    cd siga-app
    ```
2.  **Abra o projeto no Android Studio.**

3.  **Sincronize o Gradle:** O Android Studio deve pedir para sincronizar automaticamente. Caso contrário, vá em `File > Sync Project with Gradle Files`.

4.  **Certifique-se de que o backend da API está rodando:**
    * Navegue até a pasta `siga-backend` (do seu projeto de API).
    * Execute `npm install` e depois `npm start`.
    * Verifique se o MongoDB está ativo e acessível.

    * **4.1. Configuração de Rotas Protegidas em `studentRoutes.js`:**
        * Abra o arquivo `siga-backend/routes/studentRoutes.js`.
        * **Descomente a linha `router.use(auth);`** para aplicar o middleware de autenticação às rotas de estudante que exigem login. Certifique-se de que `router.post('/', studentController.createStudent);` venha antes dessa linha para que o cadastro não exija autenticação. Exemplo:
            ```javascript
            // siga-backend/routes/studentRoutes.js
            const auth = require('../middlewares/auth');
            // ...
            router.post('/', studentController.createStudent); // Cadastro não exige auth
            router.use(auth); // A partir daqui, as rotas exigem auth
            router.get('/', studentController.getStudentData);
            // ...
            ```

        **4.2. Configuração do Banco de Dados (População de Dados de Teste)**

        Para facilitar os testes e ter um ambiente pré-populado com dados de exemplo (curso, matérias e um aluno com senha hasheada), siga os passos abaixo para importar os dados para o MongoDB.

        Os arquivos JSON de dados de teste (`courses.json`, `subjects.json`, `users.json`) estão localizados na pasta `app/database_seed/` do seu repositório.

        1.  **Limpe as coleções existentes (OPCIONAL, mas recomendado para evitar conflitos de IDs):**
            * Abra o MongoDB Compass, conecte-se ao `siga_db`.
            * Para cada coleção (`courses`, `subjects`, `users`), clique nos três pontos ao lado do nome da coleção e selecione "Drop Collection" (para apagar tudo) ou "Delete All Documents".

        2.  **Importe os dados de teste:**
            * Para cada arquivo JSON localizado em `app/database_seed/`:
                * No MongoDB Compass, selecione o banco de dados `siga_db`.
                * Clique na coleção correspondente (`courses`, `subjects` ou `users`).
                * Clique no botão "Add Data" e selecione **"Import File"**.
                * Navegue e selecione o arquivo JSON apropriado (ex: `app/database_seed/courses.json`).
                * Em "File type", selecione **`JSON`**.
                * Clique em "Import".

        3.  **Credenciais do Aluno de Teste:**
            * Após a importação, o aluno de teste com o CPF `12345678909` e a senha `123` (já hasheada no banco de dados) estará disponível para login.
            
5.  **Configure o Ngrok (para testes com dispositivo físico):**
    * Baixe e autentique o ngrok (`ngrok authtoken SEU_TOKEN`).
    * Inicie o túnel: `ngrok http 3000`.
    * **Copie a URL HTTPS `Forwarding`** (ex: `https://abcd.ngrok-free.app`).

6.  **Atualize a `BASE_URL` no aplicativo:**
    * No Android Studio, abra `app/src/main/java/br.com.alura.orgs.api/RetrofitInitializer.kt`.
    * Substitua o valor de `BASE_URL` pela **URL HTTPS atual do ngrok** que você copiou, adicionando `/api/` no final:
        ```kotlin
        private val BASE_URL = "https://SUA_URL_NGROK_ATUAL.ngrok-free.app/api/"
        ```
    * Sincronize o Gradle novamente.

7.  **Limpe e Reconstrua o Projeto:**
    * Vá em `Build > Clean Project` e depois `Build > Rebuild Project`.

8.  **Desinstale qualquer versão anterior do aplicativo** do seu dispositivo/emulador.

9.  **Execute o aplicativo:** Conecte seu dispositivo Android ou inicie um emulador e clique no botão `Run` (triângulo verde) no Android Studio.

