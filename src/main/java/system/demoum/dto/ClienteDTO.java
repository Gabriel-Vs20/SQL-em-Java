package system.demoum.dto;

public class ClienteDTO {

    private String endereco;

    public ClienteDTO(String endereco) {

        this.endereco = endereco;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}
