package Projeto;

import Sistema.Organizacao;

public class ProjetoEnergiaRenovavel extends ProjetoSustentavel implements ImpactoAmbiental {

    private double energiaGerada;

    public ProjetoEnergiaRenovavel(String name, String descricao, double energiaGerada, Organizacao organizacao) {
        super(name, descricao, organizacao);
        this.energiaGerada = energiaGerada;
    }

    @Override
    public void calcularImpacto() {
        System.out.println("Impacto:" + energiaGerada + " MW de energia gerada\n");
    }
}
