package com.wei.util.random;

import java.util.Random;

public class RandomProbabilityComponent {
    private double[] probabilities;
    private Random random;

    public RandomProbabilityComponent(double[] probabilities) {
        this.probabilities = probabilities;
        this.random = new Random();
    }

    public int getRandomIndex() {
        double randomValue = random.nextDouble();
        double cumulativeProbability = 0.0;

        for (int i = 0; i < probabilities.length; i++) {
            cumulativeProbability += probabilities[i];
            if (randomValue <= cumulativeProbability) {
                return i;
            }
        }

        // 如果概率总和小于1，或者发生了舍入误差，返回最后一个索引
        return probabilities.length - 1;
    }

    public static void main(String[] args) {
        double[] probabilities = {0.2, 0.3, 0.5};
        RandomProbabilityComponent component = new RandomProbabilityComponent(probabilities);

        // 生成10个随机索引
        for (int i = 0; i < 10; i++) {
            int randomIndex = component.getRandomIndex();
            System.out.println("Random Index: " + randomIndex);
        }
    }
}
