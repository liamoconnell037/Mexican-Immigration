import java.util.Scanner;
public class Main {
    private static int NUM_TURNS = 15;
    private static int points = 0;
    private static int money = 1000;
    private static int knowledge = 0;
    private static boolean arrested = false;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        clearScreen();
        System.out.println("¡Felicidades, tú cruzaste la bordería de los Estados Unidos! Ahora, necesita ganar ciudadanía \n" +
                           "para conseguir todos los beneficios de vivir en los Estados Unidos. Este requiere tu a \n" +
                           "vivir en los Estados Unidos durante cinco años, tener un trabajo no tener antecedentes penales,\n" +
                           "hablar inglés, y entender las historias y gobierno de los Estados Unidos.");
        userWait(scanner);
        clearScreen();
        System.out.println("                            INSTRUCCIONES\n" +
                           "---------------------------------------------------------------------------\n"+
                           "- Todos los turnos, selecciona un opción desde el menú.\n" +
                           "- Completa la tarea para ganar puntos.\n" +
                           "- A veces, un evento sucederá. Juega el evento para ganar puntos.\n" +
                           "- No consigues antecedentes penales (criminal record).\n" +
                           "- Todos los turnos, el dinero de ti disminuirá porque de impuestos y otros honorarios.\n" +
                           "- Trabaja en tu trabajo para ganar dinero.\n" +
                           "- Aprende acerca de los Estados Unidos para pasar el examen de ciudadanía. \n" +
                           "- Al final de cinco años, toca el examen de ciudadanía.\n\n" +
                           "¡Gana ciudadanía y tenga divertida!");
        userWait(scanner);
        for(int i = 0; i < NUM_TURNS;i++) {
            clearScreen();
            displayResources();
            System.out.println("\n\n\n                            TURNO: " + (i+1));
            turn(scanner);
            userWait(scanner);
        }
        scanner.close();
    }
    
    public static void clearScreen() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }  
    
    public static void userWait(Scanner scanner) {
        System.out.print("\n\t Escribe algo a continuar: ");
        scanner.nextLine();
    }
    
    public static void displayResources() {
        System.out.printf("                            RECURSOS\n" +
                          "         DINERO: $%d                         PUNTOS: %d", money, points);
    }

    public static void turn(Scanner scanner) {
        System.out.println("\n   1. APRENDE INGLÉS\n" +
                           "   2. APRENDE HISTORIA DE ESTADOS UNIDOS\n" +
                           "   3. APRENDE GOBIERNO\n" +
                           "   4. TRABAJA TU TRABAJO");
        switch(userInput(scanner, "\n\t Escribe tu opción: ", new int[] {1, 4})) {
            case 1 : {
                
                break;
            }
        }
    }

    public static int userInput(Scanner scanner, String question, int[] range) { // range {least, greatest} (len 2)
        while(true) {
            System.out.print(question);
            String response = scanner.nextLine();
            int num = -1;
            try {
                num = Integer.parseInt(response);
                if(num > range[1] || num < range[0]) {
                    System.out.println("\nPor favor escribe un numero [" + range[0] + ", " + range[1] + "].");
                    continue;
                }
                return num;
            } catch(NumberFormatException e) {
                System.out.println("\nPor favor escribe un número válido [" + range[0] + ", " + range[1] + "].");
            }
        }
    }

    public static int userInput(Scanner scanner, String question) {
        while(true) {
            System.out.print(question);
            String response = scanner.nextLine();
            try {
                return Integer.parseInt(response);
            } catch(NumberFormatException e) {
                System.out.println("\nPlease enter a valid integer.");
            }
        }
    }

}