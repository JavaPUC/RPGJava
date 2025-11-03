public class SpelllistGue{
    private SpellGue[] spells ;

    public SpelllistGue(){
        spells = new SpellGue[3];
        spells[0] = new SpellGue("Gritar",
                "Ganha 2d12 de Rage",
                50,
                2,
                12);
    }
    public SpellGue[] getSpellsGue() {
        return spells;
    }

    public void setSpells(SpellGue[] spells) {
        this.spells = spells;
    }
}