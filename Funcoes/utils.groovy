/*
Recebe algumas variáveis, e preenche uma string, com um determinado caracter, até chegar a um determinado tamanho.
*/
def String preencherString(String texto, Integer quantidade, String caracter, String direcao) {
    //texto: Texto inicial, que irá ser preenchido;
    //quantidade: Tamanho final do texto, após o preenchimento;
    //caracter: Caracter a ser usado para realizar o preenchimento;
    //direcao: Direção em que o texto será preenchido ('E'- Esquerda, 'D'- Direita);
    if(texto.isEmpty() || caracter.isEmpty() || !quantidade){
        suspender("Informe os parâmetros corretamente");
    }
    switch(direcao){
        case "E":
            return texto.padLeft(quantidade, caracter[0]);
            break;
        case "D":
            return texto.padRight(quantidade, caracter[0]);
            break;
        default:
            suspender("O parâmetro de direcao deve ser 'E' ou 'D'");
            break;
    }
}
preencherString("teste", 10, "*", "D");
//└> Retorno -> "teste*****";
preencherString("teste", 10, "*", "E");
//└> Retorno -> "*****teste";


/*
Mapeia ISO 8601 duration para um map com as informações
 */
def Object mapISO8601duration(String duracao) {
    //duracao: String de padrão ISO860 duration
    //└> Mais informações de ISO 8601 duration : https://www.digi.com/resources/documentation/digidocs/90001488-13/reference/r_iso_8601_duration_format.htm
    def pattern = ~/P(?:(\d+)Y)?(?:(\d+)M)?(?:(\d+)D)?(?:T(?:(\d+)H)?(?:(\d+)M)?(?:(\d+)S)?)?/;
    def matcher = pattern.matcher(duracao);
    def resultado = [:];

    if (matcher.matches()) {
        resultado.anos = (matcher[0][1] != null) ? matcher[0][1].toInteger() : 0;
        resultado.meses = (matcher[0][2] != null) ? matcher[0][2].toInteger() : 0;
        resultado.dias = (matcher[0][3] != null) ? matcher[0][3].toInteger() : 0;
        resultado.horas = (matcher[0][4] != null) ? matcher[0][4].toInteger() : 0;
        resultado.minutos = (matcher[0][5] != null) ? matcher[0][5].toInteger() : 0;
        resultado.segundos = (matcher[0][6] != null) ? matcher[0][6].toInteger() : 0;
    }

    return resultado;
}

mapISO8601duration("P2YT4H10M");
//└> retorno = "[anos:2, meses:0, dias:0, horas:4, minutos:10, segundos:0]"
//--------------------------\\

/*
Remove caracteres especiais de uma string
*/
def String removerCaracteresEspeciais(String texto) {
    String textoTratado = texto.expressao(~/[à|á|ã|â]/).substituirPor("a")
            .expressao(~/[Á|Ã|À|Â]/).substituirPor("A")
            .expressao(~/[é|ê]/).substituirPor("e")
            .expressao(~/[É|É]/).substituirPor("E")
            .expressao(~/[É|É]/).substituirPor("")
            .expressao(~/[í]/).substituirPor("i")
            .expressao(~/[Í]/).substituirPor("I")
            .expressao(~/[õ|ó|ô]/).substituirPor("o")
            .expressao(~/[Õ|Ó|Ô]/).substituirPor("O")
            .expressao(~/[ü|ú]/).substituirPor("u")
            .expressao(~/[Ú|Ü]/).substituirPor("U")
            .expressao(~/[ç]/).substituirPor("c")
            .expressao(~/[Ç]/).substituirPor("C")
            .expressao(~/  /).substituirPor(" ")

    return textoTratado
    //.expressao(/[,|\/\|-|_||.|!|@|#|$|%|¨|&||^|´|.||:|;|)|(|%|~\-|]/).substituirPor("")
}
removerCaracteresEspeciais("caçar");
//└> retorno = "cacar"
removerCaracteresEspeciais("Rua Antônio Ávila");
//└> retorno = "Rua Antonio Avila"
