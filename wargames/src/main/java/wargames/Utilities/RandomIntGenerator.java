package wargames.Utilities;

import java.util.Random;

public class RandomIntGenerator {
    public String generateRandomInt() {
        Random random = new Random();
        int randomInt = 1000 + random.nextInt(9000);

        return String.valueOf(randomInt);
    }
}
