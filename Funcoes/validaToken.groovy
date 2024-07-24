/*
Função responsável por realizar a validação do token informado.
 */
def Map<Boolean, String> validaToken(String token) {
    //token: Token de migração, ou de tela.
    String url = ""; //Aqui deve ser inserido a url do tokenInfo
    // └> Por motivos de segurança, não foi informado
    req = Http.servico(url + token).GET();

    if(req.sucesso()) {
        return [true:""];
    } else if(req.json().expired){
        return [false:"Token expirado!"];
    } else {
        return [false:"Token não encontrado!"];
    }
}
validaToken("Imagine um Token válido");
//└> retorno -> {true:""}
validaToken("12312038");
//└> retorno -> {false:Token não encontrado!}
validaToken("Imagine um token expirado");
//└> retorno -> {false:Token expirado!}

//Exemplo utilização:
validadeToken = validaToken("token")
if(validadeToken.false) {
    suspender("O token inserido é invalido! Motivo: ${validadeToken.false}")
}