package com.wei.util.random;

public class RandomElementPicker {
    private RandomProbabilityComponent component;

    public RandomElementPicker() {
        double[] probabilities = {0.2, 0.59, 0.21};
        component = new RandomProbabilityComponent(probabilities);
    }

    public String getRandomElement() {
        String[] elements = {"A", "B", "C"};
        int randomIndex = component.getRandomIndex();
        return elements[randomIndex];
    }

    public static void main(String[] args) {
        RandomElementPicker picker = new RandomElementPicker();

        // 获取10个随机元素
        for (int i = 0; i < 10; i++) {
            String randomElement = picker.getRandomElement();
            System.out.println("Random Element: " + randomElement);
        }
    }
}

