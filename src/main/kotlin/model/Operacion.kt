package org.example.model

/**
 * Representa una operación matemática registrada, típicamente almacenada en una base de datos.
 *
 * @property id Identificador único de la operación (puede ser una marca de tiempo o UUID).
 * @property numero1 Primer número involucrado en la operación.
 * @property operador Símbolo del operador utilizado (por ejemplo: "+", "-", "*", "/").
 * @property numero2 Segundo número involucrado en la operación.
 * @property resultado Resultado de la operación, almacenado como cadena (por compatibilidad con sistemas de persistencia).
 */
data class Operacion(
    var id: String,
    val numero1: Double,
    val operador: String,
    val numero2: Double,
    val resultado: String
) {
    /**
     * Devuelve una representación en cadena detallada de la operación.
     *
     * @return Cadena con todos los campos de la operación formateados.
     */
    override fun toString(): String {
        return "Id = $id - Numero1 = $numero1 - Operador = $operador  -  Numero2 = $numero2 - Resultado = $resultado"
    }
}
