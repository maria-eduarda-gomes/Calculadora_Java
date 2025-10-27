abstract class Operacao {
    protected double a;
    protected double b;
    
    public Operacao(double a, double b) {
        this.a = a;
        this.b = b;
    }
    
    public Operacao(double a) {
        this.a = a;
        this.b = 0;
    }
    
    public abstract double calcular();
    
    public abstract String toString();
}

class Soma extends Operacao {
    public Soma(double a, double b) {
        super(a, b);
    }
    
    @Override
    public double calcular() {
        return a + b;
    }
    
    @Override
    public String toString() {
        return String.format("%.2f + %.2f = %.2f", a, b, calcular());
    }
}

class Subtracao extends Operacao {
    public Subtracao(double a, double b) {
        super(a, b);
    }
    
    @Override
    public double calcular() {
        return a - b;
    }
    
    @Override
    public String toString() {
        return String.format("%.2f - %.2f = %.2f", a, b, calcular());
    }
}

class Multiplicacao extends Operacao {
    public Multiplicacao(double a, double b) {
        super(a, b);
    }
    
    @Override
    public double calcular() {
        return a * b;
    }
    
    @Override
    public String toString() {
        return String.format("%.2f × %.2f = %.2f", a, b, calcular());
    }
}

class Divisao extends Operacao {
    public Divisao(double a, double b) {
        super(a, b);
    }
    
    @Override
    public double calcular() {
        if (b == 0) {
            throw new ArithmeticException("Divisão por zero não permitida");
        }
        return a / b;
    }
    
    @Override
    public String toString() {
        return String.format("%.2f ÷ %.2f = %.2f", a, b, calcular());
    }
}

class Potencia extends Operacao {
    public Potencia(double a, double b) {
        super(a, b);
    }
    
    @Override
    public double calcular() {
        return Math.pow(a, b);
    }
    
    @Override
    public String toString() {
        return String.format("%.2f ^ %.2f = %.2f", a, b, calcular());
    }
}

class RaizQuadrada extends Operacao {
    public RaizQuadrada(double a) {
        super(a);
    }
    
    @Override
    public double calcular() {
        if (a < 0) {
            throw new ArithmeticException("Raiz quadrada de número negativo");
        }
        return Math.sqrt(a);
    }
    
    @Override
    public String toString() {
        return String.format("√%.2f = %.2f", a, calcular());
    }
}

import java.util.ArrayList;
import java.util.List;

class Historico {
    private List<Operacao> operacoes;
    
    public Historico() {
        this.operacoes = new ArrayList<>();
    }
    
    public void adicionar(Operacao operacao) {
        operacoes.add(operacao);
    }
    
    public List<String> listar() {
        List<String> historico = new ArrayList<>();
        for (Operacao op : operacoes) {
            historico.add(op.toString());
        }
        return historico;
    }
    
    public void limpar() {
        operacoes.clear();
    }
    
    public int tamanho() {
        return operacoes.size();
    }
}

class Calculadora {
    private Historico historico;
    private double resultadoAnterior;
    
    public Calculadora() {
        this.historico = new Historico();
        this.resultadoAnterior = 0.0;
    }
    
    public double executarOperacao(Operacao operacao) {
        double resultado = operacao.calcular();
        historico.adicionar(operacao);
        this.resultadoAnterior = resultado;
        return resultado;
    }
    
    public List<String> obterHistorico() {
        return historico.listar();
    }
    
    public void limparHistorico() {
        historico.limpar();
    }
    
    public double obterResultadoAnterior() {
        return resultadoAnterior;
    }
}

import java.util.Scanner;

class InterfaceTerminal {
    private Calculadora calc;
    private Scanner scanner;
    
    public InterfaceTerminal() {
        this.calc = new Calculadora();
        this.scanner = new Scanner(System.in);
    }
    
    public void exibirMenu() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("CALCULADORA - Orientação a Objetos");
        System.out.println("=".repeat(50));
        System.out.println("1. Soma");
        System.out.println("2. Subtração");
        System.out.println("3. Multiplicação");
        System.out.println("4. Divisão");
        System.out.println("5. Potência");
        System.out.println("6. Raiz Quadrada");
        System.out.println("7. Ver Histórico");
        System.out.println("8. Limpar Histórico");
        System.out.println("0. Sair");
        System.out.println("=".repeat(50));
    }
    
    public double lerNumero(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Erro: Digite um número válido!");
            }
        }
    }
    
    public void executar() {
        while (true) {
            exibirMenu();
            System.out.print("Escolha uma opção: ");
            String opcao = scanner.nextLine();
            
            try {
                switch (opcao) {
                    case "0":
                        System.out.println("Encerrando calculadora...");
                        scanner.close();
                        return;
                    
                    case "1": {
                        double a = lerNumero("Digite o primeiro número: ");
                        double b = lerNumero("Digite o segundo número: ");
                        Operacao op = new Soma(a, b);
                        double resultado = calc.executarOperacao(op);
                        System.out.printf("\nResultado: %.2f\n", resultado);
                        break;
                    }
                    
                    case "2": {
                        double a = lerNumero("Digite o primeiro número: ");
                        double b = lerNumero("Digite o segundo número: ");
                        Operacao op = new Subtracao(a, b);
                        double resultado = calc.executarOperacao(op);
                        System.out.printf("\nResultado: %.2f\n", resultado);
                        break;
                    }
                    
                    case "3": {
                        double a = lerNumero("Digite o primeiro número: ");
                        double b = lerNumero("Digite o segundo número: ");
                        Operacao op = new Multiplicacao(a, b);
                        double resultado = calc.executarOperacao(op);
                        System.out.printf("\nResultado: %.2f\n", resultado);
                        break;
                    }
                    
                    case "4": {
                        double a = lerNumero("Digite o dividendo: ");
                        double b = lerNumero("Digite o divisor: ");
                        Operacao op = new Divisao(a, b);
                        double resultado = calc.executarOperacao(op);
                        System.out.printf("\nResultado: %.2f\n", resultado);
                        break;
                    }
                    
                    case "5": {
                        double a = lerNumero("Digite a base: ");
                        double b = lerNumero("Digite o expoente: ");
                        Operacao op = new Potencia(a, b);
                        double resultado = calc.executarOperacao(op);
                        System.out.printf("\nResultado: %.2f\n", resultado);
                        break;
                    }
                    
                    case "6": {
                        double a = lerNumero("Digite o número: ");
                        Operacao op = new RaizQuadrada(a);
                        double resultado = calc.executarOperacao(op);
                        System.out.printf("\nResultado: %.2f\n", resultado);
                        break;
                    }
                    
                    case "7": {
                        List<String> historico = calc.obterHistorico();
                        System.out.println("\n--- HISTÓRICO ---");
                        if (historico.isEmpty()) {
                            System.out.println("Histórico vazio");
                        } else {
                            for (int i = 0; i < historico.size(); i++) {
                                System.out.println((i + 1) + ". " + historico.get(i));
                            }
                        }
                        break;
                    }
                    
                    case "8":
                        calc.limparHistorico();
                        System.out.println("\nHistórico limpo com sucesso!");
                        break;
                    
                    default:
                        System.out.println("\nOpção inválida!");
                }
            } catch (ArithmeticException e) {
                System.out.println("\nErro: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("\nErro inesperado: " + e.getMessage());
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        InterfaceTerminal interface_ = new InterfaceTerminal();
        interface_.executar();
    }
}