package br.com.web;

import br.com.dao.ItemDAO;
import br.com.dao.EntregasDAO;
import br.com.model.Item;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet("/AchadosPerdidosServlet")
public class AchadosPerdidosServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private ItemDAO dao = new ItemDAO();
    private EntregasDAO entregasDao = new EntregasDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "listar";
        }

        try {
            if ("listar".equals(action)) {
                String busca = request.getParameter("busca");
                String status = request.getParameter("status");
                if (status == null || status.isEmpty()) {
                    status = "ACHADO";
                }
                
                List<Item> itens = dao.listar(busca, status);
                request.setAttribute("itens", itens);
                request.getRequestDispatcher("index.jsp").forward(request, response);
                
            } else if ("detalhar".equals(action)) {
                String idParam = request.getParameter("id");
                if (idParam != null && !idParam.isEmpty() && idParam.matches("\\d+")) {
                    int id = Integer.parseInt(idParam);
                    Item item = dao.buscarPorId(id);
                    request.setAttribute("item", item);
                    request.getRequestDispatcher("detalhes.jsp").forward(request, response);
                } else {
                    response.sendRedirect("AchadosPerdidosServlet?action=listar");
                }
                
            } else if ("excluir".equals(action)) {
                String idParam = request.getParameter("id");
                if (idParam != null && !idParam.isEmpty() && idParam.matches("\\d+")) {
                    int id = Integer.parseInt(idParam);
                    dao.excluir(id);
                }
                response.sendRedirect("AchadosPerdidosServlet?action=listar");
                
            } else if ("devolver".equals(action)) {
                String idParam = request.getParameter("id");
                if (idParam != null && !idParam.isEmpty() && idParam.matches("\\d+")) {
                    int id = Integer.parseInt(idParam);
                    entregasDao.marcarComoDevolvido(id);
                }
                response.sendRedirect("AchadosPerdidosServlet?action=listar");
            }
        } catch (Exception e) {
            throw new ServletException("Erro no fluxo do Servlet: " + e.getMessage(), e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");

        if ("cadastrar".equals(action)) {
            try {
                Item item = new Item();
                item.setDescricao(request.getParameter("descricao"));
                item.setCategoria(request.getParameter("categoria"));
                item.setLocal(request.getParameter("local"));
                
                String dataStr = request.getParameter("data_encontro");
                if (dataStr != null && !dataStr.isEmpty()) {
                    item.setDataEncontro(Date.valueOf(dataStr));
                } else {
                    item.setDataEncontro(new Date(System.currentTimeMillis()));
                }
                item.setObservacao(request.getParameter("observacao"));

                dao.cadastrar(item);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        response.sendRedirect("AchadosPerdidosServlet?action=listar");
    }
}