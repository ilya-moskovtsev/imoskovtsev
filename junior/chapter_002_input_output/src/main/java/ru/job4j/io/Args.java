package ru.job4j.io;

public class Args {
    private String directory;
    private String exclude;
    private String output;

    public Args(String[] args) {
        for (int i = 0; i < args.length; i += 2) {
            switch (args[i]) {
                case "-d":
                    directory = args[i + 1];
                    break;
                case "-o":
                    output = args[i + 1];
                    break;
                case "-e":
                    exclude = args[i + 1];
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + args[i]);
            }
        }
    }

    public String directory() {
        return directory;
    }

    public String exclude() {
        return exclude;
    }

    public String output() {
        return output;
    }
}
