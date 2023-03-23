public class App {
    public static void main(String args[]) {
        //лямбда выражения
        Operationable operation;

        operation = (x, y) -> x + y;
        int result = operation.calculate(10, 20);

        Operationable op = new Operationable() {
            @Override
            public int calculate(int x, int y) {
                return x*y;
            }
        };

        result = op.calculate(12,32);

        System.out.println(result);

        operation = (x, y) -> x - y;
        result = operation.calculate(120, 32);

        System.out.println(result);
    }
}
