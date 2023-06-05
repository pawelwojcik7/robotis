package org.example;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public enum Producers {
    DELL(0), ASUS(1), FUJITSU(2), HUAWEI(3), MSI(4), SAMSUNG(4), SONY(5);

    @Getter
    private final Integer number;
}
