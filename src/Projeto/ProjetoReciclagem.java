package Projeto;

import Sistema.Empresa;
import Sistema.ONG;

public class ProjetoReciclagem extends ProjetoSustentavel implements ImpactoAmbiental {

    private double toneladasRecicladas;

    public ProjetoReciclagem(String name, String descricao, double toneladasRecicladas, ONG ong, Empresa empresa) {
        super(name, descricao, ong, empresa);
        this.toneladasRecicladas = toneladasRecicladas;
    }

    @Override
    public void calcularImpacto() {
        System.out.println("Impacto:" + toneladasRecicladas + " toneladas recicladas");
    }

}
