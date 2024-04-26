package Funcoes

def Object doPost(String url, Object dadosInserir) {
    /*
    url: Deve ser inserida a URL para onde será realizado o post
    dadosInserir: Dados a serem inseridos
     */
    requisicao = Http.servico(url)
                     .cabecalho("Authorization", "Bearer ${v_bearerToken}");

    resposta = requisicao.POST(JSON.escrever(dadosInserir), Http.JSON);
    Object respostaObj = JSON.ler(resposta.conteudo());
    return respostaObj;
}