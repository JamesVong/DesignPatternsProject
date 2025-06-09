//package guild.singleton;
//
//public class SingletonTest {
//    public static void main(String[] args) {
//        GuildRegistry registry1 = GuildRegistry.getInstance();
//        GuildRegistry registry2 = GuildRegistry.getInstance();
//
//        registry1.registerHunter("BH101", "Mando");
//        registry1.registerHunter("BH102", "Jango");
//
//        registry2.printAllHunters();  // Should show same as registry1
//
//        System.out.println("Same instance? " + (registry1 == registry2)); // true
//    }
//}
