package tema2.ejercicios.Secuencia;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Semaphore;

class EscribeLetras extends Thread {
    private final char letra;
    private final int repeticiones;
    private final List<Character> letras;
    private final Map<Character, Semaphore> semaforos;

    public EscribeLetras(char letra, int repeticiones, List<Character> letras, Map<Character, Semaphore> semaforos) {
        this.letra = letra;
        this.repeticiones = repeticiones;
        this.letras = letras;
        this.semaforos = semaforos;
    }

    @Override
    public void run() {
        try {
            while (true) {
                // Esperar turno
                semaforos.get(letra).acquire();
                
                // Imprimir letra
                for (int i = 0; i < repeticiones; i++) {
                    System.out.print(letra);
                    Thread.sleep(500);
                }
                
                // Determinar el próximo hilo a ejecutar
                int index = letras.indexOf(letra);
                int nextIndex = (index + 1) % letras.size();
                semaforos.get(letras.get(nextIndex)).release();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
