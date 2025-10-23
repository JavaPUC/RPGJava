public class Jogo {
    // perdi

    /*
     * Lore: Em busca dos 
     */
    public void batalhar(Inimigo inimigo) {

        int turnCount = 0;
        boolean turnoJogador = true;
        while (inimigo.getHp() < 0) {
            if (turnoJogador) {
                
                System.out.println("Jogador ataca o inimigo!");
                inimigo.setHp(inimigo.getHp() - 10); // Exemplo de dano
            } else {
                // Lógica de ataque do inimigo
                System.out.println("Inimigo ataca o jogador!");
                // Aqui você pode adicionar lógica para reduzir a vida do jogador
            }
            turnoJogador = !turnoJogador; // Alterna o turno
            turnCount++;
            if(turnCount > 10) {
                
            }
            
        }
    }
}