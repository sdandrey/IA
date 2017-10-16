package javaapplication1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Strams {
    static long contador = 0;

    
    public static int isSubstring(String str1, String str2){       
       return str1.toLowerCase().contains(str2.toLowerCase()) ? 1 : 0;
    }
    
    public static long contarVocales(String h1, String h2){
        h1 = h1.toLowerCase() + "l"; h2 = h2.toLowerCase() + "l";
        return (h2.split("a").length-1)*(h1.split("a").length-1) + (h2.split("e").length-1)*(h1.split("e").length-1) + 
                (h2.split("i").length-1)*(h1.split("i").length-1) + (h2.split("o").length-1)*(h1.split("o").length-1) + 
                (h2.split("u").length-1)*(h1.split("u").length-1); 
    }

    
public static void main(String[] args) {
        // TODO code application logic here
        String fileName = "./words.txt";
        
        //read file into stream, try-with-resources
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) { //convierte el contenido del archivo en un stream

            final List<String> list = stream.collect(Collectors.toList());
            long result1;
            long result2;
            
            List<String> sameCharac = list.parallelStream().filter(h1 -> {
          return list.parallelStream() 
              .anyMatch(h2 -> !h1.equals(h2) && sameCharactersarray(h1.toLowerCase(), h2.toLowerCase())); 
        }).collect(Collectors.toList());
            System.out.println("Ejercicio 1: "  + contador);
            
           result1 = list.parallelStream().map(i -> list.parallelStream().map(j -> isSubstring(i, j)).reduce(Integer::sum).get()).reduce(Integer::sum).get();
            System.out.println("Ejercicio 2: " + result1);
            result2 = list.parallelStream().map(i -> list.parallelStream().map(j -> contarVocales(i, j)).reduce(Long::sum).get()).reduce(Long::sum).get();
            System.out.println("Ejercicio 3: " + result2);
            
               
        }catch(Exception e){
            System.err.println("error " + e.toString());
        }
}




public static boolean sameCharactersarray(String h1, String h2) { 
    ArrayList<Character> l1 = new ArrayList<>(h1.chars()
        .mapToObj(e -> (char) e).collect(Collectors.toList()));
      
    ArrayList<Character> l2 = new ArrayList<>(h2.chars()
        .mapToObj(e -> (char) e).collect(Collectors.toList()));    
    if (l2.containsAll(l1)){
      contador++ ;
    }
    return l2.contains(l1) ; 
  }

}