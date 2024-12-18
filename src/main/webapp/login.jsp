<%@ page contentType="text/html;charset=UTF-8" language="java" %> <!-- Definição do tipo de conteúdo e linguagem -->
<%@ page errorPage="/pages/ErrorPage.jsp" %> <!-- Página de erro em caso de falha -->
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Área Restrita - Entrar</title>
    <link rel="shortcut icon" type="image/png" href="/imagens/LogoVersaoMenor.ico"> <!-- Correção do tipo de imagem -->
    <link rel="stylesheet" href="cascading-style-sheets/estilizacao_arearestrita.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Montserrat+Alternates:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap" rel="stylesheet">
</head>
<body>
<header>
    <div class="titulo-constroo">
        <h1>Constroo</h1>
        <div>
            <img src="imagens/LogoVersaoMenor.svg" alt="Logo do app Constroo">
        </div>
    </div>
</header>

<div class="content">
    <div class="login">
        <img src="imagens/LogoAreaRestrita.svg" alt="Logo Área Restrita" id="Imagem-Login">
        <h5 class="titulo-login">Área Restrita</h5>
        <h2>Faça seu login</h2>
        <form action="LoginServlet" method="post" class="formulario-login">
            <label>E-mail
                <br>
                <input type="email" name="email-admin" placeholder="Insira seu e-mail" class="informacoes-admin" required>
            </label>
            <br><br>
            <label>Senha *
                <br>
                <input type="password" name="senha-admin" placeholder="Insira sua senha" class="informacoes-admin" required>
            </label>
            <br><br>
            <button type="submit" value="Logar" id="botao-login">Login</button>
        </form>

        <div class="informacao-erro">
            <% if (request.getAttribute("retorno") == "senha incorreta") { %>
            <p>Senha Inválida!</p>
            <% } else if (request.getAttribute("retorno") == "email incorreto") { %>
            <p>Administrador inexistente!</p>
            <% } %>
        </div>
    </div>
</div>
</body>
</html>
