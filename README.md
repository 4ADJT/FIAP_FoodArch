# FoodArch

Bem-vindo ao **FoodArch**! 🍽️

Uma nova maneira de conectar você aos melhores restaurantes da cidade. Simplifique suas reservas, descubra novos sabores e compartilhe suas experiências gastronômicas.

## Índice

- [Sobre o Projeto](#sobre-o-projeto)
- [Funcionalidades Principais](#funcionalidades-principais)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Pré-requisitos](#pré-requisitos)
- [Como Executar](#como-executar)
- [Documentação da API](#documentação-da-api)
- [Como Contribuir](#como-contribuir)
- [Contato](#contato)

## Sobre o Projeto

O **FoodArch** é uma plataforma desenvolvida em Java com Maven, projetada para revolucionar a forma como restaurantes e clientes interagem. Com uma interface intuitiva e funcionalidades robustas, oferecemos uma experiência única para encontrar, reservar e avaliar restaurantes.

## Funcionalidades Principais

1. **Cadastro de Restaurantes**

    - Restaurantes podem criar um perfil detalhado, incluindo:
        - Nome do estabelecimento
        - Localização e mapa
        - Tipo de cozinha (italiana, japonesa, brasileira, etc.)
        - Horários de funcionamento
        - Capacidade de atendimento

2. **Reserva de Mesas**

    - Usuários podem reservar mesas facilmente para datas e horários desejados, garantindo sua experiência gastronômica sem filas ou esperas.

3. **Avaliações e Comentários**

    - Compartilhe suas experiências! Após a visita, avalie o restaurante e deixe comentários que possam ajudar outros usuários.

4. **Busca de Restaurantes**

    - Encontre o lugar perfeito com nossas ferramentas de busca e filtros:
        - Pesquise por nome
        - Filtre por localização próxima
        - Escolha o tipo de cozinha que mais agrada ao seu paladar

5. **Gerenciamento de Reservas**

    - Restaurantes têm acesso a um painel de controle para:
        - Visualizar reservas pendentes e confirmadas
        - Atualizar o status das mesas
        - Organizar o fluxo de clientes de forma eficiente

## Tecnologias Utilizadas

- **Linguagem:** Java
- **Gerenciador de Dependências:** Maven
- **Containerização:** Docker (com Docker Compose para execução simplificada)
- **Documentação da API:** Swagger (acessível em `/doc`)

## Pré-requisitos

Certifique-se de ter os seguintes itens instalados:

- [Java JDK 8 ou superior](https://www.oracle.com/java/technologies/javase-downloads.html)
- [Maven 3 ou superior](https://maven.apache.org/download.cgi)
- [Docker](https://www.docker.com/get-started)
- [Docker Compose](https://docs.docker.com/compose/install/)
- [Git](https://git-scm.com/)

## Como Executar

Siga os passos abaixo para configurar e executar o **FoodArch**:

1. **Clone este repositório:**

   ```bash
   git clone https://github.com/4ADJT/FIAP_FoodArch.git
   ```

2. **Acesse o diretório do projeto:**

   ```bash
   cd FIAP_FoodArch
   ```

3. **Execute o Docker Compose:**

    - Inicie a aplicação com o comando padrão:

      ```bash
      docker-compose up
      ```

    - Isso irá baixar as dependências, construir a imagem Docker e iniciar os serviços necessários.

4. **Acesse a aplicação:**

    - Após a inicialização, a aplicação estará disponível em:

      ```
      http://localhost:8080
      ```

    - Você também pode acessar a versão ao vivo em:

      ```
      https://fiap-foodarch.onrender.com/
      ```

## Documentação da API

A documentação interativa da API está disponível em:

```
http://localhost:8080/doc
```

Utilize esta interface para explorar os endpoints, testar as funcionalidades e integrar o **FoodArch** em outras aplicações.

## Como Contribuir

Quer fazer parte desta jornada gastronômica? Veja como você pode contribuir:

1. **Faça um fork do projeto.**

2. **Crie uma branch para sua funcionalidade:**

   ```bash
   git checkout -b minha-nova-funcionalidade
   ```

3. **Implemente suas mudanças e faça commit:**

   ```bash
   git commit -m 'Adiciona nova funcionalidade'
   ```

4. **Envie suas alterações para o seu fork:**

   ```bash
   git push origin minha-nova-funcionalidade
   ```

5. **Abra um Pull Request no repositório original.**

Agradecemos antecipadamente por sua contribuição!

## Contato

Se você tiver alguma dúvida, sugestão ou quiser simplesmente dizer "olá", sinta-se à vontade para abrir uma [issue](https://github.com/4ADJT/FIAP_FoodArch/issues).

---

_Aproveite sua experiência com o **FoodArch** e descubra um mundo de sabores ao seu alcance!_

---

**Link do Repositório:** [https://github.com/4ADJT/FIAP_FoodArch](https://github.com/4ADJT/FIAP_FoodArch)

**Live Preview:** [https://fiap-foodarch.onrender.com/](https://fiap-foodarch.onrender.com/)

---