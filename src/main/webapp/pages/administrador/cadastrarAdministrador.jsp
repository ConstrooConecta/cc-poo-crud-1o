<!DOCTYPE html>
<html lang="pt-BR"> <!-- Define o idioma da página -->
<head>
    <meta charset="UTF-8"> <!-- Define a codificação de caracteres -->
    <meta name="viewport" content="width=device-width, initial-scale=1.0"> <!-- Configura responsividade -->
    <title>Cadastrar Administrador</title> <!-- Título da página -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/cascading-style-sheets/cadastrarAdministrador.css"> <!-- Importa o CSS -->
</head>
<body>

<header>
    <div class="titulo-constroo"> <!-- Container para título e logo -->
        <h1>Constroo</h1> <!-- Nome da aplicação -->
        <img src="${pageContext.request.contextPath}/imagens/LogoVersaoMenor.svg" alt="Logo do app Constroo"> <!-- Logo da aplicação -->
    </div>
</header>

<main>
    <h1 id="titulo-Cadastrar-Administrador">Cadastrar Administrador</h1> <!-- Título da seção de cadastro -->
    <form action="${pageContext.request.contextPath}/InserirAdministradorServlet" method="post"> <!-- Formulário de cadastro -->

        <h2 class="subtitulo-forms">Complete as informações abaixo</h2> <!-- Subtítulo para orientações -->

        <div class="infos-linha"> <!-- Container para informações do administrador -->

            <label for="nome">Nome</label> <!-- Rótulo para campo de nome -->
            <input type="text" name="nome" id="nome" placeholder="Matheus" required> <!-- Campo para nome, obrigatório -->

            <label for="email">Email</label> <!-- Rótulo para campo de email -->
            <input type="email" name="email" id="email" placeholder="exemplo@email.com" required> <!-- Campo para email, obrigatório -->

            <label for="senha">Senha</label> <!-- Rótulo para campo de senha -->
            <input type="password" name="senha" id="senha" placeholder="senha123" required> <!-- Campo para senha, obrigatório -->

            <input type="hidden" name="id" value="${id}"> <!-- Campo oculto para ID, se necessário -->

            <% if (request.getAttribute("retorno") == "erro") { %>
            <div class="error-message"> <!-- Container para mensagens de erro -->
                <p><%= request.getAttribute("mensagem") %></p> <!-- Mensagem de erro, se existir -->
            </div>
            <% } %>

            <input type="submit" id="buttonCadastrar" value="Cadastrar"> <!-- Botão para submeter o formulário -->
        </div>
    </form>
</main>

</body>
</html>
