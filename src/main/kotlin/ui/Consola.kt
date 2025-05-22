package org.example.ui

import java.util.Scanner

/**
 * Implementación de la interfaz [IEntradaSalida] que utiliza la consola estándar
 * para la entrada y salida de datos.
 *
 * Esta clase permite mostrar mensajes, leer diferentes tipos de datos del usuario
 * y gestionar la interacción con el teclado a través del terminal.
 */
class Consola : IEntradaSalida {
    private val scanner = Scanner(System.`in`)

    /**
     * Muestra un mensaje por consola, con o sin salto de línea final.
     *
     * @param msj El mensaje a mostrar.
     * @param salto Si es `true`, agrega un salto de línea al final.
     */
    override fun mostrar(msj: String, salto: Boolean) {
        print("$msj${if (salto) "\n" else ""}")
    }

    /**
     * Muestra un mensaje de error formateado con el prefijo "ERROR - ".
     *
     * @param msj Mensaje de error.
     * @param salto Si es `true`, agrega un salto de línea al final.
     */
    override fun mostrarError(msj: String, salto: Boolean) {
        mostrar("ERROR - $msj", salto)
    }

    /**
     * Solicita una cadena de texto al usuario.
     *
     * @param msj Mensaje mostrado antes de la entrada.
     * @return Texto introducido por el usuario, sin espacios laterales.
     */
    override fun pedirInfo(msj: String): String {
        if (msj.isNotEmpty()) mostrar(msj, false)
        return scanner.nextLine().trim()
    }

    /**
     * Solicita al usuario un número decimal.
     * Acepta también comas como separador decimal, reemplazándolas por puntos.
     *
     * @param msj Mensaje mostrado antes de la entrada.
     * @return Número decimal o `null` si la entrada no es válida.
     */
    override fun pedirDouble(msj: String): Double? =
        pedirInfo(msj).replace(',', '.').toDoubleOrNull()

    /**
     * Solicita al usuario un número entero.
     *
     * @param msj Mensaje mostrado antes de la entrada.
     * @return Número entero o `null` si la entrada no es válida.
     */
    override fun pedirEntero(msj: String): Int? =
        pedirInfo(msj).toIntOrNull()

    /**
     * Pregunta al usuario si desea continuar.
     * Se aceptan "s", "si", "n", "no" (mayúsculas o minúsculas).
     *
     * @param msj Pregunta que se le hace al usuario.
     * @return `true` si el usuario responde afirmativamente, `false` si no.
     */
    override fun preguntar(msj: String): Boolean {
        do {
            val respuesta = pedirInfo(msj).lowercase()
            when (respuesta) {
                "s", "si" -> return true
                "n", "no" -> return false
                else -> mostrarError("Respuesta no válida. Responde con s, n, si o no.")
            }
        } while (true)
    }

    /**
     * Limpia la pantalla simulando múltiples saltos de línea.
     * En consolas compatibles intenta usar códigos ANSI.
     *
     * @param numSaltos Número de líneas en blanco a imprimir si ANSI no es compatible.
     */
    override fun limpiarPantalla(numSaltos: Int) {
        if (System.console() != null) {
            mostrar("\u001b[H\u001b[2J", false)
            System.out.flush()
        } else {
            repeat(numSaltos) {
                mostrar("")
            }
        }
    }

    /**
     * Pausa la ejecución esperando que el usuario presione ENTER.
     *
     * @param msj Mensaje que se muestra antes de la pausa.
     */
    override fun pausar(msj: String) {
        pedirInfo("\n$msj")
        mostrar()
    }
}
