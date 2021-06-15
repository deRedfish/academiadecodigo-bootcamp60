public class RandGen {

    public static int randInt(int maxValue) {
        int randInt = (int) (Math.floor(Math.random() * maxValue));
        return randInt;
    }

}
