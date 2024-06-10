package tema2.ejercicios.ejercicio3;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		if (args.length != 2 || !(args[1].equalsIgnoreCase("c") ||
				args[1].equalsIgnoreCase("p")))  {
			System.out.println("Parametros incorrectos");
			System.exit(-1);
		}
		String forma = args[1];
		File[] files = new File(args[0]).listFiles();
		List<File> listaFiles = new ArrayList<>();
		for (File f : files) {
			if (f.isFile())
				listaFiles.add(f);
		}
		long t0 = System.nanoTime();
		if(args[1].toLowerCase().equals("p")) {
			LinkedList<Thread> listaHilos = new LinkedList<>();
			listaFiles.forEach(f-> {
				listaHilos.add(new Thread(new Contador(f)));
			});
			listaHilos.forEach(h->{
				h.start();
			});
			listaHilos.forEach(h->{
				try {
					h.join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
		}
		else {
			listaFiles.forEach(f-> {
				new Contador(f).run();
			});
		}
		System.out.println(System.nanoTime()-t0);
		
	}

}
