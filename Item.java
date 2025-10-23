public class Item{
    private int id;
    private String nome;
    private String descricao;
    private String effect;
    private int qtd;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public void qtdUp() {
        setQtd(getQtd() + 1);
    }

    public void qtdDown() {
        setQtd(getQtd() - 1);
    }
    public Item(int id, String nome, String descricao, String effect, int qtd) {
        setId(id);
        setNome(nome);
        setDescricao(descricao);
        setEffect(effect);
        setQtd(qtd);
    }

    public void ballsofsteel() {
        System.out.println("Balls of Steel");
    }


    @Override 
    public int hashCode() {
        return id;
    }
}
