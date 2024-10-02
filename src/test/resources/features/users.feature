#language: pt

Funcionalidade: Usuários Novos

  Cenário: Criar usuário novo com sucesso
    Quando eu faço um POST para o endpoint users com o nome "Rodrigo" e data de nascimento "1987-05-15" que é o usuário novo
    Então o código de resposta do usuário novo deve ser 201
    E o id do usuário novo deve ser um UUID
    E o nome do usuário novo retornado deve ser "Rodrigo"
    E o email do usuário novo retornado deve ser o gerado
    E a data de nascimento do usuário novo retornada deve ser "1987-05-15"
    E o CPF do usuário novo retornado deve ser o gerado
