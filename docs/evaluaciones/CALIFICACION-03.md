# Retroalimentación — Laboratorio Pilas y Colas en el proyecto de aula

**Grupo:** Grupo9 · **Proyecto:** Clínica Veterinaria

## Nota

| Criterio | Pilas (pts) | Colas (pts) |
|---|---|---|
| `Pila<T>` / `Cola<T>` correcta (30) | 28 | 30 |
| Casos límite (10) | 1 | 10 |
| Integración en la entidad / entidad dueña de la cola | 13 de 30 | 2 de 15 |
| Integración en el `Service` (solo Colas, 25) | — | 16 |
| Menú en `view/` | 0 de 15 | 10 de 10 |
| Buenas prácticas | 4 de 15 | 5 de 10 |
| **Total** | **46 / 100** | **73 / 100** |

**Nota del laboratorio: 2.98**

La nota se calcula así: se suman los dos totales (46 + 73), se divide entre 2 (59.5) y luego entre 20.

## Pilas

**Lo que hicieron bien:**
- `Pila<T>` usa `Nodo<T>`, no usa `java.util` y sus cinco operaciones son directas. `push` enlaza bien antes de mover el tope.
- `Animal` guarda la pila como atributo privado, la crea en el constructor y tiene su getter.
- `ConsultaService` apila al agregar una consulta y `retirarUltimaConsulta` quita la consulta del animal.

**Lo que pueden mejorar:**
- `pop` y `peek` devuelven `null` cuando la pila está vacía; deben lanzar `IllegalStateException` con mensaje. Además `push(null)` se acepta; debe lanzar `IllegalArgumentException`.
- Faltan los métodos del `Service` pedidos: saber si hay operaciones, y `listarOperaciones` (con pila auxiliar, sin destruir la pila). Los que existen reciben el `Animal` completo en vez de datos simples como la ficha.
- No existe `MenuPilaView` ni se conectó en `Main`, así que la secuencia de verificación de pilas no se puede hacer.
- No hay rama `feature/pila-en-el-proyecto`; el trabajo de pilas fue un solo commit ("faltan ajustes") traído por una fusión.

## Colas

**Lo que hicieron bien:**
- `Cola<T>` está muy bien: `frente` y `fin` quedan en `null` al vaciarla, lanza `NoSuchElementException` con mensaje, rechaza `null` y se puede encolar de nuevo tras vaciarla. Lo comprobamos con una prueba propia.
- `listarEnEspera` rota la fila `size()` veces y la deja igual. `atenderSiguiente` no borra al animal de la lista.
- `MenuColaView` cumple: usa `ConsoleUtils`, no usa `try/catch`, no importa nada de `model.structures` y revisa si hay animales antes de atender o consultar.

**Lo que pueden mejorar:**
- Falta la clase `SalaDeEspera` (la entidad dueña de la fila, con `nombre` y `fila`). El `Service` la usa pero nunca la crearon.
- No siguieron la estructura de carpetas acordada en clase: `SalaDeEsperaService.java` está dentro de `model/domain` y debía estar en `service`.
- Trabajaron en `developerDelgahans` en lugar de `feature/cola-en-el-proyecto`, con un solo autor y todos los commits en una tarde.

## Anotación sobre la ejecución
No compila. Dos archivos (`Vacuna.java` y `VacunaService.java`) y `MenuListasView.java` tienen texto de Markdown (las comillas ```` ```java ````) y marcas de conflicto de la fusión sin resolver, y falta `SalaDeEspera`. Por eso no se puede ejecutar el `Main`.
