package tema2.ejercicios.Piscina;

import java.util.concurrent.Semaphore;

class Piscina {
    private final Semaphore calles;
    private int hombres = 0;
    private int mujeres = 0;
    private int niños = 0;
    private int niñas = 0;
    private int submarinistas = 0;

    public Piscina(int numCalles) {
        this.calles = new Semaphore(numCalles, true);
    }

    public void entrarNadador(String nombre, String genero) throws InterruptedException {
        calles.acquire();
        synchronized (this) {
            switch (genero) {
                case "Hombre" -> hombres++;
                case "Mujer" -> mujeres++;
                case "Niño" -> niños++;
                case "Niña" -> niñas++;
            }
            mostrarEstado(nombre + " (Nadador, " + genero + ") entra");
        }
    }

    public void salirNadador(String nombre, String genero) {
        synchronized (this) {
            switch (genero) {
                case "Hombre" -> hombres--;
                case "Mujer" -> mujeres--;
                case "Niño" -> niños--;
                case "Niña" -> niñas--;
            }
            mostrarEstado(nombre + " (Nadador, " + genero + ") sale");
        }
        calles.release();
    }

    public void entrarSubmarinista(String nombre) throws InterruptedException {
        calles.acquire(2);
        synchronized (this) {
            submarinistas++;
            mostrarEstado(nombre + " (Submarinista) entra");
        }
    }

    public void salirSubmarinista(String nombre) {
        synchronized (this) {
            submarinistas--;
            mostrarEstado(nombre + " (Submarinista) sale");
        }
        calles.release(2);
    }

    private synchronized void mostrarEstado(String mensaje) {
        System.out.println(mensaje);
        System.out.println("Estado de la piscina:");
        System.out.println("Hombres: " + hombres + ", Mujeres: " + mujeres + ", Niños: " + niños + ", Niñas: " + niñas + ", Submarinistas: " + submarinistas);
        System.out.println();
    }
}