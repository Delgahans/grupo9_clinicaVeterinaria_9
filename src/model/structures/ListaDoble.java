package model.structures;

public class ListaDoble<T> {
    private NodoDoble<T> head;
    private NodoDoble<T> tail;
    private int tamanio;

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
        NodoDoble<T> nuevo = new NodoDoble<>(dato);
        if (isEmpty()) {
            tail = nuevo;
        } else {
            nuevo.setSiguiente(head);
            head.setAnterior(nuevo);
        }
        head = nuevo;
        this.tamanio++;
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

    public void insertarFinal(T dato) {
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
}
