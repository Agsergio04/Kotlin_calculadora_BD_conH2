package org.example.app

/**
 * Excepción personalizada para errores relacionados con operaciones de cálculo en la aplicación.
 *
 * Se lanza cuando ocurre una condición no válida durante una operación aritmética,
 * como un operador incorrecto o entrada no numérica.
 *
 * @param message Mensaje que describe el motivo del error.
 */
class InfoCalcException(message: String) : Exception(message)
