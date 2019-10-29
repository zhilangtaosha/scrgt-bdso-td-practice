package starter.navigation;

import net.thucydides.core.annotations.Step;

public class NavigateTo {

    BDSOLoginPage bdsoLoginPage;
    BDSODataPage bdsoDataPage;
    BDSOCelebritiesPage bdsoCelebritiesPage;

    @Step("Open the BDSO Login Page")
    public void theBdsoLoginPage() {
        bdsoLoginPage.open();
    }

    @Step("Open the BDSO Data Page")
    public void theBdsoDataPage() {
        bdsoDataPage.open();
    }

    @Step("Open the BDSO Celebrities Page")
    public void theBdsoCelebritiesPage() {
        bdsoCelebritiesPage.open();
    }
}
