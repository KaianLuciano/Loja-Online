# Loja Online

Loja online é atualmente uma API REST que irá gerenciar as requisições de um futuro front-end a ser desenvolvido, fazendo assim o gerenciamento dos dados.

## Features

- ✅ Cadastro de usuário
- ✅ Cadastro de Pedido
- ✅ Cadastro de Produto
- ✅ Associar um pedido a um usuário
- ✅ Associar um produto a um pedido
- ✅ Autenticação de usuários (registro, login e obtenção de token de acesso)
- ✅ Endpoint para processar pagamentos usando uma integração com um gateway de pagamento como PayPal ou Stripe
- ✅ Gerenciamento de estoque: atualizar a quantidade em estoque ao criar um pedido e ao receber notificações de entregas
- ✅ Endpoint para realizar pesquisas de produtos usando recursos avançados, como filtros de preços, classificações, etc.
- ✅ Implementar cache para melhorar o desempenho das consultas de produtos frequentes

# Gerenciamento de produtos
- ✦ Listar todos os produtos
- ✦ Buscar um produto por ID
- ✦ Adicionar um novo produto
- ✦ Atualizar um produto existente
- ✦ Excluir um produto existente
- ✦ Filtrar produtos por categoria, preço, etc.

# Gerenciamento de pedidos
- ✦ Criar um novo pedido
- ✦ Atualizar o status de um pedido (por exemplo: pendente, enviado, entregue (Irá ser representado por ENUM)).
- ✦ Listar todos os pedidos de um usuário
- ✦ Filtrar pedidos por status, data, etc.

# Gerenciamento de compras

- ✦ Adicionar um produto ao carrinho
- ✦ Remover um produto do carrinho
- ✦ Visualizar o carrinho de compras de um usuário

# Técnicas e tecnologias utilizadas

- ``API REST``
- ``Java 17``
- ``Spring Boot``
- ``Spring Security``
- ``Hibernate/JPA``
- ``Postman``
- ``PostgreSQL``
- ``Lombok``
- ``Angular``
- ``Spring Data``
- ``Jackson Databind``
- ``Apache Maven``
- ``Angular (Á ser usado)``
- ``TypeScript (Á ser usado)``
- ``Html e CSS (Á ser usado)``


## Status do projeto

![Badge em Desenvolvimento](http://img.shields.io/static/v1?label=STATUS&message=EM%20DESENVOLVIMENTO&color=GREEN&style=for-the-badge)
