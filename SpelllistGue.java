public class SpelllistGue{
    private SpellGue[] spells ;

    public SpelllistGue(){
        spells = new SpellGue[3];
        spells[0] = new SpellGue("Gritar",
                "Ganha 2d12 de Rage",
                50,
                2,
                12);
        spells[1] = new SpellGue("Batida Enfurecida",
        "Causa 2d10 de dano + a sua Rage e a consome",
        50,
        2,
        10);
    
        spells[2] = new SpellGue("JOGAR",
        "Dano: 2d12 + atk + Rage. JOGA UM ITEM.",
        40,
        2,
        6);
    }
    public SpellGue[] getSpellsGue() {
        return spells;
    }

    public void setSpells(SpellGue[] spells) {
        this.spells = spells;
    }
}