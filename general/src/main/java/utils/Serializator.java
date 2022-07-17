package utils;

import java.io.*;


public class Serializator {
    public static <T> T deserializeObject(byte[] buffer) {
        try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(buffer);
             ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream)) {
            return (T) objectInputStream.readObject();
        }
         catch (IOException | ClassNotFoundException e ) {
            System.out.println("Something went wrong :3");
        }
        return null;
    }

    public static <T> byte[] serializeObject(T object) {
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream)) {
            objectOutputStream.writeObject(object);
            byte[] arr = byteArrayOutputStream.toByteArray();
            objectOutputStream.flush();
            return arr;
        } catch (IOException e) {
            System.out.println("Ошибка сериализации");
            e.printStackTrace();
        }
        return null;
    }
}
