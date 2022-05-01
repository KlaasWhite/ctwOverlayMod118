package me.klaaswhite.ctwoverlay118.classes;

public class CtwPlayer {
    private final String name;
    private final String teamName;
    private final int classNumber;

    public CtwPlayer(String name, String teamName, int classNumber) {
        this.name = name;
        this.teamName = teamName;
        this.classNumber = classNumber;
    }

    public String getName() {
        return name;
    }

    public String getTeamName() {
        return teamName;
    }

    public int getClassNumber() {
        return classNumber;
    }
    public String toJson() {
        return "{\"name\":\"" + name + "\",\"teamName\":\"" + teamName + "\" + \"classNumber\":\"" + classNumber + "\"}";
    }
}
