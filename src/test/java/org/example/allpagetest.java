package org.example;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class allpagetest {

    public static void main(String[] args) {

        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(
                    new BrowserType.LaunchOptions().setHeadless(false)
            );

            BrowserContext context = browser.newContext(
                    new Browser.NewContextOptions().setIgnoreHTTPSErrors(true)
            );
            Page page = context.newPage();


            page.navigate("https://food-web.p-stageenv.xyz/");

            //User Login

            page.getByTestId("login-register-button").click();
            page.getByTestId("mobile-number-input").getByRole(AriaRole.TEXTBOX).click();
            page.getByText("▼").click();
            page.getByText("Bangladesh (বাংলাদেশ) +").click();

            page.getByTestId("mobile-number-input").getByRole(AriaRole.TEXTBOX)
                    .fill("1521432574");
            page.getByTestId("send-otp").click();

            Thread.sleep(3000);

            //otp

            page.getByRole(AriaRole.TEXTBOX).first().fill("2");
            page.getByRole(AriaRole.TEXTBOX).nth(1).fill("2");
            page.getByRole(AriaRole.TEXTBOX).nth(2).fill("2");
            page.getByRole(AriaRole.TEXTBOX).nth(3).fill("2");
            page.getByRole(AriaRole.TEXTBOX).nth(4).fill("2");
            page.getByRole(AriaRole.TEXTBOX).nth(5).fill("2");

            page.getByTestId("submit-otp").click();
            Thread.sleep(5000);

            //select Restaurent

            page.getByRole(AriaRole.TEXTBOX,
                            new Page.GetByRoleOptions().setName("Enter your address and see"))
                    .fill("gabtoli");
            page.getByText("Gabtoli Bus Terminal").click();
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions()
                    .setName("See Restaurants ")).click();

            Thread.sleep(5000);
            page.locator(".icon.icon-ic_close").click();
            page.getByText("Sakib Tehari Ghar - GabtoliBiryani5 (58) Delivery ৳").click();

            Thread.sleep(5000);
            assertThat(page.getByText("Your Cart",
                    new Page.GetByTextOptions().setExact(true))).isVisible();

            //Food item selection

            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Add"))
                    .first().click();
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Add"))
                    .first().click();

            Thread.sleep(5000);
            assertThat(page.getByText("Item(s) Price৳270.00 The")).isVisible();

            assertThat(page.locator("[id=\"__nuxt\"]"))
                    .containsText("Your Cart Sakib Tehari Ghar - Gabtoli");

            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions()
                    .setName("Checkout")).click();

            assertThat(page.locator("[id=\"__nuxt\"]")).containsText("Summary");

            assertThat(page.getByText("Special Gorur Tehari Each ৳180 ৳")).isVisible();
            assertThat(page.getByText("Special Gorur Tehari Each ৳90 ৳")).isVisible();
            assertThat(page.getByText("Total Bill")).isVisible();
            assertThat(page.getByText("Payment Method",
                    new Page.GetByTextOptions().setExact(true))).isVisible();

            Thread.sleep(5000);

            //Place Order

            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions()
                    .setName("Place Order")).click();

            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions()
                    .setName("Confirm")).click();

            assertThat(page.getByRole(AriaRole.HEADING,
                    new Page.GetByRoleOptions().setName("Order Placed"))).isVisible();

            Thread.sleep(5000);

            page.getByTestId("see-order-details").click();

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
