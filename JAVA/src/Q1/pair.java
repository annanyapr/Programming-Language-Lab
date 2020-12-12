package Q1;



//This object class have sock object as member as well as a variable called is_taken.
//is_taken tells the robot arm that someone has already taken the sock out from the queue
public class pair {

    private sock Sock; // socks object
    private int is_taken;  // is taken variable

    //constructor
    public pair(sock Sock, int is_taken){
        this.is_taken = is_taken;
        this.Sock = Sock;
    }

    // getter and setters
    public int getIs_taken() {
        return is_taken;
    }

    public sock getSock() {
        return Sock;
    }

    public void setIs_taken(int is_taken) {
        this.is_taken = is_taken;
    }

    public void setSock(sock sock) {
        Sock = sock;
    }


}
