/*
No lugar de p_arquivo será o nome do parâmetro criado.
 */
p_arquivo = parametros?.p_arquivo?.valor;

/*
No delimitador, deve ser passado o que foi utilizado como delimitador no arquivo CSV.
 */
arquivoLeitura = Arquivo.ler(p_arquivo, 'csv', [encoding: 'UTF-8', delimitador: ";"]);

while (arquivoLeitura.contemProximaLinha()) {
    linha = arquivoDePara.lerLinha();
    imprimir "Linha: " + linha;
}