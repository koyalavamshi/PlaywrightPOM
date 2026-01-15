package com.qa.opencart.Listners;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;


public class ExtentReport {

    private static ExtentReports extent;

        public static ExtentReports getExtentReport() {
            if (extent == null) {
                ExtentSparkReporter spark =
                        new ExtentSparkReporter("test-output/ExtentReport.html");

                spark.config().setReportName("OpenCart Automation Report");
                spark.config().setDocumentTitle("Playwright Test Results");

                extent = new ExtentReports();
                extent.attachReporter(spark);

                extent.setSystemInfo("Tester", "Vamshi");
                extent.setSystemInfo("Framework", "Playwright + TestNG");
                extent.setSystemInfo("Browser", "Chrome");
            }
            return extent;
        }
    }


