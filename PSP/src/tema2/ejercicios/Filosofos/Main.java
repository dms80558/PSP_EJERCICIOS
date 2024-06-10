package tema2.ejercicios.Filosofos;

import java.util.concurrent.Semaphore;

public class Main {
	public static void main(String[] args) {
        String[] nombres = {"孔夫子", "楊朱", "荀子", "商鞅", "莊子"};
        int numFilosofos = nombres.length;


        Semaphore[] palillos = new Semaphore[numFilosofos];
        for (int i = 0; i < numFilosofos; i++) {
            palillos[i] = new Semaphore(1);
        }

       
        Filosofo[] filosofos = new Filosofo[numFilosofos];
        for (int i = 0; i < numFilosofos; i++) {
            filosofos[i] = new Filosofo(nombres[i], i, palillos);
            new Thread(filosofos[i]).start();
        }
    }
}
