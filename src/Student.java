public class Student extends Character {

    int maxKP, currentKP;

    Student(String name, int baseHP, int baseAtk, int baseDef, int baseSpd, int maxKP) {
        super(name, baseHP, baseAtk, baseDef, baseSpd);
        this.maxKP = maxKP;
        this.currentKP = 0;
    }

    public void increaseKP(int amount){
        currentKP += amount;
        //this if statement stops the student's KP going above their maximum KP
        if (currentKP > maxKP){
            currentKP = maxKP;
        }
    }

    //method I implemented to make it easier to reset the student's KP back to 0 once they have used a special ability
    public void resetKP(){
        currentKP = 0;
    }

    public int getKP(){
        return currentKP;
    }

    public void javaProgramming(Character enemy){
        System.out.println(name + " is performing JavaProgramming on: " + enemy.getName());

        increaseEP(INCREASE_EP_ABILITY);
        increaseKP(1);
        //used floor division as this seemed the most appropriate for integer division
        //it essentially rounds down to the nearest whole number that is possible
        enemy.decreaseHP(Math.floorDiv(100 * getAttack(), 100 + enemy.getDefence()));
        enemy.increaseEP(2);
        //instanceof is used to determine whether a specified object is an instance of a specified class
        if (enemy instanceof Student){
            //a Character does not have the function increaseKP
            //so this trick is used to essentially assume that it is a student object and treat it like one
            //this will not cause any errors as it is guaranteed it is a student due to the if statement wrapped around it
            ((Student) enemy).increaseKP(3);
        }
        //after using the ability, this method is called to check whether this has eliminated the enemy
        checkKill(enemy);
    }

    public void selfStudy(){
        System.out.println(name + " is performing selfStudy");

        increaseHP(2);
        increaseEP(6);
        increaseKP(2);
    }
}
