import model.*;
import service.Secretaria;

import java.util.Scanner;

/**
 * Ponto de entrada do sistema escolar.
 * Exibe o menu principal em loop até o usuário escolher a opção 15 (Sair).
 * Todo input do usuário é tratado com try/catch conforme exigido pelo trabalho.
 */
public class main {

    private static final Scanner scanner   = new Scanner(System.in);
    private static final Secretaria secretaria = new Secretaria();

    public static void main(String[] args) {
        int opcao = 0;
        do {
            exibirMenu();
            opcao = lerInteiro("Escolha uma opcao: ");
            processarOpcao(opcao);
        } while (opcao != 15);
    }

    // =========================================================
    //  MENU
    // =========================================================

    private static void exibirMenu() {
        System.out.println("\n=====================================");
        System.out.println("          MENU PRINCIPAL             ");
        System.out.println("=====================================");
        System.out.println(" 1.  Cadastrar Aluno");
        System.out.println(" 2.  Cadastrar Professor");
        System.out.println(" 3.  Cadastrar Disciplina");
        System.out.println(" 4.  Cadastrar Turma");
        System.out.println(" 5.  Inserir Nota");
        System.out.println(" 6.  Relatorio Alunos");
        System.out.println(" 7.  Relatorio Professores");
        System.out.println(" 8.  Relatorio Disciplinas");
        System.out.println(" 9.  Relatorio Turmas");
        System.out.println(" 10. Relatorio de Aluno x Notas x Media");
        System.out.println(" 11. Quantidade de Alunos Cadastrados");
        System.out.println(" 12. Nome do aluno com a maior nota");
        System.out.println(" 13. Listar alunos de uma disciplina");
        System.out.println(" 14. Listar turmas de um professor");
        System.out.println(" 15. Sair");
        System.out.println("=====================================");
    }

    //  PROCESSAMENTO DE OPÇÕES

    private static void processarOpcao(int opcao) {
        switch (opcao) {
            case 1  -> cadastrarAluno();
            case 2  -> cadastrarProfessor();
            case 3  -> cadastrarDisciplina();
            case 4  -> cadastrarTurma();
            case 5  -> inserirNota();
            case 6  -> secretaria.relatorioAlunos();
            case 7  -> secretaria.relatorioProfessores();
            case 8  -> secretaria.relatorioDisciplinas();
            case 9  -> secretaria.relatorioTurmas();
            case 10 -> relatorioNotasMedia();
            case 11 -> secretaria.obterQuantidadeAlunos();
            case 12 -> secretaria.obterAlunoComMaiorNota();
            case 13 -> listarAlunosPorDisciplina();
            case 14 -> listarTurmasPorProfessor();
            case 15 -> System.out.println("Encerrando o sistema. Ate logo!");
            default -> System.out.println("Opcao invalida. Tente novamente.");
        }
    }

    //  OPÇÃO 1 — CADASTRAR ALUNO


    private static void cadastrarAluno() {
        System.out.println("\n--- CADASTRAR ALUNO ---");
        String nome          = lerTexto("Nome: ");
        String dataNasc      = lerTexto("Data de Nascimento (dd/mm/aaaa): ");
        String telefone      = lerTexto("Telefone: ");
        String rua           = lerTexto("Rua: ");
        int numero           = lerInteiro("Numero: ");
        String bairro        = lerTexto("Bairro: ");
        String cep           = lerTexto("CEP: ");
        int matricula        = lerInteiro("Matricula: ");
        int anoIngresso      = lerInteiro("Ano de Ingresso: ");

        Endereco endereco = new Endereco(rua, numero, bairro, cep);
        Aluno aluno = new Aluno(nome, dataNasc, telefone, endereco, matricula, anoIngresso);
        secretaria.cadastrarAluno(aluno);
    }

    //  OPÇÃO 2 — CADASTRAR PROFESSOR

    private static void cadastrarProfessor() {
        System.out.println("\n--- CADASTRAR PROFESSOR ---");
        String nome         = lerTexto("Nome: ");
        String dataNasc     = lerTexto("Data de Nascimento (dd/mm/aaaa): ");
        String telefone     = lerTexto("Telefone: ");
        String rua          = lerTexto("Rua: ");
        int numero          = lerInteiro("Numero: ");
        String bairro       = lerTexto("Bairro: ");
        String cep          = lerTexto("CEP: ");
        String areaForm     = lerTexto("Area de Formacao: ");
        int anoAdmissao     = lerInteiro("Ano de Admissao: ");
        String email        = lerTexto("Email: ");

        // Verifica se o professor quer vincular uma disciplina já cadastrada
        String nomeDisciplina = lerTexto("Disciplina (deixe em branco para pular): ");

        Endereco endereco = new Endereco(rua, numero, bairro, cep);
        Professor professor = new Professor(nome, dataNasc, telefone, endereco,
                areaForm, anoAdmissao, email);

        if (!nomeDisciplina.isBlank()) {
            var disciplina = secretaria.buscarDisciplinaPorNome(nomeDisciplina);
            if (disciplina == null) {
                System.out.println("Aviso: disciplina '" + nomeDisciplina +
                        "' nao encontrada. Professor sera cadastrado sem disciplina.");
            } else {
                professor.adicionarDisciplina(disciplina);
                disciplina.adicionarProfessor(professor);
                System.out.println("Disciplina '" + nomeDisciplina + "' vinculada ao professor.");
            }
        }

        secretaria.cadastrarProfessor(professor);
    }

    //  OPÇÃO 3 — CADASTRAR DISCIPLINA

    private static void cadastrarDisciplina() {
        System.out.println("\n--- CADASTRAR DISCIPLINA ---");
        String nome    = lerTexto("Nome da Disciplina: ");
        int carga      = lerInteiro("Carga Horaria (horas): ");
        int codigo     = lerInteiro("Codigo da Disciplina: ");

        Disciplina disciplina = new Disciplina(nome, carga, codigo);
        secretaria.cadastrarDisciplina(disciplina);
    }

    //  OPÇÃO 4 — CADASTRAR TURMA

    private static void cadastrarTurma() {
        System.out.println("\n--- CADASTRAR TURMA ---");
        int codigo          = lerInteiro("Codigo da Turma: ");
        String nomeDisciplina = lerTexto("Nome da Disciplina: ");
        String nomeProfessor  = lerTexto("Nome do Professor: ");
        int anoLetivo       = lerInteiro("Ano Letivo: ");

        secretaria.cadastrarTurma(codigo, nomeDisciplina, nomeProfessor, anoLetivo);
    }

    //  OPÇÃO 5 — INSERIR NOTA

    private static void inserirNota() {
        System.out.println("\n--- INSERIR NOTA ---");
        int matricula         = lerInteiro("Matricula do Aluno: ");
        String nomeDisciplina = lerTexto("Nome da Disciplina: ");
        double valor          = lerDouble("Valor da Nota: ");
        String data           = lerTexto("Data da Nota (dd/mm/aaaa): ");

        secretaria.inserirNota(matricula, nomeDisciplina, valor, data);
    }

    //  OPÇÃO 10 — RELATÓRIO NOTAS E MÉDIA

    private static void relatorioNotasMedia() {
        int matricula = lerInteiro("Informe a matricula do aluno: ");
        secretaria.gerarRelatorioNotasEMediaPorAluno(matricula);
    }

    //  OPÇÃO 13  LISTAR ALUNOS POR DISCIPLINA

    private static void listarAlunosPorDisciplina() {
        String nomeDisciplina = lerTexto("Nome da Disciplina: ");
        secretaria.listarAlunosPorDisciplina(nomeDisciplina);
    }
    // OPÇÃO 13 LISTAR TURMAS POR PROFESSOR

    private static void listarTurmasPorProfessor() {
        String nomeProfessor = lerTexto("Nome do Professor: ");
        secretaria.listarTurmasPorProfessor(nomeProfessor);
    }

    /** Lê uma linha de texto do teclado com tratamento de exceção. */
    private static String lerTexto(String mensagem) {
        try {
            System.out.print(mensagem);
            return scanner.nextLine().trim();
        } catch (Exception e) {
            System.out.println("Erro ao ler texto: " + e.getMessage());
            return "";
        }
    }

    /** Lê um número inteiro do teclado com validação e tratamento de exceção. */
    private static int lerInteiro(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                int valor = Integer.parseInt(scanner.nextLine().trim());
                return valor;
            } catch (NumberFormatException e) {
                System.out.println("Entrada invalida! Digite um numero inteiro.");
            } catch (Exception e) {
                System.out.println("Erro inesperado: " + e.getMessage());
            }
        }
    }

    /** Lê um número decimal do teclado com validação e tratamento de exceção. */
    private static double lerDouble(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                double valor = Double.parseDouble(scanner.nextLine().trim().replace(",", "."));
                return valor;
            } catch (NumberFormatException e) {
                System.out.println("Entrada invalida! Digite um numero (ex: 8.5 ou 8,5).");
            } catch (Exception e) {
                System.out.println("Erro inesperado: " + e.getMessage());
            }
        }
    }
}

