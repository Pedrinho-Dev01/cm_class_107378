// 2. Print Messages
fun main() {
	println("Use the val keyword when the value doesn't change.")
    println("Use the var keyword when the value can change.")
    println("When you define a function, you define the parameters that can be passed to it.")
    println("When you call a function, you pass arguments for the parameters.")
}

// 3. Fix compile error
fun main() { 
    println("New chat message from a friend")
}


// 4. String templates
fun main() {
    var discountPercentage: Int = 0
    var offer: String = ""
    val item = "Google Chromecast"
    discountPercentage = 20
    offer = "Sale - Up to $discountPercentage% discount on $item! Hurry up!"
    
    println(offer)
}


// 5. String concatenation
fun main() {
    val numberOfAdults = 20
    val numberOfKids = 30
    val total = numberOfAdults + numberOfKids
    println("The total party size is: $total")
}


// 6. Message formatting
fun main() {
    val baseSalary = 5000
    val bonusAmount = 1000
    val totalSalary = baseSalary + bonusAmount
    println("Congratulations for your bonus! You will receive a total of $totalSalary (additional bonus).")
}


// 7. Implement basic math operations
// Step 1
fun main() {
    val firstNumber = 10
    val secondNumber = 5
    val result = firstNumber + secondNumber
    
    println("$firstNumber + $secondNumber = $result")
}

// Step 2
fun main() {
    val firstNumber = 10
    val secondNumber = 5
    val thirdNumber = 8
    
    val result = add(firstNumber, secondNumber)
    val anotherResult = add(firstNumber, thirdNumber)

    println("$firstNumber + $secondNumber = $result")
    println("$firstNumber + $thirdNumber = $anotherResult")
}

// Define add() function below this line
fun add(n1: Int, n2: Int): Int{
    return n1 + n2
}

// Step 3
fun main() {
    val firstNumber = 10
    val secondNumber = 5
    val thirdNumber = 8
    
    val result = add(firstNumber, secondNumber)
    val anotherResult = add(firstNumber, thirdNumber)
    val yetAnotherResult = sub(secondNumber, thirdNumber)

    println("$firstNumber + $secondNumber = $result")
    println("$firstNumber + $thirdNumber = $anotherResult")
}

fun add(n1: Int, n2: Int): Int{
    return n1 + n2
}

fun subtract(n1: Int, n2: Int): Int{
    return n1 - n2
}


// 8. Default parameters
// Step 1
fun main() {
    val operatingSystem = "Chrome OS"
    val emailId = "sample@gmail.com"

    println(displayAlertMessage(operatingSystem, emailId))
}

// Define your displayAlertMessage() below this line.
fun displayAlertMessage(os: String, email: String): String {
    return "There's a new sign-in request on $os for your Google Account $email."
}

// Step 2
fun main() {
    val firstUserEmailId = "user_one@gmail.com"

    // The following line of code assumes that you named your parameter as emailId. 
    // If you named it differently, feel free to update the name.
    println(displayAlertMessage(emailId = firstUserEmailId))
    println()

    val secondUserOperatingSystem = "Windows"
    val secondUserEmailId = "user_two@gmail.com"

    println(displayAlertMessage(secondUserOperatingSystem, secondUserEmailId))
    println()

    val thirdUserOperatingSystem = "Mac OS"
    val thirdUserEmailId = "user_three@gmail.com"

    println(displayAlertMessage(thirdUserOperatingSystem, thirdUserEmailId))
    println()
}

// Define your displayAlertMessage() below this line.
fun displayAlertMessage(os: String = "Unknown OS", emailId: String): String {
    return "There's a new sign-in request on $os for your Google Account $emailId."
}


// 9. Pedometer
fun main() {
    val steps = 4000
    val caloriesBurned = steps_to_cal(steps);
    println("Walking $steps steps burns $caloriesBurned calories") 
}

fun steps_to_cal(numberSteps: Int): Double {
    val steps_to_cal_ratio = 0.04
    val totalCalories = numberSteps * caloriesBurnedforEachStep
    return totalCalories
}


// 10. Compare two numbers
fun main() {
    val timeSpentToday = 300 // 300, 200
    val timeSpentYesterday = 250 // 300, 220
    
    val result = compare(timeSpentToday, timeSpentYesterday)
    println(result)
}

fun compare(n1: Int, n2: Int): Boolean {
    return n1 > n2
}


// 11. Move duplicate code into a function
fun main() {
    displayWeather("Ankara", 27, 31, 82)
    displayWeather("Tokyo", 32, 36, 10)
    displayWeather("Cape Town", 59, 64, 2)
    displayWeather("Guatemala City", 50, 55, 7)
}

fun displayWeather(city: String, lowTemp: Int, highTemp: Int, chanceOfRain: Int) {
    println("City: $city")
    println("Low temperature: $lowTemp, High temperature: $highTemp")
    println("Chance of rain: $chanceOfRain%")
    println()
}

