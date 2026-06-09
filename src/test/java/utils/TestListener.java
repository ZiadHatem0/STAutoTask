package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.awt.Desktop;
import java.io.File;

public class TestListener implements ITestListener {

    private final ExtentReports extent = ExtentReportManager.getInstance();

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = extent.createTest(result.getMethod().getMethodName());
        ExtentReportManager.setCurrentTest(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentReportManager.log(Status.PASS, "Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentReportManager.log(Status.FAIL, result.getThrowable().getMessage());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentReportManager.log(Status.SKIP, "Test skipped — upstream dependency failed");
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
        if (ConfigReader.getBoolean("auto.open.report", false)) {
            openReport();
        }
    }

    private void openReport() {
        try {
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().browse(new File(ExtentReportManager.REPORT_PATH).toURI());
            }
        } catch (Exception e) {
            System.out.println("[Report] Could not auto-open report: " + e.getMessage());
        }
    }
}