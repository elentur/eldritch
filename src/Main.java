import factory.ItemFactory;
import factory.MonsterFactory;

public class Main {

    public static void main(String[] args) {
        MonsterFactory factory = new MonsterFactory();
        factory.getMonster();

        ItemFactory itemFactory = new ItemFactory();
        itemFactory.getAssets();
    }
}
