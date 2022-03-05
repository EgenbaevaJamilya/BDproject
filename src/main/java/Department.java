import java.util.Scanner;

public class Department {

    private int id;
    private String name;
    private String description;

    public Department() {
        super();
    }

    public Department(int id, String name, String description) {
        super();
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setData(){
        Scanner scanner=new Scanner(System.in);
        System.out.println("Введите ID:");
        this.id=scanner.nextInt();
        System.out.println("Введите название:");
        this.name=scanner.next();
        System.out.println("Введите описание:");
        this.description=scanner.next();
    }

    public void display(){
        System.out.println("Детали департамента:");
        System.out.println("ID:"+id);
        System.out.println("Название:"+name);
        System.out.println("Описание:"+description);
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Department)) {
            return false;
        }
        if ((obj instanceof Department)) {
            Department dept = (Department) obj;
            if (this.getId() != dept.getId()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {

        return this.getId();
    }

    @Override
    public String toString() {

        return "ID департамента: " + this.getId() + " Название: " + this.getName() + " Описание :" + this.getDescription();
    }
}