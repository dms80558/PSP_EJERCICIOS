package tema2.ejercicios.ejercicio6Parque;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Parque {

    public static void main(String[] args) throws Exception {
      

      
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Numero de plazas: ");
        int numPlazas = Integer.parseInt(r.readLine());
        System.out.println("Numero de personas: ");
        int numPerso = Integer.parseInt(r.readLine());

        Banco b = new Banco(numPlazas);
        Persona[] p = new Persona[numPerso];

        for (int i = 0; i < p.length; i++) {
            p[i] = new Persona(b, " PERSONA " + i);
        }
        for (int i = 0; i < p.length; i++) {
            p[i].start();
        }
        for (int i = 0; i < p.length; i++) {
            p[i].join();
        }

        System.out.println("FIN");

    }

}

