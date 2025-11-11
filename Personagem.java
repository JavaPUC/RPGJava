public abstract class Personagem {
    protected String nome;
    protected double hp;
    protected double maxHp;
    protected double atk;
    protected double def;
    protected int xp;
    protected int lvl; //100 xp = 1 lvl, aumento de 25% a cada lvl.
    protected int mana;
    protected double multLvlUp; //multiplicador de aumento de status a cada lvl
    protected Dice dice = new Dice(4);
    protected Inventario inventario;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getHp() {
        return hp;
    }

    public void setHp(double hp) {
        this.hp = hp;
    }

    public double getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(double maxHp) {
        this.maxHp = maxHp;
    }

    public double getAtk() {
        return atk;
    }

    public void setAtk(double atk) {
        this.atk = atk;
    }

    public double getDef() {
        return def;
    }

    public void setDef(double def) {
        this.def = def;
    }

    public int getXp() {
        
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
        if (this.xp >= 100) {
            this.xp -= 100;
            this.lvlUp();
        }
    }

    public int getLvl() {
        return lvl;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }

    public Inventario getInventario() {
        return inventario;
    }

    public void setInventario(Inventario inventario) {
        this.inventario = inventario;
    }

    public Dice getDice() {
        return dice;
    }

    public void setDice(Dice dice) {
        this.dice = dice;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public void lvlUp() {
        this.lvl += 1;
        this.setHp(this.getHp() * this.multLvlUp);
        this.setMaxHp(this.getMaxHp() * this.multLvlUp);
        this.setAtk(this.getAtk() * this.multLvlUp);
        this.setDef(this.getDef() * this.multLvlUp);
        this.setMana((int)(Math.floor(this.getMana() * this.multLvlUp)));
        System.out.println(this.nome + " subiu para o nível " + this.lvl + "!");
        System.out.println(
                "Status - Hp: " + this.getHp() + "\nAtk: " + this.getAtk() + "\nDef: " + this.getDef() + "\nMana: " + this.getMana());
    }
    public abstract void habilidade(Personagem alvo, int op); //interação com op para ataques que usam magia ou habilidades especiais.
    public abstract void atacar(Personagem alvo); //ataque físico padrão.
    public abstract void addToInv(Item item);


}


