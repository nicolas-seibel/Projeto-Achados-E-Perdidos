<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
    <link rel="stylesheet" href="styles.css">
    <title>Login</title>
</head>
<body>
    <main class="container">
        <form id="meuFormularioLogin">
            <h1>Login</h1>  
            
            <div class="input-box">
                <input placeholder="Usuário" type="text" id="usuario" required>
                <i class="bx bxs-user"></i>
            </div>    
            
            <div class="input-box">
                <input placeholder="Senha" type="password" id="senha" required>
                 <i class="bx bxs-lock-alt"></i>
            </div>   
            
            <div class="remenber-forgot">
                <label>
                  <input type="checkbox">
                  Lembrar minha senha
                </label>
                <a href="#">Esqueci minha senha</a>
            </div>

            <button type="submit" class="login">Login</button>

            <div class="register-link"> 
                <p>Não tem uma conta? <a href="#">Cadastre-se</a></p>
            </div>
        </form>
    </main>

    <script>
        document.getElementById('meuFormularioLogin').addEventListener('submit', function(event) {
            // Impede o navegador de tentar procurar uma página estática inexistente
            event.preventDefault(); 

            // Força o redirecionamento para a URL exata que funcionou no seu vídeo
            window.location.href = '<%= request.getContextPath() %>/index.jsp';  
        });
    </script>
</body>
</html>