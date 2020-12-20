import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TowerOfMonsters {

    //creates a buffered reader to be able to read the lines one by one from the text files
//    BufferedReader monsterReader, guildReader;
    //an arraylist to store all of the monster teams, which are each line of the text file
    ArrayList<MonsterTeam> monsterTeams;
    ArrayList<Guild> studentGuilds;
    String monsterTeamFileName, guildFileName;
    int guildWins;


    public TowerOfMonsters (String monsterTeamFileName, String guildFileName){
            this.monsterTeamFileName = monsterTeamFileName;
            this.guildFileName = guildFileName;
            monsterTeams = new ArrayList<>();
            studentGuilds = new ArrayList<>();
            guildWins = 0;
    }

    public static void main(String[] args) throws Exception {
        //creates a new instance of this tower of monsters class, using the text files specified in the command line
        //which are stored in the args string array, in the first and second position
        TowerOfMonsters towerOfMonsters = new TowerOfMonsters(args[0], args[1]);

        //this function is used to set the arraylist containing teams of monsters from the text file provided
        towerOfMonsters.setMonsterTeams();

        //this function is used to set the arraylist containing guilds from the text file provided
        towerOfMonsters.getStudentGuildsFromFile();

        //calls the play function to start the simulation between the teams already set
        towerOfMonsters.play();
    }

    void play() throws Exception {
        int tower = 1;

        for (Guild studentGuild : studentGuilds) {

            setMonsterTeams();
            int level = 0;

            System.out.println("********************************* TOWER : " + tower + " *********************************");

            //for loop to loop through each monster team in the tower the students have to face
            for (MonsterTeam monsterTeam : monsterTeams) {
                //checks whether there are alive members on the student team
                if (!studentGuild.isDead()) {
                    System.out.println("---------------------------- LEVEL: " + level + " -----------------------------");
                    //creates a new instance of the battle class, between the monster team for this round
                    //and the team retrieved from the student guild via the getTeam method
                    Battle battle = new Battle(studentGuild.getTeam("Student Team"), monsterTeam);
                    //to make it easier, I have assigned a new team to store the winning team
                    //this way when I check which team it is, I dont have to keep calling the fight function
                    Team winners = battle.fight();

                    if (winners instanceof StudentTeam) {
                        System.out.println("------------ STUDENTS WIN ------------");
                    } else if (winners instanceof MonsterTeam) {
                        System.out.println("------------ MONSTERS WIN ------------");
                    } else {
                        System.out.println("------------ DRAW ------------");
                    }

                }

                if (studentGuild.isDead()) {
                    System.out.println("---------------------------- Student Guild is dead! ----------------------------");
                    System.out.println("---------------------------- Monsters WIN! Level Reached : " + level + " ----------------------------");
                    //stops the simulation if there are no alive students in the guild
                    break;
                }

                level++;
            }

            if (!studentGuild.isDead()) {
                System.out.println("---------------------------- All Monsters are dead! ----------------------------");
                System.out.println("---------------------------- Students Defeated TowerOfMonsters! ----------------------------");
                guildWins++;
            }
            tower++;
        }
        //prints how many of the guilds defeated the tower of monsters
        System.out.println("________________ GUILDS WON: " + guildWins + " / " + studentGuilds.size() + " TOWERS ________________");
    }

    //creates and returns a guild with two students of each type
    Guild getDefaultGuild(){
        Guild studentGuild = new Guild();
        studentGuild.addMember(new AIStudent("aiStudent1"));
        studentGuild.addMember(new CSStudent("csStudent1"));
        studentGuild.addMember(new SEStudent("sesStudent1"));
        studentGuild.addMember(new CyberStudent("cyberStudent1"));
        studentGuild.addMember(new MathStudent("mathStudent1"));
        studentGuild.addMember(new AIStudent("aiStudent2"));
        studentGuild.addMember(new CSStudent("csStudent2"));
        studentGuild.addMember(new SEStudent("sesStudent2"));
        studentGuild.addMember(new CyberStudent("cyberStudent2"));
        studentGuild.addMember(new MathStudent("mathStudent2"));
        return studentGuild;
    }

    //this method returns the next line in the file as a single string value
    public String getLine(BufferedReader reader) {
        try {
            return reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return "end";
        }
    }

    //sets the monster team, using the text file provided into the constructor
    void setMonsterTeams() {
        //clears the arraylist of monster teams for the next guild to start a 'new game'
        monsterTeams.clear();
        //declares a new buffered reader to read each line from the tower text file
        BufferedReader monsterReader = null;
        try {
            monsterReader = new BufferedReader(new FileReader(monsterTeamFileName));
        } catch (IOException e){
            System.out.println("Could not reach monster team file");
        }
        //used an integer i to name the teams dynamically as they are added
        //this is just to make sure the names of each monster team is different
        int i = 0;
        while (true) {
            //gets the next line of the file in one string
            String output = getLine(monsterReader);
            //checking if it is null essentially checks if that is the end of the text files as no new line has been retrieved
            if (output != null) {
                //creates a new team of monsters, named by the number team it is in the file
                monsterTeams.add(new MonsterTeam(Integer.toString(i)));
                //this splits the line of the text file by the semi-colon, producing a string array
                //where each value of the array is each monster that has to be created - but not formatted correctly yet
                String[] newMember = output.split(";");
                //this loops over each of the monsters in the array and calls a function on them individually
                //of which I have created to format and add each member to the team
                for (String member : newMember) {
                    //int i can be used to determine which team the monster is being added to
                    addMonsterToTeam(member, monsterTeams.get(i));
                }
                i = i + 1;
            //breaks the loop if no new lines are found
            } else break;
        }
    }

    //this function is used to get name, level and type of monster given from the file and add them to the specified team
    //thought this method was necessary to break up the setMonsterTeams method, so it did not get too crowded
    public void addMonsterToTeam(String newMember, MonsterTeam monsterTeam){
        //this creates a new string array containing the name and rest of the attributes
        //I can then take the first value of this as the name, whereas the rest still needs extracting
        //regex was needed to allow the special characters to be able to be contained within quotation marks
        String[] nameAndAttributeArray = newMember.split("\\(");
        //this takes the previous array and removes the name (first value) and removes the end bracket as this is not wanted
        String attributeArray = nameAndAttributeArray[1].substring(0,nameAndAttributeArray[1].length() - 1);
        //this then creates a string array containing the type and level separately, so they can be used
        String[] attributes = attributeArray.split(",");

        //determines which type of monster to create
        //creates and adds a monster accordingly, using the values extracted earlier in this method
        if (attributes[0].equals("Minion")){
            monsterTeam.addMember(new Minion(nameAndAttributeArray[0], Integer.parseInt(attributes[1])));
        } else if (attributes[0].equals("Boss")) {
            monsterTeam.addMember(new Boss(nameAndAttributeArray[0], Integer.parseInt(attributes[1])));
        }
    }

    //sets the guilds of students from the text file provided into the constructor
    void getStudentGuildsFromFile() throws FileNotFoundException {
        //creates a new buffered reader to be able to retrieve each line of the guilds text file
        BufferedReader guildReader = null;
        try {
            guildReader = new BufferedReader(new FileReader(guildFileName));
        } catch (IOException e){
            System.out.println("Could not reach monster team file");
        }
        //used an integer i to later index which guild should be passed into the addStudentToGuild function
        int i = 0;
        while (true) {
            //gets the next line of the file in one string
            String output = getLine(guildReader);
            //checking if it is null essentially checks if that is the end of the text files as no new line has been retrieved
            if (output != null) {
                //creates and adds a new empty guild to the arraylist of guilds in this class
                studentGuilds.add(new Guild());
                //this splits the line of the text file by the semi-colon, producing a string array
                //where each value of the array is each student that has to be created - but not formatted correctly yet
                String[] newMember = output.split(";");
                //this loops over each of the students in the array and calls a function on them individually
                //of which I have created to format and add each member to the guild
                for (String member : newMember) {
                    //calls the addToGuild function to finalise the formatting and add the student to the guild provided
                    addToGuild(member, studentGuilds.get(i));
                }
                i = i + 1;
                //breaks the loop if no new lines are found
            } else break;
        }
    }

    //adds the newly extracted student to the guild provided
    //thought this method was necessary to break up the getStudentFromFile method, so it did not get too crowded
    public void addToGuild(String newMember, Guild newGuild){
        //this creates a new string array containing the name and type of the student
        //regex was needed to allow the special characters to be able to be contained within quotation marks
        String[] nameAndTypeArray = newMember.split("\\(");
        //creates a string to store the name of the student, which is the first value in the previous array
        String name = nameAndTypeArray[0];
        //creates a string to store the type of the student, which is the second value in the previous array
        //this also removes the bracket at the end, so only the type is extracted
        String type = nameAndTypeArray[1].substring(0,nameAndTypeArray[1].length() - 1);


        //determines which type of student to create, and adds them to the guild accordingly
        //for this I thought a case statement would be more appropriate, as there is quite a lot of options
        //for which the type could be
        switch (type) {
            case "CSStudent":
                newGuild.addMember(new CSStudent(name));
                break;
            case "AIStudent":
                newGuild.addMember(new AIStudent(name));
                break;
            case "CyberStudent":
                newGuild.addMember(new CyberStudent(name));
                break;
            case "MathStudent":
                newGuild.addMember(new MathStudent(name));
                break;
            case "SEStudent":
                newGuild.addMember(new SEStudent(name));
                break;
        }
    }
}