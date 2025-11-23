# 🍽️ Playwright Java Automation – Food Ordering Flow (Staging Environment)

This project automates a complete end-to-end food ordering flow in the ** Food (staging)** environment using **Playwright Java**.  
The flow includes:

- User login with OTP  
- Selecting address  
- Searching Restaurants  
- Adding food items  
- Checking out  
- Placing an order  
- Viewing order details  

This project is built using **Java**, **Playwright**, and **Maven**.

---

## 📁 Project Structure
```
project-root
│
├── src
│   ├── main
│      └── java
│          ├── org.example
│               └── allpagetest
│
├── pom.xml
└── README.md
```
### Run via IntelliJ

1. Open `allpagetest.java` in IntelliJ.
2. Click the **Run** button for `allpagetest.main()`.
3. The browser will launch and execute the steps automatically.

⚠️ **Note:** This script **cannot be run directly using `mvn test`** because it is a standalone `main()` method, not a TestNG test.
⚠️ **Note:** The generated code uses `Thread.sleep()` to slow down steps so they are visible during execution.  




