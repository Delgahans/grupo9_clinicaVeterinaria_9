# Retroalimentación — Laboratorio Lista Simple (Momento 2)

**Grupo:** Grupo9 · **Proyecto:** Clínica Veterinaria

## Nota

| Criterio | Peso | Nota (0-5) |
|---|---|---|
| Identificación de las relaciones uno-a-muchos | 20% | 5.0 |
| `ListaSimple<T>` integrada al `Service` | 30% | 3.5 |
| Menú en consola funcional | 15% | 2.5 |
| Reemplazo del arreglo previo, sin código muerto | 20% | 5.0 |
| Buenas prácticas (commits y nombres) | 15% | 3.5 |
| **Nota del laboratorio** | | **3.95** |

La nota se calcula así: 20% relaciones + 30% integración al `Service` + 15% menú + 20% reemplazo + 15% buenas prácticas.

## 1. Relaciones uno-a-muchos (5.0)
**Lo que hicieron bien:**
- Eligieron dos relaciones reales y correctas: un `Animal` tiene muchas `Vacuna` y un `Animal` tiene muchas `Consulta`.
- `Vacuna` y `Consulta` también guardan a qué `Animal` pertenecen.

## 2. `ListaSimple<T>` integrada al `Service` (3.5)
**Lo que hicieron bien:**
- `Animal` guarda `ListaSimple<Vacuna>` y `ListaSimple<Consulta>` y usa `insertarFinal`, `buscarPorValor` y `eliminarPorValor`.
- `VacunaService` y `ConsultaService` llaman a esos métodos y no tocan los nodos.
- `Vacuna` y `Consulta` tienen `equals` y `hashCode`, así que la búsqueda y la eliminación por valor funcionan.

**Lo que pueden mejorar:**
- La vista `MenuListasView` pide la lista directamente con `animal.getVacunas().buscarPorIndice(i)` y `animal.getConsultas().buscarPorIndice(i)`. La vista no debe conocer la lista: el listado debería pedirse al `Service`.
- Varios archivos tienen al inicio y al final unas marcas de código de Markdown (tres comillas invertidas con la palabra `java`). Java no las entiende, y por eso el proyecto no compila.

## 3. Menú en consola (2.5)
**Lo que hicieron bien:**
- El menú está bien pensado: tiene un submenú para vacunas y otro para consultas, con agregar, buscar, listar y eliminar.
- Valida que se escriban números y fechas con el formato correcto, y siempre pasa por el `Service`.

**Lo que pueden mejorar:**
- Como el proyecto no compila tal como lo entregaron, el menú no se puede ejecutar. Al quitar esas marcas sobrantes, el menú sí funciona.
- Las consultas se crean sin veterinario (`null`).

## 4. Reemplazo del arreglo previo (5.0)
**Lo que hicieron bien:**
- Las vacunas y las consultas del `Animal` quedaron solo con `ListaSimple<T>`: no queda ningún arreglo ni `ArrayList` sin usar, ni imports ni métodos viejos de esas relaciones.

## 5. Buenas prácticas (3.5)
**Lo que hicieron bien:**
- Hicieron muchos commits pequeños, con mensajes que explican qué cambió.
- Los nombres de clases, métodos y variables siguen las convenciones de Java.
- Respetaron la estructura de carpetas (`model/domain`, `model/structures`, `service`, `view`).

**Lo que pueden mejorar:**
- Casi todo el trabajo de este laboratorio lo hizo una sola persona y se concentró en dos días, el 26 y el 27 de septiembre. Repártanlo entre todo el equipo y usen las ramas de cada integrante.
- El archivo `jose.md` en la raíz del proyecto no hace parte de la entrega y se puede quitar.

## ¿El programa funciona?
No compila tal como está, por las marcas de Markdown que quedaron en cinco archivos. Sin esas marcas, el código sí funciona y el menú responde bien.

## Para el próximo laboratorio
- Antes de entregar, compilen el proyecto completo desde cero y ejecuten el menú.
- No copien las marcas de Markdown al pegar código en los archivos `.java`.
- Que la vista no llame a la lista: agreguen en el `Service` un método para listar y úsenlo desde el menú.
- Repartan el trabajo entre todos los integrantes y háganlo con commits de cada uno.
