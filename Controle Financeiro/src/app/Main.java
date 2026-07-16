package controle_financeiro.app;

import controle_financeiro.controller.GerenciadorFinanceiro;
import controle_financeiro.exceptions.TransacaoException;
import controle_financeiro.model.TipoTransacao;
import controle_financeiro.model.Transacao;
import controle_financeiro.model.TransacaoMensal;
import controle_financeiro.utils.Formatador;

import java.util.Scanner;

public class Main {
    private static final GerenciadorFinanceiro controller = GerenciadorFinanceiro.getInstancia();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao = 0;

        do {
            exibirMenu();
            try {
                System.out.print("Escolha uma opção: ");
                opcao = Integer.parseInt(scanner.nextLine().trim());

                switch (opcao) {
                    case 1 -> cadastrarTransacao();
                    case 2 -> listarTransacoes();
                    case 3 -> exibirSaldo();
                    case 4 -> removerTransacao();
                    case 5 -> System.out.println("\nObrigado por utilizar o Controle Financeiro. Até logo!");
                    default -> System.out.println("\n[ERRO] Opção inválida! Escolha um número de 1 a 5.");
                }
            } catch (NumberFormatException e) {
                System.out.println("\n[ERRO] Entrada inválida! Por favor, digite um número inteiro.");
            } catch (Exception e) {
                System.out.println("\n[ERRO inesperado] " + e.getMessage());
            }
            aguardarSegundos();
        } while (opcao != 5);

        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("\n===============================================");
        System.out.println("               CONTROLE FINANCEIRO             ");
        System.out.println("===============================================");
        System.out.println("  1. Adicionar Nova Transação");
        System.out.println("  2. Listar Transações");
        System.out.println("  3. Mostrar Saldo Atual");
        System.out.println("  4. Remover Transação");
        System.out.println("  5. Sair");
        System.out.println("===============================================");
    }

    private static void cadastrarTransacao() {
        try {
            System.out.println("\n--- CADASTRAR TRANSAÇÃO ---");
            System.out.print("Descrição: ");
            String descricao = scanner.nextLine().trim();

            System.out.print("Valor (Ex: 1500.50): ");
            double valor = Double.parseDouble(scanner.nextLine().trim());

            System.out.print("Tipo (1 - Receita, 2 - Despesa): ");
            int tipoEscola = Integer.parseInt(scanner.nextLine().trim());
            TipoTransacao tipo = (tipoEscola == 1) ? TipoTransacao.RECEITA :
                    (tipoEscola == 2) ? TipoTransacao.DESPESA : null;

            if (tipo == null) {
                System.out.println("[ERRO] Tipo inválido. Transação cancelada.");
                return;
            }

            System.out.print("É uma transação recorrente/mensal? (S/N): ");
            String recorrente = scanner.nextLine().trim().toUpperCase();

            Transacao novaTransacao;
            if (recorrente.equals("S")) {
                System.out.print("Quantidade de meses de recorrência: ");
                int meses = Integer.parseInt(scanner.nextLine().trim());
                novaTransacao = new TransacaoMensal(descricao, valor, tipo, meses);
            } else {
                novaTransacao = new Transacao(descricao, valor, tipo);
            }

            controller.adicionarTransacao(novaTransacao);
            System.out.println("\n[SUCESSO] Transação adicionada com sucesso!");

        } catch (NumberFormatException e) {
            System.out.println("\n[ERRO] Falha ao processar valores numéricos. Certifique-se de usar pontos em vez de vírgulas.");
        } catch (TransacaoException e) {
            System.out.println("\n[ERRO DE NEGÓCIO] " + e.getMessage());
        }
    }

    private static void listarTransacoes() {
        System.out.println("\n--- LISTA DE TRANSAÇÕES ---");
        var lista = controller.getTransacoes();

        if (lista.isEmpty()) {
            System.out.println("Nenhuma transação cadastrada até o momento.");
            return;
        }

        lista.forEach(System.out::println);
    }

    private static void exibirSaldo() {
        System.out.println("\n--- SALDO ATUAL ---");
        double saldo = controller.calcularSaldoTotal();
        String saldoFormatado = Formatador.formatarMoeda(saldo);

        if (saldo >= 0) {
            System.out.println("Saldo Disponível: \u001B[32m" + saldoFormatado + "\u001B[0m");
        } else {
            System.out.println("Saldo Devedor: \u001B[31m" + saldoFormatado + "\u001B[0m");
        }
    }

    private static void removerTransacao() {
        try {
            System.out.println("\n--- REMOVER TRANSAÇÃO ---");
            System.out.print("Informe o ID da transação que deseja remover: ");
            int id = Integer.parseInt(scanner.nextLine().trim());

            if (controller.removerTransacao(id)) {
                System.out.println("\n[SUCESSO] Transação removida com êxito!");
            }
        } catch (NumberFormatException e) {
            System.out.println("\n[ERRO] O ID deve ser um número inteiro válido.");
        } catch (TransacaoException e) {
            System.out.println("\n[ERRO] " + e.getMessage());
        }
    }

    private static void aguardarSegundos() {
        try { Thread.sleep(1000); } catch (InterruptedException ignored) {}
    }
}