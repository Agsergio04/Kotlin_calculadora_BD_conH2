package org.example.data.db

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.h2.jdbcx.JdbcDataSource
import javax.sql.DataSource

object GestorConexiones {

    // URL de conexión a la base de datos H2
    private val DB_URL = "jdbc:h2:./data/pedidosdb"
    // Usuario para conectarse a la base de datos
    private val USER = "root"
    // Contraseña para conectarse a la base de datos
    private val PASS = "root"

    init {
        try {
            // Se asegura de que el driver de H2 esté cargado
            Class.forName("org.h2.Driver")
        } catch (e: ClassNotFoundException) {
            throw IllegalArgumentException("No se pudo cargar el driver de H2", e)
        }
    }

    /**
     * Devuelve una instancia de [DataSource] según el modo especificado.
     *
     * @param mode Modo de conexión deseado, por defecto [Mode.HIKARI].
     * @return Una fuente de datos configurada.
     */
    fun getDataSource(mode: Mode = Mode.HIKARI): DataSource {
        return when (mode) {
            Mode.HIKARI -> {
                // Configura y devuelve un HikariDataSource
                val config = HikariConfig().apply {
                    jdbcUrl = DB_URL
                    username = USER
                    password = PASS
                    driverClassName = "org.h2.Driver"
                    maximumPoolSize = 10
                }
                HikariDataSource(config)
            }
            Mode.SIMPLE -> {
                // Devuelve un JdbcDataSource simple (sin pool de conexiones)
                JdbcDataSource().apply {
                    setURL(DB_URL)
                    user = USER
                    password = PASS
                }
            }
        }
    }
}