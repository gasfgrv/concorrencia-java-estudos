package completableFuture.model;

public class Discount {
    public enum Code {
        NONE(0), LOW(5), MEDIUM(10), HIGH(15);

        private final int percentage;

        Code(int percentage) {
            this.percentage = percentage;
        }

        public int getPercentage() {
            return percentage;
        }
    }
}
