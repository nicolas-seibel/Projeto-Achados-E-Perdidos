package br.com.web;

import br.com.dao.ItemDAO;
import br.com.dao.EntregasDAO;
import br.com.model.Item;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession; // Adicionado para gerenciar a sessão do login

import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet("/AchadosPerdidosServlet")
public class AchadosPerdidosServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private ItemDAO itemDao = new ItemDAO();
    private EntregasDAO entregasDao = new EntregasDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        
        // ALTERADO: Se não houver ação, agora o padrão é ir para a tela de login
        if (action == null) {
            action = "login";
        }

        try {
            if ("login".equals(action)) {
                // Encaminha para o arquivo login.jsp (FrontEnd da sua colega)
                request.getRequestDispatcher("login.jsp").forward(request, response);
                
            } else if ("listar".equals(action)) {
                String busca = request.getParameter("busca");
                String status = request.getParameter("status");
                if (status == null || status.isEmpty()) {
                    status = "ACHADO";
                }
                	
                List<Item> itens = itemDao.listarItem();
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
            }
        } catch (Exception e) {
            throw new ServletException("Erro no fluxo do Servlet: " + e.getMessage(), e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");

        // NOVO: Processa os dados enviados pelo formulário de login
        if ("logar".equals(action)) {
            String email = request.getParameter("email");
            String senha = request.getParameter("senha");

            // Validação simples de teste (Usuário: admin@email.com / Senha: 123)
            if ("admin@email.com".equals(email) && "123".equals(senha)) {
                HttpSession session = request.getSession();
                session.setAttribute("usuarioLogado", email);
                
                // Se der certo, entra no sistema e lista os itens
                response.sendRedirect("AchadosPerdidosServlet?action=listar");
            } else {
                // Se der errado, volta para o login com uma mensagem de erro
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
                item.setStatus_item(true); 

                itemDao.inserirItem(item); 
            } catch (Exception e) {
                e.printStackTrace();
            }
            response.sendRedirect("AchadosPerdidosServlet?action=listar");
        }
    }
}