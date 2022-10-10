package Amazon_AutomationTest;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AutomationTestScripts {

	public static WebDriver driver = null;
	   @BeforeTest
	   public void setUp() throws Exception {
		   System.setProperty("webdriver.chrome.driver", "chromedriver");
	       driver=new ChromeDriver();
	   }
	   
	   @Test
	   public void TestCase01() {
	       try {
	           System.out.println("Step 01: Open Website Amazon");
	           driver.get("https://www.amazon.com");
	           driver.manage().window().fullscreen();
	           
	           System.out.println("Step 02_1: Click Catagory menu");
	           WebElement listCatagory = driver.findElement(By.xpath("//*[@id='searchDropdownBox']"));
	           listCatagory.click();
	           Thread.sleep(3000);
	           
	           System.out.println("Step 02_2: If Electronics is Displayed then click Electronics, else click the first item");
	           Select catagoryList = new Select(driver.findElement(By.xpath("//*[@id='searchDropdownBox']")));
	           try {
	        	   catagoryList.selectByVisibleText("Electronics");
	           } catch(Exception e) {
	        	   catagoryList.getFirstSelectedOption();
	           }
	           
	           System.out.println("Step 03: Search text 'iPhone 14' on search field");
	           WebElement searchField = driver.findElement(By.xpath("//*[@id='twotabsearchtextbox']"));
	           searchField.click();
	           searchField.sendKeys("iPhone 14");
	           searchField.submit();
	           Thread.sleep(3000);
	           
	           System.out.println("Step 04: Select Featured Brands is Belkin");
	           WebElement featuredBelkin = driver.findElement(By.xpath("//*[@id='p_89/Belkin']/span/a/span"));
	           featuredBelkin.click();
	           Thread.sleep(3000);
	           
	           System.out.println("Step 05_1: Input min price");
	           WebElement minPrice = driver.findElement(By.xpath("//*[@id='low-price']"));
	           minPrice.sendKeys("100");
	           Thread.sleep(3000);
	           
	           System.out.println("Step 05_2: Input max price");
	           WebElement maxPrice = driver.findElement(By.xpath("//*[@id='high-price']"));
	           maxPrice.sendKeys("2000");
	           Thread.sleep(3000);
	           
	           System.out.println("Step 05_3: Click button [Go] to search by price");
	           WebElement buttonSubmitSearchByPrice = driver.findElement(By.xpath("//*[@id='p_36/price-range']//input[@type='submit']"));
	           buttonSubmitSearchByPrice.click();
	           Thread.sleep(3000);
	           
	           System.out.println("Step 06: Validate all items have 100$ <= price <= 2000$");
	           List<WebElement> listPrice = driver.findElements(By.xpath("//*[@class='a-price-whole']"));
	           for(WebElement e: listPrice)
	        	   if (Integer.parseInt(e.getText()) < 100 || Integer.parseInt(e.getText()) > 2000) {
	        		   org.testng.Assert.fail("Have items not in range 100$-2000$");
	               }
	       } catch (Exception e) {
	    	   System.out.println(e);
	    	   org.testng.Assert.fail("System error !!!");
	       }
	   }
	   
	   @Test
	   public void TestCase02() {
	       try {
	           System.out.println("Step 01: Open Website Amazon");
	           driver.get("https://www.amazon.com");
	           
	           System.out.println("Step 02_1: Click Catagory menu");
	           WebElement listCatagory = driver.findElement(By.xpath("//*[@id='searchDropdownBox']"));
	           listCatagory.click();
	           Thread.sleep(3000);
	           
	           System.out.println("Step 02_2: If Electronics is Displayed then click Electronics, else click the first item");
	           Select catagoryList = new Select(driver.findElement(By.xpath("//*[@id='searchDropdownBox']")));
	           try {
	        	   catagoryList.selectByVisibleText("Electronics");
	           } catch(Exception e) {
	        	   catagoryList.getFirstSelectedOption();
	           }
	           Thread.sleep(3000);
	           
	           System.out.println("Step 03: Search text 'iPhone 14' on search field");
	           WebElement searchField = driver.findElement(By.xpath("//*[@id='twotabsearchtextbox']"));
	           searchField.click();
	           searchField.sendKeys("iPhone 14");
	           searchField.submit();
	           Thread.sleep(3000);
	           
	           System.out.println("Step 04: Select price $100 to $200");
	           WebElement rangePrice = driver.findElement(By.xpath("//*[@id='p_36/1253506011']/span/a/span"));
	           rangePrice.click();
	           Thread.sleep(3000);
	           
	           System.out.println("Step 05_1: Click to sort items");
	           WebElement Sort = driver.findElement(By.xpath("//*[@id='a-autoid-0-announce']"));
	           Sort.click();
	           Thread.sleep(3000);
	           
	           System.out.println("Step 05_2: Select Price: High to Low");
	           WebElement highToLow = driver.findElement(By.xpath("//*[@id='s-result-sort-select_2']"));
	           highToLow.click();
	           Thread.sleep(3000);
	           
	           System.out.println("Step 06: Validate all items sort Price: High to Low");
	           List<WebElement> priceList = driver.findElements(By.xpath("//*[@class='a-price-whole']"));
	           int size = priceList.size();
	           Integer[] priceListActual = new Integer[size]; 
	           Integer[] priceListSorted = new Integer[size+1];
	           int i = 0;
	           int j = 1;
	           for(WebElement e: priceList) {
	        	   priceListActual[i]=priceListSorted[j] = Integer.parseInt(e.getText());
	        	   i++;
	        	   j++;
	           }
	           
	           //Validating the existing with sorted array
	           for(i = 1;i<size;i++)
	           {
	               if((priceListActual[i] > priceListSorted[i]))
	               {
	                   System.out.println("The items are sort Price: High to Low not correct");
	                   org.testng.Assert.fail("expected "+priceListActual[i]+" lower than "+priceListSorted[i]+" at row "+i);
	               }
	           }
	       } catch (Exception e) {
	    	   System.out.println(e);
	    	   org.testng.Assert.fail("System error !!!");
	       }
	   }
	   
	   @Test
	   public void TestCase03() {
	       try {
	           System.out.println("Step 01: Open Website Amazon");
	           driver.get("https://www.amazon.com");
	           
	           System.out.println("Step 02_1: Click Catagory menu");
	           WebElement listCatagory = driver.findElement(By.xpath("//*[@id='searchDropdownBox']"));
	           listCatagory.click();
	           Thread.sleep(3000);
	           
	           System.out.println("Step 02_2: If Electronics is Displayed then click Electronics, else click the first item");
	           Select catagoryList = new Select(driver.findElement(By.xpath("//*[@id='searchDropdownBox']")));
	           try {
	        	   catagoryList.selectByVisibleText("Electronics");
	           } catch(Exception e) {
	        	   catagoryList.getFirstSelectedOption();
	           }
	           Thread.sleep(3000);
	           
	           System.out.println("Step 03: Search text 'iPhone 14' on search field");
	           WebElement searchField = driver.findElement(By.xpath("//*[@id='twotabsearchtextbox']"));
	           searchField.click();
	           searchField.sendKeys("iPhone 14");
	           searchField.submit();
	           Thread.sleep(3000);
	           
	           System.out.println("Step 04: Select price $100 to $200");
	           WebElement rangePrice = driver.findElement(By.xpath("//*[@id='p_36/1253506011']/span/a/span"));
	           rangePrice.click();
	           Thread.sleep(3000);
	           
	           System.out.println("Step 05: Click random items on the screen");
	           List<WebElement> priceList = driver.findElements(By.xpath("//*[@class='a-price-whole']"));
	           int size = priceList.size();
	           int randomNumber = (int) ((Math.random() * (size - 1)) + 1);
	           priceList.get(randomNumber).click();
	           Thread.sleep(3000);
	           
	           System.out.println("Step 06: Click add to cart");
	           WebElement addToCart = driver.findElement(By.xpath("//*[@id='add-to-cart-button']"));
	           addToCart.click();
	           Thread.sleep(3000);
	           
	           System.out.println("Step 07: Click go to cart");
	           WebElement goToCart = driver.findElement(By.xpath("//*[@id='sw-gtc']/span/a"));
	           goToCart.click();
	           Thread.sleep(3000);
	           
	           System.out.println("Step 08: Click process to checkout");
	           WebElement processToCheckout = driver.findElement(By.xpath("//*[@id='sc-buy-box-ptc-button']/span/input"));
	           processToCheckout.click();
	           Thread.sleep(3000);
	           
	           System.out.println("Step 09: Validate sign in screen is displayed");
	           WebElement signIn = driver.findElement(By.xpath("//*[contains(text(),'Sign in')]"));
	           if(!signIn.isDisplayed()) {
	        	   org.testng.Assert.fail("Not display the Sign in screen");
	           }
	       } catch (Exception e) {
	    	   System.out.println(e);
	    	   org.testng.Assert.fail("System error !!!");
	       }
	   }
	   
	   @AfterTest
	   public void closeBrowser() {
	       driver.close();
	       System.out.println("The driver has been closed.");
	   }
}
