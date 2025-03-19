import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Optimizer {
    private final String input;

    public Optimizer(String input) {
        this.input = input;
    }

    /**
     * 1.delete ' ' and '\t';
     * 2.merge repeated '+' and '-' to one operator;
     * 3.delete '+' before exponent;
     * 4.add '0' in front if necessary
     * @param input original String
     * @return String after preprocess
     */
    public String preProcess(String input) {
        // delete space and tab
        String delSpaceTab = input.replaceAll("[ \t]", "");
        // merge repeated add and sub
        Matcher matcher = Pattern.compile("[\\+|-]{2,}").matcher(delSpaceTab);
        while (matcher.find()) {
            String repeatAddSub = matcher.group(0);
            int count = 0;
            for (char c : repeatAddSub.toCharArray()) {
                if (c == '-') {
                    count++;
                }
            }
            if (count % 2 == 1) {
                delSpaceTab = delSpaceTab.replace(repeatAddSub, "-");
            } else {
                delSpaceTab = delSpaceTab.replace(repeatAddSub, "+");
            }
            matcher = Pattern.compile("[+-]{2,}").matcher(delSpaceTab);
        }
        // delete '+' before exp
        String handleAddSub = delSpaceTab.replace("^+", "^");
        // add '0' if the head is '+' or '-'
        String addZero = handleAddSub.replace("(-", "(0-").replace("(+","(0+");
        if (addZero.charAt(0) == '+' || addZero.charAt(0) == '-') {
            addZero = '0' + addZero;
        }
        // cope "*-" or "*+"
        while (addZero.contains("*+") || addZero.contains("*-")) {
            int pos = addZero.indexOf("*+");
            if (pos == -1) {
                pos = addZero.indexOf("*-");
            }
            StringBuilder sb = new StringBuilder(addZero);
            sb.insert(pos + 1, "(0");
            int paraStack = 0;
            pos += 4;
            while (sb.length() > pos &&
                (paraStack != 0 ||
                    (sb.charAt(pos) != '*' && sb.charAt(pos) != '+' && sb.charAt(pos) != '-'))) {
                if (sb.charAt(pos) == '(') {
                    paraStack++;
                } else if (sb.charAt(pos) == ')') {
                    if (paraStack == 0) {
                        pos++;
                        break;
                    } else {
                        paraStack--;
                    }
                }
                pos++;
            }
            sb.insert(pos, ')');
            addZero = sb.toString();
        }
        return addZero;
    }

    public String optimize(String input) {
        String output = input;
        for (int pos = 0; pos < output.length(); pos++) {
            if (output.charAt(pos) == '0') {
                if (pos == 0) {
                    output = output.substring(1);
                } else if (!Character.isDigit(output.charAt(pos - 1))) {
                    output = output.substring(0, pos) + output.substring(pos + 1);
                }
            }
        }
        if (output.isEmpty()) {
            return "0";
        }
        if (output.charAt(0) == '-' && output.contains("+")) {
            int firstEnd = 0;
            int targetBegin = 0;
            int targetEnd = output.length();
            int signCnt = 0;
            for (int i = 0; i < output.length(); i++) {
                if (output.charAt(i) == '-' || output.charAt(i) == '+') {
                    signCnt++;
                    if (signCnt == 2) {
                        firstEnd = i;
                    }
                    if (targetBegin != 0) {
                        targetEnd = i;
                        break;
                    }
                    if (output.charAt(i) == '+') {
                        targetBegin = i;
                    }
                }
            }
            StringBuilder sb = new StringBuilder();
            sb.append(output.substring(targetBegin + 1, targetEnd));
            sb.append(output.substring(0, firstEnd));
            sb.append(output.substring(firstEnd, targetBegin));
            sb.append(output.substring(targetEnd));
            output = sb.toString();
        }
        if (output.charAt(0) == '+') {
            output = output.replaceFirst("[+]", "");
        }
        return (output.isEmpty() ? "0" : output);
    }
}
