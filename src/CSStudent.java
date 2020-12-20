public class CSStudent extends Student {

    CSStudent(String name, int baseHP, int baseAtk, int baseDef, int baseSpd, int maxKP) {
        super(name, baseHP, baseAtk, baseDef, baseSpd, maxKP);
    }

    CSStudent(String name){
        this(name, 7, 6, 6, 6, 4);
    }

    public void pairWorking(Character friend, Character enemy) throws Exception {
        if (currentKP == maxKP) {
            System.out.println(name + " is working with " + friend.getName() + " to attack " + enemy.getName());
            increaseEP(INCREASE_EP_SPEC_ABILITY);
            resetKP();

            enemy.decreaseHP(Math.floorDiv(100 * friend.getAttack(), 100 + enemy.getDefence()));
            enemy.decreaseHP(Math.floorDiv(100 * getAttack(), 100 + enemy.getDefence()));
            checkKill(enemy);
        } else throw new Exception("Insufficient KP");
    }

    public void support(Character friend) throws Exception{
        System.out.println(name + " is using Support on team member: " + friend.getName());
        if (currentKP == maxKP) {
            increaseEP(INCREASE_EP_SPEC_ABILITY);
            resetKP();

            friend.increaseHP(getDefence());
        } else throw new Exception("Insufficient KP");
    }
}
