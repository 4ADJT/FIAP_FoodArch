## Script PowerShell para Automatizar Build e Push da Imagem Docker

Crie um arquivo chamado `build_and_push.ps1` no diretório raiz do seu projeto com o seguinte conteúdo:

```powershell
# Definir variáveis
$dockerUser = "rbrocchi"
$imageName = "foodarch"
$imageTag = "v1.0"  # Você pode ajustar a tag conforme necessário

# Construir a imagem Docker
Write-Host "Construindo a imagem Docker..."
docker build -t "$dockerUser/$imageName:$imageTag" .

if ($LASTEXITCODE -ne 0) {
    Write-Host "Erro ao construir a imagem Docker."
    exit 1
}

# Fazer login no Docker Hub
Write-Host "Fazendo login no Docker Hub..."
$dockerPassword = Read-Host -Prompt "Digite sua senha do Docker Hub" -AsSecureString
$dockerPasswordPlain = [Runtime.InteropServices.Marshal]::PtrToStringAuto([Runtime.InteropServices.Marshal]::SecureStringToBSTR($dockerPassword))

docker login -u $dockerUser -p $dockerPasswordPlain

if ($LASTEXITCODE -ne 0) {
    Write-Host "Erro ao fazer login no Docker Hub."
    exit 1
}

# Enviar a imagem para o Docker Hub
Write-Host "Enviando a imagem para o Docker Hub..."
docker push "$dockerUser/$imageName:$imageTag"

if ($LASTEXITCODE -ne 0) {
    Write-Host "Erro ao enviar a imagem para o Docker Hub."
    exit 1
}

Write-Host "Imagem enviada com sucesso para $dockerUser/$imageName:$imageTag"

# Logout do Docker Hub (opcional)
docker logout
```

---

### Explicação do Script

1. **Definição de Variáveis**

   ```powershell
   $dockerUser = "rbrocchi"
   $imageName = "foodarch"
   $imageTag = "v1.0"
   ```

    - **`$dockerUser`**: Seu nome de usuário no Docker Hub.
    - **`$imageName`**: Nome que você deseja dar à sua imagem Docker.
    - **`$imageTag`**: Tag da imagem, usada para versionamento.

2. **Construir a Imagem Docker**

   ```powershell
   docker build -t "$dockerUser/$imageName:$imageTag" .
   ```

    - O comando constrói a imagem usando o `Dockerfile` no diretório atual.
    - Nomeia a imagem como `rbrocchi/foodarch:v1.0`.

3. **Verificar o Resultado do Build**

   ```powershell
   if ($LASTEXITCODE -ne 0) {
       Write-Host "Erro ao construir a imagem Docker."
       exit 1
   }
   ```

    - Verifica se o comando anterior foi bem-sucedido.
    - Se houve erro, exibe uma mensagem e encerra o script.

4. **Fazer Login no Docker Hub**

   ```powershell
   $dockerPassword = Read-Host -Prompt "Digite sua senha do Docker Hub" -AsSecureString
   $dockerPasswordPlain = [Runtime.InteropServices.Marshal]::PtrToStringAuto([Runtime.InteropServices.Marshal]::SecureStringToBSTR($dockerPassword))

   docker login -u $dockerUser -p $dockerPasswordPlain
   ```

    - **Segurança**: Usa `Read-Host` com `-AsSecureString` para ler a senha sem exibi-la no console.
    - Converte a senha para texto simples apenas para o comando `docker login`.
    - **Nota**: Assegure-se de não armazenar a senha em texto simples ou expô-la em logs.

5. **Verificar o Resultado do Login**

   ```powershell
   if ($LASTEXITCODE -ne 0) {
       Write-Host "Erro ao fazer login no Docker Hub."
       exit 1
   }
   ```

6. **Enviar a Imagem para o Docker Hub**

   ```powershell
   docker push "$dockerUser/$imageName:$imageTag"
   ```

7. **Verificar o Resultado do Push**

   ```powershell
   if ($LASTEXITCODE -ne 0) {
       Write-Host "Erro ao enviar a imagem para o Docker Hub."
       exit 1
   }
   ```

8. **Logout do Docker Hub (Opcional)**

   ```powershell
   docker logout
   ```

    - Encerra a sessão do Docker Hub para maior segurança.

---

## Executando o Script

1. **Abrir o PowerShell**

    - Certifique-se de abrir o PowerShell na pasta onde o script está localizado.

2. **Permitir a Execução de Scripts (se necessário)**

    - O PowerShell, por padrão, pode bloquear a execução de scripts não assinados.
    - Para permitir a execução, você pode alterar a política de execução (como administrador):

      ```powershell
      Set-ExecutionPolicy -ExecutionPolicy RemoteSigned -Scope CurrentUser
      ```

        - **Aviso**: Alterar a política de execução pode ter implicações de segurança. Retorne à configuração padrão após executar o script, se desejar.

3. **Executar o Script**

   ```powershell
   .build_and_push.ps1
   ```

    - O script solicitará sua senha do Docker Hub.

4. **Inserir sua Senha do Docker Hub**

    - Digite sua senha quando solicitado. A senha não será exibida enquanto você digita.

5. **Aguardar a Execução**

    - O script irá:

        - Construir a imagem Docker.
        - Fazer login no Docker Hub.
        - Enviar a imagem para o Docker Hub.
        - Exibir mensagens sobre o progresso.

---

## Considerações de Segurança

- **Senhas e Credenciais**

    - O script solicita a senha do Docker Hub de forma segura, sem exibi-la no console.
    - **Nunca armazene senhas em texto simples** dentro de scripts ou código fonte.
    - **Não compartilhe o script com a senha inserida**.

- **Uso de Tokens de Acesso (Recomendado)**

    - Em vez de usar sua senha do Docker Hub, é mais seguro usar um **token de acesso**.
    - Você pode gerar um token no Docker Hub em **Account Settings** > **Security** > **New Access Token**.
    - Use o token no lugar da senha quando o script solicitar.

---

## Personalizando o Script

- **Parâmetros do Script**

    - Você pode modificar o script para aceitar parâmetros, como a tag da imagem, nome da imagem, etc.

  **Exemplo de script com parâmetros:**

  ```powershell
  param (
      [string]$imageTag = "v1.0"
  )

  # Variáveis
  $dockerUser = "rbrocchi"
  $imageName = "foodarch"

  # Resto do script permanece o mesmo...
  ```

    - **Uso:**

      ```powershell
      .build_and_push.ps1 -imageTag "v1.1"
      ```

- **Tratamento de Erros**

    - O script já verifica se cada comando foi executado com sucesso.
    - Você pode adicionar logs adicionais ou notificações conforme necessário.
