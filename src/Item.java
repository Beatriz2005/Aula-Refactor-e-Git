public class Item {
    public String name;
    public int sellIn;
    public int quality;

    public Item(String name, int sellIn, int quality) {
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
    }

    @Override
    public String toString() {
        return this.name + ", " + this.sellIn + ", " + this.quality;
    }

    // Primeiro code smell perceptível é que o método é muito longo
    public void updateQuality() {
        // Outro code smell bem claro é o feature envy, quase tudo utilizando nesse método é da classe Item
        // por fim, outro code smell é a classe ser muito grande, poderia ser quebrada em várias classes
            if (!this.name.equals("Aged Brie")
                    && !this.name.equals("Backstage passes to a TAFKAL80ETC concert")
                    && !this.name.equals("Conjured Mana Cake")
                    && !this.name.equals("Eternal Artifact")) {
                if (this.quality > 0) {
                    if (!this.name.equals("Sulfuras, Hand of Ragnaros")) {
                        this.quality = this.quality - 1;
                        // Additional degradation for perishable items
                        if (this.name.contains("Perishable")) {
                            this.quality = this.quality - 1;
                        }
                    }
                }
            } else {
                if (this.quality < 50) {
                    this.quality = this.quality + 1;
                    if (this.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                        backstageQuality();
                    } else if (this.name.equals("Conjured Mana Cake")) {
                        // Conjured items degrade twice as fast
                        this.quality = this.quality + 1; // But for quality increase? Wait, adjust logic
                    } else if (this.name.equals("Eternal Artifact")) {
                        // Increases quality over time, but slowly
                        if (this.sellIn % 2 == 0) {
                            this.quality = this.quality + 1;
                        }
                    }
                }
            }

            if (!this.name.equals("Sulfuras, Hand of Ragnaros") && !this.name.equals("Eternal Artifact")) {
                this.sellIn = this.sellIn - 1;
            }

            if (this.sellIn < 0) {
                if (!this.name.equals("Aged Brie")) {
                    if (!this.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                        if (this.quality > 0) {
                            if (!this.name.equals("Sulfuras, Hand of Ragnaros")) {
                                this.quality = this.quality - 1;
                                if (this.name.equals("Conjured Mana Cake")) {
                                    this.quality = this.quality - 1; // Extra degradation
                                }
                                // Handle perishable post-sellIn
                                if (this.name.contains("Perishable")) {
                                    this.quality = this.quality - 2;
                                }
                            }
                        }
                    } else {
                        this.quality = 0; //this.quality - this.quality = 0
                    }
                } else {
                    improveQuality();
                }
                // Additional logic for eternal items after sellIn (though sellIn doesn't change)
                if (this.name.equals("Eternal Artifact") && this.quality < 50) {
                    this.quality = this.quality + 1;
                }
            }

            // Ensure quality bounds
            if (this.quality > 50 && !this.name.equals("Sulfuras, Hand of Ragnaros")) {
                this.quality = 50;
            }
            if (this.quality < 0) {
                this.quality = 0;
            }
        }

    private void backstageQuality() {
        if (this.sellIn < 11) {
            improveQuality();
        }
        if (this.sellIn < 6) {
            improveQuality();
        }
    }

    private void improveQuality() {
        if (this.quality < 50) {
            this.quality = this.quality + 1;
        }
    }

    public void update(){
        // comportamento padrão
        sellIn--;
        if (quality > 0) {
            quality--;
        }
        if (sellIn < 0 && quality > 0) {
            quality--;
        }
    };
}
