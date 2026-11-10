
package model.structures;

public class ListaDoble<T> {
    private NodoDoble<T> head;
    private NodoDoble<T> tail;
    private int tamanio;


    public T obtenerSiguiente(NodoDoble<T> nodo) {
        if (nodo == null || nodo.getSiguiente() == null) {
            return null;
        }

        return nodo.getSiguiente().getDato();
    }

    public T obtenerAnterior(NodoDoble<T> nodo) {
        if (nodo == null || nodo.getAnterior() == null) {
            return null;
        }

        return nodo.getAnterior().getDato();
    }


    public ListaDoble() {
        this.head = null;
        this.tail = null;
        this.tamanio = 0;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int getTamanio() {
        return this.tamanio;
    }

    public void insertarInicio(T dato) {
        if (dato == null) {
            throw new IllegalArgumentException("El dato no puede ser null");
        }

        NodoDoble<T> nuevo = new NodoDoble<>(dato);

        if (isEmpty()) {
            tail = nuevo;
        } else {
            nuevo.setSiguiente(head);
            head.setAnterior(nuevo);
        }

        head = nuevo;
        tamanio++;
    }

    public void insertarFinal(T dato) {
        if (dato == null) {
            throw new IllegalArgumentException("El dato no puede ser null");
        }

        NodoDoble<T> nuevo = new NodoDoble<>(dato);

        if (isEmpty()) {
            head = nuevo;
            tail = nuevo;
        } else {
            tail.setSiguiente(nuevo);
            nuevo.setAnterior(tail);
            tail = nuevo;
        }

        tamanio++;
    }

    public void recorrer() {
        NodoDoble<T> actual = head;

        while (actual != null) {
            System.out.print(actual.getDato() + " ");
            actual = actual.getSiguiente();
        }

        System.out.println();
    }

    public void recorrerInverso() {
        NodoDoble<T> actual = tail;

        while (actual != null) {
            System.out.print(actual.getDato() + " ");
            actual = actual.getAnterior();
        }

        System.out.println();
    }

    public NodoDoble<T> buscar(T dato) {
        NodoDoble<T> actual = head;

        while (actual != null) {
            if (actual.getDato().equals(dato)) {
                return actual;
            }

            actual = actual.getSiguiente();
        }

        return null;
    }

    public T eliminar(NodoDoble<T> nodo) {
        if (nodo == null) {
            throw new IllegalArgumentException("El nodo no puede ser null");
        }

        // Verificar que el nodo pertenezca a esta lista
        NodoDoble<T> actual = head;

        while (actual != null && actual != nodo) {
            actual = actual.getSiguiente();
        }

        if (actual == null) {
            throw new IllegalArgumentException("El nodo no pertenece a esta lista");
        }

        NodoDoble<T> anterior = nodo.getAnterior();
        NodoDoble<T> siguiente = nodo.getSiguiente();

        // Caso 1: eliminar el único nodo
        if (anterior == null && siguiente == null) {
            head = null;
            tail = null;
        }
        // Caso 2: eliminar el primer nodo
        else if (anterior == null) {
            head = siguiente;
            siguiente.setAnterior(null);
        }
        // Caso 3: eliminar el último nodo
        else if (siguiente == null) {
            tail = anterior;
            anterior.setSiguiente(null);
        }
        // Caso 4: eliminar un nodo del medio
        else {
            anterior.setSiguiente(siguiente);
            siguiente.setAnterior(anterior);
        }

        nodo.setAnterior(null);
        nodo.setSiguiente(null);

        tamanio--;

        return nodo.getDato();
    }


    public T obtenerPrimero() {
        if (isEmpty()) {
            return null;
        }

        return head.getDato();
    }

    public T obtenerUltimo() {
        if (isEmpty()) {
            return null;
        }

        return tail.getDato();
    }

    public NodoDoble<T> obtenerPrimerNodo() {
    return head;
}

    public NodoDoble<T> obtenerUltimoNodo() {
        return tail;
    }
}
