
public class Inimigo extends Personagem {
    private int id;

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
        
    }

    /*
     * Goblin
     * Globin (reseta seu progresso)
     * Vampiro
     * Escaravelho gigante
     * Falcão-peregrino
     * Balança rabo-leitoso gigante
     * Camponês revoltado
     * Cultista
     * Boss ???
     */

    
}
