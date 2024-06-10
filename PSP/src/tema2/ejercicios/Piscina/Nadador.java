package tema2.ejercicios.Piscina;

import java.util.concurrent.ThreadLocalRandom;

class Nadador implements Runnable {
    private final Piscina piscina;
    private final String nombre;
    private final String genero;

    public Nadador(Piscina piscina, String nombre, String genero) {
        this.piscina = piscina;
        this.nombre = nombre;
        this.genero = genero;
    }

    @Override
    public void run() {
        try {
            piscina.entrarNadador(nombre, genero);
            nadar();
            piscina.salirNadador(nombre, genero);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void nadar() throws InterruptedException {
        Thread.sleep(ThreadLocalRandom.current().nextInt(50, 81));
    }
}