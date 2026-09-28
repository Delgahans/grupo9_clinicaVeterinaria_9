```java
package service;

import model.domain.Animal;
import model.domain.Vacuna;

public class VacunaService {

    public void agregarVacuna(Animal animal, Vacuna vacuna) {
        animal.agregarVacuna(vacuna);
    }

    public boolean buscarVacuna(Animal animal, Vacuna vacuna) {
        return animal.buscarVacuna(vacuna);
    }

    public boolean eliminarVacuna(Animal animal, Vacuna vacuna) {
        return animal.eliminarVacuna(vacuna);
    }

    public int cantidadVacunas(Animal animal) {
        return animal.getVacunas().getTamano();
    }
}
```
