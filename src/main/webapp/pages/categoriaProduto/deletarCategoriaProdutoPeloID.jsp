<%@ page contentType="text/html;charset=UTF-8" language="java" %> <!-- Definição do tipo de conteúdo e linguagem -->
<%@ page errorPage="../ErrorPage.jsp" %> <!-- Página de erro a ser redirecionada em caso de falha -->
<html>
<head>
  <title>Deletar Categoria Produto pelo ID</title> <!-- Título da página -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/cascading-style-sheets/id.css"> <!-- Link para a folha de estilo -->
</head>
<body>
<header>
  <div class="titulo-constroo">
    <h1>Constroo</h1>
    <img src="${pageContext.request.contextPath}/imagens/LogoVersaoMenor.svg" alt="Logo do app Constroo">  <!-- Logo da aplicação -->
  </div>

  <div class="cruds"> <!-- Navegação principal com links para diferentes seções -->
    <ul>
      <li><a href="${pageContext.request.contextPath}/pages/plano/listagemPlanos.jsp">Planos</a></li>
      <li><a href="${pageContext.request.contextPath}/pages/categoriaProduto/listagemCategoriaProdutos.jsp" class="active">Categorias</a></li>
      <li><a href="${pageContext.request.contextPath}/pages/administrador/listagemAdministradores.jsp" >Adms</a></li>
      <li><a href="${pageContext.request.contextPath}/pages/tagServico/listagemTagServico.jsp">Tag Serviço</a></li>
      <li><a href="${pageContext.request.contextPath}/pages/planoAtivacao/listagemPlanosAtivacao.jsp">Planos Ativação</a></li>
    </ul>
  </div>
</header>
<h1 id="titulo-Editar-Pelo-ID">Deletar Categoria Produto por ID</h1> <!-- Título principal da seção -->
<form action="${pageContext.request.contextPath}/DeletarCategoriaProdutoServlet" method="post"> <!-- Formulário para deletar categoria -->
  <h2 class="subtitulo-forms"></h2> <!-- Subtítulo (atualmente vazio) -->
  <div class="infos-linha1"> <!-- Div que agrupa as informações do formulário -->

    <div class="ID"> <!-- Div para o campo ID -->
      <label for="id">ID:</label> <!-- Rótulo para o campo ID -->
      <input type="number" name="categoria_id" id="id" min="1" placeholder="Insira o ID" required> <!-- Campo de entrada para o ID da categoria -->
    </div>

    <div class="classButton1"> <!-- Div para o botão de ação -->
      <input type="submit" id="buttonCadastrar" value="Deletar"> <!-- Botão de envio para deletar a categoria -->
    </div>

    <div class="infos-erro"> <!-- Div para exibir mensagem de erro -->
      <%
        // Verifica se há mensagens de retorno da operação
        if (request.getAttribute("retorno") == "erro") {
      %>
        <p><%= request.getAttribute("mensagem") %></p> <!-- Mensagem de erro retornada -->
      <%
      } else if (request.getAttribute("retorno") == "notfound") {
      %>
        <p>ITEM NÃO ENCONTRADO</p> <!-- Mensagem quando o item não é encontrado -->
      <%}%>
    </div>
  </div>
</form>
</body>
</html>
