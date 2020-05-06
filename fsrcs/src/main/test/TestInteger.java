class Main
{
    public static void main(String[] args)
    {
        String str = "runoob";
        str += "wwwrunoobcom";
        String string = str.substring(2,13);
        string = string + str.charAt(4);
        System.out.println(string);
    }
}