package kg.megacom.miniTinder.models.enums;

public enum Gender {
    MALE("Мужчина"),
    FEMALE("Женщина");

    private String val;

    Gender(String val) {
        this.val = val;
    }

    public String getVal() {
        return val;
    }

}
