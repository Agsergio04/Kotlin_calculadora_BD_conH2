# Calculadora Basica con almacenamiento en H2
## Descripcion
Calculadora basica estructurada siguiendo los principios de la **Programacion Orientada a Objetos(POO)**,**principios SOLID** y una persistencia de datos por medio de una **base de datos** alojada en H2  
### Enunciado de la practica  
---
## Estructura del Proyecto  

```
Kotlin_calculadora_BD_conH2/
├── data #Repositorio el cual guardo la base de datos H2 junto al Script en .sql para la creacion de las tablas
│ 
├── src/
│   ├── main/
│   │   ├── kotlin/
│   │   │   ├── app/ #Controla el flujo del programa junto a la gestion del menu 
│   │   │   ├── data/ #Se encarga todo lo referente al almacenamiento de datos de nuestra calculadora 
│   │   │   │   ├── dao/ #Es el encargado de comunicarse con la base de datos por medio de los metodos CRUD (en este caso la base de datos es en H2)
│   │   │   │   ├── db/ #Se encarga de la apertura y cierre de las conexiones en la base de datos 
│   │   │   ├── model/ #Su funcion es la creacion de las entidades del dominio como `Calculo` `Operacion` y el Enumerado `Operador`
│   │   │   ├─ service/ #Se encarga de  operar la Inteligencia de Negocio que en este caso es el control de datos y la realizacion de las operaciones
│   │   │   ├── ui/ #Es la responsable de la entrada de datos del programa y la salida de la informacion a traves de la consola
│   │   |   ├── utils/ #Contiene funcionalidades utilies para la creacion de los logs 
│   │   |   ├── Main # Punto de entrada del programa                  
```
---
## Fundamentos Solid
Aqui explico un poco a cerca de los principios solid que cumple este proyecto  


### 1. Single Responsibility Principle (SRP)

- **ServicioCalc**: Solo se encarga de realizar cálculos.
- **CalculadoraService**: Solo gestiona operaciones con el DAO.
- **Consola**: Solo maneja entrada/salida por terminal.

### 2. Open/Closed Principle (OCP)

- **Operador**: Se pueden añadir nuevos operadores sin modificar el código existente.
- **IEntradaSalida**: Permite nuevas implementaciones sin cambiar el contrato.

### 3. Liskov Substitution Principle (LSP)

- **CalculadoraDaoH2** puede sustituir a **ICalculadoraDao**.
- **RepoLogTxt** puede sustituir a **IRepoLog**.
- **Consola** puede sustituir a **IEntradaSalida**.

### 4. Interface Segregation Principle (ISP)

- **IEntradaSalida** tiene métodos cohesivos solo para UI.
- **ICalculadoraServ** separa responsabilidades de consulta y almacenamiento.

### 5. Dependency Inversion Principle (DIP)

- **Controlador** depende de abstracciones (**IEntradaSalida**, **ICalculadoraServ**).

---

## Principios POO 

### Encapsulación

- **GestorConexiones** encapsula los detalles de conexión.

### Composición sobre herencia

- **Controlador** compone servicios en lugar de heredar de ellos.
- **CalculadoraService** contiene un **ICalculadoraDao** en lugar de heredar.

### Polimorfismo

- **Operador.getOperador()** devuelve diferentes implementaciones según el símbolo.

### Abstracción

- Las interfaces definen contratos claros sin implementación.
- El Enumerado **Operador** abstrae los diferentes símbolos para cada operación.

### Bajo acoplamiento

- Los componentes se comunican a través de interfaces.
- El **Controlador** no conoce implementaciones concretas de los servicios.

### Alta cohesión

- Cada clase tiene responsabilidades bien definidas y relacionadas.
- **ServicioCalc** solo contiene lógica de cálculo matemático.

--- 

## Funcionamiento del programa 

Este es el punto de entrada al programa:  
https://github.com/Agsergio04/Kotlin_calculadora_BD_conH2/blob/bc37e450f7e154a3395ab9a6b3422da30a4754c9/src/main/kotlin/Main.kt#L29-L48  

Tras la ejecucion del programa se le mostrara al usuario la ultima operacion realizada en la calculadora :  
![inicio](https://github.com/Agsergio04/Kotlin_calculadora_BD_conH2/blob/master/fotos/inicio.png)  

Tras ellos se le mostrara al usuario un menu el cual posee estas opciones:
-1 Realizar una operacion.
-2 Mostrar todas las operaciones dadas de la calculadora.
-3 Mostrar todas las operaciones hechas segun el tipo de operacion.
-4 Salir
![menu](https://github.com/Agsergio04/Kotlin_calculadora_BD_conH2/blob/master/fotos/menu.png)  

Aqui un ejemplo al realizar la segunda opcion de la calculadora :  
![logs](https://github.com/Agsergio04/Kotlin_calculadora_BD_conH2/blob/master/fotos/logs.png) 



