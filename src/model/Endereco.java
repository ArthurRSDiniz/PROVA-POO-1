package model;

/**
 * Representa o endereço de uma pessoa.
 * Armazena rua, número, bairro e CEP.
 */
public class Endereco {

    private String rua;
    private int numero;
    private String bairro;
    private String cep;

    public Endereco(String rua, int numero, String bairro, String cep) {
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.cep = cep;
    }

    // Getters e Setters
    public String getRua() { return rua; }
    public void setRua(String rua) { this.rua = rua; }

    public int getNumero() { return numero; }
    public void setNumero(int numero) { this.numero = numero; }

    public String getBairro() { return bairro; }
    public void setBairro(String bairro) { this.bairro = bairro; }

    public String getCep() { return cep; }
    public void setCep(String cep) { this.cep = cep; }

    @Override
    public String toString() {
        return rua + ", " + numero + " - " + bairro + " - CEP: " + cep;
    }

    /** Converte para linha de texto para salvar no arquivo .txt */
    public String paraArquivo() {
        return rua + ";" + numero + ";" + bairro + ";" + cep;
    }

    /** Reconstrói um Endereço a partir de uma linha do arquivo .txt */
    public static Endereco deArquivo(String linha) {
        String[] p = linha.split(";");
        return new Endereco(p[0], Integer.parseInt(p[1]), p[2], p[3]);
    }
}

