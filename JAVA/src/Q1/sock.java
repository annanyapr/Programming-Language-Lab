package Q1;

// sock object class
public class sock {

    private String colour;  // colour of sock
    private int id;  // id of sock

    // constructor
    public sock(String colour, int id){
        this.colour = colour;
        this.id = id;
    }


    // getter and setters
    public int getId() {
        return id;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public void setId(int id) {
        this.id = id;
    }
}
