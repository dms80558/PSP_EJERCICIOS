package tema2.ejercicios.ejercicio3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Contador implements Runnable {

	private File file;

	public Contador(File file) {
		this.file = file;
	}

	@Override
	public void run() {
		int lineas = 0, palabras = 0, caracteres = 0;
		Pattern pattern = Pattern.compile("\\p{L}");
		try (BufferedReader in = new BufferedReader(new FileReader(file))) {
			String linea; 
			while((linea = in.readLine())!=null){
				lineas++;
				caracteres = linea.split(" ").length;
				Matcher m = pattern.matcher(linea);
				while(m.find()) {
					palabras++;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		System.out.println(file.getName() + " --- lineas: " + lineas + ",caracteres: " + caracteres + ",palabras: " + palabras);
		
	}

}
