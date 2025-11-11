public class Arqueiro extends Personagem{

    private double dano;




    public double getDano() {
        return dano;
    }
    public void setDano(double dano) {
        this.dano = dano;
    }
    public Arqueiro(String nome) {
        setNome(nome);
        setHp(200);
        setMaxHp(200);
        setAtk(12);
        setDef(20);
        setXp(0);
        setLvl(1);
        setMana(100);
    }
    
}
