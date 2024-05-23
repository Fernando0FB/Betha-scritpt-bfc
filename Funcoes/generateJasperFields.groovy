mapaEsquema = [
        'com.betha.bfc.script.api.dados.dinamico.esquema.CaracterEsquema': 'java.lang.String',
        'com.betha.bfc.script.api.dados.dinamico.esquema.InteiroEsquema': 'java.lang.Long',
        'com.betha.bfc.script.api.dados.dinamico.esquema.NumeroEsquema': 'java.math.BigDecimal',
        'com.betha.bfc.script.api.dados.dinamico.esquema.DataEsquema': 'java.util.Date',
        'com.betha.bfc.script.api.dados.dinamico.esquema.ObjetoEsquema': 'java.util.Object',
        'com.betha.bfc.script.api.dados.dinamico.esquema.ListaEsquema': 'java.util.List'
]

def generateFieldXML(nome, tipo) {
    return """
  <field name="${nome}" class="${tipo}">
      <fieldDescription><![CDATA[${nome}]]></fieldDescription>
  </field>
  """
}

def generateXML(esquema) {
    def xml = '<schema>\n'
    esquema.each { nome, tipo ->
        chave = "${tipo.toString().split('@')[0]}"
        esquemaTipo = mapaEsquema[chave]
        xml += generateFieldXML(nome, esquemaTipo)
    }
    xml += '</schema>'
    return xml
}

//TODO adicionar o esquema aqui
esquema = [
        teste: Esquema.caracter
]

def xmlOutput = generateXML(esquema)
arquivoTxt = Arquivo.novo('xmlFields.txt');
arquivoTxt.escrever(xmlOutput);

Resultado.arquivo(arquivoTxt);
