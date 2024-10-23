<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cadastrar Administrador</title>
    <link rel="stylesheet" href="../cascading-style-sheets/cadastrarAdministrador.css">
</head>
<body>7

<header>
    <div class="titulo-constroo">
        <h1>Constroo</h1>
        <img src="../imagens/LogoVersaoMenor.svg" alt="Logo do app Constroo">
    </div>
</header>

<h1 id="titulo-Cadastrar-Administrador">Cadastrar Administrador</h1>
<form action="${pageContext.request.contextPath}/InserirAdministradorServlet" method="post" >

    <h2 class="subtitulo-forms"></h2>

    <div class="infos-linha">

        <label for="Nome">Nome</label>
        <input type="text" name="nome" id="nome" placeholder="Matheus">

        <label for="email">Email</label>
        <input type="email" name="email" id="email" placeholder="exemplo@email.com">

        <label for="senha">Senha</label>
        <input type="password" name="senha" id="senha" placeholder="senha123">

        <input type="hidden" name="id" value=id>

        <% if (request.getAttribute("retorno") == "erro"){
        %>
        <div>
            <P><%=request.getAttribute("mensagem")%></P>

        </div>
        <%}%>

        <input type="submit" id="buttonCadastrar" value="Cadastrar">

    </div>
</form>

</body>
</html>