public class Inventario implements Cloneable  {
    private Item[] items;
    
    public Item[] getItems() {
        return items;
    }
    public void lista() {
        for (Item item : items) {
            System.out.println("Nome: " + item.getNome() + ", Quantidade: " + item.getQtd());
        }
    }

    public Inventario() {
    }
    public Inventario(Item[] items) {
        this.items = items;
    }

    public void addItem(Item newItem) {
        for (Item item : items) {
            if (item.hashCode() == newItem.hashCode()) {
                item.qtdUp();
                return;
            } else {
                items[items.length] = newItem;
                return;
            }
        }
    }

    public Item getItem(int id, Item[] items) {
        for (Item item : items) {
            if (item.getId() == id) {
            return item;
            }
        }
        return null;
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
