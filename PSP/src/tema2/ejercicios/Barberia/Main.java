package tema2.ejercicios.Barberia;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Ingrese el número de sillas en la sala de espera: ");
        int numSillas = scanner.nextInt();
        
        System.out.print("Ingrese el número de clientes que acudirán a la barbería: ");
        int numClientes = scanner.nextInt();
        
        Barberia barberia = new Barberia(numSillas);
        
        Thread barbero = new Thread(new Barbero(barberia));
        barbero.start();
        
        for (int i = 0; i < numClientes; i++) {
            new Thread(new Cliente(barberia, i + 1)).start();
            try {
                Thread.sleep((int)(Math.random() * 1000)); // Tiempo aleatorio entre llegadas de clientes
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
}
}
