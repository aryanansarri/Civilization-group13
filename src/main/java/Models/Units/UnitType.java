package Models.Units;

import Models.Block.TechnologyAndImprovement.Technology;
import Models.Resources.Resource;

import java.util.ArrayList;
import java.util.Arrays;

import static Models.Block.TechnologyAndImprovement.Technology.*;
import static Models.Units.CombatType.*;
import static Models.Units.CombatType.GUNPOWDER;
import static Models.Resources.Resource.*;


public enum UnitType {
    SETTLER(CIVILIAN, -1, -1, -1, 2, 89, null, null,false),
    WORKER(CIVILIAN, -1, -1, -1, 2, 70, null, null,false),
    ARCHER(ARCHERY, 2, 4, 6, 2, 70, null, Archery,false),
    CHARIOTARCHER(MOUNTED,2,3,6,4,60,Horse, Wheel,false),
    SCOUT(RECON,-1,4,-1,2,25,null,  null,false),
    SPEARMAN(MELEE,-1,7,-1,2,50,null,  Bronze_Working,true),
    WARRIOR(MELEE,-1,6,-1,2,40,null, null,true),
    CATAPULT(SIEGE,2,4,14,2,100,Iron, Mathematics,false),
    HORSEMAN(MOUNTED,-1,12,-1,4,80,Horse,  Horse_Riding,true),
    SWORDSMAN(MELEE,-1,11,-1,2,80,Iron,  Iron_Working,true),
    CROSSBOWMAN(ARCHERY,2,6,12,2,120,null, Machinery,false),
    KNIGHT(MOUNTED,-1,18,-1,3,150,Horse,  Chivalry,true),
    Longswordsman(MELEE,-1,18,-1,3,150,Iron, Steel,true),
    Pikeman(MELEE,-1,10,-1,2,100,null,  Civil_Service,true),
    Trebuchet(SIEGE,2,6,20,2,170,Iron,  Physics,false),
    Canon(SIEGE,2,10,26,2,250,null,  CHEMISTRY,false),
    Cavalry(MOUNTED,-1,25,-1,3,260,Horse,  MILITARY_SCIENCE,true),
    Lancer(MOUNTED,-1,22,-1,4,220, Horse,  METALLURGY,true),
    Musketman(GUNPOWDER,-1,16,-1,2,120,null, Technology.GUNPOWDER,true ),
    Rifleman(GUNPOWDER,-1,25,-1,2,200,null, RIFLING,true),
    AntiTankGun(GUNPOWDER,-1,32,-1,2,300,null,  REPLACEABLE_PARTS,true),
    Artillery(SIEGE,3,16,32,2,420,null,  DYNAMITE,false),
    Infantry(GUNPOWDER,-1,36,-1,2,300,null, REPLACEABLE_PARTS,true),
    Panzer(ARMORED,-1,60,-1,5,450,null, COMBUSTION,true),
    Tank(ARMORED,-1,50,-1,4,450,null,  COMBUSTION,true);

    private final CombatType combatType;
    private final int range;
    private final int combatStrength;
    private final int rangedCombatStrength;
    private final int movementPoint;
    private final int cost;
    private final Resource prerequisiteResource;
    private final Technology prerequisiteTechs;
    private final boolean meleeAttackingAbility;



    UnitType(CombatType combatType, int range, int combatStrength, int rangedCombatStrength, int movementPoint, int cost, Resource prerequisiteResource , Technology prerequisiteTechs, boolean meleeAttackingAbility) {
        this.combatStrength = combatStrength;
        this.combatType = combatType;
        this.cost = cost;
        this.meleeAttackingAbility = meleeAttackingAbility;
        this.movementPoint = movementPoint;
        this.rangedCombatStrength = rangedCombatStrength;
        this.prerequisiteResource = prerequisiteResource;
        this.prerequisiteTechs = prerequisiteTechs;
        this.range = range;
    }

    public int getMovementPoint() {
        return movementPoint;
    }
    public int getCombatStrength() {
        return combatStrength;
    }

    public CombatType getCombatType() {
        return combatType;
    }

    public int getCost() {
        return cost;
    }

    public int getRange() {
        return range;
    }

    public int getRangedCombatStrength() {
        return rangedCombatStrength;
    }

    public Resource getPrerequisiteResource() {
        return prerequisiteResource;
    }

    public Technology getPrerequisiteTechs() {
        return prerequisiteTechs;
    }
    public boolean doesHaveMeleeAttackingAbility() {
        return meleeAttackingAbility;
    }
    public static ArrayList<UnitType> getAllUnits(){
        return new ArrayList<>(Arrays.asList(UnitType.class.getEnumConstants()));
    }

    public static ArrayList<UnitType> getSiegeMilitaryUnit() {
        ArrayList<UnitType> Siege = new ArrayList<>();
        Siege.add(Artillery);Siege.add(CATAPULT);Siege.add(Canon);Siege.add(Trebuchet);
        return Siege;
    }

}