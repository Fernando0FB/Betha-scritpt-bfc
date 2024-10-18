def Object doPost(String url, Object dadosInserir, String v_bearerToken) {
    /*
    url: Deve ser inserida a URL para onde será realizado o post
    dadosInserir: Dados a serem inseridos
    v_bearerToken: Deve conter o token de migração da entidade
     */
    requisicao = Http.servico(url)
            .cabecalho("Authorization", "Bearer ${v_bearerToken}");

    resposta = requisicao.POST(JSON.escrever(dadosInserir), Http.JSON);
    Object respostaObj = JSON.ler(resposta.conteudo());
    return respostaObj;
}

doPost("https://pessoal.betha.cloud/service-layer/v1/api/teste", [exemplo: "valor"], "3cxv123bvc13asd1")
//└> Retorno: -> [id: "as2d1-sad12-2dasd"] -> Será retornado a estrutura referente a URL enviado


def Object doPut(String url, Object dadosAtualizar, String v_bearerToken) {
    /*
    url: Deve ser inserida a URL para onde será realizado o put
    dadosAtualizar: Dados a serem atualizados
    v_bearerToken: Deve conter o token de migração da entidade
     */
    requisicao = Http.servico(url)
            .cabecalho("Authorization", "Bearer ${v_bearerToken}");

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

def Object confereLote(String idLote, String url, String v_bearerToken) {
    /*
    idLote: idLote retornado na requisição prévia
    url: Deve ser inserida a URL para onde foi realizada a requisição prévia que originou o idLote
    v_bearerToken: Deve conter o token de migração da entidade
     */
    Object lote = [];
    while (true) {
        lote = Http.servico("${url}/lotes/${idLote}")
                .cabecalho(['Authorization': "Bearer ${v_bearerToken}"])
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