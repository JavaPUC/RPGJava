public class Guerreiro extends Personagem{
    private int rage;
    private int maxRage;
    private double dano;
    private SpelllistGue habilidadeZinha;

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
    public SpellGue chooseSpellGue(int op) {
        return habilidadeZinha.getSpellsGue()[op];
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
    public void habilidade(Personagem alvo, int op){
        SpellGue spell = chooseSpellGue(op);
        String nome = spell.getNome();
        switch(nome){
            case "Gritar":
            dice.setSides(spell.getSides());
                for(int i = 0; i < 2; i++){
                    if(rageCheck()){
                        setRage(getRage() + dice.roll());
                        if(getRage() > 50){
                            setRage(50);
                        }
                        
                    }

                }
        }
    }
    
    @Override
    public void addToInv(Item item) {
        this.inventario.addItem(item);
    }

    @Override
    public void lvlUp() {
        this.lvl += 1;
        this.hp += this.hp * this.multLvlUp;
        this.atk += this.atk * this.multLvlUp;
        this.def += this.def * this.multLvlUp;
        this.mana += this.mana * this.multLvlUp;
        System.out.println(this.nome + " subiu para o nível " + this.lvl + "!");
        System.out.println(
                "Status - Hp: " + this.hp + "\nAtk: " + this.atk + "\nDef: " + this.def + "\nMana: " + this.mana);
    }
}
