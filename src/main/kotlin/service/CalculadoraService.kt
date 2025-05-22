package org.example.service

import org.example.data.dao.ICalculadoraDao
import org.example.model.Operacion

/**
 * Implementación del servicio de calculadora que actúa como capa intermedia entre la lógica de negocio
 * y la capa de acceso a datos ([ICalculadoraDao]).
 *
 * @property calculadora Instancia del DAO para acceder a las operaciones almacenadas.
 */
class CalculadoraService(
    private val calculadora: CalculadoraDao,
) : ICalculadoraServ {

    /**
     * Registra una nueva operación realizada en el sistema.
     *
     * @param fecha Fecha de la operación (formato libre o ISO).
     * @param num_1 Primer operando.
     * @param operador Operador utilizado en la operación.
     * @param num_2 Segundo operando.
     * @param resultado Resultado de la operación.
     *
     * @throws IllegalArgumentException si alguno de los parámetros es inválido (por ejemplo, vacío o NaN).
     */
    override fun agregarOperacionRealizada(
        fecha: String,
        num_1: Double,
        operador: Char,
        num_2: Double,
        resultado: String
    ) {
        require(fecha.isNotEmpty()) { "La fecha no puede estar vacía." }
        require(!num_1.isNaN()) { "El primer número no puede ser NaN." }
        require(!num_2.isNaN()) { "El segundo número no puede ser NaN." }

        calculadora.agregarOperacion(fecha, num_1, operador.toString(), num_2, resultado)
    }

    /**
     * Obtiene todas las operaciones almacenadas.
     *
     * @return Lista de todas las [Operacion] registradas.
     */
    override fun devolverTodasOperaciones(): List<Operacion> = calculadora.devolverTodos()

    /**
     * Devuelve una lista de operaciones filtradas por el operador especificado.
     *
     * @param operador Carácter que representa el operador por el que se filtrarán las operaciones.
     * @return Lista de operaciones que coinciden con el operador.
     */
    override fun devolverOperacionesFiltradas(operador: Char): List<Operacion> {
        return calculadora.filtrarPorOperador(operador)
    }

    /**
     * Obtiene la última operación registrada en el sistema.
     *
     * @return La última [Operacion] registrada o `null` si no hay ninguna.
     */
    override fun devolverUltimoLog(): Operacion? = calculadora.devolverUltimoLog()
}
