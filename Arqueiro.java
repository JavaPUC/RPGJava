public class Arqueiro extends Personagem {
    private int foco;
    private int maxFoco;
    private double dano;
    private SpelllistArq habilidades;

    public Arqueiro(String nome) {
        setNome(nome);
        setHp(140);
        setMaxHp(140);
        setAtk(18);
        setDef(10);
        setXp(0);
        setLvl(1);
        setMana(120);
        setFoco(0);
        setMaxFoco(100);
        this.multLvlUp = 1.25;
        habilidades = new SpelllistArq();
    }

    public int getFoco() {
        return foco;
    }

    public void setFoco(int foco) {
        this.foco = foco;
        if (this.foco > this.maxFoco) this.foco = this.maxFoco;
    }

    public int getMaxFoco() {
        return maxFoco;
    }

    public void setMaxFoco(int maxFoco) {
        this.maxFoco = maxFoco;
    }

    public SpellArq chooseSpell(int op) {
        return habilidades.getSpellsArq()[op];
    }

    @Override
    public void atacar(Personagem alvo) {
        System.out.println(this.nome + " dispara uma flecha em " + alvo.getNome() + ".");
        dice.setSides(6);
        dano = dice.roll() + this.getAtk() + (this.foco * 0.1);
        alvo.setHp(alvo.getHp() - dano);
        System.out.println(alvo.getNome() + " recebeu " + dano + " de dano à distância!");
        setFoco(this.foco + 10);
    }

    @Override
    public void habilidade(Personagem alvo, int op) {
        SpellArq spell = chooseSpell(op);
        String nome = spell.getNome();

        if (!verifMana(spell)) {
            System.out.println("Mana insuficiente para usar " + nome + "!");
            return;
        }

        System.out.println(this.nome + " usa " + nome + "!");
        dice.setSides(spell.getSides());

        switch (nome) {
            case "Tiro Preciso":
                dano = dice.roll() + this.getAtk() * 1.5 + (this.foco * 0.2);
                alvo.setHp(alvo.getHp() - dano);
                System.out.println(alvo.getNome() + " sofreu " + dano + " de dano crítico!");
                setFoco(this.foco - 30);
                break;

            case "Rajada de Flechas":
                dano = 0;
                for (int i = 0; i < spell.getRolls(); i++) {
                    dano += dice.roll() + (this.getAtk() * 0.8);
                }
                alvo.setHp(alvo.getHp() - dano);
                System.out.println(this.nome + " dispara várias flechas causando " + dano + " de dano total!");
                setFoco(this.foco + 20);
                break;

            case "Armadilha Explosiva":
                dano = dice.roll() * 2 + this.getAtk();
                alvo.setHp(alvo.getHp() - dano);
                System.out.println("A armadilha explode! " + alvo.getNome() + " leva " + dano + " de dano e perde 10 de DEF temporariamente!");
                alvo.setDef(alvo.getDef() - 10);
                break;
        }
    }

    public boolean verifMana(SpellArq spell) {
        if (getMana() >= spell.getCustoMana()) {
            setMana(getMana() - spell.getCustoMana());
            return true;
        } else {
            return false;
        }
    }

    public void listaSpellArq() {
        System.out.println("Escolha uma habilidade:");
        for (int i = 0; i < habilidades.getSpellsArq().length; i++) {
            System.out.println("[" + i + "] " + habilidades.getSpellsArq()[i].getNome() +
                    "\nDescrição: " + habilidades.getSpellsArq()[i].getDescricao() +
                    "\nCusto de Mana: " + habilidades.getSpellsArq()[i].getCustoMana());
        }
    }

    @Override
    public void addToInv(Item item) {
        this.inventario.addItem(item);
    }

    @Override
    public void lvlUp() {
        this.lvl += 1;
        this.hp *= this.multLvlUp;
        this.atk *= this.multLvlUp;
        this.def *= this.multLvlUp;
        this.mana *= this.multLvlUp;
        System.out.println(this.nome + " subiu para o nível " + this.lvl + "!");
        System.out.println(
                "Status - Hp: " + this.hp + "\nAtk: " + this.atk + "\nDef: " + this.def + "\nMana: " + this.mana);
    }
}
