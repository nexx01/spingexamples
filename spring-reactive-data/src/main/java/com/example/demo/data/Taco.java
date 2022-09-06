package com.example.demo.data;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Taco {

    @Id
    private Long id;

    private @NonNull String name;

    private Set<Long> ingredientIds = new HashSet<>();

    public void addIngredient(Ingredient ingredient) {
        ingredientIds.add(ingredient.getId());
    }
}

//В отличие от других проектов Spring Data, Spring Data R2DBC в на-
//стоящее время не поддерживает прямые отношения между сущно-
//стями (по крайней мере пока). В относительно новом проекте Spring
//Data R2DBC все еще не решены некоторые проблемы обработки от-
//ношений неблокирующим способом. Но ситуация может измениться
//в будущих версиях Spring Data R2DBC.
//А до тех пор мы не можем использовать в Taco ссылки на ингреди-
//енты и  ожидать, что механизм хранения обработает их правильно.
//Поэтому нам остается только несколько вариантов представления от-
//ношений:
// определить сущности со ссылками на идентификаторы связан-
//ных объектов. В этом случае соответствующий столбец в таблице
//базы данных должен быть определен как массив, если это воз-
//можно. Столбцы-массивы поддерживаются базами данных H2
//и  PostgreSQL, но не поддерживаются многими другими. Кроме
//того, даже если база данных поддерживает столбцы-массивы,
//она может не поддерживать определение внешних ключей на ос-
//нове этих столбцов, ссылающихся на другие таблицы, что делает
//невозможным обеспечение ссылочной целостности;
// определить сущности и соответствующие им таблицы так, что-
//бы они идеально соответствовали друг другу. Для коллекций это
//означает, что сущность, на которую указывает ссылка, должна
//иметь столбец с обратной ссылкой на ссылающуюся таблицу. На-
//пример, в таблице для сущностей Taco должен быть столбец, ссы-
//лающийся на сущность TacoOrder, частью которой является Taco;
// сериализовать сущности в  формат в  JSON и  хранить определе-
//ние JSON в  столбце VARCHAR. Этот прием особенно хорош, если
//нет необходимости запрашивать объекты по ссылкам. Однако
//у  него есть потенциальное ограничение на размер сериализо-
//ванных объектов JSON, обусловленное ограничением длины со-
//ответствующего столбца VARCHAR. Более того, в  этом случае нет
//никакой возможности использовать схему базы данных, чтобы
//гарантировать ссылочную целостность, потому что объекты, на
//        которые указывают ссылки, будут храниться как простое строко-
//        вое значение (которое может содержать что угодно).