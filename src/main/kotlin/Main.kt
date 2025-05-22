package org.example

import org.example.app.Controlador
import org.example.data.dao.CalculadoraDaoH2
import org.example.data.db.GestorConexiones
import org.example.data.db.Mode
import org.example.utils.RepoLogTxt
import org.example.service.CalculadoraService
import org.example.service.ServicioCalc
import org.example.ui.Consola

/**
 * Punto de entrada de la aplicación de calculadora.
 *
 * Esta función se encarga de inicializar todos los componentes necesarios:
 * - Interfaz de usuario (consola)
 * - Fuente de datos (base de datos H2)
 * - Servicios de cálculo y persistencia
 * - Controlador principal de la lógica de interacción
 *
 * Luego, delega el control al método [Controlador.iniciar], pasando los argumentos
 * recibidos desde la línea de comandos.
 *
 * @param args Argumentos de línea de comandos. Puede incluir:
 * - Ningún argumento: ejecución interactiva normal
 * - Un argumento: ruta del log
 * - Cuatro argumentos: ejecución directa de una operación (ruta, número1, operador, número2)
 */
fun main(args: Array<String>) {
    val ui = Consola()

    val dataSource = try {
        GestorConexiones.getDataSource(Mode.SIMPLE)
    } catch (e: IllegalStateException) {
        ui.mostrarError("Problemas al crear el DataSource: ${e.message}")
        return
    }

    val calculadoraService = CalculadoraService(CalculadoraDaoH2(dataSource))
    val controlador = Controlador(
        ui = ui,
        calculadora = ServicioCalc(),
        repoLog = RepoLogTxt(),
        calculadoraService = calculadoraService
    )

    controlador.iniciar(args)
}
