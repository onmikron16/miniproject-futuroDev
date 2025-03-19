import Projeto.*;
import Sistema.Empresa;
import Sistema.ONG;
import Sistema.Voluntario;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean run = true, continuar = false;
        ONG ong;
        Empresa empresa;
        List<ProjetoSustentavel> listaProjetos = new ArrayList<>();
        List<Voluntario> listaVoluntarios = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        do {
            System.out.println(
                      "1 - Adicionar Projetos\n"
                    + "2 - Adicionar Voluntarios\n"
                    + "3 - Relatorio De Impacto\n"
                    + "4 - Listar Projetos\n"
                    + "5 - Listar Voluntarios e Vontalariar em outro projeto\n"
                    + "6 - Sair\n"
            );

            int opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("Qual tipo de Projeto?");
                    System.out.println("1 - Energia Renovável");
                    System.out.println("2 - Reflorestamento");
                    System.out.println("3 - Reciclagem");
                    int tipoProjeto = sc.nextInt();
                    sc.nextLine();

                    if(tipoProjeto < 1 || tipoProjeto > 3){
                        System.out.println("Opção inválida");
                       break;
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

                    if(tipoResponsavel < 1 || tipoResponsavel > 2) {
                        System.out.println("Opção inválida");
                        return;
                    }

                    if(tipoResponsavel == 1){
                        ong = cadastroOng();
                        empresa = null;
                    } else {
                        empresa = cadastroEmpresa();
                        ong = null;
                    }

                    switch (tipoProjeto){
                        case 1:
                            System.out.println("Energia Gerada:");
                            double energiaGerada = sc.nextDouble();
                            sc.nextLine();
                            listaProjetos.add(new ProjetoEnergiaRenovavel(nomeProjeto, descricaoProjeto, energiaGerada, ong, empresa));
                            break;
                        case 2:
                            System.out.println("Árvores Plantadas:");
                            int arvoresPlantadas = sc.nextInt();
                            sc.nextLine();
                            listaProjetos.add(new ProjetoReflorestamento(nomeProjeto, descricaoProjeto, arvoresPlantadas, ong, empresa));
                            break;
                        case 3:
                            System.out.println("Toneladas de Lixo Reciclado:");
                            double toneladasLixoReciclado = sc.nextDouble();
                            sc.nextLine();
                            listaProjetos.add(new ProjetoReciclagem(nomeProjeto, descricaoProjeto, toneladasLixoReciclado, ong, empresa));
                        break;
                    }
                    break;

                case 2:
                    int id = 0;
                    System.out.println("Adicionar Voluntario");
                    System.out.println("Nome do Voluntario:");
                    String nomeVoluntario = sc.nextLine();
                    System.out.println("Email do Voluntario:");
                    String emailVoluntario = sc.nextLine();
                    Voluntario volutarioTemp = new Voluntario(nomeVoluntario, emailVoluntario);
                    listaVoluntarios.add(volutarioTemp);

                    if(listaProjetos.isEmpty()){
                        System.out.println("Nenhum projeto cadastrado");
                        break;
                    }

                    System.out.println("Projetos Cadastrados:");
                    for(ProjetoSustentavel list : listaProjetos){
                        System.out.println(list.getName() + "\nTipo: "+ list.getClass().getName().replaceFirst("Projeto.","") + "\nID:" + id);
                        id++;
                    }
                    System.out.println("Qual Projeto deseja adicionar o voluntario? Digite o ID:");
                    voluntarioAdicionarProjetos(listaProjetos, volutarioTemp, continuar);
                    break;

                case 3:
                    System.out.println("Relatorio de impacto");
                    System.out.println("Qual Projeto?");
                    System.out.println("1 - Energia Renovável");
                    System.out.println("2 - Reflorestamento");
                    System.out.println("3 - Reciclagem");
                    int tipoProjetoRelatorio = sc.nextInt();
                    sc.nextLine();

                    if(tipoProjetoRelatorio < 1 || tipoProjetoRelatorio > 3){
                        System.out.println("Opção inválida");
                        break;
                    }

                    int check = 0;
                    ArrayList<Integer> index = new ArrayList<>();

                    if(listaProjetos.isEmpty()){
                        System.out.println("Nenhum projeto cadastrado");
                        break;
                    }

                    for (int i = 0; i < listaProjetos.size(); i++) {
                        if(tipoProjetoRelatorio == 1 && listaProjetos.get(i) instanceof ProjetoEnergiaRenovavel){
                            check++;
                            index.add(i);
                        } else if(tipoProjetoRelatorio == 2 && listaProjetos.get(i) instanceof ProjetoReflorestamento){
                            check++;
                            index.add(i);
                        } else if(tipoProjetoRelatorio == 3 && listaProjetos.get(i) instanceof ProjetoReciclagem){
                            check++;
                            index.add(i);
                        }
                    }

                    if(index.isEmpty()){
                        System.out.println("Nenhum projeto cadastrado");
                        break;
                    }
                    if(check > 1){
                        System.out.println("Qual Projeto deseja gerar relatório?");
                        for (Integer i : index) {
                            System.out.println(i + " - " + listaProjetos.get(i).getName());
                        }
                        int opcao1 = sc.nextInt();
                        if(opcao1 < 0 || opcao1 > index.size() || !index.contains(opcao1)){
                            System.out.println("Opção inválida");
                            break;
                        }


                        System.out.println("Relatório gerado para o projeto: " + listaProjetos.get(opcao1).getName());
                        ((ProjetoEnergiaRenovavel) listaProjetos.get(opcao1)).calcularImpacto();
                        relatorioImpactoProjeto(listaProjetos.get(opcao1));

                    } else{
                        System.out.println("Relatório gerado para o projeto: " + listaProjetos.get(index.getFirst()).getName());
                        relatorioImpactoProjeto(listaProjetos.get(index.getFirst()));
                        ((ProjetoEnergiaRenovavel) listaProjetos.get(index.getFirst())).calcularImpacto();
                    }

                    break;
                case 4:
                    System.out.println("Listar Projetos");
                    if(listaProjetos.isEmpty()){
                        System.out.println("Nenhum projeto cadastrado");
                        break;
                    }
                    for (ProjetoSustentavel projeto : listaProjetos) {
                        System.out.println(projeto.getName());
                    }
                    break;
                case 5:
                    System.out.println("Listar Voluntarios");
                    if(listaVoluntarios.isEmpty()){
                        System.out.println("Nenhum voluntario cadastrado");
                        break;
                    }

                    for (Voluntario voluntario : listaVoluntarios) {
                        System.out.println(voluntario.getNome());
                    }

                    if(listaProjetos.isEmpty()) break;

                    System.out.println("Deseja Vontalariar em outro projeto? (S/N)");
                    String opcaoVol = sc.nextLine();
                    if(opcaoVol.equalsIgnoreCase("S")){
                        System.out.println("Qual o nome do voluntario?");
                        System.out.println("Nome do Voluntario:");
                        String voluntarioNome = sc.nextLine();
                            for (Voluntario voluntario : listaVoluntarios) {
                                if(voluntario.getNome().equals(voluntarioNome)){
                                    voluntarioAdicionarProjetos(listaProjetos, voluntario, continuar);
                                }
                            break;
                        }
                    }
                    break;

                case 6:
                    run = false;
                break;
                default:
                    System.out.println("Opção inválida");
                    break;
            }

        } while (run);

    }

    private static void voluntarioAdicionarProjetos(List<ProjetoSustentavel> listaProjetos, Voluntario volutarioTemp, boolean continuar){
        Scanner sc = new Scanner(System.in);
        do {
            int idProjeto = sc.nextInt();
            sc.nextLine();
            if(idProjeto < 0 || idProjeto > listaProjetos.size()){
                System.out.println("Opção inválida");
                return;
            }
            listaProjetos.get(idProjeto).adicionarVoluntario(volutarioTemp);
            System.out.println("Voluntario adicionado ao projeto: " + listaProjetos.get(idProjeto).getName());

            if(listaProjetos.size() < 2) break;

            System.out.println("Deseja adicionar o voluntario a outro projeto? (S/N)");
            String opcao2 = sc.nextLine();
            if(opcao2.equalsIgnoreCase("S")){
                continuar = true;
            } else if(opcao2.equalsIgnoreCase("N")){
                continuar = false;
            }
        } while (continuar);
    }

    private static void relatorioImpactoProjeto(ProjetoSustentavel projeto){
        Scanner sc = new Scanner(System.in);
        System.out.println("Arvores Plantadas");
        int arvoresPlantadas = sc.nextInt();
        System.out.println("Redução de CO2 (Toneladas)");
        double reducaoCO2 = sc.nextDouble();
        projeto.gerarRelatorioImpacto(arvoresPlantadas, reducaoCO2);
        projeto.exibirRelatorio();
    }

    private static Empresa cadastroEmpresa(){
        Scanner sc = new Scanner(System.in);

        System.out.println("Empresa Responsavel:");
        String nomeEmpresa = sc.nextLine();
        System.out.println("CNPJ da Epresa Responsavel::");
        String cnpjEmpresa = sc.nextLine();
        return new Empresa(nomeEmpresa, cnpjEmpresa);
    }

    private static ONG cadastroOng(){
        Scanner sc = new Scanner(System.in);
        System.out.println("ONG Responsavel");
        return new ONG(sc.nextLine());
    }
}