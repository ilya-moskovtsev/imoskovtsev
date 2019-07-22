package ru.job4j.search;

public class Args {
    private String directory;
    private String text;

    public Args(String[] args) {
        if (args.length == 0) {
            error();
        }
        for (int i = 0; i < args.length; i += 2) {
            switch (args[i]) {
                case "-d":
                    directory = args[i + 1];
                    break;
                case "-t":
                    text = args[i + 1];
                    break;
                default:
                    error();
            }
        }
    }

    public String getDirectory() {
        return directory;
    }

    public String getText() {
        return text;
    }

    private void error() {
        throw new IllegalStateException("Please use -d for start directory and -t for text to search");
    }
}
