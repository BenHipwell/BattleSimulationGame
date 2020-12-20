public class MathStudent extends Student{

    MathStudent(String name, int baseHP, int baseAtk, int baseDef, int baseSpd, int maxKP) {
        super(name, baseHP, baseAtk, baseDef, baseSpd, maxKP);
    }

    MathStudent(String name){
        this(name, 9, 2, 9, 6, 10);
    }

    public void floatingPointError() throws Exception {
        if (currentKP == maxKP) {
            System.out.println(name + " is performing floatingPointError");
            increaseEP(INCREASE_EP_SPEC_ABILITY);
            resetKP();

            //heals themselves by increasing HP by half of max HP
            increaseHP(Math.floorDiv(getMaxHP(), 2));
        } else throw new Exception("Insufficient KP");
    }

    public void fundamentals(Character friend) throws Exception{
        System.out.println(name + " is using Fundamentals on team member: " + friend.getName());
        if (currentKP == maxKP) {
            increaseEP(INCREASE_EP_SPEC_ABILITY);
            resetKP();

            friend.increaseHP(Math.floorDiv(friend.getMaxHP(), 2));
        } else throw new Exception("Insufficient KP");
    }
}



