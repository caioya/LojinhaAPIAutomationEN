package pojo;

public class ComponentPojo {
    private String componenteNome;
    private int componenteQuantidade;

    public ComponentPojo(String componenteNome, int componenteQuantidade) {
        this.componenteNome = componenteNome;
        this.componenteQuantidade = componenteQuantidade;
    }

    public String getComponenteNome() {
        return componenteNome;
    }

    public void setComponenteNome(String componenteNome) {
        this.componenteNome = componenteNome;
    }

    public int getComponenteQuantidade() {
        return componenteQuantidade;
    }

    public void setComponenteQuantidade(int componenteQuantidade) {
        this.componenteQuantidade = componenteQuantidade;
    }

}
