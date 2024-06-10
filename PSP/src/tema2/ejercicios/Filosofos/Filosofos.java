package tema2.ejercicios.Filosofos;

import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;

class Filosofo implements Runnable {
    private final String nombre;
    private final int id;
    private final Semaphore[] palillos;

    public Filosofo(String nombre, int id, Semaphore[] palillos) {
        this.nombre = nombre;
        this.id = id;
        this.palillos = palillos;
    }

    @Override
    public void run() {
        try {
            while (true) {
                pensar();
                if (id % 2 == 0) {
                    
                    tomarPalilloIzquierdo();
                    tomarPalilloDerecho();
                } else {
                    tomarPalilloDerecho();
                    tomarPalilloIzquierdo();
                }
                comer();
                soltarPalillos();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void pensar() throws InterruptedException {
        System.out.println(nombre + " está pensando.");
        Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 2000));
    }

    private void tomarPalilloIzquierdo() throws InterruptedException {
        palillos[id].acquire();
        System.out.println(nombre + " ha tomado el palillo izquierdo.");
    }

    private void tomarPalilloDerecho() throws InterruptedException {
        palillos[(id + 1) % palillos.length].acquire();
        System.out.println(nombre + " ha tomado el palillo derecho.");
    }

    private void comer() throws InterruptedException {
        System.out.println(nombre + " está comiendo.");
        Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 2000));
    }

    private void soltarPalillos() {
        palillos[id].release();
        palillos[(id + 1) % palillos.length].release();
        System.out.println(nombre + " ha soltado los palillos.");
    }
}
