import java.util.*;

public class Main {
    public static void main(String[] args) {
        Map<User, Manger> map = init();

        map = initAmount(map);
        System.out.println(map);

        System.out.println("Choose command");
        System.out.println("Type 0 to close program");
        System.out.println("Get most amounted manager - 1");
        System.out.println("Get all users by manager name - 2");
        System.out.println("Get all managers by dept number - 3");
        System.out.println("Get user by ammount - 4");
        System.out.println("Get most result user by manager name - 5");
        System.out.println("Get amount by dept - 6");
        System.out.println("Get manager by user name and user lastName 7");
        System.out.println("Get users by dept number 8");
        System.out.println("Get manager ande user by dept number 9");
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();



        while (n!=0) {
            switch (n) {
                case 1:
                    Manger manger = getMostAmountedManager(map);
                    System.out.println(manger);
                    break;
                case 2:
                    System.out.println("Enter manager's name");
                    scanner.nextLine();
                    String name = scanner.nextLine();
                    List<User> list = getUserByManagerName(map, name);
                    if (list.isEmpty()) {
                        System.out.println("It is no managers with this name");
                    } else {
                        System.out.println(list);
                    }
                    break;
                case 3:
                    System.out.println("Enter dept number");
                    int number = scanner.nextInt();
                    Set<Manger> set = getManagerByDept(number, map);
                    System.out.println(set);
                    break;
                case 5:
                    System.out.println("Enter manager name");
                    scanner.nextLine();
                    String nameOfManager = scanner.nextLine();
                    User user = getMostResultUserByManagerName(map, nameOfManager);
                    System.out.println(user);
                    break;
                case 6:
                    System.out.println("Enter dept number");
                    number = scanner.nextInt();
                    int deptAmount = getAmountByDept(number, map);
                    System.out.println(deptAmount);
                    break;
                case 7:
                    System.out.println("Enter name and last name of user");
                    scanner.nextLine();
                    String nameOfUser = scanner.nextLine();
                    String lastNameOfUser = scanner.nextLine();
                    Manger manager = getManagerByUserNameAndLastName(map, nameOfUser, lastNameOfUser);
                    System.out.println(manager);
                    break;
                case 8:
                    System.out.println("Enter dept number");
                    scanner.nextLine();
                    int dept = scanner.nextInt();
                    Set<User> setOfUsers = getUsersByDeptNumber(map, dept);
                    System.out.println(setOfUsers);
                    break;


            }
            System.out.println("Choose command");
            System.out.println("Type 0 to close program");
            System.out.println("Get most amounted manager - 1");
            System.out.println("Get all users by manager name - 2");
            System.out.println("Get all managers by dept number - 3");
            System.out.println("Get most result user by manager name - 5");
            System.out.println("Get amount by dept - 6");
            System.out.println("Get manager by user name and user lastName 7");
            System.out.println("Get users by dept number 8");
            n = scanner.nextInt();
        }
    }

    public static Set<User> getUsersByDeptNumber (Map<User, Manger> map, int dept){
        Set<User> setOfUsers = new HashSet<>();
        for (Map.Entry<User, Manger> entry: map.entrySet()){
            if (entry.getValue().getDept() == dept){
                setOfUsers.add(entry.getKey());
            }
        }
        return setOfUsers;
    }

    public static Manger getManagerByUserNameAndLastName (Map<User, Manger> map, String nameOfUser, String lastNameOfUser){
        Manger manager = new Manger("Name", 0);
        for (Map.Entry <User, Manger> entry: map.entrySet()) {
            if (entry.getKey().getName().equals(nameOfUser) && entry.getKey().getLastName().equals(lastNameOfUser)){
                manager = entry.getValue();
            }
        }
        return manager;
    }

    public static int getAmountByDept(int number, Map<User, Manger> map) {
        Set<Manger> set = getManagerByDept(number, map);
        int deptAmount = 0;
        for (Manger m : set) {
            if (m.getDept() == number) {
                deptAmount += m.getAmount();
            }
        }
        return deptAmount;
    }
    public static User getMostResultUserByManagerName (Map<User, Manger> map, String nameOfManager){
        int result = 0;
        User user = new User("", "", 0, 0);
        for (Map.Entry<User, Manger> entry: map.entrySet()){
            if (entry.getValue().getName().equals(nameOfManager)){
                if (result < entry.getKey().getSum()){
                    result = entry.getKey().getSum();
                    user = entry.getKey();
                }
            }
        }
        return user;
    }
    public static Map<User, Manger> initAmount(Map<User, Manger> map){
        for (Map.Entry<User, Manger> entry: map.entrySet()){
            entry.getValue().setAmount(entry.getKey().getSum());
        }
        return map;
    }

    public static Set<Manger> getManagerByDept(int number, Map<User, Manger> map){
        Set<Manger> set = new HashSet<>();
        for (Map.Entry<User, Manger> entry: map.entrySet()){
            if (entry.getValue().getDept()==number){
                set.add(entry.getValue());
            }
        }
        return set;
    }
    public static List<User> getUserByManagerName(Map<User, Manger> map,
                                                  String name){
        List<User> list = new ArrayList<>();
        for (Map.Entry<User, Manger> entry : map.entrySet()){
            if (entry.getValue().getName().equals(name)){
                list.add(entry.getKey());
            }
        }
        return list;
    }
    public static Manger getMostAmountedManager(Map<User, Manger> map){
        Manger manger = new Manger("1", 0);
        for (Map.Entry<User, Manger> entry: map.entrySet()){
            if (entry.getValue().getAmount()>manger.getAmount()){
                manger = entry.getValue();
            }
        }
        return manger;
    }
    public static Map<User, Manger> init(){
        Map<User, Manger> map = new HashMap<>();

        Manger manger = new Manger("Nikita", 1);
        Manger manger1 = new Manger("Nikolay",3);

        User user = new User("Ivan", "Ivanov", 12, 100);
        User user1 = new User("Sergey", "Ivanov", 12, 100);
        User user2 = new User("Petr", "Petrov", 12, 100);
        User user3 = new User("Nikolay", "Nikolaev", 12, 100);
        User user4 = new User("Sidr", "Sidorov", 12, 100);

        map.put(user, manger);
        map.put(user1, manger1);
        map.put(user2, manger1);
        map.put(user3, manger);
        map.put(user4, manger);

        return map;
    }
}
