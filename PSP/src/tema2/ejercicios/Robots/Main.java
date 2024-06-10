package tema2.ejercicios.Robots;

public class Main {
    public static void main(String[] args) {
        final int N = 10; // Capacidad de la cadena de montaje
        CadenaMontaje cadenaMontaje = new CadenaMontaje(N);

        Thread colocador = new Thread(new RobotColocador(cadenaMontaje));
        Thread empaquetador1 = new Thread(new RobotEmpaquetador(cadenaMontaje, 1));
        Thread empaquetador2 = new Thread(new RobotEmpaquetador(cadenaMontaje, 2));
        Thread empaquetador3 = new Thread(new RobotEmpaquetador(cadenaMontaje, 3));

        colocador.start();
        empaquetador1.start();
        empaquetador2.start();
        empaquetador3.start();
    }
}
