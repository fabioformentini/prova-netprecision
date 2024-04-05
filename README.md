# Sistema De Lanchonete

Principais tecnologias utilizadas: Spring 3.2.4, Angular v16, Docker, Postgres.

Url externa para acessar a aplicação: http://18.218.117.255/pedidos

# Ambiente Local:

## Configurando o Banco de Dados (Docker)
Certifique-se de ter o Docker instalado em sua máquina.

Abra um terminal e navegue até a raiz do seu projeto onde está localizado o arquivo Docker Compose.

Execute o seguinte comando para criar e iniciar o banco de dados PostgreSQL (certifique-se de que a porta 5432 esteja livre):

docker-compose up -d

## Configurando o Aplicação Angular
Pré requisitos: node v16.

Abra um terminal e navegue até a pasta do projeto Angular.

Execute o seguinte comando para instalar as dependências do Angular:
```
npm install
```

Após a instalação das dependências, execute o seguinte comando para iniciar o servidor de desenvolvimento do Angular:
```
ng serve
```

O aplicativo estará disponível em http://localhost:4200/.

## Configurando a Aplicação Spring
Pré requisitos: jdk17 e maven.

Abra a sua IDE.

Importe o projeto Spring para sua IDE.

Certifique-se de que o container do banco de dados esteja up.

Execute a classe LanchoneteserviceApplication
