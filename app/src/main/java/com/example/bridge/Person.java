package com.example.bridge;

import java.util.Objects;

public class Person {
    private String name;
    private Integer year;

    // 1. Конструкторы
    public Person() {
    }

    public Person(String name, Integer year) {
        this.name = name;
        this.year = year;
    }

    // 2. Геттеры и сеттеры
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    // 3. equals() и hashCode()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name) &&
                Objects.equals(year, person.year);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, year);
    }
}