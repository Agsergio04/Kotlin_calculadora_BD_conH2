package org.example.service

import org.example.model.Calculo

interface IServicioLog {
    fun registrarEntradaLog(mensaje: String)
    fun registrarEntradaLog(calculo: Calculo)
    fun getInfoUltimoLog(): List<String>
    fun crearNuevoLog()
    fun crearRutaLog(ruta: String): Boolean
}