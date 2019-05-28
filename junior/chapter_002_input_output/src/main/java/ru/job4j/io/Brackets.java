package ru.job4j.io;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Brackets {
    final Map<String, String> supportedBrackets = Map.of(
            "(", ")",
            "{", "}",
            "[", "]",
            "⟨", "⟩",
            "<", ">",
            "⸤", "⸥",
            "｢", "｣",
            "⟦", "⟧",
            "〔", "〕"
    );

    public List<Pair> stats(String str) {
        Deque<Pair> pairs = new LinkedList<>();
        List<Pair> stats = new ArrayList<>();
        boolean valid = false;
        Deque<String> stack = new LinkedList<>();
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (supportedBrackets.containsKey(String.valueOf(chars[i]))) {
                stack.addFirst(String.valueOf(chars[i]));
                pairs.addFirst(new Pair(String.valueOf(chars[i]), i));
            } else if (supportedBrackets.containsValue(String.valueOf(chars[i]))) {
                if (String.valueOf(chars[i]).equals(supportedBrackets.get(stack.pollFirst()))) {
                    stats.add(pairs.pollFirst()
                            .setClosing(String.valueOf(chars[i]))
                            .setClosingPosition(i));
                    valid = true;
                } else {
                    valid = false;
                    break;
                }
            }
        }
        return valid ? stats : null;
    }

    class Pair {
        String opening;
        int openingPosition;
        String closing;
        int closingPosition;

        public Pair(String opening, int openingPosition) {
            this.opening = opening;
            this.openingPosition = openingPosition;
        }

        public Pair setClosing(String closing) {
            this.closing = closing;
            return this;
        }

        public Pair setClosingPosition(int closingPosition) {
            this.closingPosition = closingPosition;
            return this;
        }

        @Override
        public String toString() {
            return "Pair{"
                    + "opening='" + opening + '\''
                    + ", openingPosition=" + openingPosition
                    + ", closing='" + closing + '\''
                    + ", closingPosition=" + closingPosition
                    + '}';
        }
    }
}
