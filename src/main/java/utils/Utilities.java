public class Utilities {

    /**
     * Converts a string to an integer safely, returning a default value if the conversion fails.
     * 
     * @param input The string input to convert.
     * @param defaultValue The default value to return in case of failure.
     * @return The integer value of the string, or the default value if the string is not a valid integer.
     */
    public static int safeStringToInt(String input, int defaultValue) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    /**
     * Converts a string to a double safely, returning a default value if the conversion fails.
     * 
     * @param input The string input to convert.
     * @param defaultValue The default value to return in case of failure.
     * @return The double value of the string, or the default value if the string is not a valid double.
     */
    public static double safeStringToDouble(String input, double defaultValue) {
        try {
            return Double.parseDouble(input);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    /**
     * Prints a custom formatted log message.
     * 
     * @param message The message to log.
     */
    public static void log(String message) {
        System.out.println("[LOG] " + message);
    }

    /**
     * Formats a string by capitalizing the first letter of each word in the string.
     * 
     * @param input The string to format.
     * @return The formatted string with each word's first letter capitalized.
     */
    public static String capitalizeWords(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }

        StringBuilder result = new StringBuilder(input.length());
        String[] words = input.split("\\s+");
        for (String word : words) {
            if (!word.isEmpty()) {
                result.append(Character.toUpperCase(word.charAt(0))).append(word.substring(1)).append(" ");
            }
        }
        return result.toString().trim();
    }
}
