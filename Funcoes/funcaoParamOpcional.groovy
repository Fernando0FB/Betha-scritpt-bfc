def exemploFuncao(Map args = [:]) {
    /*
        args: Deve ser passado em formato de mapa, as informações
            └> Ex de chamada de função: exemploFuncao(parametro1: "valorParametro", parametro2: "valor2Parametro")
    */
    String parametro1 = args.get('parametro1', 'valor padrão 1') /* caso não haja o valor, ele irá pegar o valor passado no segundo parâmetro */
    String parametro2 = args.get('parametro2', 'valor padrão 2') /* caso não haja o valor, ele irá pegar o valor passado no segundo parâmetro */

    println "Parâmetro 1: ${parametro1}"
    println "Parâmetro 2: ${parametro2}"
}

/* exemplos */

exemploFuncao(parametro1: "valor 1 para exemplo");
/*
    resultado no console:

    Parâmetro1: valor 1 para exemplo
    Parâmetro2: valor padrão 2
*/

exemploFuncao(parametro2: "valor 2 para exemplo");
/*
    resultado no console:

    Parâmetro1: valor padrão 1
    Parâmetro2: valor 2 para exemplo
*/

exemploFuncao(parametro1: "valor 1 para exemplo", parametro2: "valor 2 para exemplo");
/*
    resultado no console:

    Parâmetro1: valor 1 para exemplo
    Parâmetro2: valor 2 para exemplo
*/