public class Brie extends Item {

    public Brie(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    @Override
    public void update() {
        // Lógica correta do Aged Brie
        this.sellIn--;

        if (this.quality < 50) {
            this.quality++;
        }

        // Após a data de venda, o Brie melhora mais ainda
        if (this.sellIn < 0 && this.quality < 50) {
            this.quality++;
        }
    }
}
