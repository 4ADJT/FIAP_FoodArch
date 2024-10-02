#language: pt

Funcionalidade: Gestão de Restaurantes
  Cenário: Criar restaurante com sucesso
    Dado que eu obtenha um usuário existente para o restaurante
    Quando eu faço um POST para o endpoint de restaurante com o nome "Restaurante Teste"
    Então o código de resposta do restaurante deve ser 201
    E o nome do restaurante retornado deve ser "Restaurante Teste"
