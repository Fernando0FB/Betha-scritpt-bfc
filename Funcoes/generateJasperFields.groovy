mapaEsquema = [
        'com.betha.bfc.script.api.dados.dinamico.esquema.CaracterEsquema': 'java.lang.String',
        'com.betha.bfc.script.api.dados.dinamico.esquema.InteiroEsquema': 'java.lang.Long',
        'com.betha.bfc.script.api.dados.dinamico.esquema.NumeroEsquema': 'java.math.BigDecimal',
        'com.betha.bfc.script.api.dados.dinamico.esquema.DataEsquema': 'java.util.Date',
        'com.betha.bfc.script.api.dados.dinamico.esquema.ObjetoEsquema': 'java.util.Object',
        'com.betha.bfc.script.api.dados.dinamico.esquema.ListaEsquema': 'java.util.List'
]

def generateFieldXML(String nome, String tipo) {
    /*
        nome: Nome do field a ser criado
        tipo: Tipo da classe a ser criado
    */
    return """<field name="${nome}" class="${tipo}"/>"""
}

def generateXML(esquema) {
    /*
        esquema: Mapa que dita nome e tipo do fields a ser criado
        ex:
        esquema = [
            id: Esquema.numero,
            descricao: Esquema.caracter
        ]
    */
    def xml = '<schema>\n'
    esquema.each { nome, tipo ->
        chave = "${tipo.toString().split('@')[0]}"
        esquemaTipo = mapaEsquema[chave]
        xml += generateFieldXML(nome, esquemaTipo)
    }
    xml += '</schema>'
    return xml
}

/*
    TODO
    Para funcionar, o esquema deve ser adicionado a baixo
*/
esquema = [
        teste: Esquema.caracter
]

def xmlOutput = generateXML(esquema)
arquivoTxt = Arquivo.novo('xmlFields.txt');
arquivoTxt.escrever(xmlOutput);

Resultado.arquivo(arquivoTxt);
/*
    Será gerando um arquivo txt, com o resultado em XRML, que ao ser colado no source do JasperSoft Tibco, gerará os fields.
 */