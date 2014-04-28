import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    static final StringBuilder STRING_BUILDER = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int caseCount = Integer.parseInt(bufferedReader.readLine());

        for (int i = 0; i < caseCount; i++) {
            String adam = bufferedReader.readLine();
            String eve = bufferedReader.readLine();

            // long begin = System.currentTimeMillis();

            // Hard-coded values for testing
            // adam = "QWERTYUIOPC";
            // eve = "ASDFGHJKLZX";

            if (adam.length() > eve.length()) {
                String temp = adam;
                adam = eve;
                eve = temp;
            }

            List<Pair<Integer, String>> shortestCommonSequenceList = findShortestCommonSequenceList(adam, eve);

            STRING_BUILDER.setLength(0);
            STRING_BUILDER
                    .append("Case ")
                    .append(i + 1)
                    .append(": ")
                    .append(shortestCommonSequenceList.get(0).snd.length())
                    .append(' ')
                    .append(shortestCommonSequenceList.size());
            System.out.println(STRING_BUILDER.toString());

            // long time = System.currentTimeMillis() - begin;
            // System.out.println("Time: " + (time / 1000.0f) + "s");
        }
    }

    static String getAnswer(List<Pair<Integer, String>> shortestCommonSequenceList) {
        return shortestCommonSequenceList.get(0).snd.length() + " " + shortestCommonSequenceList.size();
    }

    static List<Pair<Integer, String>> findShortestCommonSequenceList(String adam, String eve) {
        // System.out.println("Process \"" + adam + "\" and \"" + eve + "\"");

        int capacity = adam.length() + eve.length();
        List<Pair<Integer, String>> commonSequenceList = new ArrayList<Pair<Integer, String>>(capacity);
        if (adam.isEmpty()) {
            commonSequenceList.add(new Pair<Integer, String>(eve.length(), eve));
        } else {
            char firstChar = adam.charAt(0);
            String subAdam = adam.substring(1);
            int shortestLength = Integer.MAX_VALUE;

            List<Pair<Integer, String>> subShortestCommonSequenceList = findShortestCommonSequenceList(subAdam, eve);
            for (Pair<Integer, String> pair : subShortestCommonSequenceList) {
                for (int i = 0; i <= pair.fst; i++) {
                    STRING_BUILDER.setLength(0);
                    STRING_BUILDER.append(pair.snd);

                    if (i != STRING_BUILDER.length() && STRING_BUILDER.charAt(i) == firstChar) {
                        // Do nothing, right?
                    } else {
                        STRING_BUILDER.insert(i, firstChar);
                    }

                    if (STRING_BUILDER.length() <= shortestLength) {
                        shortestLength = STRING_BUILDER.length();
                        commonSequenceList.add(new Pair<Integer, String>(i, STRING_BUILDER.toString()));
                    }
                }
            }

            filter(commonSequenceList, shortestLength);
        }

        // System.out.println("Done with \"" + adam + "\" and \"" + eve + "\" => " + getAnswer(commonSequenceList));
        return commonSequenceList;
    }

    static void filter(List<Pair<Integer, String>> commonSequenceList, int shortestLength) {
        Map<String, Object> iteratedElements = new HashMap<String, Object>(commonSequenceList.size());
        for (int i = 0; i < commonSequenceList.size(); ) {
            String commonSequenceString = commonSequenceList.get(i).snd;
            boolean hasPresence = iteratedElements.containsKey(commonSequenceString);
            if (hasPresence || commonSequenceString.length() != shortestLength) {
                commonSequenceList.remove(i);
            } else {
                iteratedElements.put(commonSequenceString, null);
                i++;
            }
        }
    }

    static class Pair<F, S> {

        public final F fst;
        public final S snd;

        Pair(F fst, S snd) {
            this.fst = fst;
            this.snd = snd;
        }
    }
}