<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="org.constroocrud.crud.models.CategoriaProduto" %>
<%@ page import="org.constroocrud.crud.DAOs.CategoriaProdutoDAO" %>
<%@ page import="org.constroocrud.crud.DAOs.AdministradorDAO" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>

    <%-- recebe o css --%>.
    <link rel="stylesheet" href="${pageContext.request.contextPath}/cascading-style-sheets/listagemAdministradores.css">

    <title>CRUD</title>
        <%-- NAVBAR --%>.
    <div id="navbar">
        <p id="navbar-Constroo">Constroo</p>
        <div id="navbar-line"></div>
    </div>

        <%-- Nesta parte o usuario sera direcionado para as outras listagens

         ex: ListagensProdutos.jsp--%>.

    <div id="div-entidades">
        <a href="${pageContext.request.contextPath}/pages/listagemCategoriaProdutos.jsp">
            Categorias/Tags
        </a>
        <a href="#">
            Administração
        </a>
        <a href="#">
            Planos
        </a>
    </div>
    <div id="div-crud-usuario">
        <h1 id="usuarios-titulo">Administração</h1>

        <div id="right-options-crud">

        <%--Acesso ao cadastro de um usuario--%>
            <form id="form-usuario-criar" action="${pageContext.request.contextPath}/hyperText-markup-language/cadastrarAdministrador.html">
                <button id="button-criar">Criar</button>
                <input type="text">
                <input type="submit" value="pesquisar">
            </form>
        </div>

    </div>
        <%-- fiz um sistemas de :target para dividir a seçao de profissionais e compradores/vendedores--%>.

    <div >

        <%--Esta é a seçao de compradores vendedores

        Ocorre um while no result set retornado pelos buscarCopradorVendedor, criando divs a cada resultado
        voce consegue colocar um codigo java por meio desse < e %, caso vc queira colocar apenas uma expressao, tipo um valor string voce precisa de um < % = (sem os espaços entre si)

        Coloquei tudo dentro e dentro e dentro de divs para fazer a estilização no CSS
        --%>
        <%

            AdministradorDAO administradorDAO = new AdministradorDAO();


            ResultSet resultSet =administradorDAO.buscarAdministrador();
            try {
                while (resultSet.next()){
        %>


        <div class="Usuario">
            <div class="top-usuario-infos">
                <div class="infos-principais-usuario">


                    <h1 class="nome-Usuario"><%= resultSet.getString("nome")%></h1>
                    <p ><%= resultSet.getString("email")%></p>

                </div>

                <div class="deletar-alterar">

                    <%--Este form post é para fazer o acesso ao servlet de deletar users que tem um input escondido que recebe o id do comprador vendedor--%>
                    <form action="${pageContext.request.contextPath}/DeletarAdministradorServlet" method="post">
                        <input type="hidden" name="administrador_id" value=<%=resultSet.getInt("id")%>>
                        <button type="submit" class="button-deletar-alterar">Deletar</button>
                    </form>
                        <form action="${pageContext.request.contextPath}/DirecionarAdministradorAlterarServlet" method="post">
                            <input type="hidden" name="administrador_id" value=<%=resultSet.getInt("id")%>>
                            <input type="hidden" name="nome" value=<%=resultSet.getString("nome")%>>
                            <input type="hidden" name="email" value=<%=resultSet.getString("email")%>>
                            <input type="hidden" name="senha" value=<%=resultSet.getString("senha")%>>
                            <button type="submit" class="button-deletar-alterar">Alterar</button>
                        </form>


                </div>

            </div>





        </div>




        <%


                }
            }catch (SQLException sqlException){
                sqlException.printStackTrace();

            }
        %>
    </div>



</head>
<body>

</body>
</html>
