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
//└> Retorno: -> [Situacao: "Executado"] -> Será retornado a estrutura referente a URL enviado


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
//└> Retorno: -> [Situacao: "Executado"] -> Será retornado a estrutura referente a URL enviado


/*
Inicialmente desenvolvido para o folha.
Caso utilizar para outras verticais, prestar atenção nas seguintes informações:
    - URL utilizada no Http.servico deve mudar, dependendo como é utilizado na vertical;
    - lote.situacao deve mudar, conforme estrutura da vertical;
 */
def Object confereLote(String idLote, String url, String v_bearerToken){
    /*
    idLote: idLote retornado na requisição prévia
    url: Deve ser inserida a URL para onde foi realizada a requisição prévia que originou o idLote
    v_bearerToken: Deve conter o token de migração da entidade
     */
    Object lote = [];
    while(true){
        try{
            lote = Http.servico("${url}/lotes/${idLote}")
                    .cabecalho(['Authorization': "Bearer ${v_bearerToken}"])
                    .GET().json()

            if(lote.situacao == "EXECUTADO"){
                break;
            } else {
                esperar 2000
            }
        } catch(Exception e){
            esperar 2000
        }
    }
    return lote;
}


/*
Função responsável por realizar a validação do token informado.
 */
def Map<Boolean, String> validaToken(String token) {
    //token: Token de migração, ou de tela.
    String url = ""; //Aqui deve ser inserido a url do tokenInfo
                    // └> Por motivos de segurança, não foi informado
    req = Http.servico(url + token).GET();

    if(req.sucesso()) {
        if(req.json().expired) {
            return [false:"Token expirado!"];
        } else {
            return [true:""]
        }
    }

    return [false:"Token não encontrado!"];
}
validaToken("Imagine um Token válido");
//└> retorno -> {true:""}
validaToken("12312038");
//└> retorno -> {false:Token não encontrado!}
validaToken("Imagine um token expirado");
//└> retorno -> {false:Token expirado!}