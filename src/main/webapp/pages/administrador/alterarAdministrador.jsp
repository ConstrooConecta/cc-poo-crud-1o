<%@ page errorPage="../ErrorPage.jsp" %>
<html>
<head>
    <title>Editar Administrador</title> <!-- Título da página -->
</head>
<body>
<!-- <link rel="stylesheet" href="alterarAdministrador.css">  -->
<!-- <link rel="stylesheet" href="alterar2.css"> -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/cascading-style-sheets/alterarAdministrador.css">
<header>
    <div class="titulo-constroo">
        <h1>Constroo</h1>
        <img src="${pageContext.request.contextPath}/imagens/LogoVersaoMenor.svg" alt="Logo do app Constroo">
    </div>
</header>

<h1 id="titulo-Editar-Administrador">Editar Administrador</h1> <!-- Título principal -->
<form action="AlterarAdministradorServlet" method="post"> <!-- Formulário para alteração -->
    <h2 class="subtitulo-forms"></h2> <!-- Subtítulo -->
    <div class="infos-linha"> <!-- Linha de informações -->

        <div class="gridAtualNome">
            <label>Nome Atual:</label>
            <input type="text" value="<%= request.getAttribute("nome") %>" disabled>
        </div>

        <div class="gridNovoNome">
            <label for="nome">Novo Nome:</label> <!-- Rótulo para nome Atual -->
            <input type="text" name="nome" id="nome" placeholder="Novo nome"> <!-- Campo de entrada para Nome Novo -->
        </div>

        <div class="gridAtualEmail">
            <label>Email Atual:</label>
            <input type="text"  value="<%= request.getAttribute("email") %>" disabled >
        </div>

        <div class="gridNovoEmail">
            <label for="email">Novo Email:</label> <!-- Rótulo para email -->
            <input type="email" name="email" id="email" placeholder="Email novo" > <!-- Campo de entrada para email -->
        </div>

        <div class="gridNovaSenha">
            <label for="senha">Nova Senha:</label> <!-- Rótulo para senha -->
            <input type="password" name="senha" id="senha" placeholder="Senha nova"> <!-- Campo de entrada para senha -->
            <input type="hidden" name="id" value=<%=request.getAttribute("id") %>> <!-- Campo oculto para ID -->
        </div>
    </div>

    <div class="classButton">
        <input type="submit" id="buttonCadastrar"> <!-- Botão de envio -->
    </div>


</form>
<% if (request.getAttribute("retorno") == "erro"){
%>
<div>
    <P><%=request.getAttribute("mensagem")%></P>

</div>
<%}%>
</body>
</html>