/**
 * Shipping logic for an online retailer's checkout service.
 *
 * <p>See README.md for the full pricing and delivery rules. Run the JUnit
 * tests in {@code src/test/java/OrderProcessorTest.java} to check your work.
 */
public class OrderProcessor {

    /** Packages heavier than this (in pounds) are considered heavy. */
    public static final double HEAVY_THRESHOLD_LBS = 20.0;

    /** Extra charge added to heavy STANDARD, SAVER, and EXPRESS packages. */
    public static final double HEAVY_SURCHARGE = 8.00;

    /** Value returned for any tier that is not recognized. */
    public static final int INVALID = -1;

    /**
     * Optional: try some values here while you work. The real checks are the
     * JUnit tests.
     */
    public static void main(String[] args) {
        System.out.println("STANDARD, 4 lb:  $" + calculateShippingFee("STANDARD", 4));
        System.out.println("EXPRESS, 25 lb:  $" + calculateShippingFee("EXPRESS", 25));
        System.out.println("SAME_DAY, 2 lb:  $" + calculateShippingFee("SAME_DAY", 2));
    }

    /**
     * TASK 1: Customers have reported incorrect charges from this method.
     * Find and fix the problem(s) so it matches the shipping fee rules in the
     * README. Keep it a traditional (colon-style) switch statement.
     *
     * @param tier      the shipping tier name, case-insensitive (may be null)
     * @param weightLbs the package weight in pounds
     * @return the shipping fee in dollars, or -1.0 for an unrecognized tier
     */
    public static double calculateShippingFee(String tier, double weightLbs) {
        double fee = 0.0;

        if (tier == null) {
            return -1.0;
        }
        switch (tier.toUpperCase()) {
            case "SAVER":
                fee = 5.00;
                if (weightLbs > HEAVY_THRESHOLD_LBS) {
                    fee += HEAVY_SURCHARGE;
                }
                break;
            case "STANDARD":
                fee = 5.00;
                if (weightLbs > HEAVY_THRESHOLD_LBS) {
                    fee += HEAVY_SURCHARGE;
                }
                break;
            case "EXPRESS":
                fee = 15.00;
                if (weightLbs > HEAVY_THRESHOLD_LBS) {
                    fee += HEAVY_SURCHARGE;
                }
                break;

            default:
                fee = -1.0;
                break;

            case "SAME_DAY":
                fee += 10.00;
                

            case "OVERNIGHT":
                fee += 25.00;
                break;

        }

        return fee;
    }

    /**
     * TASK 2: Implement this method with a modern switch expression
     * ({@code ->} arrows, and {@code yield} where a case needs a block).
     * Follow the delivery-time rules in the README.
     *
     * @param tier          the shipping tier name, case-insensitive (may be null)
     * @param remoteAddress true if the order ships to a remote address
     * @return estimated delivery time in days, or -1 if unavailable or invalid
     */
    public static int calculateDeliveryDays(String tier, boolean remoteAddress) {
        // TODO: replace this placeholder
        if (tier == null) {
            return -1;
        }
        int days = switch (tier.toUpperCase()) {
            case "SAME_DAY" -> { 
                if (remoteAddress) { 
                    yield -1; 
                }
                else {
                    yield 0;
                }
            }
            case "OVERNIGHT" -> 1; 
            case "EXPRESS" -> 2;
            case "STANDARD", "SAVER" -> 5;
            case "CUSTOM" -> {
                System.out.println("Calculating custom courier rates...");
                yield 10;
            }
            
            default -> INVALID;
        };

        if (remoteAddress && days != INVALID) {
            days += 3;
        }
        return days;
    }

    /**
     * TASK 3: Re-implement the (fixed) Task 1 fee rules as a modern switch
     * expression. Your version must not use {@code break} or fall-through.
     *
     * @param tier      the shipping tier name, case-insensitive (may be null)
     * @param weightLbs the package weight in pounds
     * @return the shipping fee in dollars, or -1.0 for an unrecognized tier
     */
    public static double calculateShippingFeeModern(String tier, double weightLbs) {
        // TODO: replace this placeholder
        if (tier == null || weightLbs < 0) {
            return -1;
        }
        double fee = switch (tier.toUpperCase()) {
            case "STANDARD", "SAVER" -> 5.00;
            case "EXPRESS" -> 15.00;
            case "OVERNIGHT" -> 25.00;
            case "SAME_DAY" -> 35.00;

            default -> -1.0;
        };
        if (fee == -1) {
            return fee;
        }
        if (weightLbs > 20 && !tier.toUpperCase().equals("SAME_DAY") &&
            !tier.toUpperCase().equals("OVERNIGHT")) {
            fee += 8;
        }
        return fee;
    }
}
