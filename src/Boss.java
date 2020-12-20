public class Boss extends Character implements Monster {

    Boss(String name, int baseHP, int baseAtk, int baseDef, int baseSpd) {
        super(name, baseHP, baseAtk, baseDef, baseSpd);
    }

    Boss(String name){
        this(name, 8, 7, 8, 7);
    }

    //created an extra constructor for the monsters in tower of monsters to passed in by
    Boss(String name, int level){
        this(name, 5, 5, 5, 5);
        this.level = level;
    }

    public void strike(Character enemy) {
        //gets a random number between 0 and 99 (100 values)
        int randomNum = random.nextInt(99);

        //depending on the random number, decides which ability to use
        // < 75 and not <=75 as it includes 0, so 0-74 would be 75%... and so on
        if (randomNum < 50){
            SyntaxError(enemy);
        } else if (randomNum < 65){
            NullPointerException();
        } else if (randomNum < 80){
            ArrayIndexOutOfBoundException(enemy);
        } else if (randomNum < 90){
            NoneTermination();
        } else if (randomNum < 100){
            ConcurrentModificationException(enemy.getTeam());
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
        System.out.println(name + " is performing ArrayIndexOutOfBoundException on: " + enemy.getName());

        increaseEP(INCREASE_EP_ABILITY);
        enemy.decreaseHP(2*(Math.floorDiv(100 * getAttack(), 100 + enemy.getDefence())));
        enemy.increaseEP(INCREASE_EP_ABILITY);

        if (enemy instanceof Student){
            ((Student) enemy).increaseKP(3);
        }

        checkKill(enemy);
    }

    void NoneTermination(){
        System.out.println(name + " is performing NoneTermination");
        increaseEP(INCREASE_EP_ABILITY);
        Character[] teamMembers = getTeam().getMembers();
        for (Character member : teamMembers) {
            if (member.getHP() == 0){
                member.increaseHP(member.getMaxHP());
            }
        }
    }

    void ConcurrentModificationException(Team enemyTeam){
        System.out.println(name + " is using ConcurrentModificationException on team: " + enemyTeam.getName());
        increaseEP(INCREASE_EP_ABILITY);
        Character[] enemies = enemyTeam.getMembers();
        for (Character enemy : enemies){
            if (enemy.getHP() != 0){
                enemy.decreaseHP(Math.floorDiv(100 * getAttack(), 100 + enemy.getDefence()));
                enemy.increaseEP(INCREASE_EP_ABILITY);

                if (enemy instanceof Student){
                    ((Student) enemy).increaseKP(3);
                }

                checkKill(enemy);
            }
        }

    }

}
