import java.util.ArrayList;
import java.util.Arrays;

public class AlterTask2 {

    static String[] operations = new String[]{"+", "-", "*", "/", "Concat"};

    public static void main(String[] args) {
        int[] seq = new int[]{9, 8, 7, 6, 5, 4, 3, 2};
        StringBuilder line = new StringBuilder().append(seq[0]);
        solve(seq, 1, line);
    }

    public static void solve(int[] seq, int pos, StringBuilder line) {
        if (pos >= 8) {
            String answer = getAnswer(line.toString());
            if(answer.equals("100"))
                System.out.println(line+" = 100");
        } else {
            for (String e : operations) {
                if (e.equals("Concat")) {
                    solve(seq, pos + 1, new StringBuilder(line.toString()).append(seq[pos]));
                } else {
                    solve(seq, pos + 1, new StringBuilder(line.toString()).append(" " + e + " " + seq[pos]));
                }
            }
        }
    }

    static String getAnswer(String line) {
        ArrayList<String> list = new ArrayList<>(Arrays.asList(line.split(" ")));
        findAndSolveOperators(list,new String[]{"*","/"});
        findAndSolveOperators(list,new String[]{"+","-"});
        return list.get(0);
    }

    static void findAndSolveOperators(ArrayList<String> list,String[] op) {
        for (int i = 0; i < list.size(); i++) {
            String element = list.get(i);
            if (Arrays.asList(op).contains(element)){
                calculate(list, i, element);
                i--;
            }
        }
    }

    static void calculate(ArrayList<String> list, int i, String operation) {
        int a = Integer.parseInt(list.get(i - 1));
        int b = Integer.parseInt(list.get(i + 1));
        list.remove(i - 1);
        switch (operation) {
            case ("+"):
                list.set(i - 1, Integer.toString(a + b));
                break;
            case ("-"):
                list.set(i - 1, Integer.toString(a - b));
                break;
            case ("*"):
                list.set(i - 1, Integer.toString(a * b));
                break;
            default:
                list.set(i - 1, Integer.toString(a / b));
        }
        list.remove(i);
    }

}