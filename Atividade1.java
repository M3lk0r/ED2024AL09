import java.util.Stack;
import javax.swing.JOptionPane;

public class Atividade1 {
    private Stack<Character> pilha;

    public Atividade1() {
        pilha = new Stack<>();
    }

    private void empilharPalavra(String palavra) {
        for (char c : palavra.toCharArray()) {
            pilha.push(c);
        }
    }

    private String inverterPalavra() {
        StringBuilder palavraInvertida = new StringBuilder();
        while (!pilha.isEmpty()) {
            palavraInvertida.append(pilha.pop());
        }
        return palavraInvertida.toString();
    }

    public String inverterPalavras(String input) {
        StringBuilder resultado = new StringBuilder();
        String[] palavras = input.split(" ");
        for (String palavra : palavras) {
            empilharPalavra(palavra);
            resultado.append(inverterPalavra()).append(" ");
        }
        return resultado.toString().trim();
    }

    public static void main(String[] args) {
        Atividade1 atividade = new Atividade1();
        String exemplo1 = "UM CIENTISTA DA COMPUTAÇAO E UM TECNÓLOGO EM SISTEMAS PARA INTERNET DEVEM RESOLVER OS PROBLEMAS LOGICAMENTE";
        String resultado1 = atividade.inverterPalavras(exemplo1);
        JOptionPane.showMessageDialog(null, "Exemplo 1:\n" + resultado1);

        String exemplo2 = "ESARF :ATERCES ODALERAHCAB ME AICNEIC AD OAÇATUPMOC E O OGOLÓNCET ME SAMETSIS ARAP TENRETNI OD FI ONAIOG SUPMAC SOHNIRROM OÃS SO SEROHLEM SOSRUC ED OAÇATUPMOC OD ODATSE ED .SAIOG";
        String resultado2 = atividade.inverterPalavras(exemplo2);
        JOptionPane.showMessageDialog(null, "Exemplo 2:\n" + resultado2);
    }
}
