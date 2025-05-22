package org.example.data.db


/**
 * Enumeración que define los modos disponibles para obtener una fuente de datos ([DataSource]).
 *
 * - [HIKARI]: Usa HikariCP, un pool de conexiones de alto rendimiento.
 * - [SIMPLE]: Usa una conexión directa sin pool, mediante [JdbcDataSource].
 */
enum class Mode {
    /** Modo que utiliza el pool de conexiones HikariCP. */
    HIKARI,

    /** Modo que utiliza una conexión directa sin pool de conexiones. */
    SIMPLE
}
