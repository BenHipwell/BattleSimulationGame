public class CyberStudent extends Student {

    CyberStudent(String name, int baseHP, int baseAtk, int baseDef, int baseSpd, int maxKP) {
        super(name, baseHP, baseAtk, baseDef, baseSpd, maxKP);
    }

    CyberStudent(String name){
        this(name, 7, 7, 5, 6, 6);
    }

    public void cyberAttack(Team enemyTeam) throws Exception {
        System.out.println(name + " is using cyberAttack on team: " + enemyTeam.getName());
        if (currentKP == maxKP) {
            increaseEP(INCREASE_EP_SPEC_ABILITY);
            resetKP();

            Character[] enemies = enemyTeam.getMembers();

            for (Character enemy : enemies) {
                if (enemy.getHP() != 0) {
                    enemy.decreaseHP(Math.floorDiv(100 * getAttack(), 100 + enemy.getDefence()));
                    checkKill(enemy);
                }
            }
        } else throw new Exception("Insufficient KP");
    }

}
