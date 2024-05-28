/*
Recebe um número de telefone e formata com '()' e '-'
 */
def String formatTelefone(String numero) {
    //Numero: Celular em formato string com 11 ou 10 numeros ex "4899382716"
    if (numero.size() == 11) {
        return numero.replaceAll(/(\d{2})(\d{5})(\d{4})/) { match ->
            "(${match[1]}) ${match[2]}-${match[3]}"
        }
    } else if (numero.size() == 10) {
        return numero.replaceAll(/(\d{2})(\d{4})(\d{4})/) { match ->
            "(${match[1]}) ${match[2]}-${match[3]}"
        }
    } else {
        return numero
    }
}
formatTelefone("48998567382");
//└> retorno: (48) 99856-7382
//--------------------------\\


/*
Recebe um CNPJ e formata adicionando os '.', '/' e '-'
*/
def String formatCNPJ(String cnpj) {
    //cnpj: cnpj em formato de string, deve conter 14 caracteres
    return cnpj.take(14).replaceAll(/(\d{2})(\d{3})(\d{3})(\d{4})(\d{2})/) { match ->
        "${match[1]}.${match[2]}.${match[3]}/${match[4]}-${match[5]}"
    }
}
formatCNPJ("12332112332112");
//└> retorno -> 12.332.112/3321-12
//--------------------------\\


/*
Recebe um CPF e formata adicionando os '.' e '-'
*/
def String formatCPF(String cpf) {
    //cpf: cpf em formato de string, deve conter 11 caracteres
    return cpf.take(11).replaceAll(/(\d{3})(\d{3})(\d{3})(\d{2})/) { match ->
        "${match[1]}.${match[2]}.${match[3]}-${match[4]}"
    }
}
formatCPF("123321123321");
//└> retorno -> 123.321.123-31
//--------------------------\\


/*
Recebe um CEP e formata adicionando os '.' e '-'
*/
def String formatCEP(String cep) {
    //cep: cep em formato de string, deve conter 8 caracteres
    return cep.take(8).replaceAll(/(\d{5})(\d{3})/) { match ->
        "${match[1]}-${match[2]}"
    }
}
formatCEP("77741526");
//└> retorno -> 77741-526
//--------------------------\\


/*
Recebe o tipo, e o campo.
Com base no tipo, realiza a aplicação da máscara e retorna.
 */
def String formatUtils(String tipo, String campo) {
    //tipo: String relacionado ao tipo:
    //      └> "CNPJ", "CPF", "CEP", "TELEFONE"
    //campo: Campo a ser aplicado a máscara
    String campoFormatado = campo;

    if (tipo == "CNPJ") {
        campoFormatado = campo.replaceAll(/(\d{2})(\d{3})(\d{3})(\d{4})(\d{2})/) { match ->
            "${match[1]}.${match[2]}.${match[3]}/${match[4]}-${match[5]}"
        }
    } else if (tipo == "CPF") {
        campoFormatado = campo.replaceAll(/(\d{3})(\d{3})(\d{3})(\d{2})/) { match ->
            "${match[1]}.${match[2]}.${match[3]}-${match[4]}"
        }
    } else if (tipo == "CEP") {
        campoFormatado = campo.replaceAll(/(\d{5})(\d{3})/) { match ->
            "${match[1]}-${match[2]}"
        }
    } else if (tipo == "TELEFONE") {
        if (campo.size() == 11) {
            campoFormatado = campo.replaceAll(/(\d{2})(\d{5})(\d{4})/) { match ->
                "(${match[1]}) ${match[2]}-${match[3]}"
            }
        } else if (campo.size() == 10) {
            campoFormatado = campo.replaceAll(/(\d{2})(\d{4})(\d{4})/) { match ->
                "(${match[1]}) ${match[2]}-${match[3]}"
            }
        }
    }
    return campoFormatado
}

formatUtils("CNPJ","12332112332112");
//└> retorno -> "12.332.112/3321-12"
formatUtils("CPF","123321123321");
//└> retorno -> "123.321.123-31"
formatUtils("CEP","77741526");
//└> retorno -> "77741-526"
formatUtils("TELEFONE","48998567382");
//└> retorno -> "(48) 99856-7382"
//--------------------------\\


/*
Formata ISO 8601 duration para map com as informações
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
Recebe um BigDecimal, e retorna o valor formatado
 */
def String formatValor(BigDecimal valorInicial) {
    //valorInicial: BigDecimal com o valor a ser formatado
    def valor = String.format("%.2f", valorInicial).replace('.', ',')

    def partes = valor.split(',')
    def parteInteira = partes[0]
    def parteDecimal = partes[1]
    def parteInteiraInvertida = parteInteira.reverse()
    def parteInteiraComPontos = parteInteiraInvertida.replaceAll(/(\d{3})/, '$1.').reverse()

    if (parteInteiraComPontos.startsWith('.')) {
        parteInteiraComPontos = parteInteiraComPontos.substring(1)
    }

    def valorFormatado = "R\$${parteInteiraComPontos},${parteDecimal}"
    return valorFormatado
}
formatValor(120312.132);
//└> retorno -> "R$120.312,13"
//--------------------------\\