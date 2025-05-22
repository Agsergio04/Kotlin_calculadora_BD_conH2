package org.example.service

import org.example.model.Operacion

/**
 * Interfaz que define los servicios disponibles para gestionar operaciones matemáticas
 * realizadas por la calculadora.
 */
interface ICalculadoraServ {

    /**
     * Registra una nueva operación en el sistema.
     *
     * @param fecha Fecha de la operación (puede ser timestamp, fecha formateada, etc.).
     * @param num_1 Primer número de la operación.
     * @param operador Operador utilizado (por ejemplo: '+', '-', '*', '/').
     * @param num_2 Segundo número de la operación.
     * @param resultado Resultado de la operación expresado como cadena.
     */
    fun agregarOperacionRealizada(
        fecha: String,
        num_1: Double,
        operador: Char,
        num_2: Double,
        resultado: String
    )

    /**
     * Recupera todas las operaciones registradas.
     *
     * @return Lista de todas las [Operacion] almacenadas.
     */
    fun devolverTodasOperaciones(): List<Operacion>

    /**
     * Recupera las operaciones que corresponden a un operador específico.
     *
     * @param operador Carácter del operador para filtrar (por ejemplo, '+').
     * @return Lista de [Operacion] filtradas por ese operador.
     */
    fun devolverOperacionesFiltradas(operador: Char): List<Operacion>

    /**
     * Recupera la última operación registrada en el sistema.
     *
     * @return Última [Operacion] o `null` si no hay registros.
     */
    fun devolverUltimoLog(): Operacion?
}
