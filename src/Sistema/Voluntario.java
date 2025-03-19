package Sistema;

import Projeto.ProjetoSustentavel;

public class Voluntario {

    private String nome;
    private String email;

    public Voluntario(String nome, String email) {
        this.email = email;
        this.nome = nome;
    }

    public void participarProjeto(ProjetoSustentavel projet){
        projet.adicionarVoluntario(this);
    }

    public String getNome() {
        return nome;
    }

}
