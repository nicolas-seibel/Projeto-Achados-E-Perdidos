package br.com.web;

import br.com.dao.ItemDAO;
import br.com.dao.EntregasDAO;
import br.com.model.Item;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/AchadosPerdidosServlet")
public class AchadosPerdidosServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private ItemDAO itemDao = new ItemDAO();
    private EntregasDAO entregasDao = new EntregasDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if (action == null) {
            action = "login";
        }

        try {
            if ("login".equals(action)) {
                request.getRequestDispatcher("login.jsp").forward(request, response);
                
            } else if ("listar".equals(action)) {
                String status = request.getParameter("status");
                List<Item> itens;

                // CORRIGIDO: Captura o parâmetro do combobox do JSP de forma exata
                if ("DEVOLVIDO".equals(status) || "devolvido".equals(status)) {
                    itens = itemDao.listarDItem(); // Usa seu método de filtrados devolvidos
                } else if ("ACHADO".equals(status) || "disponivel".equals(status)) {
                    itens = itemDao.listarAItem(); // Usa seu método de filtrados disponíveis
                } else {
                    itens = itemDao.listarItem();  // Fallback seguro: traz tudo
                }
                    
                request.setAttribute("itens", itens);
                request.getRequestDispatcher("index.jsp").forward(request, response);
                
            } else if ("detalhar".equals(action)) {
                String idParam = request.getParameter("id");
                if (idParam != null && !idParam.isEmpty() && idParam.matches("\\d+")) {
                    int id = Integer.parseInt(idParam);
                    Item item = itemDao.buscarPorIdItem(id);
                    request.setAttribute("item", item);
                    request.getRequestDispatcher("detalhes.jsp").forward(request, response);
                } else {
                    response.sendRedirect("AchadosPerdidosServlet?action=listar");
                }
                
            } else if ("excluir".equals(action)) {
                String idParam = request.getParameter("id");
                if (idParam != null && !idParam.isEmpty() && idParam.matches("\\d+")) {
                    int id = Integer.parseInt(idParam);
                    itemDao.excluirItem(id);
                }
                response.sendRedirect("AchadosPerdidosServlet?action=listar");
                
            } else if ("devolver".equals(action)) {
                String idParam = request.getParameter("id");
                if (idParam != null && !idParam.isEmpty() && idParam.matches("\\d+")) {
                    int id = Integer.parseInt(idParam);
                    itemDao.marcarComoDevolvido(id);
                }
                response.sendRedirect("AchadosPerdidosServlet?action=listar");

            } else if ("desfazerDevolucao".equals(action)) { 
                String idParam = request.getParameter("id");
                if (idParam != null && !idParam.isEmpty() && idParam.matches("\\d+")) {
                    int id = Integer.parseInt(idParam);
                    itemDao.marcarComoAchado(id); 
                }
                // Redireciona de volta de forma limpa para a listagem geral
                response.sendRedirect("AchadosPerdidosServlet?action=listar");
            }

        } catch (Exception e) {
            throw new ServletException("Erro no fluxo do Servlet: " + e.getMessage(), e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");

        if ("logar".equals(action)) {
            String email = request.getParameter("email");
            String senha = request.getParameter("senha");

            if ("admin@email.com".equals(email) && "123".equals(senha)) {
                HttpSession session = request.getSession();
                session.setAttribute("usuarioLogado", email);
                
                response.sendRedirect("AchadosPerdidosServlet?action=listar");
            } else {
                request.setAttribute("erro", "Usuário ou senha incorretos!");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }

        } else if ("cadastrar".equals(action)) {
            try {
                Item item = new Item();
                item.setNome_item(request.getParameter("nome_item")); 
                item.setDescricao(request.getParameter("descricao"));
                item.setLocal_achado(request.getParameter("local_achado"));
                
                String dataStr = request.getParameter("data_achado");
                if (dataStr != null && !dataStr.isEmpty()) {
                    item.setData_achado(java.time.LocalDate.parse(dataStr));
                } else {
                    item.setData_achado(java.time.LocalDate.now());
                }
                item.setStatus_item("disponivel"); 

                itemDao.inserirItem(item); 
            } catch (Exception e) {
                e.printStackTrace();
            }
            response.sendRedirect("AchadosPerdidosServlet?action=listar");
        }
    }
}