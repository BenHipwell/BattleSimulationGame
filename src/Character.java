import javax.tools.Tool;
import java.util.Arrays;
import java.util.Random;

public class Character {
    //I have included some constants for values which are either repeated or I think might want to be edited
    // to reduce the existence of 'magic numbers'
    final int INCREASE_EP_SPEC_ABILITY = 4;
    final int INCREASE_EP_ABILITY = 3;
    final double ATTRIBUTE_SCALAR = 1.2;
    final double EP_SCALAR = 1.5;
    final int INCREASE_EP_FOR_KILL = 4;

    final int CRITICAL_HIT_CHANCE_MONSTER = 10;
    final int CRITICAL_HIT_CHANCE_STUDENT = 20;

    String name;
    int baseHP, baseAtk, baseDef, baseSpd, level, currentHP, currentEP;
    Team currentTeam;
    Random random = new Random();

    Character(String name, int baseHP, int baseAtk, int baseDef, int baseSpd){
        this.name = name;
        this.baseHP = baseHP;
        this.baseAtk = baseAtk;
        this.baseDef = baseDef;
        this.baseSpd = baseSpd;
        this.level = 1;
        this.currentHP = getMaxHP();
        this.currentEP = 0;
    }

    public String getName(){
        return name;
    }

    public int getMaxHP(){
        return (int) Math.round(baseHP * (Math.pow(level, ATTRIBUTE_SCALAR)));
    }

    public int getAttack(){
        return (int) Math.round(baseAtk * (Math.pow(level, ATTRIBUTE_SCALAR)));
    }

    public int getDefence(){
        return (int) Math.round(baseDef * (Math.pow(level, ATTRIBUTE_SCALAR)));
    }

    public int getSpeed(){
        return (int) Math.round(baseSpd * (Math.pow(level, ATTRIBUTE_SCALAR)));
    }

    public int getTargetEP(){
        return (int) Math.round(10 * (Math.pow(level, EP_SCALAR)));
    }

    public int getHP(){
        if (currentHP < 0){
            //normalises all HP values under 0 to be 0
            //makes it easier to compare later on
            currentHP = 0;
        }
        return currentHP;
    }

    public int getEP(){
        return currentEP;
    }

    public int getLevel(){
        return level;
    }

    //increases HP, however does not let current HP get above max HP
    public void increaseHP(int amount){
        if (amount + currentHP > getMaxHP()){
            currentHP = getMaxHP();
        } else currentHP += amount;
    }

    //decreases HP, however does not let current HP get below 0
    public void decreaseHP(int amount){
        int randomNum = random.nextInt(99);

        //checks whether the instance of this object is a student or monster
        //and determines whether a critical hit has occurred depending on their type
        if (this instanceof Student) {
            if (randomNum < CRITICAL_HIT_CHANCE_STUDENT) {
                System.out.println("CRITICAL HIT");
                amount = amount * 2;
            }
        } else if (this instanceof Monster){
            if (randomNum < CRITICAL_HIT_CHANCE_MONSTER) {
                System.out.println("CRITICAL HIT");
                amount = amount * 2;
            }
        }

        if (currentHP - amount < 0){
            currentHP = 0;
        } else currentHP -= amount;
    }

    public void increaseEP(int amount){
        //checks whether the character's current EP plus the amount being added will meet or exceed the target EP
        if (currentEP + amount >= getTargetEP()){
            //levels up the character if this is true
            level += 1;
            //sets the EP back to 0 once leveled up
            currentEP = 0;

            //checks whether the character is alive
            if (currentHP > 0){
                //if so, restores all of their health
                currentHP = getMaxHP();
            }
        //if the amount added to the character's current EP does not exceed or meet the target EP, then it simply adds it on
        } else currentEP += amount;
    }

    public void setTeam(Team team){
        currentTeam = team;
    }

    public Team getTeam(){
        return currentTeam;
    }

    //short method to check whether a character has eliminated an enemy, and if so increasing their EP
    public void checkKill(Character enemy){
        if (enemy.getHP() == 0){
            increaseEP(INCREASE_EP_FOR_KILL);
            System.out.println("------------------------ " + enemy.getName() + " HAS DIED ------------------------");
        }
    }

}
