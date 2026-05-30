package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa um professor do sistema.
 * Herda os atributos de Pessoa e adiciona
 * área de formação, ano de admissão, e-mail e lista de disciplinas.
 */
public class Professor extends Pessoa {

    private String areaDeFormacao;
    private int anoDeAdmissao;
    private String email;
    private List<Disciplina> disciplinas;

    public Professor(String nome, String dataNascimento, String telefone,
                     Endereco endereco, String areaDeFormacao,
                     int anoDeAdmissao, String email) {
        super(nome, dataNascimento, telefone, endereco);
        this.areaDeFormacao = areaDeFormacao;
        this.anoDeAdmissao = anoDeAdmissao;
        this.email = email;
        this.disciplinas = new ArrayList<>();
    }

    // Getters e Setters
    public String getAreaDeFormacao() { return areaDeFormacao; }
    public void setAreaDeFormacao(String areaDeFormacao) { this.areaDeFormacao = areaDeFormacao; }

    public int getAnoDeAdmissao() { return anoDeAdmissao; }
    public void setAnoDeAdmissao(int anoDeAdmissao) { this.anoDeAdmissao = anoDeAdmissao; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public List<Disciplina> getDisciplinas() { return disciplinas; }

    public void adicionarDisciplina(Disciplina disciplina) {
        this.disciplinas.add(disciplina);
    }

    @Override
    public String toString() {
        return "Professor: " + getNome() +
                " | Formacao: " + areaDeFormacao +
                " | Admissao: " + anoDeAdmissao +
                " | Email: " + email +
                " | Tel: " + getTelefone();
    }

    /** Converte para linha de texto para salvar no arquivo .txt */
    public String paraArquivo() {
        return getNome() + ";" +
                getDataNascimento() + ";" +
                getTelefone() + ";" +
                getEndereco().paraArquivo() + ";" +
                areaDeFormacao + ";" +
                anoDeAdmissao + ";" +
                email;
    }

    public static Professor deArquivo(String linha) {
        String[] p = linha.split(";");
        // Ordem: nome;dataNasc;telefone;rua;numero;bairro;cep;areaForm;anoAdm;email
        Endereco endereco = new Endereco(p[3], Integer.parseInt(p[4]), p[5], p[6]);
        return new Professor(p[0], p[1], p[2], endereco, p[7], Integer.parseInt(p[8]), p[9]);
    }
}

