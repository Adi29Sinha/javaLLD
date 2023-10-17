package misc;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/*
You have and array with 1 million elements, have to find out the sum of all elements in best possible time.
(Parllel processing using threadpool, create some 4 or 5 threads and divide the array in to equal halfs and process the sum)
write in java springboot using completable futures
 */

public class SumOfMillionValues {

    public long sumOfMillionValue() {
        // Create a large array with 1 million elements for demonstration
        int[] array = IntStream.range(1, 1000001).toArray();

        // Specify the number of threads to use
        int numThreads = 4;

        // Create a thread pool with the specified number of threads
        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);

        // Split the array into equal parts
        int chunkSize = array.length / numThreads;

        CompletableFuture<Long>[] futures = new CompletableFuture[numThreads];

        // Divide the array into equal parts and calculate the sum using CompletableFutures
        for (int i = 0; i < numThreads; i++) {
            int start = i * chunkSize;
            int end = (i == numThreads - 1) ? array.length : (i + 1) * chunkSize;
            int[] subArray = new int[end - start];
            System.arraycopy(array, start, subArray, 0, subArray.length);

            futures[i] = CompletableFuture.supplyAsync(() -> {
                long sum = 0;
                for (int num : subArray) {
                    sum += num;
                }
                return sum;
            }, executorService);
        }

        // Combine the results from all threads
        CompletableFuture<Long> result = CompletableFuture.allOf(futures)
                .thenApply(v -> {
                    long totalSum = 0;
                    for (CompletableFuture<Long> future : futures) {
                        try {
                            totalSum += future.get();
                        } catch (InterruptedException | ExecutionException e) {
                            e.printStackTrace();
                        }
                    }
                    return totalSum;
                });

        try {
            long totalSum = result.get();
            System.out.println("Sum of all elements: " + totalSum);
            return totalSum;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        // Shutdown the executor service
        executorService.shutdown();
        return 0;
    }
}
