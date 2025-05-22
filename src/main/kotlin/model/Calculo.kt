package org.example.model

/**
 * Representa una operación matemática realizada entre dos números.
 *
 * @property numero1 Primer operando.
 * @property numero2 Segundo operando.
 * @property operador Operador aritmético utilizado (definido en la enum [Operador]).
 * @property resultado Resultado de la operación.
 */
data class Calculo(
    val numero1: Double,
    val numero2: Double,
    val operador: Operador,
    val resultado: Double
) {
    /**
     * Devuelve una representación en cadena de la operación con formato legible.
     *
     * @return Cadena formateada con la operación completa, por ejemplo: "5.00 + 3.00 = 8.00"
     */
    override fun toString(): String {
        return "%.2f %s %.2f = %.2f".format(numero1, operador.simboloUi, numero2, resultado)
    }
}
