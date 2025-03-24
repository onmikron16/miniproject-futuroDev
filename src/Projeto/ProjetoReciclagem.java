package Projeto;

import Sistema.Organizacao;

public class ProjetoReciclagem extends ProjetoSustentavel implements ImpactoAmbiental {

    private double toneladasRecicladas;

    public ProjetoReciclagem(String name, String descricao, double toneladasRecicladas, Organizacao organizacao) {
        super(name, descricao, organizacao);
        this.toneladasRecicladas = toneladasRecicladas;
    }

    @Override
    public void calcularImpacto() {
        System.out.println("Impacto:" + toneladasRecicladas + " toneladas de lixo reciclados\n");
    }

}
