package io.github.gabrielpadilh4.quarkus;

import io.github.gabrielpadilh4.util.FizzBuzzChallenge;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;

@QuarkusMain
public class QuarkusApp implements QuarkusApplication {

    FizzBuzzChallenge fizzBuzzChallenge;

    public QuarkusApp(FizzBuzzChallenge fizzBuzzChallenge) {
        super();
        this.fizzBuzzChallenge = fizzBuzzChallenge;
    }

    @Override
    public int run(String... args) throws Exception {
        fizzBuzzChallenge.resolveFizzBuzz();
        return 0;
    }
}
