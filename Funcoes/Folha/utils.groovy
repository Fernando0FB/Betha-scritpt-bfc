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


def Object getCorpoPutMatricula(Object matricula) {
    /*
        matricula: Objeto retornado da fonte dinâmica de matrículas
     */
    return [
            idIntegracao: "${matricula.id}",
            idGerado    : "${matricula.id}",
            conteudo    : [
                    id                    : matricula.id,
                    dataInicioContrato    : matricula.dataInicioContrato.format("yyyy-MM-dd"),
                    situacao              : matricula.situacao,
                    geraRegistroPreliminar: matricula.geraRegistroPreliminar,
                    tipo                  : matricula.tipo,
                    pessoa                : [
                            id: matricula.pessoa.id
                    ],
                    codigoMatricula       : [
                            contrato         : matricula.codigoMatricula.contrato != 0 ? matricula.codigoMatricula.contrato : null,
                            digitoVerificador: matricula.codigoMatricula.digitoVerificador,
                            numero           : matricula.codigoMatricula.numero
                    ]
            ]
    ]
}
getCorpoPutMatricula(itemMatriculas);
//└> Retorno: -> {"idIntegracao":"","idGerado":"","conteudo":{"id":,"dataInicioContrato":"","situacao":"","geraRegistroPreliminar":,"tipo":"","pessoa":{"id":},"codigoMatricula":{"contrato":,"digitoVerificador":,"numero":}}}
