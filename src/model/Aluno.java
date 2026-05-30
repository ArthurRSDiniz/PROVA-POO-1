package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa um aluno do sistema.
 * Herda os atributos de Pessoa e adiciona matrícula,
 * ano de ingresso, turma e lista de notas.
 */
public class Aluno extends Pessoa {

    private int matricula;
    private int anoIngresso;
    private Turma turma;
    private List<Nota> notas;

    public Aluno(String nome, String dataNascimento, String telefone,
                 Endereco endereco, int matricula, int anoIngresso) {
        super(nome, dataNascimento, telefone, endereco);
        this.matricula = matricula;
        this.anoIngresso = anoIngresso;
        this.notas = new ArrayList<>();
    }

    // Getters e Setters
    public int getMatricula() { return matricula; }
    public void setMatricula(int matricula) { this.matricula = matricula; }

    public int getAnoIngresso() { return anoIngresso; }
    public void setAnoIngresso(int anoIngresso) { this.anoIngresso = anoIngresso; }

    public Turma getTurma() { return turma; }
    public void setTurma(Turma turma) { this.turma = turma; }

    public List<Nota> getNotas() { return notas; }

    public void adicionarNota(Nota nota) {
        this.notas.add(nota);
    }

    /** Calcula a média de todas as notas do aluno */
    public double calcularMedia() {
        if (notas.isEmpty()) return 0.0;
        double soma = 0;
        for (Nota nota : notas) {
            soma += nota.getValor();
        }
        return soma / notas.size();
    }

    /** Retorna a maior nota do aluno entre todas as disciplinas */
    public double obterMaiorNota() {
        if (notas.isEmpty()) return 0.0;
        double maior = notas.get(0).getValor();
        for (Nota nota : notas) {
            if (nota.getValor() > maior) {
                maior = nota.getValor();
            }
        }
        return maior;
    }

    @Override
    public String toString() {
        return "Aluno: " + getNome() +
                " | Matricula: " + matricula +
                " | Ingresso: " + anoIngresso +
                " | Tel: " + getTelefone();
    }

    /** Converte para linha de texto para salvar no arquivo .txt */
    public String paraArquivo() {
        return matricula + ";" +
                getNome() + ";" +
                getDataNascimento() + ";" +
                getTelefone() + ";" +
                getEndereco().paraArquivo() + ";" +
                anoIngresso;
    }

    /** Reconstrói um Aluno a partir de uma linha do arquivo .txt */
    public static Aluno deArquivo(String linha) {
        String[] p = linha.split(";");
        // Ordem: matricula;nome;dataNasc;telefone;rua;numero;bairro;cep;anoIngresso
        Endereco endereco = new Endereco(p[4], Integer.parseInt(p[5]), p[6], p[7]);
        return new Aluno(p[1], p[2], p[3], endereco, Integer.parseInt(p[0]), Integer.parseInt(p[8]));
    }
}

