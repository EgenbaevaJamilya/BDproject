import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static Department getDepartmentById(int id) {
        Department dept = null;
        Scanner scanner = new Scanner(System.in);
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/company", "root", "");
            Statement stat = con.createStatement();
            ResultSet rs = stat.executeQuery("select * from department where id=" + id);
            while (rs.next()) {
                dept = new Department();
                dept.setId(Integer.parseInt(rs.getString("id")));
                dept.setName(rs.getString("name"));
                dept.setDescription(rs.getString("description"));
            }
            rs.close();
            stat.close();
            con.close();
            return dept;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static Employee getEmployeeById(int id) {
        Employee emp = null;
        Scanner scanner = new Scanner(System.in);
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/company", "root", "");
            Statement stat = con.createStatement();
            ResultSet rs = stat.executeQuery("select * from employee where id=" + id);
            while (rs.next()) {
                emp = new Employee();
                emp.setId(Integer.parseInt(rs.getString("id")));
                emp.setAge(Integer.parseInt(rs.getString("age")));
                emp.setName(rs.getString("name"));
                emp.setSalary(Integer.parseInt(rs.getString("salary")));
                emp.setDept(Main.getDepartmentById(Integer.parseInt(rs.getString("fk_dept"))));
            }
            rs.close();
            stat.close();
            con.close();
            return emp;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static void main(String[] args) {

        int operation;

        int employeeoperation;
        int departmentoperation;

        int empback = 0;
        int deptback = 0;
        int id = 0;
        Department dept = null;
        Employee emp = null;
        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.println("1. Управление департаментами");
            System.out.println("2. Управление сотрудниками");

            System.out.println("0. Выход");

            System.out.println("Выбирете операцию:");
            operation = scanner.nextInt();

            switch (operation) {
                case 1:
                    deptback = 0;
                    departmentoperation = 0;
                    while (true) {
                        System.out.println("1. Добавить департамент");
                        System.out.println("2. Обновить департамент");
                        System.out.println("3. Удалить департамент - ID");
                        System.out.println("4. Показать все департаменты");
                        System.out.println("0. Вернуться в меню");

                        System.out.println("Выбрать операцию:");
                        departmentoperation = scanner.nextInt();

                        switch (departmentoperation) {
                            case 1:
                                dept = new Department();
                                dept.setData();
                                try {

                                    Class.forName("com.mysql.jdbc.Driver");
                                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost/company", "root",
                                            "");

                                    Statement stat = con.createStatement();

                                    int rel = stat.executeUpdate("insert into department values(" + dept.getId() + ",'"
                                            + dept.getName() + "','" + dept.getDescription() + "')");

                                    if (rel == 1) {
                                        System.out.println("\n Значения добавлены!");

                                    }

                                    con.close();
                                } catch (Exception e) {
                                    System.out.println(e);
                                }

                                break;
                            case 2:
                                dept = null;
                                System.out.println("Введите ID департамента:");
                                id = scanner.nextInt();
                                try {

                                    dept = Main.getDepartmentById(id);

                                    if (dept != null) {
                                        Class.forName("com.mysql.jdbc.Driver");
                                       Connection con = DriverManager.getConnection("jdbc:mysql://localhost/company", "root", "");
                                        Statement stat = con.createStatement();

                                        System.out.println("Введите название департамента:");
                                        dept.setName(scanner.next());
                                        System.out.println("Введите описание департамента:");
                                        dept.setDescription(scanner.next());
                                        int rel = stat.executeUpdate("update department set name='" + dept.getName()
                                                + "',description='" + dept.getDescription() + "' where id=" + id);
                                        if (rel == 1) {
                                            System.out.println("\n Значение обновлено!");
                                        }

                                        stat.close();
                                        con.close();
                                        System.out.println(dept);

                                    } else {
                                        System.out.println("Значение не найдено!");
                                    }

                                } catch (Exception e) {
                                    System.out.println(e);
                                }
                                break;
                            case 3:

                                dept = null;
                                System.out.println("Введите ID департамента:");
                                id = scanner.nextInt();
                                try {
                                    dept = Main.getDepartmentById(id);
                                    if (dept != null) {
                                        Class.forName("com.mysql.jdbc.Driver");
                                       Connection con = DriverManager.getConnection("jdbc:mysql://localhost/company", "root", "");
                                        Statement stat = con.createStatement();
                                        int rel = stat.executeUpdate("delete from department where id=" + id);
                                        if (rel == 1) {
                                            System.out.println("\n Значение удалено!");
                                        }

                                        con.close();
                                        System.out.println(dept);
                                    } else {
                                        System.out.println("Значение не найдено!");
                                    }

                                } catch (Exception e) {
                                    System.out.println(e);
                                }

                                break;
                            case 4:
                                dept = null;
                                try {

                                    Class.forName("com.mysql.jdbc.Driver");
                                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost/company", "root",
                                            "");

                                    Statement stat = con.createStatement();

                                    ResultSet rs = stat.executeQuery("select * from department");
                                    while (rs.next()) {

                                        dept = new Department();
                                        dept.setId(Integer.parseInt(rs.getString("id")));
                                        dept.setName(rs.getString("name"));
                                        dept.setDescription(rs.getString("description"));

                                        System.out.println(dept);
                                    }
                                    con.close();
                                } catch (Exception e) {
                                    System.out.println(e);
                                }
                                break;

                            case 0:
                                deptback = 1;
                                break;
                            default:
                                break;
                        }

                        if (deptback == 1) {
                            break;

                        }
                    }
                    break;

                case 2:
                    employeeoperation = 0;
                    empback = 0;
                    while (true) {
                        System.out.println("1. Добавить сотудника");
                        System.out.println("2. Обновить детали сотрудника");
                        System.out.println("3. Удалить сотрудника - ID");
                        System.out.println("4. Показать всех сотрудников");
                        System.out.println("5. Показать всех сотрудников по ID департамента");
                        System.out.println("0. Вернуться в меню");

                        System.out.println("Выберите операцию:");
                        employeeoperation = scanner.nextInt();

                        switch (employeeoperation) {
                            case 1:
                                ArrayList<Department> deptlist = new ArrayList<>();

                                try {

                                    Class.forName("com.mysql.jdbc.Driver");
                                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost/company", "root",
                                            "");

                                    Statement stat = con.createStatement();

                                    ResultSet rs = stat.executeQuery("select * from department");
                                    while (rs.next()) {

                                        dept = new Department();
                                        dept.setId(Integer.parseInt(rs.getString("id")));
                                        dept.setName(rs.getString("name"));
                                        dept.setDescription(rs.getString("description"));

                                        deptlist.add(dept);
                                    }

                                    rs.close();

                                    for (Department tempdept : deptlist) {
                                        tempdept.display();

                                    }
                                    System.out.println("Введите ID департамента:");
                                    id = scanner.nextInt();

                                    dept = new Department();
                                    dept.setId(id);

                                    // Found
                                    if (deptlist.contains(dept)) {

                                        // Insert
                                        emp = new Employee();
                                        emp.setData();
                                        emp.setDept(dept);
                                        int rel = stat.executeUpdate(
                                                "insert into employee values(" + emp.getId() + ",'" + emp.getName() + "',"
                                                        + emp.getSalary() + "," + emp.getAge() + "," + dept.getId() + ")");
                                        if (rel == 1) {
                                            System.out.println("\n Значение добавлено!");
                                        }
                                    }
                                    stat.close();
                                    con.close();
                                } catch (Exception e) {
                                    System.out.println(e);
                                }

                                break;
                            case 2:
                                emp = null;
                                System.out.println("Введите ID сотрудника:");
                                id = scanner.nextInt();
                                try {

                                    emp = Main.getEmployeeById(id);

                                    if (emp != null) {

                                        Class.forName("com.mysql.jdbc.Driver");
                                       Connection con = DriverManager.getConnection("jdbc:mysql://localhost/company", "root", "");
                                        Statement stat = con.createStatement();
                                        System.out.println(emp);

                                        emp.updateData();

                                        int rel = stat.executeUpdate("update employee set name='" + emp.getName() + "',age="
                                                + emp.getAge() + ",salary=" + emp.getSalary() + " where id=" + id);
                                        if (rel == 1) {
                                            System.out.println("\n Значение обновлено!");
                                        }
                                        stat.close();
                                        con.close();
                                        System.out.println(emp);
                                    } else {
                                        System.out.println("Значение не найдено!");
                                    }

                                } catch (Exception e) {
                                    System.out.println(e);
                                }
                                break;
                            case 3:
                                emp = null;
                                System.out.println("Введите ID сотрудника:");
                                id = scanner.nextInt();
                                try {

                                    emp = Main.getEmployeeById(id);

                                    if (emp != null) {

                                        Class.forName("com.mysql.jdbc.Driver");
                                       Connection con = DriverManager.getConnection("jdbc:mysql://localhost/company", "root", "");
                                        Statement stat = con.createStatement();
                                        System.out.println(emp);

                                        int rel = stat.executeUpdate("delete from employee where id=" + id);
                                        if (rel == 1) {
                                            System.out.println("\n Значение удалено!");
                                        }
                                        stat.close();
                                        con.close();
                                        System.out.println(emp);
                                    } else {
                                        System.out.println("Значение не найдено!");
                                    }

                                } catch (Exception e) {
                                    System.out.println(e);
                                }
                                break;
                            case 4:
                                try {

                                    Class.forName("com.mysql.jdbc.Driver");
                                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost/company", "root",
                                            "");

                                    Statement stat = con.createStatement();

                                    ResultSet rs = stat.executeQuery("select * from employee");
                                    while (rs.next()) {

                                        emp = new Employee();
                                        emp.setId(Integer.parseInt(rs.getString("id")));
                                        emp.setName(rs.getString("name"));
                                        emp.setAge(Integer.parseInt(rs.getString("age")));
                                        emp.setSalary(Integer.parseInt(rs.getString("salary")));

                                        emp.setDept(Main.getDepartmentById(Integer.parseInt(rs.getString("fk_dept"))));
                                        emp.display();
                                        emp.getDept().display();
                                    }

                                    rs.close();
                                    stat.close();
                                    con.close();
                                } catch (Exception e) {
                                    System.out.println(e);
                                }
                                break;
                            case 5:
                                dept = null;
                                System.out.println("Введите ID департамента:");
                                id = scanner.nextInt();
                                try {

                                    dept = Main.getDepartmentById(id);

                                    if (dept != null) {
                                        Class.forName("com.mysql.jdbc.Driver");
                                       Connection con = DriverManager.getConnection("jdbc:mysql://localhost/company", "root", "");

                                        Statement stat = con.createStatement();

                                        ResultSet rs = stat.executeQuery("select * from employee where fk_dept="+id);
                                        while (rs.next()) {

                                            emp = new Employee();
                                            emp.setId(Integer.parseInt(rs.getString("id")));
                                            emp.setName(rs.getString("name"));
                                            emp.setAge(Integer.parseInt(rs.getString("age")));
                                            emp.setSalary(Integer.parseInt(rs.getString("salary")));
                                            emp.setDept(Main.getDepartmentById(Integer.parseInt(rs.getString("fk_dept"))));
                                            emp.display();
                                            emp.getDept().display();
                                        }

                                        rs.close();
                                        stat.close();
                                        con.close();
                                    }
                                } catch (Exception e) {
                                    System.out.println(e);
                                }
                                break;
                            case 0:
                                empback = 1;
                                break;
                            default:
                                break;
                        }

                        if (empback == 1) {
                            break;

                        }
                    }
                    break;

                case 0:
                    System.exit(0);
            }

        }
    }
}