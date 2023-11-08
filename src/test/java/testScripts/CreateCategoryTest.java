package testScripts;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import genericLibraries.BaseClass;
import genericLibraries.IConstantPath;

public class CreateCategoryTest extends BaseClass {

	@Test
	public void CreateCategoryTest() throws InterruptedException {
		SoftAssert soft = new SoftAssert();
		home.clickCoursesTab();
		home.clickCategoryLink();
		soft.assertTrue(category.getPageHeader().contains("Category"));
		
		category.clickNewButton();
		Thread.sleep(3000);
		soft.assertEquals(addCategory.getPageHeader(),"Add New Category");
		Map<String,String> map = excel.readFromExcel("Sheet1","Add Category");
		String categoryName = map.get("Name")+jutil.generateRandomNum(100);
		addCategory.setName(map.get("name"));
		addCategory.clickSave();
		
		soft.assertTrue(category.getSuccessMessage().contains("Success"));
		boolean isPresent = false;
		List<WebElement> categoryList = category.getCategoryList();
		for (WebElement e : categoryList) {
			if(e.getText().equals(categoryName)) {
				isPresent = true;
				break;
			}
		}
		soft.assertTrue(isPresent);
		category.clickDeleteButton(categoryName,driver);
		category.clickDelete();
		soft.assertTrue(category.getSuccessMessage().contains("Success"));
		
		 if(course.getSuccessMessage().contains("Success"));
                 excel.writeToExcel("Sheet1","Add Course","Pass",IConstantPath.EXCEL_PATH);
            
            	 excel.writeToExcel("Sheet1","Add Course","Fail",IConstantPath.EXCEL_PATH);
         
               
		soft.assertAll();
	}

}
