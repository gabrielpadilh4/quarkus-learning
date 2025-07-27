package io.github.gabrielpadilh4.util;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FizzBuzzChallenge {

    @ConfigProperty(name = "fizz.text", defaultValue = "Fizz")
    String fizzText;

    @ConfigProperty(name = "buzz.text", defaultValue = "Buzz")
    String buzzText;

    @ConfigProperty(name = "fizzbuzz.text", defaultValue = "FizzBuzz")
    String fizzBuzzText;

    @ConfigProperty(name = "fizzbuzz.max.number", defaultValue = "150")
    int fizzBuzzMaxNumber;

    public void resolveFizzBuzz() {

        for (int i = 0; i < fizzBuzzMaxNumber; i++) {

            String result = String.valueOf(i);

            if (i % 3 == 0 && i % 5 == 0) {
                result = fizzBuzzText;
            } else if (i % 3 == 0) {   
                result= fizzText;
            } else if (i % 5 == 0) {
               result = buzzText;
            }

            System.out.println(result);
        }
    }

}
