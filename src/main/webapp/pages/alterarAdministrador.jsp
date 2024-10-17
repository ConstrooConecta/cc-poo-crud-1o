<%@ page contentType="text/html;charset=UTF-8" language="java" %> <!-- Definição do tipo de conteúdo e linguagem -->
<html>
<head>
    <title>Title</title> <!-- Título da página -->
</head>
<body>
<link rel="stylesheet" href="${pageContext.request.contextPath}/cascading-style-sheets/cadastrarUsuario.css"> <!-- Importação do CSS -->
<div id="navbar"> <!-- Navegação -->
    <p id="navbar-Constroo">Constroo</p> <!-- Nome da marca -->
    <div id="navbar-line"></div> <!-- Linha de separação -->
</div>
<h1 id="titulo-Cadastrar-Usuario">Alterar</h1> <!-- Título principal -->
<form action="AlterarAdministradorServlet" method="post"> <!-- Formulário para alteração -->
    <h2 class="subtitulo-forms"></h2> <!-- Subtítulo -->
    <div class="infos-linha"> <!-- Linha de informações -->
        <label for="Nome">Nome</label> <!-- Rótulo para nome -->
        <input type="text" name="nome" id="nome" value=<%=request.getAttribute("nome") %>> <!-- Campo de entrada para nome -->
        <label for="email">Email</label> <!-- Rótulo para email -->
        <input type="email" name="email" id="email" value=<%=request.getAttribute("email") %>> <!-- Campo de entrada para email -->
        <label for="senha">Senha</label> <!-- Rótulo para senha -->
        <input type="password" name="senha" id="senha" value=<%=request.getAttribute("senha") %>> <!-- Campo de entrada para senha -->
        <input type="hidden" name="id" value=<%=request.getAttribute("id") %>> <!-- Campo oculto para ID -->
    </div>
    <input type="submit" id="buttonCadastrar"> <!-- Botão de envio -->
</form>
</body>
</html>
