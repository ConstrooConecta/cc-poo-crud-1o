<%@ page contentType="text/html;charset=UTF-8" language="java" %> <!-- Definição do tipo de conteúdo e linguagem -->
<%@ page errorPage="../ErrorPage.jsp" %>
<html>
<head>
    <title>Deletar Plano Ativação pelo ID</title> <!-- Título da página -->
</head>
<body>
<link rel="stylesheet" href="${pageContext.request.contextPath}/cascading-style-sheets/id.css">
<header>
    <div class="titulo-constroo">
        <h1>Constroo</h1>
        <img src="${pageContext.request.contextPath}/imagens/LogoVersaoMenor.svg" alt="Logo do app Constroo">
    </div>
</header>

<h1 id="titulo-Editar-Pelo-ID">Deletar Plano Ativação por ID</h1> <!-- Título principal -->
<form action="${pageContext.request.contextPath}/DeletarPlanoAtivacaoServlet" method="post"> <!-- Formulário para alteração -->
    <h2 class="subtitulo-forms"></h2> <!-- Subtítulo -->
    <div class="infos-linha1"> <!-- Linha de informações -->

        <div class="ID">
            <label for="id">ID:</label> <!-- Rótulo para nome Atual -->
            <input type="text" name="id_planoativacao" id="id" placeholder="Insira o ID"> <!-- Campo de entrada para Nome Novo -->
        </div>

        <div class="classButton1">
            <input type="submit" id="buttonCadastrar" value="Deletar"> <!-- Botão de envio -->
        </div>

        <div id="infos-erro">
            <% if (request.getAttribute("retorno") == "erro"){
        %>
            <p><%=request.getAttribute("mensagem") %></p>
            <%} else if (request.getAttribute("retorno") == "notfound") {%>
                <p>ITEM NÃO ENCONTRADO</p>
            <%}%>
        </div>
    </div>
</form>

</body>
</html>