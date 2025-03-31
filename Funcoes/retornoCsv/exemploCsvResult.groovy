/*
    Função responsável pela escrita no arquivo de retorno
 */
def Void mapeiaEnvio(String numMatricula, Boolean sucesso, String motivo) {
    arquivoResultado.escrever(sucesso ? "Sim" : "Não");
    arquivoResultado.escrever(motivo ?: "");
    arquivoResultado.novaLinha();
}

/*
    Declaração da variável de arquivo, e escrita do cabeçalho
 */
arquivoResultado = Arquivo.novo("ArquivoSaida.csv", "csv", [delimitador: '|']);
arquivoResultado.escrever("Linha|Nome|Sucesso?|MotivoErro"); arquivoResultado.novaLinha();

/*
    Exemplo de utilização
 */
//Exemplo sucesso
mapeiaEnvio(numMatricula, false, "Já existe uma nomeação para esse candidato!");
//Exemplo falha
mapeiaEnvio(numMatricula, true, "");
