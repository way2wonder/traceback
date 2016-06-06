package traceback.datasource;



public class HandleDataSource
{
    public static final ThreadLocal<String> holder = new ThreadLocal<String>();

    public static void putDataSource(String datasource)
    {
        holder.set(datasource);
    }

    public static String getDataSource()
    {
        return holder.get();
    }
    
    //清除数据源
    public static void clearDataSource() {
        holder.remove();
    }
}