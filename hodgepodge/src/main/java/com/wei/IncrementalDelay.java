package com.wei;

public class IncrementalDelay {
    private static final long MAX_WAIT_INTERVAL = 10;
    private static final int MAX_RETRIES = 20;

    public enum Results {
        SUCCESS,
        NOT_READY,
        THROTTLED,
        SERVER_ERROR
    }

    /*
     * Performs an asynchronous operation, then polls for the result of the
     * operation using an incremental delay.
     */
    public static void doOperationAndWaitForResult() {
        try {
            // Do some asynchronous operation.
            long token = asyncOperation();
            int retries = 0;
            boolean retry = false;
            do {
                long waitTime = Math.min(getWaitTimeExp(retries), MAX_WAIT_INTERVAL);
                System.out.print(waitTime + "\n");
                // Wait for the result.
                Thread.sleep(waitTime);
                // Get the result of the asynchronous operation.
                Results result = getAsyncOperationResult(token);
                if (Results.SUCCESS == result) {
                    retry = false;
                } else if (Results.NOT_READY == result) {
                    retry = true;
                } else if (Results.THROTTLED == result) {
                    retry = true;
                } else if (Results.SERVER_ERROR == result) {
                    retry = true;
                } else {
                    // Some other error occurred, so stop calling the API.
                    retry = false;
                }
            } while (retry && (retries++ < MAX_RETRIES));
        } catch (Exception ex) {
        }
    }

    private static Results getAsyncOperationResult(long token) {
        return null;
    }

    private static long asyncOperation() {
        return 1;
    }

    /*
     * Returns the next wait interval, in milliseconds, using an exponential
     * backoff algorithm.
     */
    public static long getWaitTimeExp(int retryCount) {
        long waitTime = ((long) Math.pow(2, retryCount) * 100L);
        return waitTime;
    }
}
