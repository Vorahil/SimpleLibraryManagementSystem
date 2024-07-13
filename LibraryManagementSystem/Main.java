package LibraryManagementSystem;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<Book> LIST;//用列表保存书籍

    public static void main(String[] args) {
        System.out.println("正在初始化图书管理系统");
        load();//加载
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("=====欢迎使用图书管理系统=====");
            System.out.println("1.录入书籍信息");
            System.out.println("2.查询书籍信息");
            System.out.println("3.删除书籍信息");
            System.out.println("4.修改书籍信息");
            System.out.println("5.退出系统");
            System.out.println("===========================");
            switch (sc.nextInt()) {
                case 1:
                    insert(sc);
                    break;
                case 2:
                    list();
                    break;
                case 3:
                    delete(sc);
                    break;
                case 4:
                    modify(sc);
                    break;
                case 5:
                    System.out.println("正在保存数据");
                    save();//保存
                    System.out.println("欢迎下次使用");
                    System.exit(0);
                default:
                    System.out.println("请输入指定的数字");
            }
        }
    }

    @SuppressWarnings("unchecked")//忽略未检查的类型转换警告
    private static void load() {//加载
        File file = new File("books.txt");
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {//对象流
                LIST = (List<Book>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            LIST = new LinkedList<>();//空表则重新写一个链表
        }
    }

    private static void save() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("books.txt"))) {
            oos.writeObject(LIST);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void insert(Scanner sc) {//录入
        System.out.println("请输入书名");
        Book book = Book.builder()
                .name(sc.next())
                .author(sc.next())
                .price(sc.nextDouble())
                .build();
        LIST.add(book);
        System.out.println("录入完毕");
        System.out.println(book);
    }

    private static void list() {//查看
        for (int i = 0; i < LIST.size(); i++) {
            System.out.println((i + 1) + "." + LIST.get(i));
        }
    }
    private static void delete(Scanner sc) {//删除
        if (LIST.isEmpty()) {
            System.out.println("请录入书籍");
            return;
        }
        System.out.println("输入您想要删除的书籍的编号");
        int id=sc.nextInt()-1;
        while (id<0||id>LIST.size()-1) {
            System.out.println("请输入正确的编号");
            id = sc.nextInt()-1;
        }
        LIST.remove(id);
        System.out.println("删除成功");
    }
    private static void modify(Scanner sc) {//修改
        if (LIST.isEmpty()) {
            System.out.println("请录入书籍");
            return;
        }
        System.out.println("输入您想要修改的书籍的编号");
        int id=sc.nextInt()-1;
        while (id<0||id>LIST.size()-1) {
            System.out.println("请输入正确的编号");
            id = sc.nextInt()-1;
        }
        Book book = LIST.get(id);
        System.out.println("输入书籍名称");
        book.setName(sc.next());
        System.out.println("输入作者");
        book.setAuthor(sc.next());
        System.out.println("输入价格");
        book.setPrice(sc.nextDouble());
        System.out.println("书籍修改成功");
        System.out.println(book);
    }
}
