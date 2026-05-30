package model;


/**
 * Classe abstrata que representa uma pessoa no sistema.
 * É a classe mãe de Aluno e Professor.
 * Aplicação de herança e encapsulamento
 */
public abstract class Pessoa {

    private String nome;
    private String dataNascimento;
    private String telefone;
    private Endereco endereco;

    public Pessoa(String nome, String dataNascimento, String telefone, Endereco endereco) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
        this.endereco = endereco;
    }

    // Getters e Setters
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(String dataNascimento) { this.dataNascimento = dataNascimento; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public Endereco getEndereco() { return endereco; }
    public void setEndereco(Endereco endereco) { this.endereco = endereco; }

    public abstract String toString();
}
