def Boolean ehMaiorDeIdade(Long idMatricula) {
    /*
    idMatricula: Deve ser inserido o id da matrícula
     */
    Date dataNascimento = Dados.pessoal.v1.historicoMatricula.buscaHistoricoMatricula(campos: "pessoa.dataNascimento", criterio: "id = ${idMatricula}", primeiro: true)?.pessoa?.dataNascimento ?: null;
    Integer idade = Datas.diferencaAnos(dataNascimento, Datas.hoje());
    return idade >= 18;
}
//└>Hoje é 15/08/2024
//└>Matricula id 1234, possui a data de nascimento dia 01/01/2006
//└>Matricula id 4321, possui a data de nascimento dia 01/01/2010
ehMaiorDeIdade(1234);
//└>Retorno: -> true
ehMaiorDeIdade(4321);
//└>Retorno: -> false