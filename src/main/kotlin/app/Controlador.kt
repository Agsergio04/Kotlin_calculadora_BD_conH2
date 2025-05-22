package org.example.app

import org.example.utils.RepoLogTxt
import org.example.model.Operacion
import org.example.model.Operador
import org.example.service.ServicioCalc
import org.example.service.ICalculadoraServ
import org.example.ui.IEntradaSalida

/**
 * Clase que controla el flujo principal de la aplicación de calculadora.
 *
 * @property ui Interfaz de entrada/salida para interacción con el usuario.
 * @property calculadora Servicio de cálculo matemático.
 * @property repoLog Repositorio para manejo de logs en archivos de texto.
 * @property calculadoraService Servicio que gestiona operaciones realizadas y sus registros.
 */
class Controlador(
    private val ui: IEntradaSalida,
    private val calculadora: ServicioCalc,
    private val repoLog : RepoLogTxt,
    private val calculadoraService: ICalculadoraServ
) {

    /**
     * Inicia el flujo principal de ejecución de la aplicación.
     *
     * @param args Argumentos de línea de comandos (pueden incluir operación directa).
     */
    fun iniciar(args: Array<String>) {
        if (!procesarArgumentos(args)) return

        if(calculadoraService.devolverUltimoLog() != null){
            ui.mostrar("Esta es la última Operación Registrada: \n ${calculadoraService.devolverUltimoLog()} ")
        } else {
            ui.mostrar("No se tiene registro del Último Log.")
        }

        if (args.size == 4) ejecutarCalculoConArgumentos(args)

        ui.pausar("Pulsa ENTER para acceder al menú...")
        ui.limpiarPantalla()

        mostrarMenuPrincipal()
    }

    /**
     * Muestra el menú principal y gestiona las opciones seleccionadas por el usuario.
     */
    private fun mostrarMenuPrincipal() {
        var opcion: Int
        do {
            ui.mostrar("- - - CALCULADORA - - -")
            ui.mostrar("-1. Hacer operación")
            ui.mostrar("-2. Ver historial completo")
            ui.mostrar("-3. Filtrar historial por operador")
            ui.mostrar("-4. Salir")

            opcion = ui.pedirEntero("Seleccione una opción: ") ?: 4

            when(opcion) {
                1 -> bucleCalculosUsuario()
                2 -> mostrarHistorialCompleto()
                3 -> mostrarHistorialFiltrado()
                4 -> ui.mostrar("Saliendo...")
                else -> ui.mostrarError("¡Opción no válida!")
            }

            if(opcion != 4) ui.pausar()

        } while(opcion != 4)
    }

    /**
     * Muestra todo el historial de operaciones realizadas.
     */
    private fun mostrarHistorialCompleto() {
        ui.limpiarPantalla()
        val operaciones = calculadoraService.devolverTodasOperaciones()
        mostrarOperaciones(operaciones)
    }

    /**
     * Solicita un operador al usuario y muestra las operaciones filtradas por dicho operador.
     */
    private fun mostrarHistorialFiltrado() {
        ui.limpiarPantalla()
        var operador = ui.pedirInfo("Introduce el operador a filtrar (SUMA(+), RESTA(-), MULTIPLICACION( * / x ), DIVISION('/' / ':' )): ")

        try {
            val operaciones = calculadoraService.devolverOperacionesFiltradas(operador[0])
            for(elemento in operaciones){
                ui.mostrar(elemento.toString())
            }
        } catch(e: IllegalArgumentException) {
            ui.mostrarError(e.message ?: "Error al filtrar")
        }
    }

    /**
     * Muestra una lista de operaciones en pantalla.
     *
     * @param operaciones Lista de operaciones a mostrar.
     */
    private fun mostrarOperaciones(operaciones: List<Operacion>) {
        if(operaciones.isEmpty()) {
            ui.mostrar("No hay operaciones registradas")
        } else {
            for(elemento in operaciones){
                ui.mostrar(elemento.toString())
            }
        }
    }

    companion object {
        /** Ruta por defecto para almacenar logs. */
        private const val RUTA_POR_DEFECTO = "./log"
    }

    /**
     * Procesa los argumentos recibidos desde la línea de comandos para
     * determinar si deben ejecutarse operaciones automáticas o usar ruta personalizada.
     *
     * @param args Argumentos recibidos por la aplicación.
     * @return `true` si los argumentos son válidos, `false` en caso contrario.
     */
    private fun procesarArgumentos(args: Array<String>): Boolean {
        val ruta = when (args.size) {
            0 -> RUTA_POR_DEFECTO
            1, 4 -> args[0]
            else -> {
                ui.mostrarError("Número de argumentos inválido. Esperado: 0, 1 o 4.")
                return false
            }
        }
        return true
    }

    /**
     * Ejecuta una operación aritmética directamente desde los argumentos de línea de comandos.
     *
     * @param args Argumentos con formato: número1 operador número2.
     */
    private fun ejecutarCalculoConArgumentos(args: Array<String>) {
        val numero1 = args[1].replace(',', '.').toDoubleOrNull()
        val operador = Operador.getOperador(args[2].firstOrNull())
        val numero2 = args[3].replace(',', '.').toDoubleOrNull()

        if (numero1 == null || operador == null || numero2 == null) {
            val msg = "Error en los argumentos: operación no válida."
            ui.mostrarError(msg)
        } else {
            realizarCalculo(numero1, operador, numero2)
        }
    }

    /**
     * Ejecuta un bucle interactivo de cálculos hasta que el usuario decida salir.
     */
    private fun bucleCalculosUsuario() {
        do {
            try {
                val numero1 = ui.pedirDouble("Introduce el primer número: ") ?: throw InfoCalcException("¡El primer número no es válido!")
                val simbolo = ui.pedirInfo("Introduce el operador (+, -, x, /): ").firstOrNull()
                val operador = Operador.getOperador(simbolo) ?: throw InfoCalcException("¡El operador no es válido!")
                val numero2 = ui.pedirDouble("Introduce el segundo número: ") ?: throw InfoCalcException("¡El segundo número no es válido!")

                realizarCalculo(numero1, operador, numero2)
            } catch (e: InfoCalcException) {
                val mensaje = e.message ?: "¡Se ha producido un error!"
                ui.mostrarError(mensaje)
            }
        } while (ui.preguntar())

        ui.limpiarPantalla()
    }

    /**
     * Realiza el cálculo de una operación, la muestra por pantalla y registra en el log.
     *
     * @param num1 Primer operando.
     * @param operador Operador aritmético.
     * @param num2 Segundo operando.
     */
    private fun realizarCalculo(num1: Double, operador: Operador, num2: Double) {
        val calculo = calculadora.realizarCalculo(num1, operador, num2)
        ui.mostrar(calculo.toString())
        val fecha = repoLog.crearNuevoLog()
        val simbolo = operador.simboloUi
        val resultado = calculo.resultado.toString()
        calculadoraService.agregarOperacionRealizada(fecha, num1, simbolo, num2, resultado)
    }
}
