import java.util.*;

public class PrincipalAnimales {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Map<String, List<Animal>> clasificacion = new TreeMap<>();
        List<Animal> animales = new ArrayList<>();

        String continuar;
        do {
            System.out.print("Ingrese el nombre del animal: ");
            String nombre = sc.nextLine();

            System.out.print("Ingrese el tipo (terrestre, aéreo, acuático): ");
            String tipo = sc.nextLine().toLowerCase();

            System.out.print("Ingrese el género (masculino, femenino): ");
            String genero = sc.nextLine().toLowerCase();

            Animal animal = new Animal(nombre, tipo, genero);
            animales.add(animal);

            // Clasificamos el animal
            clasificacion.putIfAbsent(tipo, new ArrayList<>());
            clasificacion.get(tipo).add(animal);

            System.out.print("Desea ingresar otro animal? (si/no): ");
            continuar = sc.nextLine().toLowerCase();

        } while (continuar.equals("si"));

        // Mostramos los resultados
        System.out.println("\nClasificación de animales:");
        for (Map.Entry<String, List<Animal>> entry : clasificacion.entrySet()) {
            System.out.println(entry.getKey().substring(0, 1).toUpperCase() + entry.getKey().substring(1) + ":");
            for (Animal a : entry.getValue()) {
                System.out.println("    " + a.getNombre());
            }
        }
    }
}