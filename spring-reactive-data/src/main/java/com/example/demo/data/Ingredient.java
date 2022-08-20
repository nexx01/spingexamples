package com.example.demo.data;

import lombok.*;
import org.springframework.data.annotation.Id;
// Spring Data R2DBC требует, чтобы свойства имели методы до-
//ступа, поэтому большинство свойств определено как нефиналь-
//ные. Но чтобы помочь библиотеке Lombok создать обязательный
//конструктор с аргументами, мы снабдили большинство свойств
//аннотацией @NonNull
//Благодаря этому Lombok и аннотация @Re-
//quiredArgsConstructor включат эти свойства в конструктор;

//при попытке сохранить в репозиторий Spring Data R2DBC объект
//с ненулевым значением свойства id операция будет рассматри-
//ваться как изменение имеющейся сущности. Свойство id класса
//Ingredient раньше было объявлено с  типом String. Но при ис-
//пользовании Spring Data R2DBC это приведет к ошибке. Поэтому
//здесь мы преобразовали свойство идентификатора в новое стро-
//ковое свойство с  именем slug, которое является псевдоиденти-
//фикатором ингредиента, а  для идентификации в  базе данных
//используем целочисленное свойство Long id, значение которого
//генерируется самой базой данных.
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(exclude = "id")

public class Ingredient {

    @Id
    private Long id;

    private @NonNull String slug;

    private @NonNull String name;

    private @NonNull Type type;

    public enum Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }
}
