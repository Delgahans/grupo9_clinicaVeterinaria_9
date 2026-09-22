# Retroalimentación — Laboratorio 1: Codificación del diseño OO

**Grupo:** Grupo9 · **Proyecto:** Clínica Veterinaria
**Fecha límite:** 2026-09-08 23:59 · **Versión revisada:** commit `cd9a13b`

## Nota

| Criterio | Peso | Nota (0-5) |
|---|---|---|
| El código sigue el diagrama UML | 60% | 3.5 |
| Pruebas: creación de objetos en el programa | 20% | 0.0 |
| Buenas prácticas de programación | 20% | 4.5 |
| **Nota del laboratorio** | | **3.00** |

La nota se calcula así: 60% diseño UML + 20% pruebas + 20% buenas prácticas.

## 1. El código sigue el diagrama UML (3.5)
**Lo que hicieron bien:**
- Las clases del dominio están en la carpeta `src/model/domain/`, como se acordó.
- `Dueno` y `Veterinario` heredan de `Persona`, llaman a `super(...)` y guardan sus propios datos (`direccion` y `especialidad`).
- Dejaron a `Animal` por fuera de la herencia, tal como pide el diagrama.
- `Dueno` guarda su lista de animales y tiene métodos para agregar, eliminar y buscar animales.
- `Consulta` y `Vacuna` están bien relacionadas con `Animal` (y `Consulta` también con `Veterinario`).
- `rolEnClinica()` da una respuesta distinta en `Dueno` y en `Veterinario`, como se pedía.

**Lo que pueden mejorar:**
- A `Persona.java` y `RolEnClinica.java` les falta la línea `package model.domain;` al inicio. Esto no sigue la convención de paquetes acordada, y además es la razón por la que el proyecto no compila: Java no encuentra esas clases junto a las demás.
- La interfaz `RolEnClinica` está vacía. Le faltan los métodos `datosResumen()` y `rolEnClinica()`. Sin ellos, los `@Override` de las otras clases no tienen nada que sobrescribir y dan error.
- Faltan dos validaciones en los constructores: `Persona` debe rechazar una `identificacion` vacía y `Animal` debe rechazar un `numeroFicha` vacío (por ejemplo, lanzando `IllegalArgumentException`).

## 2. Pruebas: creación de objetos (0.0)
**Lo que pueden mejorar:**
- No encontramos una clase que cree los objetos y los pruebe (`PruebaCreacionObjetos`). Esta clase es la que muestra en consola que su diseño funciona: crear un dueño con sus animales, un veterinario, una consulta, y llamar a los métodos. Para el próximo laboratorio es muy importante incluirla.

## 3. Buenas prácticas (4.5)
**Lo que hicieron bien:**
- Varios commits antes de la fecha límite, de los tres integrantes, con mensajes que explican qué se hizo. Se nota trabajo en equipo y constante.
- Los nombres siguen las costumbres de Java: clases como `Animal` o `Veterinario` y métodos como `agregarAnimal`.

**Lo que pueden mejorar:**
- Todo se hizo directamente sobre `main`. Prueben trabajar en ramas y luego unirlas a `main`.
- Algunos commits tienen el mensaje "Add files via upload". Mejor escribir un mensaje que diga qué cambió.

## ¿El programa funciona?
Todavía no: el proyecto no compila por la línea `package` que falta en dos archivos y por la interfaz vacía. Son arreglos pequeños, y con ellos el código que ya tienen debería funcionar.

## Para el próximo laboratorio
- Revisen que todos los archivos de `model/domain` empiecen con `package model.domain;`.
- Completen la interfaz `RolEnClinica` con sus métodos antes de usar `@Override`.
- Compilen el proyecto en su computador antes de cada commit para confirmar que no hay errores.
- Creen la clase `PruebaCreacionObjetos` que cree los objetos y muestre los resultados en consola.
- Agreguen las validaciones en los constructores de `Persona` y `Animal`.
