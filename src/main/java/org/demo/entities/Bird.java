package org.demo.entities;

import org.springframework.lang.NonNull;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * Bird.
 *
 * Должны быть следущие свойства:
 *
 * name  тип String
 * livingArea тип String
 * size – число с плавающей точкой, может быть незаполненным (Double).
 *
 */
public class Bird {
    @NotNull (message = "Name couldn't be null")
    private String name;
    @NotNull
    private String livingArea;
    @NonNull @Min(value = 1, message = "Size should have value from 1 to 3") @Max(value = 3, message = "Size should have value from 1 to 3")
    private Double size;

    public Bird(String name, String livingArea, Double size) {
        this.name = name;
        this.livingArea = livingArea;
        this.size = size;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Bird{");
        sb.append("name='").append(name).append('\'');
        sb.append(", livingArea='").append(livingArea).append('\'');
        sb.append(", size=").append(size);
        sb.append('}');
        return sb.toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLivingArea() {
        return livingArea;
    }

    public void setLivingArea(String livingArea) {
        this.livingArea = livingArea;
    }

    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bird bird = (Bird) o;
        return Objects.equals(name, bird.name) &&
                Objects.equals(livingArea, bird.livingArea) &&
                Objects.equals(size, bird.size);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, livingArea, size);
    }
}
