package org.example.utils

/**
 * Interfaz que define el contrato para la creación de identificadores de log.
 *
 * Esta interfaz se utiliza para generar un nuevo identificador único o marca
 * para registrar una operación en un sistema de logging o persistencia.
 */
interface IRepoLog {

    /**
     * Genera y devuelve un nuevo identificador de log.
     *
     * @return Un `String` que representa un nuevo identificador o marca de log.
     */
    fun crearNuevoLog(): String
}
