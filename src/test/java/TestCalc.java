import FileAccess.ReadExcelFile;
import FileAccess.WriteExcelFile;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class TestCalc {

    @Test
    public void testcalculator(){
        System.setProperty("webdriver.chrome.driver","drivers/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://testsheepnz.github.io/BasicCalculator.html");
        ReadExcelFile readExcel = new ReadExcelFile();
        WriteExcelFile writeExcel = new WriteExcelFile();
        //----------------------MAPPING----------------------
        WebElement selectBuild = driver.findElement(By.name("selectBuild"));
        WebElement selectOperationDropdown = driver.findElement(By.id("selectOperationDropdown"));
        WebElement firstNum = driver.findElement(By.id("number1Field"));
        WebElement secondNum = driver.findElement(By.id("number2Field"));
        WebElement calculateButton = driver.findElement(By.id("calculateButton"));
        WebElement result = driver.findElement(By.id("numberAnswerField"));
        WebElement integerSelect = driver.findElement(By.id("integerSelect"));
        WebElement clearButton = driver.findElement(By.id("clearButton"));
        WebElement errorMsgField = driver.findElement(By.id("errorForm"));
        Select selecttypeCal = new Select(selectBuild);
        Select selectOp = new Select(selectOperationDropdown);
        //---------------------------------------------------
        //---------------------Scroll------------------------
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,1000)", "");
        //---------------------------------------------------
        WebDriverWait ewait = new WebDriverWait(driver, 3);
        String filePath = "C:\\Users\\PC\\IdeaProjects\\PruebaBabel\\file\\Data.xlsx";
        String varError="";

        try {
            String matriz[][] = readExcel.readExcelFile(filePath, "Data");

            int i=1, j;
            while (i<matriz.length) {
                j = matriz[i].length;
                selecttypeCal.selectByVisibleText(matriz[i][j - 8]);


                if (firstNum.isDisplayed()) {
                    firstNum.sendKeys(matriz[i][j - 7]);
                } else {
                    varError = varError + "400" + "-";
                }
                if (secondNum.isDisplayed()) {
                    secondNum.sendKeys(matriz[i][j - 6]);
                } else {
                    varError = varError + "401" + "-";
                }
                selectOp.selectByVisibleText(matriz[i][j - 5]);

                if (calculateButton.isDisplayed()) {
                    calculateButton.click();
                } else {
                    varError = varError + "402" + "-";
                }
                if (integerSelect.isDisplayed()) {
                    if (Integer.parseInt(matriz[i][j - 4]) == 1) {
                        integerSelect.click();
                    }
                }
                if (errorMsgField.isDisplayed()) {
                    varError = varError + errorMsgField.getText() + "-";
                }
                //System.out.println((contieneSoloLetras(matriz[i][j - 7]) && contieneSoloLetras(matriz[i][j - 6])));

                if (contieneSoloLetras(matriz[i][j - 7]) && contieneSoloLetras(matriz[i][j - 6])) {
                    if (Double.parseDouble(matriz[i][j - 6]) > 0){
                    ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("numberAnswerField")));
                    writeExcel.writeCellValue(filePath, "Data", i, 6, result.getAttribute("value"));

                    if (clearButton.isDisplayed()) {
                        clearButton.click();
                    } else {
                        varError = varError + "404" + "-";
                    }
                    if (firstNum.getText().isEmpty() && secondNum.getText().isEmpty() && result.getText().isEmpty() && integerSelect.isSelected() == false) {
                        varError = varError + "403";
                    }
                    selecttypeCal.selectByVisibleText("Prototype"); //Para limpiar correctamente todos los campos
                    selectOp.selectByVisibleText("Add"); //Para limpiar correctamente todos los campos
                    firstNum.clear();
                    secondNum.clear();
                    if (integerSelect.isSelected()) {
                        integerSelect.click();
                    }
                    writeExcel.writeCellValue(filePath, "Data", i, 8, varError);
                    varError = "";
                } else {
                    writeExcel.writeCellValue(filePath, "Data", i, 8, varError);
                    varError = "";
                    if (matriz[i][j - 5] == "Prototype") {
                        selecttypeCal.selectByVisibleText("1"); //Para limpiar correctamente todos los campos
                    } else {
                        if (matriz[i][j - 5] == "1") {
                            selecttypeCal.selectByVisibleText("2");
                        } else {
                            selecttypeCal.selectByVisibleText("Prototype");
                        }
                    }

                    selectOp.selectByVisibleText("Add");
                    calculateButton.click();
                    firstNum.clear();
                    secondNum.clear();

                    if (integerSelect.isDisplayed()) {
                        if (integerSelect.isSelected()) {
                            integerSelect.click();
                        }
                    }
                }
            }else{
                    writeExcel.writeCellValue(filePath, "Data", i, 8, varError);
                    varError = "";
                    selecttypeCal.selectByVisibleText("Prototype"); //Para limpiar correctamente todos los campos
                    selectOp.selectByVisibleText("Add"); //Para limpiar correctamente todos los campos
                    firstNum.clear();
                    secondNum.clear();
                    if (integerSelect.isSelected()) {
                        integerSelect.click();
                    }
                }
                i++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean contieneSoloLetras(String cadena) {
        for (int x = 0; x < cadena.length(); x++) {
            int c = cadena.charAt(x);
            if ((c >= 0 && c <= 44) || (c == 47) ||(c>= 58 && c <= 127)) {
                return false;
            }
        }
        return true;
    }

}
