package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Comparator;

@AllArgsConstructor
public enum LabelEnum {
    NAZWA_PRODUCENTA("Nazwa producenta", 1),
    WIELKOSC_MATRYCY("Wielkość matrycy", 2),
    ROZDZIELCZOSC("Rozdzielczość", 3),
    TYP_MATRYCY("Typ matrycy", 4),
    CZY_DOTYKOWA("Czy dotykowa", 5),
    PROCESOR("Procesor", 6),
    LICZBA_RDZENI_FIZYCZNYCH("Liczba rdzeni fizycznych", 7),
    TAKTOWANIE("Taktowanie", 8),
    RAM("RAM", 9),
    POJEMNOSC_DYSKU("Pojemność dysku", 10),
    TYP_DYSKU("Typ dysku", 11),
    KARTA_GRAFICZNA("Karta graficzna", 12),
    PAMIEC_KARTY_GRAFICZNEJ("Pamięć karty graficznej", 13),
    SYSTEM_OPERACYJNY("System operacyjny", 14),
    NAPED_OPTYCZNY("Napęd optyczny", 15);

    @Getter
    private final String label;
    @Getter
    private final Integer priority;


    public static LabelEnum[] sortedValues() {
        LabelEnum[] values = LabelEnum.values();
        Arrays.sort(values, Comparator.comparingInt(LabelEnum::getPriority));
        return values;
    }
}
