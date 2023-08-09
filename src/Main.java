import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Login masuk = new Login();
        masuk.logmasuk();               // class dan object

        Menu option = new Menu();
        option.menu();

    }
}
