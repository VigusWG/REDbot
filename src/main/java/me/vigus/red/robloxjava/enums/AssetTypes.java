package me.vigus.red.robloxjava.enums;

public enum AssetTypes 
{
    IMAGE(1),
    TSHIRT(2),
    AUDIO(3),
    MESH(4),
    LUA(5),
    HTML(6),
    TEXT(7),
    HAT(8),
    PLACE(9),
    MODEL(10),
    SHIRT(11),
    PANTS(12),
    DECAL(13),
    AVATAR(16),
    HEAD(17),
    FACE(18),
    GEAR(19),
    BADGE(21),
    GROUPEMBLEM(22),
    ANIMATION(24),
    ARMS(25),
    LEGS(26),
    TORSO(27),
    RIGHTARM(28),
    LEFTARM(29),
    LEFTLEG(30),
    RIGHTLEG(31),
    PACKAGE(32),
    YOUTUBEVIDEO(33),
    GAMEPASS(34),
    APP(35),
    CODE(37),
    PLUGIN(38),
    SOLIDMODEL(39),
    MESHPART(40),
    HAIRACCESSORY(41),
    FACEACCESSORY(42),
    NECKACCESSORY(43),
    SHOULDERACCESSORY(44),
    FRONTACCESSORY(45),
    BACKACCESSORY(46),
    WAISTACCESSORY(47),
    CLIMBANIMATION(48),
    DEATHANIMATION(49),
    FALLANIMATION(50),
    IDLEANIMATION(51),
    JUMPANIMATION(52),
    RUNANIMATION(53),
    SWIMANIMATION(54),
    WALKANIMATION(55),
    POSEANIMATION(56),
    LOCALIZATIONTABLEMANIFEST(59),
    LOCALIZATIONTABLETRANSLATION(60),
    EMOTEANIMATION(61),
    VIDEO(62),
    TEXTUREPACK(63),
    TSHIRTACCESSORY(64),
    SHIRTACCESSORY(65),
    PANTSACCESSORY(66),
    JACKETACCESSORY(67),
    SWEATERACCESSORY(68),
    SHORTSACCESSORY(69),
    LEFTSHOEACCESSORY(70),
    RIGHTSHOEACCESSORY(71),
    DRESSSKIRTACCESSORY(72),
    FONTFAMILY(73),
    FONTFACE(74);

    private final Integer value;

    AssetTypes(final int newValue) {
        value = newValue;
    }

    public int getValue() { return value; }

    public static AssetTypes typeOfValue(Integer value) {
        for (AssetTypes e : values()) {
            if (e.value.equals(value)) {
                return e;
            }
        }
        return null;
    }

}
