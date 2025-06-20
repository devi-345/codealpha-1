import java.util.*;

public class ChatBot {

    private static final Map<String, List<String>> RESPONSES = new HashMap<>();
    private static final Map<String, List<String>> INTENTS = new HashMap<>();

    public static void main(String[] args) {
        initBot();

        System.out.println("ChatBot: Hello! How can I help you today?");

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.print("You: ");
                String input = scanner.nextLine();

                if (shouldExit(input)) {
                    System.out.println("ChatBot: Goodbye! Have a nice day.");
                    break;
                }

                String intent = getIntent(input.toLowerCase());
                String response = getRandomResponse(intent);
                System.out.println("ChatBot: " + response);
            }
        }
    }

    private static void initBot() {
        // Define patterns for each intent
        INTENTS.put("greeting", Arrays.asList("hi", "hello", "hey", "good morning", "good evening"));
        INTENTS.put("goodbye", Arrays.asList("bye", "goodbye", "see you", "farewell"));
        INTENTS.put("thanks", Arrays.asList("thanks", "thank you", "thx", "appreciate it"));
        INTENTS.put("name", Arrays.asList("your name", "who are you", "what are you"));

        // Define responses for each intent
        RESPONSES.put("greeting", Arrays.asList("Hello!", "Hi there!", "Hey! How can I help you?"));
        RESPONSES.put("goodbye", Arrays.asList("Goodbye!", "See you later!", "Take care!"));
        RESPONSES.put("thanks", Arrays.asList("You're welcome!", "Anytime!", "No problem!"));
        RESPONSES.put("name", Arrays.asList("I'm an AI chatbot created in Java.", "They call me JavaBot!"));
        RESPONSES.put("unknown", Arrays.asList("I'm not sure I understand.", "Can you rephrase that?", "Sorry, I don't get it."));
    }

    private static boolean shouldExit(String input) {
        if (input == null) return false;
        String lowerInput = input.toLowerCase();
        return lowerInput.equals("exit") || lowerInput.equals("bye");
    }

    private static String getIntent(String input) {
        if (input == null || input.trim().isEmpty()) {
            return "unknown";
        }

        for (Map.Entry<String, List<String>> entry : INTENTS.entrySet()) {
            for (String pattern : entry.getValue()) {
                if (input.contains(pattern)) {
                    return entry.getKey();
                }
            }
        }
        return "unknown";
    }

    private static String getRandomResponse(String intent) {
        List<String> possibleResponses = RESPONSES.getOrDefault(intent, RESPONSES.get("unknown"));
        Random random = new Random();
        return possibleResponses.get(random.nextInt(possibleResponses.size()));
    }
}