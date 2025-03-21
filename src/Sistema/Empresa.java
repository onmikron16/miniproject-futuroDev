package Sistema;

public class Empresa extends Organizacao {

    private String cnpj, name;
    public Empresa(String name, String cnpj) {
        super(name);
        this.cnpj = cnpj;
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
