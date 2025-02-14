def Object doPost(String url, Object dadosInserir) {
    /*
    url: Deve ser inserida a URL para onde será realizado o post
    dadosInserir: Dados a serem inseridos
     */
    requisicao = Http.servico(url)
            .chaveIntegracao(Variavel.CHAVE_INTEGRACAO);

    resposta = requisicao.POST(JSON.escrever(dadosInserir), Http.JSON);
    Object respostaObj = JSON.ler(resposta.conteudo());
    return respostaObj;
}

doPost("https://pessoal.betha.cloud/service-layer/v1/api/teste", [exemplo: "valor"], "3cxv123bvc13asd1")
//└> Retorno: -> [id: "as2d1-sad12-2dasd"] -> Será retornado a estrutura referente a URL enviado


def Object doPut(String url, Object dadosAtualizar) {
    /*
    url: Deve ser inserida a URL para onde será realizado o put
    dadosAtualizar: Dados a serem atualizados
     */
    requisicao = Http.servico(url)
            .chaveIntegracao(Variavel.CHAVE_INTEGRACAO);

    resposta = requisicao.PUT(JSON.escrever(dadosAtualizar), Http.JSON);
    Object respostaObj = JSON.ler(resposta.conteudo());
    return respostaObj;
}

doPut("https://pessoal.betha.cloud/service-layer/v1/api/teste", [exemplo: "valor"], "3cxv123bvc13asd1")
//└> Retorno: -> [id: "as2d1-sad12-2dasd"] -> Será retornado a estrutura referente a URL enviado


/*
- URL utilizada no Http.servico deve mudar, dependendo como é utilizado na vertical;
- lote.situacao deve mudar, conforme estrutura da vertical;
 */

def Object confereLote(String idLote, String url) {
    /*
    idLote: idLote retornado na requisição prévia
    url: Deve ser inserida a URL para onde foi realizada a requisição prévia que originou o idLote
     */
    Object lote = [];
    while (true) {
        lote = Http.servico("${url}/lotes/${idLote}")
                .chaveIntegracao(Variavel.CHAVE_INTEGRACAO)
                .GET().json()

        if (lote.situacao == "EXECUTADO") {
            break;
        } else {
            esperar 2000
        }
    }
    return lote;
}

confereLote("as2d1-sad12-2dasd", "https://pessoal.betha.cloud/service-layer/v1/api/teste", "3cxv123bvc13asd1");
//└> Retorno: -> [situacao: "executado"] -> Será retornado a estrutura referente a URL enviada