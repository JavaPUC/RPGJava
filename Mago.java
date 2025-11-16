public class Mago extends Personagem {
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
        dice.setSides(2);
        dano = dice.roll() + (this.getAtk()/2);
        alvo.setHp(alvo.getHp() - dano);
    System.out.println(alvo.getNome() + " recebeu " + String.format("%.2f", dano) + ".");
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
        System.out.println(this.nome + " lançou " + nome + ".");
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
                        this.nome + " recuperou " + manaRecuperada + " de mana e agora tem " + this.getMana()
                                + " de mana.");
                break;

            case "Perturbação da Alma":
                System.out.println(
                        this.nome + " lançou Perturbação da Alma, ignorando a defesa de " + alvo.getNome() + ".");
                dano = dice.roll() + atk;
                System.out.println(alvo.getNome() + " recebeu " + String.format("%.2f", dano) + " de dano.");
                alvo.setHp(alvo.getHp() - dano);
                break;

            case "Demônio Escarlate":

                boolean lowHp = this.getHp() <= this.getMaxHp() * 0.10;
                boolean meditated = medit;

                double danoBase = 0;
                if (lowHp && meditated) {
                    dice.setSides(spell.getSides2()); 
                    for (int i = 0; i < 4; i++)
                        danoBase += dice.roll();
                    dice.setSides(spell.getSides()); 
                    for (int i = 0; i < 4; i++)
                        danoBase += dice.roll();
                    dano = (danoBase + this.getAtk()) * 2; 
                    double cura = dano * 0.20;
                    this.setHp(Math.min(this.getHp() + cura, this.getMaxHp()));
                } else if (lowHp) {
                    dice.setSides(spell.getSides2());
                    for (int i = 0; i < 4; i++)
                        danoBase += dice.roll();
                    dano = danoBase + this.getAtk();
                    dano -= alvo.getDef();
                    if (dano < 0)
                        dano = 0;
                    double cura = dano * 0.20;
                    this.setHp(Math.min(this.getHp() + cura, this.getMaxHp()));
                } else if (meditated) {
                    dice.setSides(spell.getSides());
                    for (int i = 0; i < 4; i++)
                        danoBase += dice.roll();
                    dano = danoBase + this.getAtk();
                    dano -= alvo.getDef();
                    if (dano < 0)
                        dano = 0;
                } else {
                    dice.setSides(spell.getSides());
                    for (int i = 0; i < spell.getRolls(); i++)
                        danoBase += dice.roll();
                    dano = danoBase + this.getAtk();
                    dano -= alvo.getDef();
                    if (dano < 0)
                        dano = 0;
                }
                alvo.setHp(alvo.getHp() - dano);
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
                    System.out
                            .println(this.getNome() + " não tem mana suficiente para lançar " + spell.getNome() + ".");
                }
                break;
        }
    System.out.println("Causou " + String.format("%.2f", dano) + " de dano.");
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
        if (dano < 0) {
            dano = 0;
        }
        return dano;
    }

    public void setSides(Spell spell) {
        dice.setSides(spell.getSides());
    }

    @Override
    public void addToInv(Item item) {
        this.inventario.addItem(item);
    }
}
