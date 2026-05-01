# TODO List for Adding Betting Feature to 21 Game



### 1. Fix reward variable calculation in twentyone.java ✅
- Changed `public int reward=bet*2;` to a method `getReward()` that calculates reward dynamically when needed

### 2. Add placeBet() method in twentyone.java ✅
- Created a method to allow player to place their bet
- Validates that bet is within available balance
- Prompts player for bet amount

### 3. Update balance handling in hit() method ✅
- When player busts (goes over 21):
  - Deducts bet from balance
  - Prints "You lost [bet]! Your new balance is [balance]"

### 4. Update balance handling in stand() method ✅
- When dealer busts (player wins):
  - Adds bet * 2 to balance
  - Prints "You won [bet]! Your new balance is [balance]"
- When player wins (higher hand than dealer):
  - Adds bet * 2 to balance
  - Prints "You won [bet]! Your new balance is [balance]"
- When player loses (dealer higher):
  - Deducts bet from balance
  - Prints "You lost [bet]! Your new balance is [balance]"
- When push (tie):
  - Returns the bet (no change to balance)
  - Prints "It's a tie! Your bet of [bet] is returned."

### 5. Update mainhub.java to call placeBet() ✅
- Calls game.placeBet() before the game loop starts
