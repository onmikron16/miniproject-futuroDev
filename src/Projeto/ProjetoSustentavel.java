package Projeto;

import Sistema.*;

import java.util.ArrayList;
import java.util.List;

public class ProjetoSustentavel implements ImpactoAmbiental{

    private String name;
    private String descricao;
    private List<Voluntario> voluntarios;
    private RelatorioImpacto relatorioImpacto;
    private Organizacao organizacao;

    public ProjetoSustentavel(String name, String descricao, Organizacao organizacao) {
        this.name = name;
        this.voluntarios = new ArrayList<>();
        this.descricao = descricao;
        this.organizacao = organizacao;
    }

    public void adicionarVoluntario(Voluntario voluntario) {
        voluntarios.add(voluntario);
    }

    public void gerarRelatorioImpacto(int arvoresPlantadas, double reducaoCO2) {
       relatorioImpacto = new RelatorioImpacto(arvoresPlantadas, reducaoCO2);
    }

    public void exibirRelatorio() {
        if (relatorioImpacto != null) {
            relatorioImpacto.exibirRelatorio();
        } else {
            System.out.println("Nenhum relatório gerado para este projeto.\n");
        }
    }

    public String getName() {
        return name;
    }

    public String getDescricao() {
        return descricao;
    }

    public List<Voluntario> getVoluntarios() {
        return voluntarios;
    }

    public Organizacao getOrganizacao() {
        return organizacao;
    }

    public RelatorioImpacto getRelatorioImpacto() {
        return relatorioImpacto;
    }

    @Override
    public void calcularImpacto() {
        System.out.println("Nenhum relatório gerado para este projeto.");
    }
}
