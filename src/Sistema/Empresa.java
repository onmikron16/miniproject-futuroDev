package Sistema;

public class Empresa extends Organizacao {

    private String cnpj;

    public Empresa(String name, String cnpj) {
        super(name);
        this.cnpj = cnpj;
    }

}
