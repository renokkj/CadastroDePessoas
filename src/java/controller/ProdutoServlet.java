package controller;

import dao.ProdutoDAO;
import model.Produto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/ProdutoServlet")
public class ProdutoServlet extends HttpServlet {

    private ProdutoDAO dao;

    @Override
    public void init() {
        dao = new ProdutoDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) action = "";

        try {
            switch (action) {
                case "novo":
                    request.getRequestDispatcher("adicionar.jsp").forward(request, response);
                    break;

                case "editar":
                    Produto pEditar = new Produto();
                    pEditar.setId(Integer.parseInt(request.getParameter("id")));
                    Produto encontradoEditar = dao.buscarPorId(pEditar);
                    if (encontradoEditar != null) {
                        request.setAttribute("produto", encontradoEditar);
                        request.getRequestDispatcher("editar.jsp").forward(request, response);
                    } else {
                        request.setAttribute("mensagem", "Produto não encontrado.");
                        listar(request, response);
                    }
                    break;

                case "excluir":
                    Produto pExcluir = new Produto();
                    pExcluir.setId(Integer.parseInt(request.getParameter("id")));
                    dao.deletarProduto(pExcluir);
                    response.sendRedirect("ProdutoServlet");
                    break;

                case "buscar":
                    Produto pBusca = new Produto();
                    try {
                        pBusca.setId(Integer.parseInt(request.getParameter("idBusca")));
                        Produto resultado = dao.buscarPorId(pBusca);
                        if (resultado != null) {
                            request.setAttribute("produtoBusca", resultado);
                        } else {
                            request.setAttribute("mensagem", "Produto com ID " + pBusca.getId() + " não encontrado.");
                        }
                    } catch (Exception e) {
                        request.setAttribute("mensagem", "ID inválido.");
                    }
                    listar(request, response);
                    break;

                case "limpar":
                    dao.deletarTodos();
                    dao.resetarAutoIncremento();
                    response.sendRedirect("ProdutoServlet");
                    break;

                default:
                    listar(request, response);
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException("Erro ao executar ação: " + action, e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Produto p = new Produto();
        p.setNome(request.getParameter("nome"));
        p.setDescricao(request.getParameter("descricao"));
        p.setPreco(Double.parseDouble(request.getParameter("preco")));
        p.setQuantidade(Integer.parseInt(request.getParameter("quantidade")));
        p.setCategoria(request.getParameter("categoria"));
        p.setCodigoBarras(request.getParameter("codigoBarras"));
        p.setFornecedor(request.getParameter("fornecedor"));
        p.setDataValidade(Date.valueOf(request.getParameter("dataValidade")));
        p.setLote(request.getParameter("lote"));

        try {
            String id = request.getParameter("id");
            if (id == null || id.isEmpty()) {
                dao.inserirProduto(p);
            } else {
                p.setId(Integer.parseInt(id));
                dao.atualizarProduto(p);
            }
        } catch (SQLException e) {
            throw new ServletException("Erro ao salvar produto.", e);
        }

        response.sendRedirect("ProdutoServlet");
    }

    private void listar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Produto> lista = dao.listarTodos();
            request.setAttribute("produtos", lista);
            request.getRequestDispatcher("listar.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Erro ao listar produtos.", e);
        }
    }
}