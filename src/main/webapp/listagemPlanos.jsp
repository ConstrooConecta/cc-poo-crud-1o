<%@ page import="java.sql.ResultSet" %>
<%@ page import="org.constroocrud.crud.entidades.Usuario" %>
<%@ page import="org.constroocrud.crud.conexao.Conexao" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="org.constroocrud.crud.entidades.CategoriaProduto" %>
<%@ page import="org.constroocrud.crud.DAOs.CategoriaProdutoDAO" %>
<%@ page import="org.constroocrud.crud.DAOs.PlanoDAO" %><%--
  Created by IntelliJ IDEA.
  User: matheusueno-ieg
  Date: 23/08/2024
  Time: 23:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>

    <%-- recebe o css --%>.
    <link rel="stylesheet" href="listagemCategoriaProduto.css">

    <title>CRUD</title>
        <%-- NAVBAR --%>.
    <div id="navbar">
        <p id="navbar-Constroo">Constroo</p>
        <div id="navbar-line"></div>
    </div>

        <%-- Nesta parte o usuario sera direcionado para as outras listagens

         ex: ListagensProdutos.jsp--%>.

    <div id="div-entidades">
        <a href="listagemCategoriaProdutos.jsp">
            Categorias/Tags
        </a>
        <a href="listagemAdministradores.jsp">
            Administração

        </a>
        <a href="#">
            Planos
        </a>
    </div>
    <div id="div-crud-usuario">
        <h1 id="usuarios-titulo">Planos</h1>

        <div id="right-options-crud">

        <%--Acesso ao cadastro de um usuario--%>
            <form id="form-usuario-criar" action="cadastrarCategoriaProduto.html">
                <button id="button-criar">Criar</button>
                <input type="text">
                <input type="submit" value="pesquisar">
            </form>
        </div>

    </div>
        <%-- fiz um sistemas de :target para dividir a seçao de profissionais e compradores/vendedores--%>.
    <div id="tipos-usuario" >
        <a href="#">
            Categoria Produto
        </a>
        <a href="listagemTagServico.jsp">
            Tag Serviços
        </a>
    </div>
    <div id="secao_CategoriaProduto">

        <%--Esta é a seçao de compradores vendedores

        Ocorre um while no result set retornado pelos buscarCopradorVendedor, criando divs a cada resultado
        voce consegue colocar um codigo java por meio desse < e %, caso vc queira colocar apenas uma expressao, tipo um valor string voce precisa de um < % = (sem os espaços entre si)

        Coloquei tudo dentro e dentro e dentro de divs para fazer a estilização no CSS
        --%>
        <%

            PlanoDAO planoDAO = new PlanoDAO();

            ResultSet resultSet = planoDAO.buscarPlanos();
            try {
                while (resultSet.next()){
        %>


        <div class="Usuario">
            <div class="top-usuario-infos">
                <div class="infos-principais-usuario">


                    <h1 class="nome-Usuario"><%= resultSet.getString("tipo_plano")%></h1>
                    <p>ID: <%= resultSet.getInt("id")%></p>
                </div>
                <p><%= resultSet.getInt("descricao")%></p>>

                <div class="deletar-alterar">

                    <%--Este form post é para fazer o acesso ao servlet de deletar users que tem um input escondido que recebe o id do comprador vendedor--%>
                    <form action="DeletarCategoriaProdutoServlet" method="post">
                        <input type="hidden" name="categoria_id" value=<%=resultSet.getInt("id")%>>
                        <button type="submit" class="button-deletar-alterar">Deletar</button>
                    </form>

                    <button class="button-deletar-alterar">Alterar</button>
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
