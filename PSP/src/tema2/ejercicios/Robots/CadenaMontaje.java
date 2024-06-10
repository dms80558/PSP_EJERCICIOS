package tema2.ejercicios.Robots;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

class CadenaMontaje {
    private final int capacidad;
    private final Queue<Integer> cadena;
    private final Semaphore mutex;
    private final Semaphore hayEspacio;
    private final Semaphore[] hayProducto;
    private int totalEmpaquetados;

    public CadenaMontaje(int capacidad) {
        this.capacidad = capacidad;
        this.cadena = new LinkedList<>();
        this.mutex = new Semaphore(1);
        this.hayEspacio = new Semaphore(capacidad);
        this.hayProducto = new Semaphore[]{new Semaphore(0), new Semaphore(0), new Semaphore(0)};
        this.totalEmpaquetados = 0;
    }

    public void colocarProducto(int producto) throws InterruptedException {
        hayEspacio.acquire();
        mutex.acquire();
        try {
            cadena.add(producto);
            System.out.println("Colocado producto de tipo " + producto);
            hayProducto[producto - 1].release();
        } finally {
            mutex.release();
        }
    }

    public void retirarProducto(int tipo) throws InterruptedException {
        hayProducto[tipo - 1].acquire();
        mutex.acquire();
        try {
            cadena.remove((Integer) tipo);
            totalEmpaquetados++;
            System.out.println("Empaquetado producto de tipo " + tipo + ". Total empaquetados: " + totalEmpaquetados);
            hayEspacio.release();
        } finally {
            mutex.release();
        }
    }
}