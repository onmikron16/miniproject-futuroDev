package Projeto;

import Sistema.Empresa;
import Sistema.ONG;

public class ProjetoEnergiaRenovavel extends ProjetoSustentavel implements ImpactoAmbiental {

    private double energiaGerada;

    public ProjetoEnergiaRenovavel(String name, String descricao, double energiaGerada, ONG ong, Empresa empresa) {
        super(name, descricao, ong, empresa);
        this.energiaGerada = energiaGerada;
    }

    @Override
    public void calcularImpacto() {
        System.out.println("Impacto:" + energiaGerada + " energia gerada");
    }
}
