package pl.kurs.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
@RequiredArgsConstructor
public class UserInputService {
    private final Scanner scanner;

    public String readCurrency() {
        System.out.println("Podaj walutę inwestycji (użyj formatu USD, EUR, ect.): ");
        return scanner.nextLine().toUpperCase();
    }

    public double readAmount() {
        System.out.println("Podaj kwotę inwestycji: ");
        return scanner.nextDouble();
    }

    public String readStartDate() {
        System.out.println("Podaj datę początku inwestycji (użyj formatu 10-05-2015): ");
        return scanner.next();
    }

    public String readEndDate() {
        System.out.println("Podaj datę końca inwestycji (użyj formatu 10-05-2015, wciskając enter będzie to data dzisiejsza): ");
        String input = scanner.nextLine();
        return input.isEmpty() ? null : input ;
    }

}
