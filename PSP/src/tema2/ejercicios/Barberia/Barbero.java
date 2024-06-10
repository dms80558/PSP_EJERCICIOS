package tema2.ejercicios.Barberia;

class Barbero implements Runnable {
    private final Barberia barberia;

    public Barbero(Barberia barberia) {
        this.barberia = barberia;
    }

    @Override
    public void run() {
        try {
            while (true) {
                System.out.println("El barbero está durmiendo.");
                barberia.esperarCliente();
                Cliente cliente = barberia.siguienteCliente();
                if (cliente != null) {
                    System.out.println("El barbero comienza a cortar el pelo al cliente " + cliente.getId());
                    Thread.sleep(1000); // Simular el tiempo de cortar el pelo
                    System.out.println("El barbero ha terminado de cortar el pelo al cliente " + cliente.getId());
                    barberia.atenderCliente();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
