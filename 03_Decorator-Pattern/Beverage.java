/**
 * Created by sogoagain on 2017. 4. 22..
 */
public abstract class Beverage {
    private String description = "Unknown Beverage";

    public String getDescription() {
        return description;
    }

    void setDescription(String description) {
        this.description = description;
    }

    public Beverage removeCondiment() {
        return this;
    }

    abstract public int cost();

    @Override
    public boolean equals(Object obj) {
        return (this == obj) ||(obj != null) && (this.getClass().getName().equals(obj.getClass().getName()));
    }

}

