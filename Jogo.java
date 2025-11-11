import java.util.Random;
import java.util.Scanner;

public class Jogo {
    private int classe;
    private Personagem jogador;
    private Random random;
    private Scanner scanner;
    private Inimigo[] inimigos;
    boolean canFightBoss = false;
    private int ato = 1;

    /* predef items */
    Item pocaoHp = new Item(1, "Poção de Vida", "Restaura 50 pontos de vida.", "restoreHp", 5);
    Item pocaoMana = new Item(2, "Poção de Mana", "Restaura 50 pontos de mana.", "restoreMana", 5);
    Item moedaOuro = new Item(3, "Moeda de Ouro", "Uma moeda brilhante de ouro.", "money", 100);

    public int getClasse() {
        return classe;
    }

    public void setClasse(int classe) {
        this.classe = classe;
    }

    public Personagem getJogador() {
        return jogador;
    }

    public void setJogador(Personagem jogador) {
        this.jogador = jogador;
    }

    public Jogo() {

        /* predef enemies */
        this.inimigos = new Inimigo[20];
        this.inimigos[0] = new Inimigo(1, "Goblin", 20, 4, 2, 1);
        this.inimigos[1] = new Inimigo(2, "Globin", 9999, 9999, 9999, 100);
        this.inimigos[2] = new Inimigo(3, "Vampiro", 50, 8, 4, 2);
        this.inimigos[3] = new Inimigo(4, "Caralho velho Gigante", 40, 6, 3, 2);
        this.inimigos[4] = new Inimigo(5, "Falcão-peregrino", 30, 7, 2, 2);
        this.inimigos[5] = new Inimigo(6, "Balança Rabo-leitoso Gigante", 80, 8, 8, 3);
        this.inimigos[6] = new Inimigo(7, "Camponês Revoltado", 25, 5, 3, 1);
        this.inimigos[7] = new Inimigo(8, "Cultista", 60, 12, 5, 3);

        this.random = new Random();
        this.scanner = new Scanner(System.in);

        boolean escolhaValida = false;
        while (!escolhaValida) {
            System.out.println("Escolha um nome:");
            String nome = scanner.nextLine();
            System.out.println("Escolha sua classe:");
            System.out.println("[1] Mago");
            System.out.println("[2] Arqueiro");
            System.out.println("[3] Guerreiro");

            this.classe = scanner.nextInt();
            scanner.nextLine();

            switch (classe) {
                case 1:
                    setJogador(new Mago(nome));
                    System.out.println("Você escolheu - Mago.");
                    System.out.println("Status inicial:");
                    System.out.println("HP: " + jogador.getHp() + "/" + jogador.getMaxHp());
                    System.out.println("Mana: " + ((Mago) jogador).getMana());
                    System.out.println("Atk: " + jogador.getAtk());
                    System.out.println("Def: " + jogador.getDef());
                    escolhaValida = true;
                    break;
                case 2:
                    setJogador(new Arqueiro(nome));
                    System.out.println("Você escolheu - Arqueiro.");
                    escolhaValida = true;
                    break;
                case 3:
                    setJogador(new Guerreiro(nome));
                    System.out.println("Você escolheu - Guerreiro.");
                    escolhaValida = true;
                    break;
                default:
                    System.out.println("Escolha inválida. Tente novamente.");
                    break;
            }
        }
        jogador.addToInv(pocaoHp);
        jogador.addToInv(pocaoMana);
        jogador.addToInv(moedaOuro);
    }

    public void iniciar() {
        int eventCount = 0;
        System.out.println("Bem-vindo ao RPG!");
        boolean jogando = true;

        while (jogando) {

            System.out.println("\nO que você deseja fazer?");
            System.out.println("[1] Explorar");
            System.out.println("[2] Usar item");
            System.out.println("[3] Verificar status");
            System.out.println("[4] Prosseguir com a história");
            System.out.println("[0] Sair do jogo");

            int escolha = scanner.nextInt();
            scanner.nextLine();

            switch (escolha) {
                case 0:
                    jogando = false;
                    System.out.println("Saindo do jogo. Até a próxima!");
                    break;
                case 1:
                    eventCount++;
                    if (eventCount == 30) {
                        canFightBoss = true;
                    }
                    explorar();
                    break;
                case 2:
                    usarItem();
                case 3:
                    status();
                    break;
                case 4:
                    
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    

    private void explorar() {

        System.out.println("Você está explorando...");
        if (canFightBoss) {
            this.inimigos[8] = new Inimigo(9, "???", 30, 5, 5, jogador.getLvl() + 2);
            Inimigo boss = this.inimigos[8];
            for (int i = 0; i < (jogador.getLvl() + 2); i++) {
                boss.lvlUp();
            }
            batalhar(boss);
        }
        int evento = random.nextInt(1000);
        if (evento == 1) {
            System.out.println("Você encontrou um Globin!");
            batalhar(inimigos[1]);
            return;
        }

        evento = random.nextInt(10);
        switch (evento) {
            case 0:
                int index = random.nextInt(8);

                System.out.println("Você encontrou um inimigo!");
                batalhar(inimigos[index]);
                break;
            case 1:
                System.out.println("Você caiu em uma armadilha! Que pena.");
                jogador.setHp(jogador.getHp() - 10);
                System.out.println("Você perdeu 10 de HP e agora tem " + jogador.getHp() + " de HP.");
                break;
            case 2:
                System.out.println("Nada aconteceu.");
                break;
            case 3:
                System.out.println("Você encontrou uma bolsa! Quer abrir?");
                System.out.println("[1] Sim" + "\n[2] Não");
                int abrir = scanner.nextInt();
                if (abrir == 1) {
                    System.out.println("Você abriu a bolsa e encontrou uma armadilha! Você perdeu 5 de HP.");
                    jogador.setHp(jogador.getHp() - 5);
                    System.out.println("Agora você tem " + jogador.getHp() + " de HP.");
                } else {
                    System.out.println("Você decidiu não abrir a bolsa.");
                }
                break;
            case 4:
                System.out.println("Você encontrou uma bolsa! Quer abrir?");
                System.out.println("[1] Sim" + "\n[2] Não");
                int abrir2 = scanner.nextInt();
                if (abrir2 == 1) {
                    System.out.println("Você abriu a bolsa e encontrou 100 moedas de ouro!");
                    jogador.addToInv(moedaOuro);
                } else {
                    System.out.println("Você decidiu não abrir a bolsa.");
                }
                break;
            case 5:
                System.out.println("Você encontrou uma bolsa! Quer abrir?");
                System.out.println("[1] Sim" + "\n[2] Não");
                int abrir4 = scanner.nextInt();
                if (abrir4 == 1) {
                    System.out.println("Você abriu a bolsa e encontrou uma poção de vida e 50 moedas de ouro!");
                    jogador.addToInv(pocaoHp);
                    Item moedaOuro50 = new Item(3, "Moeda de Ouro", "Uma moeda brilhante de ouro.", "money", 50);
                    jogador.addToInv(moedaOuro50);
                } else {
                    System.out.println("Você decidiu não abrir a bolsa.");
                }
                break;
            case 6:
                tomarDecisao(); // a falsa ilusão da livre escolha
                break;
        }
    }

    private boolean fugir() {
        System.out.println("Tentando fugir...");
        int chance = random.nextInt(100);
        if (chance < 50) {
            System.out.println("Você conseguiu fugir.");
            return true;
        } else {
            System.out.println("Você falhou.");
            return false;
        }
    }

    private void tomarDecisao() {
        System.out.println("Você encontrou uma bifurcação. Escolha um caminho:");
        System.out.println("1. Caminho da esquerda");
        System.out.println("2. Caminho da direita");

        int escolha = scanner.nextInt();
        scanner.nextLine();

        if (escolha == 1) {
            System.out.println("Você escolheu o caminho da esquerda. Algo interessante acontece...");
        } else if (escolha == 2) {
            System.out.println("Você escolheu o caminho da direita. Algo perigoso acontece...");
        } else {
            System.out.println("Escolha inválida.");
        }
    }

    private void status() {
        System.out.println("Status do Jogador:");
        System.out.println("Nome: " + jogador.getNome());
        System.out.println("Nível: " + jogador.getLvl());
        System.out.println("XP: " + jogador.getXp() + "/100");
        System.out.println("HP: " + jogador.getHp() + "/" + jogador.getMaxHp());
        System.out.println("Atk: " + jogador.getAtk());
        System.out.println("Def: " + jogador.getDef());
        System.out.println("Mana: " + jogador.getMana());
    }

    private void usarItem() {
        System.out.println("Inventário:");
        jogador.getInventario().lista();
        System.out.println("Digite a posição do item que deseja usar:");
        int itemPos = scanner.nextInt();
        jogador.getInventario().useItem(jogador, itemPos);
    }

    public void textoAto(int ato ) {
        if (ato == 1) {
            System.out.println("Placeholder");
        } else if (ato == 2) {
            System.out.println("Placeholder ato 2");
        } else {
            System.out.println("Placeholder ato 3");
        }
    }

    public void batalhar(Inimigo inimigo) {
        if (inimigo.getHp() <= 0) {
            System.out.println(inimigo.getNome() + " já está derrotado.");
            return;
        }

        boolean acaoLista = false;
        boolean fugiu = false;
        inimigo.setHp(inimigo.getMaxHp());
        System.out.println("Iniciando batalha!");
        System.out.println("============================================================================");
        if (this.classe == 1) {
            System.out.println("||HP - " + this.jogador.getNome() + ": " + jogador.getHp() + " | HP - "
                    + inimigo.getNome() + ": " + inimigo.getHp() + " || ");
            System.out.println("||Mana -" + this.jogador.getNome() + ": " + ((Mago) jogador).getMana());
        } else if (this.classe == 3) {
            System.out
                    .println("||HP - " + this.jogador.getNome() + ": " + jogador.getHp() + " | HP - "
                            + inimigo.getNome() + ": " + inimigo.getHp() + " || ");
            System.out.println("||Raiva -" + this.jogador.getNome() + ": " + ((Guerreiro) jogador).getRage());
            System.out.println("||Mana -" + this.jogador.getNome() + ": " + ((Guerreiro) jogador).getMana());
        } else {
            System.out.println("||HP Jogador: " + jogador.getHp() + " | HP - " + inimigo.getNome() + ": "
                    + inimigo.getHp() + " || ");
        }
        System.out.println("============================================================================");
        boolean turnoJogador = true;
        while (inimigo.getHp() > 0 && jogador.getHp() > 0 && fugiu == false) {
            if (turnoJogador) {
                acaoLista = false;
                if (this.classe == 1) {
                    System.out.println("Escolha sua ação:");
                    System.out.println("[1] Ataque físico");
                    System.out.println("[2] Usar magia");
                    System.out.println("[3] Listar magias");
                    System.out.println("[4] Usar item");
                    System.out.println("[5] Tentar fugir");
                    int acao = scanner.nextInt();
                    scanner.nextLine();
                    if (acao == 1) {
                        jogador.atacar(inimigo);
                    } else if (acao == 2) {
                        int op = scanner.nextInt();
                        ((Mago) jogador).habilidade(inimigo, op);
                    } else if (acao == 3) {
                        acaoLista = true;
                        ((Mago) jogador).listaSpell();
                    } else if (acao == 4) {
                        usarItem();
                        break;
                    } else if (acao == 5) {
                        fugiu = fugir();
                        inimigo.atacar(jogador);
                    } else {
                        System.out.println("Ação inválida. Você perde seu turno.");
                    }
                } else if (classe == 3) {
                    System.out.println("Escolha sua ação:");
                    System.out.println("[1] Ataque físico");
                    System.out.println("[2] Usar habilidade");
                    System.out.println("[3] Listar habilidades");
                    System.out.println("[4] Usar item");
                    System.out.println("[5] Tentar fugir");
                    int acao = scanner.nextInt();
                    scanner.nextLine();
                    if (acao == 1) {
                        jogador.atacar(inimigo);
                    } else if (acao == 2) {
                        int op = scanner.nextInt();
                        ((Guerreiro) jogador).habilidade(inimigo, op);
                    } else if (acao == 3) {
                        System.out.println("Listando habilidades...");
                        ((Guerreiro) jogador).listaSpellGue();
                        acaoLista = true;
                    }
                    else if (acao == 4) {
                        usarItem();
                        break;
                    } else if (acao == 5) {
                        fugiu = fugir();
                        inimigo.atacar(jogador);
                    } else {
                        System.out.println("Ação inválida. Você perde seu turno.");
                    }
                }

                System.out.println("============================================================================");
                if (this.classe == 1) {
                    System.out.println("||HP - " + this.jogador.getNome() + ": " + jogador.getHp() + " | HP - "
                            + inimigo.getNome() + ": " + inimigo.getHp() + " || ");
                    System.out.println("||Mana -" + this.jogador.getNome() + ": " + ((Mago) jogador).getMana());
                } else if (this.classe == 3) {
                    System.out
                            .println("||HP - " + this.jogador.getNome() + ": " + jogador.getHp() + " | HP - "
                                    + inimigo.getNome() + ": " + inimigo.getHp() + " || ");
                    System.out.println("||Raiva -" + this.jogador.getNome() + ": " + ((Guerreiro) jogador).getRage());
                    System.out.println("||Mana -" + this.jogador.getNome() + ": " + ((Guerreiro) jogador).getMana());
                }
                System.out.println("============================================================================");
                if (!acaoLista) {
                    turnoJogador = !turnoJogador;
                    acaoLista = false;
                }
            } else {
                int randChoice = random.nextInt(2);
                if (randChoice == 0) {
                    inimigo.atacar(jogador);
                } else {
                    inimigo.habilidade(jogador, 1);
                }
                turnoJogador = !turnoJogador;
            }

            System.out.println("============================================================================");
            if (this.classe == 1) {
                System.out.println("||HP - " + this.jogador.getNome() + ": " + jogador.getHp() + " | HP - "
                        + inimigo.getNome() + ": " + inimigo.getHp() + " || ");
                System.out.println("||Mana -" + this.jogador.getNome() + ": " + ((Mago) jogador).getMana());
            } else if (this.classe == 3) {
                System.out
                        .println("||HP - " + this.jogador.getNome() + ": " + jogador.getHp() + " | HP - "
                                + inimigo.getNome() + ": " + inimigo.getHp() + " || ");
                System.out.println("||Raiva -" + this.jogador.getNome() + ": " + ((Guerreiro) jogador).getRage());
                System.out.println("||Mana -" + this.jogador.getNome() + ": " + ((Guerreiro) jogador).getMana());
            }

            else {
                System.out.println("||HP Jogador: " + jogador.getHp() + " | HP - " + inimigo.getNome() + ": "
                        + inimigo.getHp() + " || ");
            }
            System.out.println("============================================================================");
        }

        if (jogador.getHp() <= 0) {
            System.out.println("Você morreu.");
            System.exit(0);
        } else if (fugiu) {
            System.out.println("Você fugiu da batalha.");
        } else {
            System.out.println("Inimigo derrotado!");
            jogador.setXp(inimigo.getLvl() * 20);
        }
    }

}