package com.qa.opencart.Listners;
import com.aventstack.extentreports.*;
import org.testng.*;


public class ExtentTestListner implements ITestListener{

    private static ExtentReport ExtentReportManager;
    private static ExtentReports extent = ExtentReportManager.getExtentReport();
        private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    public static void setExtentReportManager(ExtentReport extentReportManager) {
        ExtentReportManager = extentReportManager;
    }

    @Override
        public void onTestStart(ITestResult result) {
            ExtentTest extentTest =
                    extent.createTest(result.getMethod().getMethodName());
            test.set(extentTest);
        }

        @Override
        public void onTestSuccess(ITestResult result) {
            test.get().log(Status.PASS, "Test Passed");
        }

        @Override
        public void onTestFailure(ITestResult result) {
            test.get().log(Status.FAIL, result.getThrowable());
        }

        @Override
        public void onTestSkipped(ITestResult result) {
            test.get().log(Status.SKIP, "Test Skipped");
        }

        @Override
        public void onFinish(ITestContext context) {
            extent.flush();
        }
    }


