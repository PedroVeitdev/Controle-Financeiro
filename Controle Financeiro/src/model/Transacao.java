package controle_financeiro.model;

public class Transacao {
    private static int contadorId = 1;

    private final int id;
    private String descricao;
    private double valor;
    private TipoTransacao tipo;

    public Transacao(String descricao, double valor, TipoTransacao tipo) {
        this.id = contadorId++;
        this.descricao = descricao;
        this.valor = valor;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public TipoTransacao getTipo() {
        return tipo;
    }

    public void setTipo(TipoTransacao tipo) {
        this.tipo = tipo;
    }

    public double getValorImpacto() {
        return tipo == TipoTransacao.DESPESA ? -valor : valor;
    }

    @Override
    public String toString() {
        return String.format("[%d] %s - %s: %s",
                id, tipo.getDescricao(), descricao, controle_financeiro.utils.Formatador.formatarMoeda(valor));
    }
}