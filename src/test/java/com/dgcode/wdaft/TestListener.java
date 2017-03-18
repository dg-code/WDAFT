package com.dgcode.wdaft;

import static com.dgcode.wdaft.Tester.getVerificationFailures;

import java.util.List;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.internal.Utils;

/**
 * A custom test listener that gets invoked before and after a method is invoked by TestNG.
 * This listener will only be invoked for configuration and test methods.
 * <br>
 * <br>
 * Created on August 29, 2016
 * @author dgcode
 */
public class TestListener implements IInvokedMethodListener {

	@Override
	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
		// Implementation not needed with the current version
	}
	
    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult result) {

        Reporter.setCurrentTestResult(result);

        if (method.isTestMethod()) {

            List<Throwable> verificationFailures = getVerificationFailures();

            //if there are verification failures...
            if (verificationFailures.size() > 0) {

                //set the tests to failed
                result.setStatus(ITestResult.FAILURE);

                //if there is an assertion failure add it to verificationFailures
                if (result.getThrowable() != null) {
                    verificationFailures.add(result.getThrowable());
                }

                int size = verificationFailures.size();
                //if there's only one failure just set that
                if (size == 1) {
                    result.setThrowable(verificationFailures.get(0));
                } else {
                    //create a failure message with all failures and stack traces (except last failure)
                    StringBuffer failureMessage = new StringBuffer("Multiple failures (").append(size).append("):\n\n");
                    for (int i = 0; i < size-1; i++) {
                        failureMessage.append("Failure ").append(i+1).append(" of ").append(size).append(":\n");
                        Throwable t = verificationFailures.get(i);
                        String fullStackTrace = Utils.longStackTrace(t, false);
                        failureMessage.append(fullStackTrace).append("\n\n");
                    }

                    //final failure
                    Throwable last = verificationFailures.get(size-1);
                    failureMessage.append("Failure ").append(size).append(" of ").append(size).append(":\n");
                    failureMessage.append(last.toString());

                    //set merged throwable
                    Throwable merged = new Throwable(failureMessage.toString());
                    merged.setStackTrace(last.getStackTrace());

                    result.setThrowable(merged);
                }
            }
        }
    }
}