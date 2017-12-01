
package cmput301f17t12.quirks.Enumerations;

public enum DropType {
    // Common items
    jeremylin(40, "Jeremy Lin"),
    kylesingler(40, "Kyle Singler"),
    zhouqi(40, "Zhou Qi"),
    krisdunn(40, "Kris Dunn"),
    joshhart(40, "Josh Hart"),
    jordanbell(40, "Jordan Bell"),
    kylekorver(40, "Kyle Korver"),
    coreybrewer(40, "Corey Brewer"),
    davidnwaba(40, "David Nwaba"),
    jakobpoeltl(40, "Jakob Poeltl"),

    // Uncommon items
    thonmaker(20, "Thon Maker"),
    jrsmith(20, "J.R. Smith"),
    jaylenbrown(20, "Jaylen Brown"),
    rajonrondo(20, "Rajon Rondo"),
    michaelkiddgilchrist(20, "Michael Kidd-Gilchrist"),
    manuginobli(20, "Manu Ginobli"),
    skallabissiere(20, "Skal Labissiere"),
    davidwest(20, "David West"),
    deaaronfox(20, "De'Aaron Fox"),

    // Rare items
    jordanclarkson(10, "Jordan Clarkson"),
    kcp(10, "Kentavious Caldwell-Pope"),
    buddyhield(10, "Buddy Hield"),
    lonzoball(10, "Lonzo Ball"),
    tarikblack(10, "Tarik Black"),
    bismackbiyombo(10, "Bismack Biyombo"),
    tysonchandler(10, "Tyson Chandler"),
    tyrekeevans(10, "Tyreke Evans"),
    ekpeudoh(10, "Ekpe Udoh"),
    larrynance(10, "Larry Nance Jr."),

    // Mythical items
    giannis(5, "Giannis Antetokounmpo"),
    draymond(5, "Draymond Green"),
    ingram(5, "Brandon Ingram"),
    dangelo(5, "D'Angelo Russell"),
    paugasol(5, "Pau Gasol"),
    averybradley(5, "Avery Bradley"),
    bradleybeal(5, "Bradley Beal"),
    markkanen(5, "Lauri Markkanen"),
    kylekuzma(5, "Kyle Kuzma"),


    // Legendary items
    durant(1, "Kevin Durant"),
    curry(1, "Steph Curry"),
    harden(1, "James Harden"),
    cousins(1, "DeMarcus Cousins"),
    davis(1, "Anthony Davis"),
    westbrook(1, "Russell Westbrook"),
    kawhi(1, "Kawhi Leonard"),
    derozan(1, "Demar Derozan"),
    carmelo(1, "Carmelo Anthony"),
    paulgeorge(1, "Paul George"),

    // Ancient items
    kobe(0.5, "Kobe Bryant"),
    jordan(0.5, "Michael Jordan"),
    lebron(0.5, "Lebron James"),
    magic(0.5, "Magic Johnson"),
    kareem(0.5, "Kareem Abdul-Jabar"),
    wilt(0.5, "Wilt Chamberlain"),
    larrybird(0.5, "Larry Bird"),
    duncan(0.5, "Tim Duncan"),
    hakeem(0.5, "Hakeem Olajuwon"),
    shaq(0.5, "Shaquille O'neal");

    private final double randomWeight;
    private final String name;

    DropType(double randomWeight, String name) {
        this.randomWeight = randomWeight;
        this.name = name;
    }

    public double getWeight() { return this.randomWeight; }

    public String getName() { return this.name; }

    public String getType() {
        if (this.randomWeight == 40) {
            return "COMMON";
        } else if (this.randomWeight == 20) {
            return "UNCOMMON";
        } else if (this.randomWeight == 10) {
            return "RARE";
        } else if (this.randomWeight == 5) {
            return "MYTHICAL";
        } else if (this.randomWeight == 1) {
            return "LEGENDARY";
        } else if (this.randomWeight == 0.5) {
            return "ANCIENT";
        } else {
            return "";
        }
    }

}