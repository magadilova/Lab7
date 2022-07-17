package workers;



import dto.*;
import dto.enumdto.CountryDTO;
import dto.enumdto.EyeColorDTO;
import dto.enumdto.HairColorDTO;
import dto.enumdto.UnitOfMeasureDTO;
import workers.exceptions.FieldException;

import java.io.Console;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.UUID;


/**
 * Класс, отвечающий за добавления элемента в коллекцию
 */

public class Asker {
    private Scanner scanner = null;
    private Console console = null;

    public Asker(Scanner scanner) {
        this.scanner = scanner;
    }

    public Asker(Console console) {
        this.console = console;
    }


    public UserDTO makeUserDTO(){
        String login = askLogin();
        String password = askPassword();
        return new UserDTO(login, password);
    }

    /**
     *
     * @return name
     */

    private String askLogin() {
        String login;
        while (true) {
            try {
                System.out.println("Enter login");
                login = console.readLine().trim();
                if (login.isEmpty()){
                    throw new FieldException("Login can not be empty \nPlease try again");
                }
                break;
            } catch (FieldException e) {
                System.out.println(e.getMessage());
            }
        }
        return login;
    }


    private String askPassword() {
        String password;
        while (true) {
            try {
                System.out.println("Enter password");
                char[] chars = console.readPassword();
                password = new String(chars);
                if (password.isEmpty()){
                    throw new FieldException("Password can not be empty \nPlease try again");
                }
                break;
            } catch (FieldException e) {
                System.out.println(e.getMessage());
            }
        }
        return password;
    }

    private String askName() {
        String name;
        while (true) {
            try {
                System.out.println("Enter product's name");
                name = console.readLine().trim();
                if (name.isEmpty()){
                    throw new FieldException("model.Product's name can not be empty \nPlease try again");
                }
                break;
            } catch (FieldException e) {
                System.out.println(e.getMessage());
            }
        }

        return name;
    }


    /**
     *
     * @return x
     */

    private Integer askX() {
        String strX;
        Integer x;
        while (true) {
            try {
                System.out.println("Enter X coordinate (integer):");
                strX = console.readLine().trim();
                if (strX.isEmpty()){
                    throw new FieldException("X coordinate can not be null \nPlease try again");
                }
                x = Integer.parseInt(strX);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Wrong format! Format must be integer.");

            } catch (FieldException e) {
                System.out.println(e.getMessage());

            }
        }
        return x;
    }

    /**
     *
     * @return y
     */

    private Long askY() {
        String strY;
        Long y;
        while (true) {
            try {
                System.out.println("Enter Y coordinate (long)");
                strY = console.readLine().trim();
                if (strY.isEmpty()){
                    throw new FieldException("Y coordinates can not be null \nPlease try again");
                }
                y = Long.parseLong(strY);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Wrong format! Format must be long.");
            } catch (FieldException e) {
                System.out.println(e.getMessage());

            }
        }
        return y;
    }

    private CoordinatesDTO coordinates() {
        Integer x = askX();
        Long y = askY();
        return new CoordinatesDTO(x, y);
    }

    /**
     *
     * @return price
     */

    private double askPrice() {
        String strPrice;
        double price;
        while (true) {
            try {
                System.out.println("Enter price (double)");
                strPrice = console.readLine().trim();
                price = Double.parseDouble(strPrice);
                if(price <= 0){
                    throw new FieldException("Price can not be less or equal zero \nPlease try again");
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Wrong format! Format must be double.");
            } catch (FieldException e) {
                System.out.println(e.getMessage());

            }
        }
        return price;
    }

    /**
     *
     * @return partNumber
     */

    private String askPartNumber() {
        String partNumber;
        while (true) {
            try {
                System.out.println("Enter part number");
                partNumber = console.readLine().trim();
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                System.out.println("Please try again");
            }
        }
        return partNumber;
    }

    /**
     *
     * @return cost
     */

    private long askManufactureCost() {
        String strCost;
        long cost;
        while (true) {
            try {
                System.out.println("Enter manufacture cost (long)");
                strCost = console.readLine().trim();
                cost = Long.parseLong(strCost);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Wrong format! Format must be double.");
                System.out.println("Please try again");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return cost;
    }

    /**
     *
     * @return unit
     */

    private UnitOfMeasureDTO askUnitOfMeasure() {
        String strUnit;
        UnitOfMeasureDTO unit;
        while (true) {
            try {
                System.out.println("Choose unit of measure: ");
                UnitOfMeasureDTO.showUnitOfMeasureList();
                strUnit = console.readLine().trim().toUpperCase();
                unit = UnitOfMeasureDTO.valueOf(strUnit);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Unit of measure wasn't found. \n "
                        + "Please try again. Must be entered as shown in the list.");
            }
        }
        return unit;
    }

    /**
     *
     * @return nameOwner
     */

    private String askOwnerName() {
        String nameOwner;
        while (true) {
            try {
                System.out.println("Enter owner's name");
                nameOwner = console.readLine().trim();
                if (nameOwner.isEmpty()){
                    throw new FieldException("Owner can not be null \nPlease try again");
                }
                break;
            } catch (FieldException e) {
                System.out.println(e.getMessage());
            }
        }
        return nameOwner;
    }

    /**
     *
     * @return color
     */

    private EyeColorDTO askOwnerEyeColor() {
        String strColor;
        EyeColorDTO color;
        while (true) {
            try {
                System.out.println("Choose eyes' color: ");
                EyeColorDTO.showEyeColorsList();
                strColor = console.readLine().trim().toUpperCase();
                if (strColor.isEmpty()){
                    throw new FieldException("Eye color can not be null \n Please try again");
                }
                color = EyeColorDTO.valueOf(strColor);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Eyes color wasn't found. \n"
                        + "Please try again. Must be entered as shown in the list.");
            } catch (FieldException e) {
                System.out.println(e.getMessage());
            }
        }
        return color;
    }

    /**
     *
     * @return color
     */

    private HairColorDTO askOwnerHairColor() {
        String strColor;
        HairColorDTO color;
        while (true) {
            try {
                System.out.println("Choose hairs' color:");
                HairColorDTO.showHairColorList();
                strColor = console.readLine().trim().toUpperCase();
                if (strColor.isEmpty()){
                    throw new FieldException("Hair color can not be null \n Please try again");
                }
                color = HairColorDTO.valueOf(strColor);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Hairs color wasn't found. \n"
                        + "Please try again. Must be entered as shown in the list.");
            } catch (FieldException e) {
                System.out.println(e.getMessage());
            }
        }
        return color;
    }

    /**
     *
     * @return country
     */

    private CountryDTO askOwnerCountry() {
        String strCountry;
        CountryDTO country;
        while (true) {
            try {
                System.out.println("Choose country:");
                CountryDTO.showCountryList();
                strCountry = console.readLine().trim().toUpperCase();
                if (strCountry.isEmpty()){strCountry = null;}
                country = CountryDTO.valueOf(strCountry);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("model.Country wasn't found. \n"
                        + "Please try again. Must be entered as shown in the list.");
            }
        }
        return country;
    }

    /**
     *
     * @return x
     */

    private float askOwnerX() {
        String strX;
        float x;
        while (true) {
            try {
                System.out.println("Enter X coordinate (float)");
                strX = console.readLine().trim();
                x = Float.parseFloat(strX);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Wrong format! Format must be float.");
                System.out.println("Please try again");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return x;
    }

    /**
     *
     * @return y
     */

    private double askOwnerY() {
        String strY;
        double y;
        while (true) {
            try {
                System.out.println("Enter Y coordinate (double)");
                strY = console.readLine().trim();
                y = Double.parseDouble(strY);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Wrong format! Format must be double.");
                System.out.println("Please try again");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return y;
    }

    /**
     *
     * @return z
     */

    private double askOwnerZ() {
        String strZ;
        double z;
        while (true) {
            try {
                System.out.println("Enter Z coordinate (double)");
                strZ = console.readLine().trim();
                z = Double.parseDouble(strZ);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Wrong format! Format must be double.");
                System.out.println("Please try again");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return z;
    }

    private LocationDTO location() {
        float x = askOwnerX();
        double y = askOwnerY();
        double z = askOwnerZ();
        return new LocationDTO(x, y, z);
    }
    private PersonDTO owner(){
        String passportID = String.valueOf(Math.abs(UUID.randomUUID().hashCode()));
        String name = askOwnerName();
        EyeColorDTO eyeColor = askOwnerEyeColor();
        HairColorDTO hairColor = askOwnerHairColor();
        CountryDTO country = askOwnerCountry();
        LocationDTO location = location();
        return new PersonDTO(name, passportID, eyeColor,hairColor,country,location);
    }

/*    public ProductDTO startAsker(){
        String name = askName();
        CoordinatesDTO coordinates = coordinates();
        double price = askPrice();
        String partNumber = askPartNumber();
        long manufactureCost = askManufactureCost();
        UnitOfMeasureDTO unitOfMeasure = askUnitOfMeasure();
        PersonDTO owner = owner();
        LocalDate creationDate = LocalDate.now();
        return new ProductDTO(name, coordinates,creationDate, price,partNumber,manufactureCost,unitOfMeasure,owner);
    }*/

    public ProductDTO makeDto(){
        String name = askName();
        CoordinatesDTO coordinates = coordinates();
        double price = askPrice();
        String partNumber = askPartNumber();
        long manufactureCost = askManufactureCost();
        UnitOfMeasureDTO unitOfMeasure = askUnitOfMeasure();
        PersonDTO owner = owner();
        LocalDate creationDate = LocalDate.now();
        return new ProductDTO(name,coordinates,creationDate, price,partNumber,manufactureCost,unitOfMeasure,owner);
    }


}
