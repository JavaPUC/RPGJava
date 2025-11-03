public class Mago extends Personagem {
    private int mana;
    private double dano;
    private Spelllist grimorio;
    boolean medit = false;

    public Mago(String nome) {
        setNome(nome);
        setHp(100);
        setMaxHp(100);
        setAtk(6);
        setDef(10);
        setXp(0);
        setLvl(1);
        setMana(200);
        this.multLvlUp = 1.5;
        this.grimorio = new Spelllist();
        this.inventario = new Inventario();

    }

    @Override
    public void atacar(Personagem alvo) {
        System.out.println(this.nome + " ataca" + alvo.getNome() + "com um ataque corpo-a-corpo.");
        dice.setSides(4);
        dano = dice.roll() + this.getAtk();
        alvo.setHp(alvo.getHp() - dano);
        System.out.println(alvo.getNome() + " recebeu " + dano + ".");
    }

    @Override
    public void habilidade(Personagem alvo, int op) {
        dano = 0;
        System.out.println(this.nome + " está atacando " + alvo.getNome() + " com magia.");
        Spell spell = chooseSpell(op);
        String nome = spell.getNome();
        int rolls = spell.getRolls();
        
        System.out.println(this.nome + " lançou " + nome + ".");
        if (!verifMana(spell)) {
            System.out.println("Sem mana para castar o feitiço.");
            return;
        }
        switch (nome) {
            case "Meditação":
                medit = true;
                System.out.println(this.nome + " está meditando para recuperar mana.");
                setSides(spell);
                int manaRecuperada = 0;
                for (int i = 0; i < 4; i++) {
                    manaRecuperada += dice.roll();
                    this.setMana(getMana() + manaRecuperada);
                }
                
                System.out.println(
                        this.nome + " recuperou " + manaRecuperada + " de mana e agora tem " + this.getMana() + " de mana.");
                break;

            case "Perturbação da Alma":
                System.out.println(
                        this.nome + " lançou Perturbação da Alma, ignorando a defesa de " + alvo.getNome() + ".");
                dano = dice.roll() + atk;
                System.out.println(alvo.getNome() + " recebeu " + dano + " de dano.");
                alvo.setHp(alvo.getHp() - dano);
                break;

            case "Demônio Escarlate":
                if (this.getHp() < this.getMaxHp()* 0.1 && medit) {
                    rolls = 4;
                    dano = rollDie(spell, rolls) + atk;
                    alvo.setHp(alvo.getHp() - dano);
                } else if (this.getHp() < this.getHp() * 0.1 && !medit) {
                    dice.setSides(spell.getSides2());
                    rolls = 4;
                    for (int i = 0; i < rolls; i++) {
                        dano += dice.roll();
                    }
                    dano += atk;
                    dano -= alvo.getDef();
                    this.setHp(dano * 0.2);
                    alvo.setHp(alvo.getHp() - dano);
                } else {
                    if (verifMana(spell)) {
                        dano = calcDmgDef(spell, rolls, alvo);
                        alvo.setHp(alvo.getHp() - dano);
                    }
                }
                break;

            case "Barragem de Esmeraldas":
                if (this.getHp() < this.getMaxHp() * 0.05) {
                    dano = rollDie(spell, rolls);
                    dano = dano * 4;
                    dano -= alvo.getDef();
                    alvo.setHp(alvo.getHp() - dano);
                    if (alvo.getHp() <= 0) {
                        this.setHp(1);
                    } else {
                        this.setHp(0);
                    }
                } else {
                    dano = calcDmgDef(spell, rolls, alvo);
                    alvo.setHp(alvo.getHp() - dano);
                }
                break;

            case "Pandemonium":
                dice.setSides(20);
                int dc = (int) rollDie(spell, rolls);
                if (dc < 14) {
                    rolls = 3;
                    dano = calcDmgDef(spell, rolls, alvo);
                    alvo.setHp(alvo.getHp() - dano);
                } else {
                    dano = calcDmgDef(spell, rolls, alvo);
                    alvo.setHp(alvo.getHp() - dano);
                }
                break;

            case "Catapulta":
                if (!this.inventario.isEmpty()) {
                    int randomIndex = (int) (Math.random() * this.inventario.getItems().length);
                    Item randomItem = this.inventario.getItem(randomIndex, inventario.getItems());
                    this.inventario.removeItem(randomItem);
                    System.out.println(
                            this.nome + " usou Catapulta e removeu " + randomItem.getNome() + " do inventário.");
                    dano = calcDmgDef(spell, rolls, alvo);
                    alvo.setHp(alvo.getHp() - dano);
                } else {
                    System.out.println(this.nome + " tentou usar Catapulta, mas o inventário está vazio.");
                }
                break;

            case "Heresia":
                dano = rollDie(spell, rolls);
                alvo.setHp(alvo.getHp() - dano);
                break;

            case "Rosso Phantasma":
                int clones = (int) rollDie(spell, rolls);
                for (int i = 0; i < clones; i++) {
                    dano += calcDmgDef(spell, rolls, alvo);
                }
                alvo.setHp(alvo.getHp() - dano);
                break;

            default:
                if (verifMana(spell)) {
                    setSides(spell);
                    dano = calcDmgDef(spell, rolls, alvo);
                    
                    alvo.setHp(alvo.getHp() - dano);
                } else {
                    System.out.println(this.nome + " não tem mana suficiente para lançar " + spell.getNome() + ".");
                }
                break;
        }
        System.out.println("Causou " + dano + " de dano.");
    }

    public void listaSpell() {
        System.out.println("Escolha um ataque:");
        for (int i = 0; i < grimorio.getSpells().length; i++) {
            System.out.println("[" + i + "] " + grimorio.getSpells()[i].getNome() +
                    "\n Descrição: " + grimorio.getSpells()[i].getDescricao() +
                    "\n Custo de Mana: " + grimorio.getSpells()[i].getCustoMana() +
                    ")");
        }

    }

    public Spell chooseSpell(int op) {
        return grimorio.getSpells()[op];
    }

    public boolean verifMana(Spell spell) {
        if (getMana() >= spell.getCustoMana()) {
            setMana(getMana() - spell.getCustoMana());
            return true;
        } else {
            return false;
        }
    }

    public double rollDie(Spell spell, int rolls) {
        double total = 0;
        dice.setSides(spell.getSides());
        for (int i = 0; i < rolls; i++) {
            total += dice.roll();
        }
        return total;
    }

    public double calcDmgDef(Spell spell, int rolls, Personagem alvo) {
        dano = rollDie(spell, rolls) + this.getAtk();
        dano = dano - alvo.getDef();
        return dano;
    }

    public void setSides(Spell spell) {
        dice.setSides(spell.getSides());
    }

    @Override
    public void addToInv(Item item) {
        this.inventario.addItem(item);
    }

    @Override
    public void lvlUp() {
        this.lvl += 1;
        this.hp += this.hp * this.multLvlUp;
        this.atk += this.atk * this.multLvlUp;
        this.def += this.def * this.multLvlUp;
        this.mana += this.mana * this.multLvlUp;
        System.out.println(this.nome + " subiu para o nível " + this.lvl + "!");
        System.out.println(
                "Status - Hp: " + this.hp + "\nAtk: " + this.atk + "\nDef: " + this.def + "\nMana: " + this.mana);
    }
}
