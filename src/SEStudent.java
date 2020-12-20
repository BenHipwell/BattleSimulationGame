public class SEStudent extends Student {

    SEStudent(String name, int baseHP, int baseAtk, int baseDef, int baseSpd, int maxKP) {
        super(name, baseHP, baseAtk, baseDef, baseSpd, maxKP);
    }

    SEStudent(String name){
        this(name, 8, 5, 8, 4, 10);
    }

    public void groupWork(Character enemy) throws Exception {
        if (currentKP == maxKP) {
            System.out.println(name + " is performing groupWork on: " + enemy.getName());
            increaseEP(INCREASE_EP_SPEC_ABILITY);
            resetKP();

            Character[] teamMembers = getTeam().getMembers();
            for (Character member : teamMembers) {
                if (member.getHP() > 0) {
                    enemy.decreaseHP(Math.floorDiv(100 * member.getAttack(), 100 + enemy.getDefence()));
                    checkKill(enemy);
                }
            }
        } else throw new Exception("Insufficient KP");
    }

    public void groupDiscussion() throws Exception {
        if (currentKP == maxKP) {
            System.out.println(name + " is performing groupDiscussion");
            increaseEP(INCREASE_EP_SPEC_ABILITY);
            resetKP();

            Character[] teamMembers = getTeam().getMembers();
            for (Character member : teamMembers) {
                if (member.getHP() != 0) {
                    member.increaseHP(Math.floorDiv(getDefence(), 2));
                }
            }
        } else throw new Exception("Insufficient KP");
    }

}
