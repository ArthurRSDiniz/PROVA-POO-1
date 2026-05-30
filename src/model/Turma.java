package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa uma turma da escola.
 * Associa uma disciplina e um professor a um grupo de alunos.
 */
public class Turma {

    private int codigoTurma;
    private Disciplina disciplina;
    private Professor professor;
    private List<Aluno> alunos;
    private int anoLetivo;

    public Turma(int codigoTurma, Disciplina disciplina, Professor professor, int anoLetivo) {
        this.codigoTurma = codigoTurma;
        this.disciplina = disciplina;
        this.professor = professor;
        this.anoLetivo = anoLetivo;
        this.alunos = new ArrayList<>();
    }

    // Getters e Setters
    public int getCodigoTurma() { return codigoTurma; }
    public void setCodigoTurma(int codigoTurma) { this.codigoTurma = codigoTurma; }

    public Disciplina getDisciplina() { return disciplina; }
    public void setDisciplina(Disciplina disciplina) { this.disciplina = disciplina; }

    public Professor getProfessor() { return professor; }
    public void setProfessor(Professor professor) { this.professor = professor; }

    public List<Aluno> getAlunos() { return alunos; }

    public int getAnoLetivo() { return anoLetivo; }
    public void setAnoLetivo(int anoLetivo) { this.anoLetivo = anoLetivo; }

    public void adicionarAluno(Aluno aluno) {
        this.alunos.add(aluno);
    }

    @Override
    public String toString() {
        return "Turma: " + codigoTurma +
                " | Disciplina: " + disciplina.getNomeDisciplina() +
                " | Professor: " + professor.getNome() +
                " | Ano Letivo: " + anoLetivo +
                " | Alunos: " + alunos.size();
    }

    /** Converte para linha de texto para salvar no arquivo .txt */
    public String paraArquivo() {
        return codigoTurma + ";" +
                disciplina.getCodigo() + ";" +
                professor.getNome() + ";" +
                anoLetivo;
    }
}

