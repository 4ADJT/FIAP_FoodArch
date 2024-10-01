#language: pt

Funcionalidade: Usuários

  Cenário: Criar usuário com sucesso
    Quando eu faço um POST para o endpoint users com o nome "Rodrigo", email "rodrigo@example.com", data de nascimento "1987-05-15" e CPF "51223513092"
    Então o código de resposta deve ser 201
    E o id do usuário deve ser um UUID
    E o nome retornado deve ser "Rodrigo"
    E o email retornado deve ser "rodrigo@example.com"
    E a data de nascimento retornada deve ser "1987-05-15"
    E o CPF retornado deve ser "51223513092"

  Cenário: Criar usuário com CPF ou email já existente
    Quando eu faço um POST para o endpoint users com o nome "Rodrigo", email "rodrigo@example.com", data de nascimento "1987-05-15" e CPF "51223513092"
    Então o código de resposta deve ser 400
    E a mensagem de erro deve ser "User already exists."