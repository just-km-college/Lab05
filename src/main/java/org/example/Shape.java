package org.example;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;

import java.util.Scanner;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"  // This can be the name of the field holding type information
)

@JsonSubTypes({
        @JsonSubTypes.Type(value = Rectangle.class, name = "rectangle"),
        @JsonSubTypes.Type(value = Triangle.class, name = "triangle")
})
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
public abstract class Shape {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Embedded
    private Color color;

    public Shape(Color color) {
        this.color = color;
    }

    public Shape() {}

    public abstract Shape update(Scanner scanner);
    abstract double getArea();
    abstract double getPerimeter();

    public long getId() {
        return id;
    }

    public void setId(long ID) {
        id = ID;
    }

    public Color getColor() {
        return this.color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getColorDescription() {
        return "Alpha: " + this.color.alfa() + " Red: " + this.color.red() + " Green: " + this.color.green() + " Blue: " + this.color.blue() + "\n";
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", color=" + color +
                '}';
    }
}