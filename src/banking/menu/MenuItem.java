package banking.menu;

public class MenuItem {

    private int id;
    private String name;
    private MenuAction action;

    public MenuItem(int id, String name, MenuAction action) {
	this.id = id;
	this.name = name;
	this.action = action;
    }

    public int getId() {
	return id;
    }

    public String getName() {
	return name;
    }

    public MenuAction getAction() {
	return action;
    }

    @Override
    public String toString() {
	return id + ". " + name;
    }

}