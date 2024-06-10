package tema2.ejercicios.ejercicio6Parque;

public class Banco {

	private int plazaslibres;

	public Banco(int plazas) {
		plazaslibres = plazas;
	}

	public synchronized void sentarse() {

		while (plazaslibres == 0) {
			try {
				System.out.println(Thread.currentThread().getName() + " espera");
				wait();

			} catch (InterruptedException e) {
			}

			plazaslibres--;

		}
	}

	public synchronized void levantarse() {
		plazaslibres++;
		notify();
	}

}
