/*
Recebe um número de telefone e formata com '()' e '-'
 */
def String formatTelefone(String numero){
    //Numero: Celular em formato string com 11 ou 10 numeros
    if (numero.size() == 11) {
        return numero.trim().replaceAll(/(\d{2})(\d{5})(\d{4})/) { match ->
            "(${match[1]}) ${match[2]}-${match[3]}"
        }
    } else if (numero.trim().size() == 10) {
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
    return cnpj.trim().take(14).replaceAll(/(\d{2})(\d{3})(\d{3})(\d{4})(\d{2})/) { match ->
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
    return cpf.trim().take(11).replaceAll(/(\d{3})(\d{3})(\d{3})(\d{2})/) { match ->
        "${match[1]}.${match[2]}.${match[3]}-${match[4]}"
    }
}
formatCPF("123321123321");
//└> retorno -> 123.321.123-31
//--------------------------\\

/*
Recebe uma String numeral, e formata corretamente se for cpf ou cnpj
 */
def String formatCpfCnpj(String cpfCnpj) {
    //cpfCnpj: String numeral, com tamanho 11 ou 14;
    if(cpfCnpj.trim().size() == 11) {
        return cpf.trim().take(11).replaceAll(/(\d{3})(\d{3})(\d{3})(\d{2})/) { match ->
            "${match[1]}.${match[2]}.${match[3]}-${match[4]}"
        }
    } else if (cpfCnpj.trim().size() == 14) {
        return cnpj.trim().take(14).replaceAll(/(\d{2})(\d{3})(\d{3})(\d{4})(\d{2})/) { match ->
            "${match[1]}.${match[2]}.${match[3]}/${match[4]}-${match[5]}"
        }
    } else {
        return cpfCnpj;
    }
}
formatCpfCnpj("12345678910");
//└> retorno -> 123.456.789-10
formatCpfCnpj("12345678910123");
//└> retorno -> 12.345.678/9101-23
//--------------------------\\


/*
Recebe um CEP e formata adicionando os '.' e '-'
*/
def String formatCEP(String cep) {
    //cep: cep em formato de string, deve conter 8 caracteres
    return cep.trim().take(8).replaceAll(/(\d{5})(\d{3})/) { match ->
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
        campoFormatado = campo.trim().replaceAll(/(\d{2})(\d{3})(\d{3})(\d{4})(\d{2})/) { match ->
            "${match[1]}.${match[2]}.${match[3]}/${match[4]}-${match[5]}"
        }
    } else if (tipo == "CPF") {
        campoFormatado = campo.trim().replaceAll(/(\d{3})(\d{3})(\d{3})(\d{2})/) { match ->
            "${match[1]}.${match[2]}.${match[3]}-${match[4]}"
        }
    } else if (tipo == "CEP") {
        campoFormatado = campo.trim().replaceAll(/(\d{5})(\d{3})/) { match ->
            "${match[1]}-${match[2]}"
        }
    } else if (tipo == "TELEFONE") {
        if (campo.trim().size() == 11) {
            campoFormatado = campo.trim().replaceAll(/(\d{2})(\d{5})(\d{4})/) { match ->
                "(${match[1]}) ${match[2]}-${match[3]}"
            }
        } else if (campo.trim().size() == 10) {
            campoFormatado = campo.trim().replaceAll(/(\d{2})(\d{4})(\d{4})/) { match ->
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
formatValor(120312.13);
//└> retorno -> "R$120.312,13"
//--------------------------\\


/*
Recebe uma data, valida, e retorna formatada no padrão dd/MM/yyyy
 */
def String validaFormataData(Date data) {
    //data: Data no formato Date
    //└> caso nulo, retorna em branco
    if (data && (data?.format("dd/MM/yyyy") != "01/01/1800")) {
        return data.format("dd/MM/yyyy");
    } else {
        return "";
    }
}
validaFormataData(Datas.data(2024,10,15));
//└> retorno -> "15/10/2024"
validaFormataData(null);
//└> retorno -> ""