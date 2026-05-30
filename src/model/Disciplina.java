package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa uma disciplina do sistema escolar.
 * Possui uma lista de professores responsáveis por lecionar a disciplina.
 */
public class Disciplina {

    private String nomeDisciplina;
    private int cargaHoraria;
    private int codigo;
    private List<Professor> professores;

    public Disciplina(String nomeDisciplina, int cargaHoraria, int codigo) {
        this.nomeDisciplina = nomeDisciplina;
        this.cargaHoraria = cargaHoraria;
        this.codigo = codigo;
        this.professores = new ArrayList<>();
    }

    // Getters e Setters
    public String getNomeDisciplina() { return nomeDisciplina; }
    public void setNomeDisciplina(String nomeDisciplina) { this.nomeDisciplina = nomeDisciplina; }

    public int getCargaHoraria() { return cargaHoraria; }
    public void setCargaHoraria(int cargaHoraria) { this.cargaHoraria = cargaHoraria; }

    public int getCodigo() { return codigo; }
    public void setCodigo(int codigo) { this.codigo = codigo; }

    public List<Professor> getProfessores() { return professores; }

    public void adicionarProfessor(Professor professor) {
        this.professores.add(professor);
    }

    @Override
    public String toString() {
        return "Disciplina: " + nomeDisciplina +
                " | Codigo: " + codigo +
                " | Carga Horaria: " + cargaHoraria + "h";
    }

    /** Converte para linha de texto para salvar no arquivo .txt */
    public String paraArquivo() {
        return codigo + ";" + nomeDisciplina + ";" + cargaHoraria;
    }

    /** Reconstrói uma Disciplina a partir de uma linha do arquivo .txt */
    public static Disciplina deArquivo(String linha) {
        String[] p = linha.split(";");
        return new Disciplina(p[1], Integer.parseInt(p[2]), Integer.parseInt(p[0]));
    }
}

