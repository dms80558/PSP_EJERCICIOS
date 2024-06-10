package tema2.ejercicios.Secuencia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.Semaphore;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el patrón:");
        String input = scanner.nextLine().toUpperCase();
        
        Pattern pattern = Pattern.compile("([A-Z])(\\d+)");
        Matcher matcher = pattern.matcher(input);
        
    
        Map<Character, Integer> secuencia = new LinkedHashMap<>();
        while (matcher.find()) {
            char letra = matcher.group(1).charAt(0);
            int repeticiones = Integer.parseInt(matcher.group(2));
            secuencia.putIfAbsent(letra, 0);
            secuencia.put(letra, secuencia.get(letra) + repeticiones);
        }
        
        if (!matcher.hitEnd()) {
            System.out.println("Error: Patrón incorrecto");
            return;
        }
        
       
        List<Character> letras = new ArrayList<>(secuencia.keySet());
        Map<Character, Semaphore> semaforos = new HashMap<>();
        for (char letra : letras) {
            semaforos.put(letra, new Semaphore(0));
        }
        
        
        semaforos.get(letras.get(0)).release();
        
        
        for (char letra : letras) {
            new EscribeLetras(letra, secuencia.get(letra), letras, semaforos).start();
        }
    }
}
