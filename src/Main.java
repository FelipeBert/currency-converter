import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        String opcao = "";
        CurrencyQuery query = new CurrencyQuery();
        do{
            System.out.println("**********************************");
            System.out.println("Enter currency to be converted or type 'exit' to exit: ");
            String typeToConvert = scanner.nextLine();

            if(typeToConvert.equalsIgnoreCase("exit")){
                break;
            }

            System.out.println("Enter the type to which the currency will be converted: ");
            String typeConverted = scanner.nextLine();

            System.out.println("Enter the quantity to be converted: ");
            double value = scanner.nextDouble();
            scanner.nextLine();

            double convertedValue = query.conversionCalculation(typeToConvert, typeConverted, value);

            System.out.println(value + " " + typeToConvert + " to " + typeConverted + " = " + convertedValue);
            System.out.println("**********************************\n");
        }while (!opcao.equalsIgnoreCase("exit"));
    }
}