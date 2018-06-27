package Beans;

import java.util.List;

public class Basket {
    private List<Potato> potatoes;

    public void add(Potato potato){
        potatoes.add(potato);
    }

    public Potato get(int i){
        return potatoes.get(i);
    }
}
