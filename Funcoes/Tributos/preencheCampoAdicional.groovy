/*
Recebe variáveis informativas, e altera/insere informações em campos adicionais
feito por: Patrick.elias
 */
def void preencheCampoAdicional(Long id, String titulo, String valor, String data) {
    //id: ID do imóvel a ser inserido
    //titulo: Titulo do campo adicional a ser alterado
    //valor: Valor a ser inserido
    //data: Data no formato 'yyyy-MM-dd', normalmente Datas.hoje().format('yyyy-MM-dd')
    Dados.tributosServices.v1.economico.campoAdicional.preenche(conteudo: [
            idReferente: id,
            data: data,
            nomeCampo: titulo,
            valor: valor
    ])
}
preencheCampoAdicional(12651098, "Nome do Campo", "teste2", Datas.hoje().format('yyyy-MM-dd'));
// └> Insere valor "teste" ao campo adicional "Nome do Campo" para o imóvel de id 12651098