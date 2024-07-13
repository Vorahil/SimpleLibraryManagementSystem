package LibraryManagementSystem;

import java.io.Serializable;

public class Book implements Serializable{//大体是建造者模式
    private  String name;
    private  String author;
    private  double price;
    public void setAuthor(String author) {//修改操作
        this.author = author;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public void setName(String name) {
        this.name= name;
    }
    private Book(String name, String author, double price){
        this.name = name;
        this.author = author;
        this.price = price;
    }

    @Override
    public String toString() {
        return " 《"+name+"》 "+"作者："+author+"  "+price+"￥";
    }

    public static BookBuilder builder(){//Book转为BookBuilder
        return new BookBuilder();
    }
    public static class BookBuilder  {
        private String name;
        private String author;
        private double price;
        private BookBuilder(){};
        public BookBuilder name(String name){
            this.name = name;
            System.out.println("请输入作者");
            return this;
        }
        public BookBuilder author(String author){
            this.author = author;
            System.out.println("请输入价格");
            return this;
        }
        public BookBuilder price(double price){
            this.price = price;
            return this;
        }

        public Book build(){//搞完在回到Book
            return new Book(name, author, price);
        }

    }
}
