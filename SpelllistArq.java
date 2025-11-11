public class SpelllistArq {
    private SpellArq[] spells;

    public SpelllistArq() {
        spells = new SpellArq[3];
        spells[0] = new SpellArq("Tiro Preciso",
                "Um disparo concentrado que causa grande dano crítico. Dano: 1d10 + 150% do ATK + 20% do Foco.",
                40,
                1,
                10);
        spells[1] = new SpellArq("Rajada de Flechas",
                "Dispara várias flechas consecutivas. Dano: 3d6 + 80% do ATK.",
                60,
                3,
                6);
        spells[2] = new SpellArq("Armadilha Explosiva",
                "Coloca uma armadilha que explode ao contato. Dano: 2d8 + ATK, reduz DEF inimiga.",
                50,
                2,
                8);
    }

    public SpellArq[] getSpellsArq() {
        return spells;
    }

    public void setSpellsArq(SpellArq[] spells) {
        this.spells = spells;
    }
}
