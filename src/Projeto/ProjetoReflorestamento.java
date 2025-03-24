package Projeto;

import Sistema.Organizacao;

public class ProjetoReflorestamento extends ProjetoSustentavel implements ImpactoAmbiental {

    private int arvoresPlantadas;

    public ProjetoReflorestamento(String name, String descricao, int arvoresPlantadas, Organizacao organizacao) {
        super(name, descricao, organizacao);
        this.arvoresPlantadas = arvoresPlantadas;
    }


    @Override
    public void calcularImpacto() {
        System.out.println("Impacto:" + arvoresPlantadas + " árvores plantadas\n");
    }
}
