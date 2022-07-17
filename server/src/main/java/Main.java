import org.flywaydb.core.Flyway;
import servermodule.Server;
import utils.HibernateUtils;
import utils.exceptions.ReadingFilePropertiesException;

import java.io.Console;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        try {
            initTables();
            Server server = new Server(50005);
            server.start();
            server.run();

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("File not found X_X");
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (ReadingFilePropertiesException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }








    private static void initTables() {
        try {
            Map<String, String> databaseProperties = getDatabaseProperties();
            Flyway flyway = Flyway.configure().dataSource(
                    databaseProperties.get("url"),
                    databaseProperties.get("username"),
                    databaseProperties.get("password")).
                    locations("classpath:/db/migration").
                    load();
            flyway.migrate();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Map<String, String> getDatabaseProperties() throws IOException {
        try (InputStream is = Main.class.getClassLoader().getResourceAsStream("flyway_conf.properties")) {
            Properties properties = new Properties();
            properties.load(is);
            Map<String, String> map = new HashMap<>();
            map.put("url", properties.getProperty("flyway.url"));
            map.put("username", properties.getProperty("flyway.username"));
            map.put("password", properties.getProperty("flyway.password"));
            return map;
        }
    }

    private static void addShutdownHandler() {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                System.exit(0);
            }
        });
        new Thread(() -> {
            while (true) {
                Console console = System.console();
                String input = console.readLine().trim();
                if (input.equals("exit")) {
                    HibernateUtils.getSessionFactory().close();
                    System.exit(0);
                }
            }
        }).start();
    }
}


