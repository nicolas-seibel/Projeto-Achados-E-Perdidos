<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Achados E Perdidos | Painel</title>
    <style>
        :root { --primary: #2563eb; --danger: #dc2626; --success: #16a34a; --bg: #f8fafc; }
        body { font-family: 'Segoe UI', sans-serif; background: var(--bg); margin: 0; padding: 20px; }
        .container { max-width: 1000px; margin: auto; background: white; padding: 2rem; border-radius: 12px; box-shadow: 0 4px 6px rgba(0,0,0,0.1); }
        .header { display: flex; justify-content: space-between; align-items: center; margin-bottom: 2rem; }
        .filter-bar { display: flex; gap: 10px; margin-bottom: 1.5rem; background: #f1f5f9; padding: 15px; border-radius: 8px; }
        input, select { padding: 10px; border: 1px solid #cbd5e1; border-radius: 6px; font-size: 1rem; }
        input[type="text"] { flex: 1; }
        table { width: 100%; border-collapse: collapse; margin-top: 15px; }
        th { text-align: left; background: #f8fafc; padding: 12px; color: #64748b; text-transform: uppercase; font-size: 0.8rem; border-bottom: 2px solid #e2e8f0; }
        td { padding: 14px; border-bottom: 1px solid #f1f5f9; }
        .btn { padding: 8px 16px; border-radius: 6px; cursor: pointer; text-decoration: none; border: none; font-weight: 600; display: inline-block; }
        .btn-primary { background: var(--primary); color: white; }
        .btn-action { background: none; border: none; cursor: pointer; font-size: 1.1rem; text-decoration: none; }
    </style>
</head>
<body>
<div class="container">
    <div class="header">
        <h1>📦 Achados E Perdidos</h1>
        <a href="cadastro.jsp" class="btn btn-primary">+ Cadastrar Item (F01)</a>
    </div>

    <form class="filter-bar" action="AchadosPerdidosServlet" method="GET">
        <input type="hidden" name="action" value="listar">
        <input type="text" name="busca" placeholder="Buscar por descrição (F02)..." value="${param.busca}">
        <select name="status">
            <option value="disponivel" ${param.status == 'disponivel' ? 'selected' : ''}>Ver: ACHADOS</option>
            <option value="devolvido" ${param.status == 'devolvido' ? 'selected' : ''}>Ver: DEVOLVIDOS</option>
        </select>
        <button type="submit" class="btn btn-primary">Filtrar</button>
    </form>

<table>
        <thead>
            <tr>
                <th>Item / Descrição</th>
                <th>Local Onde Foi Achado</th>
                <th>Status</th>
                <th>Ações</th>
            </tr>
        </thead>
        <tbody>
            <c:choose>
                <c:when test="${not empty itens}">
                    <c:forEach var="item" items="${itens}">
                        <tr>
                            <td><strong>${item.nome_item}</strong><br><small style="color: #64748b;">${item.descricao}</small></td>
                            <td>${item.local_achado}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${item.status_item == 'disponivel'}">
                                        <span style="color: var(--success); font-weight: bold;">ACHADO</span>
                                    </c:when>
                                    <c:when test="${item.status_item == 'devolvido'}">
                                        <span style="color: #64748b; font-weight: bold;">DEVOLVIDO</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span style="font-weight: bold; text-transform: uppercase;">${item.status_item}</span>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <a href="AchadosPerdidosServlet?action=detalhar&id=${item.id_item}" class="btn-action" title="Detalhes">👁️</a> 
                                
                                <c:if test="${item.status_item == 'disponivel'}">
                                    <button onclick="confirmarDevolucao(${item.id_item})" class="btn-action" title="Marcar como Devolvido">✅</button>
                                </c:if>

                                <c:if test="${item.status_item == 'devolvido'}">
                                    <button onclick="confirmarRetornoAchado(${item.id_item})" class="btn-action" title="Desfazer devolução (Voltar para Achado)">↩️</button>
                                </c:if>

                                <button onclick="confirmarExclusao(${item.id_item})" class="btn-action" title="Excluir">🗑️</button>
                            </td>
                        </tr>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <tr><td colspan="4" style="text-align:center; padding: 20px; color: #64748b;">Nenhum item listado para este filtro.</td></tr>
                </c:otherwise>
            </c:choose>
        </tbody>
    </table>
</div>

<script>
    function confirmarExclusao(id) {
        if(confirm("🚨 F05: Deseja realmente EXCLUIR o item #" + id + "?")) {
            window.location.href = "AchadosPerdidosServlet?action=excluir&id=" + id;
        }
    }
    function confirmarDevolucao(id) {
        if(confirm("✅ F04: Confirmar que o item #" + id + " foi devolvido?")) {
            window.location.href = "AchadosPerdidosServlet?action=devolver&id=" + id;
        }
    }
    // NOVA FUNÇÃO JS PARA O RETORNO:
    function confirmarRetornoAchado(id) {
        if(confirm("🔄 Deseja cancelar a devolução e colocar o item #" + id + " de volta como ACHADO?")) {
            window.location.href = "AchadosPerdidosServlet?action=desfazerDevolucao&id=" + id;
        }
    }
</script>
</body>
</html>