/*
Информация по самим дракончикам коллекции
 */
package org.example.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Dragon implements Serializable {
    private final Integer id;
    private final String name;
    private final Coordinates coordinates;
    private final LocalDateTime creationDate;
    private final Integer age;
    private final String description;
    private final Double wingspan;
    private final DragonCharacter character;
    private final DragonCave cave;

    public Dragon(
            Integer id,
            String name,
            Coordinates coordinates,
            LocalDateTime creationDate,
            Integer age,
            String description,
            Double wingspan,
            DragonCharacter character,
            DragonCave cave
    ) {
        validateParameters(id, name, coordinates, creationDate, age, description, wingspan, character, cave);

        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.age = age;
        this.description = description;
        this.wingspan = wingspan;
        this.character = character;
        this.cave = cave;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public Integer getAge() {
        return age;
    }

    public String getDescription() {
        return description;
    }

    public Double getWingspan() {
        return wingspan;
    }

    public DragonCharacter getCharacter() {
        return character;
    }

    public DragonCave getCave() {
        return cave;
    }

    private static void validateParameters(
            Integer id,
            String name,
            Coordinates coordinates,
            LocalDateTime creationDate,
            Integer age,
            String description,
            Double wingspan,
            DragonCharacter character,
            DragonCave cave
    ) {
        if (id == null) {
            throw new NullPointerException("Id cannot be null");
        }
        if (id <= 0) {
            throw new IllegalArgumentException("Id must be greater than 0");
        }

        if (name == null) {
            throw new NullPointerException("Name cannot be null");
        }
        if (name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty string");
        }

        if (coordinates == null) {
            throw new NullPointerException("Coordinates cannot be null");
        }

        if (creationDate == null) {
            throw new NullPointerException("Creation date cannot be null");
        }

        if (age == null) {
            throw new NullPointerException("Age cannot be null");
        }
        if (age <= 0) {
            throw new IllegalArgumentException("Age must be greater than 0");
        }

        if (description == null) {
            throw new NullPointerException("Description cannot be null");
        }

        if (wingspan == null) {
            throw new NullPointerException("Wingspan cannot be null");
        }
        if (wingspan <= 0) {
            throw new IllegalArgumentException("Wingspan must be greater than 0");
        }

        if (character == null) {
            throw new NullPointerException("Character cannot be null");
        }

        if (cave == null) {
            throw new NullPointerException("Cave cannot be null");
        }
    }
}
