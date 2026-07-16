package controle_financeiro.model;

public class TransacaoMensal extends Transacao {
    private int mesesRecorrencia;

    public TransacaoMensal(String descricao, double valor, TipoTransacao tipo, int mesesRecorrencia) {
        super(descricao, valor, tipo);
        this.mesesRecorrencia = mesesRecorrencia;
    }

    public int getMesesRecorrencia() {
        return mesesRecorrencia;
    }

    public void setMesesRecorrencia(int mesesRecorrencia) {
        this.mesesRecorrencia = mesesRecorrencia;
    }

    @Override
    public double getValorImpacto() {
        return super.getValorImpacto() * mesesRecorrencia;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" (Recorrente por %d meses | Total: %s)",
                mesesRecorrencia, controle_financeiro.utils.Formatador.formatarMoeda(Math.abs(getValorImpacto())));
    }
}