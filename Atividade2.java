import javax.swing.*;
import java.util.*;

public class Atividade2 {
    public static void main(String[] args) {
        String input = JOptionPane.showInputDialog("Digite a expressão (com espaços entre os elementos):");
        String tipoNotacao = JOptionPane.showInputDialog("Informe o tipo de notação (infixa, pos-fixa, pre-fixa):").toLowerCase();

        String resultado;
        try {
            if (tipoNotacao.equals("infixa")) {
                String posfixa = infixaParaPosfixa(input);
                String prefixa = infixaParaPrefixa(input);
                double resultadoCalculo = avaliarPosfixa(posfixa);
                resultado = "Infixa: " + input + "\nPos-fixa: " + posfixa + "\nPré-fixa: " + prefixa + "\nResultado: " + resultadoCalculo;
            } else if (tipoNotacao.equals("pos-fixa")) {
                double resultadoCalculo = avaliarPosfixa(input);
                String infixa = posfixaParaInfixa(input);
                String prefixa = infixaParaPrefixa(infixa);
                resultado = "Infixa: " + infixa + "\nPos-fixa: " + input + "\nPré-fixa: " + prefixa + "\nResultado: " + resultadoCalculo;
            } else if (tipoNotacao.equals("pre-fixa")) {
                double resultadoCalculo = avaliarPrefixa(input);
                String infixa = prefixaParaInfixa(input);
                String posfixa = infixaParaPosfixa(infixa);
                resultado = "Infixa: " + infixa + "\nPos-fixa: " + posfixa + "\nPré-fixa: " + input + "\nResultado: " + resultadoCalculo;
            } else {
                throw new IllegalArgumentException("Tipo de notação inválido.");
            }
        } catch (Exception e) {
            resultado = "Erro: " + e.getMessage();
        }

        JOptionPane.showMessageDialog(null, resultado);
    }

    private static String infixaParaPosfixa(String infixa) {
        StringBuilder posfixa = new StringBuilder();
        Stack<Character> pilha = new Stack<>();
        Map<Character, Integer> precedencia = Map.of('+', 1, '-', 1, '*', 2, '/', 2);

        for (char c : infixa.replaceAll("\s+", "").toCharArray()) {
            if (Character.isDigit(c)) {
                posfixa.append(c).append(' ');
            } else if (precedencia.containsKey(c)) {
                while (!pilha.isEmpty() && precedencia.getOrDefault(pilha.peek(), 0) >= precedencia.get(c)) {
                    posfixa.append(pilha.pop()).append(' ');
                }
                pilha.push(c);
            } else if (c == '(') {
                pilha.push(c);
            } else if (c == ')') {
                while (!pilha.isEmpty() && pilha.peek() != '(') {
                    posfixa.append(pilha.pop()).append(' ');
                }
                pilha.pop();
            }
        }
        while (!pilha.isEmpty()) {
            posfixa.append(pilha.pop()).append(' ');
        }

        return posfixa.toString().trim();
    }

    private static String infixaParaPrefixa(String infixa) {
        StringBuilder invertida = new StringBuilder(infixa).reverse();
        for (int i = 0; i < invertida.length(); i++) {
            if (invertida.charAt(i) == '(') {
                invertida.setCharAt(i, ')');
            } else if (invertida.charAt(i) == ')') {
                invertida.setCharAt(i, '(');
            }
        }
        String posfixaInvertida = infixaParaPosfixa(invertida.toString());
        return new StringBuilder(posfixaInvertida).reverse().toString();
    }

    private static double avaliarPosfixa(String posfixa) {
        Stack<Double> pilha = new Stack<>();
        for (String token : posfixa.split("\s+")) {
            if (token.matches("-?\\d+(\\.\\d+)?")) {
                pilha.push(Double.parseDouble(token));
            } else {
                double b = pilha.pop();
                double a = pilha.pop();
                switch (token) {
                    case "+": pilha.push(a + b); break;
                    case "-": pilha.push(a - b); break;
                    case "*": pilha.push(a * b); break;
                    case "/": pilha.push(a / b); break;
                }
            }
        }
        return pilha.pop();
    }

    private static double avaliarPrefixa(String prefixa) {
        Stack<Double> pilha = new Stack<>();
        List<String> tokens = Arrays.asList(prefixa.split("\s+"));
        Collections.reverse(tokens);
        for (String token : tokens) {
            if (token.matches("-?\\d+(\\.\\d+)?")) {
                pilha.push(Double.parseDouble(token));
            } else {
                double a = pilha.pop();
                double b = pilha.pop();
                switch (token) {
                    case "+": pilha.push(a + b); break;
                    case "-": pilha.push(a - b); break;
                    case "*": pilha.push(a * b); break;
                    case "/": pilha.push(a / b); break;
                }
            }
        }
        return pilha.pop();
    }

    private static String posfixaParaInfixa(String posfixa) {
        Stack<String> pilha = new Stack<>();
        for (String token : posfixa.split("\s+")) {
            if (token.matches("-?\\d+(\\.\\d+)?")) {
                pilha.push(token);
            } else {
                String b = pilha.pop();
                String a = pilha.pop();
                pilha.push("(" + a + " " + token + " " + b + ")");
            }
        }
        return pilha.pop();
    }

    private static String prefixaParaInfixa(String prefixa) {
        Stack<String> pilha = new Stack<>();
        List<String> tokens = Arrays.asList(prefixa.split("\s+"));
        Collections.reverse(tokens);
        for (String token : tokens) {
            if (token.matches("-?\\d+(\\.\\d+)?")) {
                pilha.push(token);
            } else {
                String a = pilha.pop();
                String b = pilha.pop();
                pilha.push("(" + a + " " + token + " " + b + ")");
            }
        }
        return pilha.pop();
    }
}
