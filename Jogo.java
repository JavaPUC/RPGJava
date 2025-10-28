import java.util.Random;
import java.util.Scanner;

public class Jogo {
    private int classe;
    private Personagem jogador;
    private Random random;
    private Scanner scanner;
    private Inimigo[] inimigos; 

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
        this.inimigos = new Inimigo[9];
        this.inimigos[0] = new Inimigo(1, "Goblin", 20, 4, 2, 1);
        this.inimigos[1] = new Inimigo(2, "Globin", 9999, 9999, 9999, 100);
        this.inimigos[2] = new Inimigo(3, "Vampiro", 50, 8, 4, 2);
        this.inimigos[3] = new Inimigo(4, "Escaravelho Gigante", 40, 6, 3, 2);
        this.inimigos[4] = new Inimigo(5, "Falcão-peregrino", 30, 7, 2, 2);
        this.inimigos[5] = new Inimigo(6, "Balança Rabo-leitoso Gigante", 80, 8, 8, 3);
        this.inimigos[6] = new Inimigo(7, "Camponês Revoltado", 25, 5, 3, 1);
        this.inimigos[7] = new Inimigo(8, "Cultista", 60, 12, 5, 3);
        this.inimigos[8] = new Inimigo(9, "???", 500, 30, 40, 10);

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
                escolhaValida = true;
                break;
            case 2:
                this.jogador = new Arqueiro();
                System.out.println("Você escolheu - Arqueiro.");
                escolhaValida = true;
                break;
            case 3:
                this.jogador = new Guerreiro(nome);
                System.out.println("Você escolheu - Guerreiro.");
                escolhaValida = true;
                break;
            default:
                System.out.println("Escolha inválida. Tente novamente.");
                break;
            }
        }
    }

    public void iniciar() {
        System.out.println("Bem-vindo ao RPG!");
        boolean jogando = true;

        while (jogando) {
            System.out.println("\nO que você deseja fazer?");
            System.out.println("[1] Explorar");
            System.out.println("[2] Usar item");
            System.out.println("[0] Sair do jogo");

            int escolha = scanner.nextInt();
            scanner.nextLine(); 

            switch (escolha) {
                case 0:
                    jogando = false;
                    System.out.println("Saindo do jogo. Até a próxima!");
                    break;
                case 1:
                    explorar();
                    break;
                case 2:
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private void explorar() {
        System.out.println("Você está explorando...");
        int evento = random.nextInt(3); // 0: inimigo, 1: armadilha, 2: nada
        switch (evento) {
            case 0:
                int index = random.nextInt(9);

                System.out.println("Você encontrou um inimigo!");
                batalhar(inimigos[8]); 
                break;
            case 1:
                System.out.println("Nada aconteceu.");
                break;
            case 2:
                System.out.println("Nada aconteceu.");
                break;
        }
    }

    private void fugir() {
        System.out.println("Tentando fugir...");
        int chance = random.nextInt(100);
        if (chance < 50) { 
            System.out.println("Você conseguiu fugir.");
        } else {
            System.out.println("Você falhou.");
           
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

    public void batalhar(Inimigo inimigo) {
        inimigo.setHp(inimigo.getMaxHp());
        System.out.println("Iniciando batalha!");
        System.out.println("============================================================================");
            if (this.classe == 1) {
            System.out.println("||HP - " + this.jogador.getNome() + ": " + jogador.getHp() + " | HP - " + inimigo.getNome() + ": " + inimigo.getHp() + " || ");  
            System.out.println("||Mana -" + this.jogador.getNome() + ": " + ((Mago) jogador).getMana());
            } else {
            System.out.println("||HP Jogador: " + jogador.getHp() + " | HP - " + inimigo.getNome() + ": " + inimigo.getHp()  + " || ");
            }
            System.out.println("============================================================================");
        int turnCount = 0;
        boolean turnoJogador = true;

        while (inimigo.getHp() > 0 && jogador.getHp() > 0) {
            if (turnoJogador) {
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
                        ((Mago) jogador).atacar(inimigo, op);
                    } else if (acao == 3) {
                        ((Mago) jogador).listaSpell();
                    } else if (acao == 4) {
                        System.out.println("Usando item...");
                    } else if (acao == 5) {
                        System.out.println("Tentando fugir...");
                        int chanceFuga = random.nextInt(100);
                        if (chanceFuga < 50) {
                            System.out.println("Você fugiu com sucesso!");
                            break;
                        } else {
                            System.out.println("Fuga falhou!");
                        }
                    } else {
                        System.out.println("Ação inválida. Você perde seu turno.");
                    }
                    
                }

            System.out.println("============================================================================");
            if (this.classe == 1) {
            System.out.println("||HP - " + this.jogador.getNome() + ": " + jogador.getHp() + " | HP - " + inimigo.getNome() + ": " + inimigo.getHp() + " || ");  
            System.out.println("||Mana -" + this.jogador.getNome() + ": " + ((Mago) jogador).getMana());
            } else {
            System.out.println("||HP Jogador: " + jogador.getHp() + " | HP - " + inimigo.getNome() + ": " + inimigo.getHp()  + " || ");
            }
            System.out.println("============================================================================");
            } else {
                System.out.println("Inimigo ataca o jogador!");
                jogador.setHp(jogador.getHp() - (10 + inimigo.getAtk())); 
            }
            
            turnoJogador = !turnoJogador; 
            System.out.println("============================================================================");
            if (this.classe == 1) {
            System.out.println("||HP - " + this.jogador.getNome() + ": " + jogador.getHp() + " | HP - " + inimigo.getNome() + ": " + inimigo.getHp() + " || ");  
            System.out.println("||Mana -" + this.jogador.getNome() + ": " + ((Mago) jogador).getMana());
            } else {
            System.out.println("||HP Jogador: " + jogador.getHp() + " | HP - " + inimigo.getNome() + ": " + inimigo.getHp()  + " || ");
            }
            System.out.println("============================================================================");
            turnCount++;
        }

        if (jogador.getHp() <= 0) {
            System.out.println("Você morreu.");
            caveira();
        } else {
            System.out.println("Você derrotou" + inimigo.getNome() + "!");
        }
    }

    public void caveira() {
        System.out.println("                            __xxxxxxxxxxxxxxxx___.\r\n" + //
                        "                        _gxXXXXXXXXXXXXXXXXXXXXXXXX!x_\r\n" + //
                        "                   __x!XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX!x_\r\n" + //
                        "                ,gXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXx_\r\n" + //
                        "              ,gXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX!_\r\n" + //
                        "            _!XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX!.\r\n" + //
                        "          gXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXs\r\n" + //
                        "        ,!XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX!.\r\n" + //
                        "       g!XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX!\r\n" + //
                        "      iXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX!\r\n" + //
                        "     ,XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXx\r\n" + //
                        "     !XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXx\r\n" + //
                        "   ,XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXx\r\n" + //
                        "   !XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXi\r\n" + //
                        "  dXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\r\n" + //
                        "  XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX!\r\n" + //
                        "  XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX!\r\n" + //
                        "  XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\r\n" + //
                        "  XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\r\n" + //
                        "  XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX!\r\n" + //
                        "  XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX!\r\n" + //
                        "  XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX!\r\n" + //
                        "  XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX!\r\n" + //
                        "  XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\r\n" + //
                        "  XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\r\n" + //
                        "  XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\r\n" + //
                        "  !XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\r\n" + //
                        "   XXXXXXXXXXXXXXXXXXXf~~~VXXXXXXXXXXXXXXXXXXXXXXXXXXvvvvvvvvXXXXXXXXXXXXXX!\r\n" + //
                        "   !XXXXXXXXXXXXXXXf`       'XXXXXXXXXXXXXXXXXXXXXf`          '~XXXXXXXXXXP\r\n" + //
                        "    vXXXXXXXXXXXX!            !XXXXXXXXXXXXXXXXXX!              !XXXXXXXXX\r\n" + //
                        "     XXXXXXXXXXv`              'VXXXXXXXXXXXXXXX                !XXXXXXXX!\r\n" + //
                        "     !XXXXXXXXX.                 YXXXXXXXXXXXXX!                XXXXXXXXX\r\n" + //
                        "      XXXXXXXXX!                 ,XXXXXXXXXXXXXX                VXXXXXXX!\r\n" + //
                        "      'XXXXXXXX!                ,!XXXX ~~XXXXXXX               iXXXXXX~\r\n" + //
                        "       'XXXXXXXX               ,XXXXXX   XXXXXXXX!             xXXXXXX!\r\n" + //
                        "        !XXXXXXX!xxxxxxs______xXXXXXXX   'YXXXXXX!          ,xXXXXXXXX\r\n" + //
                        "         YXXXXXXXXXXXXXXXXXXXXXXXXXXX`    VXXXXXXX!s. __gxx!XXXXXXXXXP\r\n" + //
                        "          XXXXXXXXXXXXXXXXXXXXXXXXXX!      'XXXXXXXXXXXXXXXXXXXXXXXXX!\r\n" + //
                        "          XXXXXXXXXXXXXXXXXXXXXXXXXP        'YXXXXXXXXXXXXXXXXXXXXXXX!\r\n" + //
                        "          XXXXXXXXXXXXXXXXXXXXXXXX!     i    !XXXXXXXXXXXXXXXXXXXXXXXX\r\n" + //
                        "          XXXXXXXXXXXXXXXXXXXXXXXX!     XX   !XXXXXXXXXXXXXXXXXXXXXXXX\r\n" + //
                        "          XXXXXXXXXXXXXXXXXXXXXXXXx_   iXX_,_dXXXXXXXXXXXXXXXXXXXXXXXX\r\n" + //
                        "          XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXP\r\n" + //
                        "          XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX!\r\n" + //
                        "           ~vXvvvvXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXf\r\n" + //
                        "                    'VXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXvvvvvv~\r\n" + //
                        "                      'XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX~\r\n" + //
                        "                  _    XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXv`\r\n" + //
                        "                 -XX!  !XXXXXXX~XXXXXXXXXXXXXXXXXXXXXX~   Xxi\r\n" + //
                        "                  YXX  '~ XXXXX XXXXXXXXXXXXXXXXXXXX`     iXX`\r\n" + //
                        "                  !XX!    !XXX` XXXXXXXXXXXXXXXXXXXX      !XX\r\n" + //
                        "                  !XXX    '~Vf  YXXXXXXXXXXXXXP YXXX     !XXX\r\n" + //
                        "                  !XXX  ,_      !XXP YXXXfXXXX!  XXX     XXXV\r\n" + //
                        "                  !XXX !XX           'XXP 'YXX!       ,.!XXX!\r\n" + //
                        "                  !XXXi!XP  XX.                  ,_  !XXXXXX!\r\n" + //
                        "                  iXXXx X!  XX! !Xx.  ,.     xs.,XXi !XXXXXXf\r\n" + //
                        "                   XXXXXXXXXXXXXXXXX! _!XXx  dXXXXXXX.iXXXXXX\r\n" + //
                        "                   VXXXXXXXXXXXXXXXXXXXXXXXxxXXXXXXXXXXXXXXX!\r\n" + //
                        "                   YXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXV\r\n" + //
                        "                    'XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX!\r\n" + //
                        "                    'XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXf\r\n" + //
                        "                       VXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXf\r\n" + //
                        "                         VXXXXXXXXXXXXXXXXXXXXXXXXXXXXv`\r\n" + //
                        "                          ~vXXXXXXXXXXXXXXXXXXXXXXXf`\r\n" + //
                        "                              ~vXXXXXXXXXXXXXXXXv~\r\n" + //
                        "                                 '~VvXXXXXXXV~~\r\n" + //
                        "                                       ~~\r\n" + //
                        "");
    }
}