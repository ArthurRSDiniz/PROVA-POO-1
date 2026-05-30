package service;

import interfaces.GerenciadorCadastroAluno;
import interfaces.GerenciadorCadastroProfessor;
import model.*;
import util.ArquivoUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe central do sistema escolar.
 * Implementação as interfaces de cadastro e concentra toda a lógica d negócio
 * Gerencia listas em memória e persiste os dados em arquivos .txt
 */
public class Secretaria implements GerenciadorCadastroAluno, GerenciadorCadastroProfessor {

    // Listas em memória — carregadas dos arquivos ao iniciar
    private List<Aluno>      alunos;
    private List<Professor>  professores;
    private List<Disciplina> disciplinas;
    private List<Turma>      turmas;

    public Secretaria() {
        this.alunos      = new ArrayList<>();
        this.professores = new ArrayList<>();
        this.disciplinas = new ArrayList<>();
        this.turmas      = new ArrayList<>();
        carregarTodosDados();
    }

    //  IMPLEMENTAÇÃO DAS INTERFACES

    /** Cadastra um aluno na lista e salva no arquivo */
    @Override
    public void cadastrarAluno(Aluno aluno) {
        alunos.add(aluno);
        ArquivoUtil.adicionarLinha(ArquivoUtil.ARQUIVO_ALUNOS, aluno.paraArquivo());
        System.out.println("Aluno '" + aluno.getNome() + "' cadastrado com sucesso!");
    }

    /** Cadastra um professor na lista e salva no arquivo. */
    @Override
    public void cadastrarProfessor(Professor professor) {
        professores.add(professor);
        ArquivoUtil.adicionarLinha(ArquivoUtil.ARQUIVO_PROFESSORES, professor.paraArquivo());
        System.out.println("Professor '" + professor.getNome() + "' cadastrado com sucesso!");
    }

    //  CADASTRO DE DISCIPLINA E TURMA

    /** Cadastra uma disciplina na lista e salva no arquivo */
    public void cadastrarDisciplina(Disciplina disciplina) {
        disciplinas.add(disciplina);
        ArquivoUtil.adicionarLinha(ArquivoUtil.ARQUIVO_DISCIPLINAS, disciplina.paraArquivo());
        System.out.println("Disciplina '" + disciplina.getNomeDisciplina() + "' cadastrada com sucesso!");
    }

    /**
     * Cadastra uma turma  (só é permitido se professor e disciplina já existirem)
     * Retorna false se alguma restrição for violada
     */
    public boolean cadastrarTurma(int codigoTurma, String nomeDisciplina,
                                  String nomeProfessor, int anoLetivo) {
        Disciplina disciplina = buscarDisciplinaPorNome(nomeDisciplina);
        if (disciplina == null) {
            System.out.println("Erro: disciplina '" + nomeDisciplina + "' nao encontrada no sistema.");
            return false;
        }

        Professor professor = buscarProfessorPorNome(nomeProfessor);
        if (professor == null) {
            System.out.println("Erro: professor '" + nomeProfessor + "' nao encontrado no sistema.");
            return false;
        }

        Turma turma = new Turma(codigoTurma, disciplina, professor, anoLetivo);
        turmas.add(turma);
        ArquivoUtil.adicionarLinha(ArquivoUtil.ARQUIVO_TURMAS, turma.paraArquivo());
        System.out.println("Turma " + codigoTurma + " cadastrada com sucesso!");
        return true;
    }

    /**
     * Insere uma nota para um aluno em uma disciplina
     * Só é permitido se o aluno e a disciplina já existirem no sistema
     */
    public boolean inserirNota(int matriculaAluno, String nomeDisciplina,
                               double valor, String data) {
        Aluno aluno = buscarAlunoPorMatricula(matriculaAluno);
        if (aluno == null) {
            System.out.println("Erro: aluno com matricula " + matriculaAluno + " nao encontrado.");
            return false;
        }

        Disciplina disciplina = buscarDisciplinaPorNome(nomeDisciplina);
        if (disciplina == null) {
            System.out.println("Erro: disciplina '" + nomeDisciplina + "' nao encontrada.");
            return false;
        }

        Nota nota = new Nota(aluno, disciplina, valor, data);
        aluno.adicionarNota(nota);
        ArquivoUtil.adicionarLinha(ArquivoUtil.ARQUIVO_NOTAS, nota.paraArquivo());
        System.out.println("Nota " + valor + " inserida para " + aluno.getNome() + " com sucesso!");
        return true;
    }

    //  RELATÓRIOS

    /** Exibe todos os alunos cadastrados. */
    public void relatorioAlunos() {
        System.out.println("\n--- RELATORIO DE ALUNOS ---");
        if (alunos.isEmpty()) {
            System.out.println("Nenhum aluno cadastrado.");
            return;
        }
        for (Aluno a : alunos) {
            System.out.println(a);
        }
    }

    /** Exibe todos os professores cadastrados */
    public void relatorioProfessores() {
        System.out.println("\n--- RELATORIO DE PROFESSORES ---");
        if (professores.isEmpty()) {
            System.out.println("Nenhum professor cadastrado.");
            return;
        }
        for (Professor p : professores) {
            System.out.println(p);
        }
    }

    /** Exibe todas as disciplinas cadastradas */
    public void relatorioDisciplinas() {
        System.out.println("\n--- RELATORIO DE DISCIPLINAS ---");
        if (disciplinas.isEmpty()) {
            System.out.println("Nenhuma disciplina cadastrada.");
            return;
        }
        for (Disciplina d : disciplinas) {
            System.out.println(d);
        }
    }

    /** Exibe todas as turmas cadastradas. */
    public void relatorioTurmas() {
        System.out.println("\n--- RELATORIO DE TURMAS ---");
        if (turmas.isEmpty()) {
            System.out.println("Nenhuma turma cadastrada.");
            return;
        }
        for (Turma t : turmas) {
            System.out.println(t);
        }
    }

    /** Gera relatório com notas e média de um aluno específico */
    public void gerarRelatorioNotasEMediaPorAluno(int matriculaAluno) {
        Aluno aluno = buscarAlunoPorMatricula(matriculaAluno);
        if (aluno == null) {
            System.out.println("Aluno com matricula " + matriculaAluno + " nao encontrado.");
            return;
        }
        System.out.println("\n--- RELATORIO DE NOTAS: " + aluno.getNome() + " ---");
        if (aluno.getNotas().isEmpty()) {
            System.out.println("Nenhuma nota registrada.");
            return;
        }
        for (Nota n : aluno.getNotas()) {
            System.out.println(n);
        }
        System.out.printf("Media: %.2f%n", aluno.calcularMedia());
    }

    /** Retorna e exibe a quantidade de alunos cadastrados */
    public int obterQuantidadeAlunos() {
        System.out.println("\nTotal de alunos cadastrados: " + alunos.size());
        return alunos.size();
    }

    /** Exibe o nome do aluno com a maior nota individual cadastrada */
    public void obterAlunoComMaiorNota() {
        if (alunos.isEmpty()) {
            System.out.println("Nenhum aluno cadastrado.");
            return;
        }
        Aluno melhor = null;
        double maiorNota = -1;
        for (Aluno a : alunos) {
            double nota = a.obterMaiorNota();
            if (nota > maiorNota) {
                maiorNota = nota;
                melhor = a;
            }
        }
        if (melhor == null || maiorNota == 0.0) {
            System.out.println("Nenhuma nota registrada no sistema.");
        } else {
            System.out.println("\nAluno com maior nota: " + melhor.getNome() +
                    " | Nota: " + maiorNota);
        }
    }

    /** Lista todos os alunos matriculados em uma determinada disciplina */
    public void listarAlunosPorDisciplina(String nomeDisciplina) {
        System.out.println("\n--- ALUNOS DA DISCIPLINA: " + nomeDisciplina + " ---");
        boolean encontrou = false;
        for (Turma t : turmas) {
            if (t.getDisciplina().getNomeDisciplina().equalsIgnoreCase(nomeDisciplina)) {
                for (Aluno a : t.getAlunos()) {
                    System.out.println(a);
                    encontrou = true;
                }
            }
        }
        if (!encontrou) {
            System.out.println("Nenhum aluno encontrado para esta disciplina.");
        }
    }

    /** Lista todas as turmas sob responsabilidade de um determinado professor. */
    public void listarTurmasPorProfessor(String nomeProfessor) {
        System.out.println("\n--- TURMAS DO PROFESSOR: " + nomeProfessor + " ---");
        boolean encontrou = false;
        for (Turma t : turmas) {
            if (t.getProfessor().getNome().equalsIgnoreCase(nomeProfessor)) {
                System.out.println(t);
                encontrou = true;
            }
        }
        if (!encontrou) {
            System.out.println("Nenhuma turma encontrada para este professor.");
        }
    }

    public Aluno buscarAlunoPorMatricula(int matricula) {
        for (Aluno a : alunos) {
            if (a.getMatricula() == matricula) return a;
        }
        return null;
    }

    public Professor buscarProfessorPorNome(String nome) {
        for (Professor p : professores) {
            if (p.getNome().equalsIgnoreCase(nome)) return p;
        }
        return null;
    }

    public Disciplina buscarDisciplinaPorNome(String nome) {
        for (Disciplina d : disciplinas) {
            if (d.getNomeDisciplina().equalsIgnoreCase(nome)) return d;
        }
        return null;
    }

    public Turma buscarTurmaPorCodigo(int codigo) {
        for (Turma t : turmas) {
            if (t.getCodigoTurma() == codigo) return t;
        }
        return null;
        }

    /**
     * Carrega todos os dados dos arquivos .txt para as listas em memória.
     * Chamado automaticamente ao instanciar a Secretaria.
     * A ordem de carga importa: disciplinas e professores antes das turmas;
     * alunos antes das notas
     */
    private void carregarTodosDados() {
        carregarDisciplinas();
        carregarProfessores();
        carregarAlunos();
        carregarTurmas();
        carregarNotas();
    }

    private void carregarDisciplinas() {
        List<String> linhas = ArquivoUtil.lerLinhas(ArquivoUtil.ARQUIVO_DISCIPLINAS);
        for (String linha : linhas) {
            try {
                Disciplina d = Disciplina.deArquivo(linha);
                disciplinas.add(d);
            } catch (Exception e) {
                System.out.println("Aviso: linha invalida em disciplinas.txt -> " + linha);
            }
        }
    }

    private void carregarProfessores() {
        List<String> linhas = ArquivoUtil.lerLinhas(ArquivoUtil.ARQUIVO_PROFESSORES);
        for (String linha : linhas) {
            try {
                Professor p = Professor.deArquivo(linha);
                professores.add(p);
            } catch (Exception e) {
                System.out.println("Aviso: linha invalida em professores.txt -> " + linha);
            }
        }
    }

    private void carregarAlunos() {
        List<String> linhas = ArquivoUtil.lerLinhas(ArquivoUtil.ARQUIVO_ALUNOS);
        for (String linha : linhas) {
            try {
                Aluno a = Aluno.deArquivo(linha);
                alunos.add(a);
            } catch (Exception e) {
                System.out.println("Aviso: linha invalida em alunos.txt -> " + linha);
            }
        }
    }

    private void carregarTurmas() {
        List<String> linhas = ArquivoUtil.lerLinhas(ArquivoUtil.ARQUIVO_TURMAS);
        for (String linha : linhas) {
            try {
                String[] p = linha.split(";");
                int codTurma    = Integer.parseInt(p[0]);
                int codDisc     = Integer.parseInt(p[1]);
                String nomeProf = p[2];
                int anoLetivo   = Integer.parseInt(p[3]);

                Disciplina disc = buscarDisciplinaPorCodigo(codDisc);
                Professor prof  = buscarProfessorPorNome(nomeProf);

                if (disc != null && prof != null) {
                    turmas.add(new Turma(codTurma, disc, prof, anoLetivo));
                }
            } catch (Exception e) {
                System.out.println("Aviso: linha invalida em turmas.txt -> " + linha);
            }
        }
    }

    private void carregarNotas() {
        List<String> linhas = ArquivoUtil.lerLinhas(ArquivoUtil.ARQUIVO_NOTAS);
        for (String linha : linhas) {
            try {
                String[] p      = linha.split(";");
                int matricula   = Integer.parseInt(p[0]);
                int codDisc     = Integer.parseInt(p[1]);
                double valor    = Double.parseDouble(p[2]);
                String data     = p[3];

                Aluno aluno       = buscarAlunoPorMatricula(matricula);
                Disciplina disc   = buscarDisciplinaPorCodigo(codDisc);

                if (aluno != null && disc != null) {
                    Nota nota = new Nota(aluno, disc, valor, data);
                    aluno.adicionarNota(nota);
                }
            } catch (Exception e) {
                System.out.println("Aviso: linha invalida em notas.txt -> " + linha);
            }
        }
    }

    // Busca auxiliar por código (usada na carga de arquivos)
    private Disciplina buscarDisciplinaPorCodigo(int codigo) {
        for (Disciplina d : disciplinas) {
            if (d.getCodigo() == codigo) return d;
        }
        return null;
    }
}
