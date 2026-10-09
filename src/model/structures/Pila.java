package model.structures;

public class Pila<T> {

    private Nodo<T> tope;
    private int tamano;

    public Pila() {
        this.tope = null;
        this.tamano = 0;
    }

    public void push(T valor) {
        Nodo<T> nuevo = new Nodo<>(valor);
        nuevo.setSiguiente(tope);
        tope = nuevo;
        tamano++;
    }

    public T pop() {
        if (isEmpty()) {
            return null;
        }

        T valor = tope.getDato();
        tope = tope.getSiguiente();
        tamano--;

        return valor;
    }

    public T peek() {
        if (isEmpty()) {
            return null;
        }

        return tope.getDato();
    }

    public boolean isEmpty() {
        return tope == null;
    }

    public int size() {
        return tamano;
    }
}
