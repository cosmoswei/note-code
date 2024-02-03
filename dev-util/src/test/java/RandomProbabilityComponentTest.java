import com.wei.util.random.RandomProbabilityComponent;

import static org.junit.Assert.assertEquals;

public class RandomProbabilityComponentTest {
    public static void main(String[] args) {
        testRandomElement();
    }
    public static void testRandomElement() {
        double[] probabilities = {0.2, 0.59, 0.21};
        RandomProbabilityComponent component = new RandomProbabilityComponent(probabilities);

        int[] counts = new int[3]; // 记录每个元素出现的次数

        // 进行10000次随机选择
        for (int i = 0; i < 10000; i++) {
            int randomIndex = component.getRandomIndex();
            counts[randomIndex]++;
        }

        // 计算每个元素的实际概率
        double[] actualProbabilities = new double[3];
        for (int i = 0; i < actualProbabilities.length; i++) {
            actualProbabilities[i] = (double) counts[i] / 10000;
        }

        // 断言每个元素的实际概率接近于预期概率
        double[] expectedProbabilities = {0.2, 0.59, 0.21};
        double epsilon = 0.01; // 允许的误差范围

        for (int i = 0; i < expectedProbabilities.length; i++) {
            System.out.println("i = " + i);
            assertEquals(expectedProbabilities[i], actualProbabilities[i], epsilon);
        }
    }
}
