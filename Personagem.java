public abstract class Personagem {
    protected String nome;
    protected double hp;
    protected double atk;
    protected double def;
    protected int xp;
    protected int lvl; //100 xp = 1 lvl, aumento de 25% a cada lvl.
    protected double multLvlUp; //multiplicador de aumento de status a cada lvl
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

    public abstract void lvlUp();

    public abstract void atacar(Personagem alvo, int op); //interação com op para ataques que usam magia ou habilidades especiais.
    public abstract void atacar(Personagem alvo); //ataque físico padrão.
    public abstract void addToInv(Item item);


}


