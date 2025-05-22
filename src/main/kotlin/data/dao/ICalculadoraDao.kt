package org.example.data.dao

import org.example.model.Operacion

/**
 * Interfaz DAO (Data Access Object) para operaciones relacionadas con cálculos matemáticos.
 * Define los métodos necesarios para interactuar con la base de datos o cualquier sistema de almacenamiento.
 */
interface ICalculadoraDao {

    /**
     * Registra una nueva operación en el sistema de almacenamiento.
     *
     * @param fecha Fecha en la que se realizó la operación.
     * @param num_1 Primer operando de la operación.
     * @param operador Operador utilizado (ej: "+", "-", "*", "/").
     * @param num_2 Segundo operando de la operación.
     * @param resultado Resultado de la operación como cadena.
     */
    fun agregarOperacion(
        fecha: String,
        num_1: Double,
        operador: String,
        num_2: Double,
        resultado: String
    )

    /**
     * Recupera todas las operaciones almacenadas.
     *
     * @return Lista de todas las operaciones registradas.
     */
    fun devolverTodos(): List<Operacion>

    /**
     * Recupera todas las operaciones que utilizan un operador específico.
     *
     * @param operador Operador a utilizar como filtro (ej: '+', '-', '*', '/').
     * @return Lista de operaciones que coinciden con el operador dado.
     */
    fun filtrarPorOperador(operador: Char): List<Operacion>

    /**
     * Obtiene la operación más reciente registrada.
     *
     * @return Última operación registrada o `null` si no existe ninguna.
     */
    fun devolverUltimoLog(): Operacion?
}
