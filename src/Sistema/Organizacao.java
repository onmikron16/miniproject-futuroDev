package Sistema;

import Projeto.ProjetoSustentavel;

import java.util.ArrayList;
import java.util.List;

public class Organizacao {

    private String name;
    private List<ProjetoSustentavel> listaProjetos;


    public Organizacao(String name) {
        this.name = name;
        listaProjetos = new ArrayList<>();
    }

    public void adicionarProjeto(ProjetoSustentavel projeto) {
        listaProjetos.add(projeto);
    }

    public String getName() {
        return name;
    }

    public void listarProjetos() {
        for (ProjetoSustentavel projeto : listaProjetos) {
            System.out.println("Projeto: " + projeto.getName());
        }
    }

    public List<ProjetoSustentavel> getListaProjetos() {
        return listaProjetos;
    }
}
