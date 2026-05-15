<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Novo Cadastro (F01)</title>
    <style>
        :root { --primary: #2563eb; --bg: #f8fafc; }
        body { font-family: 'Segoe UI', sans-serif; background: var(--bg); padding: 20px; }
        .container { max-width: 600px; margin: auto; background: white; padding: 2rem; border-radius: 12px; box-shadow: 0 4px 6px rgba(0,0,0,0.1); }
        h1 { color: var(--primary); text-align: center; font-size: 1.5rem; margin-bottom: 1.5rem; }
        .form-group { margin-bottom: 1rem; }
        label { display: block; margin-bottom: 5px; font-weight: 600; color: #475569; }
        input, select, textarea { width: 100%; padding: 10px; border: 1px solid #cbd5e1; border-radius: 6px; box-sizing: border-box; font-size: 1rem; }
        textarea { height: 80px; resize: vertical; }
        .actions { display: flex; gap: 10px; margin-top: 2rem; }
        .btn { flex: 1; padding: 12px; border-radius: 6px; cursor: pointer; border: none; font-weight: 600; text-align: center; text-decoration: none; }
        .btn-save { background: var(--primary); color: white; }
        .btn-cancel { background: #e2e8f0; color: #475569; }
    </style>
</head>
<body>
<div class="container">
    <h1>📝 Novo Cadastro (F01)</h1>
    <form action="AchadosPerdidosServlet" method="POST">
        <input type="hidden" name="action" value="cadastrar">

        <div class="form-group">
            <label>Descrição*</label>
            <input type="text" name="descricao" required placeholder="Ex: iPhone 13 preto">
        </div>
        <div class="form-group">
            <label>Categoria*</label>
            <select name="categoria" required>
                <option value="">Selecione...</option>
                <option value="Eletrônicos">Eletrônicos</option>
                <option value="Documentos">Documentos</option>
                <option value="Acessórios">Acessórios</option>
                <option value="Outros">Outros</option>
            </select>
        </div>
        <div class="form-group">
            <label>Local onde foi encontrado*</label>
            <input type="text" name="local" required placeholder="Ex: Praça de Alimentação">
        </div>
        <div class="form-group">
            <label>Data do Encontro*</label>
            <input type="date" name="data_encontro" required>
        </div>
        <div class="form-group">
            <label>Observação (Opcional)</label>
            <textarea name="observacao" placeholder="Detalhes adicionais..."></textarea>
        </div>

        <div class="actions">
            <a href="AchadosPerdidosServlet?action=listar" class="btn btn-cancel">Cancelar</a>
            <button type="submit" class="btn btn-save">Salvar Item</button>
        </div>
    </form>
</div>
</body>
</html>