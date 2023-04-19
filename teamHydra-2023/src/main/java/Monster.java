// Author @ Jason and Abdoulie
public class Monster extends ActorForMonsterAndPlayer {
    private int monsterID;

    private int healthPoints;

    private int attackStat;

    private int defenseStat;

    private String monsterName;

    private String monsterDescription;

    // default constructor for Monster
    public  Monster(){}

    public Monster(int monsterID, int healthPoints, int attackStat, int defenseStat , String monsterName, String monsterDescription){
        this.healthPoints = healthPoints;
        this.attackStat = attackStat;
        this.defenseStat = defenseStat;
        this.monsterID = monsterID;
        this.monsterName = monsterName;
        this.monsterDescription = monsterDescription;
    }

    public int getMonsterID() {
        return monsterID;
    }

    public void setMonsterID(int monsterID) {
        this.monsterID = monsterID;
    }

    public String getMonsterDescription() {
        return monsterDescription;
    }

    public void setMonsterDescription(String monsterDescription) {this.monsterDescription = monsterDescription;}





}