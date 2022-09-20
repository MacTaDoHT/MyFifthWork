package MyFifthWork;

/*    Created by: Maxim.G, GU_QA_1883

   1. Реализовать сохранение данных в csv файл;
   2. Реализовать загрузку данных из csv файла. Файл читается целиком.
      Структура csv файла:
       | Строка заголовок с набором столбцов |
       | Набор строк с целочисленными значениями |
       | * Разделитель между столбцами - символ точка с запятой (;) |

      Пример:
        Value 1;Value 2;Value 3
        100;200;123
        300,400,500
      Для хранения данных использовать класс вида:
        public class AppData {
        private String[] header;
        private int[][] data;

        // ...
        }
      Если выполняется save(AppData data), то старые данные в файле полностью перезаписываются.
*/

public class Main {
    private static final String FILE_NAME = "appData.csv";
    private static final String CSV_SPLIT_BY = ";";


    public static void main(String[] args) {
        String[] testHeader = {"Value 1", "Value 2", "Value 3"};
        int[][] testData = {
                {100, 200, 123},
                {300, 400, 500}
        };
        AppData appData1 = new AppData(testHeader, testData);
        appData1.print();
        appData1.saveDataIntoCSV();
        appData1.getDataFromCSV(FILE_NAME, CSV_SPLIT_BY);
        appData1.print();
    }
}