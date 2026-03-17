# Mini Spotify API

API REST em Spring Boot para simular um mini Spotify de forma direta, com foco em CRUD e nas regras de negocio do enunciado.

## Stack

- Java 25
- Spring Boot
- Spring Web
- Spring Data JPA
- H2 Database

## Estrutura

- `controller`: endpoints REST
- `service`: regras de negocio
- `repository`: acesso a dados
- `model`: entidades
- `dto`: apenas retorno do relatorio de top musicas
- `exception`: tratamento padrao de erros

## Entidades

- `User`
- `Artist`
- `Album`
- `Song`
- `Playlist`
- `PlaybackHistory`

Todas possuem CRUD completo. A exclusao e logica, usando o campo `ativo`.

## Regras de negocio implementadas

- `POST /musicas/{id}/reproduzir`
  - recebe `X-USER-ID`
  - incrementa `totalReproducoes`
  - bloqueia usuario inativo
  - registra em `PlaybackHistory`
- `POST /playlists/{playlistId}/musicas/{musicaId}`
  - recebe `X-USER-ID`
  - apenas o dono da playlist pode adicionar
  - nao permite musica duplicada
- `GET /relatorios/top-musicas`
  - retorna as 10 musicas mais reproduzidas

## Como rodar

```powershell
.\mvnw.cmd spring-boot:run
```

Base da API:

```text
http://localhost:8080
```

## H2 Console

- URL: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:minispotify`
- User: `sa`
- Password: vazio

## Formato dos payloads

O CRUD foi simplificado para usar as proprias entidades no JSON. Quando uma entidade depende de outra, envie o relacionamento com um objeto contendo o `id`.

### Criar usuario

```json
{
  "nome": "Ana",
  "email": "ana@email.com",
  "tipoPlano": "PREMIUM",
  "ativo": true
}
```

### Criar artista

```json
{
  "nome": "Daft Punk",
  "generoMusical": "Eletronica",
  "paisOrigem": "Franca",
  "ativo": true
}
```

### Criar album

```json
{
  "titulo": "Discovery",
  "dataLancamento": "2001-03-12",
  "artista": {
    "id": 1
  },
  "ativo": true
}
```

### Criar musica

```json
{
  "titulo": "One More Time",
  "duracaoSegundos": 320,
  "numeroFaixa": 1,
  "album": {
    "id": 1
  },
  "artista": {
    "id": 1
  },
  "ativo": true
}
```

### Criar playlist

```json
{
  "nome": "Favoritas",
  "publica": true,
  "usuario": {
    "id": 1
  },
  "ativo": true
}
```

### Criar historico de reproducao

```json
{
  "usuario": {
    "id": 1
  },
  "musica": {
    "id": 1
  },
  "ativo": true
}
```

## Ordem recomendada para testar

1. Criar usuario
2. Criar artista
3. Criar album
4. Criar musica
5. Criar playlist
6. Reproduzir musica
7. Adicionar musica a playlist
8. Consultar top musicas

## Endpoints

### Usuarios

- `POST /usuarios`
- `GET /usuarios`
- `GET /usuarios/{id}`
- `PUT /usuarios/{id}`
- `DELETE /usuarios/{id}`

### Artistas

- `POST /artistas`
- `GET /artistas`
- `GET /artistas/{id}`
- `PUT /artistas/{id}`
- `DELETE /artistas/{id}`

### Albuns

- `POST /albuns`
- `GET /albuns`
- `GET /albuns/{id}`
- `PUT /albuns/{id}`
- `DELETE /albuns/{id}`

### Musicas

- `POST /musicas`
- `GET /musicas`
- `GET /musicas/{id}`
- `PUT /musicas/{id}`
- `DELETE /musicas/{id}`
- `POST /musicas/{id}/reproduzir`

### Playlists

- `POST /playlists`
- `GET /playlists`
- `GET /playlists/{id}`
- `PUT /playlists/{id}`
- `DELETE /playlists/{id}`
- `POST /playlists/{playlistId}/musicas/{musicaId}`

### Historicos de reproducao

- `POST /historicos-reproducao`
- `GET /historicos-reproducao`
- `GET /historicos-reproducao/{id}`
- `PUT /historicos-reproducao/{id}`
- `DELETE /historicos-reproducao/{id}`

### Relatorios

- `GET /relatorios/top-musicas`

## Collection Postman

Arquivo:

- `MiniSpotify.postman_collection.json`

A collection inclui:

- CRUD completo das 6 entidades
- rotas de regra de negocio
- variaveis para `usuarioId`, `artistaId`, `albumId`, `musicaId`, `playlistId` e `historicoId`
