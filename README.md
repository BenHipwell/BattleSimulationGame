# BattleSimulationGame

This is a round-based battle simulation, where each round a team of monsters challenges a team of students from a guild.
The first text file contains list of teams of monsters to fight for each level of the tower.
The second file contains a list of guilds of students for which should fight in teams against the teams of monsters.

**How to run:**

- I have first been compiling all java files using 'javac *.java'

Executing:

  - 'java TowerOfMonsters monsterfilename.txt guildsfilename.txt'
  
  - where both monsterfilename & guildsfilename are just examples

The format for the guild text file and monster text file are pretty much the same:
- No semi-colon at the end of the line

- Guilds file example (one line per guild):
  - name(type);name(type);....name(type);name(type)

    - 'name' is where the name of the student should go - String

    - 'type' is the type of student, e.g. CSStudent, AIStudent...etc - String 

- Monsters file example (one line per team of monster):
  - name(type, level);name(type, level);....name(type, level);name(type, level)

    - 'name' is where the name of the monster should go - String
    - 'type' is the type of student, e.g. Minion or Boss - String
    - 'level' is the integer number of the level this monster will start at

*type needs to be spelt word for word the same as the java file / class name*
