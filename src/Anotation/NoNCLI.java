package Anotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface NoNCLI {
    String value() default "Внутренняя операция без ввод/вывод";
}
