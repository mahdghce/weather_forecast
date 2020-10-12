package ir.aut.ceit.app;

import ir.aut.ceit.app.application.Menu;
import org.json.JSONException;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws JSONException, IOException {
        Menu menu = new Menu();
        menu.menu();
    }
}
