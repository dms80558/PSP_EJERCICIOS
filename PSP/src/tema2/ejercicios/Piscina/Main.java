package tema2.ejercicios.Piscina;

import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static void main(String[] args) {
        Piscina piscina = new Piscina(8);

        Thread[] usuarios = new Thread[20];
        for (int i = 0; i < 20; i++) {
            if (ThreadLocalRandom.current().nextBoolean()) {
                usuarios[i] = new Thread(new Nadador(piscina, "Nadador" + i, getRandomgenero()));
            } else {
                usuarios[i] = new Thread(new Submarinista(piscina, "Submarinista" + i));
            }
            usuarios[i].start();
        }

        for (Thread usuario : usuarios) {
            try {
                usuario.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static String getRandomgenero() {
        String[] generos = {"Hombre", "Mujer", "Niño", "Niña"};
        return generos[ThreadLocalRandom.current().nextInt(generos.length)];
    }
}
