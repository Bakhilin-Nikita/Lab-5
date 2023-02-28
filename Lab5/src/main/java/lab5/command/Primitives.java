package lab5.command;

import lab5.exceptions.ValidityNumber;
import lab5.phrasesToUser.Phrase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Primitives {
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public Integer enterDigit() {
        boolean flag = false;
        Integer res = null;
        while (!flag) {
            try {
                Phrase.addOrNot();
                res = Integer.parseInt(reader.readLine());
                if (res == null) throw new NullPointerException("Пустая строка!");
                if (res == 1 || res == 2) {
                    flag = true;
                } else {
                    throw new ValidityNumber("Некорректный ввод! Попробуйте еще раз.");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            }
        }

        return res;
    }

    public String nameOfLab() {
        boolean flag = false;
        String name = null;
        while (!flag) {
            try {
                Phrase.nameLab();
                name = reader.readLine();
                if (name.isEmpty())
                    throw new IOException("Пустая строка, попробуйте еще раз!");
                else
                    flag = true;
            } catch(IOException e) {
                System.out.println(e.getMessage());
            }
        }
        return name;
    }

    public int minimalPoint() {
        boolean flag = false;
        Integer res = 0;
        while (!flag) {
            Phrase.minimalPoint();
            try {
                res = Integer.parseInt(reader.readLine());
                if (res == null) throw new NullPointerException("Пустая строка!");
                if (res <= 100) {
                    flag = true;
                } else {
                    throw new ValidityNumber("Некорректный ввод или неправильное колличество поинтов! Попробуйте еще раз.");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            }
        }
        return res;
    }

    public int tunedInWorks() {
        boolean flag = false;
        Integer res = 0;
        while (!flag) {
            Phrase.tunedInWorks();
            try {
                res = Integer.parseInt(reader.readLine());
                if (res == null) throw new NullPointerException("Пустая строка!");
                if (res <= 100) {
                    flag = true;
                } else {
                    throw new ValidityNumber("Некорректный ввод или неправильное колличество поинтов! Попробуйте еще раз.");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            }
        }
        return res;
    }

    public String difficulty(){
        boolean flag = false;
        String res = null;
        while (!flag) {
            Phrase.difficulty();
            try {
                res = reader.readLine();
                if (res.isEmpty())
                    throw new IOException("Попробуйте еще раз!");
                flag = true;
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

        return res;
    }
}
