package tema2.ejercicios.Barberia;

class Cliente implements Runnable {
    private final Barberia barberia;
    private final int id;

    public Cliente(Barberia barberia, int id) {
        this.barberia = barberia;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public void run() {
        try {
            if (barberia.entrarBarberia(this)) {
                barberia.esperarCliente();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

