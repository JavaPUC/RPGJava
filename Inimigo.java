
public class Inimigo extends Personagem {
    private int id;
    double dano;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public Inimigo(int id, String nome, double hp, double atk, double def, int lvl) {
        setId(id);
        setNome(nome);
        setHp(hp);
        setMaxHp(hp);
        setAtk(atk);
        setDef(def);
        setXp(0);
        setLvl(lvl);
        this.multLvlUp = 1.5;
        
    }

    @Override
    public void atacar(Personagem alvo) {
        System.out.println(this.getNome() + " ataca " + alvo.getNome() + " com um ataque corpo-a-corpo.");
        dice.setSides(4);
        dano = dice.roll() + this.getAtk();
        dano = dano - alvo.getDef();
        if (dano < 0) {
            dano = 0;
        }
        alvo.setHp(alvo.getHp() - dano);
    System.out.println(alvo.getNome() + " recebeu " + String.format("%.2f", dano) + ".");
    }

    @Override
    public void addToInv(Item item) {
        this.inventario.addItem(item);
    }

    @Override
    public void habilidade(Personagem alvo, int op) {
        op = 1;
        System.out.println(this.getNome() + " ataca" + alvo.getNome() + " com um ataque mais forte.");
        dice.setSides(6);
        dano = dice.roll() + this.getAtk();
        dano = dano + (dice.roll() + this.getAtk());
         dano = dano - alvo.getDef();
        if (dano < 0) {
            dano = 0;
        }
        alvo.setHp(alvo.getHp() - dano);
    System.out.println(alvo.getNome() + " recebeu " + String.format("%.2f", dano) + ".");
    }

    @Override
    public void lvlUp() {
        this.lvl += 1;
        this.setHp(this.getHp() * this.multLvlUp);
        this.setMaxHp(this.getHp());
        this.setAtk(this.getAtk() * this.multLvlUp);
        this.setMana((int)(Math.floor(this.getMana() * this.multLvlUp)));
    }

    /*
     * Goblin
     * Globin (reseta seu progresso)
     * Vampiro
     * caralho, velho gigante
     * Falcão-peregrino
     * Balança rabo-leitoso gigante
     * Camponês revoltado
     * Cultista
     * Boss ???
     */

    
}
