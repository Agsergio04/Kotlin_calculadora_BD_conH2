package org.example.utils

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * Implementación de [IRepoLog] que genera un identificador de log
 * basado en la fecha y hora actual con precisión hasta los segundos.
 *
 * Este identificador se puede usar como timestamp único para registrar
 * operaciones o eventos en sistemas de logging.
 */
class RepoLogTxt : IRepoLog {

    /**
     * Genera un nuevo identificador de log usando la fecha y hora actual
     * en el formato `yyyyMMddHHmmss` (por ejemplo, `20250522113045`).
     *
     * @return Un [String] que representa la marca de tiempo actual formateada.
     */
    override fun crearNuevoLog(): String {
        val fecha = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"))
        return "$fecha"
    }
}
