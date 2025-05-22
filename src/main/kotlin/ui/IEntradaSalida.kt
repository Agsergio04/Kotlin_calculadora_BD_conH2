package org.example.ui

/**
 * Interfaz que define los métodos necesarios para manejar la entrada y salida
 * de datos en la aplicación, permitiendo desacoplar la lógica de presentación
 * del resto de la aplicación.
 */
interface IEntradaSalida {

    /**
     * Muestra un mensaje al usuario.
     *
     * @param msj Mensaje a mostrar.
     * @param salto Indica si se debe agregar un salto de línea al final.
     */
    fun mostrar(msj: String = "", salto: Boolean = true)

    /**
     * Muestra un mensaje de error al usuario.
     *
     * @param msj Mensaje de error.
     * @param salto Indica si se debe agregar un salto de línea al final.
     */
    fun mostrarError(msj: String, salto: Boolean = true)

    /**
     * Solicita información textual al usuario.
     *
     * @param msj Mensaje que se muestra antes de la solicitud.
     * @return Cadena introducida por el usuario.
     */
    fun pedirInfo(msj: String = ""): String

    /**
     * Solicita un número decimal (Double) al usuario.
     *
     * @param msj Mensaje que se muestra antes de la solicitud.
     * @return Valor introducido como Double, o `null` si no es válido.
     */
    fun pedirDouble(msj: String = ""): Double?

    /**
     * Solicita un número entero (Int) al usuario.
     *
     * @param msj Mensaje que se muestra antes de la solicitud.
     * @return Valor introducido como Int, o `null` si no es válido.
     */
    fun pedirEntero(msj: String = ""): Int?

    /**
     * Pregunta al usuario si desea repetir una acción o continuar.
     *
     * @param msj Pregunta mostrada al usuario.
     * @return `true` si la respuesta es afirmativa, `false` en caso contrario.
     */
    fun preguntar(msj: String = "¿Deseas intentarlo de nuevo? (s/n): "): Boolean

    /**
     * Limpia la pantalla simulando múltiples saltos de línea.
     *
     * @param numSaltos Número de saltos de línea a imprimir.
     */
    fun limpiarPantalla(numSaltos: Int = 20)

    /**
     * Pausa la ejecución hasta que el usuario presione Enter.
     *
     * @param msj Mensaje mostrado antes de la pausa.
     */
    fun pausar(msj: String = "Pulse Enter para Continuar...")
}
