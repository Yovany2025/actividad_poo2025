import java.util.*;
import java.util.stream.Collectors;

public class PrincipalPersonas {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<Persona> personas = new ArrayList<>();

        String continuar;
        do {
            System.out.print("Ingrese el nombre: ");
            String nombre = sc.nextLine();

            System.out.print("Ingrese el apellido: ");
            String apellido = sc.nextLine();

            System.out.print("Ingrese la edad: ");
            int edad = Integer.parseInt(sc.nextLine());

            System.out.print("Ingrese el género (M/F): ");
            String genero = sc.nextLine().toUpperCase();

            System.out.print("Ingrese el sueldo por hora: ");
            double sueldoHora = Double.parseDouble(sc.nextLine());

            System.out.print("Ingrese el cargo: ");
            String cargo = sc.nextLine();

            Persona persona = new Persona(nombre, apellido, edad, genero, sueldoHora, cargo);
            personas.add(persona);

            System.out.print("Desea ingresar otra persona? (si/no): ");
            continuar = sc.nextLine().toLowerCase();

        } while (continuar.equals("si"));

        // a. Cantidad de personas
        System.out.println("\nCantidad de personas: " + personas.size());

        // b. Promedio de edades
        double promedioEdad = personas.stream()
                .mapToInt(Persona::getEdad)
                .average()
                .orElse(0);
        System.out.println("Promedio de edad: " + promedioEdad);

        // c. Cantidad de mayores de edad
        long mayoresEdad = personas.stream()
                .filter(p -> p.getEdad() >= 18)
                .count();
        System.out.println("Cantidad de personas mayores de edad: " + mayoresEdad);

        // d. Personas cuyos nombres empiezan con "A"
        System.out.println("Personas cuyos nombres empiezan con 'A':");
        personas.stream()
                .filter(p -> p.getNombre().startsWith("A"))
                .forEach(p -> System.out.println(p.getNombre() + " " + p.getApellido()));

        // e. Personas cuyos apellidos contienen "M"
        System.out.println("Personas cuyos apellidos contienen 'M':");
        personas.stream()
                .filter(p -> p.getApellido().toUpperCase().contains("M"))
                .forEach(p -> System.out.println(p.getNombre() + " " + p.getApellido()));
        System.out.println("\nSueldo por 8 horas de Directores masculinos:");
        personas.stream()
                .filter(p -> p.getCargo().equalsIgnoreCase("director") && p.getGenero().equalsIgnoreCase("M"))
                .peek(p -> System.out.println("Nombre: " + p.getNombre() + " " + p.getApellido() +
                        " Sueldo por 8 horas: $" + (p.getSueldoHora() * 8)))
                .forEach(p -> {}); // forEach vacío porque peek ya muestra

// b. Primera mujer desarrolladora
        System.out.println("\nPrimera mujer desarrolladora:");
        personas.stream()
                .filter(p -> p.getCargo().equalsIgnoreCase("desarrollador") && p.getGenero().equalsIgnoreCase("F"))
                .findFirst()
                .ifPresent(p -> System.out.println(p.getNombre() + " " + p.getApellido()));

// c. Desarrollador que más gana por hora
        System.out.println("\nDesarrollador que más gana por hora:");
        Optional<Persona> masGana = personas.stream()
                .filter(p -> p.getCargo().equalsIgnoreCase("desarrollador"))
                .max(Comparator.comparingDouble(Persona::getSueldoHora));

        if (masGana.isPresent()) {
            Persona p = masGana.get();
            System.out.println(p.getNombre() + " " + p.getApellido() + " - Sueldo/hora: $" + p.getSueldoHora());
        } else {
            System.out.println("No hay desarrolladores.");
        }

// d. Mujeres ordenadas por nombre
        System.out.println("\nMujeres ordenadas por nombre:");
        personas.stream()
                .filter(p -> p.getGenero().equalsIgnoreCase("F"))
                .sorted(Comparator.comparing(Persona::getNombre))
                .forEach(p -> System.out.println(p.getNombre() + " " + p.getApellido()));
    }
}
