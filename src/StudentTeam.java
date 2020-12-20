import java.nio.charset.CharacterCodingException;

public class StudentTeam extends Team {

    final int JAVA_PROG_PERCENTAGE = 75;
    final int DEFAULT_DECISION_SCALE = 1;
    final int HP_AMOUNT_TO_HELP = 3;

    StudentTeam(String name) {
        super(name);
    }

    void move(Character member, Team enemyTeam) {
        //only takes a turn if the character is alive
        if (member.getHP() != 0){
            System.out.println("******************************************************************");
            //determines whether the student has maximum KP - if so it will use a special ability
            if (((Student) member).getKP() == ((Student) member).maxKP){

                //determines which type of student this character is
                if (member instanceof AIStudent){
                    //and then calls the specific move method I have created for the student type
                    AImove(member, enemyTeam);
                }

                if (member instanceof CSStudent){
                    CSmove(member, enemyTeam);
                }

                if (member instanceof CyberStudent){
                    CyberMove(member, enemyTeam);
                }

                if (member instanceof SEStudent){
                    SEmove(member, enemyTeam);
                }

                if (member instanceof MathStudent){
                    MAmove(member, enemyTeam);
                }

            //if the character does not have maximum KP, then it performs the default move
            // - which I have created a separate function for
            } else defaultMove(member, enemyTeam);
            System.out.println("******************************************************************");
        }
    }

    //this method is the default move, it uses a random number to implement probability on which ability to use
    void defaultMove(Character member, Team enemyTeam){
        int randomNum = random.nextInt(99);

        if (randomNum < JAVA_PROG_PERCENTAGE){
            //attacks the lowest HP member on the enemy team
            ((Student) member).javaProgramming(findLowestHP(member, enemyTeam));
        } else {
            ((Student) member).selfStudy();
        }
    }

    void AImove(Character member, Team enemyTeam) {
        try {
            int decisionScale = DEFAULT_DECISION_SCALE;
            int randomNum = random.nextInt(99);

            //this changes the probability based on their HP so it is more likely that the AI student
            //will use the ability to heal themselves instead of attacking
            if (member.getHP() < HP_AMOUNT_TO_HELP) {
                //amount to multiply the default percentage by if they have low HP
                decisionScale = 2;
            }

            if (randomNum < 35 * decisionScale) {
                ((AIStudent) member).naturalLanguageProcessing();
            } else ((AIStudent) member).machineLearning(findLowestHP(member, enemyTeam));

        } catch (Exception e){
            System.out.println("Couldn't take AIStudent's maxKP turn");
        }
    }

    void CSmove(Character member, Team enemyTeam) {
        try {
            int decisionScale = DEFAULT_DECISION_SCALE;
            int randomNum = random.nextInt(99);

            //this changes the probability based whether on the HP of a team member so it is more likely that the CS student
            //will use the ability to heal their team member instead of attacking
            for (Character teamMember : getMembers()) {
                if (teamMember.getHP() < HP_AMOUNT_TO_HELP && teamMember != member) {
                    //amount to multiply the default percentage by if a team member is on low HP
                    decisionScale = 3;
                }
            }

            if (randomNum < 25 * decisionScale) {
                //heals the lowest HP member on the team - who is alive
                ((CSStudent) member).support(findLowestHP(member, member.getTeam()));
            } else ((CSStudent) member).pairWorking(findLowestHP(member, member.getTeam()), findLowestHP(member, enemyTeam));

        } catch (Exception e){
            System.out.println("Couldn't take CSStudent's maxKP turn");
        }
    }

    void CyberMove(Character member, Team enemyTeam){
        try {
            ((CyberStudent) member).cyberAttack(enemyTeam);
        } catch (Exception e){
            System.out.println("Couldn't take CyberStudent's maxKP turn");
        }
    }

    void SEmove(Character member, Team enemyTeam){
        try {

            int decisionScale = DEFAULT_DECISION_SCALE;
            int randomNum = random.nextInt(99);

            //this changes the probability based on the HP of the team so it is more likely that the SE student
            //will use the ability to heal their team instead of attacking
            for (Character teamMember : getMembers()) {
                if (teamMember.getHP() < 3) {
                    //amount to multiply the default percentage by if a team member is on low HP
                    decisionScale = 2;
                }
            }

            if (randomNum < 35 * decisionScale) {
                ((SEStudent) member).groupDiscussion();
            } else ((SEStudent) member).groupWork(findLowestHP(member, enemyTeam));;

        } catch (Exception e){
            System.out.println("Couldn't take SEStudent's maxKP turn");
        }
    }

    void MAmove(Character member, Team enemyTeam){
        try {

            boolean foundDead = false;
            int randomNum = random.nextInt(99);
            int i;
            //this finds any dead members in the characters team to see if any could be revived using Fundamentals
            for (i = 0; i < getMembers().length; i++) {
                if (getMembers()[i].getHP() == 0) {
                    //amount to multiply the default percentage by if a team member is on low HP
                    foundDead = true;
                    break;
                }
            }

            //this set of if statements determines which ability the maths student will use
            //if no team members are dead, and they are on full HP, then it will save the KP for a later turn
            if (foundDead) {
                ((MathStudent) member).fundamentals(getMembers()[i]);
            } else if (member.getHP() < member.getMaxHP()) {
                ((MathStudent) member).floatingPointError();
            } else defaultMove(member, enemyTeam);

        } catch (Exception e){
            System.out.println("Couldn't take MathStudent's maxKP turn");
        }
    }

}
