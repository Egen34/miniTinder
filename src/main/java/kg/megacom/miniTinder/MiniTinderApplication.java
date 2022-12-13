package kg.megacom.miniTinder;

import kg.megacom.miniTinder.models.Orders;
import kg.megacom.miniTinder.models.Users;
import kg.megacom.miniTinder.models.enums.Gender;
import kg.megacom.miniTinder.services.OperationService;
import kg.megacom.miniTinder.services.OrderService;
import kg.megacom.miniTinder.services.UserService;
import kg.megacom.miniTinder.services.impl.OperationServiceImpl;
import kg.megacom.miniTinder.services.impl.OrderServiceImpl;
import kg.megacom.miniTinder.services.impl.UserServiceImpl;

import java.util.List;
import java.util.Scanner;

public class MiniTinderApplication {

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        UserService userService = new UserServiceImpl();
        OperationService operationService = new OperationServiceImpl();
        OrderService orderService=new OrderServiceImpl();
        lebel1:
        while (true) {
            Users user = new Users();
            System.out.println("1)Ввойти" +
                    "\n2)Регистрация" +
                    "            \n3)ВЫйти");
            switch (scn.nextInt()) {
                case 1 -> {
                    scn.nextLine();
                    System.out.println("Введите логин: ");
                    String login = scn.nextLine();
                    System.out.println("Введите пороль: ");
                    String pass = scn.nextLine();
                    Long log = operationService.logIn(login, pass);
                    if (log == 0l) {
                        System.out.println("Не правильный логин или/и пороль");
                        continue;
                    }
                    user = userService.findById(log);
                }
                case 2 -> {
                    operationService.registration();
                    continue;
                }
                case 3 -> {
                    break lebel1;
                }
                default -> {
                    continue;
                }


            }
            while (true) {
                System.out.println("1)Интересные люди" +
                        "\n2)Подписчики (" + operationService.followersCount(user.getId()) + ")" +
                        "\n3)Подписки (" + operationService.followingsCount(user.getId()) + ")" +
                        "\n4)Выйти");
//
                switch (scn.nextInt()) {
                    case 1 -> {
                        int i = 0;
                        while (true) {
                            List<Users> list= operationService.recomended4You(user.getId(), i, 1);

                            if(list.size()==0)break;
                            Users rec =list.get(0);
                            System.out.println(rec+
                                    "\n1)подписатся" +
                                    "\n2)далее" +
                                    "\n3)выйти");
                            switch (scn.nextInt()){
                                case 1->{
                                    Orders order=new Orders(user,rec,true);
                                    orderService.fallow(order);
                                    System.out.println("Вы подписалис на "+rec.getName());
                                }
                                case 2->{i++;}
                                case 3->{break lebel1;}
                                default -> {continue;}
                            }

                        }

                    }
                    case 2->{
                        for (Users u: operationService.followers(user.getId())){
                            System.out.println(u);
                        }

                    }
                    case 3->{
                        for (Users u: operationService.followings(user.getId())){
                            System.out.println(u);
                        }
                    }

                }
            }

        }


    }

}
