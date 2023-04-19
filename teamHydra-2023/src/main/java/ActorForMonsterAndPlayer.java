
// Author @ Jason
public class ActorForMonsterAndPlayer {


    private String name;
    private int healthPoints;
    private int attackStat;
    private int defenseStat;

    public ActorForMonsterAndPlayer(){

    }

    public ActorForMonsterAndPlayer(String name, int healthPoints, int attackStat, int defenseStat){
        this.name = name;
        this.healthPoints = healthPoints;
        this.attackStat = attackStat;
        this.defenseStat = defenseStat;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public int getAttackStat() {
        return attackStat;
    }

    public void setAttackStat(int attackStat) {
        this.attackStat = attackStat;
    }

    public int getDefenseStat() {
        return defenseStat;
    }

    public void setDefenseStat(int defenseStat) {
        this.defenseStat = defenseStat;
    }

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}
}

