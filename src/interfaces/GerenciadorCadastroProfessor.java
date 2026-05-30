package interfaces;

import model.Professor;

/**
 * Interface que define o contrato para cadastro de professores.
 * Segue o princípio da Segregação de Interfaces (SOLID - ISP).
 */
public interface GerenciadorCadastroProfessor {
    void cadastrarProfessor(Professor professor);
}

