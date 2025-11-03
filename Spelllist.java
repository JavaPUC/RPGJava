public class Spelllist {
    private Spell[] spells;

    public Spelllist() {
        spells = new Spell[11];
        spells[0] = new Spell("Bola de Fogo",
                "Dano: 1d6 + atk",
                50,
                1,
                6);

        spells[1] = new Spell("Bola de Fogo Negro",
                "Dano: 1d20 + atk.",
                100,
                1,
                20);

        spells[2] = new Spell("Perturbação da Alma",
                "Dano: 1d12 + atk. Ignora defesa.",
                120,
                1,
                12);

        spells[3] = new Spell("Meditação",
                "Recupera 4d20 de mana.", 
                -1,
                4,
                20);

        spells[4] = new Spell("Enxurrada de Borboletas", // seele mencionada
                "Dano: 2d8 + atk.",
                120,
                2,
                8);

        spells[5] = new Spell("Barragem de Esmeraldas",
                "Dano: 3d10 + atk. Com vida abaixo de 5%, causa 4x dano e deixa o usuário com 1 de vida." +
                " Caso o alvo não morra com este ataque, o usuário tem seu HP reduzido à 0." + 
                "Talvez seu último ataque?",
                50,
                3,
                10);

        spells[6] = new Spell("Pandemonium",
                "Dano: 3d10 caso falhe o teste, 1d10 caso passe. Teste sem vantagem, 14DC.",
                150,
                1,
                20);

        spells[7] = new Spell("Demônio Escarlate",
                "Dano: 2d8 + atk.\n" +
                        "Caso a vida esteja abaixo de 10%, cura em 20% do dano causado e causa 4d12 + atk de dano.\n" +
                        "Caso o feitiço 'Meditação' tenha sido utilizado anteriormente, este causa 4d8 + atk de dano." +
                        "Caso as duas condições sejam atendidas, o dano será dobrado e ignorará a defesa do inimigo.",
                300,
                2,
                8,
                12);

        spells[8] = new Spell("Catapulta",
                "Dano: 2d6 + atk. Pega um objeto do seu inventário e o arremessa no inimigo utilizando magia.",
                40,
                2,
                6);

        spells[9] = new Spell("Heresia", "Atormenta a cabeça do alvo com gritos de almas pecadoras," +
                "causando 2d6 de dano e ignorando sua defesa.",
                100,
                2,
                6);
        
        spells[10] = new Spell("Rosso Phantasma", 
        "Cria 1d6 clones do usuário que avançarão em direção ao inimigo, causando 1d6 + atk de dano cada.", 
        80, 
        1,
        6
        );
    }

    public Spell[] getSpells() {
        return spells;
    }

    public void setSpells(Spell[] spells) {
        this.spells = spells;
    }
    

}
