package tema2.ejercicios.Robots;

class RobotEmpaquetador implements Runnable {
    private final CadenaMontaje cadenaMontaje;
    private final int tipoProducto;

    public RobotEmpaquetador(CadenaMontaje cadenaMontaje, int tipoProducto) {
        this.cadenaMontaje = cadenaMontaje;
        this.tipoProducto = tipoProducto;
    }

    @Override
    public void run() {
        try {
            while (true) {
                cadenaMontaje.retirarProducto(tipoProducto);
                Thread.sleep(150); 
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
