package org.prime;

import java.util.Arrays;

/**
 * @author chenjianrong-lhq 2019年03月09日 16:16:50
 * @Description:
 * @ClassName: PrimeUtilTest
 */
public class PrimeUtil {

    public static void main(String[] args) {
        getPrimes(100);
    }

    public static int[] getPrimesPrv(int max) {

        if (max <= 2) {
            return new int[]{};
        } else {
            int n, size = 0;
            int[] arr = new int[max];
            for (int m = 2; m < max; m++) {
                for (n = 2; n < m / 2 + 1; n++) {
                    if (m % n == 0) {
                        break;
                    }
                }
                if (n == m / 2 + 1) {
                    arr[size++] = m;
                }
            }
            arr = Arrays.copyOf(arr, size);
            return arr;
        }
    }

    public static int[] getPrimes(int max) {

        if (max <= 2) {
            return new int[]{};
        }
        int size = 0;
        int[] primes = new int[max];
        for (int num = 2; num < max; num++) {
            if (isPrime(num)) {
                primes[size++] = num;
            }
        }
        primes = Arrays.copyOf(primes, size);
        return primes;
    }

    private static boolean isPrime(int num) {

        for (int i = 2; i < num / 2 + 1; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}
