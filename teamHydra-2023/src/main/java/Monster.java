import java.io.Serializable;

// Author @ Jason and Abdoulie
public class Monster implements Serializable {
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
        return this.monsterID;
    }

    public int getHealthPoints() {
        return this.healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public int getAttackStat() {
        return this.attackStat;
    }

    public void setAttackStat(int attackStat) {
        this.attackStat = attackStat;
    }

    public int getDefenseStat() {
        return this.defenseStat;
    }

    public void setDefenseStat(int defenseStat) {
        this.defenseStat = defenseStat;
    }

    public String getName() {return this.monsterName;}

    public void setName(String name) {this.monsterName = name;}

    // method used to display the name, attack power, and defense level of either the player
    public void getStatusForMonster(Monster monster){
        System.out.println("Status:\n" +
                "Name: " + monster.getName() + "\n" +
                "HP: " + monster.getHealthPoints() + " " +
                "ATK: " + monster.getAttackStat() + " " +
                "DEF: " + monster.getDefenseStat());
    }

    public void setMonsterID(int monsterID) {
        this.monsterID = monsterID;
    }

    public String getMonsterDescription() {
        return monsterDescription;
    }

    public void setMonsterDescription(String monsterDescription) {this.monsterDescription = monsterDescription;}





}