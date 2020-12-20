public class Minion extends Character implements Monster {
    
    Minion(String name, int baseHP, int baseAtk, int baseDef, int baseSpd) {
        super(name, baseHP, baseAtk, baseDef, baseSpd);
    }

    Minion(String name){
        this(name, 5, 5, 5, 5);
    }

    //created an extra constructor for the monsters in tower of monsters to passed in by
    Minion(String name, int level){
        this(name, 5, 5, 5, 5);
        this.level = level;
    }

    public void strike(Character enemy) {
        //gets a random number between 0 and 99 (100 values)
        int randomNum = random.nextInt(99);

        //depending on the random number, decides which ability to use
        // < 75 and not <=75 as it includes 0, so 0-74 would be 75%... and so on
        if (randomNum < 75){
            SyntaxError(enemy);
        } else if (randomNum < 90 ){
            NullPointerException();
        } else if (randomNum < 100){
            ArrayIndexOutOfBoundException(enemy);
        }
    }

    public void SyntaxError(Character enemy){
        System.out.println(name + " is performing SyntaxError on: " + enemy.getName());
        increaseEP(INCREASE_EP_ABILITY);
        enemy.decreaseHP(Math.floorDiv(100 * getAttack(), 100 + enemy.getDefence()));
        enemy.increaseEP(INCREASE_EP_ABILITY);

        if (enemy instanceof Student){
            ((Student) enemy).increaseKP(3);
        }

        checkKill(enemy);
    }

    public void NullPointerException(){
        System.out.println(name + " is performing NullPointerException");
        increaseEP(INCREASE_EP_ABILITY);
        increaseHP(getDefence());
    }

    void ArrayIndexOutOfBoundException(Character enemy){
        System.out.println(name + " is performing ArrayIndexOutOfBoundException on:" + enemy.getName());
        increaseEP(3);
        enemy.decreaseHP(2*(Math.floorDiv(100 * getAttack(), 100 + enemy.getDefence())));
        enemy.increaseEP(3);

        if (enemy instanceof Student){
            ((Student) enemy).increaseKP(3);
        }

        checkKill(enemy);
    }

}
