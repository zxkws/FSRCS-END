class TestIt
{
    public static void main ( String[] args )
    {
        int[] myArray = {1, 2, 3, 4, 5};
        System.out.println("1:"+myArray);
        ChangeIt.doIt( myArray );
        System.out.println("4:"+myArray);
        for(int j=0; j<myArray.length; j++)
            System.out.print( myArray[j] + " " );
    }
}
class ChangeIt
{
    static void doIt( int[] z )
    {
        System.out.println("2:"+z);
        int a[] =new int[]{2,2,222};
        z= a;
        System.out.println("3:"+z);
    }
}