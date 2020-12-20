import java.util.ArrayList;

public class Guild {

    ArrayList<Character> members;

    Guild(){
        members = new ArrayList<>();
    }

    void addMember(Character character){
        members.add(character);
    }

    ArrayList<Character> getMembers(){
        return members;
    }

    //constructs a team from the guild of alive members, with a maximum of 5 members
    StudentTeam getTeam(String name){
        //creates a student team to later be returned
        StudentTeam studentTeam = new StudentTeam(name);

        //loops through the characters in the guild
        for (Character member : members){
            //if the member is alive, and the team does not already have 5 members, the member is added to the team
            if (studentTeam.getMembers().length < 5 && member.getHP() > 0){
                studentTeam.addMember(member);
            }
        }

        return studentTeam;
    }

    //simple method to check whether every student in the guild is dead
    boolean isDead(){
        //loops through all of the members, and if one is found to have a HP of more than 0, it returns false
        for (Character member : members){
            if (member.getHP() > 0){
                return false;
            }
        }
        return true;
    }
}
