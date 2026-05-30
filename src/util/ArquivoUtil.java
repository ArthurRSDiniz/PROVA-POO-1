package util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Utilitário responsável por toda leitura e escrita nos arquivos .txt.
 * Centraliza o acesso a arquivos, seguindo o princípio da Responsabilidade Única
 */
public class ArquivoUtil {

    // Nomes dos arquivos de dados
    public static final String ARQUIVO_ALUNOS      = "alunos.txt";
    public static final String ARQUIVO_PROFESSORES = "professores.txt";
    public static final String ARQUIVO_DISCIPLINAS = "disciplinas.txt";
    public static final String ARQUIVO_TURMAS      = "turmas.txt";
    public static final String ARQUIVO_NOTAS       = "notas.txt";

    /**
     * Lê todas as linhas de um arquivo e retorna como lista de Strings.
     * Retorna lista vazia se o arq não existir.
     */
    public static List<String> lerLinhas(String nomeArquivo) {
        List<String> linhas = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                if (!linha.trim().isEmpty()) {
                    linhas.add(linha.trim());
                }
            }
        } catch (FileNotFoundException e) {
            // Arquivo ainda não existe; retorna lista vazia (comportamento esperado)
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo '" + nomeArquivo + "': " + e.getMessage());
        }
        return linhas;
    }

    /**
     * Sobrescreve o arq com a lista de linhas fornecida.
     * Usado para salvar o estado atual de uma coleção
     */
    public static void salvarLinhas(String nomeArquivo, List<String> linhas) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo, false))) {
            for (String linha : linhas) {
                writer.write(linha);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar o arquivo '" + nomeArquivo + "': " + e.getMessage());
        }
    }

    /**
     * Adiciona uma única linha ao final do arquivo
     */
    public static void adicionarLinha(String nomeArquivo, String linha) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo, true))) {
            writer.write(linha);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Erro ao gravar no arquivo '" + nomeArquivo + "': " + e.getMessage());
        }
    }
}

