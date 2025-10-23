public class Spell {
    private String nome;
    private String descricao;
    private int custoMana;
    private int rolls;
    private int sides;
    private int sides2;
    Dice dice = new Dice();


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
    public int getCustoMana() {
        return custoMana;
    }
    public void setCustoMana(int custoMana) {
        this.custoMana = custoMana;
    }

    public int getRolls() {
        return rolls;
    }
    public void setRolls(int rolls) {
        this.rolls = rolls;
    }

    public int getSides() {
        return sides;
    }
    public void setSides(int sides) {
        this.sides = sides;
    }

    public int getSides2() {
        return sides2;
    }
    public void setSides2(int sides2) {
        this.sides2 = sides2;
    }

    public Spell(String nome, String descricao, int custoMana, int rolls, int sides) {
        setNome(nome);
        setDescricao(descricao);
        setCustoMana(custoMana);
        setRolls(rolls);
        setSides(sides);   
    }

    public Spell(String nome, String descricao, int custoMana, int rolls, int sides, int sides2) {
        setNome(nome);
        setDescricao(descricao);
        setCustoMana(custoMana);
        setRolls(rolls);
        setSides(sides);
        setSides2(sides2);
    }


    
}
