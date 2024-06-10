package tema2.ejercicios.Piscina;

import java.util.concurrent.ThreadLocalRandom;

class Submarinista implements Runnable {
    private final Piscina piscina;
    private final String nombre;

    public Submarinista(Piscina piscina, String nombre) {
        this.piscina = piscina;
        this.nombre = nombre;
    }

    @Override
    public void run() {
        try {
            piscina.entrarSubmarinista(nombre);
            bucear();
            piscina.salirSubmarinista(nombre);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void bucear() throws InterruptedException {
        Thread.sleep(ThreadLocalRandom.current().nextInt(50, 81));
    }
}
