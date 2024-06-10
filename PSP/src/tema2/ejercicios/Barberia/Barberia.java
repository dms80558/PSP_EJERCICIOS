package tema2.ejercicios.Barberia;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class Barberia {

	private final int numSillas;
	private final Queue<Cliente> salaEspera;
	private final Semaphore sillasDisponibles;
	private final Semaphore barberoDisponible;
	private final Semaphore clienteListo;
	
    public Barberia(int numSillas) {
        this.numSillas = numSillas;
        this.salaEspera = new LinkedList<>();
        this.sillasDisponibles = new Semaphore(numSillas, true);
        this.barberoDisponible = new Semaphore(0, true);
        this.clienteListo = new Semaphore(0, true);
    }

    public boolean entrarBarberia(Cliente cliente) throws InterruptedException {
        synchronized (salaEspera) {
            if (sillasDisponibles.tryAcquire()) {
                salaEspera.add(cliente);
                System.out.println("Cliente " + cliente.getId() + " entra en la sala de espera.");
                clienteListo.release();
                return true;
            } else {
                System.out.println("Cliente " + cliente.getId() + " se va sin ser atendido, sala de espera llena.");
                return false;
            }
        }
    }

    public Cliente siguienteCliente() throws InterruptedException {
        clienteListo.acquire();
        synchronized (salaEspera) {
            Cliente cliente = salaEspera.poll();
            sillasDisponibles.release();
            return cliente;
        }
    }

    public void atenderCliente() throws InterruptedException {
        barberoDisponible.release();
    }

    public void esperarCliente() throws InterruptedException {
        barberoDisponible.acquire();
    }
}


