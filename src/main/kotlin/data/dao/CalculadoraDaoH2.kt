package org.example.data.dao

import org.example.model.Operacion
import javax.sql.DataSource

/**
 * Implementación de [ICalculadoraDao] que utiliza una base de datos H2 para almacenar y recuperar operaciones matemáticas.
 *
 * @property ds Fuente de datos (DataSource) que proporciona conexiones a la base de datos H2.
 */
class CalculadoraDaoH2(private val ds: DataSource) : ICalculadoraDao {

    /**
     * Inserta una nueva operación en la base de datos.
     *
     * @param fecha Fecha de la operación.
     * @param num_1 Primer número del cálculo.
     * @param operador Operador matemático utilizado (ej. "+", "-", "*", "/").
     * @param num_2 Segundo número del cálculo.
     * @param resultado Resultado de la operación.
     */
    override fun agregarOperacion(
        fecha: String,
        num_1: Double,
        operador: String,
        num_2: Double,
        resultado: String
    ) {
        ds.connection.use { conn ->
            conn.prepareStatement(
                "INSERT INTO Calculadora (fecha, num_1, operador, num_2, resultado) VALUES (?, ?, ?, ?, ?)"
            ).use { stmt ->
                stmt.setString(1, fecha)
                stmt.setDouble(2, num_1)
                stmt.setString(3, operador)
                stmt.setDouble(4, num_2)
                stmt.setString(5, resultado)
                stmt.executeUpdate()
            }
        }
    }

    /**
     * Recupera todas las operaciones registradas en la base de datos, ordenadas por fecha descendente.
     *
     * @return Lista de operaciones.
     */
    override fun devolverTodos(): List<Operacion> {
        val listaOperaciones = mutableListOf<Operacion>()
        ds.connection.use { conn ->
            conn.createStatement().use { stmt ->
                stmt.executeQuery("SELECT * FROM Calculadora ORDER BY fecha DESC").use { rs ->
                    while (rs.next()) {
                        listaOperaciones.add(
                            Operacion(
                                rs.getString("fecha"),
                                rs.getDouble("num_1"),
                                rs.getString("operador"),
                                rs.getDouble("num_2"),
                                rs.getString("resultado")
                            )
                        )
                    }
                }
            }
        }
        return listaOperaciones
    }

    /**
     * Recupera todas las operaciones filtradas por un operador específico.
     *
     * @param operador Operador a filtrar (ej. '+', '-', '*', '/').
     * @return Lista de operaciones que utilizan el operador especificado.
     */
    override fun filtrarPorOperador(operador: Char): List<Operacion> {
        val listaOperaciones = mutableListOf<Operacion>()
        ds.connection.use { conn ->
            conn.prepareStatement("SELECT * FROM Calculadora WHERE operador = ?").use { stmt ->
                stmt.setString(1, operador.toString())
                stmt.executeQuery().use { rs ->
                    while (rs.next()) {
                        listaOperaciones.add(
                            Operacion(
                                rs.getString("fecha"),
                                rs.getDouble("num_1"),
                                rs.getString("operador"),
                                rs.getDouble("num_2"),
                                rs.getString("resultado")
                            )
                        )
                    }
                }
            }
        }
        return listaOperaciones
    }

    /**
     * Recupera la última operación registrada según la fecha más reciente.
     *
     * @return Última operación registrada, o `null` si no hay operaciones.
     */
    override fun devolverUltimoLog(): Operacion? {
        var ultimaOperacion: Operacion? = null
        ds.connection.use { conn ->
            conn.createStatement().use { stmt ->
                stmt.executeQuery("SELECT * FROM Calculadora ORDER BY fecha DESC LIMIT 1").use { rs ->
                    if (rs.next()) {
                        ultimaOperacion = Operacion(
                            rs.getString("fecha"),
                            rs.getDouble("num_1"),
                            rs.getString("operador"),
                            rs.getDouble("num_2"),
                            rs.getString("resultado")
                        )
                    }
                }
            }
        }
        return ultimaOperacion
    }
}
