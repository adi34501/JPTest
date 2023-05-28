package Reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ExtentTestListener implements ITestListener {
        private ExtentReports extent = ExtentReportManager.getInstance();
        private ExtentTest test;

        @Override
        public void onTestStart(ITestResult result) {
            test = extent.createTest(result.getMethod().getMethodName());
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

