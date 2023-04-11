public class Main {

  public static void main(String[] args) {
    System.out.println(sumDig(1234));
  }
  public static void printBin() {

  }
  int n = 1234;
  public static int sumDig(int n) {
    int result = n % 10;
    if ( n >= 10) {
      result += sumDig(n / 10);
    }
    return result;
  }
}