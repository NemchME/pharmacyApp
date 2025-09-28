package org.example.menu;

import org.example.ConsoleApp;

public interface CrudMenu {
    Menu findAllEntities(ConsoleApp consoleApp);
    Menu createEntity(ConsoleApp consoleApp);
    Menu readEntity(ConsoleApp consoleApp);
    Menu updateEntity(ConsoleApp consoleApp);
    Menu deleteEntity(ConsoleApp consoleApp);
}
