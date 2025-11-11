public class Guerreiro extends Personagem{
    private int rage;
    private int maxRage;
    private double dano;
    private SpelllistGue habilidadeZinha;

    public int getRage() {
        return rage;
    }

    public void setRage(int rage) {
        this.rage = rage;
    }

    public int getMaxRage() {
        return maxRage;
    }

    public void setMaxRage(int maxRage) {
        this.maxRage = maxRage;
    }

    public Guerreiro(String nome) {
        setNome(nome);
        setHp(200);
        setMaxHp(200);
        setAtk(12);
        setDef(20);
        setXp(0);
        setLvl(1);
        setMana(100);
        setRage(0);
        setMaxRage(50);
        habilidadeZinha = new SpelllistGue(); // Initialize habilidadeZinha
    }
    public boolean rageCheck() {
        if (getRage() == getMaxRage()) {
            return true;
        } else {
            return false;
        }
    }
    public SpellGue chooseSpellGue(int op) {
        return habilidadeZinha.getSpellsGue()[op];
    }
    @Override
    public void atacar(Personagem alvo) {
        
        System.out.println(this.nome + " ataca" + alvo.getNome() + "com um ataque corpo-a-corpo.");
        dice.setSides(4);
        dano = dice.roll() + this.getAtk() + getRage();
        alvo.setHp(alvo.getHp() - dano);
        System.out.println(alvo.getNome() + " recebeu " + dano + ".");
        if(rageCheck()){
            setRage(getRage() + 5) ;
        }
        else{
             System.out.println("Rage maxima atingida");
            }
    }
    public void habilidade(Personagem alvo, int op){
        SpellGue spell = chooseSpellGue(op);
        String nome = spell.getNome();
        System.out.println(this.nome + " lançou " + nome + ".");
        if (!verifMana(spell)) {
            System.out.println("Sem mana para castar o feitiço.");
            return;
        }
        switch(nome){
            case "Gritar":
            dice.setSides(spell.getSides());
                for(int i = 0; i < 2; i++){
                    if(rageCheck()){
                        setRage(getRage() + dice.roll());
                        if(getRage() > 50){
                            setRage(50);
                        }
                        
                    }

                }
            System.out.println(this.nome + " usou Gritar e agora tem " + this.getRage() + " de raiva.");
            break;

            case "Batida Enfurecida":
            dice.setSides(spell.getSides());
            System.out.println(this.nome + " está atacando " + alvo.getNome() + " com RAIVA.");
            dano = dice.roll() + atk + this.getRage();
            System.out.println(alvo.getNome() + " recebeu " + dano + " de dano.");
            alvo.setHp(alvo.getHp() - dano);
            this.setRage(0);
            break;
            
            case "JOGAR":
            if (!this.inventario.isEmpty()) {
                int rolls = spell.getRolls();
                int randomIndex = (int) (Math.random() * this.inventario.getItems().length);
                Item randomItem = this.inventario.getItem(randomIndex, inventario.getItems());
                this.inventario.removeItem(randomItem);
                System.out.println(
                        this.nome + " JOGOU e removeu " + randomItem.getNome() + " do inventário.");
                dano = calcDmgDef(spell, rolls, alvo);
                alvo.setHp(alvo.getHp() - dano);
            } else {
                System.out.println(this.nome + " tentou jogar mas nao tem o que jogar");
            }
            break;
            

        }
    }

    public boolean verifMana(SpellGue spell) {
        if (getMana() >= spell.getCustoMana()) {
            setMana(getMana() - spell.getCustoMana());
            return true;
        } else {
            return false;
        }
    }
    
    public void listaSpellGue() {
        System.out.println("Escolha um ataque:");
        for (int i = 0; i < habilidadeZinha.getSpellsGue().length; i++) {
            System.out.println("[" + i + "] " + habilidadeZinha.getSpellsGue()[i].getNome() +
                    "\n Descrição: " + habilidadeZinha.getSpellsGue()[i].getDescricao() +
                    "\n Custo de Mana: " + habilidadeZinha.getSpellsGue()[i].getCustoMana() +
                    ")");
        }
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
    public double rollDie(SpellGue spell, int rolls) {
        double total = 0;
        dice.setSides(spell.getSides());
        for (int i = 0; i < rolls; i++) {
            total += dice.roll();
        }
        return total;
    }

    public double calcDmgDef(SpellGue spell, int rolls, Personagem alvo) {
        dano = rollDie(spell, rolls) + this.getAtk() + this.getRage();
        dano = dano - alvo.getDef();
        return dano;
    }
}
