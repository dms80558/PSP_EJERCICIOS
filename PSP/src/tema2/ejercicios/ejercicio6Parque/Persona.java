package tema2.ejercicios.ejercicio6Parque;

public class Persona extends Thread {
	private Banco banco;

	private long tpaseo;
	private long tsentado;

	public Persona(Banco banco, String nombre) {
		super(nombre);
		this.banco = banco;

		this.tpaseo = (long) ((Math.random() * 1000000) % 2001 + 100);
		this.tsentado = (long) ((Math.random() * 1000000) % 6001 + 100);

	}

	@Override
	public void run() {
		try {
			System.out.println(getName() + " llega al banco");
			Thread.sleep(tpaseo);
			banco.sentarse();
			System.out.println(getName() + " se ha setado");
			Thread.sleep(tsentado);
			banco.levantarse();
			System.out.println(getName() + " se ha levantado");
		} catch (InterruptedException e) {

		}
	}
}
