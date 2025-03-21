import Projeto.*;
import Sistema.Empresa;
import Sistema.ONG;
import Sistema.Organizacao;
import Sistema.Voluntario;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean run = true;
        Organizacao organizacao = new Organizacao("Prefeitura de Joinville");
        Scanner sc = new Scanner(System.in);

        while (run) {
            System.out.println(
                    """
                          1 - Adicionar Projetos
                          2 - Adicionar Voluntarios
                          3 - Relatorio De Impacto
                          4 - Listar Projetos
                          5 - Listar Voluntarios e Vontalariar em outro projeto
                          6 - Sair
                          """
            );

            int opcao = sc.nextInt();
            sc.nextLine();

            if(opcao == 1) adicionarProjeto(sc,organizacao);
            else if(opcao == 2) adicionarVoluntario(sc, organizacao);
            else if(opcao == 3) gerarRelatorioImpacto(sc,organizacao);
            else if(opcao == 4) listarProjetos(organizacao);
            else if(opcao == 5) listarVoluntarios(sc, organizacao, organizacao.getListaProjetos());
            else if(opcao == 6) run = false;
            else System.out.println("Opção inválida");
        }
    }

    private static void adicionarProjeto(Scanner sc,Organizacao organizacao) {
        System.out.println("--- Qual tipo de Projeto? ---");
        System.out.println("1 - Energia Renovável");
        System.out.println("2 - Reflorestamento");
        System.out.println("3 - Reciclagem");
        int tipoProjeto = sc.nextInt();
        sc.nextLine();

        if (tipoProjeto < 1 || tipoProjeto > 3) {
            System.out.println("Opção inválida");
            return;
        }

        System.out.println("Nome do Projeto:");
        String nomeProjeto = sc.nextLine();
        System.out.println("Descrição do Projeto:");
        String descricaoProjeto = sc.nextLine();

        System.out.println("ONG ou Empresa Responsável?");
        System.out.println("1 - ONG");
        System.out.println("2 - Empresa");

        int tipoResponsavel = sc.nextInt();
        sc.nextLine();

        if (tipoResponsavel < 1 || tipoResponsavel > 2) {
            System.out.println("Opção inválida");
            return;
        }

        Organizacao organizacaoTemp;
        if (tipoResponsavel == 1) organizacaoTemp = cadastroOng(sc);
        else organizacaoTemp = cadastroEmpresa(sc);

        switch (tipoProjeto) {
            case 1:
                System.out.println("Energia Gerada:");
                double energiaGerada = sc.nextDouble();
                sc.nextLine();
                organizacao.adicionarProjeto(new ProjetoEnergiaRenovavel(nomeProjeto, descricaoProjeto, energiaGerada, organizacaoTemp));
                break;
            case 2:
                System.out.println("Árvores Plantadas:");
                int arvoresPlantadas = sc.nextInt();
                sc.nextLine();
                organizacao.adicionarProjeto(new ProjetoReflorestamento(nomeProjeto, descricaoProjeto, arvoresPlantadas, organizacaoTemp));
                break;
            case 3:
                System.out.println("Toneladas de Lixo Reciclado:");
                double toneladasLixoReciclado = sc.nextDouble();
                sc.nextLine();
                organizacao.adicionarProjeto(new ProjetoReciclagem(nomeProjeto, descricaoProjeto, toneladasLixoReciclado, organizacaoTemp));
                break;
            default:
                System.out.println("Opção inválida");
                break;
        }

        System.out.println("Projeto Adicionado!");
    }

    private static void adicionarVoluntario(Scanner sc,Organizacao organizacao) {
        if (organizacao.getListaProjetos().isEmpty()) {
            System.out.println("Nenhum projeto cadastrado!!");
            return;
        }

        System.out.println("--- Adicionar Voluntario ---");
        System.out.println("Nome do Voluntario:");
        String nomeVoluntario = sc.nextLine();
        System.out.println("Email do Voluntario:");
        String emailVoluntario = sc.nextLine();
        Voluntario voluntarioTemp = new Voluntario(nomeVoluntario, emailVoluntario);


        System.out.println("Projetos Cadastrados:");
        for (int i = 0; i < organizacao.getListaProjetos().size(); i++) {
            ProjetoSustentavel projeto = organizacao.getListaProjetos().get(i);
            System.out.println(i + " - " + projeto.getName()
                    + "\nTipo: " + projeto.getClass().getSimpleName());
        }

        System.out.println("Qual Projeto deseja adicionar o voluntario? Digite o ID:");
        int idProjeto = sc.nextInt();
        sc.nextLine();

        if (idProjeto < 0 || idProjeto >= organizacao.getListaProjetos().size()) {
            System.out.println("Opção inválida");
            return;
        }

        voluntarioTemp.participarProjeto(organizacao.getListaProjetos().get(idProjeto));
        System.out.println("Voluntario adicionado ao projeto: " + organizacao.getListaProjetos().get(idProjeto).getName());

        if (organizacao.getListaProjetos().size() > 1) {
            System.out.println("Deseja adicionar o voluntario a outro projeto? (S/N)");
            String opcao2 = sc.nextLine();
            while (opcao2.equalsIgnoreCase("S")) {
                for (int i = 0; i < organizacao.getListaProjetos().size(); i++) {
                    ProjetoSustentavel projeto = organizacao.getListaProjetos().get(i);
                    System.out.println(i + " - " + projeto.getName()
                            + "\nTipo: " + projeto.getClass().getSimpleName());
                }

                if (organizacao.getListaProjetos().stream().allMatch(projeto //metodo lambda pra verificar todos os projetos se coin volubntario
                        -> projeto.getVoluntarios().contains(voluntarioTemp))) {
                    System.out.println("Voluntario já está em todos os projetos.");
                    break;
                }

                System.out.println("Qual Projeto deseja adicionar o voluntario? Digite o ID:");
                int idProjeto2 = sc.nextInt();
                sc.nextLine();
                organizacao.getListaProjetos().get(idProjeto2).adicionarVoluntario(voluntarioTemp);

                System.out.println("Deseja adicionar o voluntario a outro projeto? (S/N)");
                opcao2 = sc.nextLine();
            }
        }
    }

    private static void gerarRelatorioImpacto(Scanner sc, Organizacao organizacao) {
        System.out.println("--- Relatorio de impacto ---");
        System.out.println("Qual Projeto?");
        System.out.println("1 - Energia Renovável");
        System.out.println("2 - Reflorestamento");
        System.out.println("3 - Reciclagem");
        int tipoProjetoRelatorio = sc.nextInt();
        sc.nextLine();

        if (tipoProjetoRelatorio < 1 || tipoProjetoRelatorio > 3) {
            System.out.println("Opção inválida");
            return;
        }

        if (organizacao.getListaProjetos().isEmpty()) {
            System.out.println("Nenhum projeto cadastrado");
            return;
        }


        List<Integer> index = IndexProjeto(organizacao, tipoProjetoRelatorio);;

        if (index.isEmpty()) {
            System.out.println("Nenhum projeto cadastrado");
            return;
        }

        if (index.size() > 1) {
            System.out.println("Qual Projeto deseja gerar relatório?");
            for (Integer i : index) {
                System.out.println("--------------------------------------------------");
                System.out.println(i + " - " + organizacao.getListaProjetos().get(i).getName());
            }

            int opcao1 = sc.nextInt();
            sc.nextLine();

            if (opcao1 < 0 || opcao1 >= index.size() || !index.contains(opcao1)) {
                System.out.println("Opção inválida");
                return;
            }

            ProjetoSustentavel projeto = organizacao.getListaProjetos().get(opcao1);
            System.out.println("Relatório gerado para o projeto: " + projeto.getName());
            relatorioImpactoProjeto(sc, projeto);
            projeto.calcularImpacto();

        } else {
            ProjetoSustentavel projeto = organizacao.getListaProjetos().get(index.getFirst());
            System.out.println("Relatório gerado para o projeto: " + projeto.getName());
            relatorioImpactoProjeto(sc, projeto);
            projeto.calcularImpacto();
        }
    }


    private static void listarProjetos(Organizacao organizacao) {
        if (organizacao.getListaProjetos().isEmpty()) {
            System.out.println("Nenhum projeto cadastrado");
            return;
        }

        System.out.println("--- Listar Projetos ---\n");
        organizacao.getListaProjetos().forEach(projeto -> {
            System.out.println("-----------------------------");
            System.out.println("Nome: " + projeto.getName());
            System.out.println("Descrição: " + projeto.getDescricao());
            if(organizacao instanceof ONG) System.out.println("ONG Responsável: " + projeto.getOrganizacao().getName());
            else System.out.println("EmpresaResponsável: " + projeto.getOrganizacao().getName());

            if(projeto.getRelatorioImpacto() == null) {
                System.out.println("\nNenhum relatório encontrado para este projeto.");
                return;
            }
            System.out.println();
            projeto.exibirRelatorio();
            projeto.calcularImpacto();
        });
        System.out.println("-----------------------------");
    }

    private static void listarVoluntarios(Scanner sc, Organizacao organizacao, List<ProjetoSustentavel> listaProjetos) {
        System.out.println("--- Listar Voluntarios ---");

        System.out.println("Qual Projeto?");
        System.out.println("1 - Energia Renovável");
        System.out.println("2 - Reflorestamento");
        System.out.println("3 - Reciclagem");

        int tipoProjeto = sc.nextInt();
        sc.nextLine();

        if(tipoProjeto < 1 || tipoProjeto > 3) {
            System.out.println("Opção inválida");
            return;
        }

        List<Integer> index = IndexProjeto(organizacao, tipoProjeto);

        if (index.isEmpty() || organizacao.getListaProjetos().isEmpty()) {
            System.out.println("Nenhum projeto cadastrado");
            return;
        }

        for (Integer i : index) {
            if(listaProjetos.get(i).getVoluntarios().isEmpty()) {
                System.out.println("Nenhum voluntario cadastrado para este projeto.");
                return;
            }
            System.out.println("--------------------------------------------------");
            System.out.println(i + " - " + listaProjetos.get(i).getName());
            listaProjetos.get(i).getVoluntarios().forEach(voluntario -> {
                System.out.println("Nome: " + voluntario.getNome());
            });
        }

        System.out.println("Deseja Adicionar voluntarios para algun projeto? (S/N)");
        String opcaoVol = sc.nextLine();
        if (opcaoVol.equalsIgnoreCase("S")) {
            System.out.println("Qual o nome do voluntario?");
            String voluntarioNome = sc.nextLine();

            listaProjetos.forEach(projetoSustentavel -> {
                projetoSustentavel.getVoluntarios().forEach(voluntario -> {
                    if(!voluntario.getNome().equals(voluntarioNome)
                            || organizacao.getListaProjetos().stream().allMatch(projeto
                            -> projeto.getVoluntarios().contains(voluntario))) {
                        System.out.println("Voluntario não encontrado ou já está em todos os projetos.");
                        return;
                    }

                    System.out.println("Projetos Cadastrados:");
                    for (int i = 0; i < organizacao.getListaProjetos().size(); i++) {
                        System.out.println("--------------------------------------------------");
                        ProjetoSustentavel projeto = organizacao.getListaProjetos().get(i);
                        System.out.println(i + " - " + projeto.getName() + "\nTipo: " + projeto.getClass().getSimpleName());
                    }
                    System.out.println("\nQual Projeto deseja adicionar o voluntario? Digite o ID:");
                    int idProjeto = sc.nextInt();
                    sc.nextLine();

                    if (idProjeto < 0 || idProjeto >= organizacao.getListaProjetos().size()) {
                        System.out.println("Opção inválida");
                        return;
                    }
                    organizacao.getListaProjetos().get(idProjeto).adicionarVoluntario(voluntario);
                    System.out.println("Voluntario adicionado ao projeto: " + organizacao.getListaProjetos().get(idProjeto).getName());
                });
            });
        }

    }

    private static void relatorioImpactoProjeto(Scanner sc, ProjetoSustentavel projeto) {
        System.out.println("Arvores Plantadas");
        int arvoresPlantadas = sc.nextInt();
        System.out.println("Redução de CO2 (Toneladas)");
        double reducaoCO2 = sc.nextDouble();
        projeto.gerarRelatorioImpacto(arvoresPlantadas, reducaoCO2);
    }

    private static Empresa cadastroEmpresa(Scanner sc) {
        System.out.println("Empresa Responsavel:");
        String nomeEmpresa = sc.nextLine();
        System.out.println("CNPJ da Epresa Responsavel:");
        String cnpjEmpresa = sc.nextLine();
        return new Empresa(nomeEmpresa, cnpjEmpresa);
    }

    private static ONG cadastroOng(Scanner sc) {
        System.out.println("ONG Responsavel");
        return new ONG(sc.nextLine());
    }

    private static List<Integer> IndexProjeto(Organizacao organizacao, int tipoProjetoRelatorio) {
        List<Integer> index = new ArrayList<>();
        for (int i = 0; i < organizacao.getListaProjetos().size(); i++) {
            ProjetoSustentavel projeto = organizacao.getListaProjetos().get(i);
            if ((tipoProjetoRelatorio == 1 && projeto instanceof ProjetoEnergiaRenovavel)
                    || (tipoProjetoRelatorio == 2 && projeto instanceof ProjetoReflorestamento)
                    || (tipoProjetoRelatorio == 3 && projeto instanceof ProjetoReciclagem)) {
                index.add(i);
            }
        }
        return index;
    }

}