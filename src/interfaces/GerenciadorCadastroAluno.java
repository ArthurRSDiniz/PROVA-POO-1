package interfaces;

import model.Aluno;

/**
 * Interface que define o contrato para cadastro de alunos.
 * Segue o princípio da Segregação de Interfaces (SOLID - ISP).
 */
public interface GerenciadorCadastroAluno {
    void cadastrarAluno(Aluno aluno);
}

