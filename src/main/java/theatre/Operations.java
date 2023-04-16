package theatre;

public enum Operations {
    B("book seats"), F("free up seats"), A("check availability"), E("exit");
    String description;
    Operations(String desc){
        this.description = desc;
    }
}
