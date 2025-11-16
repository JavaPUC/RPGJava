import java.util.Random;
import java.util.Scanner;

public class Jogo {
    private int classe;
    private Personagem jogador;
    private Random random;
    private Scanner scanner;
    private Inimigo[] inimigos;
    boolean canFightBoss = false;
    private int ato = 0;

    private int cbt = 50;
    private int trap = 20;
    private int none = 20;
    private int decision = 10;

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
                    System.out.println(String.format("HP: %.2f/%.2f", jogador.getHp(), jogador.getMaxHp()));
                    System.out.println("Mana: " + ((Mago) jogador).getMana());
                    System.out.println(String.format("Atk: %.2f", jogador.getAtk()));
                    System.out.println(String.format("Def: %.2f", jogador.getDef()));
                    escolhaValida = true;
                    break;
                case 2:
                    setJogador(new Arqueiro(nome));
                    System.out.println("Você escolheu - Arqueiro.");
                    System.out.println("Status inicial:");
                    System.out.println(String.format("HP: %.2f/%.2f", jogador.getHp(), jogador.getMaxHp()));
                    System.out.println("Mana: " + jogador.getMana());
                    System.out.println(String.format("Atk: %.2f", jogador.getAtk()));
                    System.out.println(String.format("Def: %.2f", jogador.getDef()));
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

        int lvl = jogador.getLvl();
        this.inimigos = new Inimigo[20];
        this.inimigos[0] = new Inimigo(1, "Goblin", 20, 4, 2, 1);
        this.inimigos[1] = new Inimigo(2, "Globin", 9999, 9999, 9999, 100);
        this.inimigos[2] = new Inimigo(3, "Vampiro", 50, 8, 4, lvl + 3);
        this.inimigos[3] = new Inimigo(4, "Escaravelho Gigante", 40, 6, 3, lvl);
        this.inimigos[4] = new Inimigo(5, "Falcão-peregrino", 30, 7, 2, lvl + 2);
        this.inimigos[5] = new Inimigo(6, "Balança Rabo-leitoso Gigante", 80, 8, 8, lvl + 4);
        this.inimigos[6] = new Inimigo(7, "Camponês Revoltado", 25, 5, 3, lvl + 1);
        this.inimigos[7] = new Inimigo(8, "Cultista", 60, 12, 5, lvl + 5);
        this.inimigos[8] = new Inimigo(9, "Lich", 300, 25, 25, 10);

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
                    if (ato == 3) {
                        System.out.println("Você não pode explorar.");
                    } else {
                        explorar();
                    }
                    break;
                case 2:
                    usarItem();
                case 3:
                    status();
                    break;
                case 4:
                    prosseguirHistoria();
                    break;
            }
        }
    }

    private void explorar() {

        System.out.println("Você está explorando...");
        int evento = random.nextInt(1000000);
        if (evento == 1) {
            System.out.println("Você encontrou um Globin!");
            batalhar(inimigos[1]);
            return;
        }

        evento = random.nextInt(10);
        switch (evento) {

            case 3:
                System.out.println("Você encontrou uma bolsa! Quer abrir?");
                System.out.println("[1] Sim" + "\n[2] Não");
                int abrir = scanner.nextInt();
                if (abrir == 1) {
                    System.out.println("Você abriu a bolsa e encontrou uma armadilha! Você perdeu 5 de HP.");
                    jogador.setHp(jogador.getHp() - 5);
                    System.out.println("Agora você tem " + String.format("%.2f", jogador.getHp()) + " de HP.");
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
            default: {

                int subEvento = sorteiaSubEvento();
                switch (subEvento) {
                    case 0: {
                        int index = random.nextInt(8);
                        System.out.println("Você encontrou um inimigo!");
                        batalhar(inimigos[index]);
                        break;
                    }
                    case 1: {
                        System.out.println("Você caiu em uma armadilha! Que pena.");
                        jogador.setHp(jogador.getHp() - 10);
                        System.out.println("Você perdeu 10 de HP e agora tem " + String.format("%.2f", jogador.getHp()) + " de HP.");
                        break;
                    }
                    case 2: {
                        System.out.println("Nada aconteceu.");
                        break;
                    }
                    case 3: {
                        tomarDecisao(); // a falsa ilusão da livre escolha
                        break;
                    }
                }
                break;
            }
        }
    }

    private int sorteiaSubEvento() {
        int total = cbt + trap + none + decision;
        if (total <= 0) {

            return random.nextInt(4);
        }
        int r = random.nextInt(total);
        int acc = cbt;
        if (r < acc)
            return 0;
        acc += trap;
        if (r < acc)
            return 1;
        acc += none;
        if (r < acc)
            return 2;
        return 3;
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
            System.out.println("Você encontrou uma bolsa! Quer abrir?");
            System.out.println("[1] Sim" + "\n[2] Não");
            int abrir2 = scanner.nextInt();
            if (abrir2 == 1) {
                System.out.println("Você abriu a bolsa e encontrou 100 moedas de ouro!");
                jogador.addToInv(moedaOuro);
            } else {
                System.out.println("Você decidiu não abrir a bolsa.");
            }
        } else if (escolha == 2) {
            System.out.println("Você escolheu o caminho da direita. Algo perigoso acontece...");
            System.out.println("Você caiu em uma armadilha! Que pena.");
            jogador.setHp(jogador.getHp() - 10);
            System.out.println("Você perdeu 10 de HP e agora tem " + String.format("%.2f", jogador.getHp()) + " de HP.");
        } else {
            System.out.println("Escolha inválida.");
        }
    }

    private void prosseguirHistoria() {
        int proximoAto = ato + 1;

        switch (proximoAto) {
            case 1: {
                textoAto(1, false);
                boolean venceu = batalhar(inimigos[2]);
                if (venceu) {
                    textoAto(1, true);
                    ato = proximoAto;
                } else {
                    System.out.println("Você escapou da batalha. A história não progride.");
                }
                break;
            }

            case 2: {
                textoAto(2, false);
                boolean venceu = batalhar(inimigos[7]);
                if (venceu) {
                    textoAto(2, true);
                    ato = proximoAto;
                } else {
                    System.out.println("Você escapou da batalha. A história não progride.");
                }
                break;
            }

            case 3: {
                textoAto(3, false);
                boolean venceu = batalhar(inimigos[8]);
                if (venceu) {
                    textoAto(3, true);
                    System.out.println("\n=== FIM DO JOGO ===");
                    ato = proximoAto;
                    System.exit(0);
                } else {
                    System.out.println("Você escapou da batalha. A história não progride.");
                }
                break;
            }

            default:
                System.out.println("Você já completou toda a história!");
        }
    }

    private void status() {
        System.out.println("Status do Jogador:");
        System.out.println("Nome: " + jogador.getNome());
        System.out.println("Nível: " + jogador.getLvl());
        System.out.println("XP: " + jogador.getXp() + "/100");
    System.out.println(String.format("HP: %.2f/%.2f", jogador.getHp(), jogador.getMaxHp()));
    System.out.println(String.format("Atk: %.2f", jogador.getAtk()));
    System.out.println(String.format("Def: %.2f", jogador.getDef()));
        System.out.println("Mana: " + jogador.getMana());
    }

    private void usarItem() {
        System.out.println("Inventário:");
        jogador.getInventario().lista();
        System.out.println("Digite a posição do item que deseja usar:");
        int itemPos = scanner.nextInt();
        jogador.getInventario().useItem(jogador, itemPos);
    }

    public void textoAto(int ato, boolean posBatalha) {
        System.out.println("----------------------------------------------");

        // ===== ATO 1 =====
        if (ato == 1 && !posBatalha) {
            System.out.println("ATO 1 - A FLORESTA\n");
            System.out.println(
                    "Você se encontra em uma floresta.\n" +
                            "Houveram rumores de que existe uma criatura vagando por aqui.\n" +
                            "O vento sopra forte, e o som das árvores te deixa inquieto.\n" +
                            "Mais à frente, você escuta barulhos dentro de uma abertura nas árvores...\n\n" +
                            "Você sente que algo te observa nas sombras.");
        } else if (ato == 1 && posBatalha) {
            System.out.println(
                    "Após uma intensa batalha, você derrota o vampiro.\n" +
                            "Com ele, encontra um mapa antigo que revela a localização da base dos cultistas.");
        }

        // ===== ATO 2 =====
        else if (ato == 2 && !posBatalha) {
            System.out.println("ATO 2 - O RITUAL DOS CULTISTAS\n");
            System.out.println(
                    "Seguindo as pistas do mapa, você chega à base dos cultistas.\n" +
                            "O ar é denso e o som de cânticos ecoa pelos corredores subterrâneos.\n" +
                            "Você sente uma energia sombria pulsando ao seu redor...");
        } else if (ato == 2 && posBatalha) {
            System.out.println(
                    "Mesmo ferido, o cultista consegue concluir o ritual.\n" +
                            "Um portal se abre diante de você, sugando tudo ao redor.\n" +
                            "Você é arrastado para dentro — direto para a caverna do Lich.");
        }

        // ===== ATO 3 =====
        else if (ato == 3 && !posBatalha) {
            System.out.println("ATO 3 - A LAIR DO LICH\n");
            System.out.println(
                    "Você desperta em uma caverna escura e fria.\n" +
                            "No chão, uma pedra cintilante emite uma energia misteriosa.\n" +
                            "Ao tocá-la, você sente seu corpo ser envolto por uma aura poderosa.\n\n" +
                            "Avançando pela caverna, você sente o poder do Lich te observando...");
        } else if (ato == 3 && posBatalha) {
            System.out.println(
                    "O Lich cai diante de você, sua essência se desfaz em poeira.\n" +
                            "A esfera de energia se desfaz, e o mundo finalmente está a salvo.\n" +
                            "Parabéns, herói — sua jornada chega ao fim!");
        }

        System.out.println("----------------------------------------------");
    }

    private void display(Personagem jogador, Personagem inimigo) {
        System.out.println("============================================================================");
        if (this.classe == 1) {
            System.out.println(String.format("||HP - %s: %.2f / %.2f | HP - %s: %.2f / %.2f || ",
                    jogador.getNome(), jogador.getHp(), jogador.getMaxHp(),
                    inimigo.getNome(), inimigo.getHp(), inimigo.getMaxHp()));
            System.out.println("||Mana -" + jogador.getNome() + ": " + ((Mago) jogador).getMana());
        } else if (this.classe == 2) {
            System.out.println(String.format("||HP - %s: %.2f / %.2f | HP - %s: %.2f / %.2f || ",
                    jogador.getNome(), jogador.getHp(), jogador.getMaxHp(),
                    inimigo.getNome(), inimigo.getHp(), inimigo.getMaxHp()));
            System.out.println("||Mana - " + jogador.getNome() + ": " + ((Arqueiro) jogador).getMana());
        } else if (this.classe == 3) {
            System.out.println(String.format("||HP - %s: %.2f / %.2f | HP - %s: %.2f / %.2f || ",
                    jogador.getNome(), jogador.getHp(), jogador.getMaxHp(),
                    inimigo.getNome(), inimigo.getHp(), inimigo.getMaxHp()));
            System.out.println("||Raiva -" + jogador.getNome() + ": " + ((Guerreiro) jogador).getRage());
            System.out.println("||Mana -" + jogador.getNome() + ": " + ((Guerreiro) jogador).getMana());
        } else {
            System.out.println("How did you even manage to get here");
        }
        System.out.println("============================================================================");
    }

    public boolean batalhar(Inimigo inimigo) {
        if (jogador instanceof Mago) {
            jogador.setMana(500); //permite overflow de mana durante combate, mas reseta no começo da próxima batalha
        }
        if (inimigo.getHp() <= 0) {
            inimigo.setHp(inimigo.getMaxHp());
        }

        boolean acaoLista = false;
        boolean fugiu = false;
        inimigo.setHp(inimigo.getMaxHp());
        System.out.println("Iniciando batalha!");
        display(jogador, inimigo);
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
                        System.out.println("Você usou um item. Seu turno acabou.");
                    } else if (acao == 5) {
                        fugiu = fugir();
                        if (!fugiu) {
                            inimigo.atacar(jogador);
                        }
                    } else {
                        System.out.println("Ação inválida. Você perde seu turno.");
                    }
                } else if (classe == 2) {
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
                        ((Arqueiro) jogador).habilidade(inimigo, op);
                    } else if (acao == 3) {
                        System.out.println("Listando habilidades...");
                        ((Arqueiro) jogador).listaSpellArq();
                        acaoLista = true;
                    } else if (acao == 4) {
                        usarItem();
                        System.out.println("Você usou um item. Seu turno acabou.");
                    } else if (acao == 5) {
                        fugiu = fugir();
                        if (!fugiu) {
                            inimigo.atacar(jogador);
                        }
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
                    } else if (acao == 4) {
                        usarItem();
                        System.out.println("Você usou um item. Seu turno acabou.");
                    } else if (acao == 5) {
                        fugiu = fugir();
                        if (!fugiu) {
                            inimigo.atacar(jogador);
                        }
                    } else {
                        System.out.println("Ação inválida. Você perde seu turno.");
                    }
                }
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
            display(jogador, inimigo);
        }

        if (jogador.getHp() <= 0) {
            System.out.println("Você morreu.");
            System.exit(0);
            return false;
        } else if (fugiu) {
            System.out.println("Você fugiu da batalha.");
            return false;
        } else {
            System.out.println("Inimigo derrotado!");
            if (jogador.getXp() + inimigo.getLvl() * 20 >= 100) {
                jogador.lvlUp();
            } else {
                jogador.setXp(jogador.getXp() + inimigo.getLvl() * 20);
                System.out.println("Você ganhou " + inimigo.getLvl() * 20 + " de XP.");
                System.out.println("Nível atual: " + jogador.getLvl());
                System.out.println("XP atual: " + jogador.getXp() + "/100");
            }
            return true;
        }
    }

}