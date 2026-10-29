# Lab Activity: Refactoring E-Commerce Order Processing
**Topics:** Traditional `switch` statements and fall-through vs. modern Java `switch` expressions (`->`, `yield`)

---

## Background
You maintain the checkout service for an online retailer. Support tickets are piling up:

- Some customers say they were charged the wrong amount for shipping.
- Orders with a mistyped shipping tier are showing up with real charges instead of being rejected.
- The service crashed once when an order came in with no shipping tier.

Your job is to fix the existing shipping fee code, then add new features using modern `switch` expressions.

All work happens in `src/main/java/OrderProcessor.java`. **Do not edit the test file.**

---

## Running the Tests
- **VS Code:** open the Testing panel (beaker icon) and click **Run Tests**, or
- **Terminal → Run Task → Run JUnit Tests**

At the start, most tests fail. When you're done, all of them should pass.

---

## Business Rules

### Shipping fees
| Tier | Fee | Heavy package (over 20 lb) |
|---|---|---|
| `STANDARD` | $5.00 | add $8.00 |
| `SAVER` | same as `STANDARD` | add $8.00 |
| `EXPRESS` | $15.00 | add $8.00 |
| `OVERNIGHT` | $25.00 | no surcharge (flat rate) |
| `SAME_DAY` | the `OVERNIGHT` rate + a $10.00 rush fee | no surcharge (flat rate) |
| anything else, including `null` | `-1.0` | n/a |

A package that weighs exactly 20 lb is **not** heavy.

### Delivery times
| Tier | Days | Remote address |
|---|---|---|
| `SAME_DAY` | 0 | not available: return `-1` |
| `OVERNIGHT` | 1 | add 3 days |
| `EXPRESS` | 2 | add 3 days |
| `STANDARD`, `SAVER` | 5 | add 3 days |
| `CUSTOM` | 10, and print `Calculating custom courier rates...` | add 3 days |
| anything else, including `null` | `-1` | `-1` |

Tier names are **case-insensitive** (`"express"`, `"Express"`, and `"EXPRESS"` are all the same tier).

---

## Your Tasks

### Task 1: Fix `calculateShippingFee()` (traditional `switch`)
The method doesn't follow the shipping fee rules. Use the failing tests to figure out what's wrong, then fix it.

- Keep it a traditional colon-style `switch` statement (`case "X":`).
- Not every fall-through in this method is a mistake. Before you change a case, make sure you understand what it is supposed to do.

### Task 2: Implement `calculateDeliveryDays()` (`switch` expression)
Implement the delivery time rules with a **`switch` expression**:
- Use arrow cases (`case ... ->`).
- Tiers that share a result should share **one** case label.
- `CUSTOM` needs a block (`{ ... }`) that prints the message and returns the value with **`yield`**.
- No `break` statements.

### Task 3: Implement `calculateShippingFeeModern()` (`switch` expression)
Rewrite the shipping fee rules from Task 1 as a `switch` expression, with no `break` and no colon-style `case` labels.

How do you handle a rule like `SAME_DAY` (the `OVERNIGHT` rate plus a fee) without fall-through? Once you have both versions, compare them: which one is easier to read, and which one is easier to get wrong?

---

## Deliverables
- All JUnit tests pass.
- Submit your completed `OrderProcessor.java` to classroom50 with the correct submit tag.
