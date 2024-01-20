import java.util.*;

import codeanalysis.*;

// 1:21:28
class REPL {
    static void printColorText(String color, String text) {
        System.out.println(Colors.ANSI_RED + text + Colors.ANSI_RESET);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean showTree=false;
        while (true) {
            System.out.print(">");
            String str = sc.nextLine();
            if (str.isEmpty())
                break;
            else if(str.equals("#showTree")){
                showTree=!showTree;
                printColorText(Colors.ANSI_GREEN,showTree?"Enabled Tree Printing":"Disabled Tree Priniting");
                continue;
            }else if(str.equals("#cls")){
                System.out.print("\033\143");
                continue;
            }
            var syntaxTree = SyntaxTree.parse(str);
            if(showTree) PrettyPrint.pprint(null, str, showTree);
    
            if (!syntaxTree.get_diagnostics().isEmpty()) {
                for (String error : syntaxTree.get_diagnostics()) {
                    printColorText(Colors.ANSI_RED, error);
                }
            } else {
                Evaluator e = new Evaluator(syntaxTree.get_root());
                var res = e.evaluate();
                printColorText(Colors.ANSI_GREEN, "Result : " + res);
            }
        }
        sc.close();       
    }

    

}


