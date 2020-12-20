public class AIStudent extends Student {

    AIStudent(String name, int baseHP, int baseAtk, int baseDef, int baseSpd, int maxKP) {
        super(name, baseHP, baseAtk, baseDef, baseSpd, maxKP);
    }

    AIStudent(String name){
        this(name, 6, 7, 7, 5, 3);
    }

    public void machineLearning(Character enemy) throws Exception {
        if (currentKP == maxKP) {
            System.out.println(name + " is performing machineLearning on: " + enemy.getName());
            increaseEP(INCREASE_EP_SPEC_ABILITY);
            resetKP();

            if (enemy.getHP() > 0) {
                enemy.decreaseHP(Math.floorDiv(2 * (100 * getAttack()), 100 + enemy.getDefence()));
                checkKill(enemy);
            }
        } else throw new Exception("Insufficient KP");
    }

    public void naturalLanguageProcessing() throws Exception {
        if (currentKP == maxKP) {
            System.out.println(name + " is performing naturalLanguageProcessing");
            increaseEP(INCREASE_EP_SPEC_ABILITY);
            resetKP();

            increaseHP(getDefence());
        } else throw new Exception("Insufficient KP");
    }



}
