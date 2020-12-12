package Q1;

import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;


// This class implements the shelf manager. Shelf Manager will recieve pair of socks from the matching robot
public class shelfManager{


    // shelves which store the result from matching robot. These are shared with matching robot
    private ConcurrentLinkedQueue<sock> shelf_white;
    private ConcurrentLinkedQueue<sock> shelf_black;
    private ConcurrentLinkedQueue<sock> shelf_blue;
    private ConcurrentLinkedQueue<sock> shelf_grey;

    public ConcurrentLinkedQueue<sock> getShelf_black() {
        return shelf_black;
    }

    public ConcurrentLinkedQueue<sock> getShelf_blue() {
        return shelf_blue;
    }

    public ConcurrentLinkedQueue<sock> getShelf_grey() {
        return shelf_grey;
    }

    public ConcurrentLinkedQueue<sock> getShelf_white() {
        return shelf_white;
    }

    public shelfManager(){
        this.shelf_black = new ConcurrentLinkedQueue<>();
        this.shelf_white = new ConcurrentLinkedQueue<>();
        this.shelf_blue = new ConcurrentLinkedQueue<>();
        this.shelf_grey = new ConcurrentLinkedQueue<>();
    }



    // Below two functions print the shelf after everything is done by matching machine and arm robots
    private void print_shelf(Iterator iter, String colour){
        System.out.println(String.format("\nPrinting %s shelf ", colour));
        int pair_number = 0;
        while(iter.hasNext()){
            sock first = (sock) iter.next();
            sock second  = (sock) iter.next();
            System.out.println(String.format("pair Number %d with socks of id number: %d and id number: %d ", pair_number, first.getId(), second.getId()));
            pair_number += 1;
        }
    }

    public void print_shelves(){
        System.out.println("\nPrinting Shelves");
        print_shelf(shelf_white.iterator(), "White");
        print_shelf(shelf_black.iterator(), "Black");
        print_shelf(shelf_blue.iterator(), "Blue");
        print_shelf(shelf_grey.iterator(), "Grey");

    }


}
