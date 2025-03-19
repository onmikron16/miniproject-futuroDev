package Projeto;

import Sistema.Empresa;
import Sistema.ONG;

public class ProjetoReflorestamento extends ProjetoSustentavel implements ImpactoAmbiental {

    private int arvoresPlantadas;

    public ProjetoReflorestamento(String name, String descricao, int arvoresPlantadas, ONG ong, Empresa empresa) {
        super(name, descricao, ong, empresa);
        this.arvoresPlantadas = arvoresPlantadas;
    }


    @Override
    public void calcularImpacto() {
        System.out.println("Impacto:" + arvoresPlantadas + " árvores plantadas");
    }
}
