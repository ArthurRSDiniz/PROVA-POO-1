package model;

/**
 * Representa uma nota de um aluno em uma disciplina.
 * Cada nota registra o valor (double) e a data em que foi atribuída.
 */
public class Nota {

    private Aluno aluno;
    private Disciplina disciplina;
    private double valor;
    private String data;

    public Nota(Aluno aluno, Disciplina disciplina, double valor, String data) {
        this.aluno = aluno;
        this.disciplina = disciplina;
        this.valor = valor;
        this.data = data;
    }

    // Getters e Setters
    public Aluno getAluno() { return aluno; }
    public void setAluno(Aluno aluno) { this.aluno = aluno; }

    public Disciplina getDisciplina() { return disciplina; }
    public void setDisciplina(Disciplina disciplina) { this.disciplina = disciplina; }

    public double getValor() { return valor; }
    public void setValor(double valor) { this.valor = valor; }

    public String getData() { return data; }
    public void setData(String data) { this.data = data; }

    @Override
    public String toString() {
        return "Nota: " + valor +
                " | Disciplina: " + disciplina.getNomeDisciplina() +
                " | Data: " + data;
    }

    /** Converte para linha de texto para salvar no arquivo .txt */
    public String paraArquivo() {
        return aluno.getMatricula() + ";" +
                disciplina.getCodigo() + ";" +
                valor + ";" +
                data;
    }
}
