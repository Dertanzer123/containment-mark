import core.SystemRoot;

import java.io.IOException;

public class Main {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\033[0;93m";
    public static final String ANSI_BLUE = "\033[0;94m";
    public static final String ANSI_RED = "\033[0;91m";

    public static void main(String[] args) throws IOException {
        System.out.println(ANSI_YELLOW + "Hello, World!" + ANSI_RESET);
        System.out.println(ANSI_BLUE + "Hello, World!" + ANSI_RESET);
        System.out.println(ANSI_RED + "Hello, World!" + ANSI_RESET);

        SystemRoot systemRoot = new SystemRoot();
    }
}
