# 🚀 Gestão de Vagas - Backend API (Java + Spring Boot)

Sistema backend desenvolvido em **Java 17 + Spring Boot 3**, responsável por gerenciar vagas de emprego, empresas e candidatos com autenticação via JWT e controle de permissões por perfil.

O projeto oferece uma API REST completa com suporte a:

✅ Empresas criando vagas  
✅ Candidatos criando conta e acessando perfil  
✅ Autenticação separada para Candidate e Company  
✅ Segurança com JWT + Roles  
✅ Docker + Postgres + Hot Reload para desenvolvimento

---

## 📌 Tecnologias Utilizadas

- Java 17
- Spring Boot 3.5
- Spring Security
- JWT Authentication (Auth0 Java JWT)
- Spring Data JPA
- PostgreSQL
- Lombok
- Docker + Docker Compose
- Spring Boot DevTools (Hot Reload)

---

## 📂 Estrutura do Projeto

```
src/main/java/br/com/rh/gestaovagas
│
├── modules
│   ├── company
│   │   ├── controllers
│   │   ├── dto
│   │   ├── entities
│   │   ├── repositories
│   │   └── usecases
│   │
│   └── candidate
│       ├── controllers
│       ├── dto
│       ├── entities
│       ├── repositories
│       └── usecases
│
├── providers
│   ├── JWTProvider
│   └── JWTCandidateProvider
│
└── security
    ├── SecurityConfig
    ├── SecurityFilter
    └── SecurityCandidateFilter
```

---

# ✅ Autenticação e Roles

O sistema possui dois tipos de usuários:

| Tipo | Role JWT |
|------|----------|
| Empresa | `COMPANY` |
| Candidato | `CANDIDATE` |

Cada tipo possui filtro e segredo JWT separado:

- `security.token.secret` → Empresa
- `security.token.secret.candidate` → Candidato

---

# ✅ Endpoints Principais

---

## 🔑 Login Empresa

### `POST /auth/company`

**Request**

```json
{
  "username": "java_company",
  "password": "1234567890"
}
```

**Response**

```json
{
  "access_token": "JWT_TOKEN",
  "expires_in": 7200
}
```

---

## 🔑 Login Candidato

### `POST /auth/candidate`

```json
{
  "username": "candidate_user",
  "password": "1234567890"
}
```

**Response**

```json
{
  "access_token": "JWT_TOKEN",
  "expires_in": 7200
}
```

---

## 🏢 Criar Vaga (Empresa)

### `POST /company/job`

🔒 **Requer token com role COMPANY**

**Authorization**

```
Bearer {token}
```

**Request**

```json
{
  "description": "Vaga Java Jr",
  "benefits": "GymPass + Plano Saúde",
  "level": "JUNIOR"
}
```

**Response**

```json
{
  "id": "uuid",
  "description": "Vaga Java Jr",
  "benefits": "GymPass + Plano Saúde",
  "level": "JUNIOR",
  "companyId": "uuid"
}
```

---

## 👤 Perfil do Candidato

### `GET /candidate/`

🔒 **Requer token com role CANDIDATE**

**Response**

```json
{
  "id": "uuid",
  "name": "João Silva",
  "username": "joao123",
  "email": "joao@email.com",
  "description": "Dev backend"
}
```

---

# ✅ Rodando com Docker (Produção)

## 1. Criar arquivo `.env`

```env
DB_USER=admin
DB_PASSWORD=admin
DB_NAME=gestao_vagas
JWT_SECRET=JAVAGAS_@123
```

## 2. Subir aplicação

```bash
docker compose up --build
```

API disponível em:

```
http://localhost:8080
```

---

# ✅ Rodando com Hot Reload no Docker (Desenvolvimento)

Usando `docker-compose.dev.yml` + DevTools + Volume Mount.

### Subir modo DEV

```bash
docker compose -f docker-compose.dev.yml up --build
```

Agora, ao alterar qualquer arquivo Java em:

```
src/main/java/**
```

o Spring reinicia automaticamente dentro do container ✅

---

## Verificando Hot Reload

No log, procure:

```
Restarting due to classpath change
```

ou

```
LiveReload server is running on port 35729
```

---

# ✅ Melhorias Futuras

- [ ] Listagem pública de vagas
- [ ] Inscrição de candidatos em vagas
- [ ] Refresh Token
- [ ] Deploy com AWS ou Render
- [ ] Documentação Swagger/OpenAPI

---

# 👨‍💻 Autor

Projeto desenvolvido por **Nicole Bidigaray**  
Backend Java + Spring Boot + Docker + JWT

---