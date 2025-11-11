public class Inventario implements Cloneable {
    private Item[] items;

    public Item[] getItems() {
        return items;
    }

    public void lista() {
        if (items == null || items.length == 0) {
            System.out.println("O inventário está vazio.");
            return;
        }
        for (Item item : items) {
            System.out.println("Posição:" + item.getId() + ", Nome: " + item.getNome() + ", Quantidade: " + item.getQtd());
        }
    }

    public Inventario() {
    }

    public Inventario(Item[] items) {
        this.items = items;
    }

    public void addItem(Item newItem) {
        if (items == null) {
            // Initialize the array if it is null
            items = new Item[1];
            items[0] = newItem;
            return;
        }

        for (Item item : items) {
            if (item.hashCode() == newItem.hashCode()) {
                item.qtdUp();
                return;
            }
        }

        // If the array is full, create a new array with a larger size
        Item[] newItems = new Item[items.length + 1];
        System.arraycopy(items, 0, newItems, 0, items.length);
        newItems[items.length] = newItem;
        items = newItems;
    }

    public Item getItem(int id, Item[] items) {
        for (Item item : items) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    public void useItem(Personagem player, int itemId) {
        Item itemToUse = getItem(itemId, items);
        if (itemToUse == null) {
            System.out.println("Item não encontrado no inventário.");
            return;
        }

        switch (itemId) {
            case 1:
                player.setHp(player.getHp() + 50);
                if (player.getHp() > player.getMaxHp()) {
                    player.setHp(player.getMaxHp());
                }
                System.out.println(player.getNome() + " usou " + itemToUse.getNome() + " e recuperou 50 de HP.");
                break;
            case 2:
                player.setMana(player.getMana() + 30);
                if (player.getMana() > player.getMana()) {
                    player.setMana(player.getMana());
                }
                System.out.println(player.getNome() + " usou " + itemToUse.getNome() + " e recuperou 30 de Mana.");
                break;
            case 3:
                System.out.println("Você não pode usar moedas.");
                break;
            default:
                System.out.println("Efeito do item desconhecido.");
                return;
        }

        if (itemId != 3) {
            removeItem(itemToUse);
        }
    }

    public void removeItem(Item remItem) {
        for (int i = 0; i < items.length; i++) {
            if (items[i].hashCode() == remItem.hashCode()) {
                items[i].qtdDown();
                if (items[i].getQtd() <= 0) {
                    for (int j = i; j < items.length - 1; j++) {
                        items[j] = items[j + 1];
                    }
                    Item[] newItems = new Item[items.length - 1];
                    System.arraycopy(items, 0, newItems, 0, newItems.length);
                    items = newItems;
                }
                return;
            }
        }
    }

    public boolean isEmpty() {
        if (items == null || items.length == 0) {
            return true;
        } else {
            return false;
        }
    }
}
