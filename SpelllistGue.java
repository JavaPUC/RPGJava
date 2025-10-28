public class SpelllistGue{
    private SpellGue[] spells ;

    public SpelllistGue(){
        spells = new SpellGue[3];
        spells[0] = new SpellGue("Bola de Fogo",
                "Dano: 1d6 + atk",
                50,
                1);
    }
}