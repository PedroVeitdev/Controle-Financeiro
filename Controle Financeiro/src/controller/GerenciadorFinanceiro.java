package controle_financeiro.controller;

import controle_financeiro.exceptions.TransacaoException;
import controle_financeiro.model.Transacao;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorFinanceiro {
    private static GerenciadorFinanceiro instancia;
    private final List<Transacao> transacoes;

    private GerenciadorFinanceiro() {
        this.transacoes = new ArrayList<>();
    }

    public static synchronized GerenciadorFinanceiro getInstancia() {
        if (instancia == null) {
            instancia = new GerenciadorFinanceiro();
        }
        return instancia;
    }

    public void adicionarTransacao(Transacao transacao) throws TransacaoException {
        if (transacao == null) {
            throw new TransacaoException("Não é possível adicionar uma transação nula.");
        }
        if (transacao.getValor() <= 0) {
            throw new TransacaoException("O valor da transação deve ser maior que zero.");
        }
        if (transacao.getDescricao() == null || transacao.getDescricao().trim().isEmpty()) {
            throw new TransacaoException("A descrição da transação não pode estar vazia.");
        }
        transacoes.add(transacao);
    }

    public List<Transacao> getTransacoes() {
        return new ArrayList<>(transacoes);
    }

    public boolean removerTransacao(int id) throws TransacaoException {
        boolean removido = transacoes.removeIf(t -> t.getId() == id);
        if (!removido) {
            throw new TransacaoException("Transação com o ID " + id + " não foi encontrada.");
        }
        return true;
    }

    public double calcularSaldoTotal() {
        double saldo = 0;
        for (Transacao t : transacoes) {
            saldo += t.getValorImpacto();
        }
        return saldo;
    }
}