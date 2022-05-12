package Models.Building;

import Models.Resources.Resource;
import Models.Units.UnitType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Technology{

    Agriculture("Agriculture",20,"Ancient",new ArrayList<Technology>(Arrays.asList())),
    Mining("Mining",35,"Ancient",new ArrayList<Technology>(Arrays.asList(Agriculture))),
    Pottery("Pottery",35,"Ancient",new ArrayList<Technology>(Arrays.asList(Agriculture))),
    Animal_Husbandry("Animal_Husbandry",35,"Ancient",new ArrayList<Technology>(Arrays.asList(Agriculture))),
    Archery("Archery",35,"Ancient",new ArrayList<Technology>(Arrays.asList(Agriculture))),
    Bronze_Working("Bronze_Working",55,"Ancient",new ArrayList<Technology>(Arrays.asList(Mining))),
    Calendar("Calendar",70,"Ancient",new ArrayList<Technology>(Arrays.asList(Pottery))),
    Masonry("Masonry",55,"Ancient",new ArrayList<Technology>(Arrays.asList(Mining))),
    Wheel("Wheel",55,"Ancient",new ArrayList<Technology>(Arrays.asList(Animal_Husbandry))),
    Writing("Writing",55,"Ancient",new ArrayList<Technology>(Arrays.asList(Pottery))),
    Trapping("Trapping",55,"Ancient",new ArrayList<Technology>(Arrays.asList(Animal_Husbandry))),

    Construction("Construction",100,"Classical",new ArrayList<Technology>(Arrays.asList(Masonry))),
    Horse_Riding("Horse_Riding",100,"Classical",new ArrayList<Technology>(Arrays.asList(Wheel))),
    Iron_Working("Iron_Working",150,"Classical",new ArrayList<Technology>(Arrays.asList(Bronze_Working))),
    Mathematics("Mathematics",100,"Classical",new ArrayList<Technology>(Arrays.asList(Archery,Wheel))),
    Philosophy("Philosophy",100,"Classical",new ArrayList<Technology>(Arrays.asList(Writing))),

    Theology("Theology",250,"Medieval",new ArrayList<Technology>(Arrays.asList(Philosophy,Calendar))),
    Currency("Currency",250,"Medieval",new ArrayList<Technology>(Arrays.asList(Theology))),
    Civil_Service("Civil_Service",400,"Medieval",new ArrayList<Technology>(Arrays.asList(Mathematics))),
    Chivalry("Chivalry",440,"Medieval",new ArrayList<Technology>(Arrays.asList(Currency,Civil_Service,Horse_Riding))),
    Education("Education",440,"Medieval",new ArrayList<Technology>(Arrays.asList(Theology))),
    Engineering("Engineering",250,"Medieval",new ArrayList<Technology>(Arrays.asList(Mathematics,Construction))),
    Machinery("Machinery",440,"Medieval",new ArrayList<Technology>(Arrays.asList(Engineering))),
    Metal_Casting("Metal_Casting",240,"Medieval",new ArrayList<Technology>(Arrays.asList(Iron_Working))),
    Physics("Physics",440,"Medieval",new ArrayList<Technology>(Arrays.asList(Metal_Casting))),
    Steel("Steel",440,"Medieval",new ArrayList<Technology>(Arrays.asList(Metal_Casting))),

    PRINTING_PRESS("PRINTING_PRESS",650,"Renaissance",new ArrayList<Technology>(Arrays.asList(Machinery,Physics))),
    GUNPOWDER("GUNPOWDER",680,"Renaissance",new ArrayList<Technology>(Arrays.asList(Physics,Steel))),
    ACOUSTICS("ACOUSTICS",650,"Renaissance",new ArrayList<Technology>(Arrays.asList(Education))),
    ARCHAEOLOGY("ARCHAEOLOGY",1300,"Renaissance",new ArrayList<Technology>(Arrays.asList(ACOUSTICS))),
    BANKING("BANKING",650,"Renaissance",new ArrayList<Technology>(Arrays.asList(Education,Chivalry))),
    CHEMISTRY("CHEMISTRY",900,"Renaissance",new ArrayList<Technology>(Arrays.asList(GUNPOWDER))),
    ECONOMICS("ECONOMICS",900,"Renaissance",new ArrayList<Technology>(Arrays.asList(PRINTING_PRESS,BANKING))),
    FERTILIZER("FERTILIZER",1300,"Renaissance",new ArrayList<Technology>(Arrays.asList(CHEMISTRY))),
    METALLURGY("METALLURGY",900,"Renaissance",new ArrayList<Technology>(Arrays.asList(GUNPOWDER))),
    MILITARY_SCIENCE("MILITARY_SCIENCE",1300,"Renaissance",new ArrayList<Technology>(Arrays.asList(CHEMISTRY,ECONOMICS))),
    RIFLING("RIFLING",1425,"Renaissance",new ArrayList<Technology>(Arrays.asList(METALLURGY))),
    SCIENTIFIC_THEORY("SCIENTIFIC_THEORY",1300,"Renaissance",new ArrayList<Technology>(Arrays.asList(ACOUSTICS))),

    STEAM_POWER("STEAM_POWER",1680,"Industrial",new ArrayList<Technology>(Arrays.asList(MILITARY_SCIENCE,SCIENTIFIC_THEORY))),
    BIOLOGY("BIOLOGY",1680,"Industrial",new ArrayList<Technology>(Arrays.asList(ARCHAEOLOGY,SCIENTIFIC_THEORY))),
    DYNAMITE("DYNAMITE",1900,"Industrial",new ArrayList<Technology>(Arrays.asList(RIFLING,FERTILIZER))),
    ELECTRICITY("ELECTRICITY",1900,"Industrial",new ArrayList<Technology>(Arrays.asList(BIOLOGY,STEAM_POWER))),
    RADIO("RADIO",2200,"Industrial",new ArrayList<Technology>(Arrays.asList(ELECTRICITY))),
    RAILROAD("RAILROAD",1900,"Industrial",new ArrayList<Technology>(Arrays.asList(STEAM_POWER))),
    REPLACEABLE_PARTS("REPLACEABLE_PARTS",1900,"Industrial",new ArrayList<Technology>(Arrays.asList(STEAM_POWER))),
    TELEGRAPH("TELEGRAPH",2200,"Industrial",new ArrayList<Technology>(Arrays.asList(ELECTRICITY))),
    COMBUSTION("COMBUSTION",2200,"Industrial",new ArrayList<Technology>(Arrays.asList(DYNAMITE,REPLACEABLE_PARTS,RAILROAD)));

    private final int cost;
    private final String era;
    private final ArrayList<Technology> prerequisiteTechs;
    private final String type;
    private int temp =0;



    Technology(String type, int cost, String era, ArrayList<Technology> prerequisiteTechs) {
        this.cost=cost;
        this.prerequisiteTechs=prerequisiteTechs;
        this.era=era;
        this.type=type;
    }

    public int getCost(){
        return this.cost;
    }
    public String getEra(){
        return this.era;
    }

    public ArrayList<Technology> getPrerequisiteTechs(){
        return this.prerequisiteTechs;
    }
    public static ArrayList<Technology> getAllTechs(){
        return new ArrayList<>(Arrays.asList(Technology.class.getEnumConstants()));
    }
    public ArrayList<Technology> getTechHasUnlocked (){
        ArrayList<Technology> chiz = new ArrayList<>();
        for(Technology techs : getAllTechs()){
            if (techs.getPrerequisiteTechs().contains(this))
                chiz.add(techs);
        }
        return  chiz;
    }
    public ArrayList<Improvement> unlockingTechsForImprovements(){
        ArrayList<Improvement> improve = new ArrayList<Improvement>();
        for(Improvement improvement : Improvement.getAllImprovements()){
            if(this == improvement.getNeededTech())  improve.add(improvement) ;
        }
        return improve;
    }
    public ArrayList<Resource> unlockingTechsForResources(){
        ArrayList<Resource> improve = new ArrayList<Resource>();
        for(Resource resource : Resource.getAllResources()){
            if(this == resource.getRequiredTechnology())  improve.add(resource) ;
        }
        return improve;
    }

    public ArrayList<UnitType> unlockingTechsForUnits(){
        ArrayList<UnitType> unit = new ArrayList<UnitType>();
        for(UnitType units : UnitType.getAllUnits()){
            if(this == units.getPrerequisiteTechs())   unit.add(units) ;
        }
        return unit;
    }

    public String getType() {
        return type;
    }
}