package Reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ExtentTestListener implements ITestListener {
        private ExtentReports extent = ExtentReportManager.getInstance();
        private static ExtentTest test;


        @Override
        public void onTestStart(ITestResult result) {
            String className = result.getMethod().getRealClass().getSimpleName();
            String methodName = result.getMethod().getMethodName();
            String description = result.getMethod().getDescription();
            test = extent.createTest(className + " - " + methodName, description);
        }

        @Override
        public void onTestSuccess(ITestResult result) {
            test.log(Status.PASS, "Test passed");
        }

        @Override
        public void onTestFailure(ITestResult result) {
            test.log(Status.FAIL, "Test failed: " + result.getThrowable());
        }

        @Override
        public void onFinish(ITestContext context) {
            extent.flush();
        }


    }

