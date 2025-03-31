/*
Função responsável por checar se a chave de integração está devidamente configurada.
 */
def void validaChaveIntegracao() {
    token = Variavel?.CHAVE_INTEGRACAO ?: null;
    if (!token) {
        suspender("A variável de ambiente 'CHAVE_INTEGRACAO' não está corretamente configurada, por favor, verifique!");
    }
}
validaChaveIntegracao();