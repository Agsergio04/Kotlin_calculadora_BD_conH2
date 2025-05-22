package org.example.model

/**
 * Enumeración que representa los distintos operadores matemáticos soportados por la calculadora.
 *
 * @property simboloUi Carácter utilizado para mostrar el operador en la interfaz de usuario.
 * @property simbolos Lista de caracteres válidos que representan este operador (incluye variantes).
 */
enum class Operador(val simboloUi: Char, val simbolos: List<Char>) {
    /**
     * Operación de suma.
     * Acepta el símbolo '+'.
     */
    SUMA('+', listOf('+')),

    /**
     * Operación de resta.
     * Acepta el símbolo '-'.
     */
    RESTA('-', listOf('-')),

    /**
     * Operación de multiplicación.
     * Acepta los símbolos '*' y 'x'.
     */
    MULTIPLICACION('x', listOf('*', 'x')),

    /**
     * Operación de división.
     * Acepta los símbolos ':' y '/'.
     */
    DIVISION('/', listOf(':', '/'));

    companion object {
        /**
         * Obtiene el tipo de [Operador] correspondiente a un símbolo dado.
         *
         * @param operador Carácter que representa el operador ingresado.
         * @return El [Operador] correspondiente, o `null` si el símbolo no es reconocido.
         */
        fun getOperador(operador: Char?) = operador?.let { op -> entries.find { op in it.simbolos } }
    }
}
