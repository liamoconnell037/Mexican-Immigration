import java.util.Scanner;


public class Main {
    private static int NUM_TURNS = 15;
    private static int points = 0;
    private static int money = 1000;
    private static int knowledge = 0;
    private static boolean arrested = false;
    private static boolean succeed = true;
    private static boolean exam = false;
    private static Question[] questions = {
        new SpanishQuestion("What is the conjugation of ser in yo form?", new String[] {"eres", "so", "soy", "somos"}, 'c'),
        new SpanishQuestion("Como se dice \"Ven aquí\" en inglés?", new String[] {"He comes here", "You come here", "I come here", "They go here"}, 'b'),
        new SpanishQuestion("Traduce al inglés: Amo los estados unidos.", new String[] {"He hates the United States", "You love the United States", "They hate the United States", "I love the United States"}, 'd'),
        new SpanishQuestion("Como se dice “I am going to the mall” en español?", new String[] {"Yo vo al centro comercial.", "Yo va al centro comercial.", "Yo voy al centro comercial.", "Yo soy ir al centro comercial."}, 'c'),
        new SpanishQuestion("Traduce al español: I was eight years old once.", new String[] {"Yo fue ocho años una vez.", "Yo tenía ocho años una vez.", "Una vez, yo soy ocho años.", "Una vez, yo tengo ocho años."}, 'b'),
        new SpanishQuestion("Traduce al español: Do not clean the kitchen. ", new String[] {"No limpias la cocina.", "No limpia la cocina.", "No limpiar la cocina.", "No limpies la cocina."}, 'd'),
        new HistoryQuestion("¿Quién fue el primer rey de España después de la unificación de España?", new String[] {"Ferdinand II de Aragon", "Henry de VIII", "Reina Elizabeth", "Franz Ferdinand"}, 'a'),
        new HistoryQuestion("¿Cuándo se fundó el mariachi Vargas de Tecalitlán?", new String[] {"1931", "1898", "1972", "1812"}, 'b'),
        new HistoryQuestion("En 1519, Hernan Cortes llegó a México. ¿El y sus músicas trajeron qué instrumento?", new String[] {"Guitarra", "Clarinete", "Saxofón"}, 'a'),
        new HistoryQuestion("¿Quién fue Hernan Cortes?", new String[] {"Músico internacional", "Pintor", "Conquistador espanol", "lustrador de zapatos"}, 'c'),
        new HistoryQuestion("¿Que vivieron en México antes de que México fuera conquistado?", new String[] {"Aztecas, mayas, toltecas", "Espanoles", "Americanos, británicos, canadienses", "Indios"}, 'a'),
        new GovernmentQuestion("¿Qué tipo de gobierno es México?", new String[] {"Democracia", "República Federal", "Dictadura"}, 'b'),
        new GovernmentQuestion("¿Quién es el presidente de méxico?", new String[] {"Andrés Manuel López Obrador", "Manual Mirande", "Guillermo del Toro", "Alfonso Cuarón"}, 'a'),
        new GovernmentQuestion("¿Cuáles son las tres sucursales (branches) del gobierno mexicano?", new String[] {"Presidencial, pudiente, policía", "Ejecutiva, legislativa, judicial ", "Federales, personas, leyes"}, 'b'),
        new GovernmentQuestion("¿Cuál es el nombre oficial de México?", new String[] {"México", "Gran México", "Republica de México", "Estados Unidos Mexicanos"}, 'd'),
        new GovernmentQuestion("¿Cuando se hizo la actual constitucion de México?", new String[] {"1824", "1782", "1917", "1853"}, 'c'),
    };

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
            System.out.println("\n\n\n                         TURNO: " + (i+1) + " / " + NUM_TURNS);
            turn(scanner);
            userWait(scanner);
            clearScreen();
            utilityCost();
            userWait(scanner);
            if(i == 9)
                event(scanner);
            if(money < 0) {
                System.out.println("\nYa no tienes dinero...");
                succeed = false;
                userWait(scanner);
            }
            if(!succeed) {
                gameOverScreen(scanner);
                return;
            }
        }
        
        clearScreen();
        System.out.println("\n\t\tTARIFA DE APLICACION DE CIUDADANÍA: -$725");
        money -= 725;
        userWait(scanner);
        if(arrested) {
            System.out.println("\nTienes antecedentes penales. El gobierno no te da el examen.");
            succeed = false;
            userWait(scanner);
            gameOverScreen(scanner);
            return;
        }

        clearScreen();

        System.out.println("\nLos estados unidos te da el examen.");
        userWait(scanner);
        beginExam(scanner);
        if(knowledge >= 7 && exam) {
            System.out.println("\nTú pasaste el examen!");
        } else {
            succeed = false;
            System.out.println("\nTú no pasaste el examen...");
            if(knowledge < 7) {
                System.out.println("\nDeberías haber estudiado más!");
            }
        }
        userWait(scanner);

        gameOverScreen(scanner);

        scanner.close();
    }

    public static void event(Scanner scanner) {
        clearScreen();
        System.out.println("\t\tDe camino a casa, encuentra una billetera en la calle. Tu no piensas que alguien está cerca.");
        System.out.println("\n   1. ROBAR EL DINERO\n" +
        "   2. DEVOLVER LA BILLETERA\n" +
        "   3. HACER NADA\n");

        switch(userInput(scanner, "\n\t Escribe tu opción: ", new int[] {1, 3})) {
            case 1 : {
                clearScreen();
                System.out.println("\tUn policía te pilla en el acto. Te da una multa de 1000 dolares. Tu ganas unas antecedentes penales.");
                arrested = true;
                money -= 1000;
                System.out.println("\n\t\tLA MULTA: -$1000");
                userWait(scanner);
                break;
            }
            case 2 : {
                clearScreen();
                System.out.println("\tTú encuentras al dueño. El es feliz que tu lo devolvió. Él te da 250 dolares.");
                money += 250;
                System.out.println("\n\t\tÉL TE DA: +$250");
                userWait(scanner);
                break;
            }
            case 3 : {
                clearScreen();
                System.out.println("\tTu pasas la billetera.");
                userWait(scanner);
                break;
            }
        }
        
    }

    public static void utilityCost() {
        int cost = (int)(Math.random()*100+265);
        System.out.println("\n\t\tCOSTO DE LA CUESTAS: -$" + cost);
        money -= cost;
    }

    public static void beginExam(Scanner scanner) {
        int numCorrect = 0;
        System.out.println("\n\t\tSECCIÓN INGLÉS");
        for(int i = 0; i < 2;i++) {
            clearScreen();
            if(askQuestion(scanner, Question.QuestionType.Spanish))
                numCorrect++;
            userWait(scanner);
        }
        System.out.println("\n\t\tSECCIÓN HISTORIA");
        for(int i = 0; i < 2;i++) {
            clearScreen();
            if(askQuestion(scanner, Question.QuestionType.History))
                numCorrect++;
                userWait(scanner);
        }
        for(int i = 0; i < 2;i++) {
            clearScreen();
            if(askQuestion(scanner, Question.QuestionType.Government))
                numCorrect++;
            userWait(scanner);
        }
        clearScreen();
        if(numCorrect >= 3) {
            exam = true;
        }
    }

    public static void gameOverScreen(Scanner scanner) {
        if(succeed) {
            clearScreen();
            System.out.println("\n\n\t\t\t¡TIENES CIUDADANIA!");
            System.out.printf("         DINERO: $%d                         PUNTOS: %d", money, points);
        }
        else {
            clearScreen();
            System.out.println("\n\n\t\t\t¡NO TIENES CIUDADANIA!");
            System.out.printf("         DINERO: $%d                         PUNTOS: %d", money, points);
        }
        System.out.println("\n\n");
        System.out.print("\n\t Escribe algo a salir: ");
        scanner.nextLine();
    }
    
    public static void clearScreen() {  
        try {
        	if(System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        	else {
        		System.out.print("\033[H\033[2J");  
            	System.out.flush();  
        	}
        } catch(final Exception e) {
        	e.printStackTrace();
        }
        System.out.println();
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
                           "   2. APRENDE HISTORIA\n" +
                           "   3. APRENDE GOBIERNO\n" +
                           "   4. TRABAJA TU TRABAJO");


        switch(userInput(scanner, "\n\t Escribe tu opción: ", new int[] {1, 4})) {
            case 1 : {
                clearScreen();
                askQuestion(scanner, Question.QuestionType.Spanish);
                break;
            }
            case 2 : {
                clearScreen();
                askQuestion(scanner, Question.QuestionType.History);
                break;
            }
            case 3 : {
                clearScreen();
                askQuestion(scanner, Question.QuestionType.Government);
                break;
            }
            case 4 : {
                workJob();
                break;
            }
        }
    }
    public static void workJob() {
        int num = (int)(Math.random()*750+250);
        System.out.println("\n\t\t\tTÚ GANASTE: $" + num);
        money += num;
        points += num/5;
    }


    public static boolean askQuestion(Scanner scanner, Question.QuestionType type) {
        Question q = getRandomQuestion(type);
        System.out.println("\t" + q.getQuestion() + "\n");
        char letter = 'a';
        for(int i = 0; i < q.getAnswers().length;i++) {
            System.out.println("\t\t" + letter + ") " + q.getAnswers()[i]);
            letter++;
        }
        System.out.print("\n\t Escribe tu opción: ");
        String response = scanner.nextLine();
        if(!(response.length() == 0) && Character.toLowerCase(response.charAt(0)) == q.getRightAnswer()) {
            knowledge++;
            points += 150;
            System.out.println("\n\t\t¡Estás correcto!");
            return true;
        }
        else {
            System.out.println("\n\t\tNo estás correcto...");
            return false;
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


    public static Question getRandomQuestion(Question.QuestionType type) {
        while(true) {
            Question q = questions[(int)(Math.random()*questions.length)];
            if(q.getType() == type) 
                return q;
        }
    }
}