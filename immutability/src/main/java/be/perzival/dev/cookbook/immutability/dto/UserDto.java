package be.perzival.dev.cookbook.immutability.dto;

import be.perzival.dev.cookbook.immutability.AllowNulls;
import be.perzival.dev.cookbook.immutability.ImmutableWithStaticConstructor;
import org.immutables.value.Value;

import java.time.LocalDate;
import java.util.List;

@Value.Immutable
@ImmutableWithStaticConstructor
public interface UserDto extends User {
    //That's all for basic
    //You're not force to put more code !

    //You can override specification for better control
    @Override
    @Value.Parameter(order = 0) // used to specify constructor parameter for static constructor of()
    String getFirstName();

    @Override
    @Value.Parameter(order = 1) // order is not mandatory for constructor and can be omitted
    String getLastName();

    @Override
    @AllowNulls //Specify whether a field is nullable or not for List use @AllowNulls (to have emptyList) for other than collections use @Nullable
    List<Integer> getLuckyNumberList();

    @Override //Default Value
    default LocalDate getBirthDate(){ return LocalDate.now();}
}
