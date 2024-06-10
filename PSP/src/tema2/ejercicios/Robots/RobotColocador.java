package tema2.ejercicios.Robots;

class RobotColocador implements Runnable {
    private final CadenaMontaje cadenaMontaje;

    public RobotColocador(CadenaMontaje cadenaMontaje) {
        this.cadenaMontaje = cadenaMontaje;
    }

    @Override
    public void run() {
        try {
            while (true) {
                int producto = (int) (Math.random() * 3) + 1;
                cadenaMontaje.colocarProducto(producto);
                Thread.sleep(100); 
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
