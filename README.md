# IF2210 Aether Wars

## JDK

As of the creation of this template, the assistant team used and tested the JDK 8 installation from OpenJDK and Amazon Corretto (since JavaFX is readily available on those installations). You can easily pick and install your desired JDK version and variant by doing it via an IDE like [IntelliJ IDEA](https://www.jetbrains.com/idea/)

## Struktur Kode

    src
    └── main
        ├── java
        │   └── com
        │       └── aetherwars
        │           ├── AetherWars.java
        │           ├── model
        │           │   ├── Battleground.java
        │           │   ├── Board.java
        │           │   ├── Card.java
        │           │   ├── CardLibrary.java
        │           │   ├── CardReader.java
        │           │   ├── Character.java
        │           │   ├── Deck.java
        │           │   ├── Hand.java
        │           │   ├── ISummoned.java
        │           │   ├── ImportDeck.java
        │           │   ├── LVL.java
        │           │   ├── MORPH.java
        │           │   ├── Main.java
        │           │   ├── PTN.java
        │           │   ├── PermSpell.java
        │           │   ├── Player.java
        │           │   ├── SWAP.java
        │           │   ├── Spell.java
        │           │   ├── SummonedChar.java
        │           │   ├── TempSpell.java
        │           │   ├── TypeChar.java
        │           │   ├── TypePhase.java
        │           │   └── TypeSpell.java
        │           └── util
        │               └── CSVReader.java
        └── resources
            └── com
                └── aetherwars
                    └── card
                        ├── data
                        │   ├── character.csv
                        │   ├── spell_lvl.csv
                        │   ├── spell_morph.csv
                        │   ├── spell_ptn.csv
                        │   └── spell_swap.csv
                        ├── deck
                        │   └── importdeck.txt
                        └── image
                            ├── Alex.png
                            ├── Default.png
                            ├── Steve.png
                            ├── character
                            │   ├── Creeper.png
                            │   ├── Drowned.png
                            │   ├── Ender Dragon.png
                            │   ├── Enderman.png
                            │   ├── Endermite.png
                            │   ├── Ghast.png
                            │   ├── Magma Cube.png
                            │   ├── Obsidian.png
                            │   ├── Piglin Brute.png
                            │   ├── Sheep.png
                            │   ├── Shulker.png
                            │   ├── Skeleton.png
                            │   ├── Slime.png
                            │   ├── Villager.png
                            │   ├── Warden.png
                            │   ├── Wither Skeleton.png
                            │   ├── Wither.png
                            │   └── Zombie.png
                            └── spell
                                ├── level
                                │   ├── gatotkaca nangis.png
                                │   └── kena zonk.png
                                ├── morph
                                │   ├── Creeper... Aw Man.png
                                │   ├── Crybaby Dominion.png
                                │   ├── Drowning.png
                                │   ├── Malin Kundang.png
                                │   ├── Sheepify.png
                                │   └── Sugondese.png
                                ├── potion
                                │   ├── Aromatic Ginger Rice.png
                                │   ├── Bad Alcohol.png
                                │   ├── Contract of Living Space.png
                                │   ├── Deathly Magic.png
                                │   ├── Divine Wind.png
                                │   ├── GPU Very Good.png
                                │   ├── Grabkeun.png
                                │   ├── Halal Porkchop.png
                                │   ├── Herobrine's Blessing.png
                                │   ├── Honey Bottle.png
                                │   ├── Kordas' Curse.png
                                │   ├── Mother's Prayer.png
                                │   ├── SNMPTN Acceptance Letter.png
                                │   ├── SNMPTN Rejection Letter.png
                                │   ├── Sadikin Elixir.png
                                │   ├── Spectral's Power.png
                                │   ├── Witch's Eye.png
                                │   └── Yoasobi.png
                                └── swap
                                    ├── Axolotl Blood.png
                                    ├── Bone Marrow.png
                                    ├── Cat Food.png
                                    ├── Cooked Beef Juice.png
                                    ├── Detergent.png
                                    ├── Ghast Tears.png
                                    ├── Morning's Blessing.png
                                    ├── Potion of Bargaining.png
                                    ├── Potion of Turtle Master.png
                                    └── Swab Test.png

## Cara Compile & Run

Here is an example of project using gradle as the build tools.
Try running these commands:

`./gradlew run`

What happen is when you use `./gradlew run`, it will start the main function in your app.
For this app, the main function lives in `AetherWars.java`.

You can explore more about gradle [here](https://guides.gradle.org/creating-new-gradle-builds/)