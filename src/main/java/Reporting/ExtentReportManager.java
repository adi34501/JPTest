package Reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtentReportManager {

        private static ExtentReports extent;
        private static ExtentHtmlReporter htmlReporter;

        public static ExtentReports getInstance() {
            if (extent == null) {
                extent = new ExtentReports();
                String reportPath = getReportPath();
                htmlReporter = new ExtentHtmlReporter(reportPath);
                extent.attachReporter(htmlReporter);
            }
            return extent;
        }

        private static String getReportPath() {
            String reportDir = System.getProperty("user.dir") + "/test-output/";
            String reportName = "ExtentReport.html";
            return reportDir + reportName;
        }
    }

