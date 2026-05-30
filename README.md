# 🏫 Sistema de Gerenciamento Escolar

Trabalho acadêmico desenvolvido para a disciplina de **Programação Orientada a Objetos I** — Anhanguera.

---

## 📋 Sobre o Projeto

Sistema de gerenciamento escolar desenvolvido em **Java**, com o objetivo de gerenciar alunos, professores, disciplinas, turmas e notas por meio de um menu interativo no terminal.

---

## ✅ Funcionalidades

- Cadastrar Aluno
- Cadastrar Professor
- Cadastrar Disciplina
- Cadastrar Turma
- Inserir Nota
- Relatório de Alunos
- Relatório de Professores
- Relatório de Disciplinas
- Relatório de Turmas
- Relatório de Aluno x Notas x Média
- Quantidade de Alunos Cadastrados
- Aluno com a Maior Nota
- Listar Alunos por Disciplina
- Listar Turmas por Professor

---

## 🧱 Conceitos de POO Aplicados

| Conceito | Onde foi aplicado |
|---|---|
| **Herança** | `Aluno` e `Professor` herdam de `Pessoa` |
| **Classe Abstrata** | `Pessoa` é abstrata |
| **Encapsulamento** | Atributos privados com getters/setters em todas as classes |
| **Polimorfismo** | `toString()` sobrescrito em cada subclasse |
| **Interfaces** | `GerenciadorCadastroAluno` e `GerenciadorCadastroProfessor` |
| **SOLID** | SRP (Secretaria/ArquivoUtil), ISP (interfaces separadas) |

---

## 📁 Estrutura do Projeto

```
src/
├── Main.java
├── model/
│   ├── Pessoa.java
│   ├── Aluno.java
│   ├── Professor.java
│   ├── Disciplina.java
│   ├── Turma.java
│   ├── Nota.java
│   └── Endereco.java
├── interfaces/
│   ├── GerenciadorCadastroAluno.java
│   └── GerenciadorCadastroProfessor.java
├── service/
│   └── Secretaria.java
└── util/
    └── ArquivoUtil.java
```

---

## 💾 Persistência de Dados

Todos os dados são salvos e carregados automaticamente em arquivos `.txt`:

```
alunos.txt
professores.txt
disciplinas.txt
turmas.txt
notas.txt
```

---

## 🚀 Como Executar

1. Clone o repositório
2. Abra o projeto no **IntelliJ IDEA**
3. Execute a classe `Main.java`
4. Navegue pelo menu no terminal

---

## 🛠️ Tecnologias

- Java 17+
- IntelliJ IDEA


Desenvolvido como trabalho acadêmico — POO I, Anhanguera.
```
