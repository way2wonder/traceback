package traceback.datasource;


import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;


public class InstanceDataSource extends  AbstractRoutingDataSource
{

    @Override
    protected Object determineCurrentLookupKey()
    {
        return HandleDataSource.getDataSource();
    }
}