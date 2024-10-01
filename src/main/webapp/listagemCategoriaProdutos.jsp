<%@ page import="java.sql.ResultSet" %>
<%@ page import="org.constroocrud.crud.entidades.Usuario" %>
<%@ page import="org.constroocrud.crud.conexao.Conexao" %>
<%@ page import="java.sql.SQLException" %><%--
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
    <link rel="stylesheet" href="listagemUsuarios.css">

    <title>CRUD</title>
        <%-- NAVBAR --%>.
    <div id="navbar">
        <p id="navbar-Constroo">Constroo</p>
        <div id="navbar-line"></div>
    </div>

        <%-- Nesta parte o usuario sera direcionado para as outras listagens

         ex: ListagensProdutos.jsp--%>.

    <div id="div-entidades">
        <a>
            Usuários
        </a>
        <a>
            Produtos
        </a>
        <a>
            Serviços
        </a>
    </div>
    <div id="div-crud-usuario">
        <h1 id="usuarios-titulo">Usuários</h1>

        <div id="right-options-crud">

        <%--Acesso ao cadastro de um usuario--%>
            <form id="form-usuario-criar" action="cadastrarUsuario.html">
                <button id="button-criar">Criar</button>
                <input type="text">
                <input type="submit" value="pesquisar">
            </form>
        </div>

    </div>
        <%-- fiz um sistemas de :target para dividir a seçao de profissionais e compradores/vendedores--%>.
    <div id="tipos-usuario" >
        <a href="#secao_profissional">
            Profissional
        </a>
        <a href="#secao_compradorvendedor">
            Comprador/Vendedor
        </a>
    </div>
    <div id="secao_compradorvendedor">

        <%--Esta é a seçao de compradores vendedores

        Ocorre um while no result set retornado pelos buscarCopradorVendedor, criando divs a cada resultado
        voce consegue colocar um codigo java por meio desse < e %, caso vc queira colocar apenas uma expressao, tipo um valor string voce precisa de um < % = (sem os espaços entre si)

        Coloquei tudo dentro e dentro e dentro de divs para fazer a estilização no CSS
        --%>
        <%
            Conexao conexao = new Conexao();
            ResultSet resultSet = conexao.buscarCompradorVendedor();
            try {
                while (resultSet.next()){
        %>


        <div class="Usuario">
            <div class="top-usuario-infos">
                <div class="infos-principais-usuario">


                    <h1 class="nome-Usuario"><%= resultSet.getString("nome_completo")%></h1>
                    <h3 class="tipo">Comprador/Vendedor</h3>
                </div>

                <div class="deletar-alterar">

                    <%--Este form post é para fazer o acesso ao servlet de deletar users que tem um input escondido que recebe o id do comprador vendedor--%>
                    <form action="DeletarUsuarioServlet" method="post">
                        <input type="hidden" name="id_compradorvendedor" value=<%=resultSet.getInt("CompradorVendedor_id")%>>
                        <button type="submit" class="button-deletar-alterar">Deletar</button>
                    </form>

                    <button class="button-deletar-alterar">Alterar</button>
                </div>

            </div>
            <div class="bottom-usuario-infos">
                <div class="infos-contato">
                    <p class="infos-texto">Informações pessoais</p>
                    <p class="infos-texto">Telefone: </p><span class="info_span"><%= resultSet.getString("telefone_celular")%></span>
                    <p class="infos-texto">E-mail: </p><span class="info_span"><%= resultSet.getString("email")%></span>
                    <p class="infos-texto">CPF: </p><span class="info_span"><%= resultSet.getString("cpf")%></span>
                </div>
                <div class="endereco">
                    <p class="infos-texto">Endereço</p>
                    <div class="endereco-infos">

                        <div class="info-end">
                            <p class="infos-texto">CEP</p><span class="info_span"><%= resultSet.getString("cep")%></span>
                            <p class="infos-texto">UF</p><span class="info_span"><%= resultSet.getString("uf")%></span>
                            <p class="infos-texto">Cidade</p><span class="info_span"><%= resultSet.getString("cidade")%></span>
                            <p class="infos-texto">Bairro</p><span class="info_span"><%= resultSet.getString("bairro")%></span>

                        </div>
                        <div class="info-end">
                            <p class="infos-texto">Rua</p><span class="info_span"><%= resultSet.getString("rua")%></span>
                            <p class="infos-texto">Numero</p><span class="info_span"><%= resultSet.getInt("numero")%></span>
                            <p class="infos-texto">Complemento</p><span class="info_span"><%= resultSet.getString("complemento")%></span>

                        </div>
                    </div>
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
    <%-- o mesmo se repete aqui--%>

    <div id="secao_profissional">
        <%
        resultSet = conexao.buscarProfissionais();
        try {
            while (resultSet.next()){

    %>
        <div class="Usuario">
            <div class="top-usuario-infos">
                <div class="infos-principais-usuario">
                    <h1 class="nome-Usuario"><%= resultSet.getString("nome_completo")%></h1>
                    <h3 class="tipo">Profissional</h3>
                </div>

                <div class="deletar-alterar">
                    <form action="DeletarUsuarioServlet" method="post">
                        <input type="hidden" name="id_compradorvendedor" value=<%=resultSet.getInt("profissional_id")%>>
                        <button type="submit" class="button-deletar-alterar">Deletar</button>
                    </form>
                    <button class="button-deletar-alterar">Alterar</button>
                </div>

            </div>
            <div class="bottom-usuario-infos">
                <div class="infos-contato">
                    <p class="infos-texto">Informações pessoais</p>
                    <p class="infos-texto">Telefone: </p><span class="info_span"><%= resultSet.getString("telefone_celular")%></span>
                    <p class="infos-texto">E-mail: </p><span class="info_span"><%= resultSet.getString("email")%></span>
                    <p class="infos-texto">CPF: </p><span class="info_span"><%= resultSet.getString("cpf")%></span>
                </div>
                <div class="endereco">
                    <p class="infos-texto">Endereço</p>
                    <div class="endereco-infos">

                        <div class="info-end">
                            <p class="infos-texto">CEP</p><span class="info_span"><%= resultSet.getString("CEP")%></span>
                            <p class="infos-texto">UF</p><span class="info_span"><%= resultSet.getString("uf")%></span>
                            <p class="infos-texto">Cidade</p><span class="info_span"><%= resultSet.getString("cidade")%></span>
                            <p class="infos-texto">Bairro</p><span class="info_span"><%= resultSet.getString("bairro")%></span>

                        </div>
                        <div class="info-end">

                            <p class="infos-texto">Rua</p><span class="info_span"><%= resultSet.getString("rua")%></span>
                            <p class="infos-texto">Numero</p><span class="info_span"><%= resultSet.getInt("numero")%></span>
                            <p class="infos-texto">Complemento</p><span class="info_span"><%= resultSet.getString("complemento")%></span>


                        </div>
                    </div>
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
