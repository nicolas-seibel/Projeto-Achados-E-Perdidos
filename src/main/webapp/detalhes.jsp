<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Detalhes do Item (F03)</title>
    <style>
        body { font-family: 'Segoe UI', sans-serif; background: #f8fafc; padding: 20px; }
        .container { max-width: 600px; margin: auto; background: white; padding: 2rem; border-radius: 12px; box-shadow: 0 4px 6px rgba(0,0,0,0.1); }
        h1 { color: #2563eb; font-size: 1.5rem; margin-bottom: 1.5rem; }
        .info-box { background: #f1f5f9; padding: 12px; border-radius: 6px; margin-bottom: 1rem; }
        label { font-weight: bold; color: #475569; display: block; margin-bottom: 4px; }
        .btn { padding: 10px 20px; background: #e2e8f0; color: #475569; text-decoration: none; border-radius: 6px; font-weight: 600; display: inline-block; margin-top: 15px; }
    </style>
</head>
<body>
<div class="container">
    <h1>👁️ Detalhes do Item #${item.id}</h1>
    
    <label>Descrição:</label>
    <div class="info-box">${item.descricao}</div>
    
    <label>Categoria:</label>
    <div class="info-box">${item.categoria}</div>
    
    <label>Local Encontrado:</label>
    <div class="info-box">${item.local}</div>
    
    <label>Data:</label>
    <div class="info-box">${item.dataEncontro}</div>
    
    <label>Status Atual:</label>
    <div class="info-box"><strong>${item.status}</strong></div>
    
    <label>Observações:</label>
    <div class="info-box">${not empty item.observacao ? item.observacao : 'Nenhuma.'}</div>

    <a href="AchadosPerdidosServlet?action=listar" class="btn">Voltar à Lista</a>
</div>
</body>
</html>