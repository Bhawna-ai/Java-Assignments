package model;

/**
 * Abstract base class representing a Person.
 */
public abstract class Person {
    protected String name;
    protected String email;

    public Person(String name, String email) {
        this.name = name;
        this.email = email;
    }

    // Abstract method to be implemented by subclasses
    public abstract void displayInfo();

    // Example of a concrete helper
    public String basicInfo() {
        return "Name: " + name + "\nEmail: " + email;
    }

    // Note: finalize is deprecated in modern Java; shown here only to match the assignment mention.
    @Override
    protected void finalize() throws Throwable {
        try {
            System.out.println("Finalize method called before object is garbage collected for: " + name);
        } finally {
            super.finalize();
        }
    }
}

