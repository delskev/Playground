package be.perzival.dev.cookbook.immutability;


import org.immutables.value.Value;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


//This annotation si made to configure Immutables framework so we can have the same behaviour everywhere on every beans
//More information on https://immutables.github.io/immutable.html
@Target({ElementType.PACKAGE, ElementType.TYPE})
@Retention(RetentionPolicy.CLASS) // Make it class retention for incremental compilation
@Value.Style(
        get = {"is*", "get*", "has*"}, // Detect 'get' and 'is' prefixes in accessor methods
        //init = "with*", // Builder initialization methods will have 'set' prefix
        //        with = "with*", // Default
        //        typeAbstract = {"Abstract*"}, // 'Abstract' prefix will be detected and trimmed
        //        typeImmutable = "Immutable*", // Prefix or suffix for generated immutable type
        //        builder = "builder", // construct builder using 'new' instead of factory method
        //        build = "build", // rename 'build' method on builder to 'create'
        visibility = Value.Style.ImplementationVisibility.PUBLIC, // Generated class will be always public
        //        defaults = @Value.Immutable(copy = false),
        jdkOnly = true //enforce usage of standard Library
//    allParameters = true
) // Disable copy methods by default
public @interface ImmutableWithStaticConstructor {
}
