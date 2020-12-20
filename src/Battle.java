import java.nio.charset.CharacterCodingException;
import java.util.*;

public class Battle {

    //I have included some constants for values which are either repeated or I think might want to be edited
    // to reduce the existence of 'magic numbers'
    final int MAX_ROUND_NUM = 30;
    final int SLEEP_TIME_MS = 500;

    //created a local store for the teams fighting, so they can just be passed in through the constructor and not fight
    Team studentTeam, monsterTeam;
    //created an arraylist to have all of the characters from both teams
    //this makes it easier to sort and decide which characters' turn is next
    ArrayList<Character> allCharacters;
    //integer to store the round number - used for printing to console and for calculating how many rounds
    //have been completed out of the maximum allowed rounds
    int round;

    Battle(Team studentTeam, Team monsterTeam){
        this.studentTeam = studentTeam;
        this.monsterTeam = monsterTeam;
        allCharacters = new ArrayList<>();
    }

    Team fight() throws Exception {
        round = 1;

        //adds all members of both teams into one arraylist
        allCharacters.addAll(studentTeam.members);
        allCharacters.addAll(monsterTeam.members);
        //called to sort the allCharacters arraylist before the rounds start
        sortSpeed();

        //carries on until one of the teams has no alive members, or until the maximum round has been reached
        while (!teamDead(studentTeam) && !teamDead(monsterTeam) && round <= MAX_ROUND_NUM){
            System.out.println("---------------------------- ROUND " + round + " -----------------------------");
            //loops through all of the members in each team, in order of their speed which has already been sorted
            for (Character member : allCharacters){
                //this is to stop a character from taking a turn if they are dead
                if (member.getHP() != 0){

                    //if the player is a student it performs the student's move function
                    //or if the player is a monster then it performs the monster's move function
                    if (member instanceof Student){
                        studentTeam.move(member, monsterTeam);
                    }
                    if (member instanceof Monster){
                        monsterTeam.move(member, studentTeam);
                    }
                    printStats();

                    //returns the winner, once one of the teams has no alive members
                    if (teamDead(studentTeam)){
                        return monsterTeam;
                    } else if (teamDead(monsterTeam)){
                        return studentTeam;
                    }

                    //this is to implement a time gap in between each characters successful turn
                    try {
                        Thread.sleep(SLEEP_TIME_MS);
                    } catch (InterruptedException e){
                        System.out.println("Sleep Error");
                    }

                }
            }
            round++;
        }
        //returns null if no team wins when the max round has been met and both teams are alive - a draw
        return null;
    }

    //method to check whether the team specified is dead
    Boolean teamDead(Team team){
        for (Character member : team.getMembers()){
            if (member.getHP() != 0){
                //once it finds a single member who has more than 0 HP, it returns false
                return false;
            }
        }
        //if no members with over 0 HP can be found, then it returns true
        return true;
    }

    //method to display the values of the important attributes of each character
    void printStats(){
        for (Character member : studentTeam.getMembers()){
            System.out.print(member.getName());
            System.out.print(" | HP:" + member.getHP());
            System.out.print(" | EP:" + member.getEP());
            System.out.print(" | LVL:" + member.getLevel());
            System.out.print(" | KP:" + ((Student) member).getKP());
            System.out.println();
        }

        for (Character member : monsterTeam.getMembers()){
            System.out.print(member.getName());
            System.out.print(" | HP:" + member.getHP());
            System.out.print(" | EP:" + member.getEP());
            System.out.print(" | LVL:" + member.getLevel());
            System.out.println();
        }
    }

    //this method is used to sort the arraylist, containing the students, into descending order of their speed values
    void sortSpeed(){
        //it makes use of a method in java.util.Collections to sort the objects in allCharacters by their speed attribute
        allCharacters.sort(new Comparator<Character>() {
            public int compare(Character o1, Character o2) {
                //swapped the typical character instances around to be descending and not ascending
                return Integer.compare(o2.getSpeed(), o1.getSpeed());
            }
        });
    }

    int getRound(){
        return round;
    }
}