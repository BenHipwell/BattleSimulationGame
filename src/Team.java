import java.util.*;

public abstract class Team {

    String name;
    ArrayList<Character> members;
    Random random = new Random();

    Team(String name){
        this.name = name;
        members = new ArrayList<>();
    }

    public String getName(){
        return name;
    }

    public Character[] getMembers(){
        return members.toArray(Character[]::new);
    }

    public int addMember(Character member){
        if (members.contains(member)){
            return -1;
        } else if (members.size() == 5){
            return -2;
        } else {
            members.add(member);
            //not only are you adding the member to the team, the member has to have a team assigned to it too
            //makes it easier later on to determine which team a member is on without already knowing
            member.setTeam(this);
            return members.size();
        }
    }

    //determines a method that needs to be implemented in any subclass of this abstract class
    abstract void move(Character member, Team enemyTeam) throws Exception;

    //this method finds the lowest HP member of a team
    //it passes in the team of which to find the lowest HP member
    //and also the character using this so that if it searching its own team, they can be excluded
    Character findLowestHP(Character characterChoosing, Team team){

        Character[] members = team.getMembers();
        //sets a temporary lowestHP member
        Character lowestHPmember = members[0];

        //this finds the first member in the order that doesnt have 0 hp
        //this is to avoid returning an incorrect result later in the method
        for (Character member : team.getMembers()){
            //finds a member that does not have 0 HP and that is not themselves (if own team)
            if (member.getHP() != 0 && member != characterChoosing){
                lowestHPmember = member;
                //stops the search after finding a member that satisfies the if statement
                break;
            }
        }

        //finds any members in the team that have a lower Hp than the temporary member set
        //and makes sure its not themselves, and that it is not a dead member
        for (Character member : members){
            if (member.getHP() < lowestHPmember.getHP() && member.getHP() != 0 && member != characterChoosing){
                lowestHPmember = member;
            }
        }
        //then returns the lowest HP member of the team found
        return lowestHPmember;
    }

}
