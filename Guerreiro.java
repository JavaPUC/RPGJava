public class Guerreiro extends Personagem{
    private int rage;
    private int maxRage;
    private double dano;

    public int getRage() {
        return rage;
    }

    public void setRage(int rage) {
        this.rage = rage;
    }

    public int getMaxRage() {
        return maxRage;
    }

    public void setMaxRage(int maxRage) {
        this.maxRage = maxRage;
    }

    public Guerreiro(String nome) {
        setNome(nome);
        setHp(200);
        setMaxHp(200);
        setAtk(12);
        setDef(20);
        setXp(0);
        setLvl(1);
        setMana(100);
        setRage(0);
        setMaxRage(50);
    }
    public boolean rageCheck() {
        if (getRage() == getMaxRage()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void atacar(Personagem alvo) {
        
        System.out.println(this.nome + " ataca" + alvo.getNome() + "com um ataque corpo-a-corpo.");
        dice.setSides(4);
        dano = dice.roll() + this.getAtk() + getRage();
        alvo.setHp(alvo.getHp() - dano);
        System.out.println(alvo.getNome() + " recebeu " + dano + ".");
        if(rageCheck()){
            setRage(getRage() + 5) ;
        }
        else{
             System.out.println("Rage maxima atingida");
            }
        
    }
}
