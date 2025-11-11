public class SpellArq {
    private String nome;
    private String descricao;
    private int custoMana;
    private int rolls;
    private int sides;

    public SpellArq(String nome, String descricao, int custoMana, int rolls, int sides) {
        this.nome = nome;
        this.descricao = descricao;
        this.custoMana = custoMana;
        this.rolls = rolls;
        this.sides = sides;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getCustoMana() {
        return custoMana;
    }

    public int getRolls() {
        return rolls;
    }

    public int getSides() {
        return sides;
    }
}
