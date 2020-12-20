public class MonsterTeam extends Team {

    MonsterTeam(String name) {
        super(name);
    }

    void move(Character member, Team enemyTeam) {
        try{
            System.out.println("******************************************************************");
            //calls the strike function depending on what type of monster they are
            if (member instanceof Minion) {
                ((Monster) member).strike(findLowestHP(member, enemyTeam));
            } else ((Boss) member).strike(findLowestHP(member, enemyTeam));
            System.out.println("******************************************************************");
        } catch (Exception e){
            System.out.println("Could not take monster's turn");
        }
    }

}
