def exemploFuncao(Map args = [:]) {
    String parametro1 = args.get('parametro1', 'valor padrão 1')
    String parametro2 = args.get('parametro2', 'valor padrão 2')

    println "Parâmetro 1: ${parametro1}"
    println "Parâmetro 2: ${parametro2}"
}

exemploFuncao()

exemploFuncao(parametro2: "valor fornecido")

exemploFuncao(parametro1: "valor 1", parametro2: "valor 2")