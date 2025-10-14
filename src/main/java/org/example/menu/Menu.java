package org.example.menu;

import org.example.ConsoleApp;

public interface Menu {
    Menu show(ConsoleApp consoleApp);

    void printMenu();
}
