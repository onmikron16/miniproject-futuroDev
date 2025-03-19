package Projeto;

import Sistema.Empresa;
import Sistema.ONG;
import Sistema.Voluntario;

import java.util.ArrayList;
import java.util.List;

public class ProjetoSustentavel {

    private String name;
    private String descricao;
    private List<Voluntario> voluntarios;
    private RelatorioImpacto relatorioImpacto;

    private ONG ong;
    private Empresa empresa;

    public ProjetoSustentavel(String name, String descricao, ONG ong, Empresa empresa) {
        this.name = name;
        this.voluntarios = new ArrayList<>();
        this.descricao = descricao;
        this.ong = ong;
        this.empresa = empresa;
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
            System.out.println("Nenhum relatório gerado para este projeto.");
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

    public RelatorioImpacto getRelatorioImpacto() {
        return relatorioImpacto;
    }
}
