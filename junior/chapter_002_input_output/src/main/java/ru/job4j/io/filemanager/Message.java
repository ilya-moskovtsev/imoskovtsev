package ru.job4j.io.filemanager;

public interface Message {
    Type type();

    enum Type {
        LS,
        UP,
        CD,
        DOWNLOAD,
        UPLOAD;

        public static boolean contains(String type) {
            boolean result = false;
            for (Type t : Message.Type.values()) {
                if (t.name().equalsIgnoreCase(type)) {
                    result = true;
                    break;
                }
            }
            return result;
        }
    }
}
