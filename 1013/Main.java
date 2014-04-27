import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int caseCount = in.nextInt();

        for (int i = 0; i < caseCount; i++) {
            String adam = in.next();
            String eve = in.next();

            if (adam.length() > eve.length()) {
                String temp = adam;
                adam = eve;
                eve = temp;
            }

            List<Pair<Integer, String>> shortestCommonSequenceList = findShortestCommonSequenceList(adam, eve);
            String answer = shortestCommonSequenceList.get(0).snd.length() + " " + shortestCommonSequenceList.size();

            System.out.println("Case " + (i + 1) + ": " + answer);
        }
    }

    static List<Pair<Integer, String>> findShortestCommonSequenceList(String adam, String eve) {
        // System.out.println("Process \"" + adam + "\" and \"" + eve + "\"");

        List<Pair<Integer, String>> commonSequenceList = new ArrayList<Pair<Integer, String>>(adam.length() + eve.length());
        if (adam.isEmpty()) {
            commonSequenceList.add(new Pair<Integer, String>(eve.length(), eve));
        } else {
            char firstChar = adam.charAt(0);
            String subAdam = adam.substring(1);
            int shortestLength = Integer.MAX_VALUE;
            StringBuilder stringBuilder = new StringBuilder();

            List<Pair<Integer, String>> subShortestCommonSequenceList = findShortestCommonSequenceList(subAdam, eve);
            for (Pair<Integer, String> pair : subShortestCommonSequenceList) {
                for (int i = 0; i <= pair.fst; i++) {
                    stringBuilder.setLength(0);
                    stringBuilder.append(pair.snd);

                    if (i != stringBuilder.length() && stringBuilder.charAt(i) == firstChar) {
                        // Do nothing, right?
                    } else {
                        stringBuilder.insert(i, firstChar);
                    }

                    if (stringBuilder.length() <= shortestLength) {
                        shortestLength = stringBuilder.length();
                        commonSequenceList.add(new Pair<Integer, String>(i, stringBuilder.toString()));
                    }

                    // System.out.println("Upshot: <" + i + ", \"" + stringBuilder.toString() + "\">");
                }

                filter(commonSequenceList, shortestLength);
            }
        }

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